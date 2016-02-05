/*==============================================================*/
/* Table: PK_TABLE                                              */
/*==============================================================*/
create table PK_TABLE
(
  PK_KEY               VARCHAR2(64),
  PK_VALUE             NUMBER
);

comment on table PK_TABLE is
'主键表';

/*==============================================================*/
/* Table: ALARM_GROUP                                           */
/*==============================================================*/
create table ALARM_GROUP
(
  ID                   INTEGER              not null,
  NAME                 VARCHAR2(16)         not null,
  "DESC"               VARCHAR2(1000),
  STATUS               INTEGER              default 1 not null
    constraint CKC_ALARM_GROUP_STATUS check (STATUS in (0,1)),
  CANCEL_FLAG          INTEGER              default 0 not null
    constraint CKC_ALARM_GROUP_CANCEL_FLAG check (CANCEL_FLAG in (0,1)),
  CREATE_BY            VARCHAR2(128)        not null,
  CREATE_DATE          DATE                 not null,
  UPDATE_BY            VARCHAR2(128)        not null,
  UPDATE_DATE          DATE                 not null,
  constraint PK_ALARM_GROUP primary key (ID)
);

comment on table ALARM_GROUP is
'告警组';

comment on column ALARM_GROUP.STATUS is
'是否有效标识，0表示无效，1表示有效。';

comment on column ALARM_GROUP.CANCEL_FLAG is
'逻辑删除标识，0表示未删除，1表示删除。';

comment on column ALARM_GROUP.CREATE_BY is
'创建人';

comment on column ALARM_GROUP.CREATE_DATE is
'创建时间';

comment on column ALARM_GROUP.UPDATE_BY is
'修改人';

comment on column ALARM_GROUP.UPDATE_DATE is
'修改时间';

/*==============================================================*/
/* Table: ALARM_GROUP_PERSON                                    */
/*==============================================================*/
create table ALARM_GROUP_PERSON
(
  ID                   INTEGER              not null,
  GROUP_ID             INTEGER              not null,
  PERSON_ID            INTEGER              not null,
  "DESC"               VARCHAR2(1000),
  STATUS               INTEGER              default 1 not null
    constraint CKC_GROUP_PERSON_STATUS check (STATUS in (0,1)),
  CANCEL_FLAG          INTEGER              default 0 not null
    constraint CKC_GROUP_PERSON_CANCEL_FLAG check (CANCEL_FLAG in (0,1)),
  CREATE_BY            VARCHAR2(128)        not null,
  CREATE_DATE          DATE                 not null,
  UPDATE_BY            VARCHAR2(128)        not null,
  UPDATE_DATE          DATE                 not null,
  constraint PK_ALARM_GROUP_PERSON primary key (ID)
);

comment on table ALARM_GROUP_PERSON is
'告警组与告警人关系表';

comment on column ALARM_GROUP_PERSON.STATUS is
'是否有效标识，0表示无效，1表示有效。';

comment on column ALARM_GROUP_PERSON.CANCEL_FLAG is
'逻辑删除标识，0表示未删除，1表示删除。';

comment on column ALARM_GROUP_PERSON.CREATE_BY is
'创建人';

comment on column ALARM_GROUP_PERSON.CREATE_DATE is
'创建时间';

comment on column ALARM_GROUP_PERSON.UPDATE_BY is
'修改人';

comment on column ALARM_GROUP_PERSON.UPDATE_DATE is
'修改时间';

/*==============================================================*/
/* Table: ALARM_INFO                                            */
/*==============================================================*/
create table ALARM_INFO
(
  ID                   INTEGER              not null,
  ITEM_GROUP_ID        INTEGER              not null,
  INFO                 VARCHAR2(1000)                 not null,
  STATUS               INTEGER              default 1 not null
    constraint CKC_ALARM_INFO_STATUS check (STATUS in (0,1)),
  CANCEL_FLAG          INTEGER              default 0 not null
    constraint CKC_ALARM_INFO_CANCEL_FLAG check (CANCEL_FLAG in (0,1)),
  CREATE_BY            VARCHAR2(128)        not null,
  CREATE_DATE          DATE                 not null,
  UPDATE_BY            VARCHAR2(128)        not null,
  UPDATE_DATE          DATE                 not null,
  constraint PK_ALARM_INFO primary key (ID)
);

