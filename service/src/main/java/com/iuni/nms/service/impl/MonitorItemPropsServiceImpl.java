package com.iuni.nms.service.impl;

import com.iuni.nms.persist.domain.MonitorItemProps;
import com.iuni.nms.persist.domain.QMonitorItemProps;
import com.iuni.nms.persist.repository.MonitorItemPropsRepository;
import com.iuni.nms.service.MonitorItemPropsService;
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
public class MonitorItemPropsServiceImpl implements MonitorItemPropsService {

    private static final Logger logger = LoggerFactory.getLogger(MonitorItemPropsService.class);

    @Autowired
    private AccountService accountService;
    @Autowired
    private MonitorItemPropsRepository monitorItemPropsRepository;

    @Override
    public MonitorItemProps getById(Long id) {
        MonitorItemProps monitorItemProps;
        try {
            monitorItemProps = monitorItemPropsRepository.findOne(id);
        } catch (Exception e) {
            logger.error("get monitorItemProps by id error. msg:" + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
        return monitorItemProps;
    }

    @Override
    public MonitorItemProps getByCode(String code) {
        MonitorItemProps monitorItemProps = null;
        try {
            monitorItemProps = monitorItemPropsRepository.findByCode(code);
        }catch (Exception e){
            logger.error("get monitorItemProps by code error. msg:" + e.getLocalizedMessage());
        }
        return monitorItemProps;
    }

    @Override
    public List<MonitorItemProps> listMonitorItemProps(MonitorItemProps monitorItemProps) {
        List<MonitorItemProps> monitorItemPropsList = new ArrayList<>();
        QMonitorItemProps qMonitorItemProps = QMonitorItemProps.monitorItemProps;
        BooleanExpression booleanExpression = generateExpression(qMonitorItemProps, monitorItemProps);
        Iterable<MonitorItemProps> iterable = monitorItemPropsRepository.findAll(booleanExpression);
        Iterator<MonitorItemProps> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            MonitorItemProps nMonitorItemProps = iterator.next();
            monitorItemPropsList.add(nMonitorItemProps);
        }
        return monitorItemPropsList;
    }

    @Override
    public void addMonitorItemProps(MonitorItemProps monitorItemProps) {
        monitorItemProps.setBasicInfoForCreate(accountService.getCurrentUser().getLoginName());
        saveMonitorItemProps(monitorItemProps);
    }

    @Override
    public void updateMonitorItemProps(MonitorItemProps monitorItemProps) {
        MonitorItemProps oldMonitorItemProps = getById(monitorItemProps.getId());
        if (oldMonitorItemProps != null) {
            monitorItemProps.setCreateBy(oldMonitorItemProps.getCreateBy());
            monitorItemProps.setCreateDate(oldMonitorItemProps.getCreateDate());
            monitorItemProps.setStatus(oldMonitorItemProps.getStatus());
            monitorItemProps.setBasicInfoForUpdate(accountService.getCurrentUser().getLoginName());
        }
        saveMonitorItemProps(monitorItemProps);
    }

    @Override
    public void deleteMonitorItemProps(String ids) {
        List<MonitorItemProps> monitorItemPropsList = new ArrayList<>();
        try {
            String[] idArray = ids.split(",");
            for (String id : idArray) {
                if (!StringUtils.isNumeric(id))
                    continue;
                MonitorItemProps monitorItemProps = getById(Long.parseLong(id));
                if (monitorItemProps != null) {
                    monitorItemProps.setBasicInfoForCancel(accountService.getCurrentUser().getLoginName());
                    monitorItemPropsList.add(monitorItemProps);
                }
            }
            monitorItemPropsRepository.save(monitorItemPropsList);
        } catch (Exception e) {
            logger.error("logic delete monitorItemProps error. msg: {}", e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    private void saveMonitorItemProps(MonitorItemProps monitorItemProps) {
        try {
            monitorItemPropsRepository.save(monitorItemProps);
        } catch (Exception e) {
            logger.error("save monitorItemProps error, msg: ", e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    private void saveMonitorItemProps(List<MonitorItemProps> monitorItemPropsList) {
        try {
            monitorItemPropsRepository.save(monitorItemPropsList);
        } catch (Exception e) {
            logger.error("save monitorItemProps error, msg: ", e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    private BooleanExpression generateExpression(QMonitorItemProps qMonitorItemProps, MonitorItemProps monitorItemProps) {
        BooleanExpression booleanExpression = null;
        if (monitorItemProps.getCancelFlag() != null) {
            BooleanExpression cancelBooleanExpression = qMonitorItemProps.cancelFlag.eq(monitorItemProps.getCancelFlag());
            booleanExpression = (booleanExpression == null ? cancelBooleanExpression : booleanExpression.and(cancelBooleanExpression));
        }
        if (monitorItemProps.getStatus() != null) {
            BooleanExpression statusBooleanExpression = qMonitorItemProps.status.eq(monitorItemProps.getStatus());
            booleanExpression = (booleanExpression == null ? statusBooleanExpression : booleanExpression.and(statusBooleanExpression));
        }
        if (StringUtils.isNotBlank(monitorItemProps.getName())) {
            BooleanExpression nameBooleanExpression = qMonitorItemProps.name.like("%" + monitorItemProps.getName() + "%");
            booleanExpression = (booleanExpression == null ? nameBooleanExpression : booleanExpression.and(nameBooleanExpression));
        }
        if (StringUtils.isNotBlank(monitorItemProps.getCode())) {
            BooleanExpression codeBooleanExpression = qMonitorItemProps.code.like("%" + monitorItemProps.getCode() + "%");
            booleanExpression = (booleanExpression == null ? codeBooleanExpression : booleanExpression.and(codeBooleanExpression));
        }
        if (StringUtils.isNotBlank(monitorItemProps.getLevel())) {
            BooleanExpression levelBooleanExpression = qMonitorItemProps.level.like("%" + monitorItemProps.getLevel() + "%");
            booleanExpression = (booleanExpression == null ? levelBooleanExpression : booleanExpression.and(levelBooleanExpression));
        }
        if (monitorItemProps.getMonitorItem() != null && monitorItemProps.getMonitorItem().getId() != 0) {
            BooleanExpression itemBooleanExpression = qMonitorItemProps.monitorItem.id.eq(monitorItemProps.getMonitorItem().getId());
            booleanExpression = (booleanExpression == null ? itemBooleanExpression : booleanExpression.and(itemBooleanExpression));
        }
        return booleanExpression;
    }

}
