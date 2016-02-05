package com.iuni.nms.service.impl;

import com.iuni.nms.persist.domain.MonitorObject;
import com.iuni.nms.persist.domain.MonitorObjectItem;
import com.iuni.nms.persist.domain.QMonitorObject;
import com.iuni.nms.persist.repository.MonitorItemRepository;
import com.iuni.nms.persist.repository.MonitorObjectItemRepository;
import com.iuni.nms.persist.repository.MonitorObjectRepository;
import com.iuni.nms.service.MonitorObjectService;
import com.iuni.nms.sso.service.AccountService;
import com.mysema.query.types.expr.BooleanExpression;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author zowie
 *         Email: nicholas@iuni.com
 */
@Service
public class MonitorObjectServiceImpl implements MonitorObjectService {

    private static final Logger logger = LoggerFactory.getLogger(MonitorObjectService.class);

    @Autowired
    private AccountService accountService;
    @Autowired
    private MonitorObjectRepository monitorObjectRepository;
    @Autowired
    private MonitorItemRepository monitorItemRepository;
    @Autowired
    private MonitorObjectItemRepository monitorObjectItemRepository;

    @Override
    public MonitorObject getById(Long id) {
        MonitorObject monitorObject;
        try {
            monitorObject = monitorObjectRepository.findOne(id);
            checkMonitorObjectItem(monitorObject);
        } catch (Exception e) {
            logger.error("get monitorObject by id error. msg:" + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
        return monitorObject;
    }

    @Override
    public MonitorObject getByCode(String code) {
        MonitorObject monitorObject = null;
        try {
            monitorObject = monitorObjectRepository.findByCode(code);
        } catch (Exception e) {
            logger.error("get monitorObject by code error. msg:" + e.getLocalizedMessage());
        }
        return monitorObject;
    }

    @Override
    public List<MonitorObject> listMonitorObject(MonitorObject monitorObject) {
        List<MonitorObject> monitorObjectList = new ArrayList<>();
        QMonitorObject qMonitorObject = QMonitorObject.monitorObject;
        BooleanExpression booleanExpression = generateExpression(qMonitorObject, monitorObject);
        Iterable<MonitorObject> iterable = monitorObjectRepository.findAll(booleanExpression);
        Iterator<MonitorObject> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            MonitorObject nMonitorObject = iterator.next();
            monitorObjectList.add(nMonitorObject);
        }
        return monitorObjectList;
    }

    @Override
    @Transactional
    public void addMonitorObject(MonitorObject monitorObject) {
        monitorObject.setBasicInfoForCreate(accountService.getCurrentUser().getLoginName());
        saveMonitorObject(monitorObject);
    }

    @Override
    @Transactional
    public void updateMonitorObject(MonitorObject monitorObject) {
        MonitorObject oldMonitorObject = getById(monitorObject.getId());
        if (oldMonitorObject != null) {
            monitorObject.setCreateBy(oldMonitorObject.getCreateBy());
            monitorObject.setCreateDate(oldMonitorObject.getCreateDate());
            monitorObject.setStatus(oldMonitorObject.getStatus());
            monitorObject.setMonitorStatus(oldMonitorObject.getMonitorStatus());
            monitorObject.setAlarmStatus(oldMonitorObject.getAlarmStatus());
            monitorObject.setHealthyStatus(oldMonitorObject.getHealthyStatus());
            monitorObject.setMonitorItems(oldMonitorObject.getMonitorItems());
            monitorObject.setBasicInfoForUpdate(accountService.getCurrentUser().getLoginName());
        }
        saveMonitorObject(monitorObject);
    }

    @Override
    @Transactional
    public void deleteMonitorObject(String ids) {
        String[] idArray = ids.split(",");
        List<MonitorObject> monitorObjectList = new ArrayList<>();
        for (String id : idArray) {
            if (!StringUtils.isNumeric(id))
                continue;
            MonitorObject monitorObject = getById(Long.parseLong(id));
            if (monitorObject != null) {
                monitorObject.setBasicInfoForCancel(accountService.getCurrentUser().getLoginName());
                monitorObjectList.add(monitorObject);
            }
        }
        saveMonitorObject(monitorObjectList);
    }

    @Override
    @Transactional
    public void enableMonitorObject(String ids) {
        // todo enable
    }

    @Override
    @Transactional
    public void disableMonitorObject(String ids) {
        // todo disable
    }

    private void saveMonitorObject(MonitorObject monitorObject) {
        MonitorObject savedMonitorObject = monitorObjectRepository.save(monitorObject);
        // update old relationships to invalid
        if (monitorObject.getMonitorObjectItems() != null && monitorObject.getMonitorObjectItems().size() != 0) {
            List<MonitorObjectItem> oldMonitorObjectItemList = new ArrayList<>();
            for (MonitorObjectItem monitorObjectItem : monitorObject.getMonitorObjectItems()) {
                monitorObjectItem.setBasicInfoForCancel(accountService.getCurrentUser().getLoginName());
                oldMonitorObjectItemList.add(monitorObjectItem);
            }
            monitorObjectItemRepository.save(oldMonitorObjectItemList);
        }
        // save the relationships of monitor object with monitor item
        List<MonitorObjectItem> monitorObjectItemList = new ArrayList<>();
        for (Long monitorItemId : monitorObject.getMonitorItems()) {
            MonitorObjectItem monitorObjectItem = new MonitorObjectItem();
            monitorObjectItem.setBasicInfoForCreate(accountService.getCurrentUser().getLoginName());
            monitorObjectItem.setMonitorItem(monitorItemRepository.findOne(monitorItemId));
            monitorObjectItem.setMonitorObject(savedMonitorObject);
            monitorObjectItemList.add(monitorObjectItem);
        }
        monitorObjectItemRepository.save(monitorObjectItemList);
    }

    private void saveMonitorObject(List<MonitorObject> monitorObjectList) {
        for (MonitorObject monitorObject : monitorObjectList) {
            saveMonitorObject(monitorObject);
        }
    }

    private BooleanExpression generateExpression(QMonitorObject qMonitorObject, MonitorObject monitorObject) {
        BooleanExpression booleanExpression = null;
        if (monitorObject.getCancelFlag() != null) {
            BooleanExpression cancelBooleanExpression = qMonitorObject.cancelFlag.eq(monitorObject.getCancelFlag());
            booleanExpression = (booleanExpression == null ? cancelBooleanExpression : booleanExpression.and(cancelBooleanExpression));
        }
        if (monitorObject.getStatus() != null) {
            BooleanExpression statusBooleanExpression = qMonitorObject.status.eq(monitorObject.getStatus());
            booleanExpression = (booleanExpression == null ? statusBooleanExpression : booleanExpression.and(statusBooleanExpression));
        }
        if (StringUtils.isNotBlank(monitorObject.getName())) {
            BooleanExpression nameBooleanExpression = qMonitorObject.name.like("%" + monitorObject.getName() + "%");
            booleanExpression = (booleanExpression == null ? nameBooleanExpression : booleanExpression.and(nameBooleanExpression));
        }
        if (StringUtils.isNotBlank(monitorObject.getCode())) {
            BooleanExpression codeBooleanExpression = qMonitorObject.code.like("%" + monitorObject.getCode() + "%");
            booleanExpression = (booleanExpression == null ? codeBooleanExpression : booleanExpression.and(codeBooleanExpression));
        }
        if (monitorObject.getMonitorObjectType() != null && monitorObject.getMonitorObjectType().getId() != 0) {
            BooleanExpression objectTypeBooleanExpression = qMonitorObject.monitorObjectType.id.eq(monitorObject.getMonitorObjectType().getId());
            booleanExpression = (booleanExpression == null ? objectTypeBooleanExpression : booleanExpression.and(objectTypeBooleanExpression));
        }
        if (monitorObject.getModule() != null && monitorObject.getModule().getId() != 0) {
            BooleanExpression moduleBooleanExpression = qMonitorObject.module.id.eq(monitorObject.getModule().getId());
            booleanExpression = (booleanExpression == null ? moduleBooleanExpression : booleanExpression.and(moduleBooleanExpression));
            if (monitorObject.getModule().getSystem() != null && monitorObject.getModule().getSystem().getId() != 0) {
                BooleanExpression systemBooleanExpression = qMonitorObject.module.system.id.eq(monitorObject.getModule().getSystem().getId());
                booleanExpression = (booleanExpression == null ? systemBooleanExpression : booleanExpression.and(systemBooleanExpression));
            }
        }
        return booleanExpression;
    }

    /**
     * 检查关系是否有效，剔除无效关系
     *
     * @param monitorObject
     */
    private void checkMonitorObjectItem(MonitorObject monitorObject) {
        if (monitorObject.getMonitorObjectItems() == null)
            return;
        List<MonitorObjectItem> validMonitorObjectItems = new ArrayList<>();
        List<MonitorObjectItem> inValidMonitorObjectItems = new ArrayList<>();
        for (MonitorObjectItem monitorObjectItem : monitorObject.getMonitorObjectItems()) {
            if (!(monitorObjectItem.getCancelFlag() == 0 && monitorObjectItem.getStatus() == 1)) {
                inValidMonitorObjectItems.add(monitorObjectItem);
                continue;
            }
            validMonitorObjectItems.add(monitorObjectItem);
        }
        monitorObject.getMonitorObjectItems().removeAll(inValidMonitorObjectItems);
        Long[] monitorItems = new Long[validMonitorObjectItems.size()];
        for (int i = 0; i < validMonitorObjectItems.size(); i++)
            monitorItems[i] = validMonitorObjectItems.get(i).getMonitorItem().getId();
        monitorObject.setMonitorItems(monitorItems);
    }

}
