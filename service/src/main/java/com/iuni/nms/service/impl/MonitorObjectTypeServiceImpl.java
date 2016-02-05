package com.iuni.nms.service.impl;

import com.iuni.nms.persist.domain.MonitorObjectType;
import com.iuni.nms.persist.domain.QMonitorObjectType;
import com.iuni.nms.persist.repository.MonitorObjectTypeRepository;
import com.iuni.nms.service.MonitorObjectTypeService;
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
public class MonitorObjectTypeServiceImpl implements MonitorObjectTypeService {

    private static final Logger logger = LoggerFactory.getLogger(MonitorObjectTypeService.class);

    @Autowired
    private AccountService accountService;
    @Autowired
    private MonitorObjectTypeRepository monitorObjectTypeRepository;

    @Override
    public MonitorObjectType getById(Long id) {
        MonitorObjectType monitorObjectType;
        try {
            monitorObjectType = monitorObjectTypeRepository.findOne(id);
        } catch (Exception e) {
            logger.error("get monitorObjectType by id error. msg:" + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
        return monitorObjectType;
    }

    @Override
    public List<MonitorObjectType> listMonitorObjectType(MonitorObjectType monitorObjectType) {
        List<MonitorObjectType> monitorObjectTypeList = new ArrayList<>();
        QMonitorObjectType qMonitorObjectType = QMonitorObjectType.monitorObjectType;
        BooleanExpression booleanExpression = generateExpression(qMonitorObjectType, monitorObjectType);
        Iterable<MonitorObjectType> iterable = monitorObjectTypeRepository.findAll(booleanExpression);
        Iterator<MonitorObjectType> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            MonitorObjectType nMonitorObjectType = iterator.next();
            monitorObjectTypeList.add(nMonitorObjectType);
        }
        return monitorObjectTypeList;
    }

    @Override
    public void addMonitorObjectType(MonitorObjectType monitorObjectType) {
        monitorObjectType.setBasicInfoForCreate(accountService.getCurrentUser().getLoginName());
        saveMonitorObjectType(monitorObjectType);
    }

    @Override
    public void updateMonitorObjectType(MonitorObjectType monitorObjectType) {
        MonitorObjectType oldMonitorObjectType = getById(monitorObjectType.getId());
        if (oldMonitorObjectType != null) {
            monitorObjectType.setCreateBy(oldMonitorObjectType.getCreateBy());
            monitorObjectType.setCreateDate(oldMonitorObjectType.getCreateDate());
            monitorObjectType.setStatus(oldMonitorObjectType.getStatus());
            monitorObjectType.setBasicInfoForUpdate(accountService.getCurrentUser().getLoginName());
        }
        saveMonitorObjectType(monitorObjectType);
    }

    @Override
    public void deleteMonitorObjectType(String ids) {
        List<MonitorObjectType> monitorObjectTypeList = new ArrayList<>();
        try {
            String[] idArray = ids.split(",");
            for (String id : idArray) {
                if (!StringUtils.isNumeric(id))
                    continue;
                MonitorObjectType monitorObjectType = getById(Long.parseLong(id));
                if (monitorObjectType != null) {
                    monitorObjectType.setBasicInfoForCancel(accountService.getCurrentUser().getLoginName());
                    monitorObjectTypeList.add(monitorObjectType);
                }
            }
            monitorObjectTypeRepository.save(monitorObjectTypeList);
        } catch (Exception e) {
            logger.error("logic delete monitorObjectType error. msg: {}", e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    private void saveMonitorObjectType(MonitorObjectType monitorObjectType) {
        try {
            monitorObjectTypeRepository.save(monitorObjectType);
        } catch (Exception e) {
            logger.error("save monitorObjectType error, msg: ", e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    private void saveMonitorObjectType(List<MonitorObjectType> monitorObjectTypeList) {
        try {
            monitorObjectTypeRepository.save(monitorObjectTypeList);
        } catch (Exception e) {
            logger.error("save monitorObjectType error, msg: ", e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    private BooleanExpression generateExpression(QMonitorObjectType qMonitorObjectType, MonitorObjectType monitorObjectType) {
        BooleanExpression booleanExpression = null;
        if (monitorObjectType.getCancelFlag() != null) {
            BooleanExpression cancelBooleanExpression = qMonitorObjectType.cancelFlag.eq(monitorObjectType.getCancelFlag());
            booleanExpression = (booleanExpression == null ? cancelBooleanExpression : booleanExpression.and(cancelBooleanExpression));
        }
        if (monitorObjectType.getStatus() != null) {
            BooleanExpression statusBooleanExpression = qMonitorObjectType.status.eq(monitorObjectType.getStatus());
            booleanExpression = (booleanExpression == null ? statusBooleanExpression : booleanExpression.and(statusBooleanExpression));
        }
        if (StringUtils.isNotBlank(monitorObjectType.getName())) {
            BooleanExpression nameBooleanExpression = qMonitorObjectType.name.like("%" + monitorObjectType.getName() + "%");
            booleanExpression = (booleanExpression == null ? nameBooleanExpression : booleanExpression.and(nameBooleanExpression));
        }
        return booleanExpression;
    }

}
