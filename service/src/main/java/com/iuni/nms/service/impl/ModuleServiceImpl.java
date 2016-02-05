package com.iuni.nms.service.impl;

import com.iuni.nms.persist.domain.Module;
import com.iuni.nms.persist.domain.QModule;
import com.iuni.nms.persist.repository.ModuleRepository;
import com.iuni.nms.service.ModuleService;
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
public class ModuleServiceImpl implements ModuleService {

    private static final Logger logger = LoggerFactory.getLogger(ModuleService.class);

    @Autowired
    private AccountService accountService;
    @Autowired
    private ModuleRepository moduleRepository;

    @Override
    public Module getById(Long id) {
        Module module;
        try {
            module = moduleRepository.findOne(id);
        } catch (Exception e) {
            logger.error("get module by id error. msg:" + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
        return module;
    }

    @Override
    public List<Module> listModule(Module module) {
        List<Module> moduleList = new ArrayList<>();
        try {
            QModule qModule = QModule.module;
            BooleanExpression booleanExpression = generateExpression(qModule, module);
            Iterable<Module> iterable = moduleRepository.findAll(booleanExpression);
            Iterator<Module> iterator = iterable.iterator();
            while (iterator.hasNext()) {
                Module nModule = iterator.next();
                moduleList.add(nModule);
            }
        } catch (Exception e) {
            logger.error("list module error. msg:" + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
        return moduleList;
    }

    @Override
    public void addModule(Module module) {
        module.setBasicInfoForCreate(accountService.getCurrentUser().getLoginName());
        saveModule(module);
    }

    @Override
    public void updateModule(Module module) {
        Module oldModule = getById(module.getId());
        if (oldModule != null) {
            module.setCreateBy(oldModule.getCreateBy());
            module.setCreateDate(oldModule.getCreateDate());
            module.setStatus(oldModule.getStatus());
            module.setBasicInfoForUpdate(accountService.getCurrentUser().getLoginName());
        }
        saveModule(module);
    }

    @Override
    public void deleteModule(String ids) {
        List<Module> moduleList = new ArrayList<>();
        try {
            String[] idArray = ids.split(",");
            for (String id : idArray) {
                if (!StringUtils.isNumeric(id))
                    continue;
                Module module = getById(Long.parseLong(id));
                if (module != null) {
                    module.setBasicInfoForCancel(accountService.getCurrentUser().getLoginName());
                    moduleList.add(module);
                }
            }
            moduleRepository.save(moduleList);
        } catch (Exception e) {
            logger.error("logic delete module error. msg: {}", e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    private void saveModule(Module module) {
        try {
            moduleRepository.save(module);
        } catch (Exception e) {
            logger.error("save module error, msg: ", e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    private void saveModule(List<Module> moduleList) {
        try {
            moduleRepository.save(moduleList);
        } catch (Exception e) {
            logger.error("save module error, msg: ", e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    private BooleanExpression generateExpression(QModule qModule, Module module) {
        BooleanExpression booleanExpression = null;
        if (module.getCancelFlag() != null) {
            BooleanExpression cancelBooleanExpression = qModule.cancelFlag.eq(module.getCancelFlag());
            booleanExpression = (booleanExpression == null ? cancelBooleanExpression : booleanExpression.and(cancelBooleanExpression));
        }
        if (module.getStatus() != null) {
            BooleanExpression statusBooleanExpression = qModule.status.eq(module.getStatus());
            booleanExpression = (booleanExpression == null ? statusBooleanExpression : booleanExpression.and(statusBooleanExpression));
        }
        if (StringUtils.isNotBlank(module.getName())) {
            BooleanExpression nameBooleanExpression = qModule.name.like("%" + module.getName() + "%");
            booleanExpression = (booleanExpression == null ? nameBooleanExpression : booleanExpression.and(nameBooleanExpression));
        }
        if (StringUtils.isNotBlank(module.getCode())) {
            BooleanExpression codeBooleanExpression = qModule.code.like("%" + module.getCode() + "%");
            booleanExpression = (booleanExpression == null ? codeBooleanExpression : booleanExpression.and(codeBooleanExpression));
        }
        return booleanExpression;
    }

}
