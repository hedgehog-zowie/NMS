package com.iuni.nms.service.impl;

import com.iuni.nms.persist.domain.QSystem;
import com.iuni.nms.persist.domain.System;
import com.iuni.nms.persist.repository.SystemRepository;
import com.iuni.nms.service.SystemService;
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
public class SystemServiceImpl implements SystemService {

    private static final Logger logger = LoggerFactory.getLogger(SystemService.class);

    @Autowired
    private AccountService accountService;
    @Autowired
    private SystemRepository systemRepository;

    @Override
    public System getById(Long id) {
        System system;
        try {
            system = systemRepository.findOne(id);
        } catch (Exception e) {
            logger.error("get system by id error. msg:" + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
        return system;
    }

    @Override
    public List<System> listSystem(System system) {
        List<System> systemList = new ArrayList<>();
        QSystem qSystem = QSystem.system;
        BooleanExpression booleanExpression = generateExpression(qSystem, system);
        Iterable<System> iterable = systemRepository.findAll(booleanExpression);
        Iterator<System> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            System nSystem = iterator.next();
            systemList.add(nSystem);
        }
        return systemList;
    }

    @Override
    public void addSystem(System system) {
        system.setBasicInfoForCreate(accountService.getCurrentUser().getLoginName());
        saveSystem(system);
    }

    @Override
    public void updateSystem(System system) {
        System oldSystem = getById(system.getId());
        if (oldSystem != null) {
            system.setCreateBy(oldSystem.getCreateBy());
            system.setCreateDate(oldSystem.getCreateDate());
            system.setStatus(oldSystem.getStatus());
            system.setBasicInfoForUpdate(accountService.getCurrentUser().getLoginName());
        }
        saveSystem(system);
    }

    @Override
    public void deleteSystem(String ids) {
        List<System> systemList = new ArrayList<>();
        try {
            String[] idArray = ids.split(",");
            for (String id : idArray) {
                if (!StringUtils.isNumeric(id))
                    continue;
                System system = getById(Long.parseLong(id));
                if (system != null) {
                    system.setBasicInfoForCancel(accountService.getCurrentUser().getLoginName());
                    systemList.add(system);
                }
            }
            systemRepository.save(systemList);
        } catch (Exception e) {
            logger.error("logic delete system error. msg: {}", e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    private void saveSystem(System system) {
        try {
            systemRepository.save(system);
        } catch (Exception e) {
            logger.error("save system error, msg: ", e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    private void saveSystem(List<System> systemList) {
        try {
            systemRepository.save(systemList);
        } catch (Exception e) {
            logger.error("save system error, msg: ", e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    private BooleanExpression generateExpression(QSystem qSystem, System system) {
        BooleanExpression booleanExpression = null;
        if (system.getCancelFlag() != null) {
            BooleanExpression cancelBooleanExpression = qSystem.cancelFlag.eq(system.getCancelFlag());
            booleanExpression = (booleanExpression == null ? cancelBooleanExpression : booleanExpression.and(cancelBooleanExpression));
        }
        if (system.getStatus() != null) {
            BooleanExpression statusBooleanExpression = qSystem.status.eq(system.getStatus());
            booleanExpression = (booleanExpression == null ? statusBooleanExpression : booleanExpression.and(statusBooleanExpression));
        }
        if (StringUtils.isNotBlank(system.getName())) {
            BooleanExpression nameBooleanExpression = qSystem.name.like("%" + system.getName() + "%");
            booleanExpression = (booleanExpression == null ? nameBooleanExpression : booleanExpression.and(nameBooleanExpression));
        }
        if (StringUtils.isNotBlank(system.getCode())) {
            BooleanExpression codeBooleanExpression = qSystem.code.like("%" + system.getCode() + "%");
            booleanExpression = (booleanExpression == null ? codeBooleanExpression : booleanExpression.and(codeBooleanExpression));
        }
        return booleanExpression;
    }

}
