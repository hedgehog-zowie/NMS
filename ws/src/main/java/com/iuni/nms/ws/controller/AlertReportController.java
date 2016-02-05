package com.iuni.nms.ws.controller;

import com.iuni.nms.common.ResultOfAjax;
import com.iuni.nms.common.ReturnCode;
import com.iuni.nms.common.constant.ConfigConstants;
import com.iuni.nms.persist.domain.MonitorInfo;
import com.iuni.nms.persist.domain.MonitorItem;
import com.iuni.nms.persist.domain.MonitorItemProps;
import com.iuni.nms.persist.domain.MonitorObject;
import com.iuni.nms.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zowie
 *         Email: nicholas@iuni.com
 */
@RestController
@RequestMapping("/alert/report")
public class AlertReportController {

    private static final Logger logger = LoggerFactory.getLogger(AlertReportController.class);

    @Autowired
    private SmsService smsService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private MonitorObjectService monitorObjectService;
    @Autowired
    private MonitorItemService monitorItemService;
    @Autowired
    private MonitorItemPropsService monitorItemPropsService;
    @Autowired
    private MonitorInfoService monitorInfoService;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json; charset=utf-8", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResultOfAjax receiveData(@RequestBody MonitorInfo info) throws Exception {
        logger.info("receive monitor info: {}, {}, {}, {}", info.getObjectCode(), info.getItemCode(), info.getPropCode(), info.getMessage());
        MonitorObject monitorObject = monitorObjectService.getByCode(info.getObjectCode());
        if(monitorObject == null)
            return new ResultOfAjax(ReturnCode.ERROR_PARAM_NO_OBJECT.getCode(), ReturnCode.ERROR_PARAM_NO_OBJECT.getMsg());
        MonitorItem monitorItem = monitorItemService.getByCode(info.getItemCode());
        if(monitorItem == null)
            return new ResultOfAjax(ReturnCode.ERROR_PARAM_NO_ITEM.getCode(), ReturnCode.ERROR_PARAM_NO_ITEM.getMsg());
        MonitorItemProps monitorItemProps = monitorItemPropsService.getByCode(info.getPropCode());
        if(monitorItemProps == null)
            return new ResultOfAjax(ReturnCode.ERROR_PARAM_NO_PROPERTY.getCode(), ReturnCode.ERROR_PARAM_NO_PROPERTY.getMsg());
        // save monitor info
        MonitorInfo monitorInfo = new MonitorInfo();
        monitorInfo.setMonitorObject(monitorObject);
        monitorInfo.setMonitorItem(monitorItem);
        monitorInfo.setMonitorItemProps(monitorItemProps);
        monitorInfo.setMessage(info.getMessage());
        monitorInfo.setStatus(ConfigConstants.STATUS_FLAG_EFFECTIVE);
        monitorInfo.setCancelFlag(ConfigConstants.LOGICAL_CANCEL_FLAG_NOT_CANCEL);
        monitorInfoService.addMonitorInfo(monitorInfo);
        // change monitor object healthy status
        monitorObject.setHealthyStatus(ConfigConstants.HEALTHY_STATUS_FLAG_UNHEALTHY);
        monitorObjectService.updateMonitorObject(monitorObject);
        // send sms and email
        smsService.sendSms(monitorInfo);
        emailService.sendEmail(monitorInfo);
        return new ResultOfAjax(ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getMsg());
    }

}
