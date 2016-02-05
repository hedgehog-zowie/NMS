package com.iuni.nms.persist.domain;

import javax.persistence.*;

/**
 * @author zowie
 *         Email: nicholas@iuni.com
 */
@Entity
@Table(name="MONITOR_OBJECT_ITEM")
public class MonitorObjectItem extends AbstractDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "monitorObjectItemIdGen")
    @TableGenerator(name = "monitorObjectItemIdGen",
            table = "PK_TABLE", pkColumnName = "PK_KEY ", valueColumnName = "PK_VALUE", pkColumnValue = "monitorObjectItemId",
            initialValue = 100, allocationSize = 1)
    private long id;

    /**
     * 描述
     */
    @Column(name = "\"DESC\"")
    protected String desc;

    @ManyToOne
    @JoinColumn(name = "OBJECT_ID")
    private MonitorObject monitorObject;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private MonitorItem monitorItem;

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

    public MonitorObject getMonitorObject() {
        return monitorObject;
    }

    public void setMonitorObject(MonitorObject monitorObject) {
        this.monitorObject = monitorObject;
    }

    public MonitorItem getMonitorItem() {
        return monitorItem;
    }

    public void setMonitorItem(MonitorItem monitorItem) {
        this.monitorItem = monitorItem;
    }

}
