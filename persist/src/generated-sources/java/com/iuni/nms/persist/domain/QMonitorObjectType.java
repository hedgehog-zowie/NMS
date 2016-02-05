package com.iuni.nms.persist.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QMonitorObjectType is a Querydsl query type for MonitorObjectType
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMonitorObjectType extends EntityPathBase<MonitorObjectType> {

    private static final long serialVersionUID = 1226854839;

    public static final QMonitorObjectType monitorObjectType = new QMonitorObjectType("monitorObjectType");

    public final QAbstractDomain _super = new QAbstractDomain(this);

    //inherited
    public final NumberPath<Integer> cancelFlag = _super.cancelFlag;

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

    //inherited
    public final StringPath updateBy = _super.updateBy;

    //inherited
    public final DatePath<java.util.Date> updateDate = _super.updateDate;

    public QMonitorObjectType(String variable) {
        super(MonitorObjectType.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QMonitorObjectType(Path<? extends MonitorObjectType> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QMonitorObjectType(PathMetadata<?> metadata) {
        super(MonitorObjectType.class, metadata);
    }

}

