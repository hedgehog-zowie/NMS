package com.iuni.nms.persist.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * @author zowie
 *         Email: nicholas@iuni.com
 */
@Entity
@Table(name="MONITOR_ITEM")
public class MonitorItem extends AbstractDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "monitorItemIdGen")
    @TableGenerator(name = "monitorItemIdGen",
            table = "PK_TABLE", pkColumnName = "PK_KEY ", valueColumnName = "PK_VALUE", pkColumnValue = "monitorItemId",
            initialValue = 100, allocationSize = 1)
    private long id;

    /**
     * 名称
     */
    @Column(name = "\"NAME\"")
    private String name;

    /**
     * 编码
     */
    @Column(name = "CODE")
    private String code;

    /**
     * 描述
     */
    @Column(name = "\"DESC\"")
    protected String desc;

    @ManyToOne
    @JoinColumn(name = "ITEM_TYPE")
    private MonitorItemType monitorItemType;

    @OneToMany(mappedBy = "monitorItem")
    private Set<MonitorItemProps> monitorItemProps;

    @OneToMany(mappedBy = "monitorItem")
    private Set<MonitorObjectItem> monitorObjectItems;

    @OneToMany(mappedBy = "monitorItem")
    private Set<MonitorInfo> monitorInfos;

    @OneToMany(mappedBy = "monitorItem")
    private Set<AlarmItemGroup> alarmItemGroups;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public MonitorItemType getMonitorItemType() {
        return monitorItemType;
    }

    public void setMonitorItemType(MonitorItemType monitorItemType) {
        this.monitorItemType = monitorItemType;
    }

    public Set<MonitorItemProps> getMonitorItemProps() {
        return monitorItemProps;
    }

    public void setMonitorItemProps(Set<MonitorItemProps> monitorItemProps) {
        this.monitorItemProps = monitorItemProps;
    }

    public Set<MonitorObjectItem> getMonitorObjectItems() {
        return monitorObjectItems;
    }

    public void setMonitorObjectItems(Set<MonitorObjectItem> monitorObjectItems) {
        this.monitorObjectItems = monitorObjectItems;
    }

    public Set<MonitorInfo> getMonitorInfos() {
        return monitorInfos;
    }

    public void setMonitorInfos(Set<MonitorInfo> monitorInfos) {
        this.monitorInfos = monitorInfos;
    }

    public Set<AlarmItemGroup> getAlarmItemGroups() {
        return alarmItemGroups;
    }

    public void setAlarmItemGroups(Set<AlarmItemGroup> alarmItemGroups) {
        this.alarmItemGroups = alarmItemGroups;
    }
}
