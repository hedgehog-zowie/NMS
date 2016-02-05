package com.iuni.nms.persist.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QAlarmGroup is a Querydsl query type for AlarmGroup
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QAlarmGroup extends EntityPathBase<AlarmGroup> {

    private static final long serialVersionUID = 179697034;

    public static final QAlarmGroup alarmGroup = new QAlarmGroup("alarmGroup");

    public final QAbstractDomain _super = new QAbstractDomain(this);

    public final ListPath<AlarmGroupPerson, QAlarmGroupPerson> alarmGroupPersonList = this.<AlarmGroupPerson, QAlarmGroupPerson>createList("alarmGroupPersonList", AlarmGroupPerson.class, QAlarmGroupPerson.class, PathInits.DIRECT2);

    public final ListPath<AlarmItemGroup, QAlarmItemGroup> alarmItemGroupList = this.<AlarmItemGroup, QAlarmItemGroup>createList("alarmItemGroupList", AlarmItemGroup.class, QAlarmItemGroup.class, PathInits.DIRECT2);

    //inherited
    public final NumberPath<Integer> cancelFlag = _super.cancelFlag;

    //inherited
    public final StringPath createBy = _super.createBy;

    //inherited
    public final DatePath<java.util.Date> createDate = _super.createDate;

    public final StringPath desc = createString("desc");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    //inherited
    public final NumberPath<Integer> status = _super.status;

    //inherited
    public final StringPath updateBy = _super.updateBy;

    //inherited
    public final DatePath<java.util.Date> updateDate = _super.updateDate;

    public QAlarmGroup(String variable) {
        super(AlarmGroup.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QAlarmGroup(Path<? extends AlarmGroup> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QAlarmGroup(PathMetadata<?> metadata) {
        super(AlarmGroup.class, metadata);
    }

}

