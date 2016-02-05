package com.iuni.nms.persist.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * @author zowie
 *         Email: nicholas@iuni.com
 */
@Entity
@Table(name="ALARM_ITEM_GROUP")
public class AlarmItemGroup extends AbstractDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "alarmItemGroupIdGen")
    @TableGenerator(name = "alarmItemGroupIdGen",
            table = "PK_TABLE", pkColumnName = "PK_KEY ", valueColumnName = "PK_VALUE", pkColumnValue = "alarmItemGroupId",
            initialValue = 100, allocationSize = 1)
    private long id;

    /**
     * 描述
     */
    @Column(name = "\"DESC\"")
    protected String desc;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private MonitorItem monitorItem;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    private AlarmGroup alarmGroup;

    @OneToMany(mappedBy = "alarmItemGroup")
    private Set<AlarmInfo> alarmInfos;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public MonitorItem getMonitorItem() {
        return monitorItem;
    }

    public void setMonitorItem(MonitorItem monitorItem) {
        this.monitorItem = monitorItem;
    }

    public AlarmGroup getAlarmGroup() {
        return alarmGroup;
    }

    public void setAlarmGroup(AlarmGroup alarmGroup) {
        this.alarmGroup = alarmGroup;
    }

    public Set<AlarmInfo> getAlarmInfos() {
        return alarmInfos;
    }

    public void setAlarmInfos(Set<AlarmInfo> alarmInfos) {
        this.alarmInfos = alarmInfos;
    }

}
