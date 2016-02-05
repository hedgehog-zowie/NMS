package com.iuni.nms.persist.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * @author zowie
 *         Email: nicholas@iuni.com
 */
@Entity
@Table(name = "ALARM_GROUP")
public class AlarmGroup extends AbstractDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "alarmGroupIdGen")
    @TableGenerator(name = "alarmGroupIdGen",
            table = "PK_TABLE", pkColumnName = "PK_KEY ", valueColumnName = "PK_VALUE", pkColumnValue = "alarmGroupId",
            initialValue = 100, allocationSize = 1)
    private long id;

    /**
     * 名称
     */
    @Column(name = "\"NAME\"")
    private String name;

    /**
     * 描述
     */
    @Column(name = "\"DESC\"")
    protected String desc;

    @OneToMany(mappedBy = "alarmGroup", fetch = FetchType.EAGER)
    private Set<AlarmItemGroup> alarmItemGroups;

    @OneToMany(mappedBy = "alarmGroup", fetch = FetchType.EAGER)
    private Set<AlarmGroupPerson> alarmGroupPersons;

    @Transient
    private Long[] alarmPersons;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Set<AlarmItemGroup> getAlarmItemGroups() {
        return alarmItemGroups;
    }

    public void setAlarmItemGroups(Set<AlarmItemGroup> alarmItemGroups) {
        this.alarmItemGroups = alarmItemGroups;
    }

    public Set<AlarmGroupPerson> getAlarmGroupPersons() {
        return alarmGroupPersons;
    }

    public void setAlarmGroupPersons(Set<AlarmGroupPerson> alarmGroupPersons) {
        this.alarmGroupPersons = alarmGroupPersons;
    }

    public Long[] getAlarmPersons() {
        return alarmPersons;
    }

    public void setAlarmPersons(Long[] alarmPersons) {
        this.alarmPersons = alarmPersons;
    }

    public Long[] getMonitorItems() {
        return monitorItems;
    }

    public void setMonitorItems(Long[] monitorItems) {
        this.monitorItems = monitorItems;
    }

}
