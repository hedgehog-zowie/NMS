package com.iuni.nms.webapp.controller;

import com.iuni.nms.common.constant.ConfigConstants;
import com.iuni.nms.persist.domain.MonitorObject;
import com.iuni.nms.service.MonitorObjectService;
import com.iuni.nms.webapp.common.PageName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
@Controller
@RequestMapping("/")
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private MonitorObjectService monitorObjectService;

    @RequestMapping
    public ModelAndView getHomePage() {
        return getIndexPage();
    }

    @RequestMapping("index")
    public ModelAndView getIndexPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PageName.index.getPath());
        return modelAndView;
    }

    @RequestMapping("index/listMonitorObject")
    @ResponseBody
    public List<MonitorObject> listMonitorObject() {
        List<MonitorObject> monitorObjectList = new ArrayList<>();
        MonitorObject monitorObject = new MonitorObject();

        monitorObject.setCancelFlag(ConfigConstants.LOGICAL_CANCEL_FLAG_NOT_CANCEL);
        monitorObject.setStatus(ConfigConstants.STATUS_FLAG_EFFECTIVE);
        monitorObject.setMonitorStatus(ConfigConstants.MONITOR_STATUS_FLAG_EFFECTIVE);
        List<MonitorObject> result = monitorObjectService.listMonitorObject(monitorObject);
        for (MonitorObject object : result) {
            MonitorObject newObject = new MonitorObject();
            newObject.setId(object.getId());
            newObject.setName(object.getName());
            newObject.setHealthyStatus(object.getHealthyStatus());
            monitorObjectList.add(newObject);
        }
        return monitorObjectList;
    }

}
