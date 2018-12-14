/**
  系统列表
*/

create database itapm;

use itapm;


create table t_itapm_system_info(
  id INT auto_increment PRIMARY  key COMMENT '主键',
  ch_name varchar(128) not null COMMENT '中文名',
  en_name VARCHAR(64) not null COMMENT '英文名',
  description VARCHAR(64) COMMENT '中文描述',
  status VARCHAR(16) COMMENT 'PRE_ONLINE 预上线，ONLINE 已上线，OFFLINE 已下线，PRE_OFFLINE 准备下线',
  create_time datetime COMMENT '创建时间' ,
  update_time datetime COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '系统信息';


ALTER table t_itapm_system_info add UNIQUE INDEX IDX_SYSTEM_EN_NAME(en_name);



create table t_itapm_system_version(
  id INT auto_increment PRIMARY  key COMMENT '主键',
  system_id INT NOT NULL COMMENT '系统ID',
  system_name VARCHAR(64) not null COMMENT '英文名',
  system_version VARCHAR(128) not NULL COMMENT '版本号',
  create_time datetime COMMENT '创建时间' ,
  update_time datetime COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '系统信息';
ALTER table t_itapm_system_version add UNIQUE INDEX IDX_SYSTEM_VERSION_NAME_VERSION(system_id,system_version);



create TABLE t_itapm_interface_catagory(
  id INT auto_increment PRIMARY  key COMMENT '主键',
  system_version_id INT NOT NULL COMMENT '系统版本ID',
  name VARCHAR(256) not null COMMENT '接口分类，二级菜单',
  status VARCHAR(16) NOT NULL COMMENT 'online显示 offline不显示',
  create_time datetime COMMENT '创建时间' ,
  update_time datetime COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '接口类别';
ALTER table t_itapm_interface_catagory add UNIQUE INDEX IDX_catagory_version_id_name(system_version_id,name);


create TABLE t_itapm_interface_detail(
  id INT auto_increment PRIMARY  key COMMENT '主键',
  system_version_id INT NOT NULL COMMENT '系统版本ID',
  system_name VARCHAR(64) NOT NULL COMMENT '所属系统名',
  catagory_id INT NOT NULL COMMENT '目录ID',
  name VARCHAR(256) not null COMMENT '接口名称',
  address VARCHAR(256) COMMENT '接口地址',
  caller VARCHAR(128) COMMENT '接口调用方',
  type VARCHAR(32) COMMENT '类型 dubbo/REST',
  request_type VARCHAR(32) COMMENT '请求类型',
  status VARCHAR(16) NOT NULL COMMENT 'PRE_ONLINE 预上线，ONLINE 已上线，OFFLINE 已下线，PRE_OFFLINE 准备下线',
  description VARCHAR(512) COMMENT '接口描述信息',
  create_time datetime COMMENT '创建时间' ,
  update_time datetime COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '接口明细表';
ALTER table t_itapm_interface_detail add UNIQUE INDEX idx_detail_version_id_name(catagory_id,name);



create TABLE t_itapm_param_type(
  id INT auto_increment PRIMARY  key COMMENT '主键',
  interface_detail_id int NOT NULL COMMENT '所属接口',
  param_type_name VARCHAR(64) NOT NULL COMMENT '参数类型名',
  resource VARCHAR(8) NOT NULL COMMENT '参数类型：REQ:请求参数 RESP:返回参数',
  create_time datetime COMMENT '创建时间' ,
  update_time datetime COMMENT '创建时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '参数类型表';
ALTER table t_itapm_param_type add UNIQUE INDEX idx_param_detail_id_name(interface_detail_id,param_type_name,resource);



create TABLE t_itapm_generic_param_type(
  id INT auto_increment PRIMARY  key COMMENT '主键',
  systemVersionId int NOT NULL COMMENT '所属系统',
  name VARCHAR(128) NOT NULL COMMENT '泛型类型名称',
  create_time datetime COMMENT '创建时间',
  update_time datetime COMMENT '创建时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '泛型参数类型';
ALTER TABLE t_itapm_generic_param_type ADD UNIQUE INDEX IDX_GENERIC_TYPE(systemVersionId,name);


CREATE TABLE t_itapm_param_type_ref_generic(
id INT auto_increment PRIMARY key COMMENT '主键',
param_type_id INT NOT NULL ,
generic_type_id INT NOT NULL,
create_time datetime COMMENT '创建时间',
update_time datetime COMMENT '创建时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '参数类型和泛型类型对应关系表';
ALTER TABLE t_itapm_param_type_ref_generic add UNIQUE INDEX IDX_PARAM_TYPE_REF(param_type_id,generic_type_id);




CREATE TABLE t_itapm_param_field(
  id INT auto_increment PRIMARY  key COMMENT '主键',
  param_type_id INT COMMENT '父参数',
  param_name VARCHAR(128) NOT NULL COMMENT '参数名称',
  param_type VARCHAR(256) NOT NULL COMMENT '参数类型',
  param_length INT COMMENT '参数长度',
  required VARCHAR(2) COMMENT 'Y 必填 N 非必填',
  default_value VARCHAR(64) COMMENT '默认值',
  param_description VARCHAR(64) COMMENT '参数描述信息',
  example VARCHAR(256) COMMENT '例子',
  create_time datetime COMMENT '创建时间',
  update_time datetime COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '参数类型表';

ALTER TABLE t_itapm_param_field add INDEX IDX_PARAM_FIELD_TYPE_ID(param_type_id);
