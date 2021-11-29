/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.7.32 : Database - scale
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`scale` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `scale`;

/*Table structure for table `act_evt_log` */

DROP TABLE IF EXISTS `act_evt_log`;

CREATE TABLE `act_evt_log` (
  `LOG_NR_` bigint(20) NOT NULL AUTO_INCREMENT,
  `TYPE_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TIME_STAMP_` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DATA_` longblob,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LOCK_TIME_` timestamp(3) NULL DEFAULT NULL,
  `IS_PROCESSED_` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`LOG_NR_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_evt_log` */

/*Table structure for table `act_ge_bytearray` */

DROP TABLE IF EXISTS `act_ge_bytearray`;

CREATE TABLE `act_ge_bytearray` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BYTES_` longblob,
  `GENERATED_` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_BYTEARR_DEPL` (`DEPLOYMENT_ID_`),
  CONSTRAINT `ACT_FK_BYTEARR_DEPL` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `act_re_deployment` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_ge_bytearray` */

/*Table structure for table `act_ge_property` */

DROP TABLE IF EXISTS `act_ge_property`;

CREATE TABLE `act_ge_property` (
  `NAME_` varchar(64) COLLATE utf8_bin NOT NULL,
  `VALUE_` varchar(300) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  PRIMARY KEY (`NAME_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_ge_property` */

insert  into `act_ge_property`(`NAME_`,`VALUE_`,`REV_`) values ('cfg.execution-related-entities-count','false',1),('next.dbid','1',1),('schema.history','create(6.0.0.4)',1),('schema.version','6.0.0.4',1);

/*Table structure for table `act_hi_actinst` */

DROP TABLE IF EXISTS `act_hi_actinst`;

CREATE TABLE `act_hi_actinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `ACT_ID_` varchar(255) COLLATE utf8_bin NOT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CALL_PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ACT_TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_ACT_INST_START` (`START_TIME_`),
  KEY `ACT_IDX_HI_ACT_INST_END` (`END_TIME_`),
  KEY `ACT_IDX_HI_ACT_INST_PROCINST` (`PROC_INST_ID_`,`ACT_ID_`),
  KEY `ACT_IDX_HI_ACT_INST_EXEC` (`EXECUTION_ID_`,`ACT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_hi_actinst` */

/*Table structure for table `act_hi_attachment` */

DROP TABLE IF EXISTS `act_hi_attachment`;

CREATE TABLE `act_hi_attachment` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `URL_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CONTENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TIME_` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_hi_attachment` */

/*Table structure for table `act_hi_comment` */

DROP TABLE IF EXISTS `act_hi_comment`;

CREATE TABLE `act_hi_comment` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TIME_` datetime(3) NOT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACTION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `MESSAGE_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `FULL_MSG_` longblob,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_hi_comment` */

/*Table structure for table `act_hi_detail` */

DROP TABLE IF EXISTS `act_hi_detail`;

CREATE TABLE `act_hi_detail` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VAR_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TIME_` datetime(3) NOT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_DETAIL_PROC_INST` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_DETAIL_ACT_INST` (`ACT_INST_ID_`),
  KEY `ACT_IDX_HI_DETAIL_TIME` (`TIME_`),
  KEY `ACT_IDX_HI_DETAIL_NAME` (`NAME_`),
  KEY `ACT_IDX_HI_DETAIL_TASK_ID` (`TASK_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_hi_detail` */

/*Table structure for table `act_hi_identitylink` */

DROP TABLE IF EXISTS `act_hi_identitylink`;

CREATE TABLE `act_hi_identitylink` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `GROUP_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_USER` (`USER_ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_TASK` (`TASK_ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_PROCINST` (`PROC_INST_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_hi_identitylink` */

/*Table structure for table `act_hi_procinst` */

DROP TABLE IF EXISTS `act_hi_procinst`;

CREATE TABLE `act_hi_procinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `BUSINESS_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `START_USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `END_ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUPER_PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `PROC_INST_ID_` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_PRO_INST_END` (`END_TIME_`),
  KEY `ACT_IDX_HI_PRO_I_BUSKEY` (`BUSINESS_KEY_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_hi_procinst` */

/*Table structure for table `act_hi_taskinst` */

DROP TABLE IF EXISTS `act_hi_taskinst`;

CREATE TABLE `act_hi_taskinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_DEF_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `CLAIM_TIME_` datetime(3) DEFAULT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `DUE_DATE_` datetime(3) DEFAULT NULL,
  `FORM_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_TASK_INST_PROCINST` (`PROC_INST_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_hi_taskinst` */

/*Table structure for table `act_hi_varinst` */

DROP TABLE IF EXISTS `act_hi_varinst`;

CREATE TABLE `act_hi_varinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VAR_TYPE_` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` datetime(3) DEFAULT NULL,
  `LAST_UPDATED_TIME_` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_PROCVAR_PROC_INST` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_PROCVAR_NAME_TYPE` (`NAME_`,`VAR_TYPE_`),
  KEY `ACT_IDX_HI_PROCVAR_TASK_ID` (`TASK_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_hi_varinst` */

/*Table structure for table `act_id_group` */

DROP TABLE IF EXISTS `act_id_group`;

CREATE TABLE `act_id_group` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_id_group` */

/*Table structure for table `act_id_info` */

DROP TABLE IF EXISTS `act_id_info`;

CREATE TABLE `act_id_info` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `USER_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `VALUE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PASSWORD_` longblob,
  `PARENT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_id_info` */

/*Table structure for table `act_id_membership` */

DROP TABLE IF EXISTS `act_id_membership`;

CREATE TABLE `act_id_membership` (
  `USER_ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `GROUP_ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`USER_ID_`,`GROUP_ID_`),
  KEY `ACT_FK_MEMB_GROUP` (`GROUP_ID_`) USING BTREE,
  CONSTRAINT `act_id_membership_ibfk_1` FOREIGN KEY (`GROUP_ID_`) REFERENCES `act_id_group` (`ID_`),
  CONSTRAINT `act_id_membership_ibfk_2` FOREIGN KEY (`USER_ID_`) REFERENCES `act_id_user` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_id_membership` */

/*Table structure for table `act_id_user` */

DROP TABLE IF EXISTS `act_id_user`;

CREATE TABLE `act_id_user` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `FIRST_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LAST_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EMAIL_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PWD_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PICTURE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_id_user` */

/*Table structure for table `act_procdef_info` */

DROP TABLE IF EXISTS `act_procdef_info`;

CREATE TABLE `act_procdef_info` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `INFO_JSON_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_UNIQ_INFO_PROCDEF` (`PROC_DEF_ID_`),
  KEY `ACT_IDX_INFO_PROCDEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_INFO_JSON_BA` (`INFO_JSON_ID_`),
  CONSTRAINT `ACT_FK_INFO_JSON_BA` FOREIGN KEY (`INFO_JSON_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_INFO_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_procdef_info` */

/*Table structure for table `act_re_deployment` */

DROP TABLE IF EXISTS `act_re_deployment`;

CREATE TABLE `act_re_deployment` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `DEPLOY_TIME_` timestamp(3) NULL DEFAULT NULL,
  `ENGINE_VERSION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_re_deployment` */

/*Table structure for table `act_re_model` */

DROP TABLE IF EXISTS `act_re_model`;

CREATE TABLE `act_re_model` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LAST_UPDATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `VERSION_` int(11) DEFAULT NULL,
  `META_INFO_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EDITOR_SOURCE_VALUE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EDITOR_SOURCE_EXTRA_VALUE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_MODEL_SOURCE` (`EDITOR_SOURCE_VALUE_ID_`),
  KEY `ACT_FK_MODEL_SOURCE_EXTRA` (`EDITOR_SOURCE_EXTRA_VALUE_ID_`),
  KEY `ACT_FK_MODEL_DEPLOYMENT` (`DEPLOYMENT_ID_`),
  CONSTRAINT `ACT_FK_MODEL_DEPLOYMENT` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `act_re_deployment` (`ID_`),
  CONSTRAINT `ACT_FK_MODEL_SOURCE` FOREIGN KEY (`EDITOR_SOURCE_VALUE_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_MODEL_SOURCE_EXTRA` FOREIGN KEY (`EDITOR_SOURCE_EXTRA_VALUE_ID_`) REFERENCES `act_ge_bytearray` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_re_model` */

/*Table structure for table `act_re_procdef` */

DROP TABLE IF EXISTS `act_re_procdef`;

CREATE TABLE `act_re_procdef` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VERSION_` int(11) NOT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RESOURCE_NAME_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DGRM_RESOURCE_NAME_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `HAS_START_FORM_KEY_` tinyint(4) DEFAULT NULL,
  `HAS_GRAPHICAL_NOTATION_` tinyint(4) DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `ENGINE_VERSION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_UNIQ_PROCDEF` (`KEY_`,`VERSION_`,`TENANT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_re_procdef` */

/*Table structure for table `act_ru_deadletter_job` */

DROP TABLE IF EXISTS `act_ru_deadletter_job`;

CREATE TABLE `act_ru_deadletter_job` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EXCLUSIVE_` tinyint(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DUEDATE_` timestamp(3) NULL DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_DEADLETTER_JOB_EXECUTION` (`EXECUTION_ID_`),
  KEY `ACT_FK_DEADLETTER_JOB_PROCESS_INSTANCE` (`PROCESS_INSTANCE_ID_`),
  KEY `ACT_FK_DEADLETTER_JOB_PROC_DEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_DEADLETTER_JOB_EXCEPTION` (`EXCEPTION_STACK_ID_`),
  CONSTRAINT `ACT_FK_DEADLETTER_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_DEADLETTER_JOB_EXECUTION` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_DEADLETTER_JOB_PROCESS_INSTANCE` FOREIGN KEY (`PROCESS_INSTANCE_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_DEADLETTER_JOB_PROC_DEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_ru_deadletter_job` */

/*Table structure for table `act_ru_event_subscr` */

DROP TABLE IF EXISTS `act_ru_event_subscr`;

CREATE TABLE `act_ru_event_subscr` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `EVENT_TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EVENT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACTIVITY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CONFIGURATION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATED_` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_EVENT_SUBSCR_CONFIG_` (`CONFIGURATION_`),
  KEY `ACT_FK_EVENT_EXEC` (`EXECUTION_ID_`),
  CONSTRAINT `ACT_FK_EVENT_EXEC` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_ru_event_subscr` */

/*Table structure for table `act_ru_execution` */

DROP TABLE IF EXISTS `act_ru_execution`;

CREATE TABLE `act_ru_execution` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BUSINESS_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SUPER_EXEC_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ROOT_PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `IS_ACTIVE_` tinyint(4) DEFAULT NULL,
  `IS_CONCURRENT_` tinyint(4) DEFAULT NULL,
  `IS_SCOPE_` tinyint(4) DEFAULT NULL,
  `IS_EVENT_SCOPE_` tinyint(4) DEFAULT NULL,
  `IS_MI_ROOT_` tinyint(4) DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `CACHED_ENT_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME_` datetime(3) DEFAULT NULL,
  `START_USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LOCK_TIME_` timestamp(3) NULL DEFAULT NULL,
  `IS_COUNT_ENABLED_` tinyint(4) DEFAULT NULL,
  `EVT_SUBSCR_COUNT_` int(11) DEFAULT NULL,
  `TASK_COUNT_` int(11) DEFAULT NULL,
  `JOB_COUNT_` int(11) DEFAULT NULL,
  `TIMER_JOB_COUNT_` int(11) DEFAULT NULL,
  `SUSP_JOB_COUNT_` int(11) DEFAULT NULL,
  `DEADLETTER_JOB_COUNT_` int(11) DEFAULT NULL,
  `VAR_COUNT_` int(11) DEFAULT NULL,
  `ID_LINK_COUNT_` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_EXEC_BUSKEY` (`BUSINESS_KEY_`),
  KEY `ACT_IDC_EXEC_ROOT` (`ROOT_PROC_INST_ID_`),
  KEY `ACT_FK_EXE_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_EXE_PARENT` (`PARENT_ID_`),
  KEY `ACT_FK_EXE_SUPER` (`SUPER_EXEC_`),
  KEY `ACT_FK_EXE_PROCDEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_EXE_PARENT` FOREIGN KEY (`PARENT_ID_`) REFERENCES `act_ru_execution` (`ID_`) ON DELETE CASCADE,
  CONSTRAINT `ACT_FK_EXE_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `ACT_FK_EXE_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ACT_FK_EXE_SUPER` FOREIGN KEY (`SUPER_EXEC_`) REFERENCES `act_ru_execution` (`ID_`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_ru_execution` */

/*Table structure for table `act_ru_identitylink` */

DROP TABLE IF EXISTS `act_ru_identitylink`;

CREATE TABLE `act_ru_identitylink` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `GROUP_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_IDENT_LNK_USER` (`USER_ID_`),
  KEY `ACT_IDX_IDENT_LNK_GROUP` (`GROUP_ID_`),
  KEY `ACT_IDX_ATHRZ_PROCEDEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_TSKASS_TASK` (`TASK_ID_`),
  KEY `ACT_FK_IDL_PROCINST` (`PROC_INST_ID_`),
  CONSTRAINT `ACT_FK_ATHRZ_PROCEDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `ACT_FK_IDL_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_TSKASS_TASK` FOREIGN KEY (`TASK_ID_`) REFERENCES `act_ru_task` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_ru_identitylink` */

/*Table structure for table `act_ru_job` */

DROP TABLE IF EXISTS `act_ru_job`;

CREATE TABLE `act_ru_job` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `LOCK_EXP_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXCLUSIVE_` tinyint(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RETRIES_` int(11) DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DUEDATE_` timestamp(3) NULL DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_JOB_EXECUTION` (`EXECUTION_ID_`),
  KEY `ACT_FK_JOB_PROCESS_INSTANCE` (`PROCESS_INSTANCE_ID_`),
  KEY `ACT_FK_JOB_PROC_DEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_JOB_EXCEPTION` (`EXCEPTION_STACK_ID_`),
  CONSTRAINT `ACT_FK_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_JOB_EXECUTION` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_JOB_PROCESS_INSTANCE` FOREIGN KEY (`PROCESS_INSTANCE_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_JOB_PROC_DEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_ru_job` */

/*Table structure for table `act_ru_suspended_job` */

DROP TABLE IF EXISTS `act_ru_suspended_job`;

CREATE TABLE `act_ru_suspended_job` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EXCLUSIVE_` tinyint(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RETRIES_` int(11) DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DUEDATE_` timestamp(3) NULL DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_SUSPENDED_JOB_EXECUTION` (`EXECUTION_ID_`),
  KEY `ACT_FK_SUSPENDED_JOB_PROCESS_INSTANCE` (`PROCESS_INSTANCE_ID_`),
  KEY `ACT_FK_SUSPENDED_JOB_PROC_DEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_SUSPENDED_JOB_EXCEPTION` (`EXCEPTION_STACK_ID_`),
  CONSTRAINT `ACT_FK_SUSPENDED_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_SUSPENDED_JOB_EXECUTION` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_SUSPENDED_JOB_PROCESS_INSTANCE` FOREIGN KEY (`PROCESS_INSTANCE_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_SUSPENDED_JOB_PROC_DEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_ru_suspended_job` */

/*Table structure for table `act_ru_task` */

DROP TABLE IF EXISTS `act_ru_task`;

CREATE TABLE `act_ru_task` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TASK_DEF_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DELEGATION_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `DUE_DATE_` datetime(3) DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `FORM_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CLAIM_TIME_` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_TASK_CREATE` (`CREATE_TIME_`),
  KEY `ACT_FK_TASK_EXE` (`EXECUTION_ID_`),
  KEY `ACT_FK_TASK_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_TASK_PROCDEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_TASK_EXE` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_TASK_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `ACT_FK_TASK_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_ru_task` */

/*Table structure for table `act_ru_timer_job` */

DROP TABLE IF EXISTS `act_ru_timer_job`;

CREATE TABLE `act_ru_timer_job` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `LOCK_EXP_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXCLUSIVE_` tinyint(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RETRIES_` int(11) DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DUEDATE_` timestamp(3) NULL DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_TIMER_JOB_EXECUTION` (`EXECUTION_ID_`),
  KEY `ACT_FK_TIMER_JOB_PROCESS_INSTANCE` (`PROCESS_INSTANCE_ID_`),
  KEY `ACT_FK_TIMER_JOB_PROC_DEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_TIMER_JOB_EXCEPTION` (`EXCEPTION_STACK_ID_`),
  CONSTRAINT `ACT_FK_TIMER_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_TIMER_JOB_EXECUTION` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_TIMER_JOB_PROCESS_INSTANCE` FOREIGN KEY (`PROCESS_INSTANCE_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_TIMER_JOB_PROC_DEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_ru_timer_job` */

/*Table structure for table `act_ru_variable` */

DROP TABLE IF EXISTS `act_ru_variable`;

CREATE TABLE `act_ru_variable` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_VARIABLE_TASK_ID` (`TASK_ID_`),
  KEY `ACT_FK_VAR_EXE` (`EXECUTION_ID_`),
  KEY `ACT_FK_VAR_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_VAR_BYTEARRAY` (`BYTEARRAY_ID_`),
  CONSTRAINT `ACT_FK_VAR_BYTEARRAY` FOREIGN KEY (`BYTEARRAY_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_VAR_EXE` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_VAR_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_ru_variable` */

/*Table structure for table `dld_log` */

DROP TABLE IF EXISTS `dld_log`;

CREATE TABLE `dld_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '多伦多日志id',
  `patient_id` bigint(20) DEFAULT NULL COMMENT '患者id',
  `patient_name` varchar(100) DEFAULT NULL COMMENT '患者名称',
  `sex` char(1) DEFAULT NULL COMMENT '性别',
  `age` int(20) DEFAULT NULL COMMENT '年龄',
  `education` varchar(20) DEFAULT NULL COMMENT '文化程度',
  `job` varchar(20) DEFAULT NULL COMMENT '职业',
  `diagnosis` varchar(50) DEFAULT NULL COMMENT '诊断',
  `test_day` datetime DEFAULT NULL COMMENT '测试日期',
  `qgbb` int(20) DEFAULT NULL COMMENT '情感辨别',
  `qgms` int(20) DEFAULT NULL COMMENT '情感描述',
  `wxx` int(20) DEFAULT NULL,
  `sum` int(50) DEFAULT NULL COMMENT '总分',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='多伦多量表日志表';

/*Data for the table `dld_log` */

insert  into `dld_log`(`id`,`patient_id`,`patient_name`,`sex`,`age`,`education`,`job`,`diagnosis`,`test_day`,`qgbb`,`qgms`,`wxx`,`sum`,`create_by`,`create_time`) values (1,8,'李四','0',23,'本科','军人','焦虑症','2021-07-05 13:47:49',6,6,10,22,NULL,NULL),(2,14,'张三','0',21,'本科','学生','抑郁症','2021-07-06 09:52:47',6,5,8,19,NULL,NULL);

/*Table structure for table `eis_log` */

DROP TABLE IF EXISTS `eis_log`;

CREATE TABLE `eis_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志id',
  `patient_id` bigint(20) DEFAULT NULL COMMENT '患者id',
  `patient_name` varchar(100) DEFAULT NULL COMMENT '患者名称',
  `sex` char(1) DEFAULT NULL COMMENT '性别',
  `age` int(20) DEFAULT NULL COMMENT '年龄',
  `education` varchar(20) DEFAULT NULL COMMENT '文化程度',
  `job` varchar(20) DEFAULT NULL COMMENT '职业',
  `diagnosis` varchar(50) DEFAULT NULL COMMENT '诊断',
  `test_day` datetime DEFAULT NULL COMMENT '测试日期',
  `qxzj` int(20) DEFAULT NULL COMMENT '情绪知觉',
  `zwqx` int(20) DEFAULT NULL COMMENT '自我情绪管理',
  `trqx` int(20) DEFAULT NULL COMMENT '他人情绪管理',
  `qxbd` int(20) DEFAULT NULL COMMENT '情绪表达',
  `sum` int(50) DEFAULT NULL COMMENT '总分',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='情绪智力量表日志表';

/*Data for the table `eis_log` */

/*Table structure for table `eis_record` */

DROP TABLE IF EXISTS `eis_record`;

CREATE TABLE `eis_record` (
  `table_id` int(32) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `scale_id` int(32) DEFAULT NULL COMMENT '表id',
  `tabl_name` varchar(55) DEFAULT NULL COMMENT '表名称',
  `grade` varchar(32) DEFAULT NULL COMMENT '5自评6他评',
  `tel_number` varchar(32) DEFAULT NULL COMMENT '电话',
  `score` int(32) DEFAULT NULL COMMENT '得分',
  `task_status` varchar(32) DEFAULT NULL COMMENT '1开始2进行中3已结束',
  `del_flag` varchar(32) DEFAULT NULL COMMENT '是否删除0存在1删除',
  `table_comment` varchar(55) DEFAULT NULL COMMENT '表描述',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `scoreA` int(32) DEFAULT NULL,
  `scoreB` int(32) DEFAULT NULL,
  `scoreC` int(32) DEFAULT NULL,
  `scoreD` int(32) DEFAULT NULL,
  `scoreE` int(32) DEFAULT NULL,
  `scoreF` int(32) DEFAULT NULL,
  `scoreG` int(32) DEFAULT NULL,
  PRIMARY KEY (`table_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `eis_record` */

insert  into `eis_record`(`table_id`,`scale_id`,`tabl_name`,`grade`,`tel_number`,`score`,`task_status`,`del_flag`,`table_comment`,`create_time`,`update_time`,`remark`,`scoreA`,`scoreB`,`scoreC`,`scoreD`,`scoreE`,`scoreF`,`scoreG`) values (1,1,'1',NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `eis_scale_questions` */

DROP TABLE IF EXISTS `eis_scale_questions`;

CREATE TABLE `eis_scale_questions` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `scale_id` int(32) DEFAULT NULL COMMENT '量表id',
  `title` varchar(32) DEFAULT NULL COMMENT '题号',
  `content` varchar(255) DEFAULT NULL COMMENT '问题描述',
  `answer_a` varchar(32) DEFAULT NULL COMMENT '选项a',
  `answer_b` varchar(32) DEFAULT NULL COMMENT '选项b',
  `answer_c` varchar(32) DEFAULT NULL COMMENT '选项c',
  `answer_d` varchar(32) DEFAULT NULL COMMENT '选项d',
  `answer_e` varchar(32) DEFAULT NULL COMMENT 'e',
  `answer_f` varchar(32) DEFAULT NULL COMMENT 'f',
  `answer_g` varchar(32) DEFAULT NULL COMMENT 'g',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=261 DEFAULT CHARSET=utf8mb4 COMMENT='身心调节-量表-所有题目';

/*Data for the table `eis_scale_questions` */

insert  into `eis_scale_questions`(`id`,`scale_id`,`title`,`content`,`answer_a`,`answer_b`,`answer_c`,`answer_d`,`answer_e`,`answer_f`,`answer_g`) values (100,51,'1','我知道什么时候该和别人谈论我的私人问题','1','2','3','4','5',NULL,NULL),(101,51,'2','当我面对某种困难时，我能够回忆起面对同样困难并克服它们的时候','1','2','3','4','5',NULL,NULL),(102,51,'3','我期望我能够做好我想做的大多数的事情','1','2','3','4','5',NULL,NULL),(103,51,'4','别人很容易相信我','1','2','3','4','5',NULL,NULL),(105,51,'5','我觉得我很难理解别人的身体语言','1','2','3','4','5',NULL,NULL),(106,51,'6','我生命中的一些重大事件让我重新评估了什么是重要的什么是不重要的','1','2','3','4','5',NULL,NULL),(107,51,'7','心境好的时候我就能看到新的希望','1','2','3','4','5',NULL,NULL),(108,51,'8','我的生活是否有意义，情绪是影响因素之一','1','2','3','4','5',NULL,NULL),(109,51,'9','我能清楚意识到自己体验的情绪','1','2','3','4','5',NULL,NULL),(110,51,'10','我希望能够有好的事情发生','1','2','3','4','5',NULL,NULL),(111,51,'11','我喜欢和别人分享自己的情感','1','2','3','4','5',NULL,NULL),(112,51,'12','情绪好的时候，我知道如何把它延长','1','2','3','4','5',NULL,NULL),(113,51,'13','安排有关事情，我尽可能使别人感到满意','1','2','3','4','5',NULL,NULL),(114,51,'14','我会去找一些让我感到开心的活动','1','2','3','4','5',NULL,NULL),(115,51,'15','我很清楚我传递给别人的非言语信息','1','2','3','4','5',NULL,NULL),(116,51,'16','我尽量做的好一些，以给别人留下好的印象','1','2','3','4','5',NULL,NULL),(117,51,'17','当我心情好的时候，解决问题对我来说·很容易','1','2','3','4','5',NULL,NULL),(118,51,'18','通过观察面部表情，我可以辨别别人的情绪','1','2','3','4','5',NULL,NULL),(119,51,'19','我知道自己情绪变化的原因','1','2','3','4','5',NULL,NULL),(120,51,'20','心情好的时候，新奇的想法就会多一些','1','2','3','4','5',NULL,NULL),(121,51,'21','我能够控制自己的情绪','1','2','3','4','5',NULL,NULL),(122,51,'22','我很清楚自己在某一刻的情绪','1','2','3','4','5',NULL,NULL),(123,51,'23','学习时我会想象自己即将取得好成绩，以激励自己','1','2','3','4','5',NULL,NULL),(124,51,'24','当别人在某个方面做的很好时，我会称赞他们','1','2','3','4','5',NULL,NULL),(125,51,'25','我能够了解别人传递给我的非言语信息','1','2','3','4','5',NULL,NULL),(126,51,'26','当别人告诉我他人生中的某件重大事件时，我几乎感觉到好像发生在自己身上一样','1','2','3','4','5',NULL,NULL),(127,51,'27','当我感到情绪变化时，就会涌现一些新颖的想法','1','2','3','4','5',NULL,NULL),(128,51,'28','遇到困难时，一想到可能会失败，我就会退却','1','2','3','4','5',NULL,NULL),(129,51,'29','只要看一眼，我就知道别人的情绪怎样','1','2','3','4','5',NULL,NULL),(130,51,'30','当别人消沉时我能够帮助他，使他感觉好一点','1','2','3','4','5',NULL,NULL),(131,51,'31','在挫折面前，我让自己保持良好的情绪以应对挑战','1','2','3','4','5',NULL,NULL),(132,51,'32','我能够通过别人讲话的语调判断他当时的情绪','1','2','3','4','5',NULL,NULL),(133,51,'33','我很难理解别人的想法和感受','1','2','3','4','5',NULL,NULL),(141,52,'1','我觉得比平时容易紧张或着急','1','2','3','4','0',NULL,NULL),(142,52,'2','我无缘无故地感到害怕','1','2','3','4','0',NULL,NULL),(143,52,'3','我容易心里烦乱或觉得惊恐','1','2','3','4','0',NULL,NULL),(144,52,'4','我觉得我可能将要发疯','1','2','3','4','0',NULL,NULL),(145,52,'5','我觉得一切都很好，也不会发生什么不幸','4','3','2','1','0',NULL,NULL),(146,52,'6','我手脚发抖打颤','1','2','3','4','0',NULL,NULL),(147,52,'7','我因为头痛、颈痛和背痛而苦恼','1','2','3','4','0',NULL,NULL),(148,52,'8','我感觉容易衰弱和疲乏','1','2','3','4','0',NULL,NULL),(149,52,'9','我觉得心平气和，并且容易安静坐着','4','3','2','1','0',NULL,NULL),(150,52,'10','我觉得心跳很快','1','2','3','4','0',NULL,NULL),(151,52,'11','我因为一阵阵头晕而苦恼','1','2','3','4','0',NULL,NULL),(152,52,'12','我有晕倒发作，或觉得要晕倒似的','1','2','3','4','0',NULL,NULL),(153,52,'13','我吸气呼气都感到很容易','4','3','2','1','0',NULL,NULL),(154,52,'14','我的手脚麻木和刺痛','1','2','3','4','0',NULL,NULL),(155,52,'15','我因为胃痛和消化不良而苦恼','1','2','3','4','0',NULL,NULL),(156,52,'16','我常常要小便','1','2','3','4','0',NULL,NULL),(157,52,'17','我的手脚常常是干燥温暖的','4','3','2','1','0',NULL,NULL),(158,52,'18','我脸红发热','1','2','3','4','0',NULL,NULL),(159,52,'19','我容易入睡并且一夜睡得很好','4','3','2','1','0',NULL,NULL),(160,52,'20','我做恶梦','1','2','3','4','0',NULL,NULL),(161,53,'1','我觉得闷闷不乐,情绪低沉','1','2','3','4','0',NULL,NULL),(162,53,'2','我觉得一天之中早晨最好','4','3','2','1','0',NULL,NULL),(163,53,'3','我一阵阵哭出来或觉得想哭 ','1','2','3','4','0',NULL,NULL),(164,53,'4','我晚上睡眠不好','1','2','3','4','0',NULL,NULL),(165,53,'5','我吃得跟平常一样多','4','3','2','1','0',NULL,NULL),(166,53,'6','我与异性密切接触时和以往一样感到愉快','4','3','2','1','0',NULL,NULL),(167,53,'7','我发觉我的体重在下降','1','2','3','4','0',NULL,NULL),(168,53,'8','我有便秘的苦恼','1','2','3','4','0',NULL,NULL),(169,53,'9','我心跳比平时快','1','2','3','4','0',NULL,NULL),(170,53,'10','我无缘无故地感到疲乏','1','2','3','4','0',NULL,NULL),(171,53,'11','我的头脑跟平常一样清楚','4','3','2','1','0',NULL,NULL),(172,53,'12','我觉得经常做的事情并没困难','4','3','2','1','0',NULL,NULL),(173,53,'13','我觉得不安而平静不下来','1','2','3','4','0',NULL,NULL),(174,53,'14','我对未来抱有希望','4','3','2','1','0',NULL,NULL),(175,53,'15','我比平常容易生气激动','1','2','3','4','0',NULL,NULL),(176,53,'16','我觉得做出决定是容易的','4','3','2','1','0',NULL,NULL),(177,53,'17','我觉得自己是个有用的人，有人需要我','4','3','2','1','0',NULL,NULL),(178,53,'18','我的生活过得很有意思','4','3','2','1','0',NULL,NULL),(179,53,'19','我认为如果我死了，别人会生活得更好','1','2','3','4','0',NULL,NULL),(180,53,'20','平常感兴趣的事我仍然感兴趣','4','3','2','1','0',NULL,NULL),(181,54,'1','焦虑心境','0','1','2','3','4',NULL,NULL),(182,54,'2','紧张','0','1','2','3','4',NULL,NULL),(183,54,'3','害怕','0','1','2','3','4',NULL,NULL),(184,54,'4','失眠','0','1','2','3','4',NULL,NULL),(185,54,'5','记忆或注意障碍','0','1','2','3','4',NULL,NULL),(186,54,'6','抑郁心境','0','1','2','3','4',NULL,NULL),(187,54,'7','肌肉系统症状','0','1','2','3','4',NULL,NULL),(188,54,'8','感觉系统症状','0','1','2','3','4',NULL,NULL),(189,54,'9','心血管系统症状','0','1','2','3','4',NULL,NULL),(190,54,'10','呼吸系统症状','0','1','2','3','4',NULL,NULL),(191,54,'11','胃肠道症状','0','1','2','3','4',NULL,NULL),(192,54,'12','生殖泌尿系统症状','0','1','2','3','4',NULL,NULL),(193,54,'13','植物神经系统症状','0','1','2','3','4',NULL,NULL),(194,54,'14','会谈时行为表现','0','1','2','3','4',NULL,NULL),(201,55,'1','抑郁情绪','0','1','2','3','4',NULL,NULL),(202,55,'2','有罪感','0','1','2','3','4',NULL,NULL),(203,55,'3','自杀','0','1','2','3','4',NULL,NULL),(204,55,'4','入睡困难','0','1','2','2','2',NULL,NULL),(205,55,'5','睡眠不深','0','1','2','2','2',NULL,NULL),(206,55,'6','早醒','0','1','2','2','2',NULL,NULL),(207,55,'7','工作和兴趣','0','1','2','3','4',NULL,NULL),(208,55,'8','迟缓','0','1','2','3','4',NULL,NULL),(209,55,'9','激越','0','1','2','3','4',NULL,NULL),(210,55,'10','精神性焦虑','0','1','2','3','4',NULL,NULL),(211,55,'11','躯体性焦虑','0','1','2','3','4',NULL,NULL),(212,55,'12','胃肠道炎症','0','1','2','2','2',NULL,NULL),(213,55,'13','全身症状','0','1','2','2','2',NULL,NULL),(214,55,'14','性症状','0','1','2','3','3',NULL,NULL),(215,55,'15','疑病','0','1','2','3','4',NULL,NULL),(216,55,'16','体重减轻','0','1','2','2','2',NULL,NULL),(217,55,'17','自知力','0','1','2','2','2',NULL,NULL),(218,55,'18','日夜变化早','0','1','2','2','2',NULL,NULL),(219,55,'19','日夜变化晚','0','1','2','2','2',NULL,NULL),(220,55,'20','人格或现实解体','0','1','2','3','4',NULL,NULL),(221,55,'21','偏执症状','0','1','2','3','4',NULL,NULL),(222,55,'22','强迫症状','0','1','2','2','2',NULL,NULL),(223,55,'23','能力减退感','0','1','2','3','4',NULL,NULL),(224,55,'24','绝望感','0','1','2','3','4',NULL,NULL),(225,55,'25','自卑感','0','1','2','3','4',NULL,NULL),(231,56,'1','概念紊乱','1','2','3','4','5','6','7'),(232,56,'2','概念紊乱','1','2','3','4','5','6','7'),(233,56,'3','幻觉行为','1','2','3','4','5','6','7'),(234,56,'4','兴奋','1','2','3','4','5','6','7'),(235,56,'5','夸大','1','2','3','4','5','6','7'),(236,56,'6','猜疑或被害','1','2','3','4','5','6','7'),(237,56,'7','敌对性','1','2','3','4','5','6','7'),(238,56,'8','情感迟钝','1','2','3','4','5','6','7'),(239,56,'9','情感退缩','1','2','3','4','5','6','7'),(240,56,'10','情感交流障碍','1','2','3','4','5','6','7'),(241,56,'11','被动或淡漠','1','2','3','4','5','6','7'),(242,56,'12','抽象思维','1','2','3','4','5','6','7'),(243,56,'13','交谈缺乏自发性和流畅性','1','2','3','4','5','6','7'),(244,56,'14','刻板思维','1','2','3','4','5','6','7'),(245,56,'15','担心身体健康','1','2','3','4','5','6','7'),(246,56,'16','焦虑','1','2','3','4','5','6','7'),(247,56,'17','罪恶观念','1','2','3','4','5','6','7'),(248,56,'18','紧张','1','2','3','4','5','6','7'),(249,56,'19','装相和作态','1','2','3','4','5','6','7'),(250,56,'20','抑郁','1','2','3','4','5','6','7'),(251,56,'21','动作迟缓','1','2','3','4','5','6','7'),(252,56,'22','不合作','1','2','3','4','5','6','7'),(253,56,'23','异常思维内容','1','2','3','4','5','6','7'),(254,56,'24','定向障碍','1','2','3','4','5','6','7'),(255,56,'25','注意障碍','1','2','3','4','5','6','7'),(256,56,'26','自知力缺乏','1','2','3','4','5','6','7'),(257,56,'27','意志障碍','1','2','3','4','5','6','7'),(258,56,'28','冲动控制障碍','1','2','3','4','5','6','7'),(259,56,'29','先占观念','1','2','3','4','5','6','7'),(260,56,'30','主动社交回避','1','2','3','4','5','6','7');

/*Table structure for table `eis_table` */

DROP TABLE IF EXISTS `eis_table`;

CREATE TABLE `eis_table` (
  `table_id` int(32) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `scale_id` int(32) DEFAULT NULL COMMENT '表id',
  `tabl_name` varchar(55) DEFAULT NULL COMMENT '表名称',
  `grade` varchar(32) DEFAULT NULL COMMENT '5自评6他评',
  `score` int(32) DEFAULT NULL COMMENT '得分',
  `task_status` varchar(32) DEFAULT NULL COMMENT '1开始2进行中3已结束',
  `del_flag` varchar(32) DEFAULT NULL COMMENT '是否删除0存在1删除',
  `table_comment` varchar(55) DEFAULT NULL COMMENT '表描述',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`table_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `eis_table` */

/*Table structure for table `eis_task` */

DROP TABLE IF EXISTS `eis_task`;

CREATE TABLE `eis_task` (
  `task_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '测评人ID',
  `user_name` varchar(50) DEFAULT NULL COMMENT '测评人姓名',
  `patient_id` bigint(20) DEFAULT NULL COMMENT '患者ID',
  `patient_name` varchar(50) DEFAULT NULL COMMENT '患者名称',
  `workstation` varchar(50) DEFAULT NULL COMMENT '工作站',
  `test_coding` varchar(100) DEFAULT NULL COMMENT '测试编码',
  `typeIds` varchar(100) DEFAULT NULL COMMENT '测评任务',
  `task_status` char(1) DEFAULT NULL COMMENT '任务状态(1未开始,2进行中,3已结束)',
  `del_flag` char(1) DEFAULT NULL COMMENT '是否删除(0存在，1删除)',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `scale_id` varchar(255) DEFAULT NULL COMMENT '已完成量表',
  `type_flag` char(1) DEFAULT NULL COMMENT '任务所属系统（0新冠 1量表）',
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='智能化心身调节任务表';

/*Data for the table `eis_task` */

/*Table structure for table `eis_task_score` */

DROP TABLE IF EXISTS `eis_task_score`;

CREATE TABLE `eis_task_score` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `scale_id` int(32) DEFAULT NULL COMMENT '量表名字',
  `task_id` int(32) DEFAULT NULL COMMENT '任务id',
  `patient_id` int(32) DEFAULT NULL COMMENT '病人id',
  `score` int(32) DEFAULT NULL COMMENT '得分',
  `workstation` varchar(32) DEFAULT NULL COMMENT '工作站',
  `test_date` date DEFAULT '0000-00-00' COMMENT '测试日期',
  `type_ids` varchar(32) DEFAULT NULL COMMENT '对应的第几天',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ipa任务得分保存表';

/*Data for the table `eis_task_score` */

/*Table structure for table `eis_user` */

DROP TABLE IF EXISTS `eis_user`;

CREATE TABLE `eis_user` (
  `user_id` bigint(30) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) DEFAULT NULL COMMENT '用户名',
  `tel_number` varchar(32) DEFAULT NULL COMMENT '手机号',
  `sex` varchar(32) DEFAULT NULL COMMENT '性别',
  `age` varchar(32) DEFAULT NULL COMMENT '年龄',
  `user_type` varchar(32) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `status` char(1) DEFAULT '0' COMMENT '账号状态0正常1停用',
  `del_flag` varchar(1) DEFAULT '0',
  `login_ip` varchar(32) DEFAULT NULL COMMENT '登录ip',
  `login_date` datetime DEFAULT NULL COMMENT '登录时间',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `eis_user` */

/*Table structure for table `evaluation_type` */

DROP TABLE IF EXISTS `evaluation_type`;

CREATE TABLE `evaluation_type` (
  `type_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '测评类型ID',
  `type_name` varchar(100) DEFAULT NULL COMMENT '类型名称',
  `parent_id` bigint(100) DEFAULT NULL COMMENT '父类型ID',
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8 COMMENT='测评类型表';

/*Data for the table `evaluation_type` */

insert  into `evaluation_type`(`type_id`,`type_name`,`parent_id`) values (1,'社会认知能力评估',0),(2,'基础社会认知能力',0),(3,'进阶社会认知能力',0),(4,'高级社会认知能力',0),(5,'量表评估',1),(6,'情绪识别能力评估',1),(7,'心理理论能力评估',1),(8,'社会推理能力评估',1),(9,'失言识别能力评估',1),(10,'情绪识别能力(基础)',2),(11,'心理理论能力(基础)',2),(12,'社会推理能力(基础)',2),(14,'情绪识别能力(进阶)',3),(15,'心理理论能力(进阶)',3),(16,'社会推理能力(进阶)',3),(18,'虚拟场景社会感知',4),(19,'多伦多述情障碍量表',5),(20,'情绪智力量表',5),(21,'人际反应指针问卷',5),(22,'METT微表情识别',6),(23,'复杂眼区识别',7),(24,'社会情境排列',8),(25,'失言任务',9),(26,'高兴',10),(27,'悲伤',10),(28,'愤怒',10),(29,'恐惧',10),(30,'惊讶',10),(31,'厌恶',10),(32,'复杂眼区',11),(33,'社会情境排列',12),(35,'情绪区分',14),(36,'整体情绪感知',14),(37,'一阶社会情绪感知',14),(38,'二阶社会情绪感知',14),(39,'复杂眼区任务',15),(40,'社会情境排列',16),(42,'不想说再见',18),(43,'谁动了我快递',18),(44,'以貌取人',18),(45,'戒烟',18),(46,'智能化心身调节',0),(47,'智能化心身调节量表',0),(48,'第一天',46),(49,'第二天',46),(50,'第三天',46),(51,'第四天',46),(52,'第五天',46),(53,'第六天',46),(54,'第七天',46),(55,'第一天',47),(56,'第二天',47),(57,'第三天',47),(58,'第四天',47),(59,'第五天',47),(60,'第六天',47),(61,'第七天',47),(62,'围手术期',0),(63,'第一天',62),(64,'第二天',62),(65,'第三天',62),(66,'第四天',62),(67,'第五天',62),(68,'第六天',62);

/*Table structure for table `fzyq_log` */

DROP TABLE IF EXISTS `fzyq_log`;

CREATE TABLE `fzyq_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志id',
  `patient_id` bigint(20) DEFAULT NULL COMMENT '患者id',
  `patient_name` varchar(100) DEFAULT NULL COMMENT '患者名称',
  `sex` char(1) DEFAULT NULL COMMENT '性别',
  `age` int(20) DEFAULT NULL COMMENT '年龄',
  `education` varchar(20) DEFAULT NULL COMMENT '文化程度',
  `job` varchar(20) DEFAULT NULL COMMENT '职业',
  `diagnosis` varchar(50) DEFAULT NULL COMMENT '诊断',
  `test_day` datetime DEFAULT NULL COMMENT '测试日期',
  `correct` int(20) DEFAULT NULL COMMENT '正确率',
  `average` double(20,0) DEFAULT NULL COMMENT '平均反应时',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='复杂眼区日志表';

/*Data for the table `fzyq_log` */

/*Table structure for table `gen_table` */

DROP TABLE IF EXISTS `gen_table`;

CREATE TABLE `gen_table` (
  `table_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) DEFAULT '' COMMENT '表描述',
  `class_name` varchar(100) DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `package_name` varchar(100) DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) DEFAULT NULL COMMENT '生成功能作者',
  `options` varchar(1000) DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='代码生成业务表';

/*Data for the table `gen_table` */

insert  into `gen_table`(`table_id`,`table_name`,`table_comment`,`class_name`,`tpl_category`,`package_name`,`module_name`,`business_name`,`function_name`,`function_author`,`options`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values (2,'ipa_patient_user','心身调节系统患者表','IpaPatientUser','crud','com.qx.ipa','ipa','patient','心身调节系统患者','q','{}','admin','2021-07-02 18:17:23','','2021-07-02 18:20:28',NULL),(4,'ipa_task','智能化心身调节任务表','IpaTask','crud','com.qx.ipa','ipa','task','智能化心身调节任务','qx','{}','admin','2021-07-05 15:45:03','','2021-07-05 15:45:34',NULL),(5,'ipa_scale_questions','身心调节-量表-所有题目','IpaScaleQuestions','crud','com.qx.ipa','ipa','questions','身心调节-量-所有题目','meng','{}','admin','2021-07-05 15:52:39','','2021-07-05 16:02:57',NULL),(6,'ipa_question','问题表','IpaQuestion','crud','com.qx.ipa','ipa','ipaQuestion','ipa问题','Meng','{}','admin','2021-07-06 11:32:23','','2021-07-06 11:34:00',NULL),(7,'ipa_media','视频地址表','IpaMedia','crud','com.qx.ipa','ipa','ipa','视频地址','Meng','{}','admin','2021-07-08 16:04:15','','2021-07-08 16:05:06',NULL),(9,'ipa_task_score','ipa任务得分保存表','IpaTaskScore','crud','com.qx.ipa','ipa','ipa','ipa任务得分保存','Meng','{}','admin','2021-07-09 17:19:55','','2021-07-09 17:20:35',NULL),(10,'peri_patient_user','围手术期系统患者表','PeriPatientUser','crud','com.qx.peri','peri','peri','围手术期系统患者','Meng','{}','admin','2021-07-13 16:54:25','','2021-07-13 16:59:36',NULL),(12,'peri_task','围手术期任务表','PeriTask','crud','com.qx.peri','peri','peri','围手术期任务','Meng','{}','admin','2021-07-13 16:54:38','','2021-07-13 16:56:04',NULL),(13,'scale_menu','量表目录表','ScaleMenu','crud','com.qx.scale','scale','menu','量表目录','patient','{}','admin','2021-09-27 16:06:44','','2021-09-27 16:09:49',NULL),(15,'scale_questions','量表题目及分数','ScaleQuestions','crud','com.qx.scale','scale','questions','量表题目及分数','patient','{}','admin','2021-09-30 11:31:44','','2021-09-30 11:32:35',NULL),(16,'scale_patient','量表系统患者表','ScalePatient','crud','com.qx.scale','scale','patient','量表系统患者','patient','{}','admin','2021-10-11 09:59:27','','2021-10-11 10:00:27',NULL),(17,'scale_task','量表系统任务表','ScaleTask','crud','com.qx.scale','scale','task','量表系统任务','patient','{}','admin','2021-10-11 09:59:28','','2021-10-11 10:00:50',NULL),(18,'scale_task_score','量表得分保存表','ScaleTaskScore','crud','com.qx.scale','scale','score','量表得分保存','patient','{}','admin','2021-10-11 09:59:28','','2021-10-11 10:01:14',NULL),(19,'scale_score','量表分数','ScaleScore','crud','com.qx.scale','scale','score','量表分数','patient','{}','admin','2021-10-11 15:17:34','','2021-10-11 15:18:25',NULL);

/*Table structure for table `gen_table_column` */

DROP TABLE IF EXISTS `gen_table_column`;

CREATE TABLE `gen_table_column` (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` varchar(64) DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) DEFAULT '' COMMENT '字典类型',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`)
) ENGINE=InnoDB AUTO_INCREMENT=248 DEFAULT CHARSET=utf8 COMMENT='代码生成业务表字段';

/*Data for the table `gen_table_column` */

insert  into `gen_table_column`(`column_id`,`table_id`,`column_name`,`column_comment`,`column_type`,`java_type`,`java_field`,`is_pk`,`is_increment`,`is_required`,`is_insert`,`is_edit`,`is_list`,`is_query`,`query_type`,`html_type`,`dict_type`,`sort`,`create_by`,`create_time`,`update_by`,`update_time`) values (22,'2','patient_id','患者ID','bigint(50)','Long','patientId','1','1',NULL,'1',NULL,NULL,NULL,'EQ','input','',1,'admin','2021-07-02 18:17:24','','2021-07-02 18:20:29'),(23,'2','patient_name','患者姓名','varchar(30)','String','patientName','0','0','1','1','1','1','1','LIKE','input','',2,'admin','2021-07-02 18:17:24','','2021-07-02 18:20:29'),(24,'2','sex','用户性别（0男 1女 2未知）','char(1)','String','sex','0','0',NULL,'1','1','1','1','EQ','select','',3,'admin','2021-07-02 18:17:24','','2021-07-02 18:20:29'),(25,'2','age','年龄','int(20)','Long','age','0','0',NULL,'1','1','1','1','EQ','input','',4,'admin','2021-07-02 18:17:24','','2021-07-02 18:20:29'),(26,'2','hospital_number','住院号','bigint(50)','Long','hospitalNumber','0','0',NULL,'1','1','1','1','EQ','input','',5,'admin','2021-07-02 18:17:24','','2021-07-02 18:20:29'),(27,'2','ward','病区','varchar(50)','String','ward','0','0',NULL,'1','1','1','1','EQ','input','',6,'admin','2021-07-02 18:17:24','','2021-07-02 18:20:29'),(28,'2','test_date','检测日期','datetime','Date','testDate','0','0',NULL,'1','1','1','1','EQ','datetime','',7,'admin','2021-07-02 18:17:24','','2021-07-02 18:20:29'),(29,'2','job','职业','varchar(20)','String','job','0','0',NULL,'1','1','1','1','EQ','input','',8,'admin','2021-07-02 18:17:24','','2021-07-02 18:20:29'),(30,'2','education','学历','varchar(20)','String','education','0','0',NULL,'1','1','1','1','EQ','input','',9,'admin','2021-07-02 18:17:24','','2021-07-02 18:20:29'),(31,'2','nation','民族','varchar(20)','String','nation','0','0',NULL,'1','1','1','1','EQ','input','',10,'admin','2021-07-02 18:17:24','','2021-07-02 18:20:29'),(32,'2','marital_status','婚姻状况(0未婚,1已婚)','char(1)','String','maritalStatus','0','0',NULL,'1','1','1','1','EQ','radio','',11,'admin','2021-07-02 18:17:24','','2021-07-02 18:20:29'),(33,'2','birthday','出生日期','datetime','Date','birthday','0','0',NULL,'1','1','1','1','EQ','datetime','',12,'admin','2021-07-02 18:17:24','','2021-07-02 18:20:29'),(34,'2','contanct_information','联系方式','varchar(50)','String','contanctInformation','0','0',NULL,'1','1','1','1','EQ','input','',13,'admin','2021-07-02 18:17:24','','2021-07-02 18:20:29'),(35,'2','source','患者来源','varchar(100)','String','source','0','0',NULL,'1','1','1','1','EQ','input','',14,'admin','2021-07-02 18:17:24','','2021-07-02 18:20:29'),(36,'2','classification_coding','分类编码','varchar(100)','String','classificationCoding','0','0',NULL,'1','1','1','1','EQ','input','',15,'admin','2021-07-02 18:17:24','','2021-07-02 18:20:29'),(37,'2','diagnosis','诊断','varchar(50)','String','diagnosis','0','0',NULL,'1','1','1','1','EQ','input','',16,'admin','2021-07-02 18:17:24','','2021-07-02 18:20:29'),(38,'2','del_flag','是否删除(0存在，1删除)','char(1)','String','delFlag','0','0',NULL,'1',NULL,NULL,NULL,'EQ','input','',17,'admin','2021-07-02 18:17:24','','2021-07-02 18:20:29'),(39,'2','create_by','创建者','varchar(64)','String','createBy','0','0',NULL,'1',NULL,NULL,NULL,'EQ','input','',18,'admin','2021-07-02 18:17:24','','2021-07-02 18:20:29'),(40,'2','create_time','创建时间','datetime','Date','createTime','0','0',NULL,'1',NULL,NULL,NULL,'EQ','datetime','',19,'admin','2021-07-02 18:17:24','','2021-07-02 18:20:29'),(41,'2','update_by','更新人','varchar(64)','String','updateBy','0','0',NULL,'1','1',NULL,NULL,'EQ','input','',20,'admin','2021-07-02 18:17:24','','2021-07-02 18:20:29'),(42,'2','update_time','更新时间','datetime','Date','updateTime','0','0',NULL,'1','1',NULL,NULL,'EQ','datetime','',21,'admin','2021-07-02 18:17:24','','2021-07-02 18:20:29'),(64,'4','task_id','任务ID','bigint(20)','Long','taskId','1','1',NULL,'1',NULL,NULL,NULL,'EQ','input','',1,'admin','2021-07-05 15:45:03','','2021-07-05 15:45:34'),(65,'4','user_id','测评人ID','bigint(20)','Long','userId','0','0',NULL,'1','1','1','1','EQ','input','',2,'admin','2021-07-05 15:45:03','','2021-07-05 15:45:34'),(66,'4','user_name','测评人姓名','varchar(50)','String','userName','0','0',NULL,'1','1','1','1','LIKE','input','',3,'admin','2021-07-05 15:45:03','','2021-07-05 15:45:34'),(67,'4','patient_id','患者ID','bigint(20)','Long','patientId','0','0',NULL,'1','1','1','1','EQ','input','',4,'admin','2021-07-05 15:45:03','','2021-07-05 15:45:34'),(68,'4','patient_name','患者名称','varchar(50)','String','patientName','0','0',NULL,'1','1','1','1','LIKE','input','',5,'admin','2021-07-05 15:45:03','','2021-07-05 15:45:34'),(69,'4','workstation','工作站','varchar(50)','String','workstation','0','0',NULL,'1','1','1','1','EQ','input','',6,'admin','2021-07-05 15:45:03','','2021-07-05 15:45:34'),(70,'4','test_coding','测试编码','varchar(100)','String','testCoding','0','0',NULL,'1','1','1','1','EQ','input','',7,'admin','2021-07-05 15:45:03','','2021-07-05 15:45:34'),(71,'4','typeIds','测评任务','varchar(100)','String','typeids','0','0',NULL,'1','1','1','1','EQ','input','',8,'admin','2021-07-05 15:45:04','','2021-07-05 15:45:34'),(72,'4','task_status','任务状态(1未开始,2进行中,3已结束)','char(1)','String','taskStatus','0','0',NULL,'1','1','1','1','EQ','radio','',9,'admin','2021-07-05 15:45:04','','2021-07-05 15:45:34'),(73,'4','del_flag','是否删除(0存在，1删除)','char(1)','String','delFlag','0','0',NULL,'1',NULL,NULL,NULL,'EQ','input','',10,'admin','2021-07-05 15:45:04','','2021-07-05 15:45:34'),(74,'4','create_by','创建者','varchar(64)','String','createBy','0','0',NULL,'1',NULL,NULL,NULL,'EQ','input','',11,'admin','2021-07-05 15:45:04','','2021-07-05 15:45:35'),(75,'4','create_time','创建时间','datetime','Date','createTime','0','0',NULL,'1',NULL,NULL,NULL,'EQ','datetime','',12,'admin','2021-07-05 15:45:04','','2021-07-05 15:45:35'),(76,'4','update_by','更新者','varchar(64)','String','updateBy','0','0',NULL,'1','1',NULL,NULL,'EQ','input','',13,'admin','2021-07-05 15:45:04','','2021-07-05 15:45:35'),(77,'4','update_time','更新时间','datetime','Date','updateTime','0','0',NULL,'1','1',NULL,NULL,'EQ','datetime','',14,'admin','2021-07-05 15:45:04','','2021-07-05 15:45:35'),(78,'4','scale_id','已完成量表','varchar(255)','String','scaleId','0','0',NULL,'1','1','1','1','EQ','input','',15,'admin','2021-07-05 15:45:04','','2021-07-05 15:45:35'),(79,'4','type_flag','任务所属类型','char(1)','String','typeFlag','0','0',NULL,'1','1','1','1','EQ','input','',16,'admin','2021-07-05 15:45:04','','2021-07-05 15:45:35'),(80,'5','id',NULL,'int(32)','Long','id','1','1',NULL,'1',NULL,NULL,NULL,'EQ','input','',1,'admin','2021-07-05 15:52:40','','2021-07-05 16:02:58'),(81,'5','scale_id','量表id','int(32)','Long','scaleId','0','0',NULL,'1','1','1','1','EQ','input','',2,'admin','2021-07-05 15:52:40','','2021-07-05 16:02:58'),(82,'5','title','题号','varchar(32)','String','title','0','0',NULL,'1','1','1','1','EQ','input','',3,'admin','2021-07-05 15:52:40','','2021-07-05 16:02:58'),(83,'5','content','问题描述','varchar(64)','String','content','0','0',NULL,'1','1','1','1','EQ','input','',4,'admin','2021-07-05 15:52:40','','2021-07-05 16:02:58'),(84,'5','answer_a','选项a','varchar(32)','String','answerA','0','0',NULL,'1','1','1','1','EQ','input','',5,'admin','2021-07-05 15:52:40','','2021-07-05 16:02:58'),(85,'5','answer_b','选项b','varchar(32)','String','answerB','0','0',NULL,'1','1','1','1','EQ','input','',6,'admin','2021-07-05 15:52:40','','2021-07-05 16:02:58'),(86,'5','answer_c','选项c','varchar(32)','String','answerC','0','0',NULL,'1','1','1','1','EQ','input','',7,'admin','2021-07-05 15:52:40','','2021-07-05 16:02:58'),(87,'5','answer_d','选项d','varchar(32)','String','answerD','0','0',NULL,'1','1','1','1','EQ','input','',8,'admin','2021-07-05 15:52:40','','2021-07-05 16:02:58'),(88,'6','que_id','ID列','int(11)','Long','queId','1','1',NULL,'1',NULL,NULL,NULL,'EQ','input','',1,'admin','2021-07-06 11:32:23','','2021-07-06 11:34:01'),(89,'6','title','题号','varchar(32)','String','title','0','0',NULL,'1','1','1','1','EQ','input','',2,'admin','2021-07-06 11:32:23','','2021-07-06 11:34:01'),(90,'6','day','对应的第几天','int(11)','Long','day','0','0',NULL,'1','1','1','1','EQ','input','',3,'admin','2021-07-06 11:32:23','','2021-07-06 11:34:01'),(91,'6','question','问题描述','varchar(255)','String','question','0','0',NULL,'1','1','1','1','EQ','input','',4,'admin','2021-07-06 11:32:23','','2021-07-06 11:34:01'),(92,'6','option_a','选项a','varchar(32)','String','optionA','0','0',NULL,'1','1','1','1','EQ','input','',5,'admin','2021-07-06 11:32:23','','2021-07-06 11:34:01'),(93,'6','option_b','选项b','varchar(32)','String','optionB','0','0',NULL,'1','1','1','1','EQ','input','',6,'admin','2021-07-06 11:32:23','','2021-07-06 11:34:01'),(94,'6','resolution','问题解析','varchar(255)','String','resolution','0','0',NULL,'1','1','1','1','EQ','input','',7,'admin','2021-07-06 11:32:23','','2021-07-06 11:34:01'),(95,'6','answer','正确答案','varchar(32)','String','answer','0','0',NULL,'1','1','1','1','EQ','input','',8,'admin','2021-07-06 11:32:23','','2021-07-06 11:34:01'),(96,'7','id','文件id','int(11)','Long','id','1','1',NULL,'1',NULL,NULL,NULL,'EQ','input','',1,'admin','2021-07-08 16:04:15','','2021-07-08 16:05:06'),(97,'7','title','文件名','varchar(32)','String','title','0','0',NULL,'1','1','1','1','EQ','input','',2,'admin','2021-07-08 16:04:15','','2021-07-08 16:05:06'),(98,'7','local','位置','varchar(64)','String','local','0','0',NULL,'1','1','1','1','EQ','input','',3,'admin','2021-07-08 16:04:15','','2021-07-08 16:05:06'),(99,'7','mark','对应的哪天','varchar(32)','String','mark','0','0',NULL,'1','1','1','1','EQ','input','',4,'admin','2021-07-08 16:04:15','','2021-07-08 16:05:06'),(107,'9','id','id','int(32)','Long','id','1','0',NULL,'1',NULL,NULL,NULL,'EQ','input','',1,'admin','2021-07-09 17:19:55','','2021-07-09 17:20:35'),(108,'9','scale_id','量表名字','int(32)','Long','scaleId','0','0',NULL,'1','1','1','1','EQ','input','',2,'admin','2021-07-09 17:19:56','','2021-07-09 17:20:35'),(109,'9','task_id','任务id','int(32)','Long','taskId','0','0',NULL,'1','1','1','1','EQ','input','',3,'admin','2021-07-09 17:19:56','','2021-07-09 17:20:35'),(110,'9','patient_id','病人id','int(32)','Long','patientId','0','0',NULL,'1','1','1','1','EQ','input','',4,'admin','2021-07-09 17:19:56','','2021-07-09 17:20:35'),(111,'9','score','得分','int(32)','Long','score','0','0',NULL,'1','1','1','1','EQ','input','',5,'admin','2021-07-09 17:19:56','','2021-07-09 17:20:35'),(112,'9','workstation','工作站','varchar(32)','String','workstation','0','0',NULL,'1','1','1','1','EQ','input','',6,'admin','2021-07-09 17:19:56','','2021-07-09 17:20:35'),(113,'9','test_date','测试日期','date','Date','testDate','0','0',NULL,'1','1','1','1','EQ','datetime','',7,'admin','2021-07-09 17:19:56','','2021-07-09 17:20:35'),(114,'9','type_ids','对应的第几天','varchar(32)','String','typeIds','0','0',NULL,'1','1','1','1','EQ','input','',8,'admin','2021-07-09 17:19:56','','2021-07-09 17:20:35'),(115,'10','patient_id','患者ID','bigint(50)','Long','patientId','1','1',NULL,'1',NULL,NULL,NULL,'EQ','input','',1,'admin','2021-07-13 16:54:26','','2021-07-13 16:59:37'),(116,'10','patient_name','患者姓名','varchar(30)','String','patientName','0','0','1','1','1','1','1','LIKE','input','',2,'admin','2021-07-13 16:54:27','','2021-07-13 16:59:38'),(117,'10','sex','用户性别（0男 1女 2未知）','char(1)','String','sex','0','0',NULL,'1','1','1','1','EQ','select','',3,'admin','2021-07-13 16:54:27','','2021-07-13 16:59:38'),(118,'10','age','年龄','int(20)','Long','age','0','0',NULL,'1','1','1','1','EQ','input','',4,'admin','2021-07-13 16:54:28','','2021-07-13 16:59:39'),(119,'10','hospital_number','住院号','bigint(50)','Long','hospitalNumber','0','0',NULL,'1','1','1','1','EQ','input','',5,'admin','2021-07-13 16:54:28','','2021-07-13 16:59:39'),(120,'10','ward','病区','varchar(50)','String','ward','0','0',NULL,'1','1','1','1','EQ','input','',6,'admin','2021-07-13 16:54:29','','2021-07-13 16:59:40'),(121,'10','test_date','检测日期','datetime','Date','testDate','0','0',NULL,'1','1','1','1','EQ','datetime','',7,'admin','2021-07-13 16:54:30','','2021-07-13 16:59:40'),(122,'10','job','职业','varchar(20)','String','job','0','0',NULL,'1','1','1','1','EQ','input','',8,'admin','2021-07-13 16:54:30','','2021-07-13 16:59:41'),(123,'10','education','学历','varchar(20)','String','education','0','0',NULL,'1','1','1','1','EQ','input','',9,'admin','2021-07-13 16:54:31','','2021-07-13 16:59:42'),(124,'10','nation','民族','varchar(20)','String','nation','0','0',NULL,'1','1','1','1','EQ','input','',10,'admin','2021-07-13 16:54:31','','2021-07-13 16:59:42'),(125,'10','marital_status','婚姻状况(0未婚,1已婚)','char(1)','String','maritalStatus','0','0',NULL,'1','1','1','1','EQ','radio','',11,'admin','2021-07-13 16:54:32','','2021-07-13 16:59:43'),(126,'10','birthday','出生日期','datetime','Date','birthday','0','0',NULL,'1','1','1','1','EQ','datetime','',12,'admin','2021-07-13 16:54:33','','2021-07-13 16:59:43'),(127,'10','contanct_information','联系方式','varchar(50)','String','contanctInformation','0','0',NULL,'1','1','1','1','EQ','input','',13,'admin','2021-07-13 16:54:33','','2021-07-13 16:59:44'),(128,'10','source','患者来源','varchar(100)','String','source','0','0',NULL,'1','1','1','1','EQ','input','',14,'admin','2021-07-13 16:54:34','','2021-07-13 16:59:45'),(129,'10','classification_coding','分类编码','varchar(100)','String','classificationCoding','0','0',NULL,'1','1','1','1','EQ','input','',15,'admin','2021-07-13 16:54:34','','2021-07-13 16:59:45'),(131,'10','diagnosis','诊断','varchar(50)','String','diagnosis','0','0',NULL,'1','1','1','1','EQ','input','',16,'admin','2021-07-13 16:54:35','','2021-07-13 16:59:46'),(133,'10','del_flag','是否删除(0存在，1删除)','char(1)','String','delFlag','0','0',NULL,'1',NULL,NULL,NULL,'EQ','input','',17,'admin','2021-07-13 16:54:35','','2021-07-13 16:59:46'),(135,'10','create_by','创建者','varchar(64)','String','createBy','0','0',NULL,'1',NULL,NULL,NULL,'EQ','input','',18,'admin','2021-07-13 16:54:36','','2021-07-13 16:59:47'),(137,'10','create_time','创建时间','datetime','Date','createTime','0','0',NULL,'1',NULL,NULL,NULL,'EQ','datetime','',19,'admin','2021-07-13 16:54:36','','2021-07-13 16:59:48'),(139,'10','update_by','更新人','varchar(64)','String','updateBy','0','0',NULL,'1','1',NULL,NULL,'EQ','input','',20,'admin','2021-07-13 16:54:37','','2021-07-13 16:59:49'),(141,'10','update_time','更新时间','datetime','Date','updateTime','0','0',NULL,'1','1',NULL,NULL,'EQ','datetime','',21,'admin','2021-07-13 16:54:38','','2021-07-13 16:59:49'),(144,'12','task_id','任务ID','bigint(20)','Long','taskId','1','1',NULL,'1',NULL,NULL,NULL,'EQ','input','',1,'admin','2021-07-13 16:54:39','','2021-07-13 16:56:04'),(146,'12','user_id','测评人ID','bigint(20)','Long','userId','0','0',NULL,'1','1','1','1','EQ','input','',2,'admin','2021-07-13 16:54:40','','2021-07-13 16:56:05'),(148,'12','user_name','测评人姓名','varchar(50)','String','userName','0','0',NULL,'1','1','1','1','LIKE','input','',3,'admin','2021-07-13 16:54:40','','2021-07-13 16:56:05'),(150,'12','patient_id','患者ID','bigint(20)','Long','patientId','0','0',NULL,'1','1','1','1','EQ','input','',4,'admin','2021-07-13 16:54:41','','2021-07-13 16:56:06'),(152,'12','patient_name','患者名称','varchar(50)','String','patientName','0','0',NULL,'1','1','1','1','LIKE','input','',5,'admin','2021-07-13 16:54:41','','2021-07-13 16:56:06'),(154,'12','workstation','工作站','varchar(50)','String','workstation','0','0',NULL,'1','1','1','1','EQ','input','',6,'admin','2021-07-13 16:54:42','','2021-07-13 16:56:07'),(156,'12','test_coding','测试编码','varchar(100)','String','testCoding','0','0',NULL,'1','1','1','1','EQ','input','',7,'admin','2021-07-13 16:54:42','','2021-07-13 16:56:08'),(158,'12','typeIds','测评任务','varchar(100)','String','typeids','0','0',NULL,'1','1','1','1','EQ','input','',8,'admin','2021-07-13 16:54:43','','2021-07-13 16:56:08'),(160,'12','task_status','任务状态(1未开始,2进行中,3已结束)','char(1)','String','taskStatus','0','0',NULL,'1','1','1','1','EQ','radio','',9,'admin','2021-07-13 16:54:44','','2021-07-13 16:56:09'),(162,'12','del_flag','是否删除(0存在，1删除)','char(1)','String','delFlag','0','0',NULL,'1',NULL,NULL,NULL,'EQ','input','',10,'admin','2021-07-13 16:54:44','','2021-07-13 16:56:09'),(164,'12','create_by','创建者','varchar(64)','String','createBy','0','0',NULL,'1',NULL,NULL,NULL,'EQ','input','',11,'admin','2021-07-13 16:54:45','','2021-07-13 16:56:10'),(166,'12','create_time','创建时间','datetime','Date','createTime','0','0',NULL,'1',NULL,NULL,NULL,'EQ','datetime','',12,'admin','2021-07-13 16:54:45','','2021-07-13 16:56:10'),(168,'12','update_by','更新者','varchar(64)','String','updateBy','0','0',NULL,'1','1',NULL,NULL,'EQ','input','',13,'admin','2021-07-13 16:54:46','','2021-07-13 16:56:11'),(170,'12','update_time','更新时间','datetime','Date','updateTime','0','0',NULL,'1','1',NULL,NULL,'EQ','datetime','',14,'admin','2021-07-13 16:54:46','','2021-07-13 16:56:12'),(171,'12','scale_id','已完成量表','varchar(255)','String','scaleId','0','0',NULL,'1','1','1','1','EQ','input','',15,'admin','2021-07-13 16:54:47','','2021-07-13 16:56:12'),(173,'12','type_flag','任务所属系统（0新冠 1量表）','char(1)','String','typeFlag','0','0',NULL,'1','1','1','1','EQ','input','',16,'admin','2021-07-13 16:54:47','','2021-07-13 16:56:13'),(174,'13','id','表id','int(32)','Long','id','1','1',NULL,'1',NULL,NULL,NULL,'EQ','input','',1,'admin','2021-09-27 16:06:44','','2021-09-27 16:09:49'),(175,'13','scale_name','表名称','varchar(55)','String','scaleName','0','0',NULL,'1','1','1','1','LIKE','input','',2,'admin','2021-09-27 16:06:44','','2021-09-27 16:09:49'),(176,'13','grade','5自评6他评','varchar(32)','String','grade','0','0',NULL,'1','1','1','1','EQ','input','',3,'admin','2021-09-27 16:06:44','','2021-09-27 16:09:49'),(177,'13','pid','父id','int(32)','Long','pid','0','0',NULL,'1','1','1','1','EQ','input','',4,'admin','2021-09-27 16:06:44','','2021-09-27 16:09:49'),(178,'13','apply_scope','适用范围','varchar(32)','String','applyScope','0','0',NULL,'1','1','1','1','EQ','input','',5,'admin','2021-09-27 16:06:44','','2021-09-27 16:09:49'),(179,'13','del_flag','是否删除0存在1删除','varchar(32)','String','delFlag','0','0',NULL,'1',NULL,NULL,NULL,'EQ','input','',6,'admin','2021-09-27 16:06:44','','2021-09-27 16:09:49'),(180,'13','create_time',NULL,'datetime','Date','createTime','0','0',NULL,'1',NULL,NULL,NULL,'EQ','datetime','',7,'admin','2021-09-27 16:06:44','','2021-09-27 16:09:49'),(181,'13','update_time',NULL,'datetime','Date','updateTime','0','0',NULL,'1','1',NULL,NULL,'EQ','datetime','',8,'admin','2021-09-27 16:06:44','','2021-09-27 16:09:49'),(182,'13','remark',NULL,'varchar(255)','String','remark','0','0',NULL,'1','1','1',NULL,'EQ','input','',9,'admin','2021-09-27 16:06:44','','2021-09-27 16:09:49'),(191,'15','id','id','bigint(20)','Long','id','1','1',NULL,'1',NULL,NULL,NULL,'EQ','input','',1,'admin','2021-09-30 11:31:44','','2021-09-30 11:32:35'),(192,'15','scale_id','量表id','bigint(20)','Long','scaleId','0','0',NULL,'1','1','1','1','EQ','input','',2,'admin','2021-09-30 11:31:44','','2021-09-30 11:32:35'),(193,'15','title','题号','varchar(100)','String','title','0','0',NULL,'1','1','1','1','EQ','input','',3,'admin','2021-09-30 11:31:44','','2021-09-30 11:32:35'),(194,'15','content','内容','varchar(255)','String','content','0','0',NULL,'1','1','1','1','EQ','input','',4,'admin','2021-09-30 11:31:44','','2021-09-30 11:32:35'),(195,'15','options','选项','varchar(255)','String','options','0','0',NULL,'1','1','1','1','EQ','input','',5,'admin','2021-09-30 11:31:44','','2021-09-30 11:32:35'),(196,'15','answers','答案','varchar(255)','String','answers','0','0',NULL,'1','1','1','1','EQ','input','',6,'admin','2021-09-30 11:31:44','','2021-09-30 11:32:35'),(197,'15','resolution','问题解析','varchar(255)','String','resolution','0','0',NULL,'1','1','1','1','EQ','input','',7,'admin','2021-09-30 11:31:44','','2021-09-30 11:32:35'),(198,'15','remarks','备注','varchar(255)','String','remarks','0','0',NULL,'1','1','1','1','EQ','input','',8,'admin','2021-09-30 11:31:44','','2021-09-30 11:32:35'),(199,'16','patient_id','患者ID','bigint(50)','Long','patientId','1','1',NULL,'1',NULL,NULL,NULL,'EQ','input','',1,'admin','2021-10-11 09:59:27','','2021-10-11 10:00:27'),(200,'16','patient_name','患者姓名','varchar(30)','String','patientName','0','0','1','1','1','1','1','LIKE','input','',2,'admin','2021-10-11 09:59:27','','2021-10-11 10:00:27'),(201,'16','sex','用户性别（0男 1女 2未知）','char(1)','String','sex','0','0',NULL,'1','1','1','1','EQ','select','',3,'admin','2021-10-11 09:59:27','','2021-10-11 10:00:27'),(202,'16','age','年龄','int(20)','Long','age','0','0',NULL,'1','1','1','1','EQ','input','',4,'admin','2021-10-11 09:59:27','','2021-10-11 10:00:27'),(203,'16','hospital_number','住院号','bigint(50)','Long','hospitalNumber','0','0',NULL,'1','1','1','1','EQ','input','',5,'admin','2021-10-11 09:59:27','','2021-10-11 10:00:27'),(204,'16','ward','病区','varchar(50)','String','ward','0','0',NULL,'1','1','1','1','EQ','input','',6,'admin','2021-10-11 09:59:27','','2021-10-11 10:00:27'),(205,'16','test_date','检测日期','datetime','Date','testDate','0','0',NULL,'1','1','1','1','EQ','datetime','',7,'admin','2021-10-11 09:59:27','','2021-10-11 10:00:27'),(206,'16','job','职业','varchar(20)','String','job','0','0',NULL,'1','1','1','1','EQ','input','',8,'admin','2021-10-11 09:59:28','','2021-10-11 10:00:27'),(207,'16','education','学历','varchar(20)','String','education','0','0',NULL,'1','1','1','1','EQ','input','',9,'admin','2021-10-11 09:59:28','','2021-10-11 10:00:27'),(208,'16','nation','民族','varchar(20)','String','nation','0','0',NULL,'1','1','1','1','EQ','input','',10,'admin','2021-10-11 09:59:28','','2021-10-11 10:00:27'),(209,'16','marital_status','婚姻状况(0未婚,1已婚)','char(1)','String','maritalStatus','0','0',NULL,'1','1','1','1','EQ','radio','',11,'admin','2021-10-11 09:59:28','','2021-10-11 10:00:27'),(210,'16','birthday','出生日期','datetime','Date','birthday','0','0',NULL,'1','1','1','1','EQ','datetime','',12,'admin','2021-10-11 09:59:28','','2021-10-11 10:00:27'),(211,'16','contanct_information','联系方式','varchar(50)','String','contanctInformation','0','0',NULL,'1','1','1','1','EQ','input','',13,'admin','2021-10-11 09:59:28','','2021-10-11 10:00:27'),(212,'16','source','患者来源','varchar(100)','String','source','0','0',NULL,'1','1','1','1','EQ','input','',14,'admin','2021-10-11 09:59:28','','2021-10-11 10:00:27'),(213,'16','classification_coding','分类编码','varchar(100)','String','classificationCoding','0','0',NULL,'1','1','1','1','EQ','input','',15,'admin','2021-10-11 09:59:28','','2021-10-11 10:00:27'),(214,'16','diagnosis','诊断','varchar(50)','String','diagnosis','0','0',NULL,'1','1','1','1','EQ','input','',16,'admin','2021-10-11 09:59:28','','2021-10-11 10:00:27'),(215,'16','del_flag','是否删除(0存在，1删除)','char(1)','String','delFlag','0','0',NULL,'1',NULL,NULL,NULL,'EQ','input','',17,'admin','2021-10-11 09:59:28','','2021-10-11 10:00:27'),(216,'16','create_by','创建者','varchar(64)','String','createBy','0','0',NULL,'1',NULL,NULL,NULL,'EQ','input','',18,'admin','2021-10-11 09:59:28','','2021-10-11 10:00:27'),(217,'16','create_time','创建时间','datetime','Date','createTime','0','0',NULL,'1',NULL,NULL,NULL,'EQ','datetime','',19,'admin','2021-10-11 09:59:28','','2021-10-11 10:00:27'),(218,'16','update_by','更新人','varchar(64)','String','updateBy','0','0',NULL,'1','1',NULL,NULL,'EQ','input','',20,'admin','2021-10-11 09:59:28','','2021-10-11 10:00:27'),(219,'16','update_time','更新时间','datetime','Date','updateTime','0','0',NULL,'1','1',NULL,NULL,'EQ','datetime','',21,'admin','2021-10-11 09:59:28','','2021-10-11 10:00:27'),(220,'17','task_id','任务ID','bigint(20)','Long','taskId','1','1',NULL,'1',NULL,NULL,NULL,'EQ','input','',1,'admin','2021-10-11 09:59:28','','2021-10-11 10:00:50'),(221,'17','user_id','测评人ID','bigint(20)','Long','userId','0','0',NULL,'1','1','1','1','EQ','input','',2,'admin','2021-10-11 09:59:28','','2021-10-11 10:00:50'),(222,'17','user_name','测评人姓名','varchar(50)','String','userName','0','0',NULL,'1','1','1','1','LIKE','input','',3,'admin','2021-10-11 09:59:28','','2021-10-11 10:00:50'),(223,'17','patient_id','患者ID','bigint(20)','Long','patientId','0','0',NULL,'1','1','1','1','EQ','input','',4,'admin','2021-10-11 09:59:28','','2021-10-11 10:00:50'),(224,'17','patient_name','患者名称','varchar(50)','String','patientName','0','0',NULL,'1','1','1','1','LIKE','input','',5,'admin','2021-10-11 09:59:28','','2021-10-11 10:00:50'),(225,'17','workstation','工作站','varchar(50)','String','workstation','0','0',NULL,'1','1','1','1','EQ','input','',6,'admin','2021-10-11 09:59:28','','2021-10-11 10:00:50'),(226,'17','test_coding','测试编码','varchar(100)','String','testCoding','0','0',NULL,'1','1','1','1','EQ','input','',7,'admin','2021-10-11 09:59:28','','2021-10-11 10:00:50'),(227,'17','typeIds','测评任务','varchar(100)','String','typeids','0','0',NULL,'1','1','1','1','EQ','input','',8,'admin','2021-10-11 09:59:28','','2021-10-11 10:00:50'),(228,'17','task_status','任务状态(1未开始,2进行中,3已结束)','char(1)','String','taskStatus','0','0',NULL,'1','1','1','1','EQ','radio','',9,'admin','2021-10-11 09:59:28','','2021-10-11 10:00:50'),(229,'17','del_flag','是否删除(0存在，1删除)','char(1)','String','delFlag','0','0',NULL,'1',NULL,NULL,NULL,'EQ','input','',10,'admin','2021-10-11 09:59:28','','2021-10-11 10:00:50'),(230,'17','create_by','创建者','varchar(64)','String','createBy','0','0',NULL,'1',NULL,NULL,NULL,'EQ','input','',11,'admin','2021-10-11 09:59:28','','2021-10-11 10:00:50'),(231,'17','create_time','创建时间','datetime','Date','createTime','0','0',NULL,'1',NULL,NULL,NULL,'EQ','datetime','',12,'admin','2021-10-11 09:59:28','','2021-10-11 10:00:50'),(232,'17','update_by','更新者','varchar(64)','String','updateBy','0','0',NULL,'1','1',NULL,NULL,'EQ','input','',13,'admin','2021-10-11 09:59:28','','2021-10-11 10:00:50'),(233,'17','update_time','更新时间','datetime','Date','updateTime','0','0',NULL,'1','1',NULL,NULL,'EQ','datetime','',14,'admin','2021-10-11 09:59:28','','2021-10-11 10:00:50'),(234,'17','scale_id','已完成量表','varchar(255)','String','scaleId','0','0',NULL,'1','1','1','1','EQ','input','',15,'admin','2021-10-11 09:59:28','','2021-10-11 10:00:50'),(235,'17','type_flag','任务所属系统（0新冠 1量表）','char(1)','String','typeFlag','0','0',NULL,'1','1','1','1','EQ','input','',16,'admin','2021-10-11 09:59:28','','2021-10-11 10:00:50'),(236,'18','id','id','int(32)','Long','id','1','1',NULL,'1',NULL,NULL,NULL,'EQ','input','',1,'admin','2021-10-11 09:59:28','','2021-10-11 10:01:14'),(237,'18','scale_id','量表名字','int(32)','Long','scaleId','0','0',NULL,'1','1','1','1','EQ','input','',2,'admin','2021-10-11 09:59:28','','2021-10-11 10:01:14'),(238,'18','task_id','任务id','int(32)','Long','taskId','0','0',NULL,'1','1','1','1','EQ','input','',3,'admin','2021-10-11 09:59:28','','2021-10-11 10:01:14'),(239,'18','patient_id','病人id','int(32)','Long','patientId','0','0',NULL,'1','1','1','1','EQ','input','',4,'admin','2021-10-11 09:59:28','','2021-10-11 10:01:14'),(240,'18','score','得分','int(32)','Long','score','0','0',NULL,'1','1','1','1','EQ','input','',5,'admin','2021-10-11 09:59:28','','2021-10-11 10:01:14'),(241,'18','workstation','工作站','varchar(32)','String','workstation','0','0',NULL,'1','1','1','1','EQ','input','',6,'admin','2021-10-11 09:59:28','','2021-10-11 10:01:14'),(242,'18','test_date','测试日期','date','Date','testDate','0','0',NULL,'1','1','1','1','EQ','datetime','',7,'admin','2021-10-11 09:59:28','','2021-10-11 10:01:14'),(243,'19','id','id','int(32)','Long','id','1','1',NULL,'1',NULL,NULL,NULL,'EQ','input','',1,'admin','2021-10-11 15:17:34','','2021-10-11 15:18:25'),(244,'19','task_id','任务id','int(32)','Long','taskId','0','0',NULL,'1','1','1','1','EQ','input','',2,'admin','2021-10-11 15:17:34','','2021-10-11 15:18:25'),(245,'19','scale_id','量表id','int(32)','Long','scaleId','0','0',NULL,'1','1','1','1','EQ','input','',3,'admin','2021-10-11 15:17:34','','2021-10-11 15:18:25'),(246,'19','title','题号','varchar(100)','String','title','0','0',NULL,'1','1','1','1','EQ','input','',4,'admin','2021-10-11 15:17:34','','2021-10-11 15:18:25'),(247,'19','score','得分','int(32)','Long','score','0','0',NULL,'1','1','1','1','EQ','input','',5,'admin','2021-10-11 15:17:34','','2021-10-11 15:18:25');

/*Table structure for table `ipa_media` */

DROP TABLE IF EXISTS `ipa_media`;

CREATE TABLE `ipa_media` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文件id',
  `title` varchar(32) DEFAULT NULL COMMENT '文件名',
  `local` varchar(64) DEFAULT NULL COMMENT '位置',
  `mark` varchar(32) DEFAULT NULL COMMENT '对应的哪天',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COMMENT='视频地址表';

/*Data for the table `ipa_media` */

insert  into `ipa_media`(`id`,`title`,`local`,`mark`) values (1,NULL,'0.mp4','48'),(2,NULL,'1.mp4','49'),(3,NULL,'2.mp4','50'),(4,NULL,'3.mp4','51'),(5,NULL,'4.mp4','52'),(6,NULL,'5.mp4','53'),(7,NULL,'6.mp4','54'),(8,NULL,'11.mp4','0'),(9,NULL,'12.mp4','0'),(10,NULL,'13.mp4','0'),(11,NULL,'14.mp4','0'),(12,NULL,'15.mp4','0'),(13,NULL,'心理评估首页告知语音.mp3','8'),(14,NULL,'认知治疗配音文件.mp3','9'),(15,NULL,'认知巩固问题回答语音.mp3','10'),(16,'1','1_手术前您准备好了么？.mp4','63'),(17,'2','1_手术室.mp4','63'),(18,'1','2_第2阶段 开启手术之旅.mp4','64'),(19,'1','3_术后康复之疼痛管理篇.mp4','65'),(20,'2','3_术后康复之预防血栓篇.mp4','65'),(21,'1','4_康复之活动篇：早期活动.mp4','66'),(22,'2','4_康复之睡眠管理篇.mp4','66'),(23,'3','4_康复之早期进食.mp4','66'),(24,'1','5_被动关节活动.mp4','67'),(25,'2','5_踝泵运动.mp4','67'),(26,'3','5_主动关节活动.mp4','67'),(27,'1','6_出院后的注意事项.mp4','68'),(28,'3','放松训练1.mp4','63'),(29,'2','放松训练2.mp4','64'),(30,'3','放松训练3.mp4','65'),(31,'4','放松训练4.mp4','66'),(32,'4','放松训练5.mp4','67'),(33,'2','放松训练6.mp4','68');

/*Table structure for table `ipa_patient_user` */

DROP TABLE IF EXISTS `ipa_patient_user`;

CREATE TABLE `ipa_patient_user` (
  `patient_id` bigint(50) NOT NULL AUTO_INCREMENT COMMENT '患者ID',
  `patient_name` varchar(30) NOT NULL COMMENT '患者姓名',
  `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `age` int(20) DEFAULT NULL COMMENT '年龄',
  `hospital_number` bigint(50) DEFAULT NULL COMMENT '住院号',
  `ward` varchar(50) DEFAULT NULL COMMENT '病区',
  `test_date` datetime DEFAULT NULL COMMENT '检测日期',
  `job` varchar(20) DEFAULT NULL COMMENT '职业',
  `education` varchar(20) DEFAULT NULL COMMENT '学历',
  `nation` varchar(20) DEFAULT NULL COMMENT '民族',
  `marital_status` char(1) DEFAULT NULL COMMENT '婚姻状况(0未婚,1已婚)',
  `birthday` datetime DEFAULT NULL COMMENT '出生日期',
  `contanct_information` varchar(50) DEFAULT NULL COMMENT '联系方式',
  `source` varchar(100) DEFAULT NULL COMMENT '患者来源',
  `classification_coding` varchar(100) DEFAULT NULL COMMENT '分类编码',
  `diagnosis` varchar(50) DEFAULT NULL COMMENT '诊断',
  `del_flag` char(1) DEFAULT '0' COMMENT '是否删除(0存在，1删除)',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`patient_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='心身调节系统患者表';

/*Data for the table `ipa_patient_user` */

insert  into `ipa_patient_user`(`patient_id`,`patient_name`,`sex`,`age`,`hospital_number`,`ward`,`test_date`,`job`,`education`,`nation`,`marital_status`,`birthday`,`contanct_information`,`source`,`classification_coding`,`diagnosis`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`) values (15,'张','0',24,1022,'362',NULL,'军人','高中','蒙古族','1','1997-05-14 08:00:00','1523256310','门诊','待诊   ','抑郁症','0',NULL,'2021-07-05 10:17:47',NULL,'2021-07-14 15:44:18'),(17,'王五','0',24,1234,'1234',NULL,'学生','本科','汉族','0','1996-07-25 08:00:00','18565244587','门诊','正常','焦虑症','0',NULL,'2021-07-14 11:45:58',NULL,NULL);

/*Table structure for table `ipa_question` */

DROP TABLE IF EXISTS `ipa_question`;

CREATE TABLE `ipa_question` (
  `que_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID列',
  `title` varchar(32) DEFAULT NULL COMMENT '题号',
  `day` int(11) DEFAULT NULL COMMENT '对应的第几天',
  `question` varchar(255) DEFAULT NULL COMMENT '问题描述',
  `option_a` varchar(32) DEFAULT NULL COMMENT '选项a',
  `option_b` varchar(32) DEFAULT NULL COMMENT '选项b',
  `resolution` varchar(255) DEFAULT NULL COMMENT '问题解析',
  `answer` varchar(32) DEFAULT NULL COMMENT '正确答案',
  PRIMARY KEY (`que_id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8 COMMENT='问题表';

/*Data for the table `ipa_question` */

insert  into `ipa_question`(`que_id`,`title`,`day`,`question`,`option_a`,`option_b`,`resolution`,`answer`) values (1,'1',48,'疑似病例或有过接触的史的人群其隔离的时间为','7天','14天','潜伏期为1~14天，多为3~7天，潜伏期内可能存在传染性。因此，有过接触史的人群往往需要隔离14天。','14天'),(2,'2',48,'下列哪些能有效灭活此次病毒','75%乙醇','氯己定','病毒对紫外线和热敏感，56℃30分钟、乙醚、75%乙醇、含氯消毒剂、过氧乙酸和氯仿均可有效灭活病毒，但请注意氯己定不能有效灭活病毒。','75%乙醇'),(3,'3',48,'新型冠状病毒肺炎患者典型的胸片表现为','呈肺叶、段分布的片状浸润影','双肺多发磨玻璃影、浸润影','胸部CT检查早期呈现多发的小斑片影及间质改变，以肺外带明显，进而发展为双肺多发磨玻璃影、浸润影，严重者可出现肺实变。俗称“大白肺”','双肺多发磨玻璃影、浸润影'),(4,'4',48,'野生动物煮熟后可以放心吃','对','错','研究发现该病毒的宿主为野生动物，可能是中华菊头蝠，中间宿主可能为穿山甲，很多野生动物都可能携带病原体，成为某些传染病的传播介质，果子狸、蝙蝠、竹鼠、獾等是冠状病毒的常见宿主。所以不要吃野生动物、未经检疫的生鲜等食品','错'),(5,'5',48,'此次疫情多数患者预后良好，少数患者病情严重','对','错','从目前收治的病例情况看，多数患者预后良好，儿童病例相对较轻，少数患者病情危重。死亡患者常见于老年人和伴有慢性基础疾病者','对'),(6,'6',48,'可以用氯己定灭活新冠病毒','对','错','病毒对紫外线和热敏感，56℃30分钟、乙醚、75%乙醇、含氯消毒剂、过氧乙酸和氯仿均可有效灭活病毒，但请注意氯己定不能有效灭活病毒。','错'),(7,'7',48,'多数轻症感染患者的临床表现为','以发热、乏力、干咳为主要表现','呼吸困难和/或低氧血症、急性呼吸窘迫综合征','新型冠状病毒感染后以发热、乏力、干咳为主要表现，重症患者多在发病一周后出现呼吸困难和/或低氧血症，严重者快速进展为急性呼吸窘迫综合征、脓毒症休克等','以发热、乏力、干咳为主要表现'),(8,'8',48,'无症状感染者是否具有传染性','是','否','此次病毒的传染源主要是新型冠状病毒感染的肺炎患者，无症状感染者也具有传染性。','是'),(9,'9',48,'有发热症状就一定是感染了新冠病毒','对','错','发热虽然是新冠肺炎的主要症状，但是需要注意的是感冒、流感、伤口感染等也会导致发热。','错'),(10,'10',48,'潜伏期内没有传染性','对','错','潜伏期为1~14天，多为3~7天，潜伏期内可能存在传染性。','错'),(11,'1',49,'普通感冒一般是否伴有全身症状','是','否','普通感冒一般很少会有全身肌肉疼痛或者乏力等全身症状。','否'),(12,'2',49,'普通感冒、流感、新冠病毒肺炎都伴有全身症状','对','错','普通感冒一般很少会有全身肌肉疼痛或者乏力等全身症状；流感最主要的是常常伴有全身症状，包括全身肌肉疼痛、乏力等情况：而新型冠状病毒肺炎全身症状表现为精神差、食欲差、全身乏力。','错'),(13,'3',49,'疑似病例经实验室检查后被确诊，才转为确诊病例','对','错','疑似病例是根据某传染病所表现得临床症状和流行病学史进行诊断，而没有进行实验室检查，也就是临床诊断病例。如果实验室检查后被确诊，则转为确诊病例。','对'),(14,'4',49,'湖北省内，临床诊断病例即使没有实验室检查证据，只要疑似病例具有肺炎影像学特征者就纳入确诊病例统计中。','对','错','随着对疾病认识的深入，湖北省内临床诊断病例数纳入确诊病例统计。湖北省内临床诊断病例即使没有实验室病原学检查证据，只要疑似病例具有肺炎影像学特征者纳入确诊病例统计','对'),(15,'5',49,'呼吸困难和急促属于下列哪种疾病的呼吸道表现','普通感冒','病毒性肺炎','病毒性肺炎呼吸频率加快，甚至呼吸困难。普通感冒一般很少会有全身肌肉疼痛或者乏力等全身症状，一般不引起胸痛、呼吸困难等肺炎症状。','病毒性肺炎'),(16,'6',49,'发病前7天有武汉市及周边地区旅行史或居住史，或与新型冠状病毒感染者有接触史者等属于此次肺炎的流行病学史','对','错','新型冠状病毒肺炎的流行病学为：发病前14天内有武汉市及周边地区，或其他有病例报告社区的旅行史或居住史；发病前14天内与新型冠状病毒感染者有接触史；发病前14天曾接触过来自武汉市及周边地区，或来自有病例报告社区的发热或有呼吸道症状的患者；聚集性发病；14天内在小范围内发现2例及以上确诊病例，且病例间存在因密切接触导致的人际传播可能性或因共同暴露而感染的可能性。','对'),(17,'7',49,'下列哪项是普通感冒的潜伏期','1~3天','1~14天','普通感冒潜伏期1~3天，新型冠状病毒肺炎潜伏期1~14天，平均7天。','1~3天'),(18,'8',49,'新冠肺炎的鉴别诊断，除了需要临床症状、实验室和影像学检查外，还要结合','流行病学史，如有无旅居武汉史、有无接触史','家族史','新冠肺炎的诊断很大程度要结合流行病学史，如有无旅居武汉史、有无接触史等，因此如果怀疑自己为疑似病例，就医时一定如实提供病史。再结合临床症状，实验室和影像学检查，可以帮助您很快确诊或排除新冠肺炎。','流行病学史，如有无旅居武汉史、有无接触史'),(19,'9',49,'普通感冒发热用退烧药效果最好','对','错','与新冠肺炎相比，普通感冒中低度发热，一般持续48~72小时，退烧药效果较好。','对'),(20,'10',49,'普通感冒和流感都可以自愈','对','错','普通感冒基本3~5天就可以自愈；流感症状主要表现为发热、头痛、肌肉痛和全身不适，体温可达39~40度，这种热程相对比较长，大概在一周左右能治愈。','对'),(21,'1',50,'当出现明显的情绪不稳定，紧张，恐惧或悲伤、抑郁时，你应该','自己忍受，终会过去','向心理医生寻求帮助','轻度的心理问题可以通过自我调节缓解，但当自己无法解决时，寻求专业的心理医生是明智之举。','向心理医生寻求帮助'),(22,'2',50,'隔离期间出现的失眠，你认为的影响因素是','情绪','疼痛','过度的心理应激反应会严重影响到睡眠质量','情绪'),(23,'3',50,'当确诊为新型冠状病毒肺炎时，患者出现听天由命、消极被动的行为时，属于哪种行为改变？','敌对与攻击行为','无助与自怜','当确诊为新型冠状病毒肺炎时，患者出现听天由命、消极被动的行为时，属于无助与自怜行为','无助与自怜'),(24,'4',50,'隔离治疗期，患者会出现哪些心理反应','孤独感加重、敏感、恐惧、多疑','心情比较稳定','隔离治疗期，患者会出现孤独感加重、敏感、恐惧、多疑等心理反应。','孤独感加重、敏感、恐惧、多疑'),(25,'5',50,'有些躯体症状受心理和情绪的影响很大，应该引起关注','对','错','过度应激反应对心身的负面影响有 引起呼吸系统疾病、诱发精神疾病、诱发高血压病或冠心病、消化系统疾病。','对'),(26,'6',50,'自我应激反应过度的表现有','睡眠尚可，情绪低落','长时间的睡眠困扰，如入睡困难、多梦、梦魇','连续3天或更长时间的睡眠困扰，如入睡困难、多梦、梦魇、早醒等。明显的身体不适感，主要表现为身体紧张，不易放松，食欲不振，尿频，尿急心慌等。','长时间的睡眠困扰，如入睡困难、多梦、梦魇'),(27,'7',50,'人的情绪反应会影响人的认知','对','错','强烈的应激会激发人负面的认知反应。如偏执，灾难化，强迫思维。','对'),(28,'8',50,'当出现气紧、胸闷、干咳，你会考虑可能是情绪紧张导致的吗','会','不会','过度应激反应可能会引起呼吸系统疾病:可能诱发哮喘发作，出现气道内异物感、气道紧迫感、气紧、胸闷、干咳等症状，容易与新冠肺炎的症状混肴，加重焦虑情绪，不利于治疗。','会'),(29,'9',50,'“这种传染病太可怕了，我肯定治不好了”这种想法是过分夸大不良认知','对','错','不良认知的表现，灾难化认知：表现为过度夸大应激时间的潜在和消极后果。','对'),(30,'10',50,'确诊患者不能出现大喜大悲的情绪，否则会加重病情。','对','错','确诊患者要可以适当表达情绪，但最重要的是接纳和保持心态平稳，才有利于病情康复。','对'),(31,'1',51,'病毒从蝙蝠到人的传播可能存在多个宿主，所以不要使用未经检疫的肉类','对','错','很多野生动物都可能携带病原体，成为某些传染病的传播介质，果子狸、蝙蝠、竹鼠、獾等是冠状病毒的常见宿主。所以不要吃野生动物及未尽检疫的肉类。','对'),(32,'2',51,'家里的宠物猫宠物狗会感染病毒吗','会','不会','目前没有证据表明猫狗会感染此次冠状病毒，但在疫情中，建议在遛狗后给狗狗彻底洗澡，减少宠物出门。','不会'),(33,'3',51,'目前认为新型冠状病毒可以持续人传人。','对','错','根据当前现状可见，新型冠状病毒存在人传人的情况，而且不仅是确诊患者，潜伏期患者也是传染源。','对'),(34,'4',51,'新型冠状病毒的传播途径只有直接传播和接触传播两种。','对','错','中国疾控中心流行病学首席专家吴尊友表示，从理论上看，气溶胶传播新冠肺炎是有可能的，但对其流行的影响有限。','错'),(35,'5',51,'下列哪种动物不是冠状病毒常见的宿主','蚊子','蝙蝠','很多野生动物都可能携带病原体，成为某些传染病的传播介质，果子狸、蝙蝠、竹鼠、獾等是冠状病毒的常见宿主。','蚊子'),(36,'6',51,'控制新型冠状病毒传播的主要措施是','对确诊患者进行隔离','保护易感人群','传染病要想在人群中流行起来，必须同时具有传染源、传播途径、易感人群三个环节。而针对这次传染性较强的新型冠状病毒来说，主要的措施是对确诊患者的隔离治疗，对疑似患者隔离观察以切断传染源。此次武汉市采取封城的举措也是基于要尽可能的切断传染源。','对确诊患者进行隔离'),(37,'7',51,'确诊患者进行医院隔离可起到保护易感人群的目的。','对','错','传染病要想在人群中流行起来，必须同时具有传染源、传播途径、易感人群三个环节。对确诊患者进行集中的隔离治疗，间接的切断了传播途径，可以达到保护易感人群的目的。','对'),(38,'8',51,'新型冠状病毒有14天的潜伏期，在潜伏期内可能传染其他人。','对','错','根据目前的发病情况看，感染了新型冠状病毒的感染者，在潜伏期是有传染性的。传染性最强的时期，可能正是在潜伏期后期到症状最严重这一时期，一方面是因为这一期间病毒活性最高、数量最多。同时，此阶段咳嗽症状严重，故排出病原体也较多。','对'),(39,'9',51,'关于飞沫传播，以下说法不正确的是','医用类口罩不能阻挡飞沫传播','说话、咳嗽、打喷嚏都可以造成飞沫','飞沫通常是由说话、咳嗽、打喷嚏造成的。飞沫可以通过一定的距离（一般是一米）进入易感的黏膜表面，且颗粒较大，不会长时间悬浮在空气中。选择正确的医用口罩可以有效隔绝飞沫传播。','医用类口罩不能阻挡飞沫传播'),(40,'10',51,'肺炎隔离的主要目的是','控制传染源','切断传播途径','传染病要想在人群中流行起来，必须同时具有传染源、传播途径、易感人群三个环节。肺炎隔离的主要目的是隔离传染源，也是目前控制传染病传播最有效的措施。','控制传染源'),(41,'1',52,'一时的心理压力过大不会对身体产生影响','对','错','心理压力会调动呼吸、心血管、内分泌和免疫等系统发生一系列变化以应对生存威胁，在短期内更好的进行“战斗”或“逃跑”。','错'),(42,'2',52,'不合理的认知能否通过训练被纠正','能','不能','不合理的认知可能通过训练被纠正，引入良好的行为习惯，就可以逐渐改变大脑结构和功能，维护和控制好情绪。','能'),(43,'3',52,'当出现血压升高、心慌、失眠、尿频等症状时，不会考虑是心理的因素。','对','错','负性的情绪反应会引起躯体反应，进而出现血压升高、心率加快、口干、出汗、尿频等身体反应。','错'),(44,'4',52,'有些躯体症状受心理和情绪的影响很大，应该引起关注','对','错','过度应激反应可能会引起呼吸系统疾病、诱发精神疾病、诱发高血压病或冠心病、消化系统疾病。','对'),(45,'5',52,'在治疗隔离期间，大部分人会出现的想法是','积极','消极','在治疗隔离期间，面对突然的应激压力，大部分人都会出现以一些消极想法。','消极'),(46,'6',52,'面对疫情，哪些措施有助于心理调节？','远离不良信息','不断查阅疫情','面对疫情，远离不良信息有助于心理调节。','远离不良信息'),(47,'7',52,'以下哪个属于隔离患者自助心理干预？','积极关注疫情动态','远离不良信息','远离不良信息：从正规渠道、官方网站获取相关信息，掌握必要的相关防疫措施和知识即可，避免接触带有各种强烈情绪色彩的信息，在心理恐慌的情况下，适当的“充耳不闻”是可取的。','远离不良信息'),(48,'8',52,'过度的焦虑和压力下，皮质醇激素会增多，对身体是不利的。','对','错','过度的焦虑会消耗体内大量的葡萄糖，产生大量的压力激素皮质醇，血糖降低，一些帮助人们开心、增加活力、稳定清晰、帮助睡眠的激素生成减少，负面情绪继续增多。','对'),(49,'9',52,'在非常紧张的时候，正念冥想、呼吸放松都有助于缓解紧张和焦虑。','对','错','专业的认知行为治疗可以提供放松疗法帮助缓解紧张焦虑的情绪。','对'),(50,'10',52,'疫情带来的心理压力会影响身体康复，','对','错','疫情带来的心理压力会产生相应的身体反应，降低人的免疫力，最终影响康复。','对'),(51,'1',53,'根据不同的发病阶段，辨证论治所采取的治疗措施是','中医治疗','营养治疗','中医治疗是中国特色，在临床诊疗中取得良好的诊疗效果。本病属于中医疫病范畴，病因为受疫戾之气，各地可根据病情、当地气候特点以及不同体质等情况，进行辨证论治。处于临床治疗期的患者，根据不同的发病阶段，有不同的中药处方或中成药试剂推荐治疗。','中医治疗'),(52,'2',53,'临床症状较轻的患者，影像学没有肺炎的表现','对','错','目前很多轻症的新型冠状肺炎感染患者，仅存在乏力、低热的症状，病毒还没有累计到肺实质，因此并没有肺部实质性病变，所以影像学检查也没有明显异常。','对'),(53,'3',53,'目前针对新型冠状病毒肺炎感染者的治疗主要为对症治疗','对','错','治疗原则是在对症治疗的基础上，积极防治并发症，治疗基础疾病吗，预防继发感染，及时进行器官功能支持。','对'),(54,'4',53,'若近日出现发热、乏力等症状，自行吃感冒药后无效，应','到发热门诊就诊','到普通门诊就诊','出现发热的症状服用感冒药未缓解就应到指定医院的发热门诊就诊，有可能是新型冠状病毒感染，不要到普通门诊就诊，避免在医院内交叉感染传给其他人。','到发热门诊就诊'),(55,'5',53,'疑似病例在定点医院隔离治疗时，应','单人单间隔离','收治在同一病房','对疑似病例的排查需要更严格的隔离条件，每人一个单间，保证没有一个人在隔离期间被交叉感染，不可多人同室。','单人单间隔离'),(56,'6',53,'采用鼻导管氧疗或无创通气的患者，若短时间病情无改善吗，应','继续观察','及时进行气管插管和有创机械通气','重型患者应当接受鼻导管或面罩吸氧，及时评估呼吸窘迫和（或）低氧血症是否缓解。当患者无法缓解时，可考虑使用高流量鼻导管氧疗或无创通气。若短时间（1-2小时）内病情无改善甚至恶化，应当及时进行气管插管和有创机械通气。','及时进行气管插管和有创机械通气'),(57,'7',53,'确诊病例在定点医院隔离治疗时，可以多人收治在同一病室。','对','错','对于确诊的患者，由于不存在交叉感染的风险，所以可以将多个确诊患者收治在同一病室。','对'),(58,'8',53,'不同的人感染新型冠状病毒，症状都一样','对','错','虽然人群普遍易感，但是症状有所不同，老年人、有慢性病或基础疾病的人症状较重；轻症患者可以仅表现为乏力，低热；还有一些病人并没有呼吸症状的改变，表现为消化道症状。','错'),(59,'9',53,'新型冠状病毒肺炎要按照（）管理','甲类传染病','乙类传染病','国家卫健委发布2020年1号公告，将新型冠状病毒感染的肺炎纳入《中华人民共和国传染病防治法》规定的乙类传染病，并采取甲类传染病的预防、控制措施。','甲类传染病'),(60,'10',53,'若出现呼吸衰竭，且需要机械通气的情况，属于危重型感染患者','对','错','重型感染患者符合下列任何一条：1呼吸窘迫，呼吸频率>30次/分；2静息状态下，手指血氧饱和度≤93%；3动脉血样分压/吸氧浓度≤300毫米汞柱。危重型感染患者符合以下情况之一者：1出现呼吸衰竭，且需要机械通气；2出现休克；3合并其他器官功能衰竭需ICU监护治疗。','对'),(61,'1',54,'当家人出现呼吸道疾病症状（如发热、咳嗽或打喷嚏等），要避免与其亲密接触，必要时劝其及时就近就医。','对','错','预防新型冠状病毒，要尽可能避免与有呼吸道疾病症状（如发热、咳嗽或打喷嚏等）的人亲密接触；同时要密切关注，出现此类症状一定要及时就近就医。','对'),(62,'2',54,'适度的体育锻炼，保证充足的睡眠都是增强身体抵抗力的措施','对','错','适度体育锻炼，保证充足的睡眠都可以增强身体抵抗力。','对'),(63,'3',54,'新型冠状病毒肺炎患者康复后体内会含有大量综合抗体，能够对抗新冠肺炎病毒','对','错','患者康复后体内含有大量综合抗体，能够对抗病毒。','对'),(64,'4',54,'解除隔离出院的标准有（）','体温恢复正常','连续两次核酸检测阴性','体温恢复正常3天以上、呼吸道症状明显好转，肺部影像学显示炎症明显吸收，连续两次呼吸道病原核酸检测阴性（采样间隔时间至少1天），就可以接触隔离出院或根据病情转至相应科室治疗其他疾病。','连续两次核酸检测阴性'),(65,'5',54,'戴多层口罩会更好的预防新型冠状病毒肺炎。','对','错','戴多层口罩可能造成呼吸不畅，不需要佩戴多层口罩。建议选用N95/KN95或者普通外科口罩，并且一层就够，其他口罩防护效果不如这三种。佩戴多层口罩还可能造成呼吸不畅。','错'),(66,'6',54,'新型冠状病毒肺炎确诊患者和疑似患者医疗费用中，各类医保报销外剩余的费用由谁支付？','个人','国家','目前，新型冠状病毒肺炎确诊患者和疑似患者医疗费用个人负担部分均实施财政全额补助政策，避免患者因费用问题延误就医。1','国家'),(67,'7',54,'当有发热、咳嗽等症状出现时，如果不严重可以不去医院，自行在家服药','对','错','密切关注发热、咳嗽等症状，出现此类症状一定要及时就近就医。','错'),(68,'8',54,'成人正常体温（腋温）为（）','36-37.5℃','36-37℃','成人的正常体温是 腋下温度36-37℃，口腔温度36.3-37.2℃，直肠温度36.6-37.5℃。','36-37℃'),(69,'9',54,'解除隔离标准中，患者体温正常需要（）','3天以上','4天以上','体温恢复正常3天以上、呼吸道症状明显好转，肺部影像学显示炎症明显吸收，连续两次呼吸道病原核酸检测阴性（采样间隔时间至少1天），就可以接触隔离出院或根据病情转至相应科室治疗其他疾病。','3天以上'),(70,'10',54,'所有肉类只要煮熟、煮透，都对身体是无害的','对','错','坚持安全的饮食习惯，营养均衡，使用肉类蛋类均要煮熟、煮透；不食用野生动物和未经检疫的生鲜食品。','错');

/*Table structure for table `ipa_scale_questions` */

DROP TABLE IF EXISTS `ipa_scale_questions`;

CREATE TABLE `ipa_scale_questions` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `scale_id` int(32) DEFAULT NULL COMMENT '量表id',
  `title` varchar(32) DEFAULT NULL COMMENT '题号',
  `content` varchar(64) DEFAULT NULL COMMENT '问题描述',
  `answer_a` varchar(32) DEFAULT NULL COMMENT '选项a',
  `answer_b` varchar(32) DEFAULT NULL COMMENT '选项b',
  `answer_c` varchar(32) DEFAULT NULL COMMENT '选项c',
  `answer_d` varchar(32) DEFAULT NULL COMMENT '选项d',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COMMENT='身心调节-量表-所有题目';

/*Data for the table `ipa_scale_questions` */

insert  into `ipa_scale_questions`(`id`,`scale_id`,`title`,`content`,`answer_a`,`answer_b`,`answer_c`,`answer_d`) values (1,127,'1','我觉得比平常容易紧张或着急','1','2','3','4'),(2,127,'2','我无缘无故的感到害怕','1','2','3','4'),(3,127,'3','我容易心里烦乱或觉得惊恐','1','2','3','4'),(4,127,'4','我觉得我可能将要发疯','1','2','3','4'),(5,127,'5','我觉得一切都很好，也不会发生什么不幸','4','3','2','1'),(6,127,'6','我手脚发抖打颤','1','2','3','4'),(7,127,'7','我因为头痛、颈痛和背痛而苦恼','1','2','3','4'),(8,127,'8','我感觉容易衰弱和疲乏','1','2','3','4'),(9,127,'9','我心平气和，并且容易安静坐着','4','3','2','1'),(10,127,'10','我觉得心跳的很快','1','2','3','4'),(11,127,'11','我因为一阵头晕而苦恼','1','2','3','4'),(12,127,'12','我有晕倒发作，或觉得要晕倒似的','1','2','3','4'),(13,127,'13','我吸气呼气都感到很容易','4','3','2','1'),(14,127,'14','我的手脚麻木和刺痛','1','2','3','4'),(15,127,'15','我因为胃痛和消化不良而苦恼','1','2','3','4'),(16,127,'16','我常常要小便','1','2','3','4'),(17,127,'17','我的手脚常常是干燥温暖的','4','3','2','1'),(18,127,'18','我脸红发热','1','2','3','4'),(19,127,'19','我容易入睡并且一夜睡得很好','4','3','2','1'),(20,127,'20','我做噩梦','1','2','3','4'),(21,128,'1','我觉得闷闷不乐，情绪低沉','1','2','3','4'),(22,128,'2','我觉得一天之中早晨最好','4','3','2','1'),(23,128,'3','我一阵阵地哭出来或是想哭','1','2','3','4'),(24,128,'4','我晚上睡眠不好','1','2','3','4'),(25,128,'5','我的胃口跟以前一样','4','3','2','1'),(26,128,'6','我跟异性交往时像以前一样开心','4','3','2','1'),(27,128,'7','我发现自己体重下降','1','2','3','4'),(28,128,'8','我有便秘的烦恼','1','2','3','4'),(29,128,'9','我的心跳比平时快','1','2','3','4'),(30,128,'10','我无缘无故感到疲劳','1','2','3','4'),(31,128,'11','我的头脑像往常一样清楚','4','3','2','1'),(32,128,'12','我觉得经常做的事情并没有困难','4','3','2','1'),(33,128,'13','我感到不安，心情难以平静','1','2','3','4'),(34,128,'14','我对未来抱有希望','4','3','2','1'),(35,128,'15','我比以前更容易生气激动','1','2','3','4'),(36,128,'16','我觉得决定什么事很容易','4','3','2','1'),(37,128,'17','我觉得自己是个有用的人，有人需要我','4','3','2','1'),(38,128,'18','我的生活过得很有意思','4','3','2','1'),(39,128,'19','假如我死了，别人会过得更好','1','2','3','4'),(40,128,'20','平常感兴趣的事情我照样感兴趣','4','3','2','1'),(41,129,'1','入睡时间（关灯后到睡着的时间）','没问题','轻微延迟','显著延迟','延迟严重或没有睡觉'),(42,129,'2','夜间苏醒','没问题','轻微影响','显著影响','严重影响或没有睡觉'),(43,129,'3','比期望的时间早醒','没问题 ','轻微提早','显著提早','严重提早或没有睡觉'),(44,129,'4','总睡眠时间','足够','轻微不足','显著不足','严重不足或没有睡觉'),(45,129,'5','总睡眠质量（无论睡多长）','满意','轻微不满','显著不满','严重不满或没有睡觉'),(46,129,'6','白天情绪','正常','轻微低落','显著低落','严重低落'),(47,129,'7','白天身体功能（体力或精神：如记忆力、认知力和注意力等）','足够','轻微影响','显著影响','严重影响'),(48,129,'8','白天嗜睡','无嗜睡','轻微嗜睡','显著嗜睡','严重嗜睡');

/*Table structure for table `ipa_task` */

DROP TABLE IF EXISTS `ipa_task`;

CREATE TABLE `ipa_task` (
  `task_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '测评人ID',
  `user_name` varchar(50) DEFAULT NULL COMMENT '测评人姓名',
  `patient_id` bigint(20) DEFAULT NULL COMMENT '患者ID',
  `patient_name` varchar(50) DEFAULT NULL COMMENT '患者名称',
  `workstation` varchar(50) DEFAULT NULL COMMENT '工作站',
  `test_coding` varchar(100) DEFAULT NULL COMMENT '测试编码',
  `typeIds` varchar(100) DEFAULT NULL COMMENT '测评任务',
  `task_status` char(1) DEFAULT NULL COMMENT '任务状态(1未开始,2进行中,3已结束)',
  `del_flag` char(1) DEFAULT NULL COMMENT '是否删除(0存在，1删除)',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `scale_id` varchar(255) DEFAULT NULL COMMENT '已完成量表',
  `type_flag` char(1) DEFAULT NULL COMMENT '任务所属系统（0新冠 1量表）',
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 COMMENT='智能化心身调节任务表';

/*Data for the table `ipa_task` */

insert  into `ipa_task`(`task_id`,`user_id`,`user_name`,`patient_id`,`patient_name`,`workstation`,`test_coding`,`typeIds`,`task_status`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`,`scale_id`,`type_flag`) values (46,1,'admin',15,'张','C001','789096','48','3','1','admin','2021-07-22 18:05:27',NULL,NULL,NULL,'0');

/*Table structure for table `ipa_task_score` */

DROP TABLE IF EXISTS `ipa_task_score`;

CREATE TABLE `ipa_task_score` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `scale_id` int(32) DEFAULT NULL COMMENT '量表名字',
  `task_id` int(32) DEFAULT NULL COMMENT '任务id',
  `patient_id` int(32) DEFAULT NULL COMMENT '病人id',
  `score` int(32) DEFAULT NULL COMMENT '得分',
  `workstation` varchar(32) DEFAULT NULL COMMENT '工作站',
  `test_date` date DEFAULT '0000-00-00' COMMENT '测试日期',
  `type_ids` varchar(32) DEFAULT NULL COMMENT '对应的第几天',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8 COMMENT='ipa任务得分保存表';

/*Data for the table `ipa_task_score` */

insert  into `ipa_task_score`(`id`,`scale_id`,`task_id`,`patient_id`,`score`,`workstation`,`test_date`,`type_ids`) values (67,NULL,46,15,6,'C001','2021-07-22','48');

/*Table structure for table `iric_log` */

DROP TABLE IF EXISTS `iric_log`;

CREATE TABLE `iric_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志id',
  `patient_id` bigint(20) DEFAULT NULL COMMENT '患者id',
  `patient_name` varchar(100) DEFAULT NULL COMMENT '患者名称',
  `sex` char(1) DEFAULT NULL COMMENT '性别',
  `age` int(20) DEFAULT NULL COMMENT '年龄',
  `education` varchar(20) DEFAULT NULL COMMENT '文化程度',
  `job` varchar(20) DEFAULT NULL COMMENT '职业',
  `diagnosis` varchar(50) DEFAULT NULL COMMENT '诊断',
  `test_day` datetime DEFAULT NULL COMMENT '测试日期',
  `gdcz` int(20) DEFAULT NULL COMMENT '观点采择',
  `xx` int(20) DEFAULT NULL COMMENT '想象',
  `gqgx` int(20) DEFAULT NULL COMMENT '共情关心',
  `grtk` int(20) DEFAULT NULL COMMENT '个人痛苦',
  `sum` int(50) DEFAULT NULL COMMENT '总分',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人际反应指针日志表';

/*Data for the table `iric_log` */

/*Table structure for table `mett_log` */

DROP TABLE IF EXISTS `mett_log`;

CREATE TABLE `mett_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志id',
  `patient_id` bigint(20) DEFAULT NULL COMMENT '患者id',
  `patient_name` varchar(100) DEFAULT NULL COMMENT '患者名称',
  `sex` char(1) DEFAULT NULL COMMENT '性别',
  `age` int(20) DEFAULT NULL COMMENT '年龄',
  `education` varchar(20) DEFAULT NULL COMMENT '文化程度',
  `job` varchar(20) DEFAULT NULL COMMENT '职业',
  `diagnosis` varchar(50) DEFAULT NULL COMMENT '诊断',
  `test_day` datetime DEFAULT NULL COMMENT '测试日期',
  `gx_correct` int(20) DEFAULT NULL COMMENT '高兴正确率',
  `bs_correct` int(20) DEFAULT NULL COMMENT '悲伤正确率',
  `fn_correct` int(20) DEFAULT NULL COMMENT '愤怒正确率',
  `yw_correct` int(20) DEFAULT NULL COMMENT '厌恶正确率',
  `jy_correct` int(50) DEFAULT NULL COMMENT '惊讶正确率',
  `kj_correct` int(50) DEFAULT NULL COMMENT '恐惧正确率',
  `zx_correct` int(20) DEFAULT NULL COMMENT '中性正确率',
  `gx_reply` double(20,2) DEFAULT NULL COMMENT '高兴反应时',
  `bs_reply` double(20,2) DEFAULT NULL COMMENT '悲伤反应时',
  `fn_reply` double(20,2) DEFAULT NULL COMMENT '愤怒反应时',
  `yw_reply` double(20,2) DEFAULT NULL COMMENT '厌恶反应时',
  `jy_reply` double(50,2) DEFAULT NULL COMMENT '惊讶反应时',
  `kj_reply` double(50,2) DEFAULT NULL COMMENT '恐惧反应时',
  `zx_reply` double(20,2) DEFAULT NULL COMMENT '中性反应时',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='情绪识别日志表';

/*Data for the table `mett_log` */

insert  into `mett_log`(`id`,`patient_id`,`patient_name`,`sex`,`age`,`education`,`job`,`diagnosis`,`test_day`,`gx_correct`,`bs_correct`,`fn_correct`,`yw_correct`,`jy_correct`,`kj_correct`,`zx_correct`,`gx_reply`,`bs_reply`,`fn_reply`,`yw_reply`,`jy_reply`,`kj_reply`,`zx_reply`,`create_by`,`create_time`) values (1,14,'张三','0',21,'本科','学生','抑郁症','2021-07-22 17:55:42',0,0,0,0,0,1,0,0.38,0.42,0.40,0.37,0.41,0.43,0.62,NULL,NULL);

/*Table structure for table `newtable` */

DROP TABLE IF EXISTS `newtable`;

CREATE TABLE `newtable` (
  `id` int(32) NOT NULL COMMENT 'id',
  `scale_id` int(32) DEFAULT NULL COMMENT '量表名字',
  `task_id` int(32) DEFAULT NULL COMMENT '任务id',
  `patient_id` int(32) DEFAULT NULL COMMENT '病人id',
  `score` int(32) DEFAULT NULL COMMENT '得分',
  `workstation` varchar(32) DEFAULT NULL COMMENT '工作站',
  `test_date` date DEFAULT '1999-01-01' COMMENT '测试日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `newtable` */

/*Table structure for table `patient_advanced_task` */

DROP TABLE IF EXISTS `patient_advanced_task`;

CREATE TABLE `patient_advanced_task` (
  `task_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '测评任务ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '测评人ID',
  `user_name` varchar(50) DEFAULT NULL COMMENT '测评人姓名',
  `patient_id` bigint(20) DEFAULT NULL COMMENT '患者ID',
  `patient_name` varchar(50) DEFAULT NULL COMMENT '患者名称',
  `workstation` varchar(50) DEFAULT NULL COMMENT '工作站',
  `test_coding` varchar(100) DEFAULT NULL COMMENT '测试编码',
  `typeIds` varchar(100) DEFAULT NULL COMMENT '测评任务',
  `task_status` char(1) DEFAULT NULL COMMENT '任务状态(1未开始,2进行中,3已结束)',
  `del_flag` char(1) DEFAULT NULL COMMENT '是否删除(0存在，1删除)',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `scale_id` varchar(255) DEFAULT NULL COMMENT '已完成量表',
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='高级认知任务表';

/*Data for the table `patient_advanced_task` */

/*Table structure for table `patient_basis_task` */

DROP TABLE IF EXISTS `patient_basis_task`;

CREATE TABLE `patient_basis_task` (
  `task_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '测评任务ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '测评人ID',
  `user_name` varchar(50) DEFAULT NULL COMMENT '测评人姓名',
  `patient_id` bigint(20) DEFAULT NULL COMMENT '患者ID',
  `patient_name` varchar(50) DEFAULT NULL COMMENT '患者名称',
  `workstation` varchar(50) DEFAULT NULL COMMENT '工作站',
  `test_coding` varchar(100) DEFAULT NULL COMMENT '测试编码',
  `typeIds` varchar(100) DEFAULT NULL COMMENT '测评任务',
  `task_status` char(1) DEFAULT NULL COMMENT '任务状态(1未开始,2进行中,3已结束)',
  `del_flag` char(1) DEFAULT NULL COMMENT '是否删除(0存在，1删除)',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `scale_id` varchar(255) DEFAULT NULL COMMENT '已完成量表',
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='基础认知任务表';

/*Data for the table `patient_basis_task` */

/*Table structure for table `patient_categories` */

DROP TABLE IF EXISTS `patient_categories`;

CREATE TABLE `patient_categories` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `classification_code` varchar(100) DEFAULT NULL COMMENT '分类编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=328 DEFAULT CHARSET=utf8 COMMENT='分类目录表';

/*Data for the table `patient_categories` */

insert  into `patient_categories`(`id`,`classification_code`) values (1,'正常'),(2,'待诊   '),(3,'(F00.) 阿兹海默病（失智症）'),(4,'(F01.) 血管性痴呆'),(5,'(F01.0) 急性发作的血管性痴呆'),(6,'(F01.1) 多发脑梗死性痴呆'),(7,'(F01.2) 皮层下血管性痴呆'),(8,'(F01.5) 混合型皮层和皮层下血管性痴呆'),(9,'(F01.8) 其他血管性痴呆'),(10,'(F01.9) 未特指血管性痴呆'),(11,'(F01.9.1) 混合型心境情感障碍'),(12,'(F01.9.2) 单纯抑郁性复发心境情感障碍'),(13,'(F02.) 分类于他处的其他疾病引起的痴呆'),(14,'(F02.0) 皮克氏病引起的痴呆'),(15,'(F02.1) 克雅二氏病引起的痴呆'),(16,'(F02.2) 亨丁顿舞蹈症引起的痴呆'),(17,'(F02.3) 帕金森氏症引起的痴呆'),(18,'(F02.4) 艾滋病引起的痴呆'),(19,'(F03.) 未特指的痴呆'),(20,'(F04.) 器质性遗忘综合症，非由酒精和其他精神活性物质所致'),(21,'(F05.) 谵妄，非由酒精和其他精神活性物质所致'),(22,'(F05.0) 非附加于痴呆的谵妄'),(23,'(F05.1) 附加于痴呆的谵妄'),(24,'(F05.8) 其他谵妄（混合起因）的谵妄'),(25,'(F05.9) 感染性精神病'),(26,'(F06.) 由于脑损害和机能障碍及躯体疾病引起的其他精神障碍'),(27,'(F06.0) 器质性幻觉'),(28,'(F06.1) 器质性紧张症'),(29,'(F06.2) 器质性精神分裂症样精神障碍'),(30,'(F06.3) 器质性情感障碍'),(31,'(F06.4) 器质性焦虑障碍'),(32,'(F06.5) 器质性解离性障碍'),(33,'(F06.6) 器质性情感虚弱障碍'),(34,'(F06.7) 轻度认知障碍'),(35,'(F06.8) 由于脑损害和机能障碍及躯体疾病引起的其他特指精神障碍'),(36,'(F06.9) 由于脑损害和机能障碍及躯体疾病引起的其他非特指精神障碍'),(37,'(F07.) 由于脑部疾病、损害和功能障碍引起的人格和行为障碍'),(38,'(F07.0) 器质性人格障碍'),(39,'(F07.1) 脑炎后综合症'),(40,'(F07.2) 脑震荡后综合症'),(41,'(F07.8) 由于脑损害和机能障碍及躯体疾病引起的其他器质性人格及行为障碍'),(42,'(F07.9) 由于脑损害和机能障碍及躯体疾病引起的其他非特指器质性人格及行为障碍'),(43,'(F09.) 未特指的器质性或症状性精神障碍'),(44,'(F10.0.) 酒精中毒 '),(45,'(F10.2)酒精依赖 '),(46,'(F10.3)戒酒综合症 '),(47,'(F10.4)震颤谵妄 '),(48,'(F10.5) 酒精中毒性幻觉症 '),(49,'(F10.6) 科尔萨科夫综合症       '),(50,'(F11.0)鸦片过量 '),(51,'(F11.2)鸦片依赖                                                                               '),(52,'(F13.0)苯二氮类药物过量 '),(53,'(F13.1)苯二氮类药物滥用 '),(54,'(F13.2)苯二氮类药物依赖 '),(55,'(F13.3)苯二氮类药物戒断综合症                                '),(56,'(F14.2) 可卡因依赖                                        '),(57,'(F15.5)兴奋剂精神病                '),(58,'(F16.7)使用致幻剂后知觉障碍'),(59,'(F17.2)尼古丁戒断综合症                                        '),(60,'(F20.) 精神分裂症'),(61,'(F20.0) 偏狂型精神分裂症'),(62,'(F20.1) 青春型精神分裂症'),(63,'(F20.2) 紧张型精神分裂症'),(64,'(F20.3) 混合型精神分裂症'),(65,'(F20.4) 精神分裂症后抑郁症'),(66,'(F20.5) 残余型精神分裂症'),(67,'(F20.6) 单纯型精神分裂症'),(68,'(20.8.1)精神裂症样障碍'),(69,'(20.8.2)分裂样精神病'),(70,'(F20.9) 未特指的精神分裂症'),(71,'(F21.) 分裂型障碍'),(72,'(F22.) 持久的妄想性障碍'),(73,'(F22.0) 妄想性障碍'),(74,'(22.8.1)妄想恐畸形症'),(75,'(22.8.1)更年期妄想状态'),(76,'(F22.9) 未特指的分裂型障碍'),(77,'(F23.) 急性复合精神病性障碍'),(78,'(F23.0) 急性复合精神病性障碍未伴有精神分裂症症状'),(79,'(F23.1) 急性复合精神病性障碍伴有精神分裂症症状'),(80,'(F23.2) 急性精神分裂症样精神病性障碍'),(81,'(F23.3) 其他急性显著妄想精神病性障碍'),(82,'(F23.8) 其他急性及短暂精神病性障碍'),(83,'(F23.9) 未特指的急性及短暂精神病性障碍'),(84,'(F24.) 感应性妄想性障碍'),(85,'(F25.) 分裂情感性障碍'),(86,'(F25.0) 狂躁型分裂情感性障碍'),(87,'(F25.1) 抑郁型分裂情感性障碍'),(88,'(F25.2) 混合型分裂情感性障碍'),(89,'(F25.8) 其他分裂情感性障碍'),(90,'(F25.9) 非特指的分裂情感性障碍'),(91,'(F28.) 其他非器质性精神病性障碍'),(92,'(F29.) 未特指的非器质性精神病'),(93,'(F30.) 狂躁'),(94,'(F30.0) 轻度狂躁'),(95,'(F30.1) 无精神病性症状的狂躁症'),(96,'(F30.2) 有精神病性症状的狂躁症'),(97,'(F30.8) 其他症状的狂躁症'),(98,'(F30.9) 非特指的狂躁症'),(99,'(F31.) 双相情感障碍（双相情感障碍、躁郁症）'),(100,'(F31.0) 现呈轻度躁狂症状的双相情感障碍'),(101,'(F31.1) 现呈无精神病性症状的双相情感障碍'),(102,'(F31.2) 现呈精神病性症状的双相情感障碍'),(103,'(F31.3) 现呈轻度或中度抑郁症状的双相情感障碍'),(104,'(F31.4) 现呈重度抑郁症状的双相情感障碍，不伴有精神分裂症症状'),(105,'(F31.5) 现呈重度抑郁症状的双相情感障碍，伴有精神分裂症症状'),(106,'(F31.6) 现呈混合症状的双相情感障碍'),(107,'(F31.7) 现呈缓解状态的双相情感障碍'),(108,'(F31.8.1)双向情感障碍II型'),(109,'(F31.8.2)复发型双相情感障碍'),(110,'(F31.9) 非特指的双相情感障碍'),(111,'(F32.) 抑郁性障碍'),(112,'(F32.0) 轻度抑郁性障碍'),(113,'(F32.1) 中度抑郁性障碍'),(114,'(F32.2) 重度抑郁性障碍，不伴有精神分裂症症状'),(115,'(F32.3) 重度抑郁性障碍，伴有精神分裂症症状'),(116,'(F32.8.1)非典型抑郁障碍'),(117,'(F32.8.2)隐匿型抑郁障碍'),(118,'(F32.9) 非特异性抑郁障碍'),(119,'(F33.) 复发性抑郁障碍'),(120,'(F33.0) 现呈轻度抑郁性障碍症状的复发性抑郁障碍'),(121,'(F33.1) 现呈中度抑郁性障碍症状的复发性抑郁障碍'),(122,'(F33.2) 现呈不伴有精神分裂症症状得重度抑郁性障碍症状的复发性抑郁障碍'),(123,'(F33.3) 现呈伴有精神分裂症症状得重度抑郁性障碍症状的复发性抑郁障碍'),(124,'(F33.4) 现呈缓解状态的复发性抑郁障碍'),(125,'(F33.8) 其他复发性抑郁障碍'),(126,'(F33.9) 非特指的复发性抑郁障碍'),(127,'(F34.) 持续性心境情感障碍'),(128,'(F34.0) 循环心境障碍'),(129,'(F34.1) 心境恶劣障碍'),(130,'(F34.8) 其他持续性心境情感障碍'),(131,'(F34.9) 非特指的持续性心境情感障碍'),(132,'(F38.) 其他情感障碍'),(133,'(F38.0.1)混合型心境情感障碍'),(134,'(F38.1.1)单纯抑郁性复发心境情感障碍'),(135,'(F38.8) 其他特指的心境情感障碍'),(136,'(F39.) 未特指的情感障碍'),(137,'(F40.) 恐惧性焦虑障碍（恐惧症）'),(138,'(F40.0) 广场恐怖症'),(139,'(F40.1.1)人群恐怖'),(140,'(F40.1.2)社交神经症'),(141,'(F40.2.1)恐高症'),(142,'(F40.2.2)动物恐惧症'),(143,'(F40.2.3)幽闭恐怖症'),(144,'(F40.2.4)单纯恐怖症'),(145,'(F40.8) 其他恐惧性焦虑障碍'),(146,'(F40.9.1)恐惧症 NOS'),(147,'(F40.9.2)恐惧状态 NOS'),(148,'(F41.) 其他焦虑障碍'),(149,'(F41.0) 惊恐性障碍 (突发性焦虑障碍)'),(150,'(F41.1) 广泛性焦虑障碍'),(151,'(F42.) 强迫性障碍'),(152,'(F43.) 严重应激反应及适应障碍'),(153,'(F43.0) 急性应激反应'),(154,'(F43.1) 创伤后心理压力紧张综合症 （PTSD）'),(155,'(F43.2) 适应障碍'),(156,'(F44.) 分离性障碍'),(157,'(F44.0) 解离性失忆症'),(158,'(F44.1) 解离性漫游症'),(159,'(F44.2) 解离性昏迷症'),(160,'(F44.3) 出神和附体障碍'),(161,'(F44.4) 解离性动力紊乱'),(162,'(F44.5) 解离性痉挛症'),(163,'(F44.6) 混合性分离性转换障碍'),(164,'(F44.7) 混合性分离障碍'),(165,'(F44.8.1)甘塞尔综合症'),(166,'(F44.8.2多重人格)'),(167,'(F44.9) 未分类的分离性障碍'),(168,'(F45.) 躯体形式障碍'),(169,'(F45.0) 躯体化障碍'),(170,'(F45.1) 未分化躯体形式障碍'),(171,'(F45.2) 疑病障碍'),(172,'(F45.3) 躯体形式植物神经紊乱'),(173,'(F45.4) 持续性躯体形式疼痛障碍（PSPD）'),(174,'(F45.8) 其他躯体形式障碍'),(175,'(F45.9) 未分类的躯体形式障碍'),(176,'(F48.) 其他神经症性障碍'),(177,'(F48.0) 神经衰弱症'),(178,'(F48.1) 人格解体障碍（DD）'),(179,'(F48.8) 其他的神经症性障碍'),(180,'(F48.9) 未分类的神经症性障碍'),(181,'(F50.) 饮食障碍'),(182,'(F50.0) 神经性厌食症'),(183,'(F50.1) 神经性暴食症'),(184,'(F50.2) 暴食症'),(185,'(F50.3) 心因性暴食症'),(186,'(F50.4) 伴有其他精神症状的暴食症'),(187,'(F50.5) 伴有其他精神症状的呕吐症'),(188,'(F50.8) 其他饮食障碍'),(189,'(F50.9) 未分类的进食障碍'),(190,'(F51.) 非器质性睡眠障碍'),(191,'(F51.0) 非器质性失眠症'),(192,'(F51.1) 非器质性嗜睡症'),(193,'(F51.2) 非器质性睡眠-觉醒节律障碍'),(194,'(F51.3) 梦游症 (梦游病)'),(195,'(F51.4) 夜惊障碍症 (夜惊)'),(196,'(F51.5) 噩梦症'),(197,'(F52.) 非器质性障碍或疾病引起的性功能障碍'),(198,'(F52.0) 性欲减退'),(199,'(F52.1) 性厌恶'),(200,'(F52.2) 性冷淡'),(201,'(F52.3) 性高潮障碍'),(202,'(F52.4) 早泄'),(203,'(F52.5) 非器质性阴道痉挛'),(204,'(F52.6) 非器质性性交困难'),(205,'(F52.7) 性欲亢进'),(206,'(F52.8) 其他非器质性障碍或疾病引起的性功能障碍'),(207,'(F52.9) 未特指的非器质性障碍或疾病引起的性功能障碍'),(208,'(F53.) 与产褥期有关的精神和行为障碍，不可归类在他处者'),(209,'(F53.0) 与产褥期有关的轻度精神和行为障碍，不可归类在他处者'),(210,'(F53.1) 与产褥期有关的重度精神和行为障碍，不可归类在他处者'),(211,'(F54.) 与归类在他处的障碍或疾病有关的心理和行为因素'),(212,'(F55.) 非依赖性物质滥用'),(213,'(F59.) 未特指的与生理紊乱和躯体因素有关的行为综合症'),(214,'(F60.) 特异性人格障碍'),(215,'(F60.0) 偏执性人格障碍'),(216,'(F60.1) 分裂性人格障碍'),(217,'(F60.2) 反社会人格障碍'),(218,'(F60.3) 边缘性人格障碍'),(219,'(F60.4) 表演型人格障碍'),(220,'(F60.5) 强迫型人格障碍'),(221,'(F60.6) 回避型人格障碍'),(222,'(F60.7) 依赖型人格障碍'),(223,'(F60.8) 其他特指的人格障碍'),(224,'(F60.9) 非特指的人格障碍'),(225,'(F61.) 混合型和其他人格障碍'),(226,'(F62.) 非由脑损害和疾病所致的持久性人格改变'),(227,'(F63.) 习惯和冲动障碍'),(228,'(F63.0) 病理性赌博'),(229,'(F63.1) 病理性纵火 (纵火癖)'),(230,'(F63.2) 病理性偷窃 (盗窃癖)'),(231,'(F63.3) 拔毛癖'),(232,'(F64.) 性身份障碍'),(233,'(F64.0) 易性症（易性癖）'),(234,'(F64.1) 双重角色异装症'),(235,'(F64.2) 儿童期性身份障碍'),(236,'(F65.) 性偏好障碍'),(237,'(F65.0) 恋物癖'),(238,'(F65.1) 异装癖'),(239,'(F65.2) 裸露癖'),(240,'(F65.3) 窥视症'),(241,'(F65.4) 恋童癖'),(242,'(F65.5) 虐恋症'),(243,'(F65.6) 混合型性偏好障碍'),(244,'(F65.8) 非特指的性偏好障碍'),(245,'(F66.) 与性发育和性取向有关的心理和行障碍'),(246,'(F66.0) 性成熟障碍'),(247,'(F66.1) 自我矛盾性性定向'),(248,'(F66.2) 性关系障碍'),(249,'(F66.8) 其他与性发育和性取向有关的心理和行障碍'),(250,'(F66.9) 非特指的与性发育和性取向有关的心理和行障碍'),(251,'(F68.) 成人人格和行为的其他障碍'),(252,'(F69.) 未特指的成人人格和行为障碍'),(253,'(F70.) 轻度精神发育迟滞'),(254,'(F71.) 中度精神发育迟滞'),(255,'(F72.) 重度精神发育迟滞'),(256,'(F73.) 极重度精神发育迟滞'),(257,'(F78.) 其他精神发育迟滞'),(258,'(F79.) 未特指的精神发育迟滞'),(259,'(F80.) 关于言语和语言的特殊性发育障碍'),(260,'(F80.0) 特定言语构音障碍'),(261,'(F80.1) 表达型语言障碍'),(262,'(F80.2) 感受性语言障碍'),(263,'(F80.3) 伴发癫痫的获得性失语 (Landau-Kleffner综合症)'),(264,'(F80.8) 其他言语和语言发育障碍'),(265,'(F80.9) 未分类的言语和语言发育障碍'),(266,'(F81.) 特定性学习技能障碍'),(267,'(F81.0) 特定性阅读障碍'),(268,'(F81.1) 特定性书写障碍'),(269,'(F81.2) 特定性计算技能障碍'),(270,'(F81.3) 混合性学习技能障碍'),(271,'(F81.8) 其他学习技能障碍'),(272,'(F81.9) 未分类的学习技能障碍'),(273,'(F82.) 特定性运动功能发育障碍'),(274,'(F83.) 混合性特定发育障碍'),(275,'(F84.) 广泛性发展障碍'),(276,'(F84.0) 自闭症'),(277,'(F84.2) 蕾特氏症 （en:Rett syndrome）'),(278,'(F84.3) 其他的童年瓦解性精神障碍'),(279,'(F84.4) 伴有精神阻滞和刻板运动的过度活跃障碍'),(280,'(F84.5) 亚斯伯格综合症'),(281,'(F88.) 其他心理发育障碍'),(282,'(F89.) 未分类的心理发育障碍'),(283,'(F90.) 多动性障碍'),(284,'(F90.0) 注意力不足多动障碍'),(285,'(F90.1) 多动症合并品行障碍'),(286,'(F90.8) 其他多动障碍'),(287,'(F90.9) 未分类的多动障碍'),(288,'(F91.) 品行障碍'),(289,'(F91.0) 局限于家庭的品行障碍'),(290,'(F91.1) 非社会化品行障碍'),(291,'(F91.2) 社会化品行障碍'),(292,'(F91.3) 对立违抗性障碍（en:Oppositional defiant disorder）'),(293,'(F91.8) 其他品行障碍'),(294,'(F91.9) 未分类的品行障碍'),(295,'(F92.) 品行及情绪混合性障碍'),(296,'(F92.0) 抑郁性品行及情绪障碍'),(297,'(F92.8) 其他品行及情绪障碍'),(298,'(F92.9) 未分类的品行及情绪障碍'),(299,'(F93.) 特发于童年的情绪障碍'),(300,'(F93.0) 儿童分离性焦虑障碍'),(301,'(F93.1) 儿童恐惧障碍'),(302,'(F93.2) 儿童社交恐惧障碍'),(303,'(F93.3) 同胞竞争障碍'),(304,'(F93.8) 其他的童年情绪障碍'),(305,'(F93.9) 未分类的童年情绪障碍'),(306,'(F94.) 特发于童年和青少年的社会功能障碍'),(307,'(F94.0) 选择性缄默障碍'),(308,'(F94.1) 童年反应性障碍障碍'),(309,'(F94.2) 童年脱抑制依附障碍'),(310,'(F94.8) 其他的童年社会功能障碍'),(311,'(F94.9) 未分类的童年社会功能障碍'),(312,'(F95.) 抽动障碍'),(313,'(F95.0) 短暂性抽动障碍'),(314,'(F95.1) 慢性运动或发声抽动障碍'),(315,'(F95.2) 发声与多种运动联合抽动障碍 （Tourette综合症）'),(316,'(F95.8) 其他的抽动障碍'),(317,'(F95.9) 未分类的抽动障碍'),(318,'(F98.) 通常起病于童年和青少年期的其他行为和情绪障碍'),(319,'(F98.0) 非器质性遗尿症'),(320,'(F98.1) 非器质性遗粪症'),(321,'(F98.2) 婴幼儿和童年喂食障碍'),(322,'(F98.3) 婴幼儿和童年异食障碍'),(323,'(F98.4) 刻板性运动障碍'),(324,'(F98.5) 口吃'),(325,'(F98.6) 语言错乱'),(326,'(F98.8) 其他的通常起病于童年和青少年期的其他行为和情绪障碍'),(327,'(F98.9) 未分类的通常起病于童年和青少年期的其他行为和情绪障碍');

/*Table structure for table `patient_intermediate_task` */

DROP TABLE IF EXISTS `patient_intermediate_task`;

CREATE TABLE `patient_intermediate_task` (
  `task_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '测评任务ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '测评人ID',
  `user_name` varchar(50) DEFAULT NULL COMMENT '测评人姓名',
  `patient_id` bigint(20) DEFAULT NULL COMMENT '患者ID',
  `patient_name` varchar(50) DEFAULT NULL COMMENT '患者名称',
  `workstation` varchar(50) DEFAULT NULL COMMENT '工作站',
  `test_coding` varchar(100) DEFAULT NULL COMMENT '测试编码',
  `typeIds` varchar(100) DEFAULT NULL COMMENT '测评任务',
  `task_status` char(1) DEFAULT NULL COMMENT '任务状态(1未开始,2进行中,3已结束)',
  `del_flag` char(1) DEFAULT NULL COMMENT '是否删除(0存在，1删除)',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `scale_id` varchar(255) DEFAULT NULL COMMENT '已完成量表',
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='进阶认知任务表';

/*Data for the table `patient_intermediate_task` */

/*Table structure for table `patient_society_task` */

DROP TABLE IF EXISTS `patient_society_task`;

CREATE TABLE `patient_society_task` (
  `task_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '测评任务ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '测评人ID',
  `user_name` varchar(50) DEFAULT NULL COMMENT '测评人姓名',
  `patient_id` bigint(20) DEFAULT NULL COMMENT '患者ID',
  `patient_name` varchar(50) DEFAULT NULL COMMENT '患者名称',
  `workstation` varchar(50) DEFAULT NULL COMMENT '工作站',
  `test_coding` varchar(100) DEFAULT NULL COMMENT '测试编码',
  `typeIds` varchar(100) DEFAULT NULL COMMENT '测评任务',
  `task_status` char(1) DEFAULT NULL COMMENT '任务状态(1未开始,2进行中,3已结束)',
  `del_flag` char(1) DEFAULT NULL COMMENT '是否删除(0存在，1删除)',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `scale_id` varchar(255) DEFAULT NULL COMMENT '已完成量表',
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='社会认知任务表';

/*Data for the table `patient_society_task` */

insert  into `patient_society_task`(`task_id`,`user_id`,`user_name`,`patient_id`,`patient_name`,`workstation`,`test_coding`,`typeIds`,`task_status`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`,`scale_id`) values (7,1,'admin',14,'张三','C002','278855','22','3','1','admin','2021-07-22 17:41:24',NULL,NULL,'22'),(9,1,'admin',2,'张三','C001','38133','22','0','0','admin','2021-10-25 16:45:27',NULL,NULL,NULL);

/*Table structure for table `patient_user` */

DROP TABLE IF EXISTS `patient_user`;

CREATE TABLE `patient_user` (
  `patient_id` bigint(50) NOT NULL AUTO_INCREMENT COMMENT '患者ID',
  `patient_name` varchar(30) NOT NULL COMMENT '患者姓名',
  `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `age` int(20) DEFAULT NULL COMMENT '年龄',
  `hospital_number` bigint(50) DEFAULT NULL COMMENT '住院号',
  `ward` varchar(50) DEFAULT NULL COMMENT '病区',
  `test_date` datetime DEFAULT NULL COMMENT '检测日期',
  `job` varchar(20) DEFAULT NULL COMMENT '职业',
  `education` varchar(20) DEFAULT NULL COMMENT '学历',
  `nation` varchar(20) DEFAULT NULL COMMENT '民族',
  `marital_status` char(1) DEFAULT NULL COMMENT '婚姻状况(0未婚,1已婚)',
  `birthday` datetime DEFAULT NULL COMMENT '出生日期',
  `contanct_information` varchar(50) DEFAULT NULL COMMENT '联系方式',
  `source` varchar(100) DEFAULT NULL COMMENT '患者来源',
  `classification_coding` varchar(100) DEFAULT NULL COMMENT '分类编码',
  `diagnosis` varchar(50) DEFAULT NULL COMMENT '诊断',
  `del_flag` char(1) DEFAULT '0' COMMENT '是否删除(0存在，1删除)',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`patient_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='患者表';

/*Data for the table `patient_user` */

insert  into `patient_user`(`patient_id`,`patient_name`,`sex`,`age`,`hospital_number`,`ward`,`test_date`,`job`,`education`,`nation`,`marital_status`,`birthday`,`contanct_information`,`source`,`classification_coding`,`diagnosis`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`) values (14,'张三','0',21,1,'2',NULL,'学生','本科','汉族','0','1999-05-02 08:00:00','15536547895','门诊','(F06.7) 轻度认知障碍','抑郁症','0','SXZYLM123','2021-02-19 14:06:28','SXZYLM123','2021-02-19 14:06:43');

/*Table structure for table `peri_patient_user` */

DROP TABLE IF EXISTS `peri_patient_user`;

CREATE TABLE `peri_patient_user` (
  `patient_id` bigint(50) NOT NULL AUTO_INCREMENT COMMENT '患者ID',
  `patient_name` varchar(30) NOT NULL COMMENT '患者姓名',
  `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `age` int(20) DEFAULT NULL COMMENT '年龄',
  `hospital_number` bigint(50) DEFAULT NULL COMMENT '住院号',
  `ward` varchar(50) DEFAULT NULL COMMENT '病区',
  `test_date` datetime DEFAULT NULL COMMENT '检测日期',
  `job` varchar(20) DEFAULT NULL COMMENT '职业',
  `education` varchar(20) DEFAULT NULL COMMENT '学历',
  `nation` varchar(20) DEFAULT NULL COMMENT '民族',
  `marital_status` char(1) DEFAULT NULL COMMENT '婚姻状况(0未婚,1已婚)',
  `birthday` datetime DEFAULT NULL COMMENT '出生日期',
  `contanct_information` varchar(50) DEFAULT NULL COMMENT '联系方式',
  `source` varchar(100) DEFAULT NULL COMMENT '患者来源',
  `classification_coding` varchar(100) DEFAULT NULL COMMENT '分类编码',
  `diagnosis` varchar(50) DEFAULT NULL COMMENT '诊断',
  `del_flag` char(1) DEFAULT '0' COMMENT '是否删除(0存在，1删除)',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`patient_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='围手术期系统患者表';

/*Data for the table `peri_patient_user` */

insert  into `peri_patient_user`(`patient_id`,`patient_name`,`sex`,`age`,`hospital_number`,`ward`,`test_date`,`job`,`education`,`nation`,`marital_status`,`birthday`,`contanct_information`,`source`,`classification_coding`,`diagnosis`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`) values (16,'ross','0',48,202107232,'001',NULL,'工人','硕士研究生','汉族','1','1973-07-04 08:00:00','133333333','病房','待诊   ','焦虑症','0',NULL,'2021-07-14 16:05:50',NULL,'2021-07-15 11:34:51'),(17,'rechoi','1',4,26533,'22',NULL,'军人','本科','汉族','0','2017-07-05 08:00:00','232323','病房','正常','焦虑症','0',NULL,'2021-07-14 16:50:59',NULL,'2021-07-16 10:31:18');

/*Table structure for table `peri_task` */

DROP TABLE IF EXISTS `peri_task`;

CREATE TABLE `peri_task` (
  `task_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '测评人ID',
  `user_name` varchar(50) DEFAULT NULL COMMENT '测评人姓名',
  `patient_id` bigint(20) DEFAULT NULL COMMENT '患者ID',
  `patient_name` varchar(50) DEFAULT NULL COMMENT '患者名称',
  `workstation` varchar(50) DEFAULT NULL COMMENT '工作站',
  `test_coding` varchar(100) DEFAULT NULL COMMENT '测试编码',
  `typeIds` varchar(100) DEFAULT NULL COMMENT '测评任务',
  `task_status` char(1) DEFAULT NULL COMMENT '任务状态(1未开始,2进行中,3已结束)',
  `del_flag` char(1) DEFAULT NULL COMMENT '是否删除(0存在，1删除)',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `scale_id` varchar(255) DEFAULT NULL COMMENT '已完成量表',
  `type_flag` char(1) DEFAULT NULL COMMENT '任务所属系统（0新冠 1量表）',
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COMMENT='围手术期任务表';

/*Data for the table `peri_task` */

insert  into `peri_task`(`task_id`,`user_id`,`user_name`,`patient_id`,`patient_name`,`workstation`,`test_coding`,`typeIds`,`task_status`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`,`scale_id`,`type_flag`) values (41,1,'admin',16,'ross','C001','151481','63','3','1','admin','2021-07-22 17:40:32',NULL,'2021-07-22 18:18:46',NULL,NULL),(42,1,'admin',16,'ross','C001','590859','64','3','1','admin','2021-07-22 18:19:02',NULL,'2021-07-22 18:19:18',NULL,NULL);

/*Table structure for table `scale_menu` */

DROP TABLE IF EXISTS `scale_menu`;

CREATE TABLE `scale_menu` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '量表id',
  `scale_name` varchar(55) DEFAULT NULL COMMENT '量表名称',
  `grade` varchar(32) DEFAULT NULL COMMENT '量表类型',
  `pid` int(32) DEFAULT NULL COMMENT '上级量表id',
  `apply_scope` varchar(32) DEFAULT NULL COMMENT '适用范围',
  `commonly` int(32) DEFAULT NULL COMMENT '常用',
  `instruction` varchar(255) DEFAULT NULL COMMENT '指导语',
  `del_flag` varchar(32) DEFAULT NULL COMMENT '是否删除（0存在1删除）',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8 COMMENT='量表目录表';

/*Data for the table `scale_menu` */

insert  into `scale_menu`(`id`,`scale_name`,`grade`,`pid`,`apply_scope`,`commonly`,`instruction`,`del_flag`,`create_time`,`update_time`,`remark`) values (8,'一般心理量表',NULL,0,NULL,NULL,NULL,'0','2021-09-28 13:48:58','2021-09-28 16:45:29','——————'),(9,'90项症状清单(SCL-90)','5',8,'各类精神疾患',NULL,'请仔细地阅读每一条，然后根据最近一星期以内下述情况影响您的实际感觉，在5个选项中选择一项。','0','2021-09-28 14:03:37','2021-09-29 11:04:56',NULL),(10,'一般健康问卷(GHQ)','5',8,'各类精神疾患',NULL,'这是一份用来了解人们一般心理健康状况的问卷。共有12条文字，请仔细地阅读每一条，把意思弄明白。然后，根据您最近1个月的实际情况，和您平时的状况相比，选出最合适的回答。每一条只能选一个回答，不要多选，也不要漏选','0','2021-09-28 14:04:20','2021-09-29 11:06:08',NULL),(11,'诊断量表',NULL,0,NULL,NULL,NULL,'0','2021-09-28 14:04:42','2021-09-28 16:45:36','——————'),(12,'用于DSM-V轴I障碍的临床定式检查(SCID)','6',11,NULL,NULL,NULL,'0','2021-09-28 14:05:14',NULL,NULL),(13,'复合性国际诊断用检查(CIDI)',NULL,11,NULL,NULL,NULL,'0','2021-09-28 14:06:50',NULL,NULL),(14,'神经精神病学临床评定量表(SCAN)',NULL,11,NULL,NULL,NULL,'0','2021-09-28 14:07:28','2021-09-28 14:46:14',NULL),(15,'健康问题和疾病定量测试法(RTHD)',NULL,11,NULL,NULL,NULL,'0','2021-09-28 14:07:58','2021-09-28 14:46:18',NULL),(16,'简明国际神经精神访谈(MINI)',NULL,11,NULL,NULL,NULL,'0','2021-09-28 14:08:18','2021-09-28 14:46:21',NULL),(17,'精神病/分裂症量表',NULL,0,NULL,NULL,NULL,'0','2021-09-28 14:08:42','2021-09-28 16:45:45','——————'),(18,'简明精神病量表(BPRS)',NULL,17,'精神病性障碍',NULL,'请根据患者的口头表述和观察情况，依据症状定义和临床经验进行评分，在下列选项中选出最适合患者情况的选项。','0','2021-09-28 14:11:47','2021-09-29 10:48:39',NULL),(19,'阴性症状量表(SANS)',NULL,17,NULL,NULL,'请根据患者的口头表述和观察情况，依据症状定义和临床经验进行评分，在下列选项中选出最适合患者情况的选项。','0','2021-09-28 14:12:09','2021-09-28 14:13:40',NULL),(20,'阳性症状量表（SAPS)','8',17,NULL,NULL,'请根据患者的口头表述和观察情况，依据症状定义和临床经验进行评分，在下列选项中选出最适合患者情况的选项。','0','2021-09-28 16:37:56','2021-09-29 11:02:50',NULL),(21,'阳性和阴性症状量表（PANSS)','7',17,'精神分裂症',NULL,'请根据患者的口头表述和观察情况，依据症状定义和临床经验进行评分，在下列选项中选出最适合患者情况的选项。','0','2021-09-28 16:38:21','2021-09-29 11:02:26',NULL),(22,'Krawiecka症状量表',NULL,17,NULL,NULL,'请根据患者的口头表述和观察情况，依据症状定义和临床经验进行评分，在下列选项中选出最适合患者情况的选项。','0','2021-09-28 16:38:40',NULL,NULL),(23,'躁狂量表',NULL,0,NULL,NULL,NULL,'0','2021-09-28 16:44:41','2021-09-28 16:45:51','——————'),(24,'Bech-Rafaelsen躁狂量表(BRMS)',NULL,23,'躁狂症',NULL,'请根据患者的口头表述和观察情况，依据症状定义和临床经验进行评分，在下列选项中选出最适合患者情况的选项。','0','2021-09-28 16:46:36','2021-09-29 10:50:21',NULL),(25,'Young 躁狂评定量表(YMRS)','8',23,'躁狂症',NULL,'请根据患者的口头表述和观察情况，依据症状定义和临床经验进行评分，在下列选项中选出最适合患者情况的选项。\r\n','0','2021-09-28 16:59:18','2021-09-29 11:01:14',NULL),(26,'轻躁狂检测清单(HCL-32)',NULL,23,'躁狂症',NULL,'请仔细地阅读每一条，然后根据最近一段时间内下述情况影响您的实际感觉，在选项中选择一项最符合您的情况。','0','2021-09-28 16:59:38','2021-09-29 10:50:36',NULL),(27,'心境障碍问卷（MDQ)',NULL,23,NULL,NULL,'请仔细地阅读每一条，然后根据最近一段时间内下述情况影响您的实际感觉，在选项中选择一项最符合您的情况。','0','2021-09-28 16:59:59',NULL,NULL),(28,'抑郁量表',NULL,0,NULL,NULL,NULL,'0','2021-09-28 17:00:29',NULL,'——————'),(29,'汉密顿抑郁量表(HAMD)','7',28,'抑郁症',NULL,'请根据患者的口头表述和观察情况，依据症状定义和临床经验进行评分，在下列选项中选出最适合患者情况的选项。\r\n','0','2021-09-28 17:00:53','2021-09-29 11:00:41',NULL),(30,'Montgomery-Asberg抑郁量表(MADRS)',NULL,28,'抑郁症',NULL,'请根据患者的口头表述和观察情况，依据症状定义和临床经验进行评分，在下列选项中选出最适合患者情况的选项。\r\n','0','2021-09-28 17:01:29','2021-09-29 10:46:10',NULL),(31,'纽卡斯尔抑郁诊断量表（NDI)',NULL,28,'抑郁症',NULL,'请根据患者的口头表述和观察情况，依据症状定义和临床经验进行评分，在下列选项中选出最适合患者情况的选项。\r\n','0','2021-09-28 17:01:46','2021-09-29 10:46:16',NULL),(32,'爱丁堡产后抑郁量表（EPDS)',NULL,28,'抑郁症',NULL,'由于您最近生了孩子，我们想了解您的感受。下面有10道题，每一题都有4种选择，请选出近7天来您最接近的感觉，而不只是您今天的感觉。\r\n','0','2021-09-28 17:02:14','2021-09-29 10:46:21',NULL),(33,'流调用抑郁自评量表(CES-D)','5',28,'抑郁症',NULL,'下面一些是你可能有过或感觉到的情况或想法。请按照过去一星期内你的实际情况或感觉，在下列选项中选出最符合您的选项。','0','2021-09-28 17:02:35','2021-09-29 10:44:50',NULL),(34,'抑郁自评量表(SDS)','5',28,'抑郁症',1,'下面一些是你可能有过或感觉到的情况或想法。请按照过去一星期内你的实际情况或感觉，在下列选项中选出最符合您的选项。\r\n','0','2021-09-28 17:03:06','2021-09-29 10:45:23',NULL),(35,'贝克抑郁问卷(BDI)','5',28,'抑郁症',NULL,'下面是一个问卷，由13道题组成，每一道题均有4句短句，代表4个可能的答案。请您仔细阅读每一道题的所有的回答（0~3)。读完后，从中选出一个最能反映你今天即此刻情况的句子。然后，再接着做下一题。\r\n','0','2021-09-28 17:03:24','2021-09-29 10:59:48',NULL),(36,'医院焦虑抑郁量表(HAD)',NULL,28,'抑郁症',NULL,'请仔细地阅读每一条，然后根据最近一段时间内下述情况影响您的实际感觉，在选项中选择一项最符合您的情况。','0','2021-09-28 17:03:42','2021-09-29 10:46:36',NULL),(37,'9项患者健康问卷(PHQ-9)',NULL,28,NULL,NULL,'在过去两个星期，有多少时间您被以下问题所困扰?请选出最符合您实际情况的选项。\r\n','0','2021-09-28 17:03:58',NULL,NULL),(38,'老年抑郁量表（GDS)',NULL,28,'抑郁症',NULL,'下面一些是你可能有过或感觉到的情况或想法。请按照过去一星期内你的实际情况或感觉，在下列选项中选出最符合您的选项。\r\n','0','2021-09-28 17:04:14','2021-09-29 10:46:41',NULL),(39,'焦虑量表',NULL,0,NULL,NULL,NULL,'0','2021-09-28 17:04:32',NULL,'——————'),(40,'汉密顿焦虑量表(HAMA)',NULL,39,'焦虑症',NULL,'请根据患者的口头表述和观察情况，依据症状定义和临床经验进行评分，在下列选项中选出最适合患者情况的选项。\r\n','0','2021-09-28 17:05:37','2021-09-29 10:47:40',NULL),(41,'状态-特质焦虑问卷（STAI)','5',39,'焦虑症',NULL,'请仔细地阅读每一条，然后根据最近一段时间内下述情况影响您的实际感觉，在选项中选择一项最符合您的情况。','0','2021-09-28 17:05:56','2021-09-29 10:58:49',NULL),(42,'焦虑自评量表(SAS)','5',39,'焦虑症',1,'请仔细地阅读每一条，然后根据最近一段时间内下述情况影响您的实际感觉，在选项中选择一项最符合您的情况。','0','2021-09-28 17:06:13','2021-09-29 10:47:54',NULL),(43,'惊恐相关症状量表(PASS)',NULL,39,NULL,NULL,'PASS评定的时间范围是最近一周。应由经过本量表培训的专业人员仔细全面询问患者发作情况后评定。\r\n','0','2021-09-28 17:06:32',NULL,NULL),(44,'惊恐障碍严重度量表（PDSS)',NULL,39,NULL,NULL,'本量表仅适用于评定诊断为惊恐障碍患者的病情严重程度,而非用于筛查或诊断目的,需由经过培训的专业人员来评定。评定时间范围一般为l个月，也可以自行规定，但每个条目的评定时间范围必须一致。\r\n','0','2021-09-28 17:06:48',NULL,NULL),(45,'7项广泛性焦虑障碍量表(GAD-7)',NULL,39,'焦虑症',NULL,'在过去两个星期，有多少时间您被以下问题所困扰?请选出最符合您实际情况的选项。\r\n','0','2021-09-28 17:07:20','2021-09-29 10:48:00',NULL),(46,'恐惧/强迫量表',NULL,0,NULL,NULL,NULL,'0','2021-09-28 17:07:52',NULL,'——————'),(47,'Marks恐惧强迫量表(MSCPOR)',NULL,46,NULL,NULL,'请仔细地阅读每一条，然后根据最近一段时间内症状的严重程度或持续时间评定，在选项中选择一项最符合您的情况。','0','2021-09-28 17:08:35',NULL,NULL),(48,'Yale-Brown强迫量表（Y-BOCS)',NULL,46,NULL,NULL,'在过去一周，有多少时间您被以下问题所困扰?请选出最符合您实际情况的选项。\r\n','0','2021-09-28 17:09:03',NULL,NULL),(49,'Liebowitz社交焦虑量表(LSAS)',NULL,46,NULL,NULL,'对11个社交情境（如对权威人士讲话）和13个操作情境（如在被注意的情况下走路〉下的恐惧和回避分别进行评估。','0','2021-09-28 17:09:21',NULL,NULL),(50,'创伤后应激障碍量表',NULL,0,NULL,NULL,NULL,'0','2021-09-28 17:10:15',NULL,'——————'),(51,'临床用创伤后应激障碍量表(CAPS)',NULL,50,NULL,NULL,'访谈围绕可能导致PTSD的应激源（事件）进行。如有多个严重事件，让被试挑选出其中3个，例如:最初的、最重的和最近的，或影响最大的;或者由检查者根据临床或研究需要决定。\n','0','2021-09-28 17:12:55',NULL,NULL),(52,'创伤后应激障碍症状清单(PCL)',NULL,50,NULL,NULL,'当您经历或目睹了无法预测的突发事件后，突发事件产生的痛苦情绪有时会在您的记忆中保留很长时间，并且每次回忆时都很痛苦。请您自己评估最近1个月您的反应，包括这些反应的严重程度，选出最符合实际情况的一项。\n','0','2021-09-28 17:13:20',NULL,NULL),(53,'事件影响量表修订版（IES-R)',NULL,50,NULL,NULL,'注意:当您经历或目睹了无法预测的突发事件后，有可能会有一些心理或生理反应。请您自己评估过去1月内您的反应以及对您的影响程度，在下列选项中选出最符合的一项。\n','0','2021-09-28 17:13:39',NULL,NULL),(54,'儋妄量表',NULL,0,NULL,NULL,NULL,'0','2021-09-28 17:14:57',NULL,'——————'),(55,'儋妄评定方法(CAM)',NULL,54,NULL,NULL,'请根据患者的口头表述和观察情况，依据症状定义和临床经验进行评分，在下列选项中选出最适合患者情况的选项。\r\n','0','2021-09-28 17:15:33',NULL,NULL),(56,'儋妄严重程度量表(DSS)',NULL,54,NULL,NULL,'请根据患者的口头表述和观察情况，依据症状定义和临床经验进行评分，在下列选项中选出最适合患者情况的选项。\r\n','0','2021-09-28 17:16:44',NULL,NULL),(57,'酒精和药物依赖量表',NULL,0,NULL,NULL,NULL,'0','2021-09-28 17:17:10',NULL,'——————'),(58,'酒精使用障碍筛查量表（AUDIT)',NULL,57,NULL,NULL,'请仔细地阅读每一条，然后根据最近一段时间内的实际情况在选项中选择一项最符合您的情况。','0','2021-09-28 17:18:21',NULL,NULL),(59,'密西根酒精依赖调查表(MAST)',NULL,57,NULL,NULL,'请仔细地阅读每一条，然后根据最近一段时间内的实际情况在选项中选择一项最符合您的情况。','0','2021-09-28 17:22:34',NULL,NULL),(60,'WHO烟、酒和精神活性物质使用筛查量表（ASSIST)',NULL,57,NULL,NULL,'请仔细地阅读每一条，然后根据最近一段时间内的实际情况在选项中选择一项最符合您的情况。','0','2021-09-28 17:22:51',NULL,NULL),(61,'成瘾严重程度指数（ASI)','7',57,NULL,NULL,'请仔细地阅读每一条，然后根据最近一段时间内的实际情况在选项中选择一项最符合您的情况。','0','2021-09-28 17:23:10','2021-09-29 10:56:40',NULL),(62,'阿片成瘾严重程度量表(OASD)',NULL,57,NULL,NULL,'请仔细地阅读每一条，然后根据最近一段时间内的实际情况在选项中选择一项最符合您的情况。','0','2021-09-28 17:23:30',NULL,NULL),(63,'阿片戒断症状量表（OWS)',NULL,57,NULL,NULL,'如停药或未按时使用药物,是否出现下列症状或体征?\n','0','2021-09-28 17:23:54',NULL,NULL),(64,'成瘾研究中心量表（ARCI)',NULL,57,NULL,NULL,'注意:①请根据你自己的意见回答问题，不要空项。②回答问题根据你回忆刚刚用过药或饮过酒或吸完烟后的典型感受或心境，尽可能准确回答问题。\n','0','2021-09-28 17:24:14',NULL,NULL),(65,'阿片类依赖稽延性戒断症状评定量表',NULL,57,NULL,NULL,'请仔细地阅读每一条，然后根据最近一段时间内的实际情况在选项中选择一项最符合您的情况。','0','2021-09-28 17:25:15',NULL,NULL),(66,'海洛因渴求问卷(HCQ)',NULL,57,NULL,NULL,'下面是一些你现在的感觉或想法，请按照你的实际感受，\n在下列选项中选择最符合的一项。','0','2021-09-28 17:25:34',NULL,NULL),(67,'痴呆和相关量表',NULL,0,NULL,NULL,NULL,'0','2021-09-28 17:26:10',NULL,'——————'),(68,'简易智力状态检查(MMSE)',NULL,67,'老年痴呆',NULL,NULL,'0','2021-09-28 17:28:47','2021-09-29 10:52:42',NULL),(69,'常识-记忆-注意测验（IMCT)',NULL,67,NULL,NULL,NULL,'0','2021-09-28 17:29:58',NULL,NULL),(70,'痴呆简易筛查量表（BSSD)',NULL,67,'老年痴呆',NULL,NULL,'0','2021-09-28 17:30:36','2021-09-29 10:53:05',NULL),(71,'总体衰退量表(GDS)',NULL,67,NULL,NULL,NULL,'0','2021-09-28 17:32:11',NULL,NULL),(72,'临床痴呆评定（CDR)',NULL,67,'老年痴呆',NULL,NULL,'0','2021-09-28 17:33:53','2021-09-29 10:53:12',NULL),(73,'阿尔茨海默病病理行为评定量表(BEHAVE-AD)',NULL,67,NULL,NULL,NULL,'0','2021-09-28 17:35:11',NULL,NULL),(74,'神经精神症状问卷(NPI)',NULL,67,NULL,NULL,NULL,'0','2021-09-28 17:36:58',NULL,NULL),(75,'Hachinski缺血指数量表(HIS)',NULL,67,NULL,NULL,NULL,'0','2021-09-28 17:37:16',NULL,NULL),(76,'总评量表',NULL,0,NULL,NULL,NULL,'0','2021-09-28 17:37:35',NULL,'——————'),(77,'大体评定量表（GAS)',NULL,76,'各类精神疾患',NULL,NULL,'0','2021-09-28 17:39:13','2021-09-29 10:51:52',NULL),(78,'功能大体评定量表(GAF)',NULL,76,NULL,NULL,NULL,'0','2021-09-28 17:39:52',NULL,NULL),(79,'临床疗效总评量表(CGD）',NULL,76,'各类精神疾患',NULL,NULL,'0','2021-09-28 17:42:40','2021-09-29 10:52:18',NULL),(80,'社会和生活功能量表',NULL,0,NULL,NULL,NULL,'0','2021-09-28 17:44:17',NULL,'——————'),(81,'社会功能缺陷筛选量表（SDSS)',NULL,80,NULL,NULL,NULL,'0','2021-09-28 17:55:53',NULL,NULL),(82,'日常生活能力量表(ADL)',NULL,80,NULL,NULL,NULL,'0','2021-09-28 17:57:10',NULL,NULL),(83,'功能缺陷评定量表(WHO DAS-l)',NULL,80,NULL,NULL,NULL,'0','2021-09-28 17:58:08',NULL,NULL),(84,'席汉失能量表(SDS)',NULL,80,NULL,NULL,NULL,'0','2021-09-28 18:01:41',NULL,NULL),(85,'功能活动调查表(FAQ)',NULL,80,NULL,NULL,NULL,'0','2021-09-28 18:02:05',NULL,NULL),(86,'个人和社会功能量表(PSP)',NULL,80,NULL,NULL,NULL,'0','2021-09-28 18:02:25',NULL,NULL),(87,'生命质量评定量表',NULL,0,NULL,NULL,NULL,'0','2021-09-28 18:04:01',NULL,'——————'),(88,'健康状况调查问卷(SF- 36）',NULL,87,NULL,NULL,NULL,'0','2021-09-29 09:34:06',NULL,NULL),(89,'世界卫生组织生命质量测定量表(WHOQOL)',NULL,87,NULL,NULL,NULL,'0','2021-09-29 09:43:25',NULL,NULL),(90,'世界卫生组织生命质量测定量表100项(WHOQOL-100)',NULL,89,NULL,NULL,NULL,'0','2021-09-29 09:43:53',NULL,NULL),(91,'世界卫生组织生存质量测定量表简表(WHOQOL-BREF)',NULL,89,NULL,NULL,NULL,'0','2021-09-29 09:44:13',NULL,NULL),(92,'生活质量综合评定问卷(GQOLI)',NULL,87,NULL,NULL,NULL,'0','2021-09-29 09:44:52',NULL,NULL),(93,'生活事件量表',NULL,0,NULL,NULL,NULL,'0','2021-09-29 09:45:26',NULL,'——————'),(94,'生活事件量表(LES，协作组版)',NULL,93,NULL,NULL,NULL,'0','2021-09-29 09:46:38',NULL,NULL),(95,'生活事件量表(LES，湖南版)',NULL,93,NULL,NULL,NULL,'0','2021-09-29 09:47:00',NULL,NULL),(96,'社会支持量表',NULL,0,NULL,NULL,NULL,'0','2021-09-29 09:49:21',NULL,'——————'),(97,'社会支持评定量表(SSRS)',NULL,96,NULL,NULL,NULL,'0','2021-09-29 09:52:39',NULL,NULL),(98,'领悟社会支持量表（(PSSS)',NULL,96,NULL,NULL,NULL,'0','2021-09-29 09:53:32',NULL,NULL),(99,'儿童用量表',NULL,0,NULL,NULL,NULL,'0','2021-09-29 09:55:07',NULL,'——————'),(100,'Achenbach 儿童行为量表（CBCL)',NULL,99,'儿童行为问题',NULL,NULL,'0','2021-09-29 09:56:02','2021-09-29 10:49:48',NULL),(101,'Conners儿童行为问卷',NULL,99,NULL,NULL,NULL,'0','2021-09-29 09:56:40',NULL,NULL),(102,'Conners父母用症状问卷(PSQ)',NULL,101,NULL,NULL,NULL,'0','2021-09-29 09:57:51',NULL,NULL),(103,'Conners教师评定量表(TRS)',NULL,101,NULL,NULL,NULL,'0','2021-09-29 09:58:18',NULL,NULL),(104,'Conners简明症状问卷(ASQ)',NULL,101,NULL,NULL,NULL,'0','2021-09-29 09:58:38',NULL,NULL),(105,'Rutter 儿童行为量表',NULL,99,NULL,NULL,NULL,'0','2021-09-29 09:59:28',NULL,NULL),(106,'儿童焦虑性情绪障碍筛查表(SCARED)',NULL,99,NULL,NULL,NULL,'0','2021-09-29 10:00:21',NULL,NULL),(107,'儿童社交焦虑量表(SASC)',NULL,99,NULL,NULL,NULL,'0','2021-09-29 10:04:15',NULL,NULL),(108,'儿童抑郁障碍自评量表(DSRSC)',NULL,99,NULL,NULL,NULL,'0','2021-09-29 10:05:07',NULL,NULL),(109,'不良事件评定量表',NULL,0,NULL,NULL,NULL,'0','2021-09-29 10:06:39',NULL,'——————'),(110,'治疗时出现的症状量表（TESS)',NULL,109,'精神药物治疗',NULL,NULL,'0','2021-09-29 10:07:35','2021-09-29 10:53:50',NULL),(111,'UKU副作用量表(UKU-SERS)',NULL,109,NULL,NULL,NULL,'0','2021-09-29 10:11:26',NULL,NULL),(112,'Simpson锥体外系副作用评定量表(RSESE)',NULL,109,'精神药物治疗',NULL,NULL,'0','2021-09-29 10:18:31','2021-09-29 10:54:07',NULL),(113,'Barnes静坐不能量表(BARS)',NULL,109,NULL,NULL,NULL,'0','2021-09-29 10:18:58',NULL,NULL),(114,'不自主运动量表（AIMS)',NULL,109,NULL,NULL,NULL,'0','2021-09-29 10:37:51',NULL,NULL),(115,'迟发性运动障碍评定量表(TDRS)',NULL,109,NULL,NULL,NULL,'0','2021-09-29 10:38:16',NULL,NULL),(116,'抗抑郁药副作用量表（SERS)',NULL,109,NULL,NULL,NULL,'0','2021-09-29 10:38:35',NULL,NULL),(117,'护士用量表',NULL,0,NULL,NULL,NULL,'0','2021-09-29 10:39:17',NULL,'——————'),(118,'护士用住院患者观察量表（NOSIE)',NULL,117,'各类精神疾患',NULL,NULL,'0','2021-09-29 10:39:35','2021-09-29 10:51:02',NULL),(119,'护士用简明精神病量表(N-BPRS)',NULL,117,'各类精神疾患',NULL,NULL,'0','2021-09-29 10:40:15','2021-09-29 10:51:08',NULL),(120,'其他常用量表',NULL,0,NULL,NULL,NULL,'0','2021-09-29 10:40:40',NULL,'——————'),(121,'人格障碍诊断问卷(PDQ-4+)',NULL,120,NULL,NULL,NULL,'0','2021-09-29 10:41:14',NULL,NULL),(122,'改进版外显攻击行为量表(MOAS)','5',120,NULL,NULL,NULL,'0','2021-09-29 10:41:31','2021-09-29 11:01:45',NULL),(123,'自知力与治疗态度问卷(ITAQ)',NULL,120,NULL,NULL,NULL,'0','2021-09-29 10:41:49',NULL,NULL),(124,'自知力评定量表（SAUMD)',NULL,120,NULL,NULL,NULL,'0','2021-09-29 10:42:06',NULL,NULL),(125,'进食问题调查量表(EDI)','5',120,NULL,NULL,NULL,'0','2021-09-29 10:42:22','2021-09-29 10:58:16',NULL),(126,'家庭疾病负担量表（FBS)',NULL,120,NULL,NULL,NULL,'0','2021-09-29 10:42:39',NULL,NULL);

/*Table structure for table `scale_patient` */

DROP TABLE IF EXISTS `scale_patient`;

CREATE TABLE `scale_patient` (
  `patient_id` bigint(50) NOT NULL AUTO_INCREMENT COMMENT '患者ID',
  `patient_name` varchar(30) NOT NULL COMMENT '患者姓名',
  `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `age` int(20) DEFAULT NULL COMMENT '年龄',
  `hospital_number` bigint(50) DEFAULT NULL COMMENT '住院号',
  `ward` varchar(50) DEFAULT NULL COMMENT '病区',
  `test_date` datetime DEFAULT NULL COMMENT '检测日期',
  `job` varchar(20) DEFAULT NULL COMMENT '职业',
  `education` varchar(20) DEFAULT NULL COMMENT '学历',
  `nation` varchar(20) DEFAULT NULL COMMENT '民族',
  `marital_status` char(1) DEFAULT NULL COMMENT '婚姻状况(0未婚,1已婚)',
  `birthday` datetime DEFAULT NULL COMMENT '出生日期',
  `contanct_information` varchar(50) DEFAULT NULL COMMENT '联系方式',
  `source` varchar(100) DEFAULT NULL COMMENT '患者来源',
  `classification_coding` varchar(100) DEFAULT NULL COMMENT '分类编码',
  `diagnosis` varchar(50) DEFAULT NULL COMMENT '诊断',
  `del_flag` char(1) DEFAULT '0' COMMENT '是否删除(0存在，1删除)',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`patient_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='量表系统患者表';

/*Data for the table `scale_patient` */

insert  into `scale_patient`(`patient_id`,`patient_name`,`sex`,`age`,`hospital_number`,`ward`,`test_date`,`job`,`education`,`nation`,`marital_status`,`birthday`,`contanct_information`,`source`,`classification_coding`,`diagnosis`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`) values (2,'张三','0',10,11,'111',NULL,'农民','小学','汉族','0','2011-02-23 08:00:00','18858965241','门诊','正常','焦虑症','0',NULL,'2021-10-25 16:42:38',NULL,'2021-10-26 11:54:37'),(3,'李四','0',9,252525,'东区',NULL,'职员','高中','汉族','0','2012-10-22 08:00:00','15236568954','门诊','正常','焦虑症','0',NULL,'2021-10-26 14:55:35',NULL,NULL),(4,'王五','0',0,333,'222',NULL,'工人','小学','汉族','0','2021-10-04 08:00:00','15623548795','门诊','正常','焦虑症','0',NULL,'2021-10-28 13:32:21',NULL,NULL),(5,'刘平','0',10,456,'456',NULL,'工人','小学','汉族','0','2011-08-15 08:00:00','18523657521','门诊','正常','焦虑症','0',NULL,'2021-11-16 10:14:20',NULL,NULL),(6,'小红','1',11,NULL,NULL,NULL,'学生','大专','汉族','0','2010-01-04 08:00:00',NULL,'门诊',NULL,'焦虑症','0',NULL,'2021-11-26 16:42:56',NULL,NULL);

/*Table structure for table `scale_questions` */

DROP TABLE IF EXISTS `scale_questions`;

CREATE TABLE `scale_questions` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `scale_id` int(32) DEFAULT NULL COMMENT '量表id',
  `title` varchar(100) DEFAULT NULL COMMENT '题号',
  `content` varchar(1023) DEFAULT NULL COMMENT '内容',
  `options` varchar(1023) DEFAULT NULL COMMENT '选项',
  `answers` varchar(255) DEFAULT NULL COMMENT '答案',
  `resolution` varchar(1023) DEFAULT NULL COMMENT '问题解析',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=606 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='量表题目及分数';

/*Data for the table `scale_questions` */

insert  into `scale_questions`(`id`,`scale_id`,`title`,`content`,`options`,`answers`,`resolution`,`remarks`) values (1,9,'1','头痛','没有,很轻,中等,偏重,严重','1,2,3,4,5','你感觉头很痛',NULL),(2,9,'2','神经过敏，心里不踏实','没有,很轻,中等,偏重,严重','1,2,3,4,5','你感觉神经过敏不踏实',NULL),(5,9,'3','头脑中有不必要的想法或字句盘旋','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(6,9,'4','头昏或昏倒','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(7,9,'5','对异性的兴趣减退','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(8,9,'6','对旁人责备求全','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(9,9,'7','感到别人能控制您的思想','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(10,9,'8','责怪别人制造麻烦','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(11,9,'9','忘记性大','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(12,9,'10','担心自己的衣饰整齐及仪态的端正','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(13,9,'11','容易烦恼和激动','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(14,9,'12','胸痛','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(15,9,'13','害怕空旷的场所和街道','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(16,9,'14','感到自己的精力下降,活动减慢','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(17,9,'15','想结束自己的生命','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(18,9,'16','听到旁人听不到的声音','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(19,9,'17','发抖','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(20,9,'18','感到大多数人都不可信任','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(21,9,'19','胃口不好','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(22,9,'20','容易哭泣','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(23,9,'21','同异性相处时感到害羞不自在','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(24,9,'22','感到受骗、中了圈套或有人想抓住您','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(25,9,'23','无缘无故地突然感到害怕','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(26,9,'24','自己不能控制地发脾气','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(27,9,'25','怕单独出门','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(28,9,'26','经常责怪自己','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(29,9,'27','腰痛','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(30,9,'28','感到难以完成任务','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(31,9,'29','感到孤独','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(32,9,'30','感到苦闷','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(33,9,'31','过分担忧','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(34,9,'32','对事物不感兴趣','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(35,9,'33','感到害怕','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(36,9,'34','您的感情容易受到伤害','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(37,9,'35','旁人能知道您的私下想法','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(38,9,'36','感到别人不理解您、不同情您','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(39,9,'37','感到人们对您不友好,不喜欢您','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(40,9,'38','做事必须做得很慢以保证做得正确','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(41,9,'39','心跳得很厉害','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(42,9,'40','恶心或胃部不舒服','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(43,9,'41','感到比不上他人','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(44,9,'42','肌肉酸痛','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(45,9,'43','感到有人在监视您、谈论您','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(46,9,'44','难以入睡','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(47,9,'45','做事必须反复检查','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(48,9,'46','难以作出决定','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(49,9,'47','怕乘电车、公共汽车、地铁或火车','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(50,9,'48','呼吸有困难','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(51,9,'49','一阵阵发冷或发热','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(52,9,'50','因为感到害怕而避开某些东西、场合或活动','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(53,9,'51','脑子变空了','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(54,9,'52','身体发麻或刺痛','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(55,9,'53','喉咙有梗塞感','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(56,9,'54','感到没有前途没有希望','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(57,9,'55','不能集中注意力','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(58,9,'56','感到身体的某一部分软弱无力','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(59,9,'57','感到紧张或容易紧张','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(60,9,'58','感到手或脚发重','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(61,9,'59','想到死亡的事','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(62,9,'60','吃得太多','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(63,9,'61','当别人看着您或谈论您时感到不自在','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(64,9,'62','有一些不属于您自己的想法','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(65,9,'63','有想打人或伤害他人的冲动','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(66,9,'64','醒得太早','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(67,9,'65','必须反复洗手、点数目或触摸某些东西','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(68,9,'66','睡得不稳不深','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(69,9,'67','有想摔坏或破坏东西的冲动','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(70,9,'68','有一些别人没有的想法或念头','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(71,9,'69','感到对别人神经过敏','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(72,9,'70','在商店或电影院等人多的地方感到不自在','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(73,9,'71','感到任何事情都很困难','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(74,9,'72','一阵阵恐惧或惊恐','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(75,9,'73','感到在公共场合吃东西很不舒服','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(76,9,'74','经常与人争论','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(77,9,'75','单独一人时神经很紧张','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(78,9,'76','别人对您的成绩没有作出恰当的评价','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(79,9,'77','即使和别人在一起也感到孤单','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(80,9,'78','感到坐立不安心神不定','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(81,9,'79','感到自己没有什么价值','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(82,9,'80','感到熟悉的东西变成陌生或不像是真的','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(83,9,'81','大叫或摔东西','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(84,9,'82','害怕会在公共场合昏倒','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(85,9,'83','感到别人想占您的便宜','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(86,9,'84','为一些有关“性”的想法而很苦恼','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(87,9,'85','您认为应该因为自己的过错而受到惩罚','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(88,9,'86','感到要赶快把事情做完','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(89,9,'87','感到自己的身体有严重问题','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(90,9,'88','从未感到和其他人很亲近','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(91,9,'89','感到自己有罪','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(92,9,'90','感到自己的脑子有毛病','没有,很轻,中等,偏重,严重','1,2,3,4,5',NULL,NULL),(93,10,'1','因为担忧而睡眠太少','毫不,与平时差不多,比平时少些,比平时少很多','0,0,1,1',NULL,NULL),(94,10,'2','总是感到精神紧张','毫不,与平时差不多,比平时少些,比平时少很多','0,0,1,1',NULL,NULL),(95,10,'3','做事情时能够集中注意力','毫不,与平时差不多,比平时少些,比平时少很多','0,0,1,1',NULL,NULL),(96,10,'4','感到您在各方面起着有用的作用','毫不,与平时差不多,比平时少些,比平时少很多','0,0,1,1',NULL,NULL),(97,10,'5','能够敢于面对您的问题','毫不,与平时差不多,比平时少些,比平时少很多','0,0,1,1',NULL,NULL),(98,10,'6','感到对一些事情能够做出决定','毫不,与平时差不多,比平时少些,比平时少很多','0,0,1,1',NULL,NULL),(99,10,'7','感到无法克服您的困难','毫不,与平时差不多,比平时少些,比平时少很多','0,0,1,1',NULL,NULL),(100,10,'8','碰到事情有合情合理的比较高兴','毫不,与平时差不多,比平时少些,比平时少很多','0,0,1,1',NULL,NULL),(101,10,'9','喜爱您的日常活动','毫不,与平时差不多,比平时少些,比平时少很多','0,0,1,1',NULL,NULL),(102,10,'10','感到不高兴和压抑','毫不,与平时差不多,比平时少些,比平时少很多','0,0,1,1',NULL,NULL),(103,10,'11','对自己失去信心','毫不,与平时差不多,比平时少些,比平时少很多','0,0,1,1',NULL,NULL),(104,10,'12','想到自己是一个没用的人','毫不,与平时差不多,比平时少些,比平时少很多','0,0,1,1',NULL,NULL),(106,18,'1','对自身健康的过分关心，不考虑其主诉有无客观基础','无症状,很轻,轻度,中度,偏重,重度,极重','1,2,3,4,5,6,7','关心身体健康',NULL),(107,18,'2','对当前及未来情况的担心，恐惧或过分关注','无症状,很轻,轻度,中度,偏重,重度,极重','1,2,3,4,5,6,7','焦虑',NULL),(108,18,'3','患者在检查交谈时与检查者之间如同存在无形隔膜，无法实现正常的情感交流','无症状,很轻,轻度,中度,偏重,重度,极重','1,2,3,4,5,6,7','情感交流障碍',NULL),(109,18,'4','联想散漫、零乱和解体的程度','无症状,很轻,轻度,中度,偏重,重度,极重','1,2,3,4,5,6,7','概念紊乱',NULL),(110,18,'5','对以往言行的过分关心、内疚或懊悔','无症状,很轻,轻度,中度,偏重,重度,极重','1,2,3,4,5,6,7','罪恶观念',NULL),(111,18,'6','躯体性焦虑，即与焦虑有关的躯体运动表现','无症状,很轻,轻度,中度,偏重,重度,极重','1,2,3,4,5,6,7','紧张',NULL),(112,18,'7','不寻常的或不自然的运动性行为','无症状,很轻,轻度,中度,偏重,重度,极重','1,2,3,4,5,6,7','装相和作态',NULL),(113,18,'8','过分自负，确信具有不寻常的才能和权力等','无症状,很轻,轻度,中度,偏重,重度,极重','1,2,3,4,5,6,7','夸大',NULL),(114,18,'9','心境不佳、悲伤、沮丧或情绪低落的程度','无症状,很轻,轻度,中度,偏重,重度,极重','1,2,3,4,5,6,7','心境抑郁',NULL),(115,18,'10','对他人（不包括检查者)的仇恨、敌对和蔑视','无症状,很轻,轻度,中度,偏重,重度,极重','1,2,3,4,5,6,7','敌对性',NULL),(116,18,'11','检查当时认为有人正在或曾经恶意地对待他','无症状,很轻,轻度,中度,偏重,重度,极重','1,2,3,4,5,6,7','猜疑',NULL),(117,18,'12','没有相应外界刺激的感知','无症状,很轻,轻度,中度,偏重,重度,极重','1,2,3,4,5,6,7','幻觉',NULL),(118,18,'13','言语，动作和行为的减少和缓慢','无症状,很轻,轻度,中度,偏重,重度,极重','1,2,3,4,5,6,7','动作迟缓',NULL),(119,18,'14','会谈时对检查者的对立，不友好，不满意或不合作','无症状,很轻,轻度,中度,偏重,重度,极重','1,2,3,4,5,6,7','不合作',NULL),(120,18,'15','荒谬古怪的思维内容','无症状,很轻,轻度,中度,偏重,重度,极重','1,2,3,4,5,6,7','不寻常思维内容',NULL),(121,18,'16','情感基调低,明显缺乏相应的正常情感反应','无症状,很轻,轻度,中度,偏重,重度,极重','1,2,3,4,5,6,7','情感平淡',NULL),(122,18,'17','情感基调增高，激动，对外界反应增强','无症状,很轻,轻度,中度,偏重,重度,极重','1,2,3,4,5,6,7','兴奋',NULL),(123,18,'18','对人物、地点或时间分辨不清','无症状,很轻,轻度,中度,偏重,重度,极重','1,2,3,4,5,6,7','定向障碍',NULL),(124,19,'1','面部表情很少变化','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(125,19,'2','自发动作减少','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(126,19,'3','姿势表情缺乏','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(127,19,'4','眼神接触差','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(128,19,'5','无情感反应','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(129,19,'6','语调缺乏波动','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(130,19,'7','情感平淡总评','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(131,19,'8','语量贫乏','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(132,19,'9','言语内容贫乏','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(133,19,'10','言语中断','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(134,19,'11','应答迟缓','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(135,19,'12','言语障碍总评','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(136,19,'13','衣着及个人卫生差','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(137,19,'14','工作或学习不能持久','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(138,19,'15','躯体少动','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(139,19,'16','意志缺乏总评','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(140,19,'17','娱乐的兴致和活动减少','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(141,19,'18','性活动减少','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(142,19,'19','亲密感缺乏','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(143,19,'20','交友兴趣下降','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(144,19,'21','兴趣社交缺乏总评','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(145,19,'22','不注意社交','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(146,19,'23','心理测试时注意力不集中','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(147,19,'24','注意障碍总评','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(148,20,'1','听幻觉','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(149,20,'2','评论性幻听','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(150,20,'3','对话性幻听','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(151,20,'4','躯体或触幻觉','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(152,20,'5','嗅幻觉','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(153,20,'6','视幻觉','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(154,20,'7','幻觉总评','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(155,20,'8','被害妄想','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(156,20,'9','嫉妒妄想','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(157,20,'10','罪恶或过失妄想','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(158,20,'11','夸大妄想','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(159,20,'12','宗教妄想','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(160,20,'13','躯体妄想','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(161,20,'14','关系妄想','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(162,20,'15','被控制妄想','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(163,20,'16','读心妄想','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(164,20,'17','思想被广播','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(165,20,'18','思想被插入','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(166,20,'19','思维被夺','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(167,20,'20','妄想评分','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(168,20,'21','衣着和外表','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(169,20,'22','社交行为和性行为','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(170,20,'23','攻击和激越行为','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(171,20,'24','重复或刻板行为','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(172,20,'25','怪异行为总评','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(173,20,'26','出轨（联想散漫）','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(174,20,'27','言语不切题','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(175,20,'28','言语不连贯','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(176,20,'29','逻辑障碍','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(177,20,'30','赘述','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(178,20,'31','言语云集','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(179,20,'32','言语随境转移','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(180,20,'33','音联','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(181,20,'34','阳性思维形式障碍总评','无,可疑,轻度,中度,显著,严重','0,1,2,3,4,5',NULL,NULL),(182,21,'1','妄想','无,很轻,轻度,中度,偏重,重度,极重度','1,2,3,4,5,6,7','P1',NULL),(183,21,'2','联想散漫','无,很轻,轻度,中度,偏重,重度,极重度','1,2,3,4,5,6,7','P2',NULL),(184,21,'3','幻觉行为','无,很轻,轻度,中度,偏重,重度,极重度','1,2,3,4,5,6,7','P3',NULL),(185,21,'4','兴奋','无,很轻,轻度,中度,偏重,重度,极重度','1,2,3,4,5,6,7','P4',NULL),(186,21,'5','夸大','无,很轻,轻度,中度,偏重,重度,极重度','1,2,3,4,5,6,7','P5',NULL),(187,21,'6','猜疑/被害','无,很轻,轻度,中度,偏重,重度,极重度','1,2,3,4,5,6,7','P6',NULL),(188,21,'7','敌对性','无,很轻,轻度,中度,偏重,重度,极重度','1,2,3,4,5,6,7','P7',NULL),(189,21,'8','情感迟钝','无,很轻,轻度,中度,偏重,重度,极重度','1,2,3,4,5,6,7','N1',NULL),(190,21,'9','情感退缩','无,很轻,轻度,中度,偏重,重度,极重度','1,2,3,4,5,6,7','N2',NULL),(191,21,'10','情感交流障碍','无,很轻,轻度,中度,偏重,重度,极重度','1,2,3,4,5,6,7','N3',NULL),(192,21,'11','被动/冷漠社交退缩','无,很轻,轻度,中度,偏重,重度,极重度','1,2,3,4,5,6,7','N4',NULL),(193,21,'12','抽象思维困难','无,很轻,轻度,中度,偏重,重度,极重度','1,2,3,4,5,6,7','N5',NULL),(194,21,'13','交谈缺乏自发性和流畅性','无,很轻,轻度,中度,偏重,重度,极重度','1,2,3,4,5,6,7','N6',NULL),(195,21,'14','刻板思维','无,很轻,轻度,中度,偏重,重度,极重度','1,2,3,4,5,6,7','N7',NULL),(196,21,'15','关注身体健康','无,很轻,轻度,中度,偏重,重度,极重度','1,2,3,4,5,6,7','G1',NULL),(197,21,'16','焦虑','无,很轻,轻度,中度,偏重,重度,极重度','1,2,3,4,5,6,7','G2',NULL),(198,21,'17','自罪感','无,很轻,轻度,中度,偏重,重度,极重度','1,2,3,4,5,6,7','G3',NULL),(199,21,'18','紧张','无,很轻,轻度,中度,偏重,重度,极重度','1,2,3,4,5,6,7','G4',NULL),(200,21,'19','装相和作态','无,很轻,轻度,中度,偏重,重度,极重度','1,2,3,4,5,6,7','G5',NULL),(201,21,'20','抑郁','无,很轻,轻度,中度,偏重,重度,极重度','1,2,3,4,5,6,7','G6',NULL),(202,21,'21','动作迟缓','无,很轻,轻度,中度,偏重,重度,极重度','1,2,3,4,5,6,7','G7',NULL),(203,21,'22','不合作','无,很轻,轻度,中度,偏重,重度,极重度','1,2,3,4,5,6,7','G8',NULL),(204,21,'23','不寻常思维内容','无,很轻,轻度,中度,偏重,重度,极重度','1,2,3,4,5,6,7','G9',NULL),(205,21,'24','定向障碍','无,很轻,轻度,中度,偏重,重度,极重度','1,2,3,4,5,6,7','G10',NULL),(206,21,'25','注意障碍','无,很轻,轻度,中度,偏重,重度,极重度','1,2,3,4,5,6,7','G11',NULL),(207,21,'26','判断和自知力缺乏','无,很轻,轻度,中度,偏重,重度,极重度','1,2,3,4,5,6,7','G12',NULL),(208,21,'27','意志障碍','无,很轻,轻度,中度,偏重,重度,极重度','1,2,3,4,5,6,7','G13',NULL),(209,21,'28','冲动控制缺乏','无,很轻,轻度,中度,偏重,重度,极重度','1,2,3,4,5,6,7','G14',NULL),(210,21,'29','先占观念','无,很轻,轻度,中度,偏重,重度,极重度','1,2,3,4,5,6,7','G15',NULL),(211,21,'30','主动回避社交','无,很轻,轻度,中度,偏重,重度,极重度','1,2,3,4,5,6,7','G16',NULL),(212,21,'31','愤怒','无,很轻,轻度,中度,偏重,重度,极重度','1,2,3,4,5,6,7','S1',NULL),(213,21,'32','延迟满足困难','无,很轻,轻度,中度,偏重,重度,极重度','1,2,3,4,5,6,7','S2',NULL),(214,21,'33','情感不稳','无,很轻,轻度,中度,偏重,重度,极重度','1,2,3,4,5,6,7','S3',NULL),(215,22,'1','抑郁','无,轻度,中度,显著,严重','0,1,2,3,4','根据回答评',NULL),(216,22,'2','焦虑','无,轻度,中度,显著,严重','0,1,2,3,4','根据回答评',NULL),(217,22,'3','情感平淡或不协调','无,轻度,中度,显著,严重','0,1,2,3,4','根据回答评',NULL),(218,22,'4','精神运动性迟缓','无,轻度,中度,显著,严重','0,1,2,3,4','根据回答评',NULL),(219,22,'5','连续表达的妄想','无,轻度,中度,显著,严重','0,1,2,3,4','根据回答评',NULL),(220,22,'6','幻觉','无,轻度,中度,显著,严重','0,1,2,3,4','根据回答评',NULL),(221,22,'7','言语散漫或不连贯','无,轻度,中度,显著,严重','0,1,2,3,4','根据回答评',NULL),(222,22,'8','言语贫乏，缄默','无,轻度,中度,显著,严重','0,1,2,3,4','根据回答评',NULL),(223,22,'9','震颤','无,轻度,显著','0,1,2','不良反应',NULL),(224,22,'10','肌强直','无,轻度,显著','0,1,2','不良反应',NULL),(225,22,'11','肌张力异常','无,轻度,显著','0,1,2','不良反应',NULL),(226,22,'12','静坐不能','无,轻度,显著','0,1,2','不良反应',NULL),(227,22,'13','视力模糊','无,轻度,显著','0,1,2','不良反应',NULL),(228,22,'14','其他','无,轻度,显著','0,1,2','不良反应',NULL),(229,24,'1','动作','无,轻度,中度,较重,重度','0,1,2,3,4',NULL,NULL),(230,24,'2','言语','无,轻度,中度,较重,重度','0,1,2,3,4',NULL,NULL),(231,24,'3','意念飘忽','无,轻度,中度,较重,重度','0,1,2,3,4',NULL,NULL),(232,24,'4','语音/喧闹程度','无,轻度,中度,较重,重度','0,1,2,3,4',NULL,NULL),(233,24,'5','敌意/破坏行为','无,轻度,中度,较重,重度','0,1,2,3,4',NULL,NULL),(234,24,'6','情绪','无,轻度,中度,较重,重度','0,1,2,3,4',NULL,NULL),(235,24,'7','自我批评','无,轻度,中度,较重,重度','0,1,2,3,4',NULL,NULL),(236,24,'8','接触','无,轻度,中度,较重,重度','0,1,2,3,4',NULL,NULL),(237,24,'9','睡眠','无,轻度,中度,较重,重度','0,1,2,3,4',NULL,NULL),(238,24,'10','性兴趣','无,轻度,中度,较重,重度','0,1,2,3,4',NULL,NULL),(239,24,'11','工作初评','无,轻度,中度,较重,重度','0,1,2,3,4',NULL,NULL),(240,24,'12','工作周评','无,轻度,中度,较重,重度','0,1,2,3,4',NULL,NULL),(241,24,'13','幻觉','无,轻度,中度,较重,重度','0,1,2,3,4',NULL,NULL),(242,24,'14','妄想','无,轻度,中度,较重,重度','0,1,2,3,4',NULL,NULL),(243,25,'1','心境高涨','无,问及时有轻度或可能的心境高涨,主观感到有肯定的心境高涨；乐观；自信；愉悦；与内容相称,心境高涨；与内容不相符；滑稽可笑,欣快；不适当地发笑；唱歌','0,1,2,3,4',NULL,NULL),(244,25,'2','活动/精力增加','无,主观感觉增加,活跃；手势增多,精力过剩；时有活动过多；不安宁,运动性兴奋；时序活动过多','0,1,2,3,4',NULL,NULL),(245,25,'3','性兴趣','正常；没有增加,轻度或可能增加,问及时主观感到肯定有性兴趣增加,自发性的性内容；详细描述有关性的事,明显的性举动','0,1,2,3,4',NULL,NULL),(246,25,'4','睡眠','自述没有睡眠减少,睡眠比平时减少1小时或以下,睡眠比平时减少1小时以上,自述睡眠需求减少,否认需要睡眠','0,1,2,3,4',NULL,NULL),(247,25,'5','易激惹','无,主观感到易激惹,检查中有时易激惹；近期在病房中有愤怒或恼怒发作,检查中经常不耐烦；自始至终回答简短生硬,敌意；不合作；检查无法进行','0,2,4,6,8',NULL,NULL),(248,25,'6','言语语速语量','无增多,感觉话多,时有语速语量增加或啰嗦,言语紧迫；语速语量持续增加；难以打断,急迫；无法打断；说个不停','0,2,4,6,8',NULL,NULL),(249,25,'7','语言思维障碍','无,赘述；轻度分散；思维敏捷,分散；缺乏思维目标；经常改变话题；思维加速,思想奔逸；离题；难以跟上其思路；音联；模仿言语,语无伦次；无法交流','0,1,2,3,4',NULL,NULL),(250,25,'8','思维内容','正常,可疑的计划；新的兴趣,特殊的计划；超宗教观念,夸大或偏执观念；牵连观念,妄想；幻觉','0,2,4,6,8',NULL,NULL),(251,25,'9','破坏攻击行为','无；合作,好讥讽；时长提高嗓门；戒备,要求过多；在病房中威胁,威胁检查者；大声喊叫；检查困难,好斗；破坏性；无法检查','0,2,4,6,8',NULL,NULL),(252,25,'10','外表','穿戴修饰得体,稍微仪态不整,修饰不佳；中度蓬乱；过分穿着,穿戴蓬乱；衣冠不整；过分地化妆,极度邋遢；奇装异服','0,1,2,3,4',NULL,NULL),(253,25,'11','自知力','有；承认有病；同意需要治疗,承认可能有病,承认有行为改变；但否认有病,承认可能有行为改变；但否认有病,否认有任何行为改变','0,1,2,3,4',NULL,NULL),(254,27,'1','你感到非常好或者非常开心，但其他人认为你与平时不一样，或者因为特别开心，兴奋而给你带来麻烦？','是,否','1,0',NULL,NULL),(255,27,'2','你特别容易激动，好指责他人，或易斗殴或争吵？','是,否','1,0',NULL,NULL),(256,27,'3','你比平时更充满自信？','是,否','1,0',NULL,NULL),(257,27,'4','睡眠比平时明显减少，但你并不感到缺乏睡眠？','是,否','1,0',NULL,NULL),(258,27,'5','你比平时更健谈或讲话特别快？','是,否','1,0',NULL,NULL),(259,27,'6','你感到思维迅速、想法特别多，或者难以减慢你的思维？','是,否','1,0',NULL,NULL),(260,27,'7','你很容易随境转移,注意力很难集中,或很难专心做一件事？','是,否','1,0',NULL,NULL),(261,27,'8','你比平时更加精力充沛？','是,否','1,0',NULL,NULL),(262,27,'9','你比平时更加积极主动或忙忙碌碌？','是,否','1,0',NULL,NULL),(263,27,'10','你比平时更加乐于社交或外出，例如半夜还打电话给朋友？','是,否','1,0',NULL,NULL),(264,27,'11','你比平时性兴趣更强烈？','是,否','1,0',NULL,NULL),(265,27,'12','你与平时处事的方式不一样，使得他人感到过分、愚蠢、或者太危险了？','是,否','1,0',NULL,NULL),(266,27,'13','花钱大方给你或家人带来麻烦？','是,否','1,0',NULL,NULL),(267,27,'14','如果上述答案中有1个以上为是的话，是否有上述几种情况同时期发生？','是,否','1,0',NULL,NULL),(268,27,'15','上述这些情况给你造成多大问题——例如工作、家庭、经济或司法问题，争吵或斗殴等？','没有问题,轻度,中度,严重问题','0,1,2,3',NULL,NULL),(269,29,'1','抑郁情绪','无,轻度,中度,较重,重度','0,1,2,3,4',NULL,NULL),(270,29,'2','有罪感','无,轻度,中度,较重,重度','0,1,2,3,4',NULL,NULL),(271,29,'3','自杀','无,轻度,中度,较重,重度','0,1,2,3,4',NULL,NULL),(272,29,'4','入睡困难','无,轻度,重度','0,1,2',NULL,NULL),(273,29,'5','睡眠不深','无,轻度,重度','0,1,2',NULL,NULL),(274,29,'6','早醒','无,轻度,重度','0,1,2',NULL,NULL),(275,29,'7','工作和兴趣','无,轻度,中度,较重,重度','0,1,2,3,4',NULL,NULL),(276,29,'8','阻滞','无,轻度,中度,较重,重度','0,1,2,3,4',NULL,NULL),(277,29,'9','激越','无,轻度,中度,较重,重度','0,1,2,3,4',NULL,NULL),(278,29,'10','精神性焦虑','无,轻度,中度,较重,重度','0,1,2,3,4',NULL,NULL),(279,29,'11','躯体性焦虑','无,轻度,中度,较重,重度','0,1,2,3,4',NULL,NULL),(280,29,'12','胃肠道症状','无,轻度,重度','0,1,2',NULL,NULL),(281,29,'13','全身症状','无,轻度,重度','0,1,2',NULL,NULL),(282,29,'14','性症状','无,轻度,重度','0,1,2',NULL,NULL),(283,29,'15','疑病','无,轻度,中度,较重,重度','0,1,2,3,4',NULL,NULL),(284,29,'16','体重减轻','无,轻度,重度','0,1,2',NULL,NULL),(285,29,'17','自知力','无,轻度,重度','0,1,2',NULL,NULL),(286,29,'18','日夜变化：早','无,轻度,重度','0,1,2',NULL,NULL),(287,29,'19','日夜变化：完','无,轻度,重度','0,1,2',NULL,NULL),(288,29,'20','人格或现实解体','无,轻度,中度,较重,重度','0,1,2,3,4',NULL,NULL),(289,29,'21','偏执症状','无,轻度,中度,较重,重度','0,1,2,3,4',NULL,NULL),(290,29,'22','强迫症状','无,轻度,重度','0,1,2',NULL,NULL),(291,29,'23','能力减退感','无,轻度,中度,较重,重度','0,1,2,3,4',NULL,NULL),(292,29,'24','绝望','无,轻度,中度,较重,重度','0,1,2,3,4',NULL,NULL),(293,29,'25','自卑感','无,轻度,中度,较重,重度','0,1,2,3,4',NULL,NULL),(294,30,'1','观察到的抑郁','无,轻度,中度,重度','0,2,4,6',NULL,NULL),(295,30,'2','抑郁倾诉','无,轻度,中度,重度','0,2,4,6',NULL,NULL),(296,30,'3','内心紧张','无,轻度,中度,重度','0,2,4,6',NULL,NULL),(297,30,'4','睡眠减少','无,轻度,中度,重度','0,2,4,6',NULL,NULL),(298,30,'5','食欲减退','无,轻度,中度,重度','0,2,4,6',NULL,NULL),(299,30,'6','注意集中困难','无,轻度,中度,重度','0,2,4,6',NULL,NULL),(300,30,'7','懒散','无,轻度,中度,重度','0,2,4,6',NULL,NULL),(301,30,'8','感受不能','无,轻度,中度,重度','0,2,4,6',NULL,NULL),(302,30,'9','悲观思想','无,轻度,中度,重度','0,2,4,6',NULL,NULL),(303,30,'10','自杀观念','无,轻度,中度,重度','0,2,4,6',NULL,NULL),(304,31,'1','适当的人格','不符合,符合','0,1',NULL,NULL),(305,31,'2','无相应的心因','不符合,符合','0,2',NULL,NULL),(306,31,'3','抑郁独特的性质，持续性悲哀','不符合,符合','0,1',NULL,NULL),(307,31,'4','体重减轻','不符合,符合','0,2',NULL,NULL),(308,31,'5','既往抑郁发作','不符合,符合','0,-1',NULL,NULL),(309,31,'6','精神运动退滞','不符合,符合','0,2',NULL,NULL),(310,31,'7','焦虑','不符合,符合','0,-1',NULL,NULL),(311,31,'8','虚无妄想','不符合,符合','0,2',NULL,NULL),(312,31,'9','责任他人','不符合,符合','0,-1',NULL,NULL),(313,31,'10','罪恶感','不符合,符合','0,2',NULL,NULL),(314,32,'1','我能够大笑和看到事物可爱的一面','像过去一样多,不那么多,肯定没那么多,根本没有了','0,1,2,3',NULL,NULL),(315,32,'2','我看待事物的乐趣与过去一样多','像过去一样多,不那么多,肯定没那么多,几乎没有了','0,1,2,3',NULL,NULL),(316,32,'3','当事情做错时，我过分责备自己','多数时间是这样,有时是这样,很少是这样,从来不这样','3,2,1,0',NULL,NULL),(317,32,'4','我无缘无故焦虑和担心','从来没有,几乎没有,有时是这样,经常是这样','0,1,2,3',NULL,NULL),(318,32,'5','我感到无原因的害怕和恐惧','经常是这样,有时是这样,很少是这样,从来没有','3,2,1,0',NULL,NULL),(319,32,'6','事情压在我头上','绝大多数时候我不能应付,有时我不能像平时那样处理好,多数时候能处理好,和平时一样处理得很好','3,2,1,0',NULL,NULL),(320,32,'7','我很不愉快而睡眠困难','多数时间是这样,有时是这样,很少是这样,从来不这样','3,2,1,0',NULL,NULL),(321,32,'8','我感到伤心悲惨','绝大多数时候,经常,有时,从来没有','3,2,1,0',NULL,NULL),(322,32,'9','我不愉快而哭泣','绝大多数时候,经常,偶尔有,从来没有','3,2,1,0',NULL,NULL),(323,32,'10','我有伤害自己的想法','是的，非常普遍,有时候有,几乎没有,从来没有','3,2,1,0',NULL,NULL),(324,33,'1','我因一些小事而烦恼','没有或几乎没有,少有,常有,几乎一直有','3,2,1,0',NULL,NULL),(325,33,'2','我不大想吃东西，我的胃口不好','没有或几乎没有,少有,常有,几乎一直有','3,2,1,0',NULL,NULL),(326,33,'3','即使家属和朋友帮助我，我仍然无法摆脱心中的苦闷','没有或几乎没有,少有,常有,几乎一直有','3,2,1,0',NULL,NULL),(327,33,'4','我觉得我和一般人一样好','没有或几乎没有,少有,常有,几乎一直有','0,1,2,3',NULL,NULL),(328,33,'5','我在做事时无法集中自己的注意力','没有或几乎没有,少有,常有,几乎一直有','3,2,1,0',NULL,NULL),(329,33,'6','我感到情绪低沉','没有或几乎没有,少有,常有,几乎一直有','3,2,1,0',NULL,NULL),(330,33,'7','我感到做任何事都很费力','没有或几乎没有,少有,常有,几乎一直有','3,2,1,0',NULL,NULL),(331,33,'8','我觉得前途是有希望的','没有或几乎没有,少有,常有,几乎一直有','0,1,2,3',NULL,NULL),(332,33,'9','我觉得我的生活是失败的','没有或几乎没有,少有,常有,几乎一直有','3,2,1,0',NULL,NULL),(333,33,'10','我感到害怕','没有或几乎没有,少有,常有,几乎一直有','3,2,1,0',NULL,NULL),(334,33,'11','我的睡眠情况不好','没有或几乎没有,少有,常有,几乎一直有','3,2,1,0',NULL,NULL),(335,33,'12','我感到高兴','没有或几乎没有,少有,常有,几乎一直有','0,1,2,3',NULL,NULL),(336,33,'13','我比平时说话要少','没有或几乎没有,少有,常有,几乎一直有','3,2,1,0',NULL,NULL),(337,33,'14','我感到孤单','没有或几乎没有,少有,常有,几乎一直有','3,2,1,0',NULL,NULL),(338,33,'15','我觉得人们对我不太友好','没有或几乎没有,少有,常有,几乎一直有','3,2,1,0',NULL,NULL),(339,33,'16','我觉得生活得很有意思','没有或几乎没有,少有,常有,几乎一直有','0,1,2,3',NULL,NULL),(340,33,'17','我曾哭泣','没有或几乎没有,少有,常有,几乎一直有','3,2,1,0',NULL,NULL),(341,33,'18','我感到忧虑','没有或几乎没有,少有,常有,几乎一直有','3,2,1,0',NULL,NULL),(342,33,'19','我觉得人们不喜欢我','没有或几乎没有,少有,常有,几乎一直有','3,2,1,0',NULL,NULL),(343,33,'20','我觉得无法继续我的日常工作','没有或几乎没有,少有,常有,几乎一直有','3,2,1,0',NULL,NULL),(344,34,'1','我觉得闷闷不乐情绪低沉','没有或很少时间,少部分时间,相当多时间,绝大部分或全部时间','1,2,3,4',NULL,NULL),(345,34,'2','我觉得一天中早晨最好','没有或很少时间,少部分时间,相当多时间,绝大部分或全部时间','4,3,2,1',NULL,NULL),(346,34,'3','我一阵阵哭出来或觉得想哭','没有或很少时间,少部分时间,相当多时间,绝大部分或全部时间','1,2,3,4',NULL,NULL),(347,34,'4','我晚上睡眠不好','没有或很少时间,少部分时间,相当多时间,绝大部分或全部时间','1,2,3,4',NULL,NULL),(348,34,'5','我吃的跟平常一样多','没有或很少时间,少部分时间,相当多时间,绝大部分或全部时间','4,3,2,1',NULL,NULL),(349,34,'6','我与异性密切接触时和以往一样感到愉快','没有或很少时间,少部分时间,相当多时间,绝大部分或全部时间','4,3,2,1',NULL,NULL),(350,34,'7','我发觉我的体重在下降','没有或很少时间,少部分时间,相当多时间,绝大部分或全部时间','1,2,3,4',NULL,NULL),(351,34,'8','我有便秘的苦恼','没有或很少时间,少部分时间,相当多时间,绝大部分或全部时间','1,2,3,4',NULL,NULL),(352,34,'9','我心跳比平常快','没有或很少时间,少部分时间,相当多时间,绝大部分或全部时间','1,2,3,4',NULL,NULL),(353,34,'10','我无缘无故地感到疲乏','没有或很少时间,少部分时间,相当多时间,绝大部分或全部时间','1,2,3,4',NULL,NULL),(354,34,'11','我的头脑跟平常一样清楚','没有或很少时间,少部分时间,相当多时间,绝大部分或全部时间','4,3,2,1',NULL,NULL),(355,34,'12','我觉得经常做的事并没有困难','没有或很少时间,少部分时间,相当多时间,绝大部分或全部时间','4,3,2,1',NULL,NULL),(356,34,'13','我觉得不安而安静不下来','没有或很少时间,少部分时间,相当多时间,绝大部分或全部时间','1,2,3,4',NULL,NULL),(357,34,'14','我对将来抱有希望','没有或很少时间,少部分时间,相当多时间,绝大部分或全部时间','4,3,2,1',NULL,NULL),(358,34,'15','我比平常容易生气激动','没有或很少时间,少部分时间,相当多时间,绝大部分或全部时间','1,2,3,4',NULL,NULL),(359,34,'16','我觉得做出决定是容易的','没有或很少时间,少部分时间,相当多时间,绝大部分或全部时间','4,3,2,1',NULL,NULL),(360,34,'17','我觉得自己是个有用的人，有人需要我','没有或很少时间,少部分时间,相当多时间,绝大部分或全部时间','4,3,2,1',NULL,NULL),(361,34,'18','我的生活过得很有意思','没有或很少时间,少部分时间,相当多时间,绝大部分或全部时间','4,3,2,1',NULL,NULL),(362,34,'19','我认为如果我死了，别人会过得好些','没有或很少时间,少部分时间,相当多时间,绝大部分或全部时间','1,2,3,4',NULL,NULL),(363,34,'20','平常感兴趣的事我仍然感兴趣','没有或很少时间,少部分时间,相当多时间,绝大部分或全部时间','4,3,2,1',NULL,NULL),(364,35,'1','抑郁','我不感到抑郁,我感到抑郁或沮丧,我整天抑郁无法摆脱,我十分抑郁已经承受不住','0,1,2,3',NULL,NULL),(365,35,'2','悲观','我对未来并不悲观失望,我感到前途不太乐观,我感到我对前途不抱希望,我感到今后毫无希望不可能有所好转','0,1,2,3',NULL,NULL),(366,35,'3','失败感','我并无失败的感觉,我觉得和大多数人相比我是失败的,回顾我的一生、我觉得那是一连串的失败,我觉得我是个彻底失败的人','0,1,2,3',NULL,NULL),(367,35,'4','满意感缺如','我并不觉得有什么不满意,我觉得我不能像平时那样享受生活,任何事情都不能使我感到满意一些,我对所有的事情都不满意','0,1,2,3',NULL,NULL),(368,35,'5','自罪感','我没有特殊的内疚感,我有时感到内疚或觉得自己没价值,我感到非常内疚,我觉得自己非常坏、一文不值','0,1,2,3',NULL,NULL),(369,35,'6','自我失望感','我没有对自己感到失望,我对自己感到失望,我讨厌自己,我憎恨自己','0,1,2,3',NULL,NULL),(370,35,'7','消极倾向','我没有要伤害自己的想法,我感到还是死掉的好,我考虑过自杀,如果有机会、我还会杀了自己','0,1,2,3',NULL,NULL),(371,35,'8','社交退缩','我没失去和他人交往的兴趣,和平时相比、我和他人交往的兴趣有所减退,我已失去大部分和人交往的兴趣、我对他们没有感情,我对他人全无兴趣、也完全不理睬别人','0,1,2,3',NULL,NULL),(372,35,'9','犹豫不决','我能像平时一样做出决断,我尝试避免做决断,对我而言、做出决断十分困难,我无法做出任何决断','0,1,2,3',NULL,NULL),(373,35,'10','自我形象改变','我觉得我的形象一点也不比过去糟,我担心我看起来老了、不吸引人了,我觉得我的外表肯定变了、变得不具吸引力,我感到我的形象丑陋且讨人厌','0,1,2,3',NULL,NULL),(374,35,'11','工作可能','我能像平时那样工作,我做事时要花额外的努力才能开始,我必须强迫自己、我方能干事,我完全不能做事情','0,1,2,3',NULL,NULL),(375,35,'12','疲乏感','和以往相比、我并不容易疲倦,我比过去容易觉得疲乏,我做任何事都感到疲乏,我太容易疲乏了、不能干任何事','0,1,2,3',NULL,NULL),(376,35,'13','食欲丧失','我的胃口不必过去差,我的胃口没有过去那样好,现在我的胃口比过去差多了,我一点食欲都没有','0,1,2,3',NULL,NULL),(377,36,'1','我感到紧张或痛苦','几乎所有时候,大多数时候,有时,根本没有','3,2,1,0','A',NULL),(378,36,'2','我对以往感兴趣的事情还是有兴趣','肯定一样,不像以前那样多,只有一点儿,基本上没有了','0,1,2,3','D',NULL),(379,36,'3','我感到有点害怕、好像预感到有什么可怕的事情要发生','非常肯定和非常严重,是有、但并不太严重,有一点、但并不使我苦恼,根本没有','3,2,1,0','A',NULL),(380,36,'4','我能够哈哈大笑、并看到事物好的一面','我经常这样,现在已经不大这样了,现在肯定是不太多了,根本没有','0,1,2,3','D',NULL),(381,36,'5','我的心中充满烦恼','大多数时间,常常如此,时有、但不经常,偶然如此','3,2,1,0','A',NULL),(382,36,'6','我感到愉快','根本没有,并不经常,有时,大多数','3,2,1,0','D',NULL),(383,36,'7','我能够安心因而轻松地坐着','肯定,经常,并不经常,根本没有','0,1,2,3','A',NULL),(384,36,'8','我对自己的仪容失去兴趣','肯定,并不像我应该做到的那样关心,我可能不是非常关心,我仍像往常一样关心','3,2,1,0','D',NULL),(385,36,'9','我有点坐立不安、好像感到非要活动不可','确实非常多,是不少,并不很多,根本没有','3,2,1,0','A',NULL),(386,36,'10','我对一切都是乐观地向前看','差不多是这样做的,并不完全是这样做的,很少这样做,几乎从来不这样做','0,1,2,3','D',NULL),(387,36,'11','我突然发现恐慌感','确实很经常,时常,并非经常,根本没有','3,2,1,0','A',NULL),(388,36,'12','我好想感到情绪在渐渐低落','几乎所有时候,大多数时候,有时,根本没有','3,2,1,0','D',NULL),(389,36,'13','我感到有点害怕、好像某个内脏器官变坏了','根本没有,有时,很经常,非常经常','0,1,2,3','A',NULL),(390,36,'14','我能欣赏一本好书或一个好的广播或电视节目','常常,有时,并非经常,很少','0,1,2,3','D',NULL),(391,37,'1','做什么事都感到没有兴趣或乐趣','完全不会,几天,一半以上的日子,几乎每天','0,1,2,3',NULL,NULL),(392,37,'2','感到心情低落','完全不会,几天,一半以上的日子,几乎每天','0,1,2,3',NULL,NULL),(393,37,'3','入睡困难、很难熟睡或睡太多','完全不会,几天,一半以上的日子,几乎每天','0,1,2,3',NULL,NULL),(394,37,'4','感到疲劳或无精打采','完全不会,几天,一半以上的日子,几乎每天','0,1,2,3',NULL,NULL),(395,37,'5','胃口不好或吃太多','完全不会,几天,一半以上的日子,几乎每天','0,1,2,3',NULL,NULL),(396,37,'6','觉得自己很糟、或很失败、或让自己或家里人失望','完全不会,几天,一半以上的日子,几乎每天','0,1,2,3',NULL,NULL),(397,37,'7','注意很难集中、例如阅读报纸或看电视','完全不会,几天,一半以上的日子,几乎每天','0,1,2,3',NULL,NULL),(398,37,'8','动作或说话速度缓慢到别人可察觉的程度、或正好相反——您烦躁或坐立不安、动来动去的情况比平时更严重','完全不会,几天,一半以上的日子,几乎每天','0,1,2,3',NULL,NULL),(399,37,'9','有不如死掉或用某种方式伤害自己的念头','完全不会,几天,一半以上的日子,几乎每天','0,1,2,3',NULL,NULL),(400,38,'1','你对生活基本上满意吗？','是,否','0,1',NULL,NULL),(401,38,'2','你是否已放弃了许多活动与兴趣？','是,否','1,0',NULL,NULL),(402,38,'3','你是否觉得生活空虚？','是,否','1,0',NULL,NULL),(403,38,'4','你是否常感到厌倦？','是,否','1,0',NULL,NULL),(404,38,'5','你觉得未来有希望吗？','是,否','0,1',NULL,NULL),(405,38,'6','你是否因为脑子里的一些想法摆脱不掉而烦恼？','是,否','1,0',NULL,NULL),(406,38,'7','你是否大部分时间精力充沛？','是,否','0,1',NULL,NULL),(407,38,'8','你是否害怕会有不幸的事落到你头上？','是,否','1,0',NULL,NULL),(408,38,'9','你是否大部分时间感到幸福？','是,否','0,1',NULL,NULL),(409,38,'10','你是否常感到孤立无援？','是,否','1,0',NULL,NULL),(410,38,'11','你是否经常坐立不安、心烦意乱？','是,否','1,0',NULL,NULL),(411,38,'12','你是否希望待在家里而不愿去做些新鲜事？','是,否','1,0',NULL,NULL),(412,38,'13','你是否常常担心将来？','是,否','1,0',NULL,NULL),(413,38,'14','你是否觉得记忆力比以前差？','是,否','1,0',NULL,NULL),(414,38,'15','你觉得现在活的很惬意吗？','是,否','0,1',NULL,NULL),(415,38,'16','你是否常感到心情沉重、郁闷','是,否','1,0',NULL,NULL),(416,38,'17','你是否觉得像现在这样或者毫无意义？','是,否','1,0',NULL,NULL),(417,38,'18','你是否总为过去的事忧愁？','是,否','1,0',NULL,NULL),(418,38,'19','你觉得生活很令人兴奋吗？','是,否','0,1',NULL,NULL),(419,38,'20','你开始一件新的工作很困难吗？','是,否','1,0',NULL,NULL),(420,38,'21','你觉得生活充满活力吗？','是,否','0,1',NULL,NULL),(421,38,'22','你是否觉得你的处境已毫无希望？','是,否','1,0',NULL,NULL),(422,38,'23','你是否觉得大多数人比你强得多','是,否','1,0',NULL,NULL),(423,38,'24','你是否常为些小事伤心？','是,否','1,0',NULL,NULL),(424,38,'25','你是否常觉得想哭？','是,否','1,0',NULL,NULL),(425,38,'26','你集中精力有困难吗？','是,否','1,0',NULL,NULL),(426,38,'27','你早晨起来很快活吗？','是,否','0,1',NULL,NULL),(427,38,'28','你希望避开聚会吗？','是,否','1,0',NULL,NULL),(428,38,'29','你做决定很容易吗？','是,否','0,1',NULL,NULL),(429,38,'30','你的头脑像往常一样清晰吗？','是,否','0,1',NULL,NULL),(430,40,'1','焦虑心境','无,轻度,中度,较重,极重','0,1,2,3,4',NULL,NULL),(431,40,'2','紧张','无,轻度,中度,较重,极重','0,1,2,3,4',NULL,NULL),(432,40,'3','害怕','无,轻度,中度,较重,极重','0,1,2,3,4',NULL,NULL),(433,40,'4','失眠','无,轻度,中度,较重,极重','0,1,2,3,4',NULL,NULL),(434,40,'5','记忆或注意障碍','无,轻度,中度,较重,极重','0,1,2,3,4',NULL,NULL),(435,40,'6','抑郁心境','无,轻度,中度,较重,极重','0,1,2,3,4',NULL,NULL),(436,40,'7','肌肉系统症状','无,轻度,中度,较重,极重','0,1,2,3,4',NULL,NULL),(437,40,'8','感觉系统症状','无,轻度,中度,较重,极重','0,1,2,3,4',NULL,NULL),(438,40,'9','心血管系统症状','无,轻度,中度,较重,极重','0,1,2,3,4',NULL,NULL),(439,40,'10','呼吸系统症状','无,轻度,中度,较重,极重','0,1,2,3,4',NULL,NULL),(440,40,'11','胃肠道症状','无,轻度,中度,较重,极重','0,1,2,3,4',NULL,NULL),(441,40,'12','生殖泌尿系统症状','无,轻度,中度,较重,极重','0,1,2,3,4',NULL,NULL),(442,40,'13','自主神经系统症状','无,轻度,中度,较重,极重','0,1,2,3,4',NULL,NULL),(443,40,'14','会谈时行为表现','无,轻度,中度,较重,极重','0,1,2,3,4',NULL,NULL),(444,41,'1','我感到心情平静','完全没有,有些,中等程度,非常明显','4,3,2,1',NULL,NULL),(445,41,'2','我感到安全','完全没有,有些,中等程度,非常明显','4,3,2,1',NULL,NULL),(446,41,'3','我是紧张的','完全没有,有些,中等程度,非常明显','1,2,3,4',NULL,NULL),(447,41,'4','我感到紧张束缚','完全没有,有些,中等程度,非常明显','1,2,3,4',NULL,NULL),(448,41,'5','我感到安逸','完全没有,有些,中等程度,非常明显','4,3,2,1',NULL,NULL),(449,41,'6','我感到烦乱','完全没有,有些,中等程度,非常明显','1,2,3,4',NULL,NULL),(450,41,'7','我现在正烦恼，感到这种烦恼超过了可能的不幸','完全没有,有些,中等程度,非常明显','1,2,3,4',NULL,NULL),(451,41,'8','我感到满意','完全没有,有些,中等程度,非常明显','4,3,2,1',NULL,NULL),(452,41,'9','我感到害怕','完全没有,有些,中等程度,非常明显','1,2,3,4',NULL,NULL),(453,41,'10','我感到舒适','完全没有,有些,中等程度,非常明显','4,3,2,1',NULL,NULL),(454,41,'11','我有自信心','完全没有,有些,中等程度,非常明显','4,3,2,1',NULL,NULL),(455,41,'12','我觉得神经过敏','完全没有,有些,中等程度,非常明显','1,2,3,4',NULL,NULL),(456,41,'13','我极度紧张不安','完全没有,有些,中等程度,非常明显','1,2,3,4',NULL,NULL),(457,41,'14','我优柔寡断','完全没有,有些,中等程度,非常明显','1,2,3,4',NULL,NULL),(458,41,'15','我是轻松的','完全没有,有些,中等程度,非常明显','4,3,2,1',NULL,NULL),(459,41,'16','我感到心满意足','完全没有,有些,中等程度,非常明显','4,3,2,1',NULL,NULL),(460,41,'17','我是烦恼的','完全没有,有些,中等程度,非常明显','1,2,3,4',NULL,NULL),(461,41,'18','我感到慌乱','完全没有,有些,中等程度,非常明显','1,2,3,4',NULL,NULL),(462,41,'19','我感觉镇定','完全没有,有些,中等程度,非常明显','4,3,2,1',NULL,NULL),(463,41,'20','我感到愉快','完全没有,有些,中等程度,非常明显','4,3,2,1',NULL,NULL),(464,41,'21','我感到愉快','完全没有,有些,中等程度,非常明显','4,3,2,1',NULL,NULL),(465,41,'22','感到神经过敏和不安','完全没有,有些,中等程度,非常明显','1,2,3,4',NULL,NULL),(466,41,'23','我感到自我满足','完全没有,有些,中等程度,非常明显','4,3,2,1',NULL,NULL),(467,41,'24','我希望能像别人那样地高兴','完全没有,有些,中等程度,非常明显','4,3,2,1',NULL,NULL),(468,41,'25','我感到我像衰竭了一样','完全没有,有些,中等程度,非常明显','1,2,3,4',NULL,NULL),(469,41,'26','我感到很宁静','完全没有,有些,中等程度,非常明显','4,3,2,1',NULL,NULL),(470,41,'27','我是平静的、冷静的和泰然自若的','完全没有,有些,中等程度,非常明显','4,3,2,1',NULL,NULL),(471,41,'28','我感到困难一一堆集起来，因此无法克服','完全没有,有些,中等程度,非常明显','1,2,3,4',NULL,NULL),(472,41,'29','我过分忧虑一些事，实际上这些事无关紧要','完全没有,有些,中等程度,非常明显','1,2,3,4',NULL,NULL),(473,41,'30','我是高兴的','完全没有,有些,中等程度,非常明显','4,3,2,1',NULL,NULL),(474,41,'31','我的思想处于混乱状态','完全没有,有些,中等程度,非常明显','1,2,3,4',NULL,NULL),(475,41,'32','我缺乏自信心','完全没有,有些,中等程度,非常明显','1,2,3,4',NULL,NULL),(476,41,'33','我感到安全','完全没有,有些,中等程度,非常明显','4,3,2,1',NULL,NULL),(477,41,'34','我容易做出决断','完全没有,有些,中等程度,非常明显','4,3,2,1',NULL,NULL),(478,41,'35','我感到不合适','完全没有,有些,中等程度,非常明显','1,2,3,4',NULL,NULL),(479,41,'36','我是满足的','完全没有,有些,中等程度,非常明显','4,3,2,1',NULL,NULL),(480,41,'37','一些不重要的思想总缠绕着我，并打扰我','完全没有,有些,中等程度,非常明显','1,2,3,4',NULL,NULL),(481,41,'38','我产生的沮丧是如此强烈，以致我不能从思想中排除它们','完全没有,有些,中等程度,非常明显','1,2,3,4',NULL,NULL),(482,41,'39','我是一个镇定的人','完全没有,有些,中等程度,非常明显','4,3,2,1',NULL,NULL),(483,41,'40','当我考虑我目前的事情和利益时，我就陷入紧张状态','完全没有,有些,中等程度,非常明显','1,2,3,4',NULL,NULL),(484,42,'1','我觉得比平常容易紧张和着急','没有或很少时间,少部分时间,相当多时间,绝大多数时间','1,2,3,4',NULL,NULL),(485,42,'2','我无缘无故地感到害怕','没有或很少时间,少部分时间,相当多时间,绝大多数时间','1,2,3,4',NULL,NULL),(486,42,'3','我容易心里烦乱或觉得惊恐','没有或很少时间,少部分时间,相当多时间,绝大多数时间','1,2,3,4',NULL,NULL),(487,42,'4','我觉得我可能将要发疯','没有或很少时间,少部分时间,相当多时间,绝大多数时间','1,2,3,4',NULL,NULL),(488,42,'5','我觉得一切都很好，也不好发生什么不幸','没有或很少时间,少部分时间,相当多时间,绝大多数时间','4,3,2,1',NULL,NULL),(489,42,'6','我手脚发抖打颤','没有或很少时间,少部分时间,相当多时间,绝大多数时间','1,2,3,4',NULL,NULL),(490,42,'7','我因为头痛、头颈痛和背痛而苦恼','没有或很少时间,少部分时间,相当多时间,绝大多数时间','1,2,3,4',NULL,NULL),(491,42,'8','我感觉容易衰弱和疲乏','没有或很少时间,少部分时间,相当多时间,绝大多数时间','1,2,3,4',NULL,NULL),(492,42,'9','我觉得心平气和，并且容易安静坐着','没有或很少时间,少部分时间,相当多时间,绝大多数时间','4,3,2,1',NULL,NULL),(493,42,'10','我觉得心跳得很快','没有或很少时间,少部分时间,相当多时间,绝大多数时间','1,2,3,4',NULL,NULL),(494,42,'11','我因为一阵阵头晕而苦恼','没有或很少时间,少部分时间,相当多时间,绝大多数时间','1,2,3,4',NULL,NULL),(495,42,'12','我有晕倒发作或觉得要晕倒似的','没有或很少时间,少部分时间,相当多时间,绝大多数时间','1,2,3,4',NULL,NULL),(496,42,'13','我呼气吸气都感到很容易','没有或很少时间,少部分时间,相当多时间,绝大多数时间','4,3,2,1',NULL,NULL),(497,42,'14','我手脚麻木和刺痛','没有或很少时间,少部分时间,相当多时间,绝大多数时间','1,2,3,4',NULL,NULL),(498,42,'15','我因为胃痛和消化不良而苦恼','没有或很少时间,少部分时间,相当多时间,绝大多数时间','1,2,3,4',NULL,NULL),(499,42,'16','我常常要小便','没有或很少时间,少部分时间,相当多时间,绝大多数时间','1,2,3,4',NULL,NULL),(500,42,'17','我的手常常是干燥温暖的','没有或很少时间,少部分时间,相当多时间,绝大多数时间','4,3,2,1',NULL,NULL),(501,42,'18','我脸红发热','没有或很少时间,少部分时间,相当多时间,绝大多数时间','1,2,3,4',NULL,NULL),(502,42,'19','我容易入睡，并且一夜睡得很好','没有或很少时间,少部分时间,相当多时间,绝大多数时间','4,3,2,1',NULL,NULL),(503,42,'20','我做噩梦','没有或很少时间,少部分时间,相当多时间,绝大多数时间','1,2,3,4',NULL,NULL),(504,43,'1','情景性SPA，>3条症状的惊恐发作上周发作次数','0,1,2-7,8+','0,1,2,3',NULL,NULL),(505,43,'2','情景性SPA，>3条症状的惊恐发作平均强度','0-3,4-6,7-9,10','0,1,2,3',NULL,NULL),(506,43,'3','自发的SPPA，>3条症状的惊恐发作上周发作次数','0,1,2-7,8+','0,1,2,3',NULL,NULL),(507,43,'4','自发的SPPA，>3条症状的惊恐发作平均强度','0-3,4-6,7-9,10','0,1,2,3',NULL,NULL),(508,43,'5','有限症状的发作LSA，>3条症状的惊恐发作上周发作次数','0,1-7,8+','0,1,2',NULL,NULL),(509,43,'6','有限症状的发作LSA，>3条症状的惊恐发作平均强度','0-3,4-6,7-10','0,1,2',NULL,NULL),(510,43,'7','预期焦虑AA，>3条症状的惊恐发作上周占觉醒时间平均%','0,1-30,31-60,61-90,90-100','0,1,2,3,4',NULL,NULL),(511,43,'8','预期焦虑AA，>3条症状的惊恐发作平均强度','0-3,4-6,7-9,10','0,1,2,3',NULL,NULL),(512,43,'9','恐惧回避PS，>3条症状的惊恐发作总评分','0,1-2,3-4,5-6,7-8,9-10','0,1,2,3,4,5',NULL,NULL),(513,44,'1','惊恐发作的频率，包括有限症状的发作','没有惊恐发作或有限症状的发作,轻度、平均1周少于1次完整的发作、并有限症状的发作最多每天1次,中度、1周1次或2次完整发作、或每天多次有限症状的发作,严重、1周2次以上完整发作、但平均不超过每天1次,极度、每天1次以上的惊恐发作、有发作的日子多于不发作的日子','0,1,2,3,4',NULL,NULL),(514,44,'2','惊恐发作时苦恼，包括有限症状发作','无惊恐发作或有限症状的发作、或发作时无苦恼,轻度苦恼、但仍能继续活动、几乎没有或完全没有影响,中度苦恼、但仍能控制、能够继续活动、或能够维持注意力、但感到有困难,严重、显著的苦恼和影响、失去注意力、或必须停止活动、但仍能留在房间里或那个环境中,极度、严重和丧失功能的苦恼、必须停止活动、如有可能就会离开房间或那个环境、否则不能极重注意力、极度苦恼','0,1,2,3,4',NULL,NULL),(515,44,'3','预期性焦虑的严重度','不担心惊恐发作,轻度、对惊恐偶尔有害怕担心或惶惶不安,中度、经常担心害怕或惶惶不安、但有时没有焦虑、生活方式有注意得到的改变、但焦虑仍然可控、总体功能不受影响,严重、对惊恐有持续的害怕担心或惶惶不安、显著的干扰注意力、影响有效功能,极度、几乎持续和致残性的焦虑、因为对惊恐发作的害怕、担心或惶惶不安、不能执行重要的任务','0,1,2,3,4',NULL,NULL),(516,44,'4','场景害怕/回避','无、无害怕或回避,轻度、偶尔害怕或回避、但通常能面对或忍受、生活方式只有很小或没有改变,中度、注意得到的害怕或回避、但仍能控制、回避所害怕的场景、但有人陪伴就能面对、生活方式有些改变但总的功能未受损,严重、广泛的回避、生活方式的实质性改变就是需要有人陪伴、一般活动有困难,极广泛的致残性的害怕或回避、不得不广泛改变生活方式、不执行重要任务','0,1,2,3,4',NULL,NULL),(517,44,'5','与惊恐相关感觉的害怕/回避','没有害怕或回避会触发痛苦躯体感觉的场景或活动,轻度、偶尔害怕/回避、通常会面对或很少苦恼地忍受这些会触发躯体感觉的活动和场景、生活方式改变很少改变,中度、可注意到的回避、但仍能控制、有明确的的、但有限的生活方式改变、总体功能不受影响,严重、广泛的回避、造成生活方式的显著改变、或影响功能,极广泛的和致残性的回避、生活方式广泛的改变、不做重要的事情或活动','0,1,2,3,4',NULL,NULL),(518,44,'6','因为惊恐发作、工作能力受损/或受干扰','没有因惊恐障碍的症状而受损,轻度、轻度干扰、感觉工作困难、但表现尚好,中度、症状导致规律的、明确的干扰、但仍能控制、工作表现可能受损、但其他人会说工作还可以,严重、导致显著的职业功能损害、其他人会注意到、可能会耽误工作或某些天完全不能工作,极度、失能症状、不能工作','0,1,2,3,4',NULL,NULL),(519,44,'7','惊恐障碍损害/干扰社会功能','无损害,轻度、轻度干扰、感到社交行为的质量有所影响、但社交功能尚好,中度、明确的干扰社交生活、但仍能控制、社交活动的频率或人际关系质量有所下降、但仍能参与绝大多数的常见社交活动,严重、造成显著的社会功能损害、社交活动显著减少、或与别人交往有显著困难、仍能强迫自己与他人交往、但不能享受、或不能在大多数社交或人际交往场合中良好表现,极度、致残性症状、几乎不外出或与他人交往、可能会因为惊恐障碍而终止与他人的关系','0,1,2,3,4',NULL,NULL),(520,45,'1','感觉紧张、焦虑或烦躁','完全不会,几天,一半以上的日子,几乎每天','0,1,2,3',NULL,NULL),(521,45,'2','不能停止或控制担忧','完全不会,几天,一半以上的日子,几乎每天','0,1,2,3',NULL,NULL),(522,45,'3','对各种各样的事情担忧过多','完全不会,几天,一半以上的日子,几乎每天','0,1,2,3',NULL,NULL),(523,45,'4','很难放松下来','完全不会,几天,一半以上的日子,几乎每天','0,1,2,3',NULL,NULL),(524,45,'5','由于不安而无法静坐','完全不会,几天,一半以上的日子,几乎每天','0,1,2,3',NULL,NULL),(525,45,'6','变得容易烦恼或急躁','完全不会,几天,一半以上的日子,几乎每天','0,1,2,3',NULL,NULL),(526,45,'7','害怕将有可怕的事情发生','完全不会,几天,一半以上的日子,几乎每天','0,1,2,3',NULL,NULL),(527,47,'1','洗澡','无,轻微、偶然,中等、经常,严重、频繁,极重、一直有','1,2,3,4,5',NULL,NULL),(528,47,'2','洗脸、洗手','无,轻微、偶然,中等、经常,严重、频繁,极重、一直有','1,2,3,4,5',NULL,NULL),(529,47,'3','洗发、梳头','无,轻微、偶然,中等、经常,严重、频繁,极重、一直有','1,2,3,4,5',NULL,NULL),(530,47,'4','刷牙','无,轻微、偶然,中等、经常,严重、频繁,极重、一直有','1,2,3,4,5',NULL,NULL),(531,47,'5','穿、脱衣服','无,轻微、偶然,中等、经常,严重、频繁,极重、一直有','1,2,3,4,5',NULL,NULL),(532,47,'6','上厕所小便','无,轻微、偶然,中等、经常,严重、频繁,极重、一直有','1,2,3,4,5',NULL,NULL),(533,47,'7','上厕所大便','无,轻微、偶然,中等、经常,严重、频繁,极重、一直有','1,2,3,4,5',NULL,NULL),(534,47,'8','触摸他人或玻璃','无,轻微、偶然,中等、经常,严重、频繁,极重、一直有','1,2,3,4,5',NULL,NULL),(535,47,'9','拿垃圾或垃圾桶','无,轻微、偶然,中等、经常,严重、频繁,极重、一直有','1,2,3,4,5',NULL,NULL),(536,47,'10','洗衣','无,轻微、偶然,中等、经常,严重、频繁,极重、一直有','1,2,3,4,5',NULL,NULL),(537,47,'11','洗碗碟','无,轻微、偶然,中等、经常,严重、频繁,极重、一直有','1,2,3,4,5',NULL,NULL),(538,47,'12','拿/煮食物','无,轻微、偶然,中等、经常,严重、频繁,极重、一直有','1,2,3,4,5',NULL,NULL),(539,47,'13','打扫房间','无,轻微、偶然,中等、经常,严重、频繁,极重、一直有','1,2,3,4,5',NULL,NULL),(540,47,'14','保持物品清洁','无,轻微、偶然,中等、经常,严重、频繁,极重、一直有','1,2,3,4,5',NULL,NULL),(541,47,'15','铺床','无,轻微、偶然,中等、经常,严重、频繁,极重、一直有','1,2,3,4,5',NULL,NULL),(542,47,'16','擦鞋','无,轻微、偶然,中等、经常,严重、频繁,极重、一直有','1,2,3,4,5',NULL,NULL),(543,47,'17','握门把','无,轻微、偶然,中等、经常,严重、频繁,极重、一直有','1,2,3,4,5',NULL,NULL),(544,47,'18','触摸生殖器、性交','无,轻微、偶然,中等、经常,严重、频繁,极重、一直有','1,2,3,4,5',NULL,NULL),(545,47,'19','去医院','无,轻微、偶然,中等、经常,严重、频繁,极重、一直有','1,2,3,4,5',NULL,NULL),(546,47,'20','开/关灯','无,轻微、偶然,中等、经常,严重、频繁,极重、一直有','1,2,3,4,5',NULL,NULL),(547,47,'21','关锁门窗','无,轻微、偶然,中等、经常,严重、频繁,极重、一直有','1,2,3,4,5',NULL,NULL),(548,47,'22','使用电器','无,轻微、偶然,中等、经常,严重、频繁,极重、一直有','1,2,3,4,5',NULL,NULL),(549,47,'23','计算、记账','无,轻微、偶然,中等、经常,严重、频繁,极重、一直有','1,2,3,4,5',NULL,NULL),(550,47,'24','上班','无,轻微、偶然,中等、经常,严重、频繁,极重、一直有','1,2,3,4,5',NULL,NULL),(551,47,'25','做工作','无,轻微、偶然,中等、经常,严重、频繁,极重、一直有','1,2,3,4,5',NULL,NULL),(552,47,'26','书写','无,轻微、偶然,中等、经常,严重、频繁,极重、一直有','1,2,3,4,5',NULL,NULL),(553,47,'27','填表','无,轻微、偶然,中等、经常,严重、频繁,极重、一直有','1,2,3,4,5',NULL,NULL),(554,47,'28','寄信','无,轻微、偶然,中等、经常,严重、频繁,极重、一直有','1,2,3,4,5',NULL,NULL),(555,47,'29','阅读','无,轻微、偶然,中等、经常,严重、频繁,极重、一直有','1,2,3,4,5',NULL,NULL),(556,47,'30','上街','无,轻微、偶然,中等、经常,严重、频繁,极重、一直有','1,2,3,4,5',NULL,NULL),(557,47,'31','乘车','无,轻微、偶然,中等、经常,严重、频繁,极重、一直有','1,2,3,4,5',NULL,NULL),(558,47,'32','照顾小孩','无,轻微、偶然,中等、经常,严重、频繁,极重、一直有','1,2,3,4,5',NULL,NULL),(559,47,'33','在饭店吃饭','无,轻微、偶然,中等、经常,严重、频繁,极重、一直有','1,2,3,4,5',NULL,NULL),(560,47,'34','去电影院或剧场','无,轻微、偶然,中等、经常,严重、频繁,极重、一直有','1,2,3,4,5',NULL,NULL),(561,47,'35','去公共厕所','无,轻微、偶然,中等、经常,严重、频繁,极重、一直有','1,2,3,4,5',NULL,NULL),(562,47,'36','约会','无,轻微、偶然,中等、经常,严重、频繁,极重、一直有','1,2,3,4,5',NULL,NULL),(563,47,'37','望着他人或与人交谈','无,轻微、偶然,中等、经常,严重、频繁,极重、一直有','1,2,3,4,5',NULL,NULL),(564,47,'38','把东西丢掉','无,轻微、偶然,中等、经常,严重、频繁,极重、一直有','1,2,3,4,5',NULL,NULL),(565,47,'39','去商店购物','无,轻微、偶然,中等、经常,严重、频繁,极重、一直有','1,2,3,4,5',NULL,NULL),(566,47,'40','工作适应能力下降','无,轻微、偶然,中等、经常,严重、频繁,极重、一直有','1,2,3,4,5',NULL,NULL),(567,47,'41','家庭职能下降','无,轻微、偶然,中等、经常,严重、频繁,极重、一直有','1,2,3,4,5',NULL,NULL),(568,47,'42','恐怖靶症状-痛苦','无,似有、稍有,肯定有,明显,偏重、有些干扰生活,重、且干扰生活,很重、且明显干扰生活,严重、无法正常生活,极重、已无法忍受','1,2,3,4,5,6,7,8,9',NULL,NULL),(569,47,'43','恐怖靶症状-频度/时间','无,偶然有、如一周一次,很少有、如每几天一次,少有、如每天一次,有时有、如一天多次,常有、每天症状呈现几个小时,经常有、如有症状时间占白天的一半,几乎一直有,一直有','1,2,3,4,5,6,7,8,9',NULL,NULL),(570,47,'44','强迫靶症状-痛苦','无,似有、稍有,肯定有,明显,偏重、有些干扰生活,重、且干扰生活,很重、且明显干扰生活,严重、无法正常生活,极重、已无法忍受','1,2,3,4,5,6,7,8,9',NULL,NULL),(571,47,'45','强迫靶症状-频度/时间','无,偶然有、如一周一次,很少有、如每几天一次,少有、如每天一次,有时有、如一天多次,常有、每天症状呈现几个小时,经常有、如有症状时间占白天的一半,几乎一直有,一直有','1,2,3,4,5,6,7,8,9',NULL,NULL),(572,48,'1','花在强迫思维上的时间','无,轻度,中度,重度,极重度','0,1,2,3,4',NULL,NULL),(573,48,'2','社交或工作能力受强迫思维影响的程度','无,轻度,中度,重度,极重度','0,1,2,3,4',NULL,NULL),(574,48,'3','强迫思维所致痛苦烦恼程度','无,轻度,中度,重度,极重度','0,1,2,3,4',NULL,NULL),(575,48,'4','对强迫思维的抵制','总在努力克服或症状轻微无须克服,大多数时间在试图克服,做过一些努力欲克服,执行所有强迫行为没有试图克服,心甘情愿执行所有强迫行为','0,1,2,3,4',NULL,NULL),(576,48,'5','控制强迫思维的程度','完全控制,基本能控制,部分能控制,很少能控制,不能控制','0,1,2,3,4',NULL,NULL),(577,48,'6','花在强迫动作上的时间','无,轻度,中度,重度,极重度','0,1,2,3,4',NULL,NULL),(578,48,'7','受强迫动作干扰的程度','无,轻度,中度,重度,极重度','0,1,2,3,4',NULL,NULL),(579,48,'8','强迫动作所致痛苦烦恼程度','无,轻度,中度,重度,极重度','0,1,2,3,4',NULL,NULL),(580,48,'9','对强迫动作的抵制','总在努力克服或症状轻微无须克服,大多数时间在试图克服,做过一些努力欲克服,执行所有强迫行为没有试图克服,心甘情愿执行所有强迫行为','0,1,2,3,4',NULL,NULL),(581,48,'10','控制强迫动作的程度','完全控制,基本能控制,部分能控制,很少能控制,不能控制','0,1,2,3,4',NULL,NULL),(582,49,'1','公共场合打电话','无,轻度,中度,严重','0,1,2,3',NULL,NULL),(583,49,'2','参加小组活动','无,轻度,中度,严重','0,1,2,3',NULL,NULL),(584,49,'3','公共场合吃东西','无,轻度,中度,严重','0,1,2,3',NULL,NULL),(585,49,'4','公共场合与人共饮','无,轻度,中度,严重','0,1,2,3',NULL,NULL),(586,49,'5','与重要人物谈话','无,轻度,中度,严重','0,1,2,3',NULL,NULL),(587,49,'6','在听众前表演、演示或演讲','无,轻度,中度,严重','0,1,2,3',NULL,NULL),(588,49,'7','参加聚会','无,轻度,中度,严重','0,1,2,3',NULL,NULL),(589,49,'8','在有人注视下工作','无,轻度,中度,严重','0,1,2,3',NULL,NULL),(590,49,'9','被人注视下书写','无,轻度,中度,严重','0,1,2,3',NULL,NULL),(591,49,'10','与不太熟悉的人打电话','无,轻度,中度,严重','0,1,2,3',NULL,NULL),(592,49,'11','与不太熟悉的人交谈','无,轻度,中度,严重','0,1,2,3',NULL,NULL),(593,49,'12','与陌生人会面','无,轻度,中度,严重','0,1,2,3',NULL,NULL),(594,49,'13','在公共卫生间小便','无,轻度,中度,严重','0,1,2,3',NULL,NULL),(595,49,'14','进入已有人就坐的房间','无,轻度,中度,严重','0,1,2,3',NULL,NULL),(596,49,'15','成为关注的中心','无,轻度,中度,严重','0,1,2,3',NULL,NULL),(597,49,'16','在会议上发言','无,轻度,中度,严重','0,1,2,3',NULL,NULL),(598,49,'17','参加测试','无,轻度,中度,严重','0,1,2,3',NULL,NULL),(599,49,'18','对不熟悉的人表达不同的观点和看法','无,轻度,中度,严重','0,1,2,3',NULL,NULL),(600,49,'19','与不太熟悉的人目光对视','无,轻度,中度,严重','0,1,2,3',NULL,NULL),(601,49,'20','在小组中汇报','无,轻度,中度,严重','0,1,2,3',NULL,NULL),(602,49,'21','试着搭识某人','无,轻度,中度,严重','0,1,2,3',NULL,NULL),(603,49,'22','去商店退货','无,轻度,中度,严重','0,1,2,3',NULL,NULL),(604,49,'23','组织聚会','无,轻度,中度,严重','0,1,2,3',NULL,NULL),(605,49,'24','拒绝推销员的强制推销','无,轻度,中度,严重','0,1,2,3',NULL,NULL);

/*Table structure for table `scale_score` */

DROP TABLE IF EXISTS `scale_score`;

CREATE TABLE `scale_score` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `task_id` int(32) DEFAULT NULL COMMENT '任务id',
  `scale_id` int(32) DEFAULT NULL COMMENT '量表id',
  `title` varchar(100) DEFAULT NULL COMMENT '题号',
  `score` int(32) DEFAULT NULL COMMENT '得分',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=901 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='量表分数';

/*Data for the table `scale_score` */

insert  into `scale_score`(`id`,`task_id`,`scale_id`,`title`,`score`) values (1,1,1,'1',1),(2,1,1,'2',1),(3,1,1,'3',1),(4,1,1,'4',4),(5,1,1,'5',3),(6,1,1,'6',2),(19,1,9,'1',1),(20,1,9,'2',1),(21,1,9,'3',1),(22,1,9,'4',4),(23,1,9,'5',3),(24,1,9,'6',2),(25,20,10,'1',0),(26,20,10,'2',0),(27,20,10,'3',1),(28,20,10,'4',1),(29,20,10,'5',0),(30,20,10,'6',0),(31,20,10,'7',1),(32,20,10,'8',1),(33,20,10,'9',0),(34,20,10,'10',1),(35,20,10,'11',1),(36,20,10,'12',1),(37,21,10,'1',0),(38,21,10,'2',0),(39,21,10,'3',1),(40,21,10,'4',1),(41,21,10,'5',0),(42,21,10,'6',0),(43,21,10,'7',1),(44,21,10,'8',1),(45,21,10,'9',0),(46,21,10,'10',1),(47,21,10,'11',0),(48,21,10,'12',0),(49,3,10,'1',0),(50,3,10,'2',0),(51,3,10,'3',0),(52,3,10,'4',1),(53,3,10,'5',1),(54,3,10,'6',1),(55,3,10,'7',0),(56,3,10,'8',0),(57,3,10,'9',0),(58,3,10,'10',1),(59,3,10,'11',1),(60,3,10,'12',1),(61,22,9,'1',3),(62,22,9,'3',3),(63,22,9,'4',2),(64,22,9,'5',3),(65,22,9,'6',5),(66,22,9,'7',4),(67,22,9,'8',2),(68,22,9,'9',1),(69,22,9,'10',2),(70,22,9,'11',4),(71,22,9,'12',3),(72,22,9,'14',2),(73,22,9,'15',1),(74,22,9,'16',2),(75,22,9,'17',3),(76,22,9,'18',4),(77,22,9,'19',5),(78,22,9,'20',5),(79,22,9,'21',4),(80,22,9,'22',3),(81,22,9,'23',2),(82,22,9,'25',3),(83,22,9,'26',4),(84,22,9,'27',5),(85,22,9,'28',4),(86,22,9,'29',3),(87,22,9,'30',2),(88,22,9,'31',1),(89,22,9,'32',2),(90,22,9,'33',3),(91,22,9,'35',4),(92,22,9,'36',5),(93,22,9,'37',3),(94,22,9,'38',2),(95,22,9,'39',1),(96,22,9,'40',2),(97,22,9,'41',3),(98,22,9,'42',4),(99,22,9,'43',5),(100,22,9,'44',4),(101,22,9,'45',4),(102,22,9,'46',4),(103,22,9,'47',4),(104,22,9,'48',3),(105,22,9,'49',3),(106,22,9,'50',3),(107,22,9,'51',4),(108,22,9,'52',5),(109,22,9,'53',5),(110,22,9,'55',4),(111,22,9,'56',3),(112,22,9,'57',2),(113,22,9,'58',3),(114,22,9,'59',4),(115,22,9,'60',5),(116,22,9,'61',4),(117,22,9,'62',3),(118,22,9,'63',2),(119,22,9,'64',2),(120,22,9,'65',3),(121,22,9,'66',4),(122,22,9,'67',5),(123,22,9,'68',4),(124,22,9,'69',3),(125,22,9,'70',3),(126,22,9,'71',2),(127,22,9,'72',2),(128,22,9,'73',4),(129,22,9,'74',5),(130,22,9,'75',4),(131,22,9,'76',3),(132,22,9,'77',2),(133,22,9,'78',3),(134,22,9,'79',4),(135,22,9,'80',5),(136,22,9,'81',5),(137,22,9,'82',5),(138,22,9,'83',3),(139,22,9,'84',2),(140,22,9,'85',1),(141,22,9,'86',2),(142,22,9,'87',1),(143,22,9,'88',3),(144,22,9,'89',5),(145,22,9,'90',5),(146,22,10,'1',0),(147,22,10,'2',1),(148,22,10,'3',1),(149,22,10,'4',0),(150,22,10,'5',1),(151,22,10,'6',1),(152,22,10,'7',1),(153,22,10,'8',0),(154,22,10,'9',1),(155,22,10,'10',1),(156,22,10,'11',1),(157,22,10,'12',1),(158,22,18,'1',3),(159,22,18,'2',5),(160,22,18,'3',3),(161,22,18,'4',2),(162,22,18,'5',5),(163,22,18,'6',4),(164,22,18,'7',2),(165,22,18,'8',6),(166,22,18,'9',6),(167,22,18,'10',4),(168,22,18,'11',7),(169,22,18,'12',4),(170,22,18,'13',3),(171,22,18,'14',1),(172,22,18,'15',4),(173,22,18,'16',1),(174,22,18,'17',1),(175,22,18,'18',6),(176,22,19,'1',4),(177,22,19,'2',3),(178,22,19,'3',2),(179,22,19,'4',1),(180,22,19,'5',3),(181,22,19,'6',4),(182,22,19,'7',2),(183,22,19,'8',0),(184,22,19,'9',0),(185,22,19,'10',0),(186,22,19,'11',1),(187,22,19,'12',2),(188,22,19,'13',1),(189,22,19,'14',1),(190,22,19,'15',2),(191,22,19,'16',3),(192,22,19,'17',1),(193,22,19,'18',3),(194,22,19,'19',4),(195,22,19,'20',1),(196,22,19,'21',0),(197,22,19,'22',2),(198,22,19,'23',4),(199,22,19,'24',1),(200,22,20,'1',4),(201,22,20,'2',3),(202,22,20,'3',2),(203,22,20,'4',1),(204,22,20,'5',1),(205,22,20,'6',1),(206,22,20,'7',1),(207,22,20,'8',2),(208,22,20,'9',2),(209,22,20,'10',3),(210,22,20,'11',2),(211,22,20,'12',2),(212,22,20,'13',2),(213,22,20,'14',3),(214,22,20,'15',3),(215,22,20,'16',1),(216,22,20,'17',3),(217,22,20,'18',1),(218,22,20,'19',3),(219,22,20,'20',4),(220,22,20,'22',2),(221,22,20,'23',1),(222,22,20,'24',1),(223,22,20,'26',1),(224,22,20,'27',3),(225,22,20,'29',4),(226,22,20,'30',4),(227,22,20,'32',1),(228,22,20,'34',3),(229,22,21,'1',6),(230,22,21,'2',5),(231,22,21,'3',3),(232,22,21,'4',6),(233,22,21,'5',3),(234,22,21,'6',2),(235,22,21,'7',4),(236,22,21,'8',5),(237,22,21,'9',2),(238,22,21,'10',5),(239,22,21,'11',5),(240,22,21,'12',5),(241,22,21,'13',4),(242,22,21,'14',2),(243,22,21,'15',3),(244,22,21,'16',3),(245,22,21,'17',5),(246,22,21,'18',3),(247,22,21,'19',4),(248,22,21,'20',5),(249,22,21,'21',4),(250,22,21,'22',2),(251,22,21,'23',4),(252,22,21,'24',5),(253,22,21,'25',2),(254,22,21,'26',4),(255,22,21,'27',5),(256,22,21,'28',2),(257,22,21,'29',5),(258,22,21,'30',2),(259,22,21,'31',4),(260,22,21,'32',5),(261,22,21,'33',4),(262,22,22,'1',3),(263,22,22,'2',2),(264,22,22,'3',1),(265,22,22,'4',2),(266,22,22,'5',1),(267,22,22,'6',2),(268,22,22,'7',3),(269,22,22,'8',1),(270,22,22,'9',2),(271,22,22,'10',1),(272,22,22,'11',0),(273,22,22,'12',1),(274,22,22,'13',2),(275,22,22,'14',1),(276,23,10,'1',0),(277,23,10,'2',0),(278,23,10,'3',0),(279,23,10,'4',0),(280,23,10,'5',0),(281,23,10,'6',1),(282,23,10,'7',1),(283,23,10,'8',1),(284,23,10,'9',1),(285,23,10,'10',1),(286,23,10,'11',1),(287,23,10,'12',1),(288,24,9,'1',3),(289,24,9,'2',3),(290,24,9,'3',3),(291,24,9,'4',3),(292,24,9,'5',3),(293,24,9,'6',3),(294,24,9,'7',3),(295,24,9,'8',3),(296,24,9,'9',3),(297,24,9,'10',3),(298,24,9,'11',3),(299,24,9,'12',3),(300,24,9,'13',4),(301,24,9,'14',5),(302,24,9,'15',4),(303,24,9,'16',5),(304,24,9,'17',5),(305,24,9,'18',5),(306,24,9,'19',1),(307,24,9,'20',1),(308,24,9,'21',2),(309,24,9,'22',1),(310,24,9,'23',2),(311,24,9,'24',3),(312,24,9,'25',3),(313,24,9,'26',4),(314,24,9,'27',2),(315,24,9,'28',3),(316,24,9,'29',4),(317,24,9,'30',4),(318,24,9,'31',4),(319,24,9,'32',2),(320,24,9,'33',3),(321,24,9,'34',3),(322,24,9,'35',4),(323,24,9,'36',3),(324,24,9,'37',4),(325,24,9,'38',4),(326,24,9,'39',3),(327,24,9,'40',4),(328,24,9,'41',3),(329,24,9,'42',4),(330,24,9,'43',3),(331,24,9,'44',4),(332,24,9,'45',3),(333,24,9,'46',4),(334,24,9,'47',2),(335,24,9,'48',2),(336,24,9,'49',2),(337,24,9,'50',3),(338,24,9,'51',4),(339,24,9,'52',2),(340,24,9,'53',3),(341,24,9,'54',4),(342,24,9,'55',4),(343,24,9,'56',5),(344,24,9,'57',2),(345,24,9,'58',4),(346,24,9,'59',3),(347,24,9,'60',4),(348,24,9,'61',5),(349,24,9,'62',2),(350,24,9,'63',3),(351,24,9,'64',3),(352,24,9,'65',3),(353,24,9,'66',3),(354,24,9,'67',3),(355,24,9,'68',3),(356,24,9,'69',3),(357,24,9,'70',3),(358,24,9,'71',3),(359,24,9,'72',3),(360,24,9,'73',3),(361,24,9,'74',3),(362,24,9,'75',3),(363,24,9,'76',3),(364,24,9,'77',3),(365,24,9,'78',3),(366,24,9,'79',3),(367,24,9,'80',3),(368,24,9,'81',4),(369,24,9,'82',4),(370,24,9,'83',4),(371,24,9,'84',3),(372,24,9,'85',4),(373,24,9,'86',3),(374,24,9,'87',4),(375,24,9,'88',3),(376,24,9,'89',4),(377,24,9,'90',3),(378,26,9,'1',1),(379,26,9,'2',1),(380,26,9,'3',1),(381,26,9,'4',1),(382,26,9,'5',1),(383,26,9,'6',1),(384,26,9,'7',1),(385,26,9,'8',1),(386,26,9,'9',1),(387,26,9,'10',1),(388,26,9,'11',1),(389,26,9,'12',1),(390,26,9,'13',1),(391,26,9,'14',1),(392,26,9,'15',1),(393,26,9,'16',1),(394,26,9,'17',1),(395,26,9,'18',1),(396,26,9,'19',2),(397,26,9,'20',2),(398,26,9,'21',2),(399,26,9,'22',2),(400,26,9,'23',2),(401,26,9,'24',2),(402,26,9,'25',2),(403,26,9,'26',2),(404,26,9,'27',2),(405,26,9,'28',2),(406,26,9,'29',2),(407,26,9,'30',2),(408,26,9,'31',2),(409,26,9,'32',2),(410,26,9,'33',2),(411,26,9,'34',2),(412,26,9,'35',2),(413,26,9,'36',2),(414,26,9,'37',2),(415,26,9,'38',2),(416,26,9,'39',2),(417,26,9,'40',2),(418,26,9,'41',2),(419,26,9,'42',2),(420,26,9,'43',2),(421,26,9,'44',2),(422,26,9,'45',2),(423,26,9,'46',2),(424,26,9,'47',2),(425,26,9,'48',2),(426,26,9,'49',2),(427,26,9,'50',2),(428,26,9,'51',2),(429,26,9,'52',2),(430,26,9,'53',2),(431,26,9,'54',2),(432,26,9,'55',2),(433,26,9,'56',2),(434,26,9,'57',2),(435,26,9,'58',2),(436,26,9,'59',2),(437,26,9,'60',2),(438,26,9,'61',2),(439,26,9,'62',2),(440,26,9,'63',2),(441,26,9,'64',2),(442,26,9,'65',2),(443,26,9,'66',2),(444,26,9,'67',2),(445,26,9,'68',2),(446,26,9,'69',2),(447,26,9,'70',2),(448,26,9,'71',2),(449,26,9,'72',2),(450,26,9,'73',2),(451,26,9,'74',2),(452,26,9,'75',2),(453,26,9,'76',2),(454,26,9,'77',2),(455,26,9,'78',2),(456,26,9,'79',2),(457,26,9,'80',2),(458,26,9,'81',2),(459,26,9,'82',2),(460,26,9,'83',2),(461,26,9,'84',2),(462,26,9,'85',2),(463,26,9,'86',2),(464,26,9,'87',2),(465,26,9,'88',2),(466,26,9,'89',2),(467,26,9,'90',2),(468,27,10,'1',0),(469,27,10,'2',0),(470,27,10,'3',0),(471,27,10,'4',0),(472,27,10,'5',0),(473,27,10,'6',0),(474,27,10,'7',0),(475,27,10,'8',0),(476,27,10,'9',0),(477,27,10,'10',0),(478,27,10,'11',0),(479,27,10,'12',0),(480,28,10,'1',0),(481,28,10,'2',0),(482,28,10,'3',0),(483,28,10,'4',0),(484,28,10,'5',0),(485,28,10,'6',0),(486,28,10,'7',0),(487,28,10,'8',0),(488,28,10,'9',0),(489,28,10,'10',0),(490,28,10,'11',0),(491,28,10,'12',0),(492,30,10,'1',0),(493,30,10,'2',0),(494,30,10,'3',0),(495,30,10,'4',0),(496,30,10,'5',0),(497,30,10,'6',0),(498,30,10,'7',0),(499,30,10,'8',0),(500,30,10,'9',0),(501,30,10,'10',0),(502,30,10,'11',0),(503,30,10,'12',0),(504,32,9,'1',1),(505,32,9,'2',1),(506,32,9,'3',1),(507,32,9,'4',1),(508,32,9,'5',1),(509,32,9,'6',1),(510,32,9,'7',1),(511,32,9,'8',1),(512,32,9,'9',1),(513,32,9,'10',1),(514,32,9,'11',1),(515,32,9,'12',1),(516,32,9,'13',1),(517,32,9,'14',1),(518,32,9,'15',1),(519,32,9,'16',1),(520,32,9,'17',1),(521,32,9,'18',1),(522,32,9,'19',1),(523,32,9,'20',1),(524,32,9,'21',1),(525,32,9,'22',1),(526,32,9,'23',1),(527,32,9,'24',1),(528,32,9,'25',1),(529,32,9,'26',2),(530,32,9,'27',2),(531,32,9,'28',2),(532,32,9,'29',2),(533,32,9,'30',2),(534,32,9,'31',2),(535,32,9,'32',2),(536,32,9,'33',2),(537,32,9,'34',2),(538,32,9,'35',2),(539,32,9,'36',2),(540,32,9,'37',2),(541,32,9,'38',2),(542,32,9,'39',2),(543,32,9,'40',2),(544,32,9,'41',2),(545,32,9,'42',2),(546,32,9,'43',2),(547,32,9,'44',2),(548,32,9,'45',2),(549,32,9,'46',2),(550,32,9,'47',2),(551,32,9,'48',2),(552,32,9,'49',2),(553,32,9,'50',2),(554,32,9,'51',2),(555,32,9,'52',2),(556,32,9,'53',2),(557,32,9,'54',2),(558,32,9,'55',2),(559,32,9,'56',2),(560,32,9,'57',2),(561,32,9,'58',2),(562,32,9,'59',2),(563,32,9,'60',2),(564,32,9,'61',2),(565,32,9,'62',2),(566,32,9,'63',2),(567,32,9,'64',2),(568,32,9,'65',2),(569,32,9,'66',2),(570,32,9,'67',2),(571,32,9,'68',2),(572,32,9,'69',2),(573,32,9,'70',2),(574,32,9,'71',2),(575,32,9,'72',2),(576,32,9,'73',2),(577,32,9,'74',2),(578,32,9,'75',2),(579,32,9,'76',2),(580,32,9,'77',2),(581,32,9,'78',2),(582,32,9,'79',2),(583,32,9,'80',2),(584,32,9,'81',2),(585,32,9,'82',2),(586,32,9,'83',2),(587,32,9,'84',2),(588,32,9,'85',2),(589,32,9,'86',2),(590,32,9,'87',2),(591,32,9,'88',2),(592,32,9,'89',2),(593,32,9,'90',2),(594,33,10,'1',1),(595,33,10,'2',1),(596,33,10,'3',1),(597,33,10,'4',1),(598,33,10,'5',1),(599,33,10,'6',1),(600,33,10,'7',1),(601,33,10,'8',1),(602,33,10,'9',1),(603,33,10,'10',1),(604,33,10,'11',1),(605,33,10,'12',1),(606,33,10,'13',1),(607,33,10,'14',1),(608,33,10,'15',1),(609,33,10,'16',1),(610,33,10,'17',1),(611,33,10,'18',1),(612,33,10,'19',1),(613,33,10,'20',1),(614,33,10,'21',1),(615,33,10,'22',1),(616,33,10,'23',1),(617,33,10,'24',1),(618,33,10,'25',1),(619,33,10,'26',2),(620,33,10,'27',2),(621,33,10,'28',2),(622,33,10,'29',2),(623,33,10,'30',2),(624,33,10,'31',2),(625,33,10,'32',2),(626,33,10,'33',2),(627,33,10,'34',2),(628,33,10,'35',2),(629,33,10,'36',2),(630,33,10,'37',2),(631,33,10,'38',2),(632,33,10,'39',2),(633,33,10,'40',2),(634,33,10,'41',2),(635,33,10,'42',2),(636,33,10,'43',2),(637,33,10,'44',2),(638,33,10,'45',2),(639,33,10,'46',2),(640,33,10,'47',2),(641,33,10,'48',2),(642,33,10,'49',2),(643,33,10,'50',2),(644,33,10,'51',2),(645,33,10,'52',2),(646,33,10,'53',2),(647,33,10,'54',2),(648,33,10,'55',2),(649,33,10,'56',2),(650,33,10,'57',2),(651,33,10,'58',2),(652,33,10,'59',2),(653,33,10,'60',2),(654,33,10,'61',2),(655,33,10,'62',2),(656,33,10,'63',2),(657,33,10,'64',2),(658,33,10,'65',2),(659,33,10,'66',2),(660,33,10,'67',2),(661,33,10,'68',2),(662,33,10,'69',2),(663,33,10,'70',2),(664,33,10,'71',2),(665,33,10,'72',2),(666,33,10,'73',2),(667,33,10,'74',2),(668,33,10,'75',2),(669,33,10,'76',2),(670,33,10,'77',2),(671,33,10,'78',2),(672,33,10,'79',2),(673,33,10,'80',2),(674,33,10,'81',2),(675,33,10,'82',2),(676,33,10,'83',2),(677,33,10,'84',2),(678,33,10,'85',2),(679,33,10,'86',2),(680,33,10,'87',2),(681,33,10,'88',2),(682,33,10,'89',2),(683,33,10,'90',2),(684,34,9,'1',1),(685,34,9,'2',1),(686,34,9,'3',1),(687,34,9,'4',1),(688,34,9,'5',1),(689,34,9,'6',1),(690,34,9,'7',1),(691,34,9,'8',1),(692,34,9,'9',1),(693,34,9,'10',1),(694,34,9,'11',1),(695,34,9,'12',1),(696,34,9,'13',1),(697,34,9,'14',1),(698,34,9,'15',1),(699,34,9,'16',1),(700,34,9,'17',1),(701,34,9,'18',2),(702,34,9,'19',2),(703,34,9,'20',2),(704,34,9,'21',2),(705,34,9,'22',2),(706,34,9,'23',2),(707,34,9,'25',2),(708,34,9,'26',2),(709,34,9,'27',2),(710,34,9,'28',2),(711,34,9,'29',2),(712,34,9,'30',2),(713,34,9,'31',2),(714,34,9,'32',2),(715,34,9,'33',2),(716,34,9,'34',2),(717,34,9,'35',2),(718,34,9,'36',2),(719,34,9,'37',2),(720,34,9,'38',2),(721,34,9,'39',2),(722,34,9,'40',2),(723,34,9,'41',2),(724,34,9,'42',2),(725,34,9,'43',2),(726,34,9,'44',2),(727,34,9,'45',2),(728,34,9,'46',2),(729,34,9,'47',2),(730,34,9,'48',2),(731,34,9,'49',2),(732,34,9,'50',2),(733,34,9,'51',2),(734,34,9,'52',2),(735,34,9,'53',2),(736,34,9,'54',2),(737,34,9,'55',2),(738,34,9,'56',2),(739,34,9,'57',2),(740,34,9,'58',2),(741,34,9,'59',2),(742,34,9,'60',2),(743,34,9,'61',2),(744,34,9,'62',2),(745,34,9,'63',2),(746,34,9,'64',2),(747,34,9,'65',2),(748,34,9,'66',2),(749,34,9,'67',2),(750,34,9,'68',2),(751,34,9,'69',2),(752,34,9,'70',2),(753,34,9,'71',2),(754,34,9,'72',2),(755,34,9,'73',2),(756,34,9,'74',2),(757,34,9,'75',2),(758,34,9,'76',2),(759,34,9,'77',2),(760,34,9,'78',2),(761,34,9,'79',2),(762,34,9,'80',2),(763,34,9,'81',2),(764,34,9,'82',2),(765,34,9,'83',2),(766,34,9,'84',2),(767,34,9,'85',2),(768,34,9,'86',2),(769,34,9,'87',2),(770,34,9,'88',2),(771,34,9,'89',2),(772,34,9,'90',4),(773,36,9,'1',3),(774,36,9,'2',3),(775,36,9,'3',3),(776,36,9,'4',3),(777,36,9,'5',3),(778,36,9,'6',3),(779,36,9,'7',3),(780,36,9,'8',3),(781,36,9,'9',3),(782,36,9,'10',3),(783,36,9,'11',3),(784,36,9,'12',3),(785,36,9,'13',4),(786,36,9,'14',4),(787,36,9,'15',4),(788,36,9,'16',4),(789,36,9,'17',4),(790,36,9,'18',4),(791,36,9,'19',4),(792,36,9,'20',4),(793,36,9,'21',4),(794,36,9,'22',4),(795,36,9,'23',4),(796,36,9,'24',4),(797,36,9,'25',4),(798,36,9,'26',4),(799,36,9,'27',4),(800,36,9,'28',4),(801,36,9,'29',4),(802,36,9,'30',4),(803,36,9,'31',4),(804,36,9,'32',4),(805,36,9,'33',4),(806,36,9,'34',4),(807,36,9,'35',4),(808,36,9,'36',4),(809,36,9,'37',4),(810,36,9,'38',4),(811,36,9,'39',4),(812,36,9,'40',4),(813,36,9,'41',4),(814,36,9,'42',4),(815,36,9,'43',4),(816,36,9,'44',4),(817,36,9,'45',4),(818,36,9,'46',4),(819,36,9,'47',4),(820,36,9,'48',4),(821,36,9,'49',4),(822,36,9,'50',4),(823,36,9,'51',4),(824,36,9,'52',4),(825,36,9,'53',4),(826,36,9,'54',4),(827,36,9,'55',4),(828,36,9,'56',4),(829,36,9,'57',4),(830,36,9,'58',4),(831,36,9,'59',4),(832,36,9,'60',4),(833,36,9,'61',4),(834,36,9,'62',4),(835,36,9,'63',4),(836,36,9,'64',4),(837,36,9,'65',4),(838,36,9,'66',4),(839,36,9,'67',4),(840,36,9,'68',4),(841,36,9,'69',4),(842,36,9,'70',4),(843,36,9,'71',4),(844,36,9,'72',4),(845,36,9,'73',4),(846,36,9,'74',4),(847,36,9,'75',4),(848,36,9,'76',4),(849,36,9,'77',4),(850,36,9,'78',4),(851,36,9,'79',4),(852,36,9,'80',4),(853,36,9,'81',4),(854,36,9,'82',4),(855,36,9,'83',4),(856,36,9,'84',4),(857,36,9,'85',4),(858,36,9,'86',4),(859,36,9,'87',4),(860,36,9,'88',4),(861,36,9,'89',4),(862,36,9,'90',4),(863,37,10,'1',0),(864,37,10,'2',0),(865,37,10,'3',0),(866,37,10,'4',0),(867,37,10,'5',0),(868,37,10,'6',0),(869,37,10,'7',0),(870,37,10,'8',0),(871,37,10,'9',0),(872,37,10,'10',0),(873,37,10,'11',0),(874,37,10,'12',0),(875,38,10,'1',0),(876,38,10,'2',1),(877,38,10,'3',1),(878,38,10,'4',1),(879,38,10,'5',1),(880,38,10,'6',1),(881,38,10,'7',1),(882,38,10,'8',1),(883,38,10,'9',1),(884,38,10,'10',1),(885,38,10,'11',1),(886,38,10,'12',1),(887,39,18,'1',2),(888,39,18,'2',2),(889,39,18,'3',2),(890,39,18,'4',2),(891,39,18,'5',2),(892,39,18,'7',2),(893,39,18,'8',2),(894,39,18,'9',2),(895,39,18,'11',2),(896,39,18,'13',2),(897,39,18,'15',2),(898,39,18,'16',2),(899,39,18,'17',2),(900,39,18,'18',2);

/*Table structure for table `scale_task` */

DROP TABLE IF EXISTS `scale_task`;

CREATE TABLE `scale_task` (
  `task_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '测评人ID',
  `user_name` varchar(50) DEFAULT NULL COMMENT '测评人姓名',
  `patient_id` bigint(20) DEFAULT NULL COMMENT '患者ID',
  `patient_name` varchar(50) DEFAULT NULL COMMENT '患者名称',
  `workstation` varchar(50) DEFAULT NULL COMMENT '工作站',
  `test_coding` varchar(100) DEFAULT NULL COMMENT '测试编码',
  `typeIds` varchar(100) DEFAULT NULL COMMENT '测评任务',
  `task_status` char(1) DEFAULT NULL COMMENT '任务状态(1未开始,2进行中,3已结束)',
  `del_flag` char(1) DEFAULT NULL COMMENT '是否删除(0存在，1删除)',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `scale_id` varchar(255) DEFAULT NULL COMMENT '已完成量表',
  `type_flag` char(1) DEFAULT NULL COMMENT '任务所属系统（0新冠 1量表）',
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COMMENT='量表系统任务表';

/*Data for the table `scale_task` */

insert  into `scale_task`(`task_id`,`user_id`,`user_name`,`patient_id`,`patient_name`,`workstation`,`test_coding`,`typeIds`,`task_status`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`,`scale_id`,`type_flag`) values (1,1,'test',2,'zz','C001','1','1','3','1','test','2021-10-11 11:06:13','test','2021-10-14 14:15:21','9','1'),(3,1,'11',2,'11','C001','1','1','3','1','11','2021-10-11 11:45:54','11','2021-11-26 15:00:53','10','1'),(20,1,'admin',2,'张三','C003','866582','1','3','1','admin','2021-10-28 11:13:10','admin','2021-10-28 11:19:22','10',NULL),(21,1,'admin',4,'王五','C003','968467','1','3','1','admin','2021-10-28 13:33:06','admin','2021-10-28 13:34:01','42',NULL),(22,1,'admin',2,'张三','C001','456798','1','3','1','admin','2021-11-26 15:02:23','admin','2021-11-26 15:32:33','9',NULL),(23,1,'admin',6,'小红','C002','990365','1','3','1','admin','2021-11-26 16:46:17','admin','2021-11-26 16:47:48','10',NULL),(36,1,'admin',2,'张三','C001','600384','1','3','1','admin','2021-11-26 17:37:20','admin','2021-11-26 17:38:15','9',NULL),(37,1,'admin',2,'张三','C001','600384','1','3','1','admin','2021-11-26 17:37:20','admin','2021-11-26 17:38:38','10',NULL),(38,1,'admin',6,'小红','C002','681965','1','3','1','admin','2021-11-26 17:58:28','admin','2021-11-26 17:58:53','10',NULL),(39,1,'admin',6,'小红','C002','681965','1','3','1','admin','2021-11-26 17:58:28','admin','2021-11-26 17:59:08','18',NULL);

/*Table structure for table `scale_task_score` */

DROP TABLE IF EXISTS `scale_task_score`;

CREATE TABLE `scale_task_score` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `scale_id` int(32) NOT NULL COMMENT '量表名字',
  `task_id` int(32) NOT NULL COMMENT '任务id',
  `patient_id` int(32) DEFAULT NULL COMMENT '病人id',
  `score` int(32) DEFAULT NULL COMMENT '得分',
  `workstation` varchar(32) DEFAULT NULL COMMENT '工作站',
  `test_date` date DEFAULT '0000-00-00' COMMENT '测试日期',
  PRIMARY KEY (`id`,`task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='量表得分保存表';

/*Data for the table `scale_task_score` */

insert  into `scale_task_score`(`id`,`scale_id`,`task_id`,`patient_id`,`score`,`workstation`,`test_date`) values (3,1,1,1,11,'C001','2021-10-11'),(4,9,1,1,11,'C001','2021-10-14'),(5,10,20,2,7,'C003','2021-10-28'),(6,10,21,4,5,'C003','2021-10-28'),(7,10,3,2,6,'C001','2021-11-26'),(8,9,22,2,556,'C001','2021-11-26'),(9,10,22,2,9,'C001','2021-11-26'),(10,18,22,2,67,'C001','2021-11-26'),(11,19,22,2,45,'C001','2021-11-26'),(12,20,22,2,64,'C001','2021-11-26'),(13,21,22,2,128,'C001','2021-11-26'),(14,22,22,2,22,'C001','2021-11-26'),(15,10,23,6,7,'C002','2021-11-26'),(16,9,24,6,580,'C002','2021-11-26'),(17,9,26,2,162,'C001','2021-11-26'),(18,10,27,2,0,'C001','2021-11-26'),(19,10,28,2,0,'C001','2021-11-26'),(20,10,30,2,0,'C001','2021-11-26'),(21,9,32,2,155,'C001','2021-11-26'),(22,10,33,2,155,'C001','2021-11-26'),(23,9,34,2,163,'C001','2021-11-26'),(24,9,36,2,348,'C001','2021-11-26'),(25,10,37,2,0,'C001','2021-11-26'),(26,10,38,6,11,'C002','2021-11-26'),(27,18,39,6,28,'C002','2021-11-26');

/*Table structure for table `scale_title` */

DROP TABLE IF EXISTS `scale_title`;

CREATE TABLE `scale_title` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `grade` char(5) DEFAULT NULL COMMENT '他评量表or自评量表',
  `scale_id` bigint(20) DEFAULT NULL COMMENT '量表id',
  `title` varchar(100) DEFAULT NULL COMMENT '题号',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `option_a` varchar(100) DEFAULT NULL COMMENT '选项a',
  `option_b` varchar(100) DEFAULT '' COMMENT '选项b',
  `option_c` varchar(100) DEFAULT '' COMMENT '选项c',
  `option_d` varchar(100) DEFAULT NULL COMMENT '选项d',
  `option_e` varchar(100) DEFAULT '' COMMENT '选项e',
  `option_f` varchar(100) DEFAULT NULL COMMENT '选项f',
  `option_g` varchar(100) DEFAULT NULL COMMENT '选项g',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=261 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='量表题目';

/*Data for the table `scale_title` */

insert  into `scale_title`(`id`,`grade`,`scale_id`,`title`,`content`,`option_a`,`option_b`,`option_c`,`option_d`,`option_e`,`option_f`,`option_g`) values (1,'0',19,'1','我常常搞不清自己有什么样的感受','很不同意','不同意','部分同意','同意','很同意',NULL,NULL),(2,'0',19,'2','我感到难以用恰当的词语来描述我的感受','很不同意','不同意','部分同意','同意','很同意','',''),(3,'0',19,'3','我有一些即使是医生也不能理解的身体感觉','很不同意','不同意','部分同意','同意','很同意','',''),(4,'0',19,'4','我能容易地描述自己的感受','很不同意','不同意','部分同意','同意','很同意','',''),(5,'0',19,'5','我更喜欢分析问题而不仅仅描述它们','很不同意','不同意','部分同意','同意','很同意','',''),(6,'0',19,'6','当我心里难受时，我不知道究竟是悲伤、 害怕、还是恼怒','很不同意','不同意','部分同意','同意','很同意','',''),(7,'0',19,'7','我常常被我身体的一些感觉所困惑','很不同意','不同意','部分同意','同意','很同意','',''),(8,'0',19,'8','我偏向于任事情发生，而不是去了解它们为何发展成那样','很不同意','不同意','部分同意','同意','很同意','',''),(9,'0',19,'9','我有一些自己难以识别的感受','很不同意','不同意','部分同意','同意','很同意','',''),(10,'0',19,'10','知道自己有何内心体验对我来说很重要','很不同意','不同意','部分同意','同意','很同意','',''),(11,'0',19,'11','我难以描述我对别人有何感受','很不同意','不同意','部分同意','同意','很同意','',''),(12,'0',19,'12','人们要我多描述一些我的感受','很不同意','不同意','部分同意','同意','很同意','',''),(13,'0',19,'13','我不知道自己内心在发生一些什么活动','很不同意','不同意','部分同意','同意','很同意','',''),(14,'0',19,'14','我常常不知道我为何会气愤','很不同意','不同意','部分同意','同意','很同意','',''),(15,'0',19,'15','我喜欢与别人讨论他们的日常活动而不是他们的感受','很不同意','不同意','部分同意','同意','很同意','',''),(16,'0',19,'16','我喜欢看“轻松”的娱乐片胜过看关于个人命运的情节片','很不同意','不同意','部分同意','同意','很同意','',''),(17,'0',19,'17','即使是对密友，我也难以表露我内心深处的感受','很不同意','不同意','部分同意','同意','很同意','',''),(18,'0',19,'18','我能感到与某人有亲切感，即使在我们沉默无言之时','很不同意','不同意','部分同意','同意','很同意','',''),(19,'0',19,'19','我觉得省察自己的感受对于解决个人问题是有用的','很不同意','不同意','部分同意','同意','很同意','',''),(20,'0',19,'20','寻找电影或戏剧中隐藏的意义会使人从娱乐中分心','很不同意','不同意','部分同意','同意','很同意','',''),(21,'0',20,'1','我知道什么时候该和别人谈论我的私人问题','很不符合','较不符合','不清楚','较符合','很符合',NULL,NULL),(22,'0',20,'2','当我面对某种困难时，我能够回忆起面对同样困难并克服它们的时候','很不符合','较不符合','不清楚','较符合','很符合',NULL,NULL),(23,'0',20,'3','我期望我能够做好我想做的大多数的事情','很不符合','较不符合','不清楚','较符合','很符合',NULL,NULL),(24,'0',20,'4','别人很容易相信我','很不符合','较不符合','不清楚','较符合','很符合',NULL,NULL),(25,'0',20,'5','我觉得我很难理解别人的身体语言','很不符合','较不符合','不清楚','较符合','很符合',NULL,NULL),(26,'0',20,'6','我生命中的一些重大事件让我重新评估了什么是重要的;什么是不重要的','很不符合','较不符合','不清楚','较符合','很符合',NULL,NULL),(27,'0',20,'7','心境好的时候我就能看到新的希望','很不符合','较不符合','不清楚','较符合','很符合',NULL,NULL),(28,'0',20,'8','我的生活是否有意义，情绪是影响因素之一','很不符合','较不符合','不清楚','较符合','很符合',NULL,NULL),(29,'0',20,'9','我能清楚意识到自己体验的情绪','很不符合','较不符合','不清楚','较符合','很符合',NULL,NULL),(30,'0',20,'10','我希望能够有好的事情发生','很不符合','较不符合','不清楚','较符合','很符合',NULL,NULL),(31,'0',20,'11','我喜欢和别人分享自己的情感','很不符合','较不符合','不清楚','较符合','很符合',NULL,NULL),(32,'0',20,'12','情绪好的时候，我知道如何把它延长','很不符合','较不符合','不清楚','较符合','很符合',NULL,NULL),(33,'0',20,'13','安排有关事情，我尽可能使别人感到满意','很不符合','较不符合','不清楚','较符合','很符合',NULL,NULL),(34,'0',20,'14','我会去找一些让我感到开心的活动','很不符合','较不符合','不清楚','较符合','很符合',NULL,NULL),(35,'0',20,'15','我很清楚我传递给别人的非言语信息','很不符合','较不符合','不清楚','较符合','很符合',NULL,NULL),(36,'0',20,'16','我尽量做的好一些，以给别人留下好的印象','很不符合','较不符合','不清楚','较符合','很符合',NULL,NULL),(37,'0',20,'17','当我心情好的时候，解决问题对我来说很容易','很不符合','较不符合','不清楚','较符合','很符合',NULL,NULL),(38,'0',20,'18','通过观察面部表情，我可以辨别别人的情绪','很不符合','较不符合','不清楚','较符合','很符合',NULL,NULL),(39,'0',20,'19','我知道自己情绪变化的原因','很不符合','较不符合','不清楚','较符合','很符合',NULL,NULL),(40,'0',20,'20','心情好的时候，新奇的想法就会多一些','很不符合','较不符合','不清楚','较符合','很符合',NULL,NULL),(41,'0',20,'21','我能够控制自己的情绪','很不符合','较不符合','不清楚','较符合','很符合',NULL,NULL),(42,'0',20,'22','我很清楚自己在某一刻的情绪','很不符合','较不符合','不清楚','较符合','很符合',NULL,NULL),(43,'0',20,'23','学习时我会想象自己即将取得好成绩，以激励自己','很不符合','较不符合','不清楚','较符合','很符合',NULL,NULL),(44,'0',20,'24','当别人在某个方面做的很好时，我会称赞他们','很不符合','较不符合','不清楚','较符合','很符合',NULL,NULL),(45,'0',20,'25','我能够了解别人传递给我的非言语信息','很不符合','较不符合','不清楚','较符合','很符合',NULL,NULL),(46,'0',20,'26','当别人告诉我他人生中的某件重大事件时，我几乎感觉到好像发生在自己身上一样','很不符合','较不符合','不清楚','较符合','很符合',NULL,NULL),(47,'0',20,'27','当我感到情绪变化时，就会涌现一些新颖的想法','很不符合','较不符合','不清楚','较符合','很符合',NULL,NULL),(48,'0',20,'28','遇到困难时，一想到可能会失败，我就会退却','很不符合','较不符合','不清楚','较符合','很符合',NULL,NULL),(49,'0',20,'29','只要看一眼，我就知道别人的情绪怎样','很不符合','较不符合','不清楚','较符合','很符合',NULL,NULL),(50,'0',20,'30','当别人消沉时我能够帮助他，使他感觉好一点','很不符合','较不符合','不清楚','较符合','很符合',NULL,NULL),(51,'0',20,'31','在挫折面前，我让自己保持良好的情绪以应对挑战','很不符合','较不符合','不清楚','较符合','很符合',NULL,NULL),(52,'0',20,'32','我能够通过别人讲话的语调判断他当时的情绪','很不符合','较不符合','不清楚','较符合','很符合',NULL,NULL),(53,'0',20,'33','我很难理解别人的想法和感受','很不符合','较不符合','不清楚','较符合','很符合',NULL,NULL),(54,'0',21,'1','对那些比我不幸的人，我经常有心软和关怀的感觉','不恰当','有一点恰当','还算恰当','恰当','很恰当',NULL,NULL),(55,'0',21,'2','有时候当其他人有困难或问题时，我并不为他们感到难过','不恰当','有一点恰当','还算恰当','恰当','很恰当',NULL,NULL),(56,'0',21,'3','我的确会投入小说人物中的情感世界','不恰当','有一点恰当','还算恰当','恰当','很恰当',NULL,NULL),(57,'0',21,'4','在紧急的状况中，我感到担忧、害怕而难以平静','不恰当','有一点恰当','还算恰当','恰当','很恰当',NULL,NULL),(58,'0',21,'5','在看电影或看戏时，我通常是旁观的，而且不经常全心投入','不恰当','有一点恰当','还算恰当','恰当','很恰当',NULL,NULL),(59,'0',21,'6','在做决定前，我试着从争论中去看每个人的立场','不恰当','有一点恰当','还算恰当','恰当','很恰当',NULL,NULL),(60,'0',21,'7','当我看到有人被别人利用时，我有点感到想要保护他们','不恰当','有一点恰当','还算恰当','恰当','很恰当',NULL,NULL),(61,'0',21,'8','当我处于一个情绪非常激动的情况中时，我往往感到会无依无靠，不知如何是好','不恰当','有一点恰当','还算恰当','恰当','很恰当',NULL,NULL),(62,'0',21,'9','有时我想象从我朋友的观点来看事情的样子，以便更了解他们','不恰当','有一点恰当','还算恰当','恰当','很恰当',NULL,NULL),(63,'0',21,'10','对我来说，全新的投入一本好书或一部好电影中，是很少有的事','不恰当','有一点恰当','还算恰当','恰当','很恰当',NULL,NULL),(64,'0',21,'11','其他人的不幸通常不会给我带来很大的困扰','不恰当','有一点恰当','还算恰当','恰当','很恰当',NULL,NULL),(65,'0',21,'12','看完戏或电影之后，我觉得自己好像是剧中的某一个角色','不恰当','有一点恰当','还算恰当','恰当','很恰当',NULL,NULL),(66,'0',21,'13','处在紧张情绪的状况中，我会惊慌害怕','不恰当','有一点恰当','还算恰当','恰当','很恰当',NULL,NULL),(67,'0',21,'14','当我看到有人受到不公平的对待时，我有时并不感到非常同情他们','不恰当','有一点恰当','还算恰当','恰当','很恰当',NULL,NULL),(68,'0',21,'15','我相信每个问题都有两面观点，所以我尝试着从不同的观点来看问题','不恰当','有一点恰当','还算恰当','恰当','很恰当',NULL,NULL),(69,'0',21,'16','我认为自己是一个相当软心肠的人','不恰当','有一点恰当','还算恰当','恰当','很恰当',NULL,NULL),(70,'0',21,'17','当我欣赏一部好电影时，我很容易站在某个主角的立场去感受他的心情','不恰当','有一点恰当','还算恰当','恰当','很恰当',NULL,NULL),(71,'0',21,'18','在紧急状况中，我紧张的几乎无法控制自己','不恰当','有一点恰当','还算恰当','恰当','很恰当',NULL,NULL),(72,'0',21,'19','当我对一个人生气时，我通常会尝试着去想一下他的立场','不恰当','有一点恰当','还算恰当','恰当','很恰当',NULL,NULL),(73,'0',21,'20','当我阅读一篇引人的故事或小说时，我想象着：如果故事中的事件发生在我身上，我会感觉怎么样？','不恰当','有一点恰当','还算恰当','恰当','很恰当',NULL,NULL),(74,'0',21,'21','当我看到有人发生意外而急需帮助的时候，我紧张的几乎精神崩溃','不恰当','有一点恰当','还算恰当','恰当','很恰当',NULL,NULL),(75,'0',21,'22','在批评别人前我会试着想象：假如我处在他的情况，我的感受如何','不恰当','有一点恰当','还算恰当','恰当','很恰当',NULL,NULL),(76,'2',37,'1','你收到了最喜欢的礼物，你会有什么样的情绪？','高兴','','',NULL,'',NULL,NULL),(77,'2',37,'2','你最喜欢的水杯被自己不小心摔到地上，摔碎了，你会有什么样的情绪？','悲伤','','',NULL,'',NULL,NULL),(78,'2',37,'3','你被别人无缘无故打了一拳，你会有什么样的情绪？','愤怒','','',NULL,'',NULL,NULL),(79,'2',37,'4','你在逛街时遇到了多年未见的同学，你会有什么样的情绪？','惊讶','','',NULL,'',NULL,NULL),(80,'2',37,'5','早上起床发现枕头旁边有一条蛇，你会有什么样的情绪？','恐惧','','',NULL,'',NULL,NULL),(81,'2',37,'6','你在餐厅吃饭，突然看到碗里有半只虫子，你会有什么样的情绪？','厌恶','','',NULL,'',NULL,NULL),(82,'2',37,'7','你今天参加比赛获得第一名，受到了很多人的夸赞，你会有什么样的情绪？','高兴','','',NULL,'',NULL,NULL),(83,'2',37,'8','你为高考努力准备了很久，结果没考上大学，你会有什么样的情绪？','悲伤','','',NULL,'',NULL,NULL),(84,'2',37,'9','你踩到前面路人扔的香蕉皮，滑倒了，你会有什么样的情绪？','愤怒','','',NULL,'',NULL,NULL),(85,'2',37,'10','有一天，好久不联系的同学突然告诉你他要结婚了，你会有什么样的情绪？','惊讶','','',NULL,'',NULL,NULL),(86,'2',37,'11','你去动物园观赏，一只雄狮突然破笼而出向你迎面扑来，你会有什么样的情绪？','恐惧','','',NULL,'',NULL,NULL),(87,'2',37,'12','你在散步时路过一个垃圾场，里面传出刺鼻、难闻的味道，你会有什么样的情绪？','厌恶','','',NULL,'',NULL,NULL),(88,'2',38,'1','你的好朋友考试获得第一名，当你称赞他特别棒时，你认为他表现出的是什么情绪？','高兴','','',NULL,'',NULL,NULL),(89,'2',38,'2','你和朋友在电梯里嬉闹，导致电梯停电并急速下降，你认为他是什么样的情绪？','恐惧','','',NULL,'',NULL,NULL),(90,'2',38,'3','你带宠物狗上公交车，狗狗在车上随意小便，你认为公交车上的其他人是什么样的情绪？','厌恶','','',NULL,'',NULL,NULL),(91,'2',38,'4','你在街上看到了多年未见的老同学，特别激动的跑到他身后拍了他的肩膀，当他转头看到你时，他是什么样的情绪？','惊讶','','',NULL,'',NULL,NULL),(92,'2',38,'5','忘记了妈妈的生日，也没有给她祝福，你认为她是什么样的情绪？','悲伤','','',NULL,'',NULL,NULL),(93,'2',38,'6','你把朋友刚买的新相机弄坏了，你认为他是什么样的情绪？','愤怒','','',NULL,'',NULL,NULL),(94,'2',38,'7','你想尽办法帮朋友要到了他最喜欢明星的签名照，你认为他是什么样的情绪？','高兴','','',NULL,'',NULL,NULL),(95,'2',38,'8','你拿着玩具蛇吓唬路边的小朋友，你认为小朋友是什么样的情绪？','恐惧','','',NULL,'',NULL,NULL),(96,'2',38,'9','你在商场随地吐痰，你认为旁边的人是什么样的情绪？','厌恶','','',NULL,'',NULL,NULL),(97,'2',38,'10','和朋友一起吃饭，你一口气吃了20个大包子，你认为他是什么样的情绪？','惊讶','','',NULL,'',NULL,NULL),(98,'2',38,'11','你和朋友共同养的宠物狗生病去世了，你认为他是什么样的情绪？','悲伤','','',NULL,'',NULL,NULL),(99,'2',38,'12','你误会同事偷了你的钱包，对他进行了辱骂，你认为他是什么样的情绪？','愤怒','','',NULL,'',NULL,NULL),(100,'5',51,'1','我知道什么时候该和别人谈论我的私人问题','1','2','3','4','5',NULL,NULL),(101,'5',51,'2','当我面对某种困难时，我能够回忆起面对同样困难并克服它们的时候','1','2','3','4','5',NULL,NULL),(102,'5',51,'3','我期望我能够做好我想做的大多数的事情','1','2','3','4','5',NULL,NULL),(103,'5',51,'4','别人很容易相信我','1','2','3','4','5',NULL,NULL),(105,'5',51,'5','我觉得我很难理解别人的身体语言','1','2','3','4','5',NULL,NULL),(106,'5',51,'6','我生命中的一些重大事件让我重新评估了什么是重要的什么是不重要的','1','2','3','4','5',NULL,NULL),(107,'5',51,'7','心境好的时候我就能看到新的希望','1','2','3','4','5',NULL,NULL),(108,'5',51,'8','我的生活是否有意义，情绪是影响因素之一','1','2','3','4','5',NULL,NULL),(109,'5',51,'9','我能清楚意识到自己体验的情绪','1','2','3','4','5',NULL,NULL),(110,'5',51,'10','我希望能够有好的事情发生','1','2','3','4','5',NULL,NULL),(111,'5',51,'11','我喜欢和别人分享自己的情感','1','2','3','4','5',NULL,NULL),(112,'5',51,'12','情绪好的时候，我知道如何把它延长','1','2','3','4','5',NULL,NULL),(113,'5',51,'13','安排有关事情，我尽可能使别人感到满意','1','2','3','4','5',NULL,NULL),(114,'5',51,'14','我会去找一些让我感到开心的活动','1','2','3','4','5',NULL,NULL),(115,'5',51,'15','我很清楚我传递给别人的非言语信息','1','2','3','4','5',NULL,NULL),(116,'5',51,'16','我尽量做的好一些，以给别人留下好的印象','1','2','3','4','5',NULL,NULL),(117,'5',51,'17','当我心情好的时候，解决问题对我来说·很容易','1','2','3','4','5',NULL,NULL),(118,'5',51,'18','通过观察面部表情，我可以辨别别人的情绪','1','2','3','4','5',NULL,NULL),(119,'5',51,'19','我知道自己情绪变化的原因','1','2','3','4','5',NULL,NULL),(120,'5',51,'20','心情好的时候，新奇的想法就会多一些','1','2','3','4','5',NULL,NULL),(121,'5',51,'21','我能够控制自己的情绪','1','2','3','4','5',NULL,NULL),(122,'5',51,'22','我很清楚自己在某一刻的情绪','1','2','3','4','5',NULL,NULL),(123,'5',51,'23','学习时我会想象自己即将取得好成绩，以激励自己','1','2','3','4','5',NULL,NULL),(124,'5',51,'24','当别人在某个方面做的很好时，我会称赞他们','1','2','3','4','5',NULL,NULL),(125,'5',51,'25','我能够了解别人传递给我的非言语信息','1','2','3','4','5',NULL,NULL),(126,'5',51,'26','当别人告诉我他人生中的某件重大事件时，我几乎感觉到好像发生在自己身上一样','1','2','3','4','5',NULL,NULL),(127,'5',51,'27','当我感到情绪变化时，就会涌现一些新颖的想法','1','2','3','4','5',NULL,NULL),(128,'5',51,'28','遇到困难时，一想到可能会失败，我就会退却','1','2','3','4','5',NULL,NULL),(129,'5',51,'29','只要看一眼，我就知道别人的情绪怎样','1','2','3','4','5',NULL,NULL),(130,'5',51,'30','当别人消沉时我能够帮助他，使他感觉好一点','1','2','3','4','5',NULL,NULL),(131,'5',51,'31','在挫折面前，我让自己保持良好的情绪以应对挑战','1','2','3','4','5',NULL,NULL),(132,'5',51,'32','我能够通过别人讲话的语调判断他当时的情绪','1','2','3','4','5',NULL,NULL),(133,'5',51,'33','我很难理解别人的想法和感受','1','2','3','4','5',NULL,NULL),(141,'5',52,'1','我觉得比平时容易紧张或着急','没有或很少时间','小部分时间','相当多时间','绝大部分或全部时间',NULL,NULL,NULL),(142,'5',52,'2','我无缘无故地感到害怕','没有或很少时间','小部分时间','相当多时间','绝大部分或全部时间',NULL,NULL,NULL),(143,'5',52,'3','我容易心里烦乱或觉得惊恐','没有或很少时间','小部分时间','相当多时间','绝大部分或全部时间',NULL,NULL,NULL),(144,'5',52,'4','我觉得我可能将要发疯','没有或很少时间','小部分时间','相当多时间','绝大部分或全部时间',NULL,NULL,NULL),(145,'5',52,'5','我觉得一切都很好，也不会发生什么不幸','没有或很少时间','小部分时间','相当多时间','绝大部分或全部时间',NULL,NULL,NULL),(146,'5',52,'6','我手脚发抖打颤','没有或很少时间','小部分时间','相当多时间','绝大部分或全部时间',NULL,NULL,NULL),(147,'5',52,'7','我因为头痛、颈痛和背痛而苦恼','没有或很少时间','小部分时间','相当多时间','绝大部分或全部时间',NULL,NULL,NULL),(148,'5',52,'8','我感觉容易衰弱和疲乏','没有或很少时间','小部分时间','相当多时间','绝大部分或全部时间',NULL,NULL,NULL),(149,'5',52,'9','我觉得心平气和，并且容易安静坐着','没有或很少时间','小部分时间','相当多时间','绝大部分或全部时间',NULL,NULL,NULL),(150,'5',52,'10','我觉得心跳很快','没有或很少时间','小部分时间','相当多时间','绝大部分或全部时间',NULL,NULL,NULL),(151,'5',52,'11','我因为一阵阵头晕而苦恼','没有或很少时间','小部分时间','相当多时间','绝大部分或全部时间',NULL,NULL,NULL),(152,'5',52,'12','我有晕倒发作，或觉得要晕倒似的','没有或很少时间','小部分时间','相当多时间','绝大部分或全部时间',NULL,NULL,NULL),(153,'5',52,'13','我吸气呼气都感到很容易','没有或很少时间','小部分时间','相当多时间','绝大部分或全部时间',NULL,NULL,NULL),(154,'5',52,'14','我的手脚麻木和刺痛','没有或很少时间','小部分时间','相当多时间','绝大部分或全部时间',NULL,NULL,NULL),(155,'5',52,'15','我因为胃痛和消化不良而苦恼','没有或很少时间','小部分时间','相当多时间','绝大部分或全部时间',NULL,NULL,NULL),(156,'5',52,'16','我常常要小便','没有或很少时间','小部分时间','相当多时间','绝大部分或全部时间',NULL,NULL,NULL),(157,'5',52,'17','我的手脚常常是干燥温暖的','没有或很少时间','小部分时间','相当多时间','绝大部分或全部时间',NULL,NULL,NULL),(158,'5',52,'18','我脸红发热','没有或很少时间','小部分时间','相当多时间','绝大部分或全部时间',NULL,NULL,NULL),(159,'5',52,'19','我容易入睡并且一夜睡得很好','没有或很少时间','小部分时间','相当多时间','绝大部分或全部时间',NULL,NULL,NULL),(160,'5',52,'20','我做恶梦','没有或很少时间','小部分时间','相当多时间','绝大部分或全部时间',NULL,NULL,NULL),(161,'5',53,'1','我觉得闷闷不乐,情绪低沉','没有或很少时间','小部分时间','相当多时间','绝大部分或全部时间',NULL,NULL,NULL),(162,'5',53,'2','我觉得一天之中早晨最好','没有或很少时间','小部分时间','相当多时间','绝大部分或全部时间',NULL,NULL,NULL),(163,'5',53,'3','我一阵阵哭出来或觉得想哭 ','没有或很少时间','小部分时间','相当多时间','绝大部分或全部时间',NULL,NULL,NULL),(164,'5',53,'4','我晚上睡眠不好','没有或很少时间','小部分时间','相当多时间','绝大部分或全部时间',NULL,NULL,NULL),(165,'5',53,'5','我吃得跟平常一样多','没有或很少时间','小部分时间','相当多时间','绝大部分或全部时间',NULL,NULL,NULL),(166,'5',53,'6','我与异性密切接触时和以往一样感到愉快','没有或很少时间','小部分时间','相当多时间','绝大部分或全部时间',NULL,NULL,NULL),(167,'5',53,'7','我发觉我的体重在下降','没有或很少时间','小部分时间','相当多时间','绝大部分或全部时间',NULL,NULL,NULL),(168,'5',53,'8','我有便秘的苦恼','没有或很少时间','小部分时间','相当多时间','绝大部分或全部时间',NULL,NULL,NULL),(169,'5',53,'9','我心跳比平时快','没有或很少时间','小部分时间','相当多时间','绝大部分或全部时间',NULL,NULL,NULL),(170,'5',53,'10','我无缘无故地感到疲乏','没有或很少时间','小部分时间','相当多时间','绝大部分或全部时间',NULL,NULL,NULL),(171,'5',53,'11','我的头脑跟平常一样清楚','没有或很少时间','小部分时间','相当多时间','绝大部分或全部时间',NULL,NULL,NULL),(172,'5',53,'12','我觉得经常做的事情并没困难','没有或很少时间','小部分时间','相当多时间','绝大部分或全部时间',NULL,NULL,NULL),(173,'5',53,'13','我觉得不安而平静不下来','没有或很少时间','小部分时间','相当多时间','绝大部分或全部时间',NULL,NULL,NULL),(174,'5',53,'14','我对未来抱有希望','没有或很少时间','小部分时间','相当多时间','绝大部分或全部时间',NULL,NULL,NULL),(175,'5',53,'15','我比平常容易生气激动','没有或很少时间','小部分时间','相当多时间','绝大部分或全部时间',NULL,NULL,NULL),(176,'5',53,'16','我觉得做出决定是容易的','没有或很少时间','小部分时间','相当多时间','绝大部分或全部时间',NULL,NULL,NULL),(177,'5',53,'17','我觉得自己是个有用的人，有人需要我','没有或很少时间','小部分时间','相当多时间','绝大部分或全部时间',NULL,NULL,NULL),(178,'5',53,'18','我的生活过得很有意思','没有或很少时间','小部分时间','相当多时间','绝大部分或全部时间',NULL,NULL,NULL),(179,'5',53,'19','我认为如果我死了，别人会生活得更好','没有或很少时间','小部分时间','相当多时间','绝大部分或全部时间',NULL,NULL,NULL),(180,'5',53,'20','平常感兴趣的事我仍然感兴趣','没有或很少时间','小部分时间','相当多时间','绝大部分或全部时间',NULL,NULL,NULL),(181,'6',54,'1','焦虑心境','无症状','轻微','中等','重度','极度',NULL,NULL),(182,'6',54,'2','紧张','无症状','轻微','中等','重度','极度',NULL,NULL),(183,'6',54,'3','害怕','无症状','轻微','中等','重度','极度',NULL,NULL),(184,'6',54,'4','失眠','无症状','轻微','中等','重度','极度',NULL,NULL),(185,'6',54,'5','记忆或注意障碍','无症状','轻微','中等','重度','极度',NULL,NULL),(186,'6',54,'6','抑郁心境','无症状','轻微','中等','重度','极度',NULL,NULL),(187,'6',54,'7','肌肉系统症状','无症状','轻微','中等','重度','极度',NULL,NULL),(188,'6',54,'8','感觉系统症状','无症状','轻微','中等','重度','极度',NULL,NULL),(189,'6',54,'9','心血管系统症状','无症状','轻微','中等','重度','极度',NULL,NULL),(190,'6',54,'10','呼吸系统症状','无症状','轻微','中等','重度','极度',NULL,NULL),(191,'6',54,'11','胃肠道症状','无症状','轻微','中等','重度','极度',NULL,NULL),(192,'6',54,'12','生殖泌尿系统症状','无症状','轻微','中等','重度','极度',NULL,NULL),(193,'6',54,'13','植物神经系统症状','无症状','轻微','中等','重度','极度',NULL,NULL),(194,'6',54,'14','会谈时行为表现','无症状','轻微','中等','重度','极度',NULL,NULL),(201,'6',55,'1','抑郁情绪','无症状','轻微','中等','重度','极度',NULL,NULL),(202,'6',55,'2','有罪感','无症状','轻微','中等','重度','极度',NULL,NULL),(203,'6',55,'3','自杀','无症状','轻微','中等','重度','极度',NULL,NULL),(204,'6',55,'4','入睡困难','无症状','轻微','中等','重度','极度',NULL,NULL),(205,'6',55,'5','睡眠不深','无症状','轻微','中等','重度','极度',NULL,NULL),(206,'6',55,'6','早醒','无症状','轻微','中等','重度','极度',NULL,NULL),(207,'6',55,'7','工作和兴趣','无症状','轻微','中等','重度','极度',NULL,NULL),(208,'6',55,'8','迟缓','无症状','轻微','中等','重度','极度',NULL,NULL),(209,'6',55,'9','激越','无症状','轻微','中等','重度','极度',NULL,NULL),(210,'6',55,'10','精神性焦虑','无症状','轻微','中等','重度','极度',NULL,NULL),(211,'6',55,'11','躯体性焦虑','无症状','轻微','中等','重度','极度',NULL,NULL),(212,'6',55,'12','胃肠道炎症','无症状','轻微','中等','重度','极度',NULL,NULL),(213,'6',55,'13','全身症状','无症状','轻微','中等','重度','极度',NULL,NULL),(214,'6',55,'14','性症状','无症状','轻微','中等','重度','极度',NULL,NULL),(215,'6',55,'15','疑病','无症状','轻微','中等','重度','极度',NULL,NULL),(216,'6',55,'16','体重减轻','无症状','轻微','中等','重度','极度',NULL,NULL),(217,'6',55,'17','自知力','无症状','轻微','中等','重度','极度',NULL,NULL),(218,'6',55,'18','日夜变化早','无症状','轻微','中等','重度','极度',NULL,NULL),(219,'6',55,'19','日夜变化晚','无症状','轻微','中等','重度','极度',NULL,NULL),(220,'6',55,'20','人格或现实解体','无症状','轻微','中等','重度','极度',NULL,NULL),(221,'6',55,'21','偏执症状','无症状','轻微','中等','重度','极度',NULL,NULL),(222,'6',55,'22','强迫症状','无症状','轻微','中等','重度','极度',NULL,NULL),(223,'6',55,'23','能力减退感','无症状','轻微','中等','重度','极度',NULL,NULL),(224,'6',55,'24','绝望感','无症状','轻微','中等','重度','极度',NULL,NULL),(225,'6',55,'25','自卑感','无症状','轻微','中等','重度','极度',NULL,NULL),(231,'6',56,'1','概念紊乱','无','极轻','轻度','中度','偏重','重度','极重'),(232,'6',56,'2','概念紊乱','无','极轻','轻度','中度','偏重','重度','极重'),(233,'6',56,'3','幻觉行为','无','极轻','轻度','中度','偏重','重度','极重'),(234,'6',56,'4','兴奋','无','极轻','轻度','中度','偏重','重度','极重'),(235,'6',56,'5','夸大','无','极轻','轻度','中度','偏重','重度','极重'),(236,'6',56,'6','猜疑或被害','无','极轻','轻度','中度','偏重','重度','极重'),(237,'6',56,'7','敌对性','无','极轻','轻度','中度','偏重','重度','极重'),(238,'6',56,'8','情感迟钝','无','极轻','轻度','中度','偏重','重度','极重'),(239,'6',56,'9','情感退缩','无','极轻','轻度','中度','偏重','重度','极重'),(240,'6',56,'10','情感交流障碍','无','极轻','轻度','中度','偏重','重度','极重'),(241,'6',56,'11','被动或淡漠','无','极轻','轻度','中度','偏重','重度','极重'),(242,'6',56,'12','抽象思维','无','极轻','轻度','中度','偏重','重度','极重'),(243,'6',56,'13','交谈缺乏自发性和流畅性','无','极轻','轻度','中度','偏重','重度','极重'),(244,'6',56,'14','刻板思维','无','极轻','轻度','中度','偏重','重度','极重'),(245,'6',56,'15','担心身体健康','无','极轻','轻度','中度','偏重','重度','极重'),(246,'6',56,'16','焦虑','无','极轻','轻度','中度','偏重','重度','极重'),(247,'6',56,'17','罪恶观念','无','极轻','轻度','中度','偏重','重度','极重'),(248,'6',56,'18','紧张','无','极轻','轻度','中度','偏重','重度','极重'),(249,'6',56,'19','装相和作态','无','极轻','轻度','中度','偏重','重度','极重'),(250,'6',56,'20','抑郁','无','极轻','轻度','中度','偏重','重度','极重'),(251,'6',56,'21','动作迟缓','无','极轻','轻度','中度','偏重','重度','极重'),(252,'6',56,'22','不合作','无','极轻','轻度','中度','偏重','重度','极重'),(253,'6',56,'23','异常思维内容','无','极轻','轻度','中度','偏重','重度','极重'),(254,'6',56,'24','定向障碍','无','极轻','轻度','中度','偏重','重度','极重'),(255,'6',56,'25','注意障碍','无','极轻','轻度','中度','偏重','重度','极重'),(256,'6',56,'26','自知力缺乏','无','极轻','轻度','中度','偏重','重度','极重'),(257,'6',56,'27','意志障碍','无','极轻','轻度','中度','偏重','重度','极重'),(258,'6',56,'28','冲动控制障碍','无','极轻','轻度','中度','偏重','重度','极重'),(259,'6',56,'29','先占观念','无','极轻','轻度','中度','偏重','重度','极重'),(260,'6',56,'30','主动社交回避','无','极轻','轻度','中度','偏重','重度','极重');

/*Table structure for table `shtl_log` */

DROP TABLE IF EXISTS `shtl_log`;

CREATE TABLE `shtl_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志id',
  `patient_id` bigint(20) DEFAULT NULL COMMENT '患者id',
  `patient_name` varchar(100) DEFAULT NULL COMMENT '患者名称',
  `sex` char(1) DEFAULT NULL COMMENT '性别',
  `age` int(20) DEFAULT NULL COMMENT '年龄',
  `education` varchar(20) DEFAULT NULL COMMENT '文化程度',
  `job` varchar(20) DEFAULT NULL COMMENT '职业',
  `diagnosis` varchar(50) DEFAULT NULL COMMENT '诊断',
  `test_day` datetime DEFAULT NULL COMMENT '测试日期',
  `correct` int(20) DEFAULT NULL COMMENT '正确率',
  `average` double(20,2) DEFAULT NULL COMMENT '平均反应时',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='社会推理日志表';

/*Data for the table `shtl_log` */

/*Table structure for table `statistics_table1` */

DROP TABLE IF EXISTS `statistics_table1`;

CREATE TABLE `statistics_table1` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '统计id',
  `year` int(20) DEFAULT NULL COMMENT '年限',
  `registered_count` int(20) DEFAULT '0' COMMENT '注册人数',
  `complete_count` int(20) DEFAULT '0' COMMENT '治疗完成人数',
  `status` varchar(20) DEFAULT NULL COMMENT '状态',
  `del_flag` varchar(20) DEFAULT NULL COMMENT '是否删除',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='统计表1';

/*Data for the table `statistics_table1` */

insert  into `statistics_table1`(`id`,`year`,`registered_count`,`complete_count`,`status`,`del_flag`,`create_by`,`create_time`) values (1,2020,0,0,NULL,NULL,NULL,'2020-09-02 14:34:09'),(2,2021,0,0,NULL,NULL,NULL,'2020-09-11 17:24:40'),(3,2022,0,0,NULL,NULL,NULL,'2020-09-14 10:33:57'),(4,2024,0,0,NULL,NULL,NULL,'2020-09-14 10:33:59'),(5,2026,0,0,NULL,NULL,NULL,'2020-09-14 10:34:05'),(6,2023,0,0,NULL,NULL,NULL,'2020-09-14 10:34:41'),(7,2025,0,0,NULL,NULL,NULL,'2020-09-14 10:40:32'),(8,2010,0,0,NULL,NULL,NULL,'2020-10-28 17:17:08'),(9,2019,0,0,NULL,NULL,NULL,'2020-10-28 17:17:11'),(10,2036,0,0,NULL,NULL,NULL,'2020-10-28 17:55:53');

/*Table structure for table `statistics_table2` */

DROP TABLE IF EXISTS `statistics_table2`;

CREATE TABLE `statistics_table2` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '统计id',
  `year` int(20) DEFAULT NULL COMMENT '年限',
  `january_count` int(20) DEFAULT '0' COMMENT '一月人数',
  `february_count` int(20) DEFAULT '0' COMMENT '二月人数',
  `march` int(20) DEFAULT '0' COMMENT '三月',
  `april` int(20) DEFAULT '0' COMMENT '四月',
  `may` int(20) DEFAULT '0' COMMENT '五月',
  `june` int(20) DEFAULT '0' COMMENT '六月',
  `july` int(20) DEFAULT '0' COMMENT '七月',
  `august` int(20) DEFAULT '0' COMMENT '八月 ',
  `september` int(20) DEFAULT '0' COMMENT '九月',
  `october` int(20) DEFAULT '0' COMMENT '十月',
  `november` int(20) DEFAULT '0' COMMENT '十一月',
  `december` int(20) DEFAULT '0' COMMENT '十二月人数',
  `status` varchar(20) DEFAULT NULL COMMENT '状态',
  `del_flag` varchar(20) DEFAULT NULL COMMENT '是否删除',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='统计表2';

/*Data for the table `statistics_table2` */

insert  into `statistics_table2`(`id`,`year`,`january_count`,`february_count`,`march`,`april`,`may`,`june`,`july`,`august`,`september`,`october`,`november`,`december`,`status`,`del_flag`,`create_by`,`create_time`) values (1,2020,1,2,3,50,30,2,20,1,36,4,30,49,NULL,NULL,NULL,'2020-09-02 17:21:35'),(2,2021,0,0,1,0,0,0,13,0,1,1,0,0,NULL,NULL,NULL,'2020-09-11 17:30:35'),(3,2022,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,NULL,'2020-09-14 10:33:57'),(4,2024,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,NULL,'2020-09-14 10:34:00'),(5,2026,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,NULL,'2020-09-14 10:34:05'),(6,2023,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,NULL,'2020-09-14 10:34:41'),(7,2025,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,NULL,'2020-09-14 10:40:33'),(8,2010,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,NULL,'2020-10-28 17:17:08'),(9,2019,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,NULL,'2020-10-28 17:17:11'),(10,2036,0,0,0,0,0,0,0,0,0,0,0,0,NULL,NULL,NULL,'2020-10-28 17:55:54');

/*Table structure for table `statistics_table3` */

DROP TABLE IF EXISTS `statistics_table3`;

CREATE TABLE `statistics_table3` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '统计id',
  `year` int(20) DEFAULT NULL COMMENT '年限',
  `jlz_count` int(20) DEFAULT '0' COMMENT '焦虑症人数',
  `yyz_count` int(20) DEFAULT '0' COMMENT '抑郁症人数',
  `qpz_count` int(20) DEFAULT '0' COMMENT '强迫症人数',
  `jsflz_count` int(20) DEFAULT '0' COMMENT '精神分裂症rens',
  `smza_count` int(20) DEFAULT '0' COMMENT '失眠障碍',
  `sxqgza_count` int(20) DEFAULT '0' COMMENT '双向情感障碍',
  `qt_count` int(20) DEFAULT '0' COMMENT '其他人数',
  `status` varchar(20) DEFAULT NULL COMMENT '状态',
  `del_flag` varchar(20) DEFAULT NULL COMMENT '是否删除',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='统计表3';

/*Data for the table `statistics_table3` */

insert  into `statistics_table3`(`id`,`year`,`jlz_count`,`yyz_count`,`qpz_count`,`jsflz_count`,`smza_count`,`sxqgza_count`,`qt_count`,`status`,`del_flag`,`create_by`,`create_time`) values (3,2020,7,1,5,7,3,4,8,NULL,NULL,NULL,'2020-09-02 15:02:42'),(4,2021,1,0,1,0,0,0,0,NULL,NULL,NULL,'2020-09-11 17:30:35'),(5,2022,0,0,0,0,0,0,0,NULL,NULL,NULL,'2020-09-14 10:33:57'),(6,2024,0,0,0,0,0,0,0,NULL,NULL,NULL,'2020-09-14 10:34:00'),(7,2026,0,0,0,0,0,0,0,NULL,NULL,NULL,'2020-09-14 10:34:05'),(8,2023,0,0,0,0,0,0,0,NULL,NULL,NULL,'2020-09-14 10:34:41'),(9,2025,0,0,0,0,0,0,0,NULL,NULL,NULL,'2020-09-14 10:40:33'),(10,2010,0,0,0,0,0,0,0,NULL,NULL,NULL,'2020-10-28 17:17:08'),(11,2019,0,0,0,0,0,0,0,NULL,NULL,NULL,'2020-10-28 17:17:11'),(12,2036,0,0,0,0,0,0,0,NULL,NULL,NULL,'2020-10-28 17:55:54');

/*Table structure for table `sys_attachment` */

DROP TABLE IF EXISTS `sys_attachment`;

CREATE TABLE `sys_attachment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '附件ID',
  `hash` varchar(64) DEFAULT NULL COMMENT '附件哈希',
  `name` varchar(128) DEFAULT '' COMMENT '原名称',
  `file_name` varchar(128) DEFAULT '' COMMENT '附件名称',
  `size` bigint(20) DEFAULT '0' COMMENT '附件大小',
  `path` varchar(128) DEFAULT NULL COMMENT '附件路径',
  `abs_path` varchar(128) DEFAULT NULL COMMENT '附件绝对路径',
  `userId` bigint(20) NOT NULL COMMENT '上传者',
  `ext` varchar(64) DEFAULT '' COMMENT '附件扩展子',
  `type_` varchar(64) DEFAULT '0' COMMENT '附件类型，值范围为以下值：0:rest,1:img,2:video,3:pdf,4:music',
  `status` int(1) DEFAULT '0' COMMENT '状态（0正常 1已删除）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='附件表';

/*Data for the table `sys_attachment` */

/*Table structure for table `sys_config` */

DROP TABLE IF EXISTS `sys_config`;

CREATE TABLE `sys_config` (
  `config_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='参数配置表';

/*Data for the table `sys_config` */

insert  into `sys_config`(`config_id`,`config_name`,`config_key`,`config_value`,`config_type`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values (1,'主框架页-默认皮肤样式名称','sys.index.skinName','skin-blue','Y','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow'),(2,'用户管理-账号初始密码','sys.user.initPassword','123456','Y','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','初始化密码 123456'),(3,'主框架页-侧边栏主题','sys.index.sideTheme','theme-dark','Y','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','深色主题theme-dark，浅色主题theme-light');

/*Table structure for table `sys_dept` */

DROP TABLE IF EXISTS `sys_dept`;

CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父部门id',
  `ancestors` varchar(50) DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `leader` varchar(20) DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `status` char(1) DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `mjrs` int(6) DEFAULT NULL,
  `fjrs` int(6) DEFAULT NULL,
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=174 DEFAULT CHARSET=utf8 COMMENT='部门表';

/*Data for the table `sys_dept` */

insert  into `sys_dept`(`dept_id`,`parent_id`,`ancestors`,`dept_name`,`order_num`,`leader`,`phone`,`email`,`status`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`,`mjrs`,`fjrs`) values (100,0,'0','医院管理',0,'管理员','15888888888','','0','0','admin','2018-03-16 11:33:00','admin','2021-01-14 16:42:39',0,0),(171,100,'0,100','精神科',1,NULL,NULL,NULL,'0','0','admin','2020-07-06 16:36:21','admin','2021-01-14 16:42:39',NULL,NULL),(172,100,'0,100','神经内科',1,NULL,NULL,NULL,'0','0','admin','2020-07-06 16:37:23','',NULL,NULL,NULL),(173,100,'0,100','康复科',1,NULL,NULL,NULL,'0','0','admin','2020-07-06 16:37:47','',NULL,NULL,NULL);

/*Table structure for table `sys_dict_data` */

DROP TABLE IF EXISTS `sys_dict_data`;

CREATE TABLE `sys_dict_data` (
  `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) DEFAULT '0' COMMENT '字典排序',
  `dict_label` varchar(100) DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8 COMMENT='字典数据表';

/*Data for the table `sys_dict_data` */

insert  into `sys_dict_data`(`dict_code`,`dict_sort`,`dict_label`,`dict_value`,`dict_type`,`css_class`,`list_class`,`is_default`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values (1,1,'男','0','sys_user_sex','','','Y','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','性别男'),(2,2,'女','1','sys_user_sex','','','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','性别女'),(4,1,'显示','0','sys_show_hide','','primary','Y','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','显示菜单'),(5,2,'隐藏','1','sys_show_hide','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','隐藏菜单'),(6,1,'正常','0','sys_normal_disable','','primary','Y','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','正常状态'),(7,2,'停用','1','sys_normal_disable','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','停用状态'),(8,1,'正常','0','sys_job_status','','primary','Y','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','正常状态'),(9,2,'暂停','1','sys_job_status','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','停用状态'),(10,1,'默认','DEFAULT','sys_job_group','','','Y','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','默认分组'),(11,2,'系统','SYSTEM','sys_job_group','','','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','系统分组'),(12,1,'是','Y','sys_yes_no','','primary','Y','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','系统默认是'),(13,2,'否','N','sys_yes_no','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','系统默认否'),(14,1,'通知','1','sys_notice_type','','warning','Y','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','通知'),(15,2,'公告','2','sys_notice_type','','success','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','公告'),(16,1,'正常','0','sys_notice_status','','primary','Y','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','正常状态'),(17,2,'关闭','1','sys_notice_status','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','关闭状态'),(18,1,'新增','1','sys_oper_type','','info','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','新增操作'),(19,2,'修改','2','sys_oper_type','','info','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','修改操作'),(20,3,'删除','3','sys_oper_type','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','删除操作'),(21,4,'授权','4','sys_oper_type','','primary','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','授权操作'),(22,5,'导出','5','sys_oper_type','','warning','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','导出操作'),(23,6,'导入','6','sys_oper_type','','warning','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','导入操作'),(24,7,'强退','7','sys_oper_type','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','强退操作'),(25,8,'生成代码','8','sys_oper_type','','warning','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','生成操作'),(26,9,'清空数据','9','sys_oper_type','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','清空操作'),(27,1,'成功','0','sys_common_status','','primary','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','正常状态'),(28,2,'失败','1','sys_common_status','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','停用状态'),(51,1,'通用','1','system_value_type',NULL,NULL,'N','0','admin','2019-12-31 09:21:12','',NULL,'通用'),(52,1,'通用','1','system_purchase_type',NULL,NULL,'N','0','admin','2019-12-31 09:24:26','',NULL,'通用'),(53,2,'非通用','2','system_purchase_type',NULL,NULL,'N','0','admin','2019-12-31 09:24:41','',NULL,'非通用'),(54,1,'通用','1','system_depreciation_status',NULL,NULL,'N','0','admin','2019-12-31 09:25:05','',NULL,'通用'),(55,1,'个人办公','1','sys_use_type',NULL,NULL,'N','0','admin','2020-01-06 13:39:21','',NULL,'个人办公'),(56,2,'公共设施','2','sys_use_type',NULL,NULL,'N','0','admin','2020-01-06 13:39:37','',NULL,'公共设施'),(57,1,'未婚','0','pati_user_maritalStatus',NULL,NULL,'N','0','admin','2020-07-09 09:42:16','',NULL,'未婚'),(58,2,'已婚','1','pati_user_maritalStatus',NULL,NULL,'N','0','admin','2020-07-09 09:42:36','',NULL,'已婚'),(59,1,'未开始','1','pati_task_status',NULL,NULL,'N','0','admin','2020-07-09 09:53:23','admin','2020-07-15 15:35:18','未开始'),(60,2,'进行中','2','pati_task_status',NULL,NULL,'N','0','admin','2020-07-09 09:53:46','admin','2020-07-15 15:35:13','进行中'),(61,3,'已完成','3','pati_task_status',NULL,NULL,'N','0','admin','2020-07-09 09:54:08','admin','2020-07-15 15:35:37','已完成'),(62,10,'添加测评','10','sys_oper_type',NULL,NULL,'N','0','admin','2020-07-10 16:13:05','',NULL,'将测评添加都任务表'),(63,0,'社会认知能力任务','0','pati_task_type',NULL,NULL,'N','0','admin','2020-07-14 18:07:46','',NULL,NULL),(64,1,'基础社会认知任务','1','pati_task_type',NULL,NULL,'N','0','admin','2020-07-14 18:08:39','',NULL,NULL),(65,2,'进阶社会认知任务','2','pati_task_type',NULL,NULL,'N','0','admin','2020-07-14 18:09:09','',NULL,NULL),(66,3,'高级社会认知任务','3','pati_task_type',NULL,NULL,'N','0','admin','2020-07-14 18:09:30','',NULL,NULL),(67,0,'ICD-10','0','dis_class_code',NULL,NULL,'N','0','admin','2020-07-17 16:42:57','admin','2020-07-17 16:43:51',NULL),(68,1,'DSM-5','1','dis_class_code',NULL,NULL,'N','0','admin','2020-07-17 16:43:25','',NULL,NULL),(69,1,'C001','C001','pati_task_workstation',NULL,NULL,'N','0','admin','2020-07-21 16:15:23','admin','2021-01-12 10:16:06',NULL),(70,2,'C002','C002','pati_task_workstation',NULL,NULL,'N','0','admin','2020-07-21 16:16:15','admin','2021-01-12 09:54:54',NULL),(75,0,'工人','0','pati_job_type',NULL,NULL,'N','0','admin','2020-08-25 10:22:29','admin','2020-08-27 09:12:00',NULL),(76,1,'农民','1','pati_job_type',NULL,NULL,'N','0','admin','2020-08-25 10:22:48','admin','2020-08-27 09:12:17',NULL),(77,2,'军人','2','pati_job_type',NULL,NULL,'N','0','admin','2020-08-25 10:23:02','admin','2020-08-27 09:12:25',NULL),(78,3,'专业技术人员','3','pati_job_type',NULL,NULL,'N','0','admin','2020-08-25 10:23:19','admin','2020-08-27 09:12:47',NULL),(79,4,'医生','4','pati_job_type',NULL,NULL,'N','0','admin','2020-08-25 10:23:36','admin','2020-08-27 09:13:07',NULL),(80,5,'护士','5','pati_job_type',NULL,NULL,'N','0','admin','2020-08-25 10:23:52','admin','2020-08-27 09:13:17',NULL),(81,6,'学生','6','pati_job_type',NULL,NULL,'N','0','admin','2020-08-25 10:24:18','admin','2020-08-27 09:13:31',NULL),(82,0,'小学','0','pati_edu_type',NULL,NULL,'N','0','admin','2020-08-25 10:28:28','',NULL,NULL),(83,1,'初中','1','pati_edu_type',NULL,NULL,'N','0','admin','2020-08-25 10:28:45','',NULL,NULL),(84,2,'高中','2','pati_edu_type',NULL,NULL,'N','0','admin','2020-08-25 10:29:00','',NULL,NULL),(85,3,'大专','3','pati_edu_type',NULL,NULL,'N','0','admin','2020-08-25 10:29:25','',NULL,NULL),(86,4,'本科','4','pati_edu_type',NULL,NULL,'N','0','admin','2020-08-25 10:29:38','',NULL,NULL),(87,5,'硕士研究生','5','pati_edu_type',NULL,NULL,'N','0','admin','2020-08-25 10:30:09','',NULL,NULL),(88,6,'博士研究生','6','pati_edu_type',NULL,NULL,'N','0','admin','2020-08-25 10:30:31','',NULL,NULL),(89,7,'其他','7','pati_edu_type',NULL,NULL,'N','0','admin','2020-08-25 10:30:47','',NULL,NULL),(90,7,'教师','7','pati_job_type',NULL,NULL,'N','0','admin','2020-08-25 10:37:18','admin','2020-08-27 09:13:40',NULL),(91,0,'门诊','0','pati_source',NULL,NULL,'N','0','admin','2020-08-25 10:38:53','',NULL,NULL),(92,1,'病房','1','pati_source',NULL,NULL,'N','0','admin','2020-08-25 10:39:04','',NULL,NULL),(93,2,'其他','2','pati_source',NULL,NULL,'N','0','admin','2020-08-25 10:39:14','',NULL,NULL),(94,8,'职员','8','pati_job_type',NULL,NULL,'N','0','admin','2020-08-27 09:14:18','',NULL,NULL),(95,9,'警察','9','pati_job_type',NULL,NULL,'N','0','admin','2020-08-27 09:14:42','',NULL,NULL),(96,10,'基层公务员','10','pati_job_type',NULL,NULL,'N','0','admin','2020-08-27 09:15:06','',NULL,NULL),(97,11,'保安人员','11','pati_job_type',NULL,NULL,'N','0','admin','2020-08-27 09:15:44','',NULL,NULL),(98,12,'行政官员','12','pati_job_type',NULL,NULL,'N','0','admin','2020-08-27 09:16:02','',NULL,NULL),(99,13,'企业管理人员','13','pati_job_type',NULL,NULL,'N','0','admin','2020-08-27 09:16:28','',NULL,NULL),(100,14,'运动员','14','pati_job_type',NULL,NULL,'N','0','admin','2020-08-27 09:16:51','',NULL,NULL),(101,15,'文艺创作','15','pati_job_type',NULL,NULL,'N','0','admin','2020-08-27 09:17:10','',NULL,NULL),(102,16,'销售','16','pati_job_type',NULL,NULL,'N','0','admin','2020-08-27 09:17:34','',NULL,NULL),(103,17,'财务','17','pati_job_type',NULL,NULL,'N','0','admin','2020-08-27 09:17:54','',NULL,NULL),(104,18,'设计','18','pati_job_type',NULL,NULL,'N','0','admin','2020-08-27 09:18:13','',NULL,NULL),(105,19,'程序开发人员','19','pati_job_type',NULL,NULL,'N','0','admin','2020-08-27 09:18:49','',NULL,NULL),(106,20,'服务员','20','pati_job_type',NULL,NULL,'N','0','admin','2020-08-27 09:19:14','',NULL,NULL),(107,21,'厨师','21','pati_job_type',NULL,NULL,'N','0','admin','2020-08-27 09:19:42','',NULL,NULL),(108,22,'科研研发','22','pati_job_type',NULL,NULL,'N','0','admin','2020-08-27 09:20:22','',NULL,NULL),(109,23,'物资管理','23','pati_job_type',NULL,NULL,'N','0','admin','2020-08-27 09:20:45','',NULL,NULL),(110,24,'个体经营','24','pati_job_type',NULL,NULL,'N','0','admin','2020-08-27 09:21:11','',NULL,NULL),(111,25,'演员','25','pati_job_type',NULL,NULL,'N','0','admin','2020-08-27 09:21:41','',NULL,NULL),(112,26,'传统媒体','26','pati_job_type',NULL,NULL,'N','0','admin','2020-08-27 09:22:02','',NULL,NULL),(113,27,'离退人员','27','pati_job_type',NULL,NULL,'N','0','admin','2020-08-27 09:22:48','admin','2020-08-27 09:22:59',NULL),(114,28,'自由职业','28','pati_job_type',NULL,NULL,'N','0','admin','2020-08-27 09:23:25','',NULL,NULL),(115,29,'其他','29','pati_job_type',NULL,NULL,'N','0','admin','2020-08-27 09:23:42','',NULL,NULL),(116,0,'焦虑症','0','pati_diagnosis_type',NULL,NULL,'N','0','admin','2020-08-31 17:38:43','',NULL,NULL),(117,0,'抑郁症','1','pati_diagnosis_type',NULL,NULL,'N','0','admin','2020-08-31 17:39:00','',NULL,NULL),(118,2,'强迫症','2','pati_diagnosis_type',NULL,NULL,'N','0','admin','2020-08-31 17:39:23','',NULL,NULL),(119,3,'精神分裂症','3','pati_diagnosis_type',NULL,NULL,'N','0','admin','2020-08-31 17:39:41','',NULL,NULL),(120,4,'失眠障碍','4','pati_diagnosis_type',NULL,NULL,'N','0','admin','2020-08-31 17:40:01','',NULL,NULL),(121,5,'双相情感障碍','5','pati_diagnosis_type',NULL,NULL,'N','0','admin','2020-08-31 17:40:26','admin','2021-01-14 11:10:41',NULL),(122,6,'其他','6','pati_diagnosis_type',NULL,NULL,'N','0','admin','2020-08-31 17:40:50','',NULL,NULL),(123,3,'C003','C003','pati_task_workstation',NULL,NULL,'N','0','admin','2021-01-15 15:39:54','admin','2021-01-15 15:39:59',NULL),(124,4,'C004','C004','pati_task_workstation',NULL,NULL,'N','0','admin','2021-01-15 16:05:33','',NULL,NULL),(125,5,'C005','C005','pati_task_workstation',NULL,NULL,'N','0','admin','2021-01-15 16:05:50','',NULL,NULL),(126,7,'C006','C006','pati_task_workstation',NULL,NULL,'N','0','admin','2021-01-15 16:06:04','admin','2021-01-15 16:17:20',NULL),(127,1,'焦虑自评量表（SAS）','1','ipa_scale_type',NULL,NULL,'N','0','',NULL,'',NULL,NULL),(128,2,'抑郁自评量表（SDS）','2','ipa_scale_type',NULL,NULL,'N','0','',NULL,'',NULL,NULL),(129,3,'阿森斯失眠量表（AIS）','3','ipa_scale_type',NULL,NULL,'N','0','',NULL,'',NULL,NULL),(130,1,'自评量表','5','sys_scale_grade',NULL,NULL,'N','0','admin','2021-09-27 16:55:04','admin','2021-09-27 16:55:28',NULL),(131,2,'他评量表','6','sys_scale_grade',NULL,NULL,'N','0','admin','2021-09-27 16:55:24','',NULL,NULL),(132,3,'访谈','7','sys_scale_grade',NULL,NULL,'N','0','admin','2021-09-27 16:55:46','',NULL,NULL),(133,4,'观察者评定','8','sys_scale_grade',NULL,NULL,'N','0','admin','2021-09-29 10:57:29','',NULL,NULL),(134,7,'C007','C007','pati_task_workstation',NULL,NULL,'N','0','admin','2021-10-28 15:03:01','',NULL,NULL);

/*Table structure for table `sys_dict_type` */

DROP TABLE IF EXISTS `sys_dict_type`;

CREATE TABLE `sys_dict_type` (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`),
  UNIQUE KEY `dict_type` (`dict_type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='字典类型表';

/*Data for the table `sys_dict_type` */

insert  into `sys_dict_type`(`dict_id`,`dict_name`,`dict_type`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values (1,'用户性别','sys_user_sex','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','用户性别列表'),(2,'菜单状态','sys_show_hide','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','菜单状态列表'),(3,'系统开关','sys_normal_disable','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','系统开关列表'),(4,'任务状态','sys_job_status','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','任务状态列表'),(5,'任务分组','sys_job_group','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','任务分组列表'),(6,'系统是否','sys_yes_no','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','系统是否列表'),(7,'通知类型','sys_notice_type','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','通知类型列表'),(8,'通知状态','sys_notice_status','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','通知状态列表'),(9,'操作类型','sys_oper_type','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','操作类型列表'),(10,'系统状态','sys_common_status','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','登录状态列表'),(11,'婚姻状况','pati_user_maritalStatus','0','admin','2020-07-09 09:40:44','',NULL,'婚姻状况(0未婚，1已婚)'),(12,'患者任务状态','pati_task_status','0','admin','2020-07-09 09:52:46','admin','2020-07-15 15:35:58','状态(1未开始,2进行中,3已结束)'),(13,'任务类型','pati_task_type','0','admin','2020-07-14 18:06:56','',NULL,'任务类型4种'),(14,'疾病分类编码','dis_class_code','0','admin','2020-07-17 16:42:27','',NULL,NULL),(15,'工作站','pati_task_workstation','0','admin','2020-07-21 16:10:33','',NULL,NULL),(16,'患者职业','pati_job_type','0','admin','2020-08-25 10:21:52','admin','2020-08-25 10:26:59',NULL),(17,'患者文化程度','pati_edu_type','0','admin','2020-08-25 10:28:00','',NULL,NULL),(18,'患者来源','pati_source','0','admin','2020-08-25 10:38:21','',NULL,NULL),(19,'诊断类型','pati_diagnosis_type','0','admin','2020-08-31 17:38:14','',NULL,NULL),(20,'ipa量表类型','ipa_scale_type','0','admin','2021-07-06 17:00:00','',NULL,'ipa三张量表'),(21,'量表类型','sys_scale_grade','0','admin','2021-09-27 16:54:06','',NULL,'量表类型列表');

/*Table structure for table `sys_hometown` */

DROP TABLE IF EXISTS `sys_hometown`;

CREATE TABLE `sys_hometown` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `hometown` varchar(50) DEFAULT NULL COMMENT '籍贯(省)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COMMENT='籍贯表';

/*Data for the table `sys_hometown` */

insert  into `sys_hometown`(`id`,`hometown`) values (1,'山西省'),(2,'北京市'),(3,'上海市'),(4,'天津市'),(5,'重庆市'),(6,'黑龙江省'),(7,'吉林省'),(8,'辽宁省'),(9,'内蒙古自治区'),(10,'河北省'),(11,'新疆维吾尔自治区'),(12,'甘肃省'),(13,'青海省'),(14,'陕西省'),(15,'河南省'),(16,'山东省'),(17,'安徽省'),(18,'湖北省'),(19,'湖南省'),(20,'江苏省'),(21,'四川省'),(22,'贵州省'),(23,'云南省'),(24,'西藏自治区'),(25,'浙江省'),(26,'江西省'),(27,'广东省'),(28,'广西壮族自治区'),(29,'福建省'),(30,'台湾省'),(31,'海南省'),(32,'宁夏回族自治区'),(33,'香港特别行政区'),(34,'澳门特别行政区');

/*Table structure for table `sys_job` */

DROP TABLE IF EXISTS `sys_job`;

CREATE TABLE `sys_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
  `invoke_target` varchar(500) NOT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`,`job_name`,`job_group`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='定时任务调度表';

/*Data for the table `sys_job` */

insert  into `sys_job`(`job_id`,`job_name`,`job_group`,`invoke_target`,`cron_expression`,`misfire_policy`,`concurrent`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values (1,'系统默认（无参）','DEFAULT','ryTask.ryNoParams','0/10 * * * * ?','3','1','1','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(2,'系统默认（有参）','DEFAULT','ryTask.ryParams(\'ry\')','0/15 * * * * ?','3','1','1','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(3,'系统默认（多参）','DEFAULT','ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)','0/20 * * * * ?','3','1','1','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','');

/*Table structure for table `sys_job_log` */

DROP TABLE IF EXISTS `sys_job_log`;

CREATE TABLE `sys_job_log` (
  `job_log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL COMMENT '任务组名',
  `invoke_target` varchar(500) NOT NULL COMMENT '调用目标字符串',
  `job_message` varchar(500) DEFAULT NULL COMMENT '日志信息',
  `status` char(1) DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(2000) DEFAULT '' COMMENT '异常信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务调度日志表';

/*Data for the table `sys_job_log` */

/*Table structure for table `sys_logininfor` */

DROP TABLE IF EXISTS `sys_logininfor`;

CREATE TABLE `sys_logininfor` (
  `info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `user_name` varchar(50) DEFAULT '' COMMENT '用户账号',
  `ipaddr` varchar(50) DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统',
  `status` char(1) DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) DEFAULT '' COMMENT '提示消息',
  `login_time` datetime DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=804 DEFAULT CHARSET=utf8 COMMENT='系统访问记录';

/*Data for the table `sys_logininfor` */

insert  into `sys_logininfor`(`info_id`,`user_name`,`ipaddr`,`login_location`,`browser`,`os`,`status`,`msg`,`login_time`) values (1,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-06 15:28:02'),(2,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-06 18:44:03'),(3,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-07 15:38:13'),(4,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-07 16:46:21'),(5,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-07 17:04:50'),(6,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-08 11:16:51'),(7,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-08 11:57:14'),(8,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-08 13:54:18'),(9,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-08 15:14:49'),(10,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-08 16:33:22'),(11,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-09 09:24:44'),(12,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码已失效','2020-07-09 10:42:51'),(13,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码已失效','2020-07-09 10:43:00'),(14,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-09 10:43:10'),(15,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-09 12:34:43'),(16,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-09 14:46:32'),(17,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-09 15:53:55'),(18,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-10 09:36:10'),(19,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-10 10:10:55'),(20,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-10 13:38:39'),(21,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-10 16:04:25'),(22,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-10 16:46:42'),(23,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-13 09:08:07'),(24,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-13 09:40:31'),(25,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-13 11:02:08'),(26,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-13 11:09:48'),(27,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-13 12:53:12'),(28,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-13 13:29:54'),(29,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-14 09:03:16'),(30,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-14 10:12:41'),(31,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-14 10:43:26'),(32,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-14 14:01:15'),(33,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-14 14:32:12'),(34,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-14 16:26:41'),(35,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-15 10:36:56'),(36,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-15 13:45:09'),(37,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-15 17:11:47'),(38,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-16 09:06:31'),(39,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-16 12:53:47'),(40,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-16 13:33:43'),(41,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-17 09:02:23'),(42,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-17 11:47:41'),(43,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-17 14:15:08'),(44,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码已失效','2020-07-17 14:37:44'),(45,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-17 14:37:53'),(46,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-07-17 17:43:29'),(47,'aaa','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-17 17:43:43'),(48,'aaa','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-07-17 17:44:01'),(49,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-17 17:44:11'),(50,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-07-17 17:46:13'),(51,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-17 17:46:26'),(52,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-07-17 17:46:35'),(53,'aaa','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-17 17:46:46'),(54,'aaa','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-07-17 17:47:02'),(55,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-17 17:47:09'),(56,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-07-17 17:49:07'),(57,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-17 17:49:14'),(58,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-07-17 17:49:24'),(59,'aaa','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-17 17:49:36'),(60,'aaa','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-07-17 17:51:51'),(61,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-17 17:52:01'),(62,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-07-17 18:02:25'),(63,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-17 18:02:32'),(64,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-07-17 18:02:43'),(65,'aaa','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-17 18:02:51'),(66,'aaa','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-07-17 18:11:31'),(67,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-17 18:11:40'),(68,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-07-17 18:15:28'),(69,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-17 18:15:34'),(70,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-07-17 18:15:40'),(71,'aaa','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-17 18:15:50'),(72,'aaa','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-07-17 18:20:03'),(73,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-17 18:20:09'),(74,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码已失效','2020-07-20 09:17:33'),(75,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-20 09:17:41'),(76,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-07-20 09:25:13'),(77,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-20 09:25:21'),(78,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-07-20 09:25:25'),(79,'aaa','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-20 09:25:35'),(80,'aaa','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-07-20 09:29:38'),(81,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-20 09:29:50'),(82,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-07-20 09:45:34'),(83,'aaa','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-20 09:45:48'),(84,'aaa','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-07-20 09:54:19'),(85,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-20 09:54:28'),(86,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-07-20 10:01:12'),(87,'aaa','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-20 10:02:12'),(88,'aaa','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-07-20 10:40:19'),(89,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-20 10:40:26'),(90,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-20 11:35:14'),(91,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-20 15:05:05'),(92,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-20 17:33:34'),(93,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-21 11:21:24'),(94,'admin','192.168.1.106','内网IP','Chrome','Windows 10','0','登录成功','2020-07-21 13:37:52'),(95,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码错误','2020-07-21 13:43:53'),(96,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-21 13:44:06'),(97,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码已失效','2020-07-21 15:44:55'),(98,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-21 15:45:02'),(99,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-21 17:45:36'),(100,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-21 17:58:40'),(101,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-22 08:50:59'),(102,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-22 14:44:21'),(103,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-22 15:23:39'),(104,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-22 16:15:11'),(105,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-22 17:30:11'),(106,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-23 09:05:34'),(107,'admin','192.168.1.103','内网IP','Chrome Mobile','Android 6.x','0','登录成功','2020-07-23 09:53:33'),(108,'admin','192.168.1.105','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-23 10:14:34'),(109,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码已失效','2020-07-23 11:14:18'),(110,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-23 11:14:26'),(111,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码已失效','2020-07-23 12:51:15'),(112,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码已失效','2020-07-23 12:51:27'),(113,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-23 12:51:33'),(114,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-23 13:35:10'),(115,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-23 14:19:52'),(116,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-23 14:26:48'),(117,'admin','192.168.226.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-23 14:33:51'),(118,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-23 14:40:53'),(119,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-07-23 14:59:34'),(120,'aaa','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-23 14:59:48'),(121,'aaa','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-07-23 15:46:09'),(122,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-23 15:46:16'),(123,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-23 17:36:57'),(124,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-24 08:54:46'),(125,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-24 15:44:52'),(126,'admin','192.168.1.106','内网IP','Chrome','Windows 10','0','登录成功','2020-07-24 16:39:31'),(127,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-24 16:49:53'),(128,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-07-24 17:46:34'),(129,'admin','192.168.1.110','内网IP','Firefox 7','Windows 10','0','登录成功','2020-07-24 17:51:30'),(130,'admin','192.168.1.110','内网IP','Firefox 7','Windows 10','1','验证码已失效','2020-07-24 17:52:25'),(131,'admin','192.168.1.110','内网IP','Firefox 7','Windows 10','1','验证码已失效','2020-07-24 17:52:26'),(132,'admin','192.168.1.110','内网IP','Firefox 7','Windows 10','1','验证码已失效','2020-07-24 17:52:28'),(133,'admin','192.168.1.110','内网IP','Firefox 7','Windows 10','0','登录成功','2020-07-24 17:52:33'),(134,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-24 18:03:35'),(135,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-27 08:58:17'),(136,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-27 09:38:16'),(137,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-27 11:54:02'),(138,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-07-31 10:29:05'),(139,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-08-03 16:07:03'),(140,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码已失效','2020-08-04 08:53:11'),(141,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-08-04 08:53:17'),(142,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-08-04 14:58:05'),(143,'admin','192.168.1.105','内网IP','Chrome 8','Windows 10','0','登录成功','2020-08-05 09:54:18'),(144,'admin','192.168.1.105','内网IP','Chrome 8','Windows 10','0','退出成功','2020-08-05 09:57:52'),(145,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码错误','2020-08-05 10:01:19'),(146,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-08-05 10:01:28'),(147,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-08-05 11:24:07'),(148,'admin','127.0.0.1','内网IP','Chrome Mobile','Android 6.x','0','登录成功','2020-08-05 11:27:17'),(149,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-08-19 14:59:57'),(150,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-08-19 15:00:48'),(151,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-08-19 15:00:55'),(152,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-08-19 15:53:43'),(153,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-08-24 14:33:00'),(154,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-08-24 15:12:21'),(155,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-08-25 08:45:13'),(156,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码已失效','2020-08-25 09:41:14'),(157,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-08-25 09:41:20'),(158,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-08-25 13:51:37'),(159,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-08-25 14:21:53'),(160,'zhangsan','192.168.1.107','内网IP','Chrome','Windows 10','0','登录成功','2020-08-25 15:36:54'),(161,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-08-26 15:27:21'),(162,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-08-26 16:31:39'),(163,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-08-26 17:50:40'),(164,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-08-26 18:04:53'),(165,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-08-26 18:05:45'),(166,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-08-27 08:57:44'),(167,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-08-27 10:16:57'),(168,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-08-27 12:48:13'),(169,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-08-27 13:39:46'),(170,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-08-27 14:13:18'),(171,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-08-27 14:13:45'),(172,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-08-27 14:14:15'),(173,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-08-27 18:09:05'),(174,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-08-27 18:38:13'),(175,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-08-27 18:38:50'),(176,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-08-28 08:49:11'),(177,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-08-28 08:53:55'),(178,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-08-28 08:54:31'),(179,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-08-28 09:31:10'),(180,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-08-31 08:59:22'),(181,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码已失效','2020-08-31 09:58:07'),(182,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-08-31 09:58:18'),(183,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-08-31 11:01:23'),(184,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-08-31 11:14:46'),(185,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-08-31 11:14:55'),(186,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-08-31 11:15:21'),(187,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-08-31 12:49:59'),(188,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-08-31 15:58:35'),(189,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码已失效','2020-09-01 10:57:14'),(190,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-01 10:57:20'),(191,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-01 13:56:26'),(192,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-02 08:52:06'),(193,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-02 10:25:55'),(194,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-02 13:49:14'),(195,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-02 14:32:55'),(196,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-02 17:20:17'),(197,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-03 09:29:11'),(198,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-03 11:20:52'),(199,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-03 11:55:43'),(200,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-03 14:22:40'),(201,'admin','192.168.1.116','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-03 16:14:33'),(202,'admin','192.168.1.116','内网IP','Chrome 8','Windows 10','1','验证码已失效','2020-09-04 08:47:00'),(203,'admin','192.168.1.116','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-04 08:47:08'),(204,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-04 09:42:32'),(205,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-04 15:36:35'),(206,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-04 16:28:00'),(207,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-04 17:33:34'),(208,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-07 09:11:38'),(209,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-08 09:42:58'),(210,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-08 13:52:40'),(211,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-08 16:22:17'),(212,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-09 09:35:15'),(213,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-09 10:43:07'),(214,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-09 11:15:31'),(215,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-09 12:47:54'),(216,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-09 13:53:43'),(217,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-10 08:46:08'),(218,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-10 09:25:46'),(219,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-10 13:33:48'),(220,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-10 16:50:33'),(221,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-11 09:20:21'),(222,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-11 10:38:33'),(223,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-09-11 10:49:50'),(224,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-11 10:49:59'),(225,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-11 10:51:19'),(226,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-11 12:03:26'),(227,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-11 12:47:41'),(228,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-11 13:48:46'),(229,'zhangsan','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-11 14:16:29'),(230,'zhangsan','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-11 15:42:21'),(231,'zhangsan','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-09-11 15:42:35'),(232,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-11 15:42:53'),(233,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-11 15:58:40'),(234,'zhangsan','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-11 16:24:09'),(235,'zhangsan','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-09-11 16:48:59'),(236,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-11 16:49:12'),(237,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-11 17:24:34'),(238,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-11 17:25:24'),(239,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-14 09:02:31'),(240,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-09-14 09:33:28'),(241,'zhangsan','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-14 09:33:41'),(242,'zhangsan','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-09-14 09:34:39'),(243,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-14 09:35:00'),(244,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-14 10:22:43'),(245,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-09-14 10:24:56'),(246,'zhangsan','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-14 10:25:07'),(247,'zhangsan','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-09-14 10:35:03'),(248,'zhangsan','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-14 10:35:12'),(249,'zhangsan','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-09-14 10:35:25'),(250,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-14 10:35:37'),(251,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-14 11:22:14'),(252,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-09-14 11:36:13'),(253,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-14 11:36:23'),(254,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-09-14 11:36:30'),(255,'zhangsan','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-14 11:36:40'),(256,'zhangsan','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-09-14 11:37:18'),(257,'zhangsan','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-14 11:37:25'),(258,'zhangsan','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-14 13:50:33'),(259,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-14 15:10:59'),(260,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-09-14 15:13:20'),(261,'zhangsan','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-14 15:13:33'),(262,'zhangsan','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-09-14 15:13:47'),(263,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-14 15:13:53'),(264,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-09-14 15:18:51'),(265,'zhangsan','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-14 15:19:00'),(266,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-15 08:52:15'),(267,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-15 13:38:24'),(268,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-15 14:33:53'),(269,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-09-15 14:42:16'),(270,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-15 14:42:27'),(271,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-09-15 14:42:32'),(272,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-15 14:42:56'),(273,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-15 14:44:45'),(274,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-09-15 14:45:33'),(275,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-15 14:45:43'),(276,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-15 16:42:55'),(277,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-16 09:04:35'),(278,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-16 16:03:04'),(279,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-17 08:46:02'),(280,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-09-17 09:36:50'),(281,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码已失效','2020-09-17 09:37:00'),(282,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-17 09:37:12'),(283,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码已失效','2020-09-18 08:51:16'),(284,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-18 08:51:25'),(285,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-21 08:49:24'),(286,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-21 12:01:39'),(287,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-09-21 13:03:19'),(288,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-21 13:03:26'),(289,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-21 16:59:15'),(290,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-21 17:24:50'),(291,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-22 08:55:03'),(292,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-22 08:59:14'),(293,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-22 15:18:52'),(294,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-22 17:00:29'),(295,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-22 18:08:57'),(296,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-23 08:48:36'),(297,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码错误','2020-09-23 10:13:28'),(298,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-23 10:13:35'),(299,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-23 14:35:33'),(300,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码错误','2020-09-23 15:58:56'),(301,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-23 15:59:04'),(302,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码已失效','2020-09-24 09:15:19'),(303,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码已失效','2020-09-24 09:15:28'),(304,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-24 09:15:36'),(305,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-24 09:21:22'),(306,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-24 14:42:24'),(307,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码错误','2020-09-24 15:27:47'),(308,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-24 15:27:57'),(309,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-25 11:41:06'),(310,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-25 16:11:07'),(311,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-25 16:14:01'),(312,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-27 09:01:49'),(313,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-27 09:26:34'),(314,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-27 10:35:24'),(315,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-27 11:13:19'),(316,'122233344566','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码错误','2020-09-28 10:10:30'),(317,'122233344566','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码已失效','2020-09-28 10:10:35'),(318,'122233344566','127.0.0.1','内网IP','Chrome 8','Windows 10','1','用户不存在/密码错误','2020-09-28 10:10:44'),(319,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-28 10:12:44'),(320,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-28 10:54:53'),(321,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码已失效','2020-09-28 15:55:39'),(322,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-28 15:55:46'),(323,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-29 08:54:05'),(324,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-29 09:23:54'),(325,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-30 08:53:21'),(326,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-30 09:41:40'),(327,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-09-30 10:56:09'),(328,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-30 10:56:15'),(329,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-09-30 13:32:25'),(330,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-10-09 10:55:44'),(331,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-10-09 11:42:50'),(332,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-10-09 11:43:02'),(333,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-10-09 14:15:27'),(334,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-10-10 09:08:28'),(335,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-10-10 15:49:29'),(336,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','用户不存在/密码错误','2020-10-10 15:56:48'),(337,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码已失效','2020-10-10 15:56:54'),(338,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','用户不存在/密码错误','2020-10-10 15:57:06'),(339,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-10-10 17:00:53'),(340,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-10-12 10:44:59'),(341,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-10-12 11:26:48'),(342,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-10-14 17:18:32'),(343,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-10-14 17:54:29'),(344,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-10-21 11:20:10'),(345,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-10-28 11:19:40'),(346,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','用户不存在/密码错误','2020-10-28 16:51:07'),(347,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-10-28 16:51:18'),(348,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-10-29 13:49:56'),(349,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-11-02 09:25:41'),(350,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-11-02 15:18:59'),(351,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-11-03 09:11:55'),(352,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','用户不存在/密码错误','2020-11-03 09:30:03'),(353,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-11-03 09:30:15'),(354,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码已失效','2020-11-06 11:21:29'),(355,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-11-06 11:21:36'),(356,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-11-06 11:49:19'),(357,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-11-06 11:50:21'),(358,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-11-10 10:17:38'),(359,'admin','127.0.0.1','内网IP','Firefox 8','Windows 10','0','登录成功','2020-11-11 09:27:19'),(360,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码错误','2020-11-11 13:52:15'),(361,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-11-11 13:52:21'),(362,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-11-17 14:54:16'),(363,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-11-18 09:11:06'),(364,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-11-18 13:53:19'),(365,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-11-19 15:10:28'),(366,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-11-23 10:53:59'),(367,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-11-24 09:28:52'),(368,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-11-24 09:30:45'),(369,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-11-24 10:06:42'),(370,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-11-24 13:47:42'),(371,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-11-24 14:18:30'),(372,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-11-24 14:27:45'),(373,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-11-24 14:30:36'),(374,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-11-24 16:08:09'),(375,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码错误','2020-11-24 19:37:31'),(376,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-11-24 19:37:40'),(377,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-11-24 19:40:19'),(378,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-11-24 19:49:40'),(379,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码已失效','2020-11-25 09:04:46'),(380,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-11-25 09:04:54'),(381,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码错误','2020-11-25 16:02:12'),(382,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-11-25 16:02:20'),(383,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-11-26 09:02:48'),(384,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-11-26 14:40:20'),(385,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-11-30 09:08:54'),(386,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-01 11:09:02'),(387,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-12-01 15:49:25'),(388,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-01 16:12:03'),(389,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-02 09:06:04'),(390,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-12-02 11:28:15'),(391,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-02 11:28:55'),(392,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-03 11:42:38'),(393,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-04 09:03:45'),(394,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-04 09:58:52'),(395,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-12-04 09:59:04'),(396,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','用户不存在/密码错误','2020-12-04 10:00:07'),(397,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-04 10:00:33'),(398,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-04 11:41:45'),(399,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-04 13:38:28'),(400,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-04 13:46:28'),(401,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-04 13:49:11'),(402,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-04 13:50:45'),(403,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-04 14:03:47'),(404,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-04 14:06:06'),(405,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-04 14:10:36'),(406,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-04 14:16:09'),(407,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','用户不存在/密码错误','2020-12-04 14:31:21'),(408,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-04 14:31:31'),(409,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-04 14:54:54'),(410,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-04 14:57:37'),(411,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-04 15:11:52'),(412,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-04 16:05:39'),(413,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-04 16:09:59'),(414,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-12-04 16:11:27'),(415,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-04 16:11:41'),(416,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-04 16:13:20'),(417,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-04 16:17:28'),(418,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-04 16:19:30'),(419,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-07 09:44:33'),(420,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-12-07 10:16:33'),(421,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-07 10:18:02'),(422,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-12-07 10:21:08'),(423,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-07 10:21:42'),(424,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-12-07 10:35:37'),(425,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-07 10:36:51'),(426,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-07 10:46:14'),(427,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-07 16:31:09'),(428,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-08 08:59:25'),(429,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-12-08 14:16:39'),(430,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-08 14:17:03'),(431,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-09 09:02:25'),(432,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-09 14:29:32'),(433,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-10 08:56:37'),(434,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-10 14:11:15'),(435,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-11 08:53:13'),(436,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-11 15:44:29'),(437,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-14 10:26:20'),(438,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-14 11:52:59'),(439,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-14 18:10:13'),(440,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-15 08:58:12'),(441,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-15 14:12:17'),(442,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-12-15 14:52:39'),(443,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','用户不存在/密码错误','2020-12-16 09:04:16'),(444,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-16 09:04:25'),(445,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-17 15:04:02'),(446,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-17 15:38:55'),(447,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-18 09:07:23'),(448,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-18 15:03:53'),(449,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-21 09:04:40'),(450,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码已失效','2020-12-21 14:41:32'),(451,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-21 14:41:39'),(452,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-12-21 14:45:32'),(453,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-21 14:45:47'),(454,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-21 14:50:24'),(455,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-21 14:58:44'),(456,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-21 15:04:55'),(457,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-21 15:07:57'),(458,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-12-21 16:05:29'),(459,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-21 16:41:09'),(460,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-22 09:13:16'),(461,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-22 09:19:32'),(462,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-22 09:21:53'),(463,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-22 09:22:58'),(464,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-22 09:32:59'),(465,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-22 10:12:30'),(466,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-22 16:19:27'),(467,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-22 17:38:43'),(468,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-23 09:21:18'),(469,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-12-23 11:51:34'),(470,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-23 13:29:44'),(471,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-24 09:25:44'),(472,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-12-24 10:09:07'),(473,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码已失效','2020-12-24 10:09:28'),(474,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-24 10:09:35'),(475,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-30 09:36:18'),(476,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码已失效','2020-12-30 14:00:34'),(477,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-30 14:00:42'),(478,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2020-12-31 15:08:09'),(479,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2020-12-31 17:18:00'),(480,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-04 10:04:39'),(481,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-04 10:34:42'),(482,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码已失效','2021-01-04 16:18:46'),(483,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码已失效','2021-01-04 16:18:47'),(484,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-04 16:18:54'),(485,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-05 12:05:19'),(486,'zhangsan','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-05 12:47:39'),(487,'张三','127.0.0.1','内网IP','Chrome 8','Windows 10','1','用户不存在/密码错误','2021-01-05 15:25:04'),(488,'zhangsan','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-05 15:25:18'),(489,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-01-05 15:34:55'),(490,'zhangsan','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-05 15:35:03'),(491,'zhangsan','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码已失效','2021-01-05 17:06:19'),(492,'zhangsan','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-05 17:06:24'),(493,'zhangsan','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-05 17:14:26'),(494,'zhangsan','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-05 17:17:07'),(495,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-11 09:56:55'),(496,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-11 10:03:00'),(497,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-11 10:42:39'),(498,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-11 10:43:53'),(499,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-11 10:55:11'),(500,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-11 10:56:42'),(501,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-11 11:27:04'),(502,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-01-11 11:29:04'),(503,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-11 11:30:23'),(504,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-11 12:45:56'),(505,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-11 12:51:29'),(506,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-01-11 12:51:50'),(507,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-11 13:14:53'),(508,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-11 13:25:07'),(509,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-11 13:37:38'),(510,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-11 13:47:20'),(511,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-11 13:48:50'),(512,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-11 14:30:22'),(513,'admin','127.0.0.1','内网IP','Chrome 8','Windows 7','0','登录成功','2021-01-11 16:31:21'),(514,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-12 09:33:19'),(515,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-01-12 10:40:21'),(516,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-12 10:40:32'),(517,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-01-12 10:40:38'),(518,'zhangsan','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-12 10:40:46'),(519,'zhangsan','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-01-12 10:46:43'),(520,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-12 10:46:53'),(521,'admin','127.0.0.1','内网IP','Chrome 8','Windows 7','0','登录成功','2021-01-12 10:50:57'),(522,'admin','127.0.0.1','内网IP','Chrome 8','Windows 7','0','登录成功','2021-01-12 11:21:22'),(523,'admin','127.0.0.1','内网IP','Chrome 8','Windows 7','0','登录成功','2021-01-12 11:49:09'),(524,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-12 14:10:05'),(525,'admin','127.0.0.1','内网IP','Chrome 8','Windows 7','0','登录成功','2021-01-12 16:52:25'),(526,'admin','127.0.0.1','内网IP','Chrome 8','Windows 7','0','登录成功','2021-01-13 09:05:23'),(527,'admin','127.0.0.1','内网IP','Chrome 8','Windows 7','1','验证码错误','2021-01-13 10:33:35'),(528,'admin','127.0.0.1','内网IP','Chrome 8','Windows 7','1','验证码已失效','2021-01-13 10:33:42'),(529,'admin','127.0.0.1','内网IP','Chrome 8','Windows 7','0','登录成功','2021-01-13 10:33:50'),(530,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-14 11:09:41'),(531,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-01-14 15:59:59'),(532,'zhangsan','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-14 16:00:06'),(533,'zhangsan','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-01-14 16:00:29'),(534,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-14 16:01:19'),(535,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-01-14 16:26:23'),(536,'zhangsan','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-14 16:26:31'),(537,'zhangsan','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-01-14 16:28:53'),(538,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-14 16:30:29'),(539,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-01-14 16:31:56'),(540,'zhangsan','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-14 16:32:03'),(541,'zhangsan','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-01-14 16:32:34'),(542,'zhangsan','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-14 16:32:43'),(543,'zhangsan','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-01-14 16:35:50'),(544,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-14 16:36:00'),(545,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-15 09:42:39'),(546,'admin','127.0.0.1','内网IP','Chrome 8','Windows 7','0','登录成功','2021-01-15 10:56:16'),(547,'admin','127.0.0.1','内网IP','Chrome 8','Windows 7','0','登录成功','2021-01-15 11:12:49'),(548,'admin','127.0.0.1','内网IP','Chrome 8','Windows 7','0','登录成功','2021-01-15 12:15:59'),(549,'admin','127.0.0.1','内网IP','Chrome 8','Windows 7','0','退出成功','2021-01-15 14:08:30'),(550,'admin','127.0.0.1','内网IP','Chrome 8','Windows 7','0','登录成功','2021-01-15 14:17:50'),(551,'admin','127.0.0.1','内网IP','Chrome 8','Windows 7','1','验证码已失效','2021-01-15 14:31:26'),(552,'admin','127.0.0.1','内网IP','Chrome 8','Windows 7','0','登录成功','2021-01-15 14:31:33'),(553,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-15 14:45:20'),(554,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-18 10:50:59'),(555,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码已失效','2021-01-21 10:06:07'),(556,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-21 10:06:13'),(557,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-25 10:47:36'),(558,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-25 10:50:50'),(559,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-01-25 12:22:48'),(560,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-25 12:25:14'),(561,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-25 17:04:51'),(562,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-25 19:07:32'),(563,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-25 19:46:13'),(564,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-26 09:26:49'),(565,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-26 14:29:25'),(566,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-01-26 17:52:54'),(567,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-02 09:31:17'),(568,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-02 12:19:51'),(569,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-02 18:17:09'),(570,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-03 09:42:29'),(571,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-03 14:59:16'),(572,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-04 11:10:14'),(573,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-05 08:50:23'),(574,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-05 09:42:59'),(575,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-05 10:27:32'),(576,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码错误','2021-02-05 10:27:32'),(577,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-05 10:36:53'),(578,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-05 10:37:17'),(579,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-05 13:21:45'),(580,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-05 15:56:24'),(581,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码错误','2021-02-05 18:22:20'),(582,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码错误','2021-02-05 18:22:25'),(583,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-05 18:25:06'),(584,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-02-05 18:25:19'),(585,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-05 18:25:23'),(586,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-02-05 18:25:47'),(587,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-05 18:26:10'),(588,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-02-05 18:28:08'),(589,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-05 18:28:41'),(590,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-02-05 18:30:16'),(591,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-05 18:30:21'),(592,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-07 08:58:01'),(593,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-07 14:19:55'),(594,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-07 17:07:40'),(595,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-02-07 17:28:32'),(596,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-07 17:28:34'),(597,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-02-07 17:57:21'),(598,'SXZYLM123','127.0.0.1','内网IP','Chrome 8','Windows 10','1','用户不存在/密码错误','2021-02-07 17:57:26'),(599,'SXZYLM123','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-07 17:57:30'),(600,'SXZYLM123','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-02-07 17:57:46'),(601,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-07 17:57:48'),(602,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-02-07 17:58:46'),(603,'SXZYLM123','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-07 17:58:57'),(604,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-08 08:57:41'),(605,'SXZYLM123','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-08 09:01:47'),(606,'SXZYLM123','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-02-08 09:04:20'),(607,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-08 09:04:24'),(608,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-02-08 09:05:20'),(609,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-08 09:05:22'),(610,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-02-08 09:05:27'),(611,'SXZYLM123','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-08 09:05:30'),(612,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-08 09:07:13'),(613,'SXZYLM123','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-02-08 09:07:40'),(614,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-08 09:07:42'),(615,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-02-08 09:08:30'),(616,'SXZYLM123','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-08 09:08:33'),(617,'SXZYLM123','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-02-08 09:12:09'),(618,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-08 09:12:12'),(619,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-02-08 09:12:39'),(620,'SXZYLM123','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-08 09:12:43'),(621,'SXZYLM123','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-02-08 09:18:05'),(622,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-08 09:18:10'),(623,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-02-08 09:22:29'),(624,'SXZYLM123','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-08 09:23:03'),(625,'SXZYLM123','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-02-08 09:24:24'),(626,'SXZYLM123','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-08 09:24:53'),(627,'SXZYLM123','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-02-08 09:25:54'),(628,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-08 09:26:13'),(629,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-02-08 09:41:31'),(630,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-08 09:41:36'),(631,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-02-08 09:41:42'),(632,'SXZYLM123','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-08 09:41:45'),(633,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-08 09:48:09'),(634,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-02-08 09:48:14'),(635,'SXZYLM123','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-08 09:48:54'),(636,'SXZYLM123','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-02-08 09:49:00'),(637,'SXZYLM123','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-08 09:49:02'),(638,'SXZYLM123','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-02-08 09:49:05'),(639,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-08 09:49:17'),(640,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-02-08 09:49:37'),(641,'SXZYLM123','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-08 09:49:41'),(642,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-18 19:26:34'),(643,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-02-18 19:27:46'),(644,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-18 19:27:52'),(645,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-02-18 19:28:06'),(646,'SXZYLM123','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-18 19:28:14'),(647,'SXZYLM123','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-02-18 19:28:25'),(648,'SXZYLM123','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-18 19:28:30'),(649,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-19 10:48:50'),(650,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-02-19 10:51:13'),(651,'SXZYLM123','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-19 10:51:22'),(652,'SXZYLM123','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-02-19 10:51:38'),(653,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-19 10:51:41'),(654,'SXZYLM123','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-19 11:16:27'),(655,'SXZYLM123','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-19 11:20:36'),(656,'SXZYLM123','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-02-19 11:21:08'),(657,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-19 11:21:11'),(658,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-02-19 11:40:42'),(659,'SXZYLM123','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-19 11:40:47'),(660,'SXZYLM123','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-02-19 11:41:06'),(661,'SXZYLM123','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-19 13:59:08'),(662,'SXZYLM123','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-02-19 15:51:51'),(663,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-19 16:29:07'),(664,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-20 09:10:39'),(665,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-20 10:26:46'),(666,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-20 11:15:38'),(667,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-22 09:31:01'),(668,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-22 10:02:15'),(669,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-22 14:50:54'),(670,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-23 11:14:02'),(671,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-02-23 11:31:04'),(672,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-03-02 09:08:18'),(673,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-07-02 10:15:14'),(674,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-07-02 10:15:27'),(675,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-07-02 16:59:58'),(676,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-07-02 17:10:42'),(677,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-07-02 17:23:39'),(678,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-07-05 09:07:56'),(679,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','退出成功','2021-07-05 09:35:01'),(680,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-07-05 09:35:02'),(681,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-07-05 10:30:50'),(682,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','退出成功','2021-07-05 15:10:44'),(683,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-07-05 15:10:46'),(684,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','退出成功','2021-07-05 15:11:29'),(685,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-07-05 15:11:31'),(686,'admin','192.168.1.116','内网IP','Chrome 8','Windows 10','0','登录成功','2021-07-05 15:12:56'),(687,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-07-05 15:32:48'),(688,'admin','192.168.1.116','内网IP','Chrome 8','Windows 10','0','登录成功','2021-07-05 16:11:24'),(689,'admin','192.168.1.116','内网IP','Chrome 8','Windows 10','0','登录成功','2021-07-05 17:43:27'),(690,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-07-06 09:04:26'),(691,'admin','192.168.1.116','内网IP','Chrome 8','Windows 10','0','登录成功','2021-07-06 09:07:42'),(692,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-07-06 10:19:36'),(693,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-07-06 11:33:35'),(694,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-07-06 13:26:58'),(695,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-07-06 14:25:04'),(696,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','1','用户不存在/密码错误','2021-07-08 09:20:00'),(697,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-07-08 09:20:09'),(698,'admin','192.168.1.116','内网IP','Chrome 8','Windows 10','0','登录成功','2021-07-08 09:36:55'),(699,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','1','用户不存在/密码错误','2021-07-08 16:03:36'),(700,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','1','用户不存在/密码错误','2021-07-08 16:03:45'),(701,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-07-08 16:03:55'),(702,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-07-09 10:21:48'),(703,'admin','192.168.1.116','内网IP','Chrome 8','Windows 10','0','登录成功','2021-07-09 16:29:35'),(704,'admin','192.168.1.116','内网IP','Chrome 8','Windows 10','0','登录成功','2021-07-12 11:07:18'),(705,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-07-12 15:25:56'),(706,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-07-12 16:14:40'),(707,'admin','192.168.1.116','内网IP','Chrome 8','Windows 10','0','登录成功','2021-07-12 16:24:45'),(708,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-07-13 09:41:18'),(709,'admin','192.168.1.116','内网IP','Chrome 8','Windows 10','0','登录成功','2021-07-13 09:59:06'),(710,'admin','192.168.1.116','内网IP','Chrome 8','Windows 10','0','登录成功','2021-07-13 14:41:15'),(711,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-07-13 14:45:25'),(712,'admin','192.168.1.116','内网IP','Chrome 8','Windows 10','0','退出成功','2021-07-13 15:02:02'),(713,'admin','192.168.1.116','内网IP','Chrome 8','Windows 10','0','登录成功','2021-07-13 15:02:18'),(714,'admin','192.168.1.116','内网IP','Chrome 8','Windows 10','0','登录成功','2021-07-13 15:03:21'),(715,'admin','192.168.1.116','内网IP','Chrome 8','Windows 10','0','退出成功','2021-07-13 15:03:46'),(716,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-07-13 15:50:52'),(717,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-07-13 16:50:27'),(718,'admin','192.168.1.116','内网IP','Chrome 8','Windows 10','0','登录成功','2021-07-13 16:58:52'),(719,'admin','192.168.1.116','内网IP','Chrome 8','Windows 10','0','登录成功','2021-07-14 09:12:14'),(720,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-07-14 09:23:34'),(721,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-07-14 09:54:18'),(722,'admin','192.168.1.116','内网IP','Chrome 8','Windows 10','0','登录成功','2021-07-14 09:59:41'),(723,'admin','192.168.1.116','内网IP','Chrome 8','Windows 10','0','登录成功','2021-07-14 15:11:51'),(724,'admin','192.168.1.116','内网IP','Chrome 8','Windows 10','0','登录成功','2021-07-14 15:56:57'),(725,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','退出成功','2021-07-14 17:30:25'),(726,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-07-14 17:32:20'),(727,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-07-15 11:31:36'),(728,'admin','192.168.1.116','内网IP','Chrome 8','Windows 10','0','登录成功','2021-07-15 15:38:25'),(729,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-07-16 09:05:46'),(730,'admin','192.168.1.116','内网IP','Chrome 8','Windows 10','0','登录成功','2021-07-16 09:09:59'),(731,'admin','192.168.1.116','内网IP','Chrome 8','Windows 10','0','登录成功','2021-07-16 10:02:26'),(732,'admin','192.168.1.116','内网IP','Chrome 8','Windows 10','0','登录成功','2021-07-16 10:39:12'),(733,'admin','192.168.1.116','内网IP','Chrome 8','Windows 10','0','登录成功','2021-07-16 11:04:30'),(734,'admin','192.168.1.116','内网IP','Chrome 8','Windows 10','0','登录成功','2021-07-19 09:38:27'),(735,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-07-19 10:03:53'),(736,'admin','192.168.1.116','内网IP','Chrome 8','Windows 10','0','登录成功','2021-07-19 10:29:22'),(737,'admin','192.168.1.116','内网IP','Chrome 8','Windows 10','0','登录成功','2021-07-19 17:51:08'),(738,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-07-20 08:59:05'),(739,'admin','192.168.1.116','内网IP','Chrome 8','Windows 10','0','登录成功','2021-07-20 09:02:55'),(740,'admin','192.168.1.116','内网IP','Chrome 8','Windows 10','0','登录成功','2021-07-20 14:16:20'),(741,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-07-21 10:22:31'),(742,'admin','192.168.1.116','内网IP','Chrome 8','Windows 10','0','登录成功','2021-07-21 13:01:42'),(743,'admin','192.168.1.116','内网IP','Chrome 8','Windows 10','0','登录成功','2021-07-22 09:47:38'),(744,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-07-22 13:31:31'),(745,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-07-22 16:54:05'),(746,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-07-22 18:04:42'),(747,'admin','192.168.1.116','内网IP','Chrome 8','Windows 10','0','登录成功','2021-07-23 09:03:46'),(748,'admin','192.168.1.116','内网IP','Chrome 8','Windows 10','0','登录成功','2021-07-23 14:46:14'),(749,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-09-06 09:34:07'),(750,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-09-27 15:36:44'),(751,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-09-28 09:07:13'),(752,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-09-29 09:10:26'),(753,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-09-30 09:07:58'),(754,'admin','192.168.1.108','内网IP','Chrome 9','Windows 10','0','登录成功','2021-09-30 17:28:45'),(755,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-10-11 09:57:10'),(756,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-10-14 14:26:12'),(757,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-10-14 15:12:05'),(758,'admin','192.168.1.109','内网IP','Chrome 9','Windows 10','0','登录成功','2021-10-14 15:33:35'),(759,'admin','192.168.1.102','内网IP','Chrome Mobile','Android 1.x','0','登录成功','2021-10-14 15:41:52'),(760,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-10-22 14:14:17'),(761,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-10-22 14:28:56'),(762,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-10-25 09:12:08'),(763,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-10-25 11:34:52'),(764,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-10-25 11:40:31'),(765,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-10-25 11:44:34'),(766,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-10-25 12:00:55'),(767,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-10-25 12:01:17'),(768,'admin','127.0.0.1','内网IP','Unknown','Unknown','0','登录成功','2021-10-25 14:20:57'),(769,'admin','127.0.0.1','内网IP','Unknown','Unknown','0','登录成功','2021-10-25 14:22:49'),(770,'admin','127.0.0.1','内网IP','Unknown','Unknown','0','登录成功','2021-10-25 14:29:35'),(771,'admin','127.0.0.1','内网IP','Unknown','Unknown','0','登录成功','2021-10-25 14:40:56'),(772,'admin','127.0.0.1','内网IP','Unknown','Unknown','0','登录成功','2021-10-25 15:08:11'),(773,'admin','127.0.0.1','内网IP','Unknown','Unknown','0','登录成功','2021-10-25 15:09:14'),(774,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-10-25 15:10:56'),(775,'admin','127.0.0.1','内网IP','Unknown','Unknown','1','用户不存在/密码错误','2021-10-25 15:17:54'),(776,'admin','127.0.0.1','内网IP','Unknown','Unknown','1','用户不存在/密码错误','2021-10-25 15:18:46'),(777,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-10-25 15:19:47'),(778,'admin','127.0.0.1','内网IP','Unknown','Unknown','1','用户不存在/密码错误','2021-10-25 15:20:11'),(779,'admin','127.0.0.1','内网IP','Unknown','Unknown','0','登录成功','2021-10-25 15:28:54'),(780,'admin','127.0.0.1','内网IP','Unknown','Unknown','0','登录成功','2021-10-25 15:41:29'),(781,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-10-25 15:45:00'),(782,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-10-25 15:52:40'),(783,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-10-25 15:53:04'),(784,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-10-25 15:53:25'),(785,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-10-25 15:55:30'),(786,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-10-25 16:00:26'),(787,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-10-26 09:12:44'),(788,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-10-27 09:04:22'),(789,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-10-27 15:03:41'),(790,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-10-28 08:59:06'),(791,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-10-28 10:28:48'),(792,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-11-01 09:25:57'),(793,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-11-02 09:07:51'),(794,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-11-02 14:31:47'),(795,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-11-03 09:22:58'),(796,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-11-05 09:25:21'),(797,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-11-16 09:17:29'),(798,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2021-11-26 15:50:31'),(799,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2021-11-26 15:50:57'),(800,'admin','192.168.1.113','内网IP','Chrome 9','Windows 10','0','登录成功','2021-11-26 15:53:03'),(801,'admin','192.168.1.117','内网IP','Chrome 8','Windows 10','0','登录成功','2021-11-26 16:21:19'),(802,'admin','192.168.1.119','内网IP','Chrome','Windows 10','0','登录成功','2021-11-26 16:40:40'),(803,'admin','192.168.1.119','内网IP','Chrome','Windows 10','0','退出成功','2021-11-26 18:02:34');

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `path` varchar(200) DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
  `is_frame` int(1) DEFAULT '1' COMMENT '是否为外链（0是 1否）',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1415 DEFAULT CHARSET=utf8 COMMENT='菜单权限表';

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`menu_id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`is_frame`,`menu_type`,`visible`,`perms`,`icon`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values (1,'系统管理',0,8,'system',NULL,1,'M','0','','system','admin','2018-03-16 11:33:00','admin','2020-01-02 10:18:11','系统管理目录'),(2,'系统监控',0,9,'monitor',NULL,1,'M','1','','monitor','admin','2018-03-16 11:33:00','admin','2021-02-05 18:28:03','系统监控目录'),(3,'系统工具',0,10,'tool',NULL,1,'M','1','','tool','admin','2018-03-16 11:33:00','admin','2021-11-26 17:13:35','系统工具目录'),(4,'无分类权限接口',0,11,'noClass',NULL,1,'M','1','','chart','admin','2020-07-20 09:40:34','admin','2021-07-02 10:31:42',''),(100,'用户管理',1,1,'user','system/user/index',1,'C','0','system:user:list','user','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','用户管理菜单'),(101,'角色管理',1,2,'role','system/role/index',1,'C','0','system:role:list','peoples','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','角色管理菜单'),(102,'菜单管理',1,3,'menu','system/menu/index',1,'C','0','system:menu:list','tree-table','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','菜单管理菜单'),(103,'部门管理',1,4,'dept','system/dept/index',1,'C','0','system:dept:list','tree','admin','2018-03-16 11:33:00','admin','2020-07-06 16:11:21','部门管理菜单'),(104,'岗位管理',1,5,'post','system/post/index',1,'C','0','system:post:list','post','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','岗位管理菜单'),(105,'字典管理',1,6,'dict','system/dict/index',1,'C','0','system:dict:list','dict','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','字典管理菜单'),(106,'参数设置',1,7,'config','system/config/index',1,'C','0','system:config:list','edit','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','参数设置菜单'),(107,'通知公告',1,8,'notice','system/notice/index',1,'C','1','system:notice:list','message','admin','2018-03-16 11:33:00','admin','2020-07-06 17:23:56','通知公告菜单'),(108,'日志管理',1,9,'log','system/log/index',1,'M','0','','log','admin','2018-03-16 11:33:00','admin','2021-07-02 10:02:32','日志管理菜单'),(109,'在线用户',2,1,'online','monitor/online/index',1,'C','0','monitor:online:list','online','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','在线用户菜单'),(111,'数据监控',2,3,'druid','monitor/druid/index',1,'C','0','monitor:druid:list','druid','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','数据监控菜单'),(112,'服务监控',2,4,'server','monitor/server/index',1,'C','0','monitor:server:list','server','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','服务监控菜单'),(114,'代码生成',3,2,'gen','tool/gen/index',1,'C','0','tool:gen:list','code','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','代码生成菜单'),(115,'系统接口',3,3,'swagger','tool/swagger/index',1,'C','0','tool:swagger:list','swagger','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','系统接口菜单'),(500,'操作日志',108,1,'operlog','monitor/operlog/index',1,'C','0','monitor:operlog:list','form','admin','2018-03-16 11:33:00','admin','2021-07-02 10:02:41','操作日志菜单'),(501,'登录日志',108,2,'logininfor','monitor/logininfor/index',1,'C','0','monitor:logininfor:list','logininfor','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','登录日志菜单'),(1001,'用户查询',100,1,'','',1,'F','0','system:user:query','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1002,'用户新增',100,2,'','',1,'F','0','system:user:add','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1003,'用户修改',100,3,'','',1,'F','0','system:user:edit','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1004,'用户删除',100,4,'','',1,'F','0','system:user:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1005,'用户导出',100,5,'','',1,'F','0','system:user:export','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1006,'用户导入',100,6,'','',1,'F','0','system:user:import','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1007,'重置密码',100,7,'','',1,'F','0','system:user:resetPwd','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1008,'角色查询',101,1,'','',1,'F','0','system:role:query','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1009,'角色新增',101,2,'','',1,'F','0','system:role:add','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1010,'角色修改',101,3,'','',1,'F','0','system:role:edit','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1011,'角色删除',101,4,'','',1,'F','0','system:role:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1012,'角色导出',101,5,'','',1,'F','0','system:role:export','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1013,'菜单查询',102,1,'','',1,'F','0','system:menu:query','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1014,'菜单新增',102,2,'','',1,'F','0','system:menu:add','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1015,'菜单修改',102,3,'','',1,'F','0','system:menu:edit','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1016,'菜单删除',102,4,'','',1,'F','0','system:menu:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1017,'部门查询',103,1,'','',1,'F','0','system:dept:query','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1018,'部门新增',103,2,'','',1,'F','0','system:dept:add','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1019,'部门修改',103,3,'','',1,'F','0','system:dept:edit','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1020,'部门删除',103,4,'','',1,'F','0','system:dept:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1021,'岗位查询',104,1,'','',1,'F','0','system:post:query','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1022,'岗位新增',104,2,'','',1,'F','0','system:post:add','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1023,'岗位修改',104,3,'','',1,'F','0','system:post:edit','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1024,'岗位删除',104,4,'','',1,'F','0','system:post:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1025,'岗位导出',104,5,'','',1,'F','0','system:post:export','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1026,'字典查询',105,1,'#','',1,'F','0','system:dict:query','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1027,'字典新增',105,2,'#','',1,'F','0','system:dict:add','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1028,'字典修改',105,3,'#','',1,'F','0','system:dict:edit','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1029,'字典删除',105,4,'#','',1,'F','0','system:dict:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1030,'字典导出',105,5,'#','',1,'F','0','system:dict:export','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1031,'参数查询',106,1,'#','',1,'F','0','system:config:query','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1032,'参数新增',106,2,'#','',1,'F','0','system:config:add','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1033,'参数修改',106,3,'#','',1,'F','0','system:config:edit','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1034,'参数删除',106,4,'#','',1,'F','0','system:config:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1035,'参数导出',106,5,'#','',1,'F','0','system:config:export','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1036,'公告查询',107,1,'#','',1,'F','0','system:notice:query','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1037,'公告新增',107,2,'#','',1,'F','0','system:notice:add','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1038,'公告修改',107,3,'#','',1,'F','0','system:notice:edit','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1039,'公告删除',107,4,'#','',1,'F','0','system:notice:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1040,'操作查询',500,1,'#','',1,'F','0','monitor:operlog:query','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1041,'操作删除',500,2,'#','',1,'F','0','monitor:operlog:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1042,'日志导出',500,4,'#','',1,'F','0','monitor:operlog:export','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1043,'登录查询',501,1,'#','',1,'F','0','monitor:logininfor:query','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1044,'登录删除',501,2,'#','',1,'F','0','monitor:logininfor:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1045,'日志导出',501,3,'#','',1,'F','0','monitor:logininfor:export','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1046,'在线查询',109,1,'#','',1,'F','0','monitor:online:query','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1047,'批量强退',109,2,'#','',1,'F','0','monitor:online:batchLogout','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1048,'单条强退',109,3,'#','',1,'F','0','monitor:online:forceLogout','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1055,'生成查询',114,1,'#','',1,'F','0','tool:gen:query','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1056,'生成修改',114,2,'#','',1,'F','0','tool:gen:edit','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1057,'生成删除',114,3,'#','',1,'F','0','tool:gen:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1058,'导入代码',114,2,'#','',1,'F','0','tool:gen:import','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1059,'预览代码',114,4,'#','',1,'F','0','tool:gen:preview','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1060,'生成代码',114,5,'#','',1,'F','0','tool:gen:code','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1314,'测评类型',4,1,'type','patient/type/index',1,'C','0','patient:type:list','#','admin','2018-03-01 00:00:00','ry','2018-03-01 00:00:00','测评类型菜单'),(1315,'测评类型查询',1314,1,'#','',1,'F','0','patient:type:query','#','admin','2018-03-01 00:00:00','ry','2018-03-01 00:00:00',''),(1316,'测评类型新增',1314,2,'#','',1,'F','0','patient:type:add','#','admin','2018-03-01 00:00:00','ry','2018-03-01 00:00:00',''),(1317,'测评类型修改',1314,3,'#','',1,'F','0','patient:type:edit','#','admin','2018-03-01 00:00:00','ry','2018-03-01 00:00:00',''),(1318,'测评类型删除',1314,4,'#','',1,'F','0','patient:type:remove','#','admin','2018-03-01 00:00:00','ry','2018-03-01 00:00:00',''),(1319,'测评类型导出',1314,5,'#','',1,'F','0','patient:type:export','#','admin','2018-03-01 00:00:00','ry','2018-03-01 00:00:00',''),(1341,'数据中心',0,6,'statistics',NULL,1,'M','0','','star','admin','2020-08-31 14:27:34','admin','2020-08-31 14:41:37',''),(1342,'数据概况',1341,1,'statistics','patient/statistics/index',1,'C','0','patient:statistics:list','table','admin','2020-08-31 14:30:23','admin','2021-11-26 17:54:19',''),(1343,'统计1',4,1,'statisticsTable1','patient/statisticsTable1/index',1,'C','0','patient:statisticsTable1:list','#','admin','2018-03-01 00:00:00','ry','2018-03-01 00:00:00','统计1菜单'),(1344,'统计1查询',1343,1,'#','',1,'F','0','patient:statisticsTable1:query','#','admin','2018-03-01 00:00:00','ry','2018-03-01 00:00:00',''),(1345,'统计1新增',1343,2,'#','',1,'F','0','patient:statisticsTable1:add','#','admin','2018-03-01 00:00:00','ry','2018-03-01 00:00:00',''),(1346,'统计1修改',1343,3,'#','',1,'F','0','patient:statisticsTable1:edit','#','admin','2018-03-01 00:00:00','ry','2018-03-01 00:00:00',''),(1347,'统计1删除',1343,4,'#','',1,'F','0','patient:statisticsTable1:remove','#','admin','2018-03-01 00:00:00','ry','2018-03-01 00:00:00',''),(1348,'统计1导出',1343,5,'#','',1,'F','0','patient:statisticsTable1:export','#','admin','2018-03-01 00:00:00','ry','2018-03-01 00:00:00',''),(1349,'统计2',4,1,'statisticsTable2','patient/statisticsTable2/index',1,'C','0','patient:statisticsTable2:list','#','admin','2018-03-01 00:00:00','ry','2018-03-01 00:00:00','统计2菜单'),(1350,'统计2查询',1349,1,'#','',1,'F','0','patient:statisticsTable2:query','#','admin','2018-03-01 00:00:00','ry','2018-03-01 00:00:00',''),(1351,'统计2新增',1349,2,'#','',1,'F','0','patient:statisticsTable2:add','#','admin','2018-03-01 00:00:00','ry','2018-03-01 00:00:00',''),(1352,'统计2修改',1349,3,'#','',1,'F','0','patient:statisticsTable2:edit','#','admin','2018-03-01 00:00:00','ry','2018-03-01 00:00:00',''),(1353,'统计2删除',1349,4,'#','',1,'F','0','patient:statisticsTable2:remove','#','admin','2018-03-01 00:00:00','ry','2018-03-01 00:00:00',''),(1354,'统计2导出',1349,5,'#','',1,'F','0','patient:statisticsTable2:export','#','admin','2018-03-01 00:00:00','ry','2018-03-01 00:00:00',''),(1355,'统计3',4,1,'statisticsTable3','patient/statisticsTable3/index',1,'C','0','patient:statisticsTable3:list','#','admin','2018-03-01 00:00:00','ry','2018-03-01 00:00:00','统计3菜单'),(1356,'统计3查询',1355,1,'#','',1,'F','0','patient:statisticsTable3:query','#','admin','2018-03-01 00:00:00','ry','2018-03-01 00:00:00',''),(1357,'统计3新增',1355,2,'#','',1,'F','0','patient:statisticsTable3:add','#','admin','2018-03-01 00:00:00','ry','2018-03-01 00:00:00',''),(1358,'统计3修改',1355,3,'#','',1,'F','0','patient:statisticsTable3:edit','#','admin','2018-03-01 00:00:00','ry','2018-03-01 00:00:00',''),(1359,'统计3删除',1355,4,'#','',1,'F','0','patient:statisticsTable3:remove','#','admin','2018-03-01 00:00:00','ry','2018-03-01 00:00:00',''),(1360,'统计3导出',1355,5,'#','',1,'F','0','patient:statisticsTable3:export','#','admin','2018-03-01 00:00:00','ry','2018-03-01 00:00:00',''),(1401,'量表任务',1412,1,'task','patient/evaluation/',1,'C','0','','build','admin','2018-03-01 00:00:00','admin','2021-10-22 14:34:47','量表目录菜单'),(1412,'量表系统',0,1,'scale',NULL,1,'M','0','','build','admin','2021-09-27 16:22:52','admin','2021-10-11 10:41:17','');

/*Table structure for table `sys_nation` */

DROP TABLE IF EXISTS `sys_nation`;

CREATE TABLE `sys_nation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nation` varchar(100) DEFAULT NULL COMMENT '民族',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COMMENT='民族表';

/*Data for the table `sys_nation` */

insert  into `sys_nation`(`id`,`nation`) values (1,'汉族'),(2,'满族'),(3,'蒙古族'),(4,'回族'),(5,'藏族'),(6,'维吾尔族'),(7,'苗族'),(8,'彝族'),(9,'壮族'),(10,'布依族'),(11,'侗族'),(12,'瑶族'),(13,'白族'),(14,'土家族'),(15,'哈尼族'),(16,'哈萨克族'),(17,'傣族'),(18,'黎族'),(19,'傈僳族'),(20,'佤族'),(21,'畲族'),(22,'高山族'),(23,'拉祜族'),(24,'水族'),(25,'东乡族'),(26,'纳西族'),(27,'景颇族'),(28,'柯尔克孜族'),(29,'土族'),(30,'达斡尔族'),(31,'仫佬族'),(32,'羌族'),(33,'布朗族'),(34,'撒拉族'),(35,'毛南族'),(36,'仡佬族'),(37,'锡伯族'),(38,'阿昌族'),(39,'普米族'),(40,'朝鲜族'),(41,'塔吉克族'),(42,'怒族'),(43,'乌孜别克族'),(44,'俄罗斯族'),(45,'鄂温克族'),(46,'德昂族'),(47,'保安族'),(48,'裕固族'),(49,'京族'),(50,'塔塔尔族'),(51,'独龙族'),(52,'鄂伦春族'),(53,'赫哲族'),(54,'门巴族'),(55,'珞巴族'),(56,'基诺族');

/*Table structure for table `sys_notice` */

DROP TABLE IF EXISTS `sys_notice`;

CREATE TABLE `sys_notice` (
  `notice_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) NOT NULL COMMENT '公告标题',
  `notice_type` char(1) NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` varchar(2000) DEFAULT NULL COMMENT '公告内容',
  `status` char(1) DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通知公告表';

/*Data for the table `sys_notice` */

/*Table structure for table `sys_oper_log` */

DROP TABLE IF EXISTS `sys_oper_log`;

CREATE TABLE `sys_oper_log` (
  `oper_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) DEFAULT '' COMMENT '请求方式',
  `operator_type` int(1) DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(50) DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) DEFAULT '' COMMENT '返回参数',
  `status` int(1) DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`oper_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2214 DEFAULT CHARSET=utf8 COMMENT='操作日志记录';

/*Data for the table `sys_oper_log` */


/*Table structure for table `sys_post` */

DROP TABLE IF EXISTS `sys_post`;

CREATE TABLE `sys_post` (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) NOT NULL COMMENT '岗位名称',
  `post_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='岗位信息表';

/*Data for the table `sys_post` */

insert  into `sys_post`(`post_id`,`post_code`,`post_name`,`post_sort`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values (1,'1','医生',1,'0','admin','2020-07-06 16:56:00','admin','2020-07-06 17:19:35',NULL),(2,'2','护士',1,'0','admin','2020-07-06 17:16:58','',NULL,NULL);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `status` char(1) NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='角色信息表';

/*Data for the table `sys_role` */

insert  into `sys_role`(`role_id`,`role_name`,`role_key`,`role_sort`,`data_scope`,`status`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values (5,'普通管理员','xd-kg',3,'2','0','0','admin','2020-01-17 11:27:32','admin','2021-10-27 11:07:01','普通管理员');

/*Table structure for table `sys_role_dept` */

DROP TABLE IF EXISTS `sys_role_dept`;

CREATE TABLE `sys_role_dept` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`,`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色和部门关联表';

/*Data for the table `sys_role_dept` */

insert  into `sys_role_dept`(`role_id`,`dept_id`) values (5,100),(5,171);

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色和菜单关联表';

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`role_id`,`menu_id`) values (5,1341),(5,1342),(5,1401),(5,1412);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(30) NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) DEFAULT '' COMMENT '手机号码',
  `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) DEFAULT '' COMMENT '密码',
  `status` char(1) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(50) DEFAULT '' COMMENT '最后登陆IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

/*Data for the table `sys_user` */

insert  into `sys_user`(`user_id`,`dept_id`,`user_name`,`nick_name`,`user_type`,`email`,`phonenumber`,`sex`,`avatar`,`password`,`status`,`del_flag`,`login_ip`,`login_date`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values (1,100,'admin','超级管理员','00','123@qq.com','15888888888','0','','$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2','0','0','127.0.0.1','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','ry','2020-07-06 18:56:07','超级管理员'),(2,171,'SXZYLM123','普通管理员','00','','13888888888','0','','$2a$10$h05rjhsdYLRBaqyhnHUhLu1/67if2gL6RmAKFV4s4t.oPO78C6pdi','0','0','',NULL,'admin','2020-07-06 13:55:24','admin','2021-02-08 09:08:24','普通管理员');

/*Table structure for table `sys_user_post` */

DROP TABLE IF EXISTS `sys_user_post`;

CREATE TABLE `sys_user_post` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`,`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与岗位关联表';

/*Data for the table `sys_user_post` */

insert  into `sys_user_post`(`user_id`,`post_id`) values (2,1),(17,1);

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户和角色关联表';

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`user_id`,`role_id`) values (2,5),(17,5);

/*Table structure for table `sysb_log` */

DROP TABLE IF EXISTS `sysb_log`;

CREATE TABLE `sysb_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志id',
  `patient_id` bigint(20) DEFAULT NULL COMMENT '患者id',
  `patient_name` varchar(100) DEFAULT NULL COMMENT '患者名称',
  `sex` char(1) DEFAULT NULL COMMENT '性别',
  `age` int(20) DEFAULT NULL COMMENT '年龄',
  `education` varchar(20) DEFAULT NULL COMMENT '文化程度',
  `job` varchar(20) DEFAULT NULL COMMENT '职业',
  `diagnosis` varchar(50) DEFAULT NULL COMMENT '诊断',
  `test_day` datetime DEFAULT NULL COMMENT '测试日期',
  `kzwt` double(20,0) DEFAULT NULL COMMENT '控制问题',
  `sywt` double(20,0) DEFAULT NULL COMMENT '失言问题',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='失言识别日志表';

/*Data for the table `sysb_log` */

/*Table structure for table `zz_dld` */

DROP TABLE IF EXISTS `zz_dld`;

CREATE TABLE `zz_dld` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(255) NOT NULL COMMENT '题号',
  `point` varchar(255) NOT NULL COMMENT '得分',
  `test_date` date NOT NULL COMMENT '测试日期',
  `task_id` bigint(255) NOT NULL COMMENT '任务id',
  `patient_id` bigint(255) NOT NULL COMMENT '患者id',
  `scale_id` bigint(100) DEFAULT NULL COMMENT '量表id',
  `workstation` varchar(10) CHARACTER SET utf8 DEFAULT NULL COMMENT '工作站',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=369 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC COMMENT='多伦多述情障碍量表';

/*Data for the table `zz_dld` */

/*Table structure for table `zz_eis` */

DROP TABLE IF EXISTS `zz_eis`;

CREATE TABLE `zz_eis` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(255) NOT NULL COMMENT '题号',
  `point` varchar(255) NOT NULL COMMENT '得分',
  `test_date` date NOT NULL COMMENT '测试日期',
  `task_id` bigint(255) NOT NULL COMMENT '任务id',
  `patient_id` bigint(255) NOT NULL COMMENT '患者id',
  `scale_id` bigint(100) DEFAULT NULL COMMENT '量表id',
  `workstation` varchar(10) CHARACTER SET utf8 DEFAULT NULL COMMENT '工作站',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=306 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC COMMENT='情绪智力量表(EIS)';

/*Data for the table `zz_eis` */

/*Table structure for table `zz_fzyq_score` */

DROP TABLE IF EXISTS `zz_fzyq_score`;

CREATE TABLE `zz_fzyq_score` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(255) NOT NULL COMMENT '题号',
  `point` varchar(255) NOT NULL COMMENT '得分',
  `reaction_time` double(20,2) DEFAULT NULL COMMENT '反应时间',
  `test_date` date NOT NULL COMMENT '测试日期',
  `task_id` bigint(255) NOT NULL COMMENT '任务id',
  `patient_id` bigint(255) NOT NULL COMMENT '患者id',
  `scale_id` bigint(100) DEFAULT NULL COMMENT '量表id',
  `workstation` varchar(10) CHARACTER SET utf8 DEFAULT NULL COMMENT '工作站',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=79 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC COMMENT='复杂眼区识别范式';

/*Data for the table `zz_fzyq_score` */

/*Table structure for table `zz_fzyq_type` */

DROP TABLE IF EXISTS `zz_fzyq_type`;

CREATE TABLE `zz_fzyq_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `grade` char(10) DEFAULT NULL COMMENT '等级（测试,基础,进阶）',
  `pic_url` varchar(200) DEFAULT NULL COMMENT '图片路径',
  `pic_urla` varchar(255) DEFAULT NULL COMMENT '图片路径',
  `video_url` varchar(200) DEFAULT NULL COMMENT '视频路径',
  `pic_type` varchar(100) DEFAULT NULL COMMENT '图片类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='复杂眼区类型表';

/*Data for the table `zz_fzyq_type` */

insert  into `zz_fzyq_type`(`id`,`grade`,`pic_url`,`pic_urla`,`video_url`,`pic_type`) values (1,'0','image/fzyq/0不屑的~眼区.png',NULL,NULL,'不屑的'),(2,'0','image/fzyq/0严肃的~眼区.png',NULL,NULL,'严肃的'),(3,'0','image/fzyq/0埋怨的-眼区.png',NULL,NULL,'埋怨的'),(4,'0','image/fzyq/0忧虑的~眼区.png',NULL,NULL,'忧虑的'),(5,'0','image/fzyq/0怀疑的-眼区.png',NULL,NULL,'怀疑的'),(6,'0','image/fzyq/0惊恐的-眼区.png',NULL,NULL,'惊恐的'),(7,'0','image/fzyq/0惊讶的~眼区.png',NULL,NULL,'惊讶的'),(8,'0','image/fzyq/0愤怒的-眼区.png',NULL,NULL,'愤怒的'),(9,'0','image/fzyq/0深情的-眼区.png',NULL,NULL,'深情的'),(10,'0','image/fzyq/0高兴的-眼区.png',NULL,NULL,'高兴的'),(11,'1','image/fzyq/1伤心的-眼区.png','image/fzyq/1伤心的-面孔.png','伤心的.mp4','伤心的'),(12,'1','image/fzyq/1厌烦的-眼区~.png','image/fzyq/1厌烦的-面孔.png','厌烦的.mp4','厌烦的'),(13,'1','image/fzyq/1忧虑的-眼区.png','image/fzyq/1忧虑的-面孔.png','忧虑的.mp4','忧虑的'),(14,'1','image/fzyq/1怀疑的-眼区.png','image/fzyq/1怀疑的-面孔.png','怀疑的a.mp4','怀疑的'),(15,'1','image/fzyq/1惊恐的-眼区.png','image/fzyq/1惊恐的-面孔.png','惊恐的.mp4','惊恐的'),(16,'1','image/fzyq/1愤怒的-眼区.png','image/fzyq/1愤怒的-面孔.png','愤怒的.mp4','愤怒的'),(17,'1','image/fzyq/1担心的-眼区.png','image/fzyq/1担心的-面孔.png','担心的.mp4','担心的'),(18,'1','image/fzyq/1无奈的-眼区.png','image/fzyq/1无奈的-面孔.png','无奈的.mp4','无奈的'),(19,'1','image/fzyq/1焦急的-眼区.png','image/fzyq/1焦急的-面孔.png','焦急的.mp4','焦急的'),(20,'1','image/fzyq/1高兴的-眼区.png','image/fzyq/1高兴的-面孔.png','高兴的.mp4','高兴的'),(21,'2','image/fzyq/2不屑的-眼区.png','image/fzyq/2不屑的-面孔.png','不屑的.mp4','不屑的'),(22,'2','image/fzyq/2为难的-眼区.png','image/fzyq/2为难的-面孔.png','为难的.mp4','为难的'),(23,'2','image/fzyq/2埋怨的-眼区.png','image/fzyq/2埋怨的-面孔.png','埋怨的.mp4','埋怨的'),(24,'2','image/fzyq/2尴尬的-眼区.png','image/fzyq/2尴尬的-面孔.png','尴尬的.mp4','尴尬的'),(25,'2','image/fzyq/2怀疑的-眼区.png','image/fzyq/2怀疑的-面孔.png','怀疑的b.mp4','怀疑的'),(26,'2','image/fzyq/2惭愧的-眼区.png','image/fzyq/2惭愧的-面孔.png','惭愧的.mp4','惭愧的'),(27,'2','image/fzyq/2沉思的-眼区.png','image/fzyq/2沉思的-面孔.png','沉思的.mp4','沉思的'),(28,'2','image/fzyq/2深情的-眼区.png','image/fzyq/2深情的-面孔.png','深情的.mp4','深情的'),(29,'2','image/fzyq/2诧异的-眼区.png','image/fzyq/2诧异的-面孔.png','诧异的.mp4','诧异的'),(30,'2','image/fzyq/2难以置信的-眼区.png','image/fzyq/2难以置信的-面孔.png','难以置信的.mp4','难以置信的');

/*Table structure for table `zz_gj_type` */

DROP TABLE IF EXISTS `zz_gj_type`;

CREATE TABLE `zz_gj_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(100) DEFAULT NULL COMMENT '题号',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `option_a` varchar(100) DEFAULT NULL COMMENT '选项a',
  `option_b` varchar(100) DEFAULT '' COMMENT '选项b',
  `option_c` varchar(100) DEFAULT '' COMMENT '选项c',
  `option_d` varchar(100) DEFAULT NULL COMMENT '选项d',
  `option_correct` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='高级任务类型表';

/*Data for the table `zz_gj_type` */

insert  into `zz_gj_type`(`id`,`title`,`content`,`option_a`,`option_b`,`option_c`,`option_d`,`option_correct`,`parent_id`) values (1,'1','不想说再见','第五集《不想说再见》.mp4','','',NULL,NULL,0),(2,'2','谁动了我的快递','第二集《谁动了我的快递》.mp4','','',NULL,NULL,0),(3,'3','以貌取人','第三集《以貌取人》.mp4','','',NULL,NULL,0),(4,'4','戒烟','第四集《戒烟》.mp4','','',NULL,NULL,0),(5,'1','小伍听到璐璐提出分手时，他有何情绪？','A：高兴','B：惊讶','C：厌恶',NULL,'B：惊讶',1),(6,'2','小伍说“命里有时终须有，命里无时莫强求”，反应了他此时此刻怎样的心情？','A：愤怒','B：沮丧','C：恐惧',NULL,'B：沮丧',1),(7,'3','璐璐临走见小伍时，小伍的表现让璐璐感觉如何？','A：窃喜','B：恐惧','C：愤怒',NULL,'C：愤怒',1),(8,'4','璐璐约小伍见面时，期待小伍有什么反应？','A：询问分手原因，有所挽留',' B：立马转身离开',' C：激动地蹦跳',NULL,'A：询问分手原因，有所挽留',1),(9,'5','璐璐临走时，小伍为什么没有挽留她？','A：不喜欢璐璐','B：不想拖累璐璐','C：这样做让他很开心',NULL,'B：不想拖累璐璐',1),(10,'6','当同事发现小伍的病例单时，他有何感受？','A：欣喜','B：惊恐','C：愤怒',NULL,'B：惊恐',1),(11,'7','璐璐为什么主动提出分手？','A：不想勉强小伍和她在一起','B：小伍生病了','C：璐璐变心了',NULL,'A：不想勉强小伍和她在一起',1),(12,'8','璐璐看到小伍的化验单时，她是什么感受？','A：厌恶','B：恐惧','C：震惊',NULL,'C：震惊',1),(13,'9','璐璐最在乎的是什么？','A：小伍是否健康','B：小伍是否爱她','C：小伍是否有钱',NULL,'B：小伍是否爱她',1),(14,'10','能给小伍带来温暖的是？','A: 太阳','B：手机','C：璐璐',NULL,'C：璐璐',1),(15,'1','当主任听到厚诚已完成工作时，有何感受？','A：愤怒','B：惊讶','C：厌恶',NULL,'B：惊讶',2),(16,'2','当主任问赵琳干什么去了，她真正去哪儿了？','A: 卫生间','B：前台','C：办公室',NULL,'B：前台',2),(17,'3','主任面对大多数员工对工作的态度，他感到？','A：愤怒','B：惊讶','C：恐惧',NULL,'A：愤怒',2),(18,'4','被主任一番训斥后，王晓对厚诚的态度是怎么样的？','A：赞赏','B：不屑','C：真诚',NULL,'B：不屑',2),(19,'5','当同事纷纷去吃饭，只剩厚诚在办公室时，他可能的感受是？','A：孤独的','B：高兴的','C：厌恶的',NULL,'A：孤独的',2),(20,'6','当赵琳发现快递不见了，她此时此刻的心情是？','A：兴奋的','B：恐惧的','C：焦急的',NULL,'C：焦急的',2),(21,'7','快递不见时，为什么大家一致认为厚诚偷了快递？','A：大家听说厚诚有偷窃史','B：有人看到厚诚拿了快递','C：有人听说厚诚拿了快递',NULL,'A：大家听说厚诚有偷窃史',2),(22,'8','面对大家的质问，厚诚感到？','A：恐惧的','B：激动的','C：无辜的',NULL,'C：无辜的',2),(23,'9','谁动了赵琳的快递？','A：王晓','B：主任','C：厚诚',NULL,'B：主任',2),(24,'10','当赵琳找回快递时，她看着厚诚，眼神中流露出了什么情绪？','A：高兴的','B：厌恶的','C：惭愧的',NULL,'C：惭愧的',2),(25,'1','刘宇夫妇去同学家的真正意图？','A：拜访多年未见的同学','B：想请同学多多照顾','C：想看看同学家在哪儿',NULL,'B：想请同学多多照顾',3),(26,'2','刘宇看见收垃圾的大爷，说“干这行有前途吧”，体现了他对大爷的什么态度？','A： 羡慕','B：嘲讽','C：钦佩',NULL,'B：嘲讽',3),(27,'3','当刘宇夫妇听到大爷说住在别墅时，他们的反应是怎样的？','A：怀疑','B：相信','C：激动',NULL,'A：怀疑',3),(28,'4','刘宇问大爷房价的真正目的？','A：想打听房价消息','B：测验大爷是否清楚这里的房价','C：想在这里买房子',NULL,'B：测验大爷是否清楚这里的房价',3),(29,'5','大爷收纸片子的主要原因？','A：打发时间','B：挣烟钱','C：喜欢纸片子',NULL,'A：打发时间',3),(30,'6','刘宇夫妇为什么不相信大爷住别墅？','A：大爷的儿子没出息','B：收破烂的大爷买不起别墅','C：大爷年纪大了，没钱买别墅',NULL,'B：收破烂的大爷买不起别墅',3),(31,'7','当听到王总叫爸爸时，刘宇夫妇表现出何种情绪？','A：愤怒的','B：兴奋的','C：惊讶的',NULL,'C：惊讶的',3),(32,'8','当得知大爷收垃圾的真相时，刘宇夫妇感觉如何？','A：厌恶的','B：悲伤的','C：惭愧的',NULL,'C：惭愧的',3),(33,'9','“帮人上瘾”体现出大爷帮人时的何种情绪？','A：愉悦的','B：痛苦的','C：恐惧的',NULL,'A：愉悦的',3),(34,'10','剧中所说的“原则”是什么意思？','A：不拜访同学','B：不随意收礼','C：不请客吃饭',NULL,'B：不随意收礼',3),(35,'1','贾耀强回家，妻子怎么发现他抽烟了？','A：妻子听他说了抽烟的事儿','B：妻子嗅到了他身上的烟味','C：妻子看到他在抽烟',NULL,'B：妻子嗅到了他身上的烟味',4),(36,'2','当妻子发现贾耀强抽烟，她的情绪是','A：惊讶的','B：恐惧的','C：愤怒的',NULL,'C：愤怒的',4),(37,'3','贾耀强为什么向妻子撒谎说没抽烟？','A：妻子不喜欢烟味','B：妻子不让他抽烟','C：妻子对烟过敏',NULL,'B：妻子不让他抽烟',4),(38,'4','妻子为什么问贾耀强要打火机？','A：自己想抽烟','B：不让贾耀强抽烟','C：打火机放身上不安全',NULL,'B：不让贾耀强抽烟',4),(39,'5','张工递烟时，听到贾耀强说戒烟了，张工对其的态度是？','A：支持','B：调侃','C：赞赏',NULL,'B：调侃',4),(40,'6','贾耀强见张工时，从戒烟转变为抽烟的主要原因是什么？','A：他根本不想戒烟','B：不抽烟很难办成事儿','C：他觉得抽烟很舒服',NULL,'B：不抽烟很难办成事儿',4),(41,'7','为什么家人极力反对贾耀强抽烟？','A：抽烟的样子很难看','B：戒烟很难','C：吸烟有害健康',NULL,'C：吸烟有害健康',4),(42,'8','父亲临终前给贾耀强说了什么？','A：吸烟容易成瘾','B：戒烟很痛苦','C：不戒烟将一事无成',NULL,'C：不戒烟将一事无成',4),(43,'9','贾耀强把烟埋进土坑的可能原因是什么？','A：决定戒烟','B：不喜欢烟','C：想珍藏烟',NULL,'A：决定戒烟',4),(44,'10','干扰贾耀强戒烟的主要因素是什么？','A：妻子的严加管控','B：在外办事需要烟','C：自己不想戒烟',NULL,'B：在外办事需要烟',4);

/*Table structure for table `zz_iri_c` */

DROP TABLE IF EXISTS `zz_iri_c`;

CREATE TABLE `zz_iri_c` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(255) NOT NULL COMMENT '题号',
  `point` varchar(255) NOT NULL COMMENT '得分',
  `test_date` date NOT NULL COMMENT '测试日期',
  `task_id` bigint(255) NOT NULL COMMENT '任务id',
  `patient_id` bigint(255) NOT NULL COMMENT '患者id',
  `scale_id` bigint(100) DEFAULT NULL COMMENT '量表id',
  `workstation` varchar(10) CHARACTER SET utf8 DEFAULT NULL COMMENT '工作站',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=207 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC COMMENT='人际反应指针';

/*Data for the table `zz_iri_c` */

/*Table structure for table `zz_mett_score` */

DROP TABLE IF EXISTS `zz_mett_score`;

CREATE TABLE `zz_mett_score` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(255) NOT NULL COMMENT '题号',
  `point` varchar(255) NOT NULL COMMENT '得分',
  `reaction_time` double(255,2) DEFAULT NULL COMMENT '反应时间',
  `test_date` date NOT NULL COMMENT '测试日期',
  `task_id` bigint(255) NOT NULL COMMENT '任务id',
  `patient_id` bigint(255) NOT NULL COMMENT '患者id',
  `scale_id` bigint(100) DEFAULT NULL COMMENT '量表id',
  `workstation` varchar(10) CHARACTER SET utf8 DEFAULT NULL COMMENT '工作站',
  `ex_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '表情id',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=541 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC COMMENT='METT微表情识别范式';

/*Data for the table `zz_mett_score` */

insert  into `zz_mett_score`(`id`,`title`,`point`,`reaction_time`,`test_date`,`task_id`,`patient_id`,`scale_id`,`workstation`,`ex_id`) values (513,'1','0',1.37,'2021-07-22',7,14,22,'C002','中性'),(514,'2','0',0.55,'2021-07-22',7,14,22,'C002','惊讶'),(515,'3','0',0.48,'2021-07-22',7,14,22,'C002','愤怒'),(516,'4','0',0.47,'2021-07-22',7,14,22,'C002','高兴'),(517,'5','1',0.51,'2021-07-22',7,14,22,'C002','恐惧'),(518,'6','1',0.50,'2021-07-22',7,14,22,'C002','恐惧'),(519,'7','0',0.38,'2021-07-22',7,14,22,'C002','厌恶'),(520,'8','0',0.41,'2021-07-22',7,14,22,'C002','高兴'),(521,'9','0',0.34,'2021-07-22',7,14,22,'C002','愤怒'),(522,'10','0',0.39,'2021-07-22',7,14,22,'C002','悲伤'),(523,'11','0',0.38,'2021-07-22',7,14,22,'C002','中性'),(524,'12','0',0.36,'2021-07-22',7,14,22,'C002','愤怒'),(525,'13','1',0.33,'2021-07-22',7,14,22,'C002','恐惧'),(526,'14','0',0.32,'2021-07-22',7,14,22,'C002','厌恶'),(527,'15','0',0.41,'2021-07-22',7,14,22,'C002','愤怒'),(528,'16','0',0.35,'2021-07-22',7,14,22,'C002','惊讶'),(529,'17','0',0.39,'2021-07-22',7,14,22,'C002','厌恶'),(530,'18','0',0.36,'2021-07-22',7,14,22,'C002','中性'),(531,'19','0',0.35,'2021-07-22',7,14,22,'C002','高兴'),(532,'20','0',0.36,'2021-07-22',7,14,22,'C002','惊讶'),(533,'21','0',0.39,'2021-07-22',7,14,22,'C002','悲伤'),(534,'22','0',0.34,'2021-07-22',7,14,22,'C002','悲伤'),(535,'23','0',0.55,'2021-07-22',7,14,22,'C002','悲伤'),(536,'24','0',0.39,'2021-07-22',7,14,22,'C002','厌恶'),(537,'25','0',0.36,'2021-07-22',7,14,22,'C002','中性'),(538,'26','0',0.37,'2021-07-22',7,14,22,'C002','惊讶'),(539,'27','0',0.30,'2021-07-22',7,14,22,'C002','高兴'),(540,'28','1',0.38,'2021-07-22',7,14,22,'C002','恐惧');

/*Table structure for table `zz_mett_type` */

DROP TABLE IF EXISTS `zz_mett_type`;

CREATE TABLE `zz_mett_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `ex_id` varchar(255) DEFAULT NULL COMMENT '表情类型',
  `ex_part_url` varchar(255) DEFAULT NULL COMMENT '局部表情地址',
  `ex_part_remark` varchar(255) DEFAULT NULL COMMENT '指导语',
  `ex_all_url` varchar(255) DEFAULT NULL COMMENT '全身表情地址',
  `ex_all_remark` varchar(255) DEFAULT NULL COMMENT '整体指导语',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8 COMMENT='METT微表情识别范式类型表';

/*Data for the table `zz_mett_type` */

insert  into `zz_mett_type`(`id`,`ex_id`,`ex_part_url`,`ex_part_remark`,`ex_all_url`,`ex_all_remark`) values (1,'中性','image/emotion/中性 (1).jpg',NULL,'image/posture/zx/1.png','人在中性时，没有任何表情。'),(2,'中性','image/emotion/中性 (2).jpg',NULL,'image/posture/zx/2.png','人在中性时，没有任何表情。'),(3,'中性','image/emotion/中性 (3).jpg',NULL,'image/posture/zx/3.png','人在中性时，没有任何表情。'),(4,'中性','image/emotion/中性 (4).jpg',NULL,'image/posture/zx/4.png','人在中性时，没有任何表情。'),(5,'中性','image/emotion/中性 (5).jpg',NULL,'image/posture/zx/5.png','人在中性时，没有任何表情。'),(6,'中性','image/emotion/中性 (6).jpg',NULL,'image/posture/zx/6.png','人在中性时，没有任何表情。'),(7,'中性','image/emotion/中性 (7).jpg',NULL,'image/posture/zx/7.png','人在中性时，没有任何表情。'),(8,'中性','image/emotion/中性 (8).jpg',NULL,'image/posture/zx/8.png','人在中性时，没有任何表情。'),(9,'中性','image/emotion/中性 (9).jpg',NULL,'image/posture/zx/9.png','人在中性时，没有任何表情。'),(10,'中性','image/emotion/中性 (10).jpg',NULL,'image/posture/zx/10.png','人在中性时，没有任何表情。'),(11,'悲伤','image/emotion/悲伤 (10).jpg','眉头上扬，整体眉毛保持下压，双目无神，反映内心的忧伤，脸颊表情肌僵硬，双侧嘴角微微下拉。','image/posture/bs/10.png','人在悲伤时，往往会弯腰驼背、垂头丧气。收拢的双肩是缺乏自信或者是很沮丧的标志。'),(12,'悲伤','image/emotion/悲伤 (9).jpg','眉头上扬，整体眉毛保持下压，双目无神，反映内心的忧伤，脸颊表情肌僵硬，双侧嘴角微微下拉。','image/posture/bs/9.png','人在悲伤时，往往会弯腰驼背、垂头丧气。收拢的双肩是缺乏自信或者是很沮丧的标志。'),(13,'悲伤','image/emotion/悲伤 (8).jpg','眉头上扬，整体眉毛保持下压，双目无神，反映内心的忧伤，脸颊表情肌僵硬，双侧嘴角微微下拉。','image/posture/bs/8.png','人在悲伤时，往往会弯腰驼背、垂头丧气。收拢的双肩是缺乏自信或者是很沮丧的标志。'),(14,'悲伤','image/emotion/悲伤 (7).jpg','眉头上扬，整体眉毛保持下压，双目无神，反映内心的忧伤，脸颊表情肌僵硬，双侧嘴角微微下拉。','image/posture/bs/7.png','人在悲伤时，往往会弯腰驼背、垂头丧气。收拢的双肩是缺乏自信或者是很沮丧的标志。'),(15,'悲伤','image/emotion/悲伤 (6).jpg','眉头上扬，整体眉毛保持下压，双目无神，反映内心的忧伤，脸颊表情肌僵硬，双侧嘴角微微下拉。','image/posture/bs/6.png','人在悲伤时，往往会弯腰驼背、垂头丧气。收拢的双肩是缺乏自信或者是很沮丧的标志。'),(16,'悲伤','image/emotion/悲伤 (5).jpg','眉头上扬，整体眉毛保持下压，双目无神，反映内心的忧伤，脸颊表情肌僵硬，双侧嘴角微微下拉。','image/posture/bs/5.png','人在悲伤时，往往会弯腰驼背、垂头丧气。收拢的双肩是缺乏自信或者是很沮丧的标志。'),(17,'悲伤','image/emotion/悲伤 (4).jpg','眉头上扬，整体眉毛保持下压，双目无神，反映内心的忧伤，脸颊表情肌僵硬，双侧嘴角微微下拉。','image/posture/bs/4.png','人在悲伤时，往往会弯腰驼背、垂头丧气。收拢的双肩是缺乏自信或者是很沮丧的标志。'),(18,'悲伤','image/emotion/悲伤 (3).jpg','眉头上扬，整体眉毛保持下压，双目无神，反映内心的忧伤，脸颊表情肌僵硬，双侧嘴角微微下拉。','image/posture/bs/3.png','人在悲伤时，往往会弯腰驼背、垂头丧气。收拢的双肩是缺乏自信或者是很沮丧的标志。'),(19,'悲伤','image/emotion/悲伤 (2).jpg','眉头上扬，整体眉毛保持下压，双目无神，反映内心的忧伤，脸颊表情肌僵硬，双侧嘴角微微下拉。','image/posture/bs/2.png','人在悲伤时，往往会弯腰驼背、垂头丧气。收拢的双肩是缺乏自信或者是很沮丧的标志。'),(20,'悲伤','image/emotion/悲伤 (1).jpg','眉头上扬，整体眉毛保持下压，双目无神，反映内心的忧伤，脸颊表情肌僵硬，双侧嘴角微微下拉。','image/posture/bs/1.png','人在悲伤时，往往会弯腰驼背、垂头丧气。收拢的双肩是缺乏自信或者是很沮丧的标志。'),(21,'愤怒','image/emotion/愤怒 (10).jpg','上下眼皮是紧绷的，视线高度集中，眼神瞪视，眉毛皱起呈现倒八字。','image/posture/fn/10.png','人在愤怒时，常常会紧握拳头或直指对方，表现出咬牙切齿的状态。'),(22,'愤怒','image/emotion/愤怒 (9).jpg','上下眼皮是紧绷的，视线高度集中，眼神瞪视，眉毛皱起呈现倒八字。','image/posture/fn/9.png','人在愤怒时，常常会紧握拳头或直指对方，表现出咬牙切齿的状态。'),(23,'愤怒','image/emotion/愤怒 (8).jpg','上下眼皮是紧绷的，视线高度集中，眼神瞪视，眉毛皱起呈现倒八字。','image/posture/fn/8.png','人在愤怒时，常常会紧握拳头或直指对方，表现出咬牙切齿的状态。'),(24,'愤怒','image/emotion/愤怒 (7).jpg','上下眼皮是紧绷的，视线高度集中，眼神瞪视，眉毛皱起呈现倒八字。','image/posture/fn/7.png','人在愤怒时，常常会紧握拳头或直指对方，表现出咬牙切齿的状态。'),(25,'愤怒','image/emotion/愤怒 (6).jpg','上下眼皮是紧绷的，视线高度集中，眼神瞪视，眉毛皱起呈现倒八字。','image/posture/fn/6.png','人在愤怒时，常常会紧握拳头或直指对方，表现出咬牙切齿的状态。'),(26,'愤怒','image/emotion/愤怒 (5).jpg','上下眼皮是紧绷的，视线高度集中，眼神瞪视，眉毛皱起呈现倒八字。','image/posture/fn/5.png','人在愤怒时，常常会紧握拳头或直指对方，表现出咬牙切齿的状态。'),(27,'愤怒','image/emotion/愤怒 (4).jpg','上下眼皮是紧绷的，视线高度集中，眼神瞪视，眉毛皱起呈现倒八字。','image/posture/fn/4.png','人在愤怒时，常常会紧握拳头或直指对方，表现出咬牙切齿的状态。'),(28,'愤怒','image/emotion/愤怒 (3).jpg','上下眼皮是紧绷的，视线高度集中，眼神瞪视，眉毛皱起呈现倒八字。','image/posture/fn/3.png','人在愤怒时，常常会紧握拳头或直指对方，表现出咬牙切齿的状态。'),(29,'愤怒','image/emotion/愤怒 (2).jpg','上下眼皮是紧绷的，视线高度集中，眼神瞪视，眉毛皱起呈现倒八字。','image/posture/fn/2.png','人在愤怒时，常常会紧握拳头或直指对方，表现出咬牙切齿的状态。'),(30,'愤怒','image/emotion/愤怒 (1).jpg','上下眼皮是紧绷的，视线高度集中，眼神瞪视，眉毛皱起呈现倒八字。','image/posture/fn/1.png','人在愤怒时，常常会紧握拳头或直指对方，表现出咬牙切齿的状态。'),(31,'高兴','image/emotion/高兴 (10).jpg','目光反映心里的喜悦，眼部周围表情肌舒适活动，眼睛笑眯眯的程度和嘴角上扬的程度一致。','image/posture/gx/10.png','人在高兴时，身体是舒展的，表现出积极、外放的状态，常有庆祝的姿势。'),(32,'高兴','image/emotion/高兴 (9).jpg','目光反映心里的喜悦，眼部周围表情肌舒适活动，眼睛笑眯眯的程度和嘴角上扬的程度一致。','image/posture/gx/9.png','人在高兴时，身体是舒展的，表现出积极、外放的状态，常有庆祝的姿势。'),(33,'高兴','image/emotion/高兴 (8).jpg','目光反映心里的喜悦，眼部周围表情肌舒适活动，眼睛笑眯眯的程度和嘴角上扬的程度一致。','image/posture/gx/8.png','人在高兴时，身体是舒展的，表现出积极、外放的状态，常有庆祝的姿势。'),(34,'高兴','image/emotion/高兴 (7).jpg','目光反映心里的喜悦，眼部周围表情肌舒适活动，眼睛笑眯眯的程度和嘴角上扬的程度一致。','image/posture/gx/7.png','人在高兴时，身体是舒展的，表现出积极、外放的状态，常有庆祝的姿势。'),(35,'高兴','image/emotion/高兴 (6).jpg','目光反映心里的喜悦，眼部周围表情肌舒适活动，眼睛笑眯眯的程度和嘴角上扬的程度一致。','image/posture/gx/6.png','人在高兴时，身体是舒展的，表现出积极、外放的状态，常有庆祝的姿势。'),(36,'高兴','image/emotion/高兴 (5).jpg','目光反映心里的喜悦，眼部周围表情肌舒适活动，眼睛笑眯眯的程度和嘴角上扬的程度一致。','image/posture/gx/5.png','人在高兴时，身体是舒展的，表现出积极、外放的状态，常有庆祝的姿势。'),(37,'高兴','image/emotion/高兴 (4).jpg','目光反映心里的喜悦，眼部周围表情肌舒适活动，眼睛笑眯眯的程度和嘴角上扬的程度一致。','image/posture/gx/4.png','人在高兴时，身体是舒展的，表现出积极、外放的状态，常有庆祝的姿势。'),(38,'高兴','image/emotion/高兴 (3).jpg','目光反映心里的喜悦，眼部周围表情肌舒适活动，眼睛笑眯眯的程度和嘴角上扬的程度一致。','image/posture/gx/3.png','人在高兴时，身体是舒展的，表现出积极、外放的状态，常有庆祝的姿势。'),(39,'高兴','image/emotion/高兴 (2).jpg','目光反映心里的喜悦，眼部周围表情肌舒适活动，眼睛笑眯眯的程度和嘴角上扬的程度一致。','image/posture/gx/2.png','人在高兴时，身体是舒展的，表现出积极、外放的状态，常有庆祝的姿势。'),(40,'高兴','image/emotion/高兴 (1).jpg','目光反映心里的喜悦，眼部周围表情肌舒适活动，眼睛笑眯眯的程度和嘴角上扬的程度一致。','image/posture/gx/1.png','人在高兴时，身体是舒展的，表现出积极、外放的状态，常有庆祝的姿势。'),(41,'惊讶','image/emotion/惊讶 (10).jpg','额头紧缩，眉毛抬高，眼睛瞪圆，嘴巴张大，眼皮上提。','image/posture/jy/10.png','人在惊讶时，往往会目瞪口呆，大部分表现为一时间的失神。'),(42,'惊讶','image/emotion/惊讶 (9).jpg','额头紧缩，眉毛抬高，眼睛瞪圆，嘴巴张大，眼皮上提。','image/posture/jy/9.png','人在惊讶时，往往会目瞪口呆，大部分表现为一时间的失神。'),(43,'惊讶','image/emotion/惊讶 (8).jpg','额头紧缩，眉毛抬高，眼睛瞪圆，嘴巴张大，眼皮上提。','image/posture/jy/8.png','人在惊讶时，往往会目瞪口呆，大部分表现为一时间的失神。'),(44,'惊讶','image/emotion/惊讶 (7).jpg','额头紧缩，眉毛抬高，眼睛瞪圆，嘴巴张大，眼皮上提。','image/posture/jy/7.png','人在惊讶时，往往会目瞪口呆，大部分表现为一时间的失神。'),(45,'惊讶','image/emotion/惊讶 (6).jpg','额头紧缩，眉毛抬高，眼睛瞪圆，嘴巴张大，眼皮上提。','image/posture/jy/6.png','人在惊讶时，往往会目瞪口呆，大部分表现为一时间的失神。'),(46,'惊讶','image/emotion/惊讶 (5).jpg','额头紧缩，眉毛抬高，眼睛瞪圆，嘴巴张大，眼皮上提。','image/posture/jy/5.png','人在惊讶时，往往会目瞪口呆，大部分表现为一时间的失神。'),(47,'惊讶','image/emotion/惊讶 (4).jpg','额头紧缩，眉毛抬高，眼睛瞪圆，嘴巴张大，眼皮上提。','image/posture/jy/4.png','人在惊讶时，往往会目瞪口呆，大部分表现为一时间的失神。'),(48,'惊讶','image/emotion/惊讶 (3).jpg','额头紧缩，眉毛抬高，眼睛瞪圆，嘴巴张大，眼皮上提。','image/posture/jy/3.png','人在惊讶时，往往会目瞪口呆，大部分表现为一时间的失神。'),(49,'惊讶','image/emotion/惊讶 (2).jpg','额头紧缩，眉毛抬高，眼睛瞪圆，嘴巴张大，眼皮上提。','image/posture/jy/2.png','人在惊讶时，往往会目瞪口呆，大部分表现为一时间的失神。'),(50,'惊讶','image/emotion/惊讶 (1).jpg','额头紧缩，眉毛抬高，眼睛瞪圆，嘴巴张大，眼皮上提。','image/posture/jy/1.png','人在惊讶时，往往会目瞪口呆，大部分表现为一时间的失神。'),(51,'恐惧','image/emotion/恐惧 (10).jpg','眉峰上扬且紧皱，眼睛睁大，瞳孔中散发出内在的惊恐，嘴巴张开。','image/posture/kj/10.png','人在恐惧时，往往会全身肌肉紧张，产生一种自我保护意识，形成自我保护躯体形态。'),(52,'恐惧','image/emotion/恐惧 (9).jpg','眉峰上扬且紧皱，眼睛睁大，瞳孔中散发出内在的惊恐，嘴巴张开。','image/posture/kj/9.png','人在恐惧时，往往会全身肌肉紧张，产生一种自我保护意识，形成自我保护躯体形态。'),(53,'恐惧','image/emotion/恐惧 (8).jpg','眉峰上扬且紧皱，眼睛睁大，瞳孔中散发出内在的惊恐，嘴巴张开。','image/posture/kj/8.png','人在恐惧时，往往会全身肌肉紧张，产生一种自我保护意识，形成自我保护躯体形态。'),(54,'恐惧','image/emotion/恐惧 (7).jpg','眉峰上扬且紧皱，眼睛睁大，瞳孔中散发出内在的惊恐，嘴巴张开。','image/posture/kj/7.png','人在恐惧时，往往会全身肌肉紧张，产生一种自我保护意识，形成自我保护躯体形态。'),(55,'恐惧','image/emotion/恐惧 (6).jpg','眉峰上扬且紧皱，眼睛睁大，瞳孔中散发出内在的惊恐，嘴巴张开。','image/posture/kj/6.png','人在恐惧时，往往会全身肌肉紧张，产生一种自我保护意识，形成自我保护躯体形态。'),(56,'恐惧','image/emotion/恐惧 (5).jpg','眉峰上扬且紧皱，眼睛睁大，瞳孔中散发出内在的惊恐，嘴巴张开。','image/posture/kj/5.png','人在恐惧时，往往会全身肌肉紧张，产生一种自我保护意识，形成自我保护躯体形态。'),(57,'恐惧','image/emotion/恐惧 (4).jpg','眉峰上扬且紧皱，眼睛睁大，瞳孔中散发出内在的惊恐，嘴巴张开。','image/posture/kj/4.png','人在恐惧时，往往会全身肌肉紧张，产生一种自我保护意识，形成自我保护躯体形态。'),(58,'恐惧','image/emotion/恐惧 (3).jpg','眉峰上扬且紧皱，眼睛睁大，瞳孔中散发出内在的惊恐，嘴巴张开。','image/posture/kj/3.png','人在恐惧时，往往会全身肌肉紧张，产生一种自我保护意识，形成自我保护躯体形态。'),(59,'恐惧','image/emotion/恐惧 (2).jpg','眉峰上扬且紧皱，眼睛睁大，瞳孔中散发出内在的惊恐，嘴巴张开。','image/posture/kj/2.png','人在恐惧时，往往会全身肌肉紧张，产生一种自我保护意识，形成自我保护躯体形态。'),(60,'恐惧','image/emotion/恐惧 (1).jpg','眉峰上扬且紧皱，眼睛睁大，瞳孔中散发出内在的惊恐，嘴巴张开。','image/posture/kj/1.png','人在恐惧时，往往会全身肌肉紧张，产生一种自我保护意识，形成自我保护躯体形态。'),(61,'厌恶','image/emotion/厌恶 (1).jpg','目光中反射出对事物的厌烦，鼻子皱起，眉毛皱起，上嘴唇上提。','image/posture/yw/1.png','人在厌恶时，常常身体向侧倾斜，手捂鼻嘴，表现出拒绝的姿态。'),(62,'厌恶','image/emotion/厌恶 (2).jpg','目光中反射出对事物的厌烦，鼻子皱起，眉毛皱起，上嘴唇上提。','image/posture/yw/2.png','人在厌恶时，常常身体向侧倾斜，手捂鼻嘴，表现出拒绝的姿态。'),(63,'厌恶','image/emotion/厌恶 (3).jpg','目光中反射出对事物的厌烦，鼻子皱起，眉毛皱起，上嘴唇上提。','image/posture/yw/3.png','人在厌恶时，常常身体向侧倾斜，手捂鼻嘴，表现出拒绝的姿态。'),(64,'厌恶','image/emotion/厌恶 (4).jpg','目光中反射出对事物的厌烦，鼻子皱起，眉毛皱起，上嘴唇上提。','image/posture/yw/4.png','人在厌恶时，常常身体向侧倾斜，手捂鼻嘴，表现出拒绝的姿态。'),(65,'厌恶','image/emotion/厌恶 (5).jpg','目光中反射出对事物的厌烦，鼻子皱起，眉毛皱起，上嘴唇上提。','image/posture/yw/5.png','人在厌恶时，常常身体向侧倾斜，手捂鼻嘴，表现出拒绝的姿态。'),(66,'厌恶','image/emotion/厌恶 (6).jpg','目光中反射出对事物的厌烦，鼻子皱起，眉毛皱起，上嘴唇上提。','image/posture/yw/6.png','人在厌恶时，常常身体向侧倾斜，手捂鼻嘴，表现出拒绝的姿态。'),(67,'厌恶','image/emotion/厌恶 (7).jpg','目光中反射出对事物的厌烦，鼻子皱起，眉毛皱起，上嘴唇上提。','image/posture/yw/7.png','人在厌恶时，常常身体向侧倾斜，手捂鼻嘴，表现出拒绝的姿态。'),(68,'厌恶','image/emotion/厌恶 (8).jpg','目光中反射出对事物的厌烦，鼻子皱起，眉毛皱起，上嘴唇上提。','image/posture/yw/8.png','人在厌恶时，常常身体向侧倾斜，手捂鼻嘴，表现出拒绝的姿态。'),(69,'厌恶','image/emotion/厌恶 (9).jpg','目光中反射出对事物的厌烦，鼻子皱起，眉毛皱起，上嘴唇上提。','image/posture/yw/9.png','人在厌恶时，常常身体向侧倾斜，手捂鼻嘴，表现出拒绝的姿态。'),(70,'厌恶','image/emotion/厌恶 (10).jpg','目光中反射出对事物的厌烦，鼻子皱起，眉毛皱起，上嘴唇上提。','image/posture/yw/10.png','人在厌恶时，常常身体向侧倾斜，手捂鼻嘴，表现出拒绝的姿态。');

/*Table structure for table `zz_shtl_score` */

DROP TABLE IF EXISTS `zz_shtl_score`;

CREATE TABLE `zz_shtl_score` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(255) NOT NULL COMMENT '题号',
  `point` varchar(255) NOT NULL COMMENT '得分',
  `test_date` date NOT NULL COMMENT '测试日期',
  `task_id` bigint(255) NOT NULL COMMENT '任务id',
  `patient_id` bigint(255) NOT NULL COMMENT '患者id',
  `scale_id` bigint(100) DEFAULT NULL COMMENT '量表id',
  `workstation` varchar(10) CHARACTER SET utf8 DEFAULT NULL COMMENT '工作站',
  `reaction_time` double(20,2) DEFAULT NULL COMMENT '反应时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=53 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC COMMENT='社会情境排列';

/*Data for the table `zz_shtl_score` */

/*Table structure for table `zz_shtl_type` */

DROP TABLE IF EXISTS `zz_shtl_type`;

CREATE TABLE `zz_shtl_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `grade` varchar(10) CHARACTER SET utf8 DEFAULT NULL COMMENT '他评或自评',
  `title` varchar(255) DEFAULT NULL COMMENT '题号',
  `a_urla` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '路径',
  `a_urlb` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '路径',
  `b_urla` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '路径',
  `b_urlb` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '路径',
  `c_urla` varchar(255) CHARACTER SET utf8 DEFAULT '' COMMENT '路径',
  `c_urlb` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '路径',
  `d_urla` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '路径',
  `d_urlb` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '路径',
  `e_urla` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '路径',
  `e_urlb` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '路径',
  `f_urla` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '路径',
  `f_urlb` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '路径',
  `correct` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '正确答案',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC COMMENT='社会情境排列';

/*Data for the table `zz_shtl_type` */

insert  into `zz_shtl_type`(`id`,`grade`,`title`,`a_urla`,`a_urlb`,`b_urla`,`b_urlb`,`c_urla`,`c_urlb`,`d_urla`,`d_urlb`,`e_urla`,`e_urlb`,`f_urla`,`f_urlb`,`correct`) values (1,'0','1','image/shtl/cs/first/1.png',NULL,'image/shtl/cs/first/2.png',NULL,'image/shtl/cs/first/3.png',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,'0','2','image/shtl/cs/second/1.png',NULL,'image/shtl/cs/second/2.png',NULL,'image/shtl/cs/second/3.png',NULL,'image/shtl/cs/second/4.png',NULL,NULL,NULL,NULL,NULL,NULL),(3,'0','3','image/shtl/cs/third/1.png',NULL,'image/shtl/cs/third/2.png',NULL,'image/shtl/cs/third/3.png',NULL,'image/shtl/cs/third/4.png',NULL,'image/shtl/cs/third/5.png',NULL,NULL,NULL,NULL),(4,'0','4','image/shtl/cs/four/1.png',NULL,'image/shtl/cs/four/2.png',NULL,'image/shtl/cs/four/3.png',NULL,'image/shtl/cs/four/4.png',NULL,'image/shtl/cs/four/5.png',NULL,'image/shtl/cs/four/6.png',NULL,NULL),(5,'1','1','image/shtl/jc/first/1.png','小芳小心翼翼地把糖果藏到盒子里。','image/shtl/jc/first/2.png','妈妈吃糖果。','image/shtl/jc/first/3.png','小芳发现糖果不见了。',NULL,NULL,NULL,NULL,NULL,NULL,'image/shtl/jc/first/D.png'),(6,'1','2','image/shtl/jc/second/1.png','小玲把盒子放地上。','image/shtl/jc/second/2.png','小玲转身去摘花，小明悄悄去拿盒子。','image/shtl/jc/second/3.png','小玲摘到了花，小明拿着盒子离开。','image/shtl/jc/second/4.png','小玲回头发现盒子不见了。',NULL,NULL,NULL,NULL,'image/shtl/jc/second/D.png'),(7,'1','3','image/shtl/jc/third/1.png','小红去商店买糖果。','image/shtl/jc/third/2.png','袋子破了，糖果开始掉落。','image/shtl/jc/third/3.png','糖果掉了一路。','image/shtl/jc/third/4.png','小红回家发现糖果没了。',NULL,NULL,NULL,NULL,'image/shtl/jc/third/D.png'),(8,'1','4','image/shtl/jc/four/1.png','小星在远处看到小美在吃冰淇淋。','image/shtl/jc/four/2.png','小星坐在小美旁边看他开心地吃冰淇淋。','image/shtl/jc/four/3.png','小星伸手拿小美手中的冰淇淋。','image/shtl/jc/four/4.png','小星拿着小美的冰淇淋离开了。',NULL,NULL,NULL,NULL,'image/shtl/jc/four/D.png'),(9,'2','1','image/shtl/jj/first/1.png','晨晨在穿裤子。','image/shtl/jj/first/2.png','晨晨在穿上衣。','image/shtl/jj/first/3.png','晨晨照镜子发现衣服穿反了。','image/shtl/jj/first/4.png','晨晨脱衣服。','image/shtl/jj/first/5.png','晨晨重新穿好了衣服。',NULL,NULL,'image/shtl/jj/first/D.png'),(10,'2','2','image/shtl/jj/second/1.png','同学们正在专心听课。','image/shtl/jj/second/2.png','突然，小刚迟到了，走进教室低着头。','image/shtl/jj/second/3.png','接着，小红又迟到了，老师生气地指着她。','image/shtl/jj/second/4.png','老师不小心碰到水杯了，水往外溢。','image/shtl/jj/second/5.png','小红闷闷不乐，水杯从桌上掉下去了。',NULL,NULL,'image/shtl/jj/second/D.png'),(11,'2','3','image/shtl/jj/third/1.png','明明坐在座位上，静静提着2袋东西站其身旁。','image/shtl/jj/third/2.png','袋子破了，东西洒落在地。','image/shtl/jj/third/3.png','明明帮静静捡东西。','image/shtl/jj/third/4.png','明明主动给静静让座。','image/shtl/jj/third/5.png','静静坐在了明明的位置上。',NULL,NULL,'image/shtl/jj/third/D.png'),(12,'2','4','image/shtl/jj/four/1.png','小刚和小强想玩小明的球。','image/shtl/jj/four/2.png','小明一个人踢球，小刚和小强在商量。','image/shtl/jj/four/3.png','小刚和小强偷偷地在地上挖洞。','image/shtl/jj/four/4.png','小刚和小强示意小明过来玩。','image/shtl/jj/four/5.png','小明掉进了坑里，小刚和小强拿到了球。','image/shtl/jj/four/6.png','小明和小强拿球就跑。','image/shtl/jj/four/D.png');

/*Table structure for table `zz_sysb_score` */

DROP TABLE IF EXISTS `zz_sysb_score`;

CREATE TABLE `zz_sysb_score` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(255) NOT NULL COMMENT '题号',
  `point` varchar(255) NOT NULL COMMENT '得分',
  `test_date` date NOT NULL COMMENT '测试日期',
  `task_id` bigint(255) NOT NULL COMMENT '任务id',
  `patient_id` bigint(255) NOT NULL COMMENT '患者id',
  `scale_id` bigint(100) DEFAULT NULL COMMENT '量表id',
  `workstation` varchar(10) CHARACTER SET utf8 DEFAULT NULL COMMENT '工作站',
  `headline` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '大标题',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=714 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC COMMENT='失言任务范式';

/*Data for the table `zz_sysb_score` */

/*Table structure for table `zz_sysb_type` */

DROP TABLE IF EXISTS `zz_sysb_type`;

CREATE TABLE `zz_sysb_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `headline` varchar(255) DEFAULT NULL COMMENT '大标题',
  `title` varchar(100) DEFAULT NULL COMMENT '题号',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `option_a` varchar(100) DEFAULT NULL COMMENT '选项a',
  `option_b` varchar(100) DEFAULT '' COMMENT '选项b',
  `option_c` varchar(100) DEFAULT '' COMMENT '选项c',
  `option_d` varchar(100) DEFAULT NULL COMMENT '选项d',
  `option_correct` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='失言识别类型表';

/*Data for the table `zz_sysb_type` */

insert  into `zz_sysb_type`(`id`,`headline`,`title`,`content`,`option_a`,`option_b`,`option_c`,`option_d`,`option_correct`,`parent_id`) values (1,NULL,'1','小明刚开始一份新工作。一天，他和新朋友小强在咖啡间聊天。小强问：“你妻子是做什么的,”小明回答：“她是个律师。”过了一会儿，李志生气地走进了咖啡间说：“我刚刚接了个糟糕的电话，律师都是那么贪婪傲慢，我无法忍受他们。”小强问李志：“你要看一下这些报告吗？”她回答道：“现在不要，我想要杯咖啡。”','image/story/故事1.jpg','image/story/故事1.1.png','','',NULL,0),(2,NULL,'2','张梅的丈夫准备为她的生日举办一个惊喜派对。他邀请了张梅的一个朋友田欣，并且告诉她：“不要告诉任何人，尤其是张梅。”派对的前一天，张梅在田欣家里，张梅不小心把咖啡洒在了一条挂在椅子上的新裙子上。田欣说：“噢～我本打算穿这件衣服去参加你的派对。”张梅问：“什么派对？”田欣说：“快来，我们看看能不能把这些污渍去掉。”','image/story/故事2.jpg','image/story/故事2.1.png','',NULL,NULL,0),(3,NULL,'3','小敏刚搬进了新公寓。她去购物，买了些新窗帘挂在卧室。在她刚装饰完公寓的时候，她最好的朋友圆圆过来了。小敏带她参观了自己的新公寓，问道：“你觉得我的卧室怎么样，”“那些窗帘很难看，”圆圆回答说：“我希望你能换个新的。” \r','image/story/故事3.jpg','image/story/故事3.1.png','',NULL,NULL,0),(4,NULL,'4','阳阳买了个水晶碗作为结婚礼物送给她的朋友小美。小美举行了盛大的婚礼，收到了很多礼物，多到记不过来。大约一年以后，阳阳在小美家吃晚饭。阳阳没拿稳酒瓶，酒瓶刚好砸在了水晶碗上，水晶碗碎了。阳阳说：“真对不起，我打碎了这个碗。”小美说：“没关系，我一直不喜欢这个碗，不知道是谁在婚礼上送的礼物。”','image/story/故事4.jpg','image/story/故事4.1.png','',NULL,NULL,0),(5,NULL,'5','苏丹的表弟雷雷要来拜访苏丹，于是苏丹特地为他做了个苹果沙拉。晚饭后，苏丹说：“我为你做了个沙拉，就在厨房里。”雷雷答道：“嗯，闻起来真香，我很爱吃沙拉，当然，除了苹果味的。”\r','image/story/故事5.jpg','image/story/故事5.1.png','',NULL,NULL,0),(6,NULL,'6','圆圆是个三岁女孩，有一张圆脸和金色短发。她在张阿姨家里。门铃响了，张阿姨去开门。按门铃的是张阿姨的邻居静静。“你好，”张阿姨说：“谢谢你顺道过来。”静静说：“你好。”然后又看了看圆圆说：“我想我没见过这个小男孩，你叫什么名字？” ','image/story/故事6.jpg','image/story/故事6.1.png','',NULL,NULL,0),(7,NULL,'7','软件设计公司的经理王强召集所有员工开会。“我有事想告诉你们。” 王强说道：“我们的会计李明先生得了癌症，病得非常厉害，现在正在医院接受治疗。”所有人都沉默了，静静消化这个消息。这时，一个软件工程师赵海迟到了。“我昨晚听了个特别搞笑的笑话，”他说：“猜猜癌症晚期的病人会对他的医生说什么，”王强说：“好吧，让我们讨论正事吧。”','image/story/故事7.jpg','image/story/故事7.1.png','',NULL,NULL,0),(8,NULL,'8','9岁的男孩王洪转学到一所新学校。有一天，他在学校卫生间的隔间里。这时，另外两个男孩，李阳和王奇走进来在洗手池边聊天。李阳说：“你知道班里那个新生吗，他叫王洪，你不觉得他看上去很奇怪吗，而且他长得好矮啊。”王洪从隔间中走出来，李阳和王奇看到了他。王奇说：“你好，王洪，你现在想去踢球吗？”','image/story/故事8.jpg','image/story/故事8.1.png','',NULL,NULL,0),(9,NULL,'9','希望小学举办了一场故事比赛。每个人都可以参加。几个五年级的学生也去了。一个五年级的学生芳芳很爱讲故事，也参加了比赛。几天后，比赛结果公布了，芳芳的故事没有拿到任何奖项。而她的同学玲玲得了一等奖。第二天，芳芳和玲玲坐在长凳上，他们看着玲玲的奖杯。玲玲说：“赢得比赛简直小菜一碟，其他的故事都太烂了。”“你打算把奖杯放哪儿呢？”芳芳问。','image/story/故事9.jpg','image/story/故事9.1.png','',NULL,NULL,0),(10,NULL,'10','王浩在一家餐厅里，不小心把咖啡洒在了地上。“我再给您拿一杯咖啡，”服务员说。服务员离开了一会儿。餐厅里的另一个顾客李刚，正在收银台前排队买单。王浩走到李刚面前说：“我把咖啡洒了，你能帮我擦一下吗？”','image/story/故事10.jpg','image/story/故事10.1.png','',NULL,NULL,0),(11,NULL,'1','在故事中，小明的妻子是做什么的？','A.老师','B.律师','C.医生',NULL,'B.律师',1),(12,NULL,'2','小明和小强在哪里聊天？','A.办公室','B.会议室','C.咖啡间',NULL,'C.咖啡间',1),(13,NULL,'3','有没有人说了不该说的或者令人尴尬的话?','A.没有','B.有','C.不确定',NULL,'B.有',1),(14,NULL,'4','谁说了不该说的或者令人尴尬的话？','A.小明','B.小强','C.李志',NULL,'C.李志',1),(15,NULL,'5','为什么他们不该说或这些话为什么是尴尬的？','A.因为小明的妻子是律师','B.因为小强的妻子是律师','C.因为李志的妻子是律师',NULL,'A.因为小明的妻子是律师',1),(16,NULL,'6','你认为他/她为什么会这么说？ ','A.他可能心情不好','B.他可能遇到了一个不讨人喜欢的律师','C.他工作遇到不顺利的事情',NULL,'B.他可能遇到了一个不讨人喜欢的律师',1),(17,NULL,'7','李志知道小明的妻子是律师吗？','A.知道','B.不知道','C.不确定',NULL,'B.不知道',1),(18,NULL,'8','你认为小明感觉如何？ ','A.开心','B.生气','C.惊讶',NULL,'B.生气',1),(19,NULL,'1','在故事中，惊喜派对是为谁举办的？','A.张梅的丈夫','B.田欣','C.张梅',NULL,'C.张梅',2),(20,NULL,'2','什么东西洒在了那条新裙子上？','A.可乐','B.咖啡','C.白水',NULL,'B.咖啡',2),(21,NULL,'3','有没有人说了不该说的或者令人尴尬的话?','A.没有','B.有','C.不确定',NULL,'B.有',2),(22,NULL,'4','谁说了不该说的或者令人尴尬的话？','A.张梅','B.田欣','C.张梅的丈夫',NULL,'B.田欣',2),(23,NULL,'5','为什么他们不该说或这些话为什么是尴尬的？','A.因为田欣不想参加派对','B.因为田欣不喜欢张梅','C.因为想给张梅一个惊喜',NULL,'C.因为想给张梅一个惊喜',2),(24,NULL,'6','你认为他/她为什么会这么说？ ','A.因为张梅丈夫想让她说','B.说太快没多想','C.因为不知道是惊喜派对',NULL,'B.说太快没多想',2),(25,NULL,'7','田欣记得那个派对是个惊喜派对吗？','A.不记得','B.记得','C.不确定',NULL,'B.记得',2),(26,NULL,'8','你认为张梅感觉如何？','A.开心','B.悲伤','C.好奇',NULL,'C.好奇',2),(27,NULL,'1','在故事中，小敏买了什么？','A.床','B.板凳','C.窗帘',NULL,'C.窗帘',3),(28,NULL,'2','小敏在这个公寓里住了多久？','A.1年','B.刚住进来','C.半年',NULL,'B.刚住进来',3),(29,NULL,'3','有没有人说了不该说的或者令人尴尬的话?','A.没有','B.有','C.不确定',NULL,'B.有',3),(30,NULL,'4','谁说了不该说的或者令人尴尬的话？','A.小敏','B.不确定','C.圆圆',NULL,'C.圆圆',3),(31,NULL,'5','为什么他们不该说或这些话为什么是尴尬的？','A.因为小敏非常喜欢新窗帘','B.因为窗帘是小敏新买的','C.因为圆圆想买新窗帘',NULL,'B.因为窗帘是小敏新买的',3),(32,NULL,'6','你认为他/她为什么会这么说？ ','A.因为窗帘很难看','B.因为窗帘很陈旧','C.因为卧室布置很美观',NULL,'A.因为窗帘很难看',3),(33,NULL,'7','圆圆知道窗帘是谁买的吗？','A.知道','B.不知道','C.不确定 ',NULL,'B.不知道',3),(34,NULL,'8','你认为小敏感觉如何？','A.开心','B.恐惧','C.尴尬',NULL,'C.尴尬',3),(35,NULL,'1','在故事中，阳阳送给小美什么作为结婚礼物','A.美酒','B.水晶碗','C.首饰',NULL,'B.水晶碗',4),(36,NULL,'2','那个碗是怎么打碎的？','A.被阳阳故意打碎了','B.掉在地上了','C.被酒瓶砸碎的',NULL,'C.被酒瓶砸碎的',4),(37,NULL,'3','有没有人说了不该说的或者令人尴尬的话?','A.没有','B.有','C.不确定',NULL,'B.有',4),(38,NULL,'4','谁说了不该说的或者令人尴尬的话？','A.阳阳','B.小美','C.小美的丈夫',NULL,'B.小美',4),(39,NULL,'5','为什么他们不该说或这些话为什么是尴尬的？','A.因为小美听后会很难受','B.因为小美听后会很愤怒','C.因为阳阳听后会很难受',NULL,'C.因为阳阳听后会很难受',4),(40,NULL,'6','你认为他/她为什么会这么说？ ','A.因为她不喜欢这个碗，也不知道谁送的','B.因为她很惭愧','C.因为她很开心',NULL,'A.因为她不喜欢这个碗，也不知道谁送的',4),(41,NULL,'7','小美记得是阳阳送给她那个碗吗？','A.记得','B.不记得','C.不确定',NULL,'B.不记得',4),(42,NULL,'8','你认为阳阳感觉如何？','A.难过','B.激动','C.高兴',NULL,'A.难过',4),(43,NULL,'1','在故事中，苏丹做了什么？','A.香蕉沙拉','B.苹果沙拉','C.草莓沙拉',NULL,'B.苹果沙拉',5),(44,NULL,'2','苏丹和雷雷是什么关系？','A.亲戚','B.朋友','C.同事',NULL,'A.亲戚',5),(45,NULL,'3','有没有人说了不该说的或者令人尴尬的话?','A.没有','B.有','C.不确定',NULL,'B.有',5),(46,NULL,'4','谁说了不该说的或者令人尴尬的话？','A.苏丹','B.雷雷','C.苏丹的爸爸',NULL,'B.雷雷',5),(47,NULL,'5','为什么他们不该说或这些话为什么是尴尬的？','A.因为苏丹不喜欢吃沙拉','B.因为雷雷不喜欢吃沙拉','C.因为苏丹做的是苹果沙拉',NULL,'C.因为苏丹做的是苹果沙拉',5),(48,NULL,'6','你认为他/她为什么会这么说？ ','A.因为雷雷不喜欢苹果味的沙拉','B.因为雷雷不喜欢吃沙拉','C.因为沙拉闻起来真香',NULL,'A.因为雷雷不喜欢苹果味的沙拉',5),(49,NULL,'7','当雷雷闻到那个沙拉的时候，他知道这是个苹果沙拉吗？','A.知道','B.不知道','C.不确定',NULL,'B.不知道',5),(50,NULL,'8','你认为苏丹感觉如何？','A.尴尬','B.恐惧','C.高兴',NULL,'A.尴尬',5),(51,NULL,'1','在故事中，圆圆在哪里？','A.自己家','B.静静家','C.张阿姨家',NULL,'C.张阿姨家',6),(52,NULL,'2','谁来拜访张阿姨？','A.圆圆','B.静静','C.同事',NULL,'B.静静',6),(53,NULL,'3','有没有人说了不该说的或者令人尴尬的话?','A.没有','B.有','C.不确定',NULL,'B.有',6),(54,NULL,'4','谁说了不该说的或者令人尴尬的话？','A.张阿姨','B.圆圆','C.静静',NULL,'C.静静',6),(55,NULL,'5','为什么他们不该说或这些话为什么是尴尬的？','A.因为静静的声音不好听','B.因为张阿姨不喜欢静静','C.因为圆圆是小女孩',NULL,'C.因为圆圆是小女孩',6),(56,NULL,'6','你认为他/她为什么会这么说？ ','A.因为圆圆有着金色短发','B.因为张阿姨喜欢小男孩','C.因为静静喜欢小男孩',NULL,'A.因为圆圆有着金色短发',6),(57,NULL,'7','静静知道圆圆是个女孩吗？','A.知道','B.不确定','C.不知道',NULL,'C.不知道',6),(58,NULL,'8','你认为圆圆感觉如何？','A.开心','B.生气','C.恐惧',NULL,'B.生气',6),(59,NULL,'1','在故事中，王强在会议上告诉大家什么事？','A.赵海病重','B.赵海迟到','C.李明病重',NULL,'C.李明病重',7),(60,NULL,'2','谁在会议上迟到？','A.赵海','B.李明','C.王强',NULL,'A.赵海',7),(61,NULL,'3','有没有人说了不该说的或者令人尴尬的话?','A.没有','B.不确定','C.有',NULL,'C.有',7),(62,NULL,'4','谁说了不该说的或者令人尴尬的话？','A.赵海','B.李明','C.王强',NULL,'A.赵海',7),(63,NULL,'5','为什么他们不该说或这些话为什么是尴尬的？','A.因为这些话很搞笑','B.因为李明得了癌症','C.因为王强病重了',NULL,'B.因为李明得了癌症',7),(64,NULL,'6','你认为他/她为什么会这么说？ ','A.因为李明喜欢听笑话','B.因为王强喜欢听笑话','C.因为想给大家分享听到的笑话',NULL,'C.因为想给大家分享听到的笑话',7),(65,NULL,'7','赵海进来的时候，他知道那个会计得了癌症病得很重吗？','A.知道','B.不知道','C.不确定',NULL,'B.不知道',7),(66,NULL,'8','你认为王强感觉如何？','A.尴尬','B.高兴','C.激动',NULL,'A.尴尬',7),(67,NULL,'1','在故事中，李阳和王奇说话时，王洪在哪里？','A.教室','B.操场','C.卫生间',NULL,'C.卫生间',8),(68,NULL,'2','李阳说了些关于王洪的什么？','A.成绩','B.长相','C.家乡',NULL,'B.长相',8),(69,NULL,'3','有没有人说了不该说的或者令人尴尬的话?','A.没有','B.不确定','C.有',NULL,'C.有',8),(70,NULL,'4','谁说了不该说的或者令人尴尬的话？','A.李阳','B.王洪','C.王奇',NULL,'A.李阳',8),(71,NULL,'5','为什么他们不该说或这些话为什么是尴尬的？','A.因为王洪是新生','B.因为王奇不想听','C.因为议论不好的长相',NULL,'C.因为议论不好的长相',8),(72,NULL,'6','你认为他/她为什么会这么说？ ','A.因为感到好奇','B.因为感到厌恶','C.因为感到激动',NULL,'A.因为感到好奇',8),(73,NULL,'7','李阳和王奇说话时，李阳知道王洪在其中一间隔间里吗？','A.知道','B.不知道','C.不确定',NULL,'B.不知道',8),(74,NULL,'8','你认为王洪感觉如何？','A.开心','B.兴奋','C.气愤',NULL,'C.气愤',8),(75,NULL,'1','在故事中，谁赢了比赛？','A.芳芳','B.玲玲','C.不确定',NULL,'B.玲玲',9),(76,NULL,'2','芳芳的故事是否拿奖了？','A.是','B.否','C.不确定',NULL,'B.否',9),(77,NULL,'3','有没有人说了不该说的或者令人尴尬的话?','A.没有','B.不确定','C.有',NULL,'C.有',9),(78,NULL,'4','谁说了不该说的或者令人尴尬的话？','A.玲玲','B.芳芳','C.玲玲的同学',NULL,'A.玲玲',9),(79,NULL,'5','为什么他们不该说或这些话为什么是尴尬的？','A.因为玲玲参加了比赛','B.因为芳芳参加了比赛','C.因为玲玲得奖了',NULL,'B.因为芳芳参加了比赛',9),(80,NULL,'6','你认为他/她为什么会这么说？ ','A.因为她喜欢讲故事','B.因为她喜欢参加比赛','C.因为她得了第一名',NULL,'C.因为她得了第一名',9),(81,NULL,'7','玲玲知道芳芳参加了比赛吗？','A.知道','B.不知道','C.不确定',NULL,'A.知道',9),(82,NULL,'8','你认为芳芳感觉如何？','A.恐惧','B.高兴','C.难过',NULL,'C.难过',9),(83,NULL,'1','在故事中，李刚为什么站在收银台前？','A.服务','B.买单','C.取咖啡',NULL,'B.买单',10),(84,NULL,'2','王浩洒了什么？','A.橙汁','B.酒','C.咖啡',NULL,'C.咖啡',10),(85,NULL,'3','有没有人说了不该说的或者令人尴尬的话?','A.没有','B.不确定','C.有',NULL,'C.有',10),(86,NULL,'4','谁说了不该说的或者令人尴尬的话？','A.服务员','B.王浩','C.李刚',NULL,'B.王浩',10),(87,NULL,'5','为什么他们不该说或这些话为什么是尴尬的？','A.因为李刚是服务员','B.因为李刚是顾客','C.因为王浩是顾客',NULL,'B.因为李刚是顾客',10),(88,NULL,'6','你认为他/她为什么会这么说？ ','A.因为他以为对方是服务员','B.因为他找不到服务员','C.因为他想找收银员',NULL,'A.因为他以为对方是服务员',10),(89,NULL,'7','王浩知道李刚也是顾客吗？','A.知道','B.不知道','C.不确定',NULL,'B.不知道',10),(90,NULL,'8','你认为李刚感觉如何？','A.尴尬','B.高兴','C.恐惧',NULL,'A.尴尬',10);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;