comment on table ALARM_INFO is
'告警信息表';

comment on column ALARM_INFO.STATUS is
'是否有效标识，0表示无效，1表示有效。';

comment on column ALARM_INFO.CANCEL_FLAG is
'逻辑删除标识，0表示未删除，1表示删除。';

comment on column ALARM_INFO.CREATE_BY is
'创建人';

comment on column ALARM_INFO.CREATE_DATE is
'创建时间';

comment on column ALARM_INFO.UPDATE_BY is
'修改人';

comment on column ALARM_INFO.UPDATE_DATE is
'修改时间';

/*==============================================================*/
/* Table: ALARM_ITEM_GROUP                                      */
/*==============================================================*/
create table ALARM_ITEM_GROUP
(
  ID                   INTEGER              not null,
  ITEM_ID              INTEGER              not null,
  GROUP_ID             INTEGER              not null,
  "DESC"               VARCHAR2(1000),
  STATUS               INTEGER              default 1 not null
    constraint CKC_ITEM_GROUP_STATUS check (STATUS in (0,1)),
  CANCEL_FLAG          INTEGER              default 0 not null
    constraint CKC_ITEM_GROUP_CANCEL_FLAG check (CANCEL_FLAG in (0,1)),
  CREATE_BY            VARCHAR2(128)        not null,
  CREATE_DATE          DATE                 not null,
  UPDATE_BY            VARCHAR2(128)        not null,
  UPDATE_DATE          DATE                 not null,
  constraint PK_ALARM_ITEM_GROUP primary key (ID)
);

comment on table ALARM_ITEM_GROUP is
'监控项与告警组关系表';

comment on column ALARM_ITEM_GROUP.STATUS is
'是否有效标识，0表示无效，1表示有效。';

comment on column ALARM_ITEM_GROUP.CANCEL_FLAG is
'逻辑删除标识，0表示未删除，1表示删除。';

comment on column ALARM_ITEM_GROUP.CREATE_BY is
'创建人';

comment on column ALARM_ITEM_GROUP.CREATE_DATE is
'创建时间';

comment on column ALARM_ITEM_GROUP.UPDATE_BY is
'修改人';

comment on column ALARM_ITEM_GROUP.UPDATE_DATE is
'修改时间';

/*==============================================================*/
/* Table: ALARM_PERSON                                          */
/*==============================================================*/
create table ALARM_PERSON
(
  ID                   INTEGER              not null,
  NAME                 VARCHAR2(16)         not null,
  MOBILE               VARCHAR2(11)         not null,
  EMAIL                VARCHAR2(64)         not null,
  STATUS               INTEGER              default 1 not null
    constraint CKC_ALARM_PERSON_STATUS check (STATUS in (0,1)),
  CANCEL_FLAG          INTEGER              default 0 not null
    constraint CKC_ALARM_PERSON_CANCEL_FLAG check (CANCEL_FLAG in (0,1)),
  CREATE_BY            VARCHAR2(128)        not null,
  CREATE_DATE          DATE                 not null,
  UPDATE_BY            VARCHAR2(128)        not null,
  UPDATE_DATE          DATE                 not null,
  constraint PK_ALARM_PERSON primary key (ID)
);

comment on table ALARM_PERSON is
'告警人定义表';

comment on column ALARM_PERSON.NAME is
'姓名';

comment on column ALARM_PERSON.MOBILE is
'手机';

comment on column ALARM_PERSON.EMAIL is
'邮箱';

comment on column ALARM_PERSON.STATUS is
'是否有效标识，0表示无效，1表示有效。';

comment on column ALARM_PERSON.CANCEL_FLAG is
'逻辑删除标识，0表示未删除，1表示删除。';

comment on column ALARM_PERSON.CREATE_BY is
'创建人';

comment on column ALARM_PERSON.CREATE_DATE is
'创建时间';

comment on column ALARM_PERSON.UPDATE_BY is
'修改人';

comment on column ALARM_PERSON.UPDATE_DATE is
'修改时间';

