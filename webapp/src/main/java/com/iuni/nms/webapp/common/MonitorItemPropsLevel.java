package com.iuni.nms.webapp.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zowie
 *         Email: nicholas@iuni.com
 */
public enum MonitorItemPropsLevel {
    ERROR("error", "错误"),
    WARN("warn", "警告"),
    INFO("info", "信息"),
    DEBUG("debug", "调试"),
    ;

    private String code;
    private String name;

    MonitorItemPropsLevel(String code, String name){
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<MonitorItemPropsLevel> listAllLevel(){
        List<MonitorItemPropsLevel> levelList = new ArrayList<>();
        levelList.add(MonitorItemPropsLevel.ERROR);
        levelList.add(MonitorItemPropsLevel.WARN);
        levelList.add(MonitorItemPropsLevel.INFO);
        levelList.add(MonitorItemPropsLevel.DEBUG);
        return levelList;
    }
}
