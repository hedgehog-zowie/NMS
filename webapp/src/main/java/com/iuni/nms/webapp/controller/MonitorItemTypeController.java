package com.iuni.nms.webapp.controller;

import com.iuni.nms.common.constant.ConfigConstants;
import com.iuni.nms.persist.domain.MonitorItemType;
import com.iuni.nms.service.MonitorItemTypeService;
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
@RequestMapping("/config/monitorItemType")
public class MonitorItemTypeController {

    private static final Logger logger = LoggerFactory.getLogger(MonitorItemTypeController.class);

    @Autowired
    private MonitorItemTypeService monitorItemTypeService;

    @RequestMapping
    public ModelAndView list() {
        List<MonitorItemType> monitorItemTypeList;
        MonitorItemType monitorItemType = new MonitorItemType();

        monitorItemType.setCancelFlag(ConfigConstants.LOGICAL_CANCEL_FLAG_NOT_CANCEL);
        monitorItemTypeList = monitorItemTypeService.listMonitorItemType(monitorItemType);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PageName.config_monitor_item_type.getPath());
        modelAndView.addObject("monitorItemTypeList", monitorItemTypeList);
        return modelAndView;
    }

    @RequestMapping("/add")
    public ModelAndView add() {
        return addOrEdit(new MonitorItemType());
    }

    @RequestMapping("/edit")
    public ModelAndView edit(@RequestParam(value = "id", required = true) Long id) {
        return addOrEdit(monitorItemTypeService.getById(id));
    }

    private ModelAndView addOrEdit(MonitorItemType monitorItemType) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PageName.config_monitor_item_type_edit.getPath());
        modelAndView.addObject("monitorItemType", monitorItemType);
        return modelAndView;
    }

    @RequestMapping("/save")
    @ResponseBody
    public ResultOfAjax save(@ModelAttribute("monitorItemType") MonitorItemType monitorItemType) {
        logger.info("save monitorItemType: {}", monitorItemType.toString());
        monitorItemType.setStatus(ConfigConstants.STATUS_FLAG_EFFECTIVE);
        ResultOfAjax result = new ResultOfAjax();
        try {
            if (monitorItemType.getId() == 0) {
                monitorItemTypeService.addMonitorItemType(monitorItemType);
            } else {
                monitorItemTypeService.updateMonitorItemType(monitorItemType);
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
        logger.info("delete monitorItemType. ids: {}", ids);
        ResultOfAjax result = new ResultOfAjax();
        try {
            monitorItemTypeService.deleteMonitorItemType(ids);
            result.setCode(ResultOfAjax.CODE_SUCCEED);
            result.setMsg("成功");
        } catch (Exception e) {
            result.setCode(ResultOfAjax.CODE_FAILED);
            result.setMsg(e.getLocalizedMessage());
        }
        return result;
    }

}
