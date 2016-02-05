package com.iuni.nms.webapp.controller;

import com.iuni.nms.common.constant.ConfigConstants;
import com.iuni.nms.persist.domain.MonitorObjectType;
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
@RequestMapping("/config/monitorObjectType")
public class MonitorObjectTypeController {

    private static final Logger logger = LoggerFactory.getLogger(MonitorObjectTypeController.class);

    @Autowired
    private MonitorObjectTypeService monitorObjectTypeService;

    @RequestMapping
    public ModelAndView list() {
        List<MonitorObjectType> monitorObjectTypeList;
        MonitorObjectType monitorObjectType = new MonitorObjectType();

        monitorObjectType.setCancelFlag(ConfigConstants.LOGICAL_CANCEL_FLAG_NOT_CANCEL);
        monitorObjectTypeList = monitorObjectTypeService.listMonitorObjectType(monitorObjectType);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PageName.config_monitor_object_type.getPath());
        modelAndView.addObject("monitorObjectTypeList", monitorObjectTypeList);
        return modelAndView;
    }

    @RequestMapping("/add")
    public ModelAndView add() {
        return addOrEdit(new MonitorObjectType());
    }

    @RequestMapping("/edit")
    public ModelAndView edit(@RequestParam(value = "id", required = true) Long id) {
        return addOrEdit(monitorObjectTypeService.getById(id));
    }

    private ModelAndView addOrEdit(MonitorObjectType monitorObjectType) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PageName.config_monitor_object_type_edit.getPath());
        modelAndView.addObject("monitorObjectType", monitorObjectType);
        return modelAndView;
    }

    @RequestMapping("/save")
    @ResponseBody
    public ResultOfAjax save(@ModelAttribute("monitorObjectType") MonitorObjectType monitorObjectType) {
        logger.info("save monitorObjectType: {}", monitorObjectType.toString());
        monitorObjectType.setStatus(ConfigConstants.STATUS_FLAG_EFFECTIVE);
        ResultOfAjax result = new ResultOfAjax();
        try {
            if (monitorObjectType.getId() == 0) {
                monitorObjectTypeService.addMonitorObjectType(monitorObjectType);
            } else {
                monitorObjectTypeService.updateMonitorObjectType(monitorObjectType);
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
        logger.info("delete monitorObjectType. ids: {}", ids);
        ResultOfAjax result = new ResultOfAjax();
        try {
            monitorObjectTypeService.deleteMonitorObjectType(ids);
            result.setCode(ResultOfAjax.CODE_SUCCEED);
            result.setMsg("成功");
        } catch (Exception e) {
            result.setCode(ResultOfAjax.CODE_FAILED);
            result.setMsg(e.getLocalizedMessage());
        }
        return result;
    }

}