/*==============================================================*/
/* Table: MODULE_DEFINE                                         */
/*==============================================================*/
create table MODULE_DEFINE
(
  ID                   INTEGER              not null,
  NAME                 VARCHAR2(16)         not null,
  CODE                 VARCHAR2(16)         not null,
  SYSTEM_ID            INTEGER              not null,
  "DESC"               VARCHAR2(1000),
  STATUS               INTEGER              default 1 not null
    constraint CKC_MODULE_DEFINE_STATUS check (STATUS in (0,1)),
  CANCEL_FLAG          INTEGER              default 0 not null
    constraint CKC_MODULE_DEFINE_CANCEL_FLAG check (CANCEL_FLAG in (0,1)),
  CREATE_BY            VARCHAR2(128)        not null,
  CREATE_DATE          DATE                 not null,
  UPDATE_BY            VARCHAR2(128)        not null,
  UPDATE_DATE          DATE                 not null,
  constraint PK_MODULE_DEFINE primary key (ID)
);

comment on table MODULE_DEFINE is
'模块定义表';

comment on column MODULE_DEFINE.NAME is
'模块名称';

comment on column MODULE_DEFINE.CODE is
'系统编码';

comment on column MODULE_DEFINE."DESC" is
'描述';

comment on column MODULE_DEFINE.STATUS is
'是否有效标识，0表示无效，1表示有效。';

comment on column MODULE_DEFINE.CANCEL_FLAG is
'逻辑删除标识，0表示未删除，1表示删除。';

comment on column MODULE_DEFINE.CREATE_BY is
'创建人';

comment on column MODULE_DEFINE.CREATE_DATE is
'创建时间';

comment on column MODULE_DEFINE.UPDATE_BY is
'修改人';

comment on column MODULE_DEFINE.UPDATE_DATE is
'修改时间';

/*==============================================================*/
/* Table: MONITOR_INFO                                          */
/*==============================================================*/
create table MONITOR_INFO
(
  ID                   INTEGER              not null,
  OBJECT               VARCHAR2(16)         not null,
  ITEM                 VARCHAR2(16)         not null,
  PROP                 VARCHAR2(16),
  MESSAGE              VARCHAR2(1000)       not null,
  STATUS               INTEGER              not null
    constraint CKC_MONITOR_INFO_STATUS check (STATUS in (0,1)),
  CANCEL_FLAG          INTEGER              not null
    constraint CKC_MONITOR_INFO_CANCEL_FLAG check (CANCEL_FLAG in (0,1)),
  CREATE_BY            VARCHAR2(128)        not null,
  CREATE_DATE          DATE                 not null,
  UPDATE_BY            VARCHAR2(128)        not null,
  UPDATE_DATE          DATE                 not null,
  constraint PK_MONITOR_INFO primary key (ID)
);

comment on table MONITOR_INFO is
'监控信息表';

comment on column MONITOR_INFO.STATUS is
'是否有效标识，0表示无效，1表示有效。';

comment on column MONITOR_INFO.CANCEL_FLAG is
'逻辑删除标识，0表示未删除，1表示删除。';

comment on column MONITOR_INFO.CREATE_BY is
'创建人';

comment on column MONITOR_INFO.CREATE_DATE is
'创建时间';

comment on column MONITOR_INFO.UPDATE_BY is
'修改人';

comment on column MONITOR_INFO.UPDATE_DATE is
'修改时间';

/*==============================================================*/
/* Table: MONITOR_ITEM                                          */
/*==============================================================*/
create table MONITOR_ITEM
(
  ID                   INTEGER              not null,
  CODE                 VARCHAR2(16)         not null,
  NAME                 VARCHAR2(16)         not null,
  "DESC"               VARCHAR2(1000),
  ITEM_TYPE            INTEGER              not null,
  STATUS               CHAR(10)
    constraint CKC_MONITOR_ITEM_STATUS check (STATUS is null or (STATUS in ('0','1'))),
  CANCEL_FLAG          INTEGER              default 0 not null
    constraint CKC_MONITOR_ITEM_CANCEL_FLAG check (CANCEL_FLAG in (0,1)),
  CREATE_BY            VARCHAR2(128)        not null,
  CREATE_DATE          DATE                 not null,
  UPDATE_BY            VARCHAR2(128)        not null,
  UPDATE_DATE          DATE                 not null,
  constraint PK_MONITOR_ITEM primary key (ID),
  constraint AK_UQ_MONITOR_ITEM_CODE unique (CODE)
);

