package com.iuni.nms.persist.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QAlarmPerson is a Querydsl query type for AlarmPerson
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QAlarmPerson extends EntityPathBase<AlarmPerson> {

    private static final long serialVersionUID = 1521384874;

    public static final QAlarmPerson alarmPerson = new QAlarmPerson("alarmPerson");

    public final QAbstractDomain _super = new QAbstractDomain(this);

    //inherited
    public final NumberPath<Integer> cancelFlag = _super.cancelFlag;

    //inherited
    public final StringPath createBy = _super.createBy;

    //inherited
    public final DatePath<java.util.Date> createDate = _super.createDate;

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath mobile = createString("mobile");

    public final StringPath name = createString("name");

    //inherited
    public final NumberPath<Integer> status = _super.status;

    //inherited
    public final StringPath updateBy = _super.updateBy;

    //inherited
    public final DatePath<java.util.Date> updateDate = _super.updateDate;

    public QAlarmPerson(String variable) {
        super(AlarmPerson.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QAlarmPerson(Path<? extends AlarmPerson> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QAlarmPerson(PathMetadata<?> metadata) {
        super(AlarmPerson.class, metadata);
    }

}

