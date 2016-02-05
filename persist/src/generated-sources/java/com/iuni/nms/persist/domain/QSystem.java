package com.iuni.nms.persist.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSystem is a Querydsl query type for System
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSystem extends EntityPathBase<System> {

    private static final long serialVersionUID = -2061312245;

    public static final QSystem system = new QSystem("system");

    public final QAbstractDomain _super = new QAbstractDomain(this);

    //inherited
    public final NumberPath<Integer> cancelFlag = _super.cancelFlag;

    public final StringPath code = createString("code");

    //inherited
    public final StringPath createBy = _super.createBy;

    //inherited
    public final DatePath<java.util.Date> createDate = _super.createDate;

    public final StringPath desc = createString("desc");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<Module, QModule> moduleList = this.<Module, QModule>createList("moduleList", Module.class, QModule.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    //inherited
    public final NumberPath<Integer> status = _super.status;

    //inherited
    public final StringPath updateBy = _super.updateBy;

    //inherited
    public final DatePath<java.util.Date> updateDate = _super.updateDate;

    public QSystem(String variable) {
        super(System.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QSystem(Path<? extends System> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QSystem(PathMetadata<?> metadata) {
        super(System.class, metadata);
    }

}

