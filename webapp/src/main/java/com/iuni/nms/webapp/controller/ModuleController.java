package com.iuni.nms.webapp.controller;

import com.iuni.nms.common.constant.ConfigConstants;
import com.iuni.nms.persist.domain.Module;
import com.iuni.nms.persist.domain.System;
import com.iuni.nms.service.ModuleService;
import com.iuni.nms.service.SystemService;
import com.iuni.nms.webapp.common.PageName;
import com.iuni.nms.common.ResultOfAjax;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
@Controller
@RequestMapping("/config/module")
public class ModuleController {

    private static final Logger logger = LoggerFactory.getLogger(ModuleController.class);

    @Autowired
    private SystemService systemService;
    @Autowired
    private ModuleService moduleService;

    @RequestMapping
    public ModelAndView list() {
        List<Module> moduleList;
        Module module = new Module();

        module.setCancelFlag(ConfigConstants.LOGICAL_CANCEL_FLAG_NOT_CANCEL);
        moduleList = moduleService.listModule(module);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PageName.config_module.getPath());
        modelAndView.addObject("moduleList", moduleList);
        return modelAndView;
    }

    @RequestMapping("/add")
    public ModelAndView add() {
        return addOrEdit(new Module());
    }

    @RequestMapping("/edit")
    public ModelAndView edit(@RequestParam(value = "id", required = true) Long id) {
        return addOrEdit(moduleService.getById(id));
    }

    private ModelAndView addOrEdit(Module module) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PageName.config_module_edit.getPath());
        modelAndView.addObject("module", module);
        System system = new System();
        system.setStatus(ConfigConstants.STATUS_FLAG_EFFECTIVE);
        system.setCancelFlag(ConfigConstants.LOGICAL_CANCEL_FLAG_NOT_CANCEL);
        List<System> systems = systemService.listSystem(system);
        modelAndView.addObject("systems", systems);
        return modelAndView;
    }

    @RequestMapping("/save")
    @ResponseBody
    public ResultOfAjax save(@ModelAttribute("module") Module module) {
        logger.info("save module: {}", module.toString());
        module.setStatus(ConfigConstants.STATUS_FLAG_EFFECTIVE);
        ResultOfAjax result = new ResultOfAjax();
        try {
            if (module.getId() == 0) {
                moduleService.addModule(module);
            } else {
                moduleService.updateModule(module);
            }
            result.setCode(ResultOfAjax.CODE_SUCCEED);
            result.setMsg("成功");
        } catch (Exception e) {
            result.setCode(ResultOfAjax.CODE_FAILED);
            result.setMsg(e.getLocalizedMessage());
        }
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ResultOfAjax delete(@RequestParam(value = "ids", required = true) String ids) {
        logger.info("delete module. ids: {}", ids);
        ResultOfAjax result = new ResultOfAjax();
        try {
            moduleService.deleteModule(ids);
            result.setCode(ResultOfAjax.CODE_SUCCEED);
            result.setMsg("成功");
        } catch (Exception e) {
            result.setCode(ResultOfAjax.CODE_FAILED);
            result.setMsg(e.getLocalizedMessage());
        }
        return result;
    }

}
