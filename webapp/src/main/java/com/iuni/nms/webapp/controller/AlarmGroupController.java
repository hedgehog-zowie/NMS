package com.iuni.nms.webapp.controller;

import com.iuni.nms.common.ResultOfAjax;
import com.iuni.nms.common.constant.ConfigConstants;
import com.iuni.nms.persist.domain.AlarmGroup;
import com.iuni.nms.persist.domain.AlarmPerson;
import com.iuni.nms.persist.domain.MonitorItem;
import com.iuni.nms.service.AlarmGroupService;
import com.iuni.nms.service.AlarmPersonService;
import com.iuni.nms.service.MonitorItemService;
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
@RequestMapping("/config/alarmGroup")
public class AlarmGroupController {

    private static final Logger logger = LoggerFactory.getLogger(AlarmGroupController.class);

    @Autowired
    private AlarmGroupService alarmGroupService;
    @Autowired
    private AlarmPersonService alarmPersonService;
    @Autowired
    private MonitorItemService monitorItemService;

    @RequestMapping
    public ModelAndView list() {
        List<AlarmGroup> alarmGroupList;
        AlarmGroup alarmGroup = new AlarmGroup();

        alarmGroup.setCancelFlag(ConfigConstants.LOGICAL_CANCEL_FLAG_NOT_CANCEL);
        alarmGroupList = alarmGroupService.listAlarmGroup(alarmGroup);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PageName.config_alarm_group.getPath());
        modelAndView.addObject("alarmGroupList", alarmGroupList);
        return modelAndView;
    }

    @RequestMapping("/add")
    public ModelAndView add() {
        return addOrEdit(new AlarmGroup());
    }

    @RequestMapping("/edit")
    public ModelAndView edit(@RequestParam(value = "id", required = true) Long id) {
        return addOrEdit(alarmGroupService.getById(id));
    }

    private ModelAndView addOrEdit(AlarmGroup alarmGroup) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PageName.config_alarm_group_edit.getPath());
        modelAndView.addObject("alarmGroup", alarmGroup);

        AlarmPerson alarmPerson = new AlarmPerson();
        alarmPerson.setCancelFlag(ConfigConstants.LOGICAL_CANCEL_FLAG_NOT_CANCEL);
        alarmPerson.setStatus(ConfigConstants.STATUS_FLAG_EFFECTIVE);
        List<AlarmPerson> alarmPersons = alarmPersonService.listAlarmPerson(alarmPerson);
        modelAndView.addObject("alarmPersons", alarmPersons);

        MonitorItem monitorItem = new MonitorItem();
        monitorItem.setCancelFlag(ConfigConstants.LOGICAL_CANCEL_FLAG_NOT_CANCEL);
        monitorItem.setStatus(ConfigConstants.STATUS_FLAG_EFFECTIVE);
        List<MonitorItem> monitorItems = monitorItemService.listMonitorItem(monitorItem);
        modelAndView.addObject("monitorItems", monitorItems);
        return modelAndView;
    }

    @RequestMapping("/save")
    @ResponseBody
    public ResultOfAjax save(@ModelAttribute("alarmGroup") AlarmGroup alarmGroup) {
        logger.info("save alarmGroup: {}", alarmGroup.toString());
        alarmGroup.setStatus(ConfigConstants.STATUS_FLAG_EFFECTIVE);
        ResultOfAjax result = new ResultOfAjax();
        try {
            if (alarmGroup.getId() == 0) {
                alarmGroupService.addAlarmGroup(alarmGroup);
            } else {
                alarmGroupService.updateAlarmGroup(alarmGroup);
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
        logger.info("delete alarmGroup. ids: {}", ids);
        ResultOfAjax result = new ResultOfAjax();
        try {
            alarmGroupService.deleteAlarmGroup(ids);
            result.setCode(ResultOfAjax.CODE_SUCCEED);
            result.setMsg("成功");
        } catch (Exception e) {
            result.setCode(ResultOfAjax.CODE_FAILED);
            result.setMsg(e.getLocalizedMessage());
        }
        return result;
    }

}
