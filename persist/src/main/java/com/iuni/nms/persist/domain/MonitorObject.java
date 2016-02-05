package com.iuni.nms.persist.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * @author zowie
 *         Email: nicholas@iuni.com
 */
@Entity
@Table(name="MONITOR_OBJECT")
public class MonitorObject extends AbstractDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "monitorObjectIdGen")
    @TableGenerator(name = "monitorObjectIdGen",
            table = "PK_TABLE", pkColumnName = "PK_KEY ", valueColumnName = "PK_VALUE", pkColumnValue = "monitorObjectId",
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
     * 公网IP
     */
    @Column(name="PUBLICIP")
    private String publicIp;

    /**
     * 内网IP
     */
    @Column(name="PRIVATEIP")
    private String privateIp;

    /**
     * 监控开关，0不监控，1监控
     */
    @Column(name="MONITOR_STATUS")
    private Integer monitorStatus;

    /**
     * 告警开关，0不告警，1告警
     */
    @Column(name="ALARM_STATUS")
    private Integer alarmStatus;

    /**
     * 监控对象健康标识，0表示健康，1表示不健康。
     */
    @Column(name="HEALTHY_STATUS")
    private Integer healthyStatus;

    /**
     * 描述
     */
    @Column(name = "\"DESC\"")
    protected String desc;

    @ManyToOne
    @JoinColumn(name = "MODULE_ID")
    private Module module;

    @ManyToOne
    @JoinColumn(name="OBJECT_TYPE")
    private MonitorObjectType monitorObjectType;

    @OneToMany(mappedBy = "monitorObject")
    private Set<MonitorInfo> monitorInfos;

    @OneToMany(mappedBy = "monitorObject", fetch = FetchType.EAGER)
    private Set<MonitorObjectItem> monitorObjectItems;

    @Transient
    private Long[] monitorItems;

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

    public String getPublicIp() {
        return publicIp;
    }

    public void setPublicIp(String publicIp) {
        this.publicIp = publicIp;
    }

    public String getPrivateIp() {
        return privateIp;
    }

    public void setPrivateIp(String privateIp) {
        this.privateIp = privateIp;
    }

    public Integer getMonitorStatus() {
        return monitorStatus;
    }

    public void setMonitorStatus(Integer monitorStatus) {
        this.monitorStatus = monitorStatus;
    }

    public Integer getAlarmStatus() {
        return alarmStatus;
    }

    public void setAlarmStatus(Integer alarmStatus) {
        this.alarmStatus = alarmStatus;
    }

    public Integer getHealthyStatus() {
        return healthyStatus;
    }

    public void setHealthyStatus(Integer healthyStatus) {
        this.healthyStatus = healthyStatus;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public MonitorObjectType getMonitorObjectType() {
        return monitorObjectType;
    }

    public void setMonitorObjectType(MonitorObjectType monitorObjectType) {
        this.monitorObjectType = monitorObjectType;
    }

    public Set<MonitorInfo> getMonitorInfos() {
        return monitorInfos;
    }

    public void setMonitorInfos(Set<MonitorInfo> monitorInfos) {
        this.monitorInfos = monitorInfos;
    }

    public Set<MonitorObjectItem> getMonitorObjectItems() {
        return monitorObjectItems;
    }

    public void setMonitorObjectItems(Set<MonitorObjectItem> monitorObjectItems) {
        this.monitorObjectItems = monitorObjectItems;
    }

    public Long[] getMonitorItems() {
        return monitorItems;
    }

    public void setMonitorItems(Long[] monitorItems) {
        this.monitorItems = monitorItems;
    }

}
