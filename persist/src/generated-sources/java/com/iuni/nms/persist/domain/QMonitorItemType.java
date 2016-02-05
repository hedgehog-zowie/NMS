package com.iuni.nms.persist.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QMonitorItemType is a Querydsl query type for MonitorItemType
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMonitorItemType extends EntityPathBase<MonitorItemType> {

    private static final long serialVersionUID = -1588777205;

    public static final QMonitorItemType monitorItemType = new QMonitorItemType("monitorItemType");

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

    public QMonitorItemType(String variable) {
        super(MonitorItemType.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QMonitorItemType(Path<? extends MonitorItemType> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QMonitorItemType(PathMetadata<?> metadata) {
        super(MonitorItemType.class, metadata);
    }

}

