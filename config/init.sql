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

create TABLE t_itapm_interface_catagory(
  id INT auto_increment PRIMARY  key COMMENT '主键',
  system_id INT NOT NULL COMMENT '系统ID',
  name VARCHAR(256) not null COMMENT '接口分类，二级菜单',
  status VARCHAR(16) NOT NULL COMMENT 'online显示 offline不显示',
  create_time datetime COMMENT '创建时间' ,
  update_time datetime COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '接口类别';


create TABLE t_itapm_interface_detail(
  id INT auto_increment PRIMARY  key COMMENT '主键',
  system_id INT NOT NULL COMMENT '系统ID',
  system_name VARCHAR(64) NOT NULL COMMENT '所属系统名',
  catagory_id INT NOT NULL COMMENT '目录ID',
  name VARCHAR(256) not null COMMENT '接口名称',
  address VARCHAR(256) NOT NULL COMMENT '接口地址',
  users VARCHAR(128) COMMENT '接口调用方',
  status VARCHAR(16) NOT NULL COMMENT 'PRE_ONLINE 预上线，ONLINE 已上线，OFFLINE 已下线，PRE_OFFLINE 准备下线',
  description VARCHAR(512) COMMENT '接口描述信息',
  create_time datetime COMMENT '创建时间' ,
  update_time datetime COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '接口明细表';

CREATE TABLE t_itapm_param_info(
  id INT auto_increment PRIMARY  key COMMENT '主键',
  type VARCHAR(18) NOT NULL COMMENT 'REQ :请求参数 RESP返回参数',
  parent_id INT COMMENT '父参数',
  interface_detail_id INT not NULL COMMENT '所属接口',
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