comment on table MONITOR_ITEM is
'监控项定义表';

comment on column MONITOR_ITEM.NAME is
'监控项名称';

comment on column MONITOR_ITEM."DESC" is
'描述';

comment on column MONITOR_ITEM.ITEM_TYPE is
'监控对象类型（如：服务器、网络、数据库、应用程序等）';

comment on column MONITOR_ITEM.STATUS is
'是否有效标识，0表示无效，1表示有效。';

comment on column MONITOR_ITEM.CANCEL_FLAG is
'逻辑删除标识，0表示未删除，1表示删除。';

comment on column MONITOR_ITEM.CREATE_BY is
'创建人';

comment on column MONITOR_ITEM.CREATE_DATE is
'创建时间';

comment on column MONITOR_ITEM.UPDATE_BY is
'修改人';

comment on column MONITOR_ITEM.UPDATE_DATE is
'修改时间';

/*==============================================================*/
/* Table: MONITOR_ITEM_PROPS                                    */
/*==============================================================*/
create table MONITOR_ITEM_PROPS
(
  ID                   INTEGER              not null,
  CODE                 VARCHAR2(16)         not null,
  NAME                 VARCHAR2(16)         not null,
  "LEVEL"              VARCHAR2(16),
  ALARM_TEMPLATE       VARCHAR2(1000),
  ITEM_ID              INTEGER              not null,
  "DESC"               VARCHAR2(1000),
  STATUS               INTEGER
    constraint CKC_MONITOR_ITEM_PROPS_STATUS check (STATUS is null or (STATUS in (0,1))),
  CANCEL_FLAG          INTEGER              default 0 not null
    constraint CKC_MONITOR_ITEM_PROPS_CANCEL check (CANCEL_FLAG in (0,1)),
  CREATE_BY            VARCHAR2(128)        not null,
  CREATE_DATE          DATE                 not null,
  UPDATE_BY            VARCHAR2(128)        not null,
  UPDATE_DATE          DATE                 not null,
  constraint PK_MONITOR_ITEM_PROPS primary key (ID),
  constraint AK_UQ_MONITOR_ITEM_PROPS_CODE unique (CODE)
);

comment on table MONITOR_ITEM_PROPS is
'监控项属性表，包括异常代码及告警模板';

comment on column MONITOR_ITEM_PROPS.CODE is
'代码，000000为正常代码，其他为异常';

comment on column MONITOR_ITEM_PROPS.NAME is
'监控项属性名称';

comment on column MONITOR_ITEM_PROPS."LEVEL" is
'异常级别，包括（info,warning,error）';

comment on column MONITOR_ITEM_PROPS.ALARM_TEMPLATE is
'告警模板，其中参数用%s表示';

comment on column MONITOR_ITEM_PROPS.ITEM_ID is
'所属监控项';

comment on column MONITOR_ITEM_PROPS."DESC" is
'描述';

comment on column MONITOR_ITEM_PROPS.ITEM_TYPE is
'监控对象类型（如：服务器、网络、数据库、应用程序等）';

comment on column MONITOR_ITEM_PROPS.STATUS is
'是否有效标识，0表示无效，1表示有效。';

comment on column MONITOR_ITEM_PROPS.CANCEL_FLAG is
'逻辑删除标识，0表示未删除，1表示删除。';

comment on column MONITOR_ITEM_PROPS.CREATE_BY is
'创建人';

comment on column MONITOR_ITEM_PROPS.CREATE_DATE is
'创建时间';

comment on column MONITOR_ITEM_PROPS.UPDATE_BY is
'修改人';

comment on column MONITOR_ITEM_PROPS.UPDATE_DATE is
'修改时间';

/*==============================================================*/
/* Table: MONITOR_ITEM_TYPE                                     */
/*==============================================================*/
create table MONITOR_ITEM_TYPE
(
  ID                   INTEGER              not null,
  NAME                 VARCHAR2(16)         not null,
  "DESC"               VARCHAR2(1000),
  STATUS               INTEGER              default 1 not null
    constraint CKC_MONITOR_ITEM_TYPE_STATUS check (STATUS in (0,1)),
  CANCEL_FLAG          INTEGER              default 0 not null
    constraint CKC_MONITOR_ITEM_TYPE_CANCEL check (CANCEL_FLAG in (0,1)),
  CREATE_BY            VARCHAR2(128)        not null,
  CREATE_DATE          DATE                 not null,
  UPDATE_BY            VARCHAR2(128)        not null,
  UPDATE_DATE          DATE                 not null,
  constraint PK_MONITOR_ITEM_TYPE primary key (ID)
);

