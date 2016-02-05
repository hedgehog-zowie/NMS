package com.iuni.nms.service.impl;

import com.iuni.nms.persist.domain.*;
import com.iuni.nms.persist.repository.*;
import com.iuni.nms.service.AlarmGroupService;
import com.iuni.nms.sso.service.AccountService;
import com.mysema.query.types.expr.BooleanExpression;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author zowie
 *         Email: nicholas@iuni.com
 */
@Service
public class AlarmGroupServiceImpl implements AlarmGroupService {

    private static final Logger logger = LoggerFactory.getLogger(AlarmGroupService.class);

    @Autowired
    private AccountService accountService;
    @Autowired
    private AlarmGroupRepository alarmGroupRepository;
    @Autowired
    private AlarmPersonRepository alarmPersonRepository;
    @Autowired
    private AlarmGroupPersonRepository alarmGroupPersonRepository;
    @Autowired
    private AlarmItemGroupRepository alarmItemGroupRepository;
    @Autowired
    private MonitorItemRepository monitorItemRepository;

    @Override
    public AlarmGroup getById(Long id) {
        AlarmGroup alarmGroup;
        try {
            alarmGroup = alarmGroupRepository.findOne(id);
            checkAlarmGroupPerson(alarmGroup);
            checkAlarmItemGroup(alarmGroup);
        } catch (Exception e) {
            logger.error("get alarmGroup by id error. msg:" + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
        return alarmGroup;
    }

    @Override
    public List<AlarmGroup> listAlarmGroup(AlarmGroup alarmGroup) {
        List<AlarmGroup> alarmGroupList = new ArrayList<>();
        QAlarmGroup qAlarmGroup = QAlarmGroup.alarmGroup;
        BooleanExpression booleanExpression = generateExpression(qAlarmGroup, alarmGroup);
        Iterable<AlarmGroup> iterable = alarmGroupRepository.findAll(booleanExpression);
        Iterator<AlarmGroup> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            AlarmGroup nAlarmGroup = iterator.next();
            alarmGroupList.add(nAlarmGroup);
        }
        return alarmGroupList;
    }

    @Override
    @Transactional
    public void addAlarmGroup(AlarmGroup alarmGroup) {
        alarmGroup.setBasicInfoForCreate(accountService.getCurrentUser().getLoginName());
        saveAlarmGroup(alarmGroup);
    }

    @Override
    @Transactional
    public void updateAlarmGroup(AlarmGroup alarmGroup) {
        AlarmGroup oldAlarmGroup = getById(alarmGroup.getId());
        if (oldAlarmGroup != null) {
            alarmGroup.setCreateBy(oldAlarmGroup.getCreateBy());
            alarmGroup.setCreateDate(oldAlarmGroup.getCreateDate());
            alarmGroup.setStatus(oldAlarmGroup.getStatus());
            alarmGroup.setAlarmGroupPersons(oldAlarmGroup.getAlarmGroupPersons());
            alarmGroup.setAlarmItemGroups(oldAlarmGroup.getAlarmItemGroups());
            alarmGroup.setBasicInfoForUpdate(accountService.getCurrentUser().getLoginName());
        }
        saveAlarmGroup(alarmGroup);
    }

    @Override
    @Transactional
    public void deleteAlarmGroup(String ids) {
        List<AlarmGroup> alarmGroupList = new ArrayList<>();
        try {
            String[] idArray = ids.split(",");
            for (String id : idArray) {
                if (!StringUtils.isNumeric(id))
                    continue;
                AlarmGroup alarmGroup = getById(Long.parseLong(id));
                if (alarmGroup != null) {
                    alarmGroup.setBasicInfoForCancel(accountService.getCurrentUser().getLoginName());
                    alarmGroupList.add(alarmGroup);
                }
            }
            saveAlarmGroup(alarmGroupList);
        } catch (Exception e) {
            logger.error("logic delete alarmGroup error. msg: {}", e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    private void saveAlarmGroup(AlarmGroup alarmGroup) {
        AlarmGroup savedAlarmGroup = alarmGroupRepository.save(alarmGroup);
        // update old relationships to invalid
        if (alarmGroup.getAlarmGroupPersons() != null && alarmGroup.getAlarmGroupPersons().size() != 0) {
            List<AlarmGroupPerson> oldAlarmGroupPersonList = new ArrayList<>();
            for (AlarmGroupPerson alarmGroupPerson : alarmGroup.getAlarmGroupPersons()) {
                alarmGroupPerson.setBasicInfoForCancel(accountService.getCurrentUser().getLoginName());
                oldAlarmGroupPersonList.add(alarmGroupPerson);
            }
            alarmGroupPersonRepository.save(oldAlarmGroupPersonList);
        }
        if(alarmGroup.getAlarmItemGroups() != null && alarmGroup.getAlarmItemGroups().size() != 0){
            List<AlarmItemGroup> oldAlarmItemGroupList = new ArrayList<>();
            for(AlarmItemGroup alarmItemGroup: alarmGroup.getAlarmItemGroups()){
                alarmItemGroup.setBasicInfoForCancel(accountService.getCurrentUser().getLoginName());
                oldAlarmItemGroupList.add(alarmItemGroup);
            }
            alarmItemGroupRepository.save(oldAlarmItemGroupList);
        }
        // save the relationships
        // relationship of group with person
        List<AlarmGroupPerson> alarmGroupPersonList = new ArrayList<>();
        for (Long personId : alarmGroup.getAlarmPersons()) {
            AlarmGroupPerson alarmGroupPerson = new AlarmGroupPerson();
            alarmGroupPerson.setBasicInfoForCreate(accountService.getCurrentUser().getLoginName());
            alarmGroupPerson.setAlarmPerson(alarmPersonRepository.findOne(personId));
            alarmGroupPerson.setAlarmGroup(savedAlarmGroup);
            alarmGroupPersonList.add(alarmGroupPerson);
        }
        alarmGroupPersonRepository.save(alarmGroupPersonList);
        // relationship of group with item
        List<AlarmItemGroup> alarmItemGroupList = new ArrayList<>();
        for(Long itemId: alarmGroup.getMonitorItems()){
            AlarmItemGroup alarmItemGroup = new AlarmItemGroup();
            alarmItemGroup.setBasicInfoForCreate(accountService.getCurrentUser().getLoginName());
            alarmItemGroup.setMonitorItem(monitorItemRepository.findOne(itemId));
            alarmItemGroup.setAlarmGroup(savedAlarmGroup);
            alarmItemGroupList.add(alarmItemGroup);
        }
        alarmItemGroupRepository.save(alarmItemGroupList);
    }

    private void saveAlarmGroup(List<AlarmGroup> alarmGroupList) {
        for (AlarmGroup alarmGroup : alarmGroupList)
            saveAlarmGroup(alarmGroup);
    }

    private BooleanExpression generateExpression(QAlarmGroup qAlarmGroup, AlarmGroup alarmGroup) {
        BooleanExpression booleanExpression = null;
        if (alarmGroup.getCancelFlag() != null) {
            BooleanExpression cancelBooleanExpression = qAlarmGroup.cancelFlag.eq(alarmGroup.getCancelFlag());
            booleanExpression = (booleanExpression == null ? cancelBooleanExpression : booleanExpression.and(cancelBooleanExpression));
        }
        if (alarmGroup.getStatus() != null) {
            BooleanExpression statusBooleanExpression = qAlarmGroup.status.eq(alarmGroup.getStatus());
            booleanExpression = (booleanExpression == null ? statusBooleanExpression : booleanExpression.and(statusBooleanExpression));
        }
        if (StringUtils.isNotBlank(alarmGroup.getName())) {
            BooleanExpression nameBooleanExpression = qAlarmGroup.name.like("%" + alarmGroup.getName() + "%");
            booleanExpression = (booleanExpression == null ? nameBooleanExpression : booleanExpression.and(nameBooleanExpression));
        }
        return booleanExpression;
    }

    /**
     * 检查告警组与告警人关系
     */
    private void checkAlarmGroupPerson(AlarmGroup alarmGroup) {
        if (alarmGroup.getAlarmGroupPersons() == null)
            return;
        List<AlarmGroupPerson> validAlarmGroupPersons = new ArrayList<>();
        List<AlarmGroupPerson> inValidAlarmGroupPersons = new ArrayList<>();
        for (AlarmGroupPerson alarmGroupPerson : alarmGroup.getAlarmGroupPersons()) {
            if (!(alarmGroupPerson.getCancelFlag() == 0 && alarmGroupPerson.getStatus() == 1)) {
                inValidAlarmGroupPersons.add(alarmGroupPerson);
                continue;
            }
            validAlarmGroupPersons.add(alarmGroupPerson);
        }
        alarmGroup.getAlarmGroupPersons().removeAll(inValidAlarmGroupPersons);
        Long[] alarmPersons = new Long[validAlarmGroupPersons.size()];
        for (int i = 0; i < validAlarmGroupPersons.size(); i++)
            alarmPersons[i] = validAlarmGroupPersons.get(i).getAlarmPerson().getId();
        alarmGroup.setAlarmPersons(alarmPersons);
    }

    /**
     * 检查告警组与监控项的关系
     */
    private void checkAlarmItemGroup(AlarmGroup alarmGroup) {
        if (alarmGroup.getAlarmItemGroups() == null)
            return;
        List<AlarmItemGroup> validAlarmItemGroupList = new ArrayList<>();
        List<AlarmItemGroup> inValidAlarmItemGroupList = new ArrayList<>();
        for (AlarmItemGroup alarmItemGroup : alarmGroup.getAlarmItemGroups()) {
            if (!(alarmItemGroup.getCancelFlag() == 0 && alarmItemGroup.getStatus() == 1)) {
                inValidAlarmItemGroupList.add(alarmItemGroup);
                continue;
            }
            validAlarmItemGroupList.add(alarmItemGroup);
        }
        alarmGroup.getAlarmItemGroups().removeAll(inValidAlarmItemGroupList);
        Long[] monitorItems = new Long[validAlarmItemGroupList.size()];
        for (int i = 0; i < validAlarmItemGroupList.size(); i++)
            monitorItems[i] = validAlarmItemGroupList.get(i).getMonitorItem().getId();
        alarmGroup.setMonitorItems(monitorItems);
    }

}
