package com.iuni.nms.persist.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QAbstractDomain is a Querydsl query type for AbstractDomain
 */
@Generated("com.mysema.query.codegen.SupertypeSerializer")
public class QAbstractDomain extends EntityPathBase<AbstractDomain> {

    private static final long serialVersionUID = 44851298;

    public static final QAbstractDomain abstractDomain = new QAbstractDomain("abstractDomain");

    public final NumberPath<Integer> cancelFlag = createNumber("cancelFlag", Integer.class);

    public final StringPath createBy = createString("createBy");

    public final DatePath<java.util.Date> createDate = createDate("createDate", java.util.Date.class);

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final StringPath updateBy = createString("updateBy");

    public final DatePath<java.util.Date> updateDate = createDate("updateDate", java.util.Date.class);

    public QAbstractDomain(String variable) {
        super(AbstractDomain.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QAbstractDomain(Path<? extends AbstractDomain> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QAbstractDomain(PathMetadata<?> metadata) {
        super(AbstractDomain.class, metadata);
    }

}