comment on table MONITOR_ITEM_TYPE is
'监控项类型定义表';

comment on column MONITOR_ITEM_TYPE.NAME is
'类型名称（如：业务监控、系统监控等）';

comment on column MONITOR_ITEM_TYPE."DESC" is
'描述';

comment on column MONITOR_ITEM_TYPE.STATUS is
'是否有效标识，0表示无效，1表示有效。';

comment on column MONITOR_ITEM_TYPE.CANCEL_FLAG is
'逻辑删除标识，0表示未删除，1表示删除。';

comment on column MONITOR_ITEM_TYPE.CREATE_BY is
'创建人';

comment on column MONITOR_ITEM_TYPE.CREATE_DATE is
'创建时间';

comment on column MONITOR_ITEM_TYPE.UPDATE_BY is
'修改人';

comment on column MONITOR_ITEM_TYPE.UPDATE_DATE is
'修改时间';

/*==============================================================*/
/* Table: MONITOR_OBJECT                                        */
/*==============================================================*/
create table MONITOR_OBJECT
(
  ID                   INTEGER              not null,
  CODE                 VARCHAR2(16)         not null,
  NAME                 VARCHAR2(16)         not null,
  MODULE_ID            INTEGER              not null,
  "DESC"               VARCHAR2(1000),
  PUBLICIP             VARCHAR2(18),
  PRIVATEIP            VARCHAR2(18)         not null,
  OBJECT_TYPE          INTEGER              not null,
  MONITOR_STATUS       INTEGER              not null,
  ALARM_STATUS         INTEGER              not null,
  HEALTHY_STATUS       INTEGER              default 1 not null
    constraint CKC_MONITOR_OBJECT_HEALTHY check (HEALTHY_STATUS in (0,1)),
  STATUS               INTEGER              not null
    constraint CKC_MONITOR_OBJECT_STATUS check (STATUS in (0,1)),
  CANCEL_FLAG          INTEGER              default 0 not null
    constraint CKC_MONITOR_OBJECT_CANCEL_FLAG check (CANCEL_FLAG in (0,1)),
  CREATE_BY            VARCHAR2(128)        not null,
  CREATE_DATE          DATE                 not null,
  UPDATE_BY            VARCHAR2(128)        not null,
  UPDATE_DATE          DATE                 not null,
  constraint PK_MONITOR_OBJECT primary key (ID),
  constraint AK_UQ_MONITOR_OBJECT_CODE unique (CODE)
);

comment on table MONITOR_OBJECT is
'监控对象定义表';

comment on column MONITOR_OBJECT.CODE is
'监控对象CODE';

comment on column MONITOR_OBJECT.NAME is
'监控对象名称';

comment on column MONITOR_OBJECT.MODULE_ID is
'模块ID';

comment on column MONITOR_OBJECT."DESC" is
'描述';

comment on column MONITOR_OBJECT.PUBLICIP is
'外网IP';

comment on column MONITOR_OBJECT.PRIVATEIP is
'内网IP';

comment on column MONITOR_OBJECT.OBJECT_TYPE is
'监控对象类型';

comment on column MONITOR_OBJECT.MONITOR_STATUS is
'监控开关，0不监控，1监控';

comment on column MONITOR_OBJECT.ALARM_STATUS is
'告警开关，0不告警，1告警';

comment on column MONITOR_OBJECT.HEALTHY_STATUS is
'监控对象健康标识，0表示健康，1表示不健康。';

comment on column MONITOR_OBJECT.STATUS is
'是否有效标识，0表示无效，1表示有效。';

comment on column MONITOR_OBJECT.CANCEL_FLAG is
'逻辑删除标识，0表示未删除，1表示删除。';

comment on column MONITOR_OBJECT.CREATE_BY is
'创建人';

comment on column MONITOR_OBJECT.CREATE_DATE is
'创建时间';

comment on column MONITOR_OBJECT.UPDATE_BY is
'修改人';

comment on column MONITOR_OBJECT.UPDATE_DATE is
'修改时间';

