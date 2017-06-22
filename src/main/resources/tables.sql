
-- 用户 表
DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user(
  id bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  nickname varchar(20) DEFAULT '' COMMENT '用户昵称',
  account varchar(128) NOT NULL DEFAULT '' COMMENT '登录帐号',
  email varchar(128) DEFAULT '' COMMENT '邮箱',
  pwd varchar(32) NOT NULL DEFAULT '' COMMENT '密码',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  last_login_time datetime DEFAULT NULL COMMENT '最后登录时间',
  status int DEFAULT '1' COMMENT '1:有效，0:禁止登录'
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
insert  into `sys_user`(`id`,`account`,`nickname`,`email`,`pwd`,`create_time`,`last_login_time`,`status`) values (1,'ipple1986','admin','ipple1986@gmail.com','57eb72e6b78a87a12d46a7f5e9315138',now(),now(),1),(2,'user','user','user@qq.com','d57ffbe486910dd5b26d0167d034f9ad',now(),now(),1),(3,'mm','mm','mm@gmail.com','4afdc875a67a55528c224ce088be2ab8',now(),now(),1);
update sys_user set pwd=md5('ipple1986');

-- 用户角色表
DROP TABLE IF EXISTS sys_role;
CREATE TABLE sys_role(
   id bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name varchar(32) DEFAULT '' COMMENT '角色名称',
  type varchar(10) DEFAULT '' COMMENT '预留字段，角色类型(menu,admin等)',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_time datetime DEFAULT NULL COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO sys_role(id,name,type,create_time,update_time)VALUES
(1,'perms','menu',now(),now()),
(2,'config','menu',now(),now()),
(3,'client','menu',now(),now()),
(4,'report','menu',now(),now()),
(5,'sales','menu',now(),now()),
(6,'call','menu',now(),now()),
(7,'jkb','menu',now(),now()),
(8,'bee','menu',now(),now());

INSERT INTO sys_role(id,name,type,create_time,update_time)VALUES
(1,'权限管理','menu',now(),now()),
(2,'配置管理','menu',now(),now()),
(3,'客户管理','menu',now(),now()),
(4,'数据报表','menu',now(),now()),
(5,'移动销售','menu',now(),now()),
(6,'呼叫中心','menu',now(),now()),
(7,'聚客宝','menu',now(),now()),
(8,'小蜜蜂','menu',now(),now());


-- 用户权限表
DROP TABLE IF EXISTS sys_permission;
CREATE TABLE sys_permission(
  id bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  url varchar(256) NOT NULL DEFAULT '' COMMENT 'url地址,pid为0的顶级菜单，有可能此字段为空',
  name varchar(64) DEFAULT '' COMMENT 'url描述',
  type varchar(10) DEFAULT 'menu' COMMENT '取值有menu,btn,url,other',
  pid bigint(20) DEFAULT 0 COMMENT '供type=menu使用，父级为0，否则填写父亲级菜单id',
  sort int DEFAULT 0 COMMENT '供type=menu排序使用',
  icon varchar(64) DEFAULT '' COMMENT '供type=menu使用，图标名称',
  mask int DEFAULT 15 COMMENT '15为二进制1111的十进制数，供type=menu使用，作为显示掩码使用，判断在对应层次可以显示',
  memo varchar(256) DEFAULT '' COMMENT '备注',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_time datetime DEFAULT NULL COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO sys_permission(id,url,name,type,pid,sort,icon,mask,memo,create_time,update_time)
VALUES (1,'','角色管理','menu',0,0,'',15,'',now(),now()),
(2,'/role/list','角色列表','menu',1,0,'',15,'',now(),now()),
(3,'/role/goAdd','跳转到角色页面','other',1,0,'',15,'',now(),now()),
(4,'/role/add','添加角色','other',1,0,'',15,'',now(),now()),
(5,'/role/goUpdate','跳转到角色页面','other',1,0,'',15,'',now(),now()),
(6,'/role/update','修改角色','other',1,0,'',15,'',now(),now()),
(7,'/role/delete','删除角色','other',1,0,'',15,'',now(),now()),
(8,'','权限管理','menu',0,0,'',15,'',now(),now()),
(9,'/permission/list','权限列表','menu',8,0,'',15,'',now(),now()),
(10,'/permission/goAdd','跳转到添加页面','other',8,0,'',15,'',now(),now()),
(11,'/permission/add','添加权限','other',8,0,'',15,'',now(),now()),
(12,'/permission/goUpdate','跳转到修改页面','other',8,0,'',15,'',now(),now()),
(13,'/permission/update','修改权限','other',8,0,'',15,'',now(),now()),
(14,'/permission/delete','删除权限','other',8,0,'',15,'',now(),now()),
(15,'/userrole/list','角色分配','other',0,0,'',15,'',now(),now()),
(16,'/roleperms/list','权限分配','other',0,0,'',15,'',now(),now());



-- 用户角色权限表
DROP TABLE IF EXISTS sys_role_permission;
CREATE TABLE sys_role_permission (
  id bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  rid bigint(20) DEFAULT NULL COMMENT '角色ID',
  pid bigint(20) DEFAULT NULL COMMENT '权限ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- 权限管理 
INSERT INTO sys_role_permission(rid,pid)VALUES(1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16);


-- 用户角色 表
DROP TABLE IF EXISTS sys_user_role;
CREATE TABLE sys_user_role (
  id bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  uid bigint(20) DEFAULT NULL COMMENT '用户ID',
  rid bigint(20) DEFAULT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO sys_user_role(uid,rid) VALUE(1,1);

