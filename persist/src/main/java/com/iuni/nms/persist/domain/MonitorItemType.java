package com.iuni.nms.persist.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * @author zowie
 *         Email: nicholas@iuni.com
 */
@Entity
@Table(name="MONITOR_ITEM_TYPE")
public class MonitorItemType extends AbstractDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "monitorItemTypeIdGen")
    @TableGenerator(name = "monitorItemTypeIdGen",
            table = "PK_TABLE", pkColumnName = "PK_KEY ", valueColumnName = "PK_VALUE", pkColumnValue = "monitorItemTypeId",
            initialValue = 100, allocationSize = 1)
    private long id;

    /**
     * 类型名称（如：业务监控、系统监控等）
     */
    @Column(name = "\"NAME\"")
    private String name;

    /**
     * 描述
     */
    @Column(name = "\"DESC\"")
    protected String desc;

    @OneToMany(mappedBy = "monitorObjectType")
    private Set<MonitorObject> monitorObjects;

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

    public Set<MonitorObject> getMonitorObjects() {
        return monitorObjects;
    }

    public void setMonitorObjects(Set<MonitorObject> monitorObjects) {
        this.monitorObjects = monitorObjects;
    }

}