/*==============================================================*/
/* Table: MONITOR_OBJECT_ITEM                                   */
/*==============================================================*/
create table MONITOR_OBJECT_ITEM
(
  ID                   INTEGER              not null,
  OBJECT_ID            INTEGER              not null,
  ITEM_ID              INTEGER              not null,
  "DESC"               VARCHAR2(1000),
  STATUS               INTEGER              default 1 not null
    constraint CKC_MONITOR_OBJECT_ITEM_STATUS check (STATUS in (0,1)),
  CANCEL_FLAG          INTEGER              default 0 not null
    constraint CKC_MONITOR_OBJECT_ITEM_CANCEL check (CANCEL_FLAG in (0,1)),
  CREATE_BY            VARCHAR2(128)        not null,
  CREATE_DATE          DATE                 not null,
  UPDATE_BY            VARCHAR2(128)        not null,
  UPDATE_DATE          DATE                 not null,
  constraint PK_MONITOR_OBJECT_ITEM primary key (ID)
);

comment on table MONITOR_OBJECT_ITEM is
'监控对象与监控项关系表';

comment on column MONITOR_OBJECT_ITEM.OBJECT_ID is
'监控对象ID';

comment on column MONITOR_OBJECT_ITEM.ITEM_ID is
'监控项ID';

comment on column MONITOR_OBJECT_ITEM.STATUS is
'是否有效标识，0表示无效，1表示有效。';

comment on column MONITOR_OBJECT_ITEM.CANCEL_FLAG is
'逻辑删除标识，0表示未删除，1表示删除。';

comment on column MONITOR_OBJECT_ITEM.CREATE_BY is
'创建人';

comment on column MONITOR_OBJECT_ITEM.CREATE_DATE is
'创建时间';

comment on column MONITOR_OBJECT_ITEM.UPDATE_BY is
'修改人';

comment on column MONITOR_OBJECT_ITEM.UPDATE_DATE is
'修改时间';

/*==============================================================*/
/* Table: MONITOR_OBJECT_TYPE                                   */
/*==============================================================*/
create table MONITOR_OBJECT_TYPE
(
  ID                   INTEGER              not null,
  NAME                 VARCHAR2(16)         not null,
  "DESC"               VARCHAR2(1000),
  STATUS               INTEGER              default 1 not null
    constraint CKC_MONITOR_OBJECT_TYPE_STATUS check (STATUS in (0,1)),
  CANCEL_FLAG          INTEGER              default 0 not null
    constraint CKC_MONITOR_OBJECT_TYPE_CANCEL check (CANCEL_FLAG in (0,1)),
  CREATE_BY            VARCHAR2(128)        not null,
  CREATE_DATE          DATE                 not null,
  UPDATE_BY            VARCHAR2(128)        not null,
  UPDATE_DATE          DATE                 not null,
  constraint PK_MONITOR_OBJECT_TYPE primary key (ID)
);

comment on table MONITOR_OBJECT_TYPE is
'监控对象类型定义表';

comment on column MONITOR_OBJECT_TYPE.NAME is
'类型名称（如：服务器、网络、数据库、应用程序等）';

comment on column MONITOR_OBJECT_TYPE."DESC" is
'描述';

comment on column MONITOR_OBJECT_TYPE.STATUS is
'是否有效标识，0表示无效，1表示有效。';

comment on column MONITOR_OBJECT_TYPE.CANCEL_FLAG is
'逻辑删除标识，0表示未删除，1表示删除。';

comment on column MONITOR_OBJECT_TYPE.CREATE_BY is
'创建人';

comment on column MONITOR_OBJECT_TYPE.CREATE_DATE is
'创建时间';

comment on column MONITOR_OBJECT_TYPE.UPDATE_BY is
'修改人';

comment on column MONITOR_OBJECT_TYPE.UPDATE_DATE is
'修改时间';

