package com.iuni.nms.persist.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QModule is a Querydsl query type for Module
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QModule extends EntityPathBase<Module> {

    private static final long serialVersionUID = 2052199240;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QModule module = new QModule("module");

    public final QAbstractDomain _super = new QAbstractDomain(this);

    //inherited
    public final NumberPath<Integer> cancelFlag = _super.cancelFlag;

    public final StringPath code = createString("code");

    //inherited
    public final StringPath createBy = _super.createBy;

    //inherited
    public final DatePath<java.util.Date> createDate = _super.createDate;

    public final StringPath desc = createString("desc");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<MonitorObject, QMonitorObject> monitorObjectList = this.<MonitorObject, QMonitorObject>createList("monitorObjectList", MonitorObject.class, QMonitorObject.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    //inherited
    public final NumberPath<Integer> status = _super.status;

    public final QSystem system;

    //inherited
    public final StringPath updateBy = _super.updateBy;

    //inherited
    public final DatePath<java.util.Date> updateDate = _super.updateDate;

    public QModule(String variable) {
        this(Module.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QModule(Path<? extends Module> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QModule(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QModule(PathMetadata<?> metadata, PathInits inits) {
        this(Module.class, metadata, inits);
    }

    public QModule(Class<? extends Module> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.system = inits.isInitialized("system") ? new QSystem(forProperty("system")) : null;
    }

}

