package com.iuni.nms.persist.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QMonitorItemProps is a Querydsl query type for MonitorItemProps
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMonitorItemProps extends EntityPathBase<MonitorItemProps> {

    private static final long serialVersionUID = -2011356225;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMonitorItemProps monitorItemProps = new QMonitorItemProps("monitorItemProps");

    public final QAbstractDomain _super = new QAbstractDomain(this);

    public final StringPath alarmTemplate = createString("alarmTemplate");

    //inherited
    public final NumberPath<Integer> cancelFlag = _super.cancelFlag;

    public final StringPath code = createString("code");

    //inherited
    public final StringPath createBy = _super.createBy;

    //inherited
    public final DatePath<java.util.Date> createDate = _super.createDate;

    public final StringPath desc = createString("desc");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath level = createString("level");

    public final QMonitorItem monitorItem;

    public final StringPath name = createString("name");

    //inherited
    public final NumberPath<Integer> status = _super.status;

    //inherited
    public final StringPath updateBy = _super.updateBy;

    //inherited
    public final DatePath<java.util.Date> updateDate = _super.updateDate;

    public QMonitorItemProps(String variable) {
        this(MonitorItemProps.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QMonitorItemProps(Path<? extends MonitorItemProps> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMonitorItemProps(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMonitorItemProps(PathMetadata<?> metadata, PathInits inits) {
        this(MonitorItemProps.class, metadata, inits);
    }

    public QMonitorItemProps(Class<? extends MonitorItemProps> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.monitorItem = inits.isInitialized("monitorItem") ? new QMonitorItem(forProperty("monitorItem"), inits.get("monitorItem")) : null;
    }

}

