package com.iuni.nms.common.entity;

/**
 * @author zowie
 *         Email: nicholas@iuni.com
 */
public class SmsRequestEntity {

    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 短信来源数据：
     * IUNI 前端商城：”1”,
     * IUNI 用户中心：”2”,
     * IUNI 订单系统：“3”,
     * IUNI WMS：”4”
     */
    private Integer smsSource;
    /**
     * 短信内容
     */
    private String smsContent;

    public SmsRequestEntity(String mobile, Integer smsSource, String smsContent) {
        this.mobile = mobile;
        this.smsSource = smsSource;
        this.smsContent = smsContent;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getSmsSource() {
        return smsSource;
    }

    public void setSmsSource(Integer smsSource) {
        this.smsSource = smsSource;
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }

}
