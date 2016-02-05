package com.iuni.nms.webapp.controller;

import com.iuni.nms.common.ResultOfAjax;
import com.iuni.nms.common.constant.ConfigConstants;
import com.iuni.nms.persist.domain.*;
import com.iuni.nms.service.*;
import com.iuni.nms.webapp.common.PageName;
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
@RequestMapping("/monitorInfo")
public class MonitorInfoController {

    private static final Logger logger = LoggerFactory.getLogger(MonitorInfoController.class);

    @Autowired
    private MonitorInfoService monitorInfoService;

    @RequestMapping
    public ModelAndView list(@RequestParam(value = "objectId", required = true) Long objectId) {
        List<MonitorInfo> monitorInfos;
        MonitorInfo monitorInfo = new MonitorInfo();
        MonitorObject monitorObject = new MonitorObject();
        monitorObject.setId(objectId);
        monitorInfo.setMonitorObject(monitorObject);
        // 只显示未逻辑删除且未处理的监控信息
        monitorInfo.setCancelFlag(ConfigConstants.LOGICAL_CANCEL_FLAG_NOT_CANCEL);
        monitorInfo.setStatus(ConfigConstants.STATUS_FLAG_EFFECTIVE);
        monitorInfos = monitorInfoService.listMonitorInfo(monitorInfo);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PageName.config_monitor_info.getPath());
        modelAndView.addObject("monitorObject", monitorObject);
        modelAndView.addObject("monitorInfos", monitorInfos);
        return modelAndView;
    }

    @RequestMapping("/handle")
    @ResponseBody
    public ResultOfAjax handle(@RequestParam(value = "ids", required = true) String ids) {
        logger.info("handle monitorObject. ids: {}", ids);
        ResultOfAjax result = new ResultOfAjax();
        try {
            monitorInfoService.handleMonitorInfo(ids);
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
            monitorInfoService.deleteMonitorInfo(ids);
            result.setCode(ResultOfAjax.CODE_SUCCEED);
            result.setMsg("成功");
        } catch (Exception e) {
            result.setCode(ResultOfAjax.CODE_FAILED);
            result.setMsg(e.getLocalizedMessage());
        }
        return result;
    }

}
