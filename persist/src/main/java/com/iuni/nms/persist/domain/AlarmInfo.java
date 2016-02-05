package com.iuni.nms.persist.domain;

import javax.persistence.*;

/**
 * @author zowie
 *         Email: nicholas@iuni.com
 */
@Entity
@Table(name="ALARM_INFO")
public class AlarmInfo extends AbstractDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "alarmInfoIdGen")
    @TableGenerator(name = "alarmInfoIdGen",
            table = "PK_TABLE", pkColumnName = "PK_KEY ", valueColumnName = "PK_VALUE", pkColumnValue = "alarmInfoId",
            initialValue = 100, allocationSize = 1)
    private long id;

    @ManyToOne
    @JoinColumn(name = "ITEM_GROUP_ID")
    private AlarmItemGroup alarmItemGroup;

    private String info;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AlarmItemGroup getAlarmItemGroup() {
        return alarmItemGroup;
    }

    public void setAlarmItemGroup(AlarmItemGroup alarmItemGroup) {
        this.alarmItemGroup = alarmItemGroup;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
