package com.iuni.nms.service.impl;

import com.iuni.nms.persist.domain.AlarmPerson;
import com.iuni.nms.persist.domain.QAlarmPerson;
import com.iuni.nms.persist.repository.AlarmPersonRepository;
import com.iuni.nms.service.AlarmPersonService;
import com.iuni.nms.sso.service.AccountService;
import com.mysema.query.types.expr.BooleanExpression;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author zowie
 *         Email: nicholas@iuni.com
 */
@Service
public class AlarmPersonServiceImpl implements AlarmPersonService {

    private static final Logger logger = LoggerFactory.getLogger(AlarmPersonService.class);

    @Autowired
    private AccountService accountService;
    @Autowired
    private AlarmPersonRepository alarmPersonRepository;

    @Override
    public AlarmPerson getById(Long id) {
        AlarmPerson alarmPerson;
        try {
            alarmPerson = alarmPersonRepository.findOne(id);
        } catch (Exception e) {
            logger.error("get alarmPerson by id error. msg:" + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
        return alarmPerson;
    }

    @Override
    public List<AlarmPerson> listAlarmPerson(AlarmPerson alarmPerson) {
        List<AlarmPerson> alarmPersonList = new ArrayList<>();
        QAlarmPerson qAlarmPerson = QAlarmPerson.alarmPerson;
        BooleanExpression booleanExpression = generateExpression(qAlarmPerson, alarmPerson);
        Iterable<AlarmPerson> iterable = alarmPersonRepository.findAll(booleanExpression);
        Iterator<AlarmPerson> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            AlarmPerson nAlarmPerson = iterator.next();
            alarmPersonList.add(nAlarmPerson);
        }
        return alarmPersonList;
    }

    @Override
    public void addAlarmPerson(AlarmPerson alarmPerson) {
        alarmPerson.setBasicInfoForCreate(accountService.getCurrentUser().getLoginName());
        saveAlarmPerson(alarmPerson);
    }

    @Override
    public void updateAlarmPerson(AlarmPerson alarmPerson) {
        AlarmPerson oldAlarmPerson = getById(alarmPerson.getId());
        if (oldAlarmPerson != null) {
            alarmPerson.setCreateBy(oldAlarmPerson.getCreateBy());
            alarmPerson.setCreateDate(oldAlarmPerson.getCreateDate());
            alarmPerson.setStatus(oldAlarmPerson.getStatus());
            alarmPerson.setBasicInfoForUpdate(accountService.getCurrentUser().getLoginName());
        }
        saveAlarmPerson(alarmPerson);
    }

    @Override
    public void deleteAlarmPerson(String ids) {
        List<AlarmPerson> alarmPersonList = new ArrayList<>();
        try {
            String[] idArray = ids.split(",");
            for (String id : idArray) {
                if (!StringUtils.isNumeric(id))
                    continue;
                AlarmPerson alarmPerson = getById(Long.parseLong(id));
                if (alarmPerson != null) {
                    alarmPerson.setBasicInfoForCancel(accountService.getCurrentUser().getLoginName());
                    alarmPersonList.add(alarmPerson);
                }
            }
            alarmPersonRepository.save(alarmPersonList);
        } catch (Exception e) {
            logger.error("logic delete alarmPerson error. msg: {}", e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    private void saveAlarmPerson(AlarmPerson alarmPerson) {
        try {
            alarmPersonRepository.save(alarmPerson);
        } catch (Exception e) {
            logger.error("save alarmPerson error, msg: ", e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    private void saveAlarmPerson(List<AlarmPerson> alarmPersonList) {
        try {
            alarmPersonRepository.save(alarmPersonList);
        } catch (Exception e) {
            logger.error("save alarmPerson error, msg: ", e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    private BooleanExpression generateExpression(QAlarmPerson qAlarmPerson, AlarmPerson alarmPerson) {
        BooleanExpression booleanExpression = null;
        if (alarmPerson.getCancelFlag() != null) {
            BooleanExpression cancelBooleanExpression = qAlarmPerson.cancelFlag.eq(alarmPerson.getCancelFlag());
            booleanExpression = (booleanExpression == null ? cancelBooleanExpression : booleanExpression.and(cancelBooleanExpression));
        }
        if (alarmPerson.getStatus() != null) {
            BooleanExpression statusBooleanExpression = qAlarmPerson.status.eq(alarmPerson.getStatus());
            booleanExpression = (booleanExpression == null ? statusBooleanExpression : booleanExpression.and(statusBooleanExpression));
        }
        if (StringUtils.isNotBlank(alarmPerson.getName())) {
            BooleanExpression nameBooleanExpression = qAlarmPerson.name.like("%" + alarmPerson.getName() + "%");
            booleanExpression = (booleanExpression == null ? nameBooleanExpression : booleanExpression.and(nameBooleanExpression));
        }
        if (StringUtils.isNotBlank(alarmPerson.getEmail())) {
            BooleanExpression emailBooleanExpression = qAlarmPerson.email.like("%" + alarmPerson.getEmail() + "%");
            booleanExpression = (booleanExpression == null ? emailBooleanExpression : booleanExpression.and(emailBooleanExpression));
        }
        if (StringUtils.isNotBlank(alarmPerson.getMobile())) {
            BooleanExpression mobileBooleanExpression = qAlarmPerson.mobile.like("%" + alarmPerson.getMobile() + "%");
            booleanExpression = (booleanExpression == null ? mobileBooleanExpression : booleanExpression.and(mobileBooleanExpression));
        }
        return booleanExpression;
    }

}
