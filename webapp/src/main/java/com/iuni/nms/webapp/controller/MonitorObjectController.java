package com.iuni.nms.webapp.controller;

import com.iuni.nms.common.constant.ConfigConstants;
import com.iuni.nms.persist.domain.Module;
import com.iuni.nms.persist.domain.MonitorItem;
import com.iuni.nms.persist.domain.MonitorObject;
import com.iuni.nms.persist.domain.MonitorObjectType;
import com.iuni.nms.service.ModuleService;
import com.iuni.nms.service.MonitorItemService;
import com.iuni.nms.service.MonitorObjectService;
import com.iuni.nms.service.MonitorObjectTypeService;
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
@RequestMapping("/config/monitorObject")
public class MonitorObjectController {

    private static final Logger logger = LoggerFactory.getLogger(MonitorObjectController.class);

    @Autowired
    private ModuleService moduleService;
    @Autowired
    private MonitorObjectService monitorObjectService;
    @Autowired
    private MonitorObjectTypeService monitorObjectTypeService;
    @Autowired
    private MonitorItemService monitorItemService;

    @RequestMapping
    public ModelAndView list() {
        List<MonitorObject> monitorObjects;
        MonitorObject monitorObject = new MonitorObject();

        monitorObject.setCancelFlag(ConfigConstants.LOGICAL_CANCEL_FLAG_NOT_CANCEL);
        monitorObjects = monitorObjectService.listMonitorObject(monitorObject);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PageName.config_monitor_object.getPath());
        modelAndView.addObject("monitorObjects", monitorObjects);
        return modelAndView;
    }

    @RequestMapping("/add")
    public ModelAndView add() {
        return addOrEdit(new MonitorObject());
    }

    @RequestMapping("/edit")
    public ModelAndView edit(@RequestParam(value = "id", required = true) Long id) {
        return addOrEdit(monitorObjectService.getById(id));
    }

    private ModelAndView addOrEdit(MonitorObject monitorObject) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PageName.config_monitor_object_edit.getPath());
        modelAndView.addObject("monitorObject", monitorObject);

        Module module = new Module();
        module.setCancelFlag(ConfigConstants.LOGICAL_CANCEL_FLAG_NOT_CANCEL);
        module.setStatus(ConfigConstants.STATUS_FLAG_EFFECTIVE);
        List<Module> modules = moduleService.listModule(module);
        modelAndView.addObject("modules", modules);

        MonitorObjectType monitorObjectType = new MonitorObjectType();
        monitorObjectType.setCancelFlag(ConfigConstants.LOGICAL_CANCEL_FLAG_NOT_CANCEL);
        monitorObjectType.setStatus(ConfigConstants.STATUS_FLAG_EFFECTIVE);
        List<MonitorObjectType> monitorObjectTypes = monitorObjectTypeService.listMonitorObjectType(monitorObjectType);
        modelAndView.addObject("monitorObjectTypes", monitorObjectTypes);

        MonitorItem monitorItem = new MonitorItem();
        monitorItem.setCancelFlag(ConfigConstants.LOGICAL_CANCEL_FLAG_NOT_CANCEL);
        monitorItem.setStatus(ConfigConstants.STATUS_FLAG_EFFECTIVE);
        List<MonitorItem> monitorItemList = monitorItemService.listMonitorItem(monitorItem);
        modelAndView.addObject("monitorItemList", monitorItemList);
        return modelAndView;
    }

    @RequestMapping("/save")
    @ResponseBody
    public ResultOfAjax save(@ModelAttribute("monitorObject") MonitorObject monitorObject) {
        logger.info("save monitorObject: {}", monitorObject.toString());
        monitorObject.setStatus(ConfigConstants.STATUS_FLAG_EFFECTIVE);
        ResultOfAjax result = new ResultOfAjax();
        try {
            if (monitorObject.getId() == 0) {
                monitorObject.setMonitorStatus(ConfigConstants.MONITOR_STATUS_FLAG_EFFECTIVE);
                monitorObject.setAlarmStatus(ConfigConstants.ALARM_STATUS_FLAG_EFFECTIVE);
                monitorObject.setHealthyStatus(ConfigConstants.HEALTHY_STATUS_FLAG_HEALTHY);
                monitorObjectService.addMonitorObject(monitorObject);
            } else {
                monitorObjectService.updateMonitorObject(monitorObject);
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
        logger.info("delete monitorObject. ids: {}", ids);
        ResultOfAjax result = new ResultOfAjax();
        try {
            monitorObjectService.deleteMonitorObject(ids);
            result.setCode(ResultOfAjax.CODE_SUCCEED);
            result.setMsg("成功");
        } catch (Exception e) {
            result.setCode(ResultOfAjax.CODE_FAILED);
            result.setMsg(e.getLocalizedMessage());
        }
        return result;
    }

}
