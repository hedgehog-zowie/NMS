drop table PK_TABLE cascade constraints;

alter table ALARM_GROUP_PERSON
drop constraint FK_ALARM_GROUP_PERSON_GROUP_ID;

alter table ALARM_GROUP_PERSON
drop constraint FK_ALARM_GROUP_PERSON_PER_ID;

alter table ALARM_INFO
drop constraint FK_ALARM_INFO_ITEM_GROUP_ID;

alter table ALARM_ITEM_GROUP
drop constraint FK_ALARM_ITEM_GROUP_GROUP_ID;

alter table ALARM_ITEM_GROUP
drop constraint FK_ALARM_ITEM_GROUP_ITEM_ID;

alter table MODULE_DEFINE
drop constraint FK_MODULE_SYSTEM_ID;

alter table MONITOR_INFO
drop constraint FK_MONITOR_INFO_OBJECT;

alter table MONITOR_INFO
drop constraint FK_MONITOR_INFO_ITEM;

alter table MONITOR_INFO
drop constraint FK_MONITOR_INFO_PROPS;

alter table MONITOR_ITEM
drop constraint FK_MONITOR_ITEM_ITEM_TYPE;

alter table MONITOR_ITEM_PROPS
drop constraint FK_MONITOR_ITEM_PROPS_ITEM_ID;

alter table MONITOR_OBJECT
drop constraint FK_MONITOR_OBJECT_MODULE_ID;

alter table MONITOR_OBJECT
drop constraint FK_MONITOR_OBJECT_OBJECT_TYPE;

alter table MONITOR_OBJECT_ITEM
drop constraint FK_MONITOR_OBJECT_ITEM_OBJ_ID;

alter table MONITOR_OBJECT_ITEM
drop constraint FK_MONITOR_OBJECT_ITEM_ITEM_ID;

drop table ALARM_GROUP cascade constraints;

drop table ALARM_GROUP_PERSON cascade constraints;

drop table ALARM_INFO cascade constraints;

drop table ALARM_ITEM_GROUP cascade constraints;

drop table ALARM_PERSON cascade constraints;

drop table MODULE_DEFINE cascade constraints;

drop table MONITOR_INFO cascade constraints;

drop table MONITOR_ITEM cascade constraints;

drop table MONITOR_ITEM_PROPS cascade constraints;

drop table MONITOR_ITEM_TYPE cascade constraints;

drop table MONITOR_OBJECT cascade constraints;

drop table MONITOR_OBJECT_ITEM cascade constraints;

drop table MONITOR_OBJECT_TYPE cascade constraints;

drop table SYSTEM_DEFINE cascade constraints;