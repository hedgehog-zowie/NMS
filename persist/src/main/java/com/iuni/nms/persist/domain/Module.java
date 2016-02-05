package com.iuni.nms.persist.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * @author zowie
 *         Email: nicholas@iuni.com
 */
@Entity
@Table(name="MODULE_DEFINE")
public class Module extends AbstractDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "moduleIdGen")
    @TableGenerator(name = "moduleIdGen",
            table = "PK_TABLE", pkColumnName = "PK_KEY ", valueColumnName = "PK_VALUE", pkColumnValue = "moduleId",
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
    @JoinColumn(name = "SYSTEM_ID")
    private System system;

    @OneToMany(mappedBy = "module")
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

    public System getSystem() {
        return system;
    }

    public void setSystem(System system) {
        this.system = system;
    }

    public Set<MonitorObject> getMonitorObjects() {
        return monitorObjects;
    }

    public void setMonitorObjects(Set<MonitorObject> monitorObjects) {
        this.monitorObjects = monitorObjects;
    }
}
