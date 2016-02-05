package com.iuni.nms.service.impl;

import com.iuni.nms.common.constant.ConfigConstants;
import com.iuni.nms.common.entity.EmailRequestEntity;
import com.iuni.nms.persist.domain.AlarmGroupPerson;
import com.iuni.nms.persist.domain.AlarmItemGroup;
import com.iuni.nms.persist.domain.MonitorInfo;
import com.iuni.nms.service.EmailService;
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
public class EmailServiceImpl implements EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    /**
     * 邮件接口地址
     */
    private String emailServiceUrl;

    private CloseableHttpClient httpClient;
    private HttpPost method;
    private boolean initFlag = false;
    /**
     * 邮件标题长度
     */
    private final int TITLE_LENGTH = 15;

    @Override
    public void sendEmail(MonitorInfo monitorInfo) {
        String content;
        try {
            content = String.format(monitorInfo.getMonitorItemProps().getAlarmTemplate(), monitorInfo.getMessage());
        } catch (Exception e) {
            logger.warn("格式化模板错误，直接发送上报信息。");
            content = monitorInfo.getMessage();
        }
        List<EmailRequestEntity> emailEntityList = new ArrayList<>();
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
                EmailRequestEntity emailEntity = new EmailRequestEntity(alarmGroupPerson.getAlarmPerson().getEmail(), content.substring(0, content.length() > TITLE_LENGTH ? TITLE_LENGTH : content.length()), content, "IUNI");
                emailEntityList.add(emailEntity);
            }
        }
        if (emailEntityList.size() == 0) {
            logger.warn("监控项【{}】无有效告警人", monitorInfo.getMonitorItem().getName());
            return;
        }
        JSONArray jsonArray = JSONArray.fromObject(emailEntityList);
        String parameters = jsonArray.toString();
        printParameters(parameters);
        logger.info("调用邮件接口：{}, 内容：{}", getEmailServiceUrl(), parameters);
        String emailResponse = post(parameters);
        JSONObject jsonObject = JSONObject.fromObject(emailResponse);
        if (jsonObject == null || jsonObject.get("state") == 0)
            logger.error("邮件接口返回失败。");
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
            logger.error("发送邮件错误，httpClient 或 method 为空。邮件接口地址：{}", getEmailServiceUrl());
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

    /**
     * 初始化
     */
    private void init() {
        if (!initFlag && !StringUtils.isEmpty(emailServiceUrl)) {
            httpClient = HttpClients.createDefault();
            method = new HttpPost(emailServiceUrl);
            initFlag = true;
        }
    }

    public String getEmailServiceUrl() {
        return emailServiceUrl;
    }

    public void setEmailServiceUrl(String emailServiceUrl) {
        this.emailServiceUrl = emailServiceUrl;
    }
}
