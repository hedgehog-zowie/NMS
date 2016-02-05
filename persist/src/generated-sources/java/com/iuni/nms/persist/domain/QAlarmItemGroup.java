package com.iuni.nms.persist.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QAlarmItemGroup is a Querydsl query type for AlarmItemGroup
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QAlarmItemGroup extends EntityPathBase<AlarmItemGroup> {

    private static final long serialVersionUID = 65405111;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAlarmItemGroup alarmItemGroup = new QAlarmItemGroup("alarmItemGroup");

    public final QAbstractDomain _super = new QAbstractDomain(this);

    public final QAlarmGroup alarmGroup;

    public final ListPath<AlarmInfo, QAlarmInfo> alarmInfoList = this.<AlarmInfo, QAlarmInfo>createList("alarmInfoList", AlarmInfo.class, QAlarmInfo.class, PathInits.DIRECT2);

    //inherited
    public final NumberPath<Integer> cancelFlag = _super.cancelFlag;

    //inherited
    public final StringPath createBy = _super.createBy;

    //inherited
    public final DatePath<java.util.Date> createDate = _super.createDate;

    public final StringPath desc = createString("desc");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMonitorItem monitorItem;

    //inherited
    public final NumberPath<Integer> status = _super.status;

    //inherited
    public final StringPath updateBy = _super.updateBy;

    //inherited
    public final DatePath<java.util.Date> updateDate = _super.updateDate;

    public QAlarmItemGroup(String variable) {
        this(AlarmItemGroup.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QAlarmItemGroup(Path<? extends AlarmItemGroup> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAlarmItemGroup(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAlarmItemGroup(PathMetadata<?> metadata, PathInits inits) {
        this(AlarmItemGroup.class, metadata, inits);
    }

    public QAlarmItemGroup(Class<? extends AlarmItemGroup> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.alarmGroup = inits.isInitialized("alarmGroup") ? new QAlarmGroup(forProperty("alarmGroup")) : null;
        this.monitorItem = inits.isInitialized("monitorItem") ? new QMonitorItem(forProperty("monitorItem"), inits.get("monitorItem")) : null;
    }

}