/*==============================================================*/
/* Table: SYSTEM_DEFINE                                         */
/*==============================================================*/
create table SYSTEM_DEFINE
(
  ID                   INTEGER              not null,
  NAME                 VARCHAR2(16)         not null,
  CODE                 VARCHAR2(16)         not null,
  "DESC"               VARCHAR2(1000),
  STATUS               INTEGER              default 1 not null
    constraint CKC_SYSTEM_DEFINE_STATUS check (STATUS in (0,1)),
  CANCEL_FLAG          INTEGER              default 0 not null
    constraint CKC_SYSTEM_DEFINE_CANCEL_FLAG check (CANCEL_FLAG in (0,1)),
  CREATE_BY            VARCHAR2(128)        not null,
  CREATE_DATE          DATE                 not null,
  UPDATE_BY            VARCHAR2(128)        not null,
  UPDATE_DATE          DATE                 not null,
  constraint PK_SYSTEM_DEFINE primary key (ID)
);

comment on table SYSTEM_DEFINE is
'系统定义表';

comment on column SYSTEM_DEFINE.NAME is
'系统名称';

comment on column SYSTEM_DEFINE.CODE is
'系统编码';

comment on column SYSTEM_DEFINE."DESC" is
'描述';

comment on column SYSTEM_DEFINE.STATUS is
'是否有效标识，0表示无效，1表示有效。';

comment on column SYSTEM_DEFINE.CANCEL_FLAG is
'逻辑删除标识，0表示未删除，1表示删除。';

comment on column SYSTEM_DEFINE.CREATE_BY is
'创建人';

comment on column SYSTEM_DEFINE.CREATE_DATE is
'创建时间';

comment on column SYSTEM_DEFINE.UPDATE_BY is
'修改人';

comment on column SYSTEM_DEFINE.UPDATE_DATE is
'修改时间';

alter table ALARM_GROUP_PERSON
add constraint FK_ALARM_GROUP_PERSON_GROUP_ID foreign key (GROUP_ID)
references ALARM_GROUP (ID);

alter table ALARM_GROUP_PERSON
add constraint FK_ALARM_GROUP_PERSON_PER_ID foreign key (PERSON_ID)
references ALARM_PERSON (ID);

alter table ALARM_INFO
add constraint FK_ALARM_INFO_ITEM_GROUP_ID foreign key (ITEM_GROUP_ID)
references ALARM_ITEM_GROUP (ID);

alter table ALARM_ITEM_GROUP
add constraint FK_ALARM_ITEM_GROUP_GROUP_ID foreign key (GROUP_ID)
references ALARM_GROUP (ID);

alter table ALARM_ITEM_GROUP
add constraint FK_ALARM_ITEM_GROUP_ITEM_ID foreign key (ITEM_ID)
references MONITOR_ITEM (ID);

alter table MODULE_DEFINE
add constraint FK_MODULE_SYSTEM_ID foreign key (SYSTEM_ID)
references SYSTEM_DEFINE (ID);

alter table MONITOR_INFO
add constraint FK_MONITOR_INFO_OBJECT foreign key (OBJECT)
references MONITOR_OBJECT (CODE);

alter table MONITOR_INFO
add constraint FK_MONITOR_INFO_ITEM foreign key (ITEM)
references MONITOR_ITEM (CODE);

alter table MONITOR_INFO
add constraint FK_MONITOR_INFO_PROPS foreign key (PROP)
references MONITOR_ITEM_PROPS (CODE);

alter table MONITOR_ITEM
add constraint FK_MONITOR_ITEM_ITEM_TYPE foreign key (ITEM_TYPE)
references MONITOR_ITEM_TYPE (ID);

alter table MONITOR_ITEM_PROPS
add constraint FK_MONITOR_ITEM_PROPS_ITEM_ID foreign key (ITEM_ID)
references MONITOR_ITEM (ID);

alter table MONITOR_OBJECT
add constraint FK_MONITOR_OBJECT_MODULE_ID foreign key (MODULE_ID)
references MODULE_DEFINE (ID);

alter table MONITOR_OBJECT
add constraint FK_MONITOR_OBJECT_OBJECT_TYPE foreign key (OBJECT_TYPE)
references MONITOR_OBJECT_TYPE (ID);

alter table MONITOR_OBJECT_ITEM
add constraint FK_MONITOR_OBJECT_ITEM_OBJ_ID foreign key (OBJECT_ID)
references MONITOR_OBJECT (ID);

alter table MONITOR_OBJECT_ITEM
add constraint FK_MONITOR_OBJECT_ITEM_ITEM_ID foreign key (ITEM_ID)
references MONITOR_ITEM (ID);
