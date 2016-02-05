package com.iuni.nms.persist.domain;

import javax.persistence.*;

/**
 * @author zowie
 *         Email: nicholas@iuni.com
 */
@Entity
@Table(name="ALARM_GROUP_PERSON")
public class AlarmGroupPerson extends AbstractDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "alarmGroupPersonIdGen")
    @TableGenerator(name = "alarmGroupPersonIdGen",
            table = "PK_TABLE", pkColumnName = "PK_KEY ", valueColumnName = "PK_VALUE", pkColumnValue = "alarmGroupPersonId",
            initialValue = 100, allocationSize = 1)
    private long id;

    /**
     * 描述
     */
    @Column(name = "\"DESC\"")
    protected String desc;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    private AlarmGroup alarmGroup;

    @ManyToOne
    @JoinColumn(name = "PERSON_ID")
    private AlarmPerson alarmPerson;

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

    public AlarmGroup getAlarmGroup() {
        return alarmGroup;
    }

    public void setAlarmGroup(AlarmGroup alarmGroup) {
        this.alarmGroup = alarmGroup;
    }

    public AlarmPerson getAlarmPerson() {
        return alarmPerson;
    }

    public void setAlarmPerson(AlarmPerson alarmPerson) {
        this.alarmPerson = alarmPerson;
    }
}
