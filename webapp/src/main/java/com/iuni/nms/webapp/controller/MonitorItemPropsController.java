package com.iuni.nms.webapp.controller;

import com.iuni.nms.common.constant.ConfigConstants;
import com.iuni.nms.persist.domain.MonitorItem;
import com.iuni.nms.persist.domain.MonitorItemProps;
import com.iuni.nms.service.MonitorItemPropsService;
import com.iuni.nms.service.MonitorItemService;
import com.iuni.nms.webapp.common.MonitorItemPropsLevel;
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
@RequestMapping("/config/monitorItemProps")
public class MonitorItemPropsController {

    private static final Logger logger = LoggerFactory.getLogger(MonitorItemPropsController.class);

    @Autowired
    private MonitorItemService monitorItemService;
    @Autowired
    private MonitorItemPropsService monitorItemPropsService;

    @RequestMapping
    public ModelAndView list(@RequestParam(value = "itemId", required = true) Long itemId) {
        MonitorItem monitorItem = monitorItemService.getById(itemId);
        List<MonitorItemProps> monitorItemPropsList;
        MonitorItemProps monitorItemProps = new MonitorItemProps();
        monitorItemProps.setMonitorItem(monitorItem);
        monitorItemProps.setCancelFlag(ConfigConstants.LOGICAL_CANCEL_FLAG_NOT_CANCEL);
        monitorItemPropsList = monitorItemPropsService.listMonitorItemProps(monitorItemProps);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PageName.config_monitor_item_props.getPath());
        modelAndView.addObject("monitorItem", monitorItem);
        modelAndView.addObject("monitorItemPropsList", monitorItemPropsList);
        return modelAndView;
    }

    @RequestMapping("/add")
    public ModelAndView add(@RequestParam(value = "itemId", required = true) Long itemId) {
        MonitorItem monitorItem = monitorItemService.getById(itemId);
        MonitorItemProps monitorItemProps = new MonitorItemProps();
        monitorItemProps.setMonitorItem(monitorItem);
        return addOrEdit(monitorItemProps);
    }

    @RequestMapping("/edit")
    public ModelAndView edit(@RequestParam(value = "id", required = true) Long id) {
        return addOrEdit(monitorItemPropsService.getById(id));
    }

    private ModelAndView addOrEdit(MonitorItemProps monitorItemProps) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PageName.config_monitor_item_props_edit.getPath());
        modelAndView.addObject("monitorItemProps", monitorItemProps);
        modelAndView.addObject("monitorItemPropsLevels", MonitorItemPropsLevel.listAllLevel());
        return modelAndView;
    }

    @RequestMapping("/save")
    @ResponseBody
    public ResultOfAjax save(@ModelAttribute("monitorItemProps") MonitorItemProps monitorItemProps) {
        logger.info("save monitorItemProps: {}", monitorItemProps.toString());
        monitorItemProps.setStatus(ConfigConstants.STATUS_FLAG_EFFECTIVE);
        ResultOfAjax result = new ResultOfAjax();
        try {
            if (monitorItemProps.getId() == 0) {
                monitorItemPropsService.addMonitorItemProps(monitorItemProps);
            } else {
                monitorItemPropsService.updateMonitorItemProps(monitorItemProps);
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
        logger.info("delete monitorItemProps. ids: {}", ids);
        ResultOfAjax result = new ResultOfAjax();
        try {
            monitorItemPropsService.deleteMonitorItemProps(ids);
            result.setCode(ResultOfAjax.CODE_SUCCEED);
            result.setMsg("成功");
        } catch (Exception e) {
            result.setCode(ResultOfAjax.CODE_FAILED);
            result.setMsg(e.getLocalizedMessage());
        }
        return result;
    }

}
