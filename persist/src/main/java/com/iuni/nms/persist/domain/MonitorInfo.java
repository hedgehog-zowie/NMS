package com.iuni.nms.persist.domain;

import javax.persistence.*;

/**
 * @author zowie
 *         Email: nicholas@iuni.com
 */
@Entity
@Table(name="MONITOR_INFO")
public class MonitorInfo extends AbstractDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "monitorInfoIdGen")
    @TableGenerator(name = "monitorInfoIdGen",
            table = "PK_TABLE", pkColumnName = "PK_KEY ", valueColumnName = "PK_VALUE", pkColumnValue = "monitorInfoId",
            initialValue = 100, allocationSize = 1)
    private long id;

    @ManyToOne
    @JoinColumn(name = "OBJECT", referencedColumnName = "CODE")
    private MonitorObject monitorObject;

    @ManyToOne
    @JoinColumn(name="ITEM", referencedColumnName = "CODE")
    private MonitorItem monitorItem;

    @ManyToOne
    @JoinColumn(name="PROP", referencedColumnName = "CODE")
    private MonitorItemProps monitorItemProps;

    private String message;

    @Transient
    private String objectCode;
    @Transient
    private String itemCode;
    @Transient
    private String propCode;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public MonitorItemProps getMonitorItemProps() {
        return monitorItemProps;
    }

    public void setMonitorItemProps(MonitorItemProps monitorItemProps) {
        this.monitorItemProps = monitorItemProps;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getObjectCode() {
        return objectCode;
    }

    public void setObjectCode(String objectCode) {
        this.objectCode = objectCode;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getPropCode() {
        return propCode;
    }

    public void setPropCode(String propCode) {
        this.propCode = propCode;
    }

}
