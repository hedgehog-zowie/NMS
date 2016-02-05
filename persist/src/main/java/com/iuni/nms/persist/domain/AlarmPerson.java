package com.iuni.nms.persist.domain;

import javax.persistence.*;

/**
 * @author zowie
 *         Email: nicholas@iuni.com
 */
@Entity
@Table(name="ALARM_PERSON")
public class AlarmPerson extends AbstractDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "alarmPersonIdGen")
    @TableGenerator(name = "alarmPersonIdGen",
            table = "PK_TABLE", pkColumnName = "PK_KEY ", valueColumnName = "PK_VALUE", pkColumnValue = "alarmPersonId",
            initialValue = 100, allocationSize = 1)
    private long id;

    /**
     * 名称
     */
    @Column(name = "\"NAME\"")
    private String name;

    /**
     * 移动电话
     */
    @Column(name = "MOBILE")
    private String mobile;

    /**
     * 邮箱
     */
    @Column(name = "EMAIL")
    private String email;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
