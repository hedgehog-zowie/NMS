package com.iuni.nms.service.impl;

import com.iuni.nms.persist.domain.MonitorItem;
import com.iuni.nms.persist.domain.QMonitorItem;
import com.iuni.nms.persist.repository.MonitorItemRepository;
import com.iuni.nms.service.MonitorItemService;
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
public class MonitorItemServiceImpl implements MonitorItemService {

    private static final Logger logger = LoggerFactory.getLogger(MonitorItemService.class);

    @Autowired
    private AccountService accountService;
    @Autowired
    private MonitorItemRepository monitorItemRepository;

    @Override
    public MonitorItem getById(Long id) {
        MonitorItem monitorItem;
        try {
            monitorItem = monitorItemRepository.findOne(id);
        } catch (Exception e) {
            logger.error("get monitorItem by id error. msg:" + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
        return monitorItem;
    }

    @Override
    public MonitorItem getByCode(String code) {
        MonitorItem monitorItem = null;
        try {
            monitorItem = monitorItemRepository.findByCode(code);
        }catch (Exception e){
            logger.error("get monitorItem by code error. msg:" + e.getLocalizedMessage());
        }
        return monitorItem;
    }

    @Override
    public List<MonitorItem> listMonitorItem(MonitorItem monitorItem) {
        List<MonitorItem> monitorItemList = new ArrayList<>();
        QMonitorItem qMonitorItem = QMonitorItem.monitorItem;
        BooleanExpression booleanExpression = generateExpression(qMonitorItem, monitorItem);
        Iterable<MonitorItem> iterable = monitorItemRepository.findAll(booleanExpression);
        Iterator<MonitorItem> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            MonitorItem nMonitorItem = iterator.next();
            monitorItemList.add(nMonitorItem);
        }
        return monitorItemList;
    }

    @Override
    public void addMonitorItem(MonitorItem monitorItem) {
        monitorItem.setBasicInfoForCreate(accountService.getCurrentUser().getLoginName());
        saveMonitorItem(monitorItem);
    }

    @Override
    public void updateMonitorItem(MonitorItem monitorItem) {
        MonitorItem oldMonitorItem = getById(monitorItem.getId());
        if (oldMonitorItem != null) {
            monitorItem.setCreateBy(oldMonitorItem.getCreateBy());
            monitorItem.setCreateDate(oldMonitorItem.getCreateDate());
            monitorItem.setStatus(oldMonitorItem.getStatus());
            monitorItem.setBasicInfoForUpdate(accountService.getCurrentUser().getLoginName());
        }
        saveMonitorItem(monitorItem);
    }

    @Override
    public void deleteMonitorItem(String ids) {
        List<MonitorItem> monitorItemList = new ArrayList<>();
        try {
            String[] idArray = ids.split(",");
            for (String id : idArray) {
                if (!StringUtils.isNumeric(id))
                    continue;
                MonitorItem monitorItem = getById(Long.parseLong(id));
                if (monitorItem != null) {
                    monitorItem.setBasicInfoForCancel(accountService.getCurrentUser().getLoginName());
                    monitorItemList.add(monitorItem);
                }
            }
            monitorItemRepository.save(monitorItemList);
        } catch (Exception e) {
            logger.error("logic delete monitorItem error. msg: {}", e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    private void saveMonitorItem(MonitorItem monitorItem) {
        try {
            monitorItemRepository.save(monitorItem);
        } catch (Exception e) {
            logger.error("save monitorItem error, msg: ", e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    private void saveMonitorItem(List<MonitorItem> monitorItemList) {
        try {
            monitorItemRepository.save(monitorItemList);
        } catch (Exception e) {
            logger.error("save monitorItem error, msg: ", e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    private BooleanExpression generateExpression(QMonitorItem qMonitorItem, MonitorItem monitorItem) {
        BooleanExpression booleanExpression = null;
        if (monitorItem.getCancelFlag() != null) {
            BooleanExpression cancelBooleanExpression = qMonitorItem.cancelFlag.eq(monitorItem.getCancelFlag());
            booleanExpression = (booleanExpression == null ? cancelBooleanExpression : booleanExpression.and(cancelBooleanExpression));
        }
        if (monitorItem.getStatus() != null) {
            BooleanExpression statusBooleanExpression = qMonitorItem.status.eq(monitorItem.getStatus());
            booleanExpression = (booleanExpression == null ? statusBooleanExpression : booleanExpression.and(statusBooleanExpression));
        }
        if (StringUtils.isNotBlank(monitorItem.getName())) {
            BooleanExpression nameBooleanExpression = qMonitorItem.name.like("%" + monitorItem.getName() + "%");
            booleanExpression = (booleanExpression == null ? nameBooleanExpression : booleanExpression.and(nameBooleanExpression));
        }
        if (StringUtils.isNotBlank(monitorItem.getCode())) {
            BooleanExpression codeBooleanExpression = qMonitorItem.code.like("%" + monitorItem.getCode() + "%");
            booleanExpression = (booleanExpression == null ? codeBooleanExpression : booleanExpression.and(codeBooleanExpression));
        }
        return booleanExpression;
    }

}
