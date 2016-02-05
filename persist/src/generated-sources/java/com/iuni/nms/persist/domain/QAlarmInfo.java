package com.iuni.nms.persist.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QAlarmInfo is a Querydsl query type for AlarmInfo
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QAlarmInfo extends EntityPathBase<AlarmInfo> {

    private static final long serialVersionUID = -963979197;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAlarmInfo alarmInfo = new QAlarmInfo("alarmInfo");

    public final QAbstractDomain _super = new QAbstractDomain(this);

    public final QAlarmItemGroup alarmItemGroup;

    //inherited
    public final NumberPath<Integer> cancelFlag = _super.cancelFlag;

    //inherited
    public final StringPath createBy = _super.createBy;

    //inherited
    public final DatePath<java.util.Date> createDate = _super.createDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath info = createString("info");

    //inherited
    public final NumberPath<Integer> status = _super.status;

    //inherited
    public final StringPath updateBy = _super.updateBy;

    //inherited
    public final DatePath<java.util.Date> updateDate = _super.updateDate;

    public QAlarmInfo(String variable) {
        this(AlarmInfo.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QAlarmInfo(Path<? extends AlarmInfo> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAlarmInfo(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAlarmInfo(PathMetadata<?> metadata, PathInits inits) {
        this(AlarmInfo.class, metadata, inits);
    }

    public QAlarmInfo(Class<? extends AlarmInfo> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.alarmItemGroup = inits.isInitialized("alarmItemGroup") ? new QAlarmItemGroup(forProperty("alarmItemGroup"), inits.get("alarmItemGroup")) : null;
    }

}

