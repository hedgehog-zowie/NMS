package com.iuni.nms.webapp.common;

/**
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
public enum PageName {
    index("common/index"),

    config_system("config/system"),
    config_system_edit("config/system_edit"),
    config_module("config/module"),
    config_module_edit("config/module_edit"),
    config_monitor_info("config/monitor_info"),
    config_monitor_object("config/monitor_object"),
    config_monitor_object_edit("config/monitor_object_edit"),
    config_monitor_object_type("config/monitor_object_type"),
    config_monitor_object_type_edit("config/monitor_object_type_edit"),
    config_monitor_item("config/monitor_item"),
    config_monitor_item_edit("config/monitor_item_edit"),
    config_monitor_item_type("config/monitor_item_type"),
    config_monitor_item_type_edit("config/monitor_item_type_edit"),
    config_monitor_item_props("config/monitor_item_props"),
    config_monitor_item_props_edit("config/monitor_item_props_edit"),
    config_alarm_person("config/alarm_person"),
    config_alarm_person_edit("config/alarm_person_edit"),
    config_alarm_group("config/alarm_group"),
    config_alarm_group_edit("config/alarm_group_edit"),
    ;

    private final String path;

    PageName(String path){
        this.path = path;
    }

    public String getPath(){
        return this.path;
    }
}
