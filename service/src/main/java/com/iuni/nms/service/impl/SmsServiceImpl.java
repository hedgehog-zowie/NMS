package com.iuni.nms.service.impl;

import com.iuni.nms.common.constant.ConfigConstants;
import com.iuni.nms.common.entity.SmsRequestEntity;
import com.iuni.nms.persist.domain.AlarmGroupPerson;
import com.iuni.nms.persist.domain.AlarmItemGroup;
import com.iuni.nms.persist.domain.MonitorInfo;
import com.iuni.nms.service.SmsService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zowie
 *         Email: nicholas@iuni.com
 */
public class SmsServiceImpl implements SmsService {

    private static final Logger logger = LoggerFactory.getLogger(SmsService.class);

    /**
     * 短信接口地址
     */
    private String smsServiceUrl;

    private CloseableHttpClient httpClient;
    private HttpPost method;
    private boolean initFlag = false;

    /**
     * 初始化
     */
    private void init() {
        if (!initFlag && !StringUtils.isEmpty(smsServiceUrl)) {
            httpClient = HttpClients.createDefault();
            method = new HttpPost(smsServiceUrl);
            initFlag = true;
        }
    }

    @Override
    public void sendSms(MonitorInfo monitorInfo) {
        String content;
        try {
            content = String.format(monitorInfo.getMonitorItemProps().getAlarmTemplate(), monitorInfo.getMessage());
        } catch (Exception e) {
            logger.warn("格式化模板错误，直接发送上报信息。");
            content = monitorInfo.getMessage();
        }
        List<SmsRequestEntity> smsEntityList = new ArrayList<>();
        if (monitorInfo.getMonitorItem().getAlarmItemGroups() == null || monitorInfo.getMonitorItem().getAlarmItemGroups().size() == 0) {
            logger.error("未配置告警组，请检查配置，监控项：{}", monitorInfo.getMonitorItem().getName());
            return;
        }
        for (AlarmItemGroup alarmItemGroup : monitorInfo.getMonitorItem().getAlarmItemGroups()) {
            if (alarmItemGroup.getAlarmGroup().getAlarmGroupPersons() == null || alarmItemGroup.getAlarmGroup().getAlarmGroupPersons().size() == 0) {
                logger.warn("告警组【{}】无告警人。", alarmItemGroup.getAlarmGroup().getName());
                continue;
            }
            if (alarmItemGroup.getCancelFlag() == ConfigConstants.LOGICAL_CANCEL_FLAG_CANCEL ||
                    alarmItemGroup.getStatus() == ConfigConstants.STATUS_FLAG_INVALID)
                continue;
            for (AlarmGroupPerson alarmGroupPerson : alarmItemGroup.getAlarmGroup().getAlarmGroupPersons()) {
                if (alarmGroupPerson.getCancelFlag() == ConfigConstants.LOGICAL_CANCEL_FLAG_CANCEL ||
                        alarmGroupPerson.getStatus() == ConfigConstants.STATUS_FLAG_INVALID)
                    continue;
                SmsRequestEntity smsEntity = new SmsRequestEntity(alarmGroupPerson.getAlarmPerson().getMobile(), 5, content);
                smsEntityList.add(smsEntity);
            }
        }
        if (smsEntityList.size() == 0) {
            logger.warn("监控项【{}】无有效告警人", monitorInfo.getMonitorItem().getName());
            return;
        }
        JSONArray jsonArray = JSONArray.fromObject(smsEntityList);
        String parameters = jsonArray.toString();
        printParameters(parameters);
        logger.info("调用短信接口：{}, 内容：{}", getSmsServiceUrl(), parameters);
        String smsResponse = post(parameters);
        JSONObject jsonObject = JSONObject.fromObject(smsResponse);
        if (jsonObject == null || jsonObject.get("state") == 0)
            logger.error("短信接口返回失败。");
    }

    /**
     * 打印参数内容
     *
     * @param parameters
     */
    private void printParameters(String parameters) {
        logger.info(parameters);
    }

    /**
     * 调用 API
     *
     * @param parameters
     * @return
     */
    public String post(String parameters) {
        String body = null;
        init();
        if (httpClient == null || method == null || StringUtils.isEmpty(parameters)) {
            logger.error("发送短信错误，httpClient 或 method 为空。短信接口地址：{}", getSmsServiceUrl());
            return body;
        }
        try {
            method.addHeader("Content-type", "application/json; charset=utf-8");
            method.setHeader("Accept", "application/json; charset=utf-8");
            method.setEntity(new StringEntity(parameters, Charset.forName("UTF-8")));

            HttpResponse response = httpClient.execute(method);

            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode != HttpStatus.SC_OK)
                logger.error("调用方法失败:" + response.getStatusLine());

            // Read the response body
            body = EntityUtils.toString(response.getEntity());

        } catch (IOException e) {
            logger.error("调用方法失败:" + e.getLocalizedMessage());
        }
        return body;
    }

    public String getSmsServiceUrl() {
        return smsServiceUrl;
    }

    public void setSmsServiceUrl(String smsServiceUrl) {
        this.smsServiceUrl = smsServiceUrl;
    }
}
