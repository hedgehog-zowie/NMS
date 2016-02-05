package com.iuni.nms.common.constant;

/**
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
public interface ConfigConstants {

    public static final Integer LOGICAL_CANCEL_FLAG_CANCEL = 1;
    public static final Integer LOGICAL_CANCEL_FLAG_NOT_CANCEL = 0;

    /**
     * 有效
     */
    public static final Integer STATUS_FLAG_EFFECTIVE = 1;
    /**
     * 无效
     */
    public static final Integer STATUS_FLAG_INVALID = 0;

    /**
     * 监控
     */
    public static final Integer MONITOR_STATUS_FLAG_EFFECTIVE = 1;
    /**
     * 不监控
     */
    public static final Integer MONITOR_STATUS_FLAG_INVALID = 0;

    /**
     * 告警
     */
    public static final Integer ALARM_STATUS_FLAG_EFFECTIVE = 1;
    /**
     * 不告警
     */
    public static final Integer ALARM_STATUS_FLAG_INVALID = 0;

    /**
     * 不健康
     */
    public static final Integer HEALTHY_STATUS_FLAG_UNHEALTHY = 1;
    /**
     * 健康
     */
    public static final Integer HEALTHY_STATUS_FLAG_HEALTHY = 0;

}
