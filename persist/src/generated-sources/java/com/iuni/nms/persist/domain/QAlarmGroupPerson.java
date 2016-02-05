package com.iuni.nms.persist.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QAlarmGroupPerson is a Querydsl query type for AlarmGroupPerson
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QAlarmGroupPerson extends EntityPathBase<AlarmGroupPerson> {

    private static final long serialVersionUID = -670126721;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAlarmGroupPerson alarmGroupPerson = new QAlarmGroupPerson("alarmGroupPerson");

    public final QAbstractDomain _super = new QAbstractDomain(this);

    public final QAlarmGroup alarmGroup;

    public final QAlarmPerson alarmPerson;

    //inherited
    public final NumberPath<Integer> cancelFlag = _super.cancelFlag;

    //inherited
    public final StringPath createBy = _super.createBy;

    //inherited
    public final DatePath<java.util.Date> createDate = _super.createDate;

    public final StringPath desc = createString("desc");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final NumberPath<Integer> status = _super.status;

    //inherited
    public final StringPath updateBy = _super.updateBy;

    //inherited
    public final DatePath<java.util.Date> updateDate = _super.updateDate;

    public QAlarmGroupPerson(String variable) {
        this(AlarmGroupPerson.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QAlarmGroupPerson(Path<? extends AlarmGroupPerson> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAlarmGroupPerson(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAlarmGroupPerson(PathMetadata<?> metadata, PathInits inits) {
        this(AlarmGroupPerson.class, metadata, inits);
    }

    public QAlarmGroupPerson(Class<? extends AlarmGroupPerson> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.alarmGroup = inits.isInitialized("alarmGroup") ? new QAlarmGroup(forProperty("alarmGroup")) : null;
        this.alarmPerson = inits.isInitialized("alarmPerson") ? new QAlarmPerson(forProperty("alarmPerson")) : null;
    }

}

