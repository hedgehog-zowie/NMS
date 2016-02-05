package com.iuni.nms.webapp.controller;

import com.iuni.nms.common.constant.ConfigConstants;
import com.iuni.nms.persist.domain.MonitorItem;
import com.iuni.nms.persist.domain.MonitorItemType;
import com.iuni.nms.service.MonitorItemService;
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
@RequestMapping("/config/monitorItem")
public class MonitorItemController {

    private static final Logger logger = LoggerFactory.getLogger(MonitorItemController.class);

    @Autowired
    private MonitorItemService monitorItemService;
    @Autowired
    private MonitorItemTypeService monitorItemTypeService;

    @RequestMapping
    public ModelAndView list() {
        List<MonitorItem> monitorItemList;
        MonitorItem monitorItem = new MonitorItem();

        monitorItem.setCancelFlag(ConfigConstants.LOGICAL_CANCEL_FLAG_NOT_CANCEL);
        monitorItemList = monitorItemService.listMonitorItem(monitorItem);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PageName.config_monitor_item.getPath());
        modelAndView.addObject("monitorItemList", monitorItemList);
        return modelAndView;
    }

    @RequestMapping("/add")
    public ModelAndView add() {
        return addOrEdit(new MonitorItem());
    }

    @RequestMapping("/edit")
    public ModelAndView edit(@RequestParam(value = "id", required = true) Long id) {
        return addOrEdit(monitorItemService.getById(id));
    }

    private ModelAndView addOrEdit(MonitorItem monitorItem) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PageName.config_monitor_item_edit.getPath());
        modelAndView.addObject("monitorItem", monitorItem);
        MonitorItemType monitorItemType = new MonitorItemType();
        monitorItemType.setStatus(ConfigConstants.STATUS_FLAG_EFFECTIVE);
        monitorItemType.setCancelFlag(ConfigConstants.LOGICAL_CANCEL_FLAG_NOT_CANCEL);
        List<MonitorItemType> monitorItemTypes = monitorItemTypeService.listMonitorItemType(monitorItemType);
        modelAndView.addObject("monitorItemTypes", monitorItemTypes);
        return modelAndView;
    }

    @RequestMapping("/save")
    @ResponseBody
    public ResultOfAjax save(@ModelAttribute("monitorItem") MonitorItem monitorItem) {
        logger.info("save monitorItem: {}", monitorItem.toString());
        monitorItem.setStatus(ConfigConstants.STATUS_FLAG_EFFECTIVE);
        ResultOfAjax result = new ResultOfAjax();
        try {
            if (monitorItem.getId() == 0) {
                monitorItemService.addMonitorItem(monitorItem);
            } else {
                monitorItemService.updateMonitorItem(monitorItem);
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
        logger.info("delete monitorItem. ids: {}", ids);
        ResultOfAjax result = new ResultOfAjax();
        try {
            monitorItemService.deleteMonitorItem(ids);
            result.setCode(ResultOfAjax.CODE_SUCCEED);
            result.setMsg("成功");
        } catch (Exception e) {
            result.setCode(ResultOfAjax.CODE_FAILED);
            result.setMsg(e.getLocalizedMessage());
        }
        return result;
    }

}
