package com.iuni.nms.webapp.controller;

import com.iuni.nms.common.constant.ConfigConstants;
import com.iuni.nms.persist.domain.AlarmPerson;
import com.iuni.nms.service.AlarmPersonService;
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
@RequestMapping("/config/alarmPerson")
public class AlarmPersonController {

    private static final Logger logger = LoggerFactory.getLogger(AlarmPersonController.class);

    @Autowired
    private AlarmPersonService alarmPersonService;

    @RequestMapping
    public ModelAndView list() {
        List<AlarmPerson> alarmPersonList;
        AlarmPerson alarmPerson = new AlarmPerson();

        alarmPerson.setCancelFlag(ConfigConstants.LOGICAL_CANCEL_FLAG_NOT_CANCEL);
        alarmPersonList = alarmPersonService.listAlarmPerson(alarmPerson);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PageName.config_alarm_person.getPath());
        modelAndView.addObject("alarmPersonList", alarmPersonList);
        return modelAndView;
    }

    @RequestMapping("/add")
    public ModelAndView add() {
        return addOrEdit(new AlarmPerson());
    }

    @RequestMapping("/edit")
    public ModelAndView edit(@RequestParam(value = "id", required = true) Long id) {
        return addOrEdit(alarmPersonService.getById(id));
    }

    private ModelAndView addOrEdit(AlarmPerson alarmPerson) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PageName.config_alarm_person_edit.getPath());
        modelAndView.addObject("alarmPerson", alarmPerson);
        return modelAndView;
    }

    @RequestMapping("/save")
    @ResponseBody
    public ResultOfAjax save(@ModelAttribute("alarmPerson") AlarmPerson alarmPerson) {
        logger.info("save alarmPerson: {}", alarmPerson.toString());
        alarmPerson.setStatus(ConfigConstants.STATUS_FLAG_EFFECTIVE);
        ResultOfAjax result = new ResultOfAjax();
        try {
            if (alarmPerson.getId() == 0) {
                alarmPersonService.addAlarmPerson(alarmPerson);
            } else {
                alarmPersonService.updateAlarmPerson(alarmPerson);
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
        logger.info("delete alarmPerson. ids: {}", ids);
        ResultOfAjax result = new ResultOfAjax();
        try {
            alarmPersonService.deleteAlarmPerson(ids);
            result.setCode(ResultOfAjax.CODE_SUCCEED);
            result.setMsg("成功");
        } catch (Exception e) {
            result.setCode(ResultOfAjax.CODE_FAILED);
            result.setMsg(e.getLocalizedMessage());
        }
        return result;
    }

}
