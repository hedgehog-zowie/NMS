package com.iuni.nms.persist.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QMonitorObjectItem is a Querydsl query type for MonitorObjectItem
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMonitorObjectItem extends EntityPathBase<MonitorObjectItem> {

    private static final long serialVersionUID = 1226522000;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMonitorObjectItem monitorObjectItem = new QMonitorObjectItem("monitorObjectItem");

    public final QAbstractDomain _super = new QAbstractDomain(this);

    //inherited
    public final NumberPath<Integer> cancelFlag = _super.cancelFlag;

    //inherited
    public final StringPath createBy = _super.createBy;

    //inherited
    public final DatePath<java.util.Date> createDate = _super.createDate;

    public final StringPath desc = createString("desc");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMonitorItem monitorItem;

    public final QMonitorObject monitorObject;

    //inherited
    public final NumberPath<Integer> status = _super.status;

    //inherited
    public final StringPath updateBy = _super.updateBy;

    //inherited
    public final DatePath<java.util.Date> updateDate = _super.updateDate;

    public QMonitorObjectItem(String variable) {
        this(MonitorObjectItem.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QMonitorObjectItem(Path<? extends MonitorObjectItem> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMonitorObjectItem(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMonitorObjectItem(PathMetadata<?> metadata, PathInits inits) {
        this(MonitorObjectItem.class, metadata, inits);
    }

    public QMonitorObjectItem(Class<? extends MonitorObjectItem> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.monitorItem = inits.isInitialized("monitorItem") ? new QMonitorItem(forProperty("monitorItem"), inits.get("monitorItem")) : null;
        this.monitorObject = inits.isInitialized("monitorObject") ? new QMonitorObject(forProperty("monitorObject"), inits.get("monitorObject")) : null;
    }

}

