package com.iuni.nms.persist.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QMonitorItem is a Querydsl query type for MonitorItem
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMonitorItem extends EntityPathBase<MonitorItem> {

    private static final long serialVersionUID = -1163350991;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMonitorItem monitorItem = new QMonitorItem("monitorItem");

    public final QAbstractDomain _super = new QAbstractDomain(this);

    public final ListPath<AlarmItemGroup, QAlarmItemGroup> alarmItemGroupList = this.<AlarmItemGroup, QAlarmItemGroup>createList("alarmItemGroupList", AlarmItemGroup.class, QAlarmItemGroup.class, PathInits.DIRECT2);

    //inherited
    public final NumberPath<Integer> cancelFlag = _super.cancelFlag;

    public final StringPath code = createString("code");

    //inherited
    public final StringPath createBy = _super.createBy;

    //inherited
    public final DatePath<java.util.Date> createDate = _super.createDate;

    public final StringPath desc = createString("desc");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<MonitorInfo, QMonitorInfo> monitorInfoList = this.<MonitorInfo, QMonitorInfo>createList("monitorInfoList", MonitorInfo.class, QMonitorInfo.class, PathInits.DIRECT2);

    public final ListPath<MonitorItemProps, QMonitorItemProps> monitorItemPropsList = this.<MonitorItemProps, QMonitorItemProps>createList("monitorItemPropsList", MonitorItemProps.class, QMonitorItemProps.class, PathInits.DIRECT2);

    public final QMonitorItemType monitorItemType;

    public final ListPath<MonitorObjectItem, QMonitorObjectItem> monitorObjectItemList = this.<MonitorObjectItem, QMonitorObjectItem>createList("monitorObjectItemList", MonitorObjectItem.class, QMonitorObjectItem.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    //inherited
    public final NumberPath<Integer> status = _super.status;

    //inherited
    public final StringPath updateBy = _super.updateBy;

    //inherited
    public final DatePath<java.util.Date> updateDate = _super.updateDate;

    public QMonitorItem(String variable) {
        this(MonitorItem.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QMonitorItem(Path<? extends MonitorItem> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMonitorItem(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMonitorItem(PathMetadata<?> metadata, PathInits inits) {
        this(MonitorItem.class, metadata, inits);
    }

    public QMonitorItem(Class<? extends MonitorItem> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.monitorItemType = inits.isInitialized("monitorItemType") ? new QMonitorItemType(forProperty("monitorItemType")) : null;
    }

}

