package com.iuni.nms.service.impl;

import com.iuni.nms.common.constant.ConfigConstants;
import com.iuni.nms.persist.domain.MonitorInfo;
import com.iuni.nms.persist.domain.MonitorObject;
import com.iuni.nms.persist.domain.QMonitorInfo;
import com.iuni.nms.persist.repository.MonitorInfoRepository;
import com.iuni.nms.persist.repository.MonitorObjectRepository;
import com.iuni.nms.service.MonitorInfoService;
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
public class MonitorInfoServiceImpl implements MonitorInfoService {

    private static final Logger logger = LoggerFactory.getLogger(MonitorInfoService.class);

    @Autowired
    private AccountService accountService;
    @Autowired
    private MonitorInfoRepository monitorInfoRepository;
    @Autowired
    private MonitorObjectRepository monitorObjectRepository;

    @Override
    public MonitorInfo getById(Long id) {
        MonitorInfo monitorInfo;
        try {
            monitorInfo = monitorInfoRepository.findOne(id);
        } catch (Exception e) {
            logger.error("get monitorInfo by id error. msg:" + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
        return monitorInfo;
    }

    @Override
    public List<MonitorInfo> listMonitorInfo(MonitorInfo monitorInfo) {
        List<MonitorInfo> monitorInfoList = new ArrayList<>();
        QMonitorInfo qMonitorInfo = QMonitorInfo.monitorInfo;
        BooleanExpression booleanExpression = generateExpression(qMonitorInfo, monitorInfo);
        Iterable<MonitorInfo> iterable = monitorInfoRepository.findAll(booleanExpression);
        Iterator<MonitorInfo> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            MonitorInfo nMonitorInfo = iterator.next();
            monitorInfoList.add(nMonitorInfo);
        }
        return monitorInfoList;
    }

    @Override
    public MonitorInfo addMonitorInfo(MonitorInfo monitorInfo) {
        monitorInfo.setBasicInfoForCreate(accountService.getCurrentUser().getLoginName());
        MonitorInfo savedMonitorInfo = null;
        try {
            saveMonitorInfo(monitorInfo);
        } catch (Exception e) {
            logger.error(" add monitor info error. msg: {}", e.getLocalizedMessage());
        }
        return savedMonitorInfo;
    }

    @Override
    public MonitorInfo updateMonitorInfo(MonitorInfo monitorInfo) {
        MonitorInfo oldMonitorInfo = getById(monitorInfo.getId());
        if (oldMonitorInfo != null) {
            monitorInfo.setCreateBy(oldMonitorInfo.getCreateBy());
            monitorInfo.setCreateDate(oldMonitorInfo.getCreateDate());
            monitorInfo.setStatus(oldMonitorInfo.getStatus());
            monitorInfo.setBasicInfoForUpdate(accountService.getCurrentUser().getLoginName());
        }
        MonitorInfo savedMonitorInfo = null;
        try {
            saveMonitorInfo(monitorInfo);
        } catch (Exception e) {
            logger.error(" update monitor info error. msg: {}", e.getLocalizedMessage());
        }
        return savedMonitorInfo;
    }

    @Override
    public void deleteMonitorInfo(String ids) {
        List<MonitorInfo> monitorInfoList = new ArrayList<>();
        try {
            String[] idArray = ids.split(",");
            for (String id : idArray) {
                if (!StringUtils.isNumeric(id))
                    continue;
                MonitorInfo monitorInfo = getById(Long.parseLong(id));
                if (monitorInfo != null) {
                    monitorInfo.setBasicInfoForCancel(accountService.getCurrentUser().getLoginName());
                    monitorInfoList.add(monitorInfo);
                }
            }
            monitorInfoRepository.save(monitorInfoList);
        } catch (Exception e) {
            logger.error("logic delete monitorInfo error. msg: {}", e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public void handleMonitorInfo(String ids) {
//        List<MonitorInfo> monitorInfoList = new ArrayList<>();
        try {
            String[] idArray = ids.split(",");
            for (String id : idArray) {
                if (!StringUtils.isNumeric(id))
                    continue;
                MonitorInfo monitorInfo = getById(Long.parseLong(id));
                if (monitorInfo != null) {
                    monitorInfo.setBasicInfoForCancel(accountService.getCurrentUser().getLoginName());
                    // 置为无效
                    monitorInfo.setStatus(ConfigConstants.STATUS_FLAG_INVALID);
//                    monitorInfoList.add(monitorInfo);
                    monitorInfoRepository.save(monitorInfo);
                }
                // 检查object是否存在其他未处理监控信息
                MonitorObject monitorObjectForCheck = monitorInfo.getMonitorObject();
                MonitorInfo monitorInfoForCheck = new MonitorInfo();
                monitorInfoForCheck.setMonitorObject(monitorObjectForCheck);
                monitorInfoForCheck.setCancelFlag(ConfigConstants.LOGICAL_CANCEL_FLAG_NOT_CANCEL);
                monitorInfoForCheck.setStatus(ConfigConstants.STATUS_FLAG_EFFECTIVE);
                List<MonitorInfo> monitorInfoListForCheck = listMonitorInfo(monitorInfoForCheck);
                if (monitorInfoListForCheck.size() != 0)
                    continue;
                // 若不存在未处理的监控信息，则将该监控对象置为健康状态
                monitorObjectForCheck.setHealthyStatus(ConfigConstants.HEALTHY_STATUS_FLAG_HEALTHY);
                monitorObjectRepository.save(monitorObjectForCheck);
            }
//            monitorInfoRepository.save(monitorInfoList);
        } catch (Exception e) {
            logger.error("logic delete monitorInfo error. msg: {}", e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    private MonitorInfo saveMonitorInfo(MonitorInfo monitorInfo) {
        try {
            return monitorInfoRepository.save(monitorInfo);
        } catch (Exception e) {
            logger.error("save monitorInfo error, msg: ", e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    private void saveMonitorInfo(List<MonitorInfo> monitorInfoList) {
        try {
            monitorInfoRepository.save(monitorInfoList);
        } catch (Exception e) {
            logger.error("save monitorInfo error, msg: ", e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    private BooleanExpression generateExpression(QMonitorInfo qMonitorInfo, MonitorInfo monitorInfo) {
        BooleanExpression booleanExpression = null;
        if (monitorInfo.getCancelFlag() != null) {
            BooleanExpression cancelBooleanExpression = qMonitorInfo.cancelFlag.eq(monitorInfo.getCancelFlag());
            booleanExpression = (booleanExpression == null ? cancelBooleanExpression : booleanExpression.and(cancelBooleanExpression));
        }
        if (monitorInfo.getStatus() != null) {
            BooleanExpression statusBooleanExpression = qMonitorInfo.status.eq(monitorInfo.getStatus());
            booleanExpression = (booleanExpression == null ? statusBooleanExpression : booleanExpression.and(statusBooleanExpression));
        }
        if (StringUtils.isNotBlank(monitorInfo.getMessage())) {
            BooleanExpression nameBooleanExpression = qMonitorInfo.message.like("%" + monitorInfo.getMessage() + "%");
            booleanExpression = (booleanExpression == null ? nameBooleanExpression : booleanExpression.and(nameBooleanExpression));
        }
        if (monitorInfo.getMonitorObject() != null && monitorInfo.getMonitorObject().getId() != 0) {
            BooleanExpression objectIdBooleanExpression = qMonitorInfo.monitorObject.id.eq(monitorInfo.getMonitorObject().getId());
            booleanExpression = (booleanExpression == null ? objectIdBooleanExpression : booleanExpression.and(objectIdBooleanExpression));
        }
        if (monitorInfo.getMonitorObject() != null && StringUtils.isNotBlank(monitorInfo.getMonitorObject().getCode())) {
            BooleanExpression objectBooleanExpression = qMonitorInfo.monitorObject.code.like("%" + monitorInfo.getMonitorObject().getCode() + "%");
            booleanExpression = (booleanExpression == null ? objectBooleanExpression : booleanExpression.and(objectBooleanExpression));
        }
        if (monitorInfo.getMonitorItem() != null && StringUtils.isNotBlank(monitorInfo.getMonitorItem().getCode())) {
            BooleanExpression itemBooleanExpression = qMonitorInfo.monitorItem.code.like("%" + monitorInfo.getMonitorItem().getCode() + "%");
            booleanExpression = (booleanExpression == null ? itemBooleanExpression : booleanExpression.and(itemBooleanExpression));
        }
        if (monitorInfo.getMonitorItemProps() != null && StringUtils.isNotBlank(monitorInfo.getMonitorItemProps().getCode())) {
            BooleanExpression itemPropsBooleanExpression = qMonitorInfo.monitorItemProps.code.like("%" + monitorInfo.getMonitorItemProps().getCode() + "%");
            booleanExpression = (booleanExpression == null ? itemPropsBooleanExpression : booleanExpression.and(itemPropsBooleanExpression));
        }
        return booleanExpression;
    }

}
