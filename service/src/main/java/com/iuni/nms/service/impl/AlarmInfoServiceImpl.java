package com.iuni.nms.service.impl;

import com.iuni.nms.persist.domain.AlarmInfo;
import com.iuni.nms.persist.domain.QAlarmInfo;
import com.iuni.nms.persist.repository.AlarmInfoRepository;
import com.iuni.nms.service.AlarmInfoService;
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
public class AlarmInfoServiceImpl implements AlarmInfoService {

    private static final Logger logger = LoggerFactory.getLogger(AlarmInfoService.class);

    @Autowired
    private AccountService accountService;
    @Autowired
    private AlarmInfoRepository alarmInfoRepository;

    @Override
    public AlarmInfo getById(Long id) {
        AlarmInfo alarmInfo;
        try {
            alarmInfo = alarmInfoRepository.findOne(id);
        } catch (Exception e) {
            logger.error("get alarmInfo by id error. msg:" + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
        return alarmInfo;
    }

    @Override
    public List<AlarmInfo> listAlarmInfo(AlarmInfo alarmInfo) {
        List<AlarmInfo> alarmInfoList = new ArrayList<>();
        QAlarmInfo qAlarmInfo = QAlarmInfo.alarmInfo;
        BooleanExpression booleanExpression = generateExpression(qAlarmInfo, alarmInfo);
        Iterable<AlarmInfo> iterable = alarmInfoRepository.findAll(booleanExpression);
        Iterator<AlarmInfo> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            AlarmInfo nAlarmInfo = iterator.next();
            alarmInfoList.add(nAlarmInfo);
        }
        return alarmInfoList;
    }

    @Override
    public void addAlarmInfo(AlarmInfo alarmInfo) {
        alarmInfo.setBasicInfoForCreate(accountService.getCurrentUser().getLoginName());
        saveAlarmInfo(alarmInfo);
    }

    @Override
    public void updateAlarmInfo(AlarmInfo alarmInfo) {
        AlarmInfo oldAlarmInfo = getById(alarmInfo.getId());
        if (oldAlarmInfo != null) {
            alarmInfo.setCreateBy(oldAlarmInfo.getCreateBy());
            alarmInfo.setCreateDate(oldAlarmInfo.getCreateDate());
            alarmInfo.setStatus(oldAlarmInfo.getStatus());
            alarmInfo.setBasicInfoForUpdate(accountService.getCurrentUser().getLoginName());
        }
        saveAlarmInfo(alarmInfo);
    }

    @Override
    public void deleteAlarmInfo(String ids) {
        List<AlarmInfo> alarmInfoList = new ArrayList<>();
        try {
            String[] idArray = ids.split(",");
            for (String id : idArray) {
                if (!StringUtils.isNumeric(id))
                    continue;
                AlarmInfo alarmInfo = getById(Long.parseLong(id));
                if (alarmInfo != null) {
                    alarmInfo.setBasicInfoForCancel(accountService.getCurrentUser().getLoginName());
                    alarmInfoList.add(alarmInfo);
                }
            }
            alarmInfoRepository.save(alarmInfoList);
        } catch (Exception e) {
            logger.error("logic delete alarmInfo error. msg: {}", e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    private void saveAlarmInfo(AlarmInfo alarmInfo) {
        try {
            alarmInfoRepository.save(alarmInfo);
        } catch (Exception e) {
            logger.error("save alarmInfo error, msg: ", e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    private void saveAlarmInfo(List<AlarmInfo> alarmInfoList) {
        try {
            alarmInfoRepository.save(alarmInfoList);
        } catch (Exception e) {
            logger.error("save alarmInfo error, msg: ", e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    private BooleanExpression generateExpression(QAlarmInfo qAlarmInfo, AlarmInfo alarmInfo) {
        BooleanExpression booleanExpression = null;
        if (alarmInfo.getCancelFlag() != null) {
            BooleanExpression cancelBooleanExpression = qAlarmInfo.cancelFlag.eq(alarmInfo.getCancelFlag());
            booleanExpression = (booleanExpression == null ? cancelBooleanExpression : booleanExpression.and(cancelBooleanExpression));
        }
        if (alarmInfo.getStatus() != null) {
            BooleanExpression statusBooleanExpression = qAlarmInfo.status.eq(alarmInfo.getStatus());
            booleanExpression = (booleanExpression == null ? statusBooleanExpression : booleanExpression.and(statusBooleanExpression));
        }
        if (alarmInfo.getAlarmItemGroup().getAlarmGroup().getId() != 0) {
            BooleanExpression groupBooleanExpression = qAlarmInfo.alarmItemGroup.alarmGroup.id.eq(alarmInfo.getAlarmItemGroup().getAlarmGroup().getId());
            booleanExpression = (booleanExpression == null ? groupBooleanExpression : booleanExpression.and(groupBooleanExpression));
        }
        if (StringUtils.isNotBlank(alarmInfo.getInfo())) {
            BooleanExpression infoBooleanExpression = qAlarmInfo.info.like("%" + alarmInfo.getInfo() + "%");
            booleanExpression = (booleanExpression == null ? infoBooleanExpression : booleanExpression.and(infoBooleanExpression));
        }
        return booleanExpression;
    }

}
