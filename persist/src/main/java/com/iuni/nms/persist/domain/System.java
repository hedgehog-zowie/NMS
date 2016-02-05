package com.iuni.nms.persist.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * @author zowie
 *         Email: nicholas@iuni.com
 */
@Entity
@Table(name="SYSTEM_DEFINE")
public class System extends AbstractDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "systemIdGen")
    @TableGenerator(name = "systemIdGen",
            table = "PK_TABLE", pkColumnName = "PK_KEY ", valueColumnName = "PK_VALUE", pkColumnValue = "systemId",
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

    @OneToMany(mappedBy = "system")
    private Set<Module> modules;

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

    public Set<Module> getModules() {
        return modules;
    }

    public void setModules(Set<Module> modules) {
        this.modules = modules;
    }

}
