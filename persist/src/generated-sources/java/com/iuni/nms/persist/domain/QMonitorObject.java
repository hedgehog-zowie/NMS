package com.iuni.nms.persist.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QMonitorObject is a Querydsl query type for MonitorObject
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMonitorObject extends EntityPathBase<MonitorObject> {

    private static final long serialVersionUID = -1133509411;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMonitorObject monitorObject = new QMonitorObject("monitorObject");

    public final QAbstractDomain _super = new QAbstractDomain(this);

    public final NumberPath<Integer> alarmStatus = createNumber("alarmStatus", Integer.class);

    //inherited
    public final NumberPath<Integer> cancelFlag = _super.cancelFlag;

    public final StringPath code = createString("code");

    //inherited
    public final StringPath createBy = _super.createBy;

    //inherited
    public final DatePath<java.util.Date> createDate = _super.createDate;

    public final StringPath desc = createString("desc");

    public final NumberPath<Integer> healthyStatus = createNumber("healthyStatus", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QModule module;

    public final QMonitorObjectType monitorObjectType;

    public final NumberPath<Integer> monitorStatus = createNumber("monitorStatus", Integer.class);

    public final StringPath name = createString("name");

    public final StringPath privateIp = createString("privateIp");

    public final StringPath publicIp = createString("publicIp");

    //inherited
    public final NumberPath<Integer> status = _super.status;

    //inherited
    public final StringPath updateBy = _super.updateBy;

    //inherited
    public final DatePath<java.util.Date> updateDate = _super.updateDate;

    public QMonitorObject(String variable) {
        this(MonitorObject.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QMonitorObject(Path<? extends MonitorObject> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMonitorObject(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMonitorObject(PathMetadata<?> metadata, PathInits inits) {
        this(MonitorObject.class, metadata, inits);
    }

    public QMonitorObject(Class<? extends MonitorObject> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.module = inits.isInitialized("module") ? new QModule(forProperty("module"), inits.get("module")) : null;
        this.monitorObjectType = inits.isInitialized("monitorObjectType") ? new QMonitorObjectType(forProperty("monitorObjectType")) : null;
    }

}

