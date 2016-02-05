package com.iuni.nms.service.impl;

import com.iuni.nms.persist.domain.MonitorItemType;
import com.iuni.nms.persist.domain.QMonitorItemType;
import com.iuni.nms.persist.repository.MonitorItemTypeRepository;
import com.iuni.nms.service.MonitorItemTypeService;
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
public class MonitorItemTypeServiceImpl implements MonitorItemTypeService {

    private static final Logger logger = LoggerFactory.getLogger(MonitorItemTypeService.class);

    @Autowired
    private AccountService accountService;
    @Autowired
    private MonitorItemTypeRepository monitorItemTypeRepository;

    @Override
    public MonitorItemType getById(Long id) {
        MonitorItemType monitorItemType;
        try {
            monitorItemType = monitorItemTypeRepository.findOne(id);
        } catch (Exception e) {
            logger.error("get monitorItemType by id error. msg:" + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
        return monitorItemType;
    }

    @Override
    public List<MonitorItemType> listMonitorItemType(MonitorItemType monitorItemType) {
        List<MonitorItemType> monitorItemTypeList = new ArrayList<>();
        QMonitorItemType qMonitorItemType = QMonitorItemType.monitorItemType;
        BooleanExpression booleanExpression = generateExpression(qMonitorItemType, monitorItemType);
        Iterable<MonitorItemType> iterable = monitorItemTypeRepository.findAll(booleanExpression);
        Iterator<MonitorItemType> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            MonitorItemType nMonitorItemType = iterator.next();
            monitorItemTypeList.add(nMonitorItemType);
        }
        return monitorItemTypeList;
    }

    @Override
    public void addMonitorItemType(MonitorItemType monitorItemType) {
        monitorItemType.setBasicInfoForCreate(accountService.getCurrentUser().getLoginName());
        saveMonitorItemType(monitorItemType);
    }

    @Override
    public void updateMonitorItemType(MonitorItemType monitorItemType) {
        MonitorItemType oldMonitorItemType = getById(monitorItemType.getId());
        if (oldMonitorItemType != null) {
            monitorItemType.setCreateBy(oldMonitorItemType.getCreateBy());
            monitorItemType.setCreateDate(oldMonitorItemType.getCreateDate());
            monitorItemType.setStatus(oldMonitorItemType.getStatus());
            monitorItemType.setBasicInfoForUpdate(accountService.getCurrentUser().getLoginName());
        }
        saveMonitorItemType(monitorItemType);
    }

    @Override
    public void deleteMonitorItemType(String ids) {
        List<MonitorItemType> monitorItemTypeList = new ArrayList<>();
        try {
            String[] idArray = ids.split(",");
            for (String id : idArray) {
                if (!StringUtils.isNumeric(id))
                    continue;
                MonitorItemType monitorItemType = getById(Long.parseLong(id));
                if (monitorItemType != null) {
                    monitorItemType.setBasicInfoForCancel(accountService.getCurrentUser().getLoginName());
                    monitorItemTypeList.add(monitorItemType);
                }
            }
            monitorItemTypeRepository.save(monitorItemTypeList);
        } catch (Exception e) {
            logger.error("logic delete monitorItemType error. msg: {}", e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    private void saveMonitorItemType(MonitorItemType monitorItemType) {
        try {
            monitorItemTypeRepository.save(monitorItemType);
        } catch (Exception e) {
            logger.error("save monitorItemType error, msg: ", e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    private void saveMonitorItemType(List<MonitorItemType> monitorItemTypeList) {
        try {
            monitorItemTypeRepository.save(monitorItemTypeList);
        } catch (Exception e) {
            logger.error("save monitorItemType error, msg: ", e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    private BooleanExpression generateExpression(QMonitorItemType qMonitorItemType, MonitorItemType monitorItemType) {
        BooleanExpression booleanExpression = null;
        if (monitorItemType.getCancelFlag() != null) {
            BooleanExpression cancelBooleanExpression = qMonitorItemType.cancelFlag.eq(monitorItemType.getCancelFlag());
            booleanExpression = (booleanExpression == null ? cancelBooleanExpression : booleanExpression.and(cancelBooleanExpression));
        }
        if (monitorItemType.getStatus() != null) {
            BooleanExpression statusBooleanExpression = qMonitorItemType.status.eq(monitorItemType.getStatus());
            booleanExpression = (booleanExpression == null ? statusBooleanExpression : booleanExpression.and(statusBooleanExpression));
        }
        if (StringUtils.isNotBlank(monitorItemType.getName())) {
            BooleanExpression nameBooleanExpression = qMonitorItemType.name.like("%" + monitorItemType.getName() + "%");
            booleanExpression = (booleanExpression == null ? nameBooleanExpression : booleanExpression.and(nameBooleanExpression));
        }
        return booleanExpression;
    }

}
