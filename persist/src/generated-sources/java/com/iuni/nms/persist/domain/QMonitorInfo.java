package com.iuni.nms.persist.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QMonitorInfo is a Querydsl query type for MonitorInfo
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMonitorInfo extends EntityPathBase<MonitorInfo> {

    private static final long serialVersionUID = -1163356724;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMonitorInfo monitorInfo = new QMonitorInfo("monitorInfo");

    public final QAbstractDomain _super = new QAbstractDomain(this);

    //inherited
    public final NumberPath<Integer> cancelFlag = _super.cancelFlag;

    //inherited
    public final StringPath createBy = _super.createBy;

    //inherited
    public final DatePath<java.util.Date> createDate = _super.createDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath message = createString("message");

    public final QMonitorItem monitorItem;

    public final QMonitorItemProps monitorItemProps;

    public final QMonitorObject monitorObject;

    //inherited
    public final NumberPath<Integer> status = _super.status;

    //inherited
    public final StringPath updateBy = _super.updateBy;

    //inherited
    public final DatePath<java.util.Date> updateDate = _super.updateDate;

    public QMonitorInfo(String variable) {
        this(MonitorInfo.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QMonitorInfo(Path<? extends MonitorInfo> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMonitorInfo(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMonitorInfo(PathMetadata<?> metadata, PathInits inits) {
        this(MonitorInfo.class, metadata, inits);
    }

    public QMonitorInfo(Class<? extends MonitorInfo> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.monitorItem = inits.isInitialized("monitorItem") ? new QMonitorItem(forProperty("monitorItem"), inits.get("monitorItem")) : null;
        this.monitorItemProps = inits.isInitialized("monitorItemProps") ? new QMonitorItemProps(forProperty("monitorItemProps"), inits.get("monitorItemProps")) : null;
        this.monitorObject = inits.isInitialized("monitorObject") ? new QMonitorObject(forProperty("monitorObject"), inits.get("monitorObject")) : null;
    }

}

