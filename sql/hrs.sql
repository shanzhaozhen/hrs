/*
用户表
 */
DROP TABLE IF EXISTS sys_user;

CREATE TABLE sys_user
(
    id BIGINT NOT NULL COMMENT '主键ID',
    username VARCHAR(30) NOT NULL UNIQUE COMMENT '用户名',
    password VARCHAR(255) NOT NULL COMMENT '密码',
    dep_id BIGINT NULL DEFAULT NULL COMMENT '部门ID',
    account_non_expired bit(1) NOT NULL COMMENT '账户是否过期,过期无法验证',
    account_non_locked bit(1) NOT NULL COMMENT '指定用户是否被锁定或者解锁,锁定的用户无法进行身份验证',
    credentials_non_expired bit(1) NOT NULL COMMENT '指示是否已过期的用户的凭据(密码),过期的凭据防止认证',
    enabled bit(1) NOT NULL COMMENT '是否被禁用,禁用的用户不能身份验证',
    name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    nickname VARCHAR(30) NULL DEFAULT NULL COMMENT '昵称',
    sex INT NULL DEFAULT NULL COMMENT '性别',
    birthday DATE NULL DEFAULT NULL COMMENT '生日',
    avatar VARCHAR(255) NULL DEFAULT NULL COMMENT '头像',
    email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    phone_number VARCHAR(11) NULL DEFAULT NULL COMMENT '手机号码',
    address_code VARCHAR(255) NULL DEFAULT NULL COMMENT '地址编号',
    detailed_address VARCHAR(255) NULL DEFAULT NULL COMMENT '详细地址',
    introduction VARCHAR(255) NULL DEFAULT NULL COMMENT '个人介绍',
    created_by BIGINT NULL DEFAULT NULL COMMENT '创建人',
    created_date datetime NULL DEFAULT NULL COMMENT '创建时间',
    last_modified_by BIGINT NULL DEFAULT NULL COMMENT '修改人',
    last_modified_date datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (id)
);


/*
角色表
 */
DROP TABLE IF EXISTS sys_role;

CREATE TABLE sys_role
(
    id BIGINT NOT NULL COMMENT '主键ID',
    name VARCHAR(30) NOT NULL COMMENT '名称',
    code VARCHAR(30) NOT NULL UNIQUE COMMENT '角色代码',
    description VARCHAR(255) COMMENT '描述',
    created_by BIGINT NULL DEFAULT NULL COMMENT '创建人',
    created_date datetime NULL DEFAULT NULL COMMENT '创建时间',
    last_modified_by BIGINT NULL DEFAULT NULL COMMENT '修改人',
    last_modified_date datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (id)
);

/*
菜单表
 */
DROP TABLE IF EXISTS sys_menu;

CREATE TABLE sys_menu
(
    id BIGINT NOT NULL COMMENT '主键ID',
    name VARCHAR(30) NOT NULL COMMENT '菜单名称',
    locale VARCHAR(255) COMMENT '菜单名称（本地化）',
    path VARCHAR(255) COMMENT '菜单路由',
    pid BIGINT NULL DEFAULT NULL COMMENT '上级ID',
    icon VARCHAR(255) COMMENT '图标',
    priority INT(11) NULL DEFAULT NULL COMMENT '排序等级',
    hide_in_menu bit(1) NOT NULL DEFAULT 0 COMMENT '是否隐藏菜单',
    hide_children_in_menu bit(1) NOT NULL DEFAULT 0 COMMENT '是否隐藏子节点',
    props VARCHAR(255) COMMENT '参数',
    description VARCHAR(255) COMMENT '菜单描述',
    created_by BIGINT NULL DEFAULT NULL COMMENT '创建人',
    created_date datetime NULL DEFAULT NULL COMMENT '创建时间',
    last_modified_by BIGINT NULL DEFAULT NULL COMMENT '修改人',
    last_modified_date datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (id)
);

/*
资源表
 */
DROP TABLE IF EXISTS sys_resource;

CREATE TABLE sys_resource
(
    id BIGINT NOT NULL COMMENT '主键ID',
    name VARCHAR(30) NOT NULL COMMENT '资源名称',
    path VARCHAR(255) COMMENT '资源路由',
    type INT(11) NOT NULL COMMENT '资源类型',
    pid BIGINT NULL DEFAULT NULL COMMENT '上级ID',
    priority INT(11) NULL DEFAULT NULL COMMENT '排序等级',
    description VARCHAR(255) COMMENT '资源描述',
    created_by BIGINT NULL DEFAULT NULL COMMENT '创建人',
    created_date datetime NULL DEFAULT NULL COMMENT '创建时间',
    last_modified_by BIGINT NULL DEFAULT NULL COMMENT '修改人',
    last_modified_date datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (id)
);

/*
用户-角色关系表
 */
DROP TABLE IF EXISTS sys_user_role;

CREATE TABLE sys_user_role
(
    id BIGINT NOT NULL COMMENT '主键ID',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    role_id BIGINT NOT NULL COMMENT '角色ID',
    created_by BIGINT NULL DEFAULT NULL COMMENT '创建人',
    created_date datetime NULL DEFAULT NULL COMMENT '创建时间',
    last_modified_by BIGINT NULL DEFAULT NULL COMMENT '修改人',
    last_modified_date datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (id)
);

/*
角色-菜单关系表
 */
DROP TABLE IF EXISTS sys_role_menu;

CREATE TABLE sys_role_menu
(
    id BIGINT NOT NULL COMMENT '主键ID',
    role_id BIGINT NOT NULL COMMENT '角色ID',
    menu_id BIGINT NOT NULL COMMENT '权限ID',
    created_by BIGINT NULL DEFAULT NULL COMMENT '创建人',
    created_date datetime NULL DEFAULT NULL COMMENT '创建时间',
    last_modified_by BIGINT NULL DEFAULT NULL COMMENT '修改人',
    last_modified_date datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (id)
);

/*
角色-资源关系表
 */
DROP TABLE IF EXISTS sys_role_resource;

CREATE TABLE sys_role_resource
(
    id BIGINT NOT NULL COMMENT '主键ID',
    role_id BIGINT NOT NULL COMMENT '角色ID',
    resource_id BIGINT NOT NULL COMMENT '权限ID',
    created_by BIGINT NULL DEFAULT NULL COMMENT '创建人',
    created_date datetime NULL DEFAULT NULL COMMENT '创建时间',
    last_modified_by BIGINT NULL DEFAULT NULL COMMENT '修改人',
    last_modified_date datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (id)
);

/*
定时任务表
 */
DROP TABLE IF EXISTS sys_task;

CREATE TABLE sys_task
(
    id BIGINT NOT NULL COMMENT '主键ID',
    name VARCHAR(30) NOT NULL COMMENT '任务名称',
    cron VARCHAR(255) COMMENT 'cron表达式',
    bean_name VARCHAR(255) COMMENT 'bean名称',
    method_info VARCHAR(255) COMMENT '方法名',
    param_info VARCHAR(255) COMMENT '参数',
    open bit(1) NOT NULL COMMENT '开启状态',
    description VARCHAR(255) COMMENT '描述',
    created_by BIGINT NULL DEFAULT NULL COMMENT '创建人',
    created_date datetime NULL DEFAULT NULL COMMENT '创建时间',
    last_modified_by BIGINT NULL DEFAULT NULL COMMENT '修改人',
    last_modified_date datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (id)
);

/*
区域信息表
 */
DROP TABLE IF EXISTS sys_region;

CREATE TABLE sys_region
(
    id BIGINT NOT NULL COMMENT '主键ID',
    pid BIGINT COMMENT '父级ID',
    name VARCHAR(255) NOT NULL COMMENT '区域名称',
    code VARCHAR(255) NOT NULL COMMENT '区域编码',
    level INT NULL DEFAULT NULL COMMENT '层级',
    created_by BIGINT NULL DEFAULT NULL COMMENT '创建人',
    created_date datetime NULL DEFAULT NULL COMMENT '创建时间',
    last_modified_by BIGINT NULL DEFAULT NULL COMMENT '修改人',
    last_modified_date datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (id)
);

/*
部门表
 */
DROP TABLE IF EXISTS sys_department;

CREATE TABLE sys_department
(
    id BIGINT NOT NULL COMMENT '主键ID',
    name VARCHAR(255) NOT NULL COMMENT '部门名称',
    code VARCHAR(255) NULL DEFAULT NULL COMMENT '部门编号',
    pid BIGINT NULL DEFAULT NULL COMMENT '上级ID',
    priority INT(11) NULL DEFAULT NULL COMMENT '排序等级',
    description VARCHAR(255) COMMENT '描述',
    created_by BIGINT NULL DEFAULT NULL COMMENT '创建人',
    created_date datetime NULL DEFAULT NULL COMMENT '创建时间',
    last_modified_by BIGINT NULL DEFAULT NULL COMMENT '修改人',
    last_modified_date datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (id)
);

/*
字典表
 */
DROP TABLE IF EXISTS sys_dictionary;

CREATE TABLE sys_dictionary
(
    id BIGINT NOT NULL COMMENT '主键ID',
    name VARCHAR(255) NOT NULL COMMENT '字典名称',
    code VARCHAR(30) NOT NULL UNIQUE COMMENT '角色代码',
    pid BIGINT NULL DEFAULT NULL COMMENT '上级ID',
    priority INT(11) NULL DEFAULT NULL COMMENT '排序等级',
    description VARCHAR(255) COMMENT '字典描述',
    created_by BIGINT NULL DEFAULT NULL COMMENT '创建人',
    created_date datetime NULL DEFAULT NULL COMMENT '创建时间',
    last_modified_by BIGINT NULL DEFAULT NULL COMMENT '修改人',
    last_modified_date datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (id)
);

/*
文件记录表
 */
DROP TABLE IF EXISTS sys_file;

CREATE TABLE sys_file
(
    id BIGINT NOT NULL COMMENT '文件ID',
    name VARCHAR(255) NOT NULL COMMENT '文件名称',
    suffix VARCHAR(255) NULL DEFAULT NULL COMMENT '文件后缀',
    type VARCHAR(255) NULL DEFAULT NULL COMMENT '文件类型',
    path VARCHAR(255) NULL DEFAULT NULL COMMENT '文件路径',
    url_path VARCHAR(255) NULL DEFAULT NULL COMMENT '访问路径',
    md5 VARCHAR(255) NULL DEFAULT NULL COMMENT 'md5',
    created_by BIGINT NULL DEFAULT NULL COMMENT '创建人',
    created_date datetime NULL DEFAULT NULL COMMENT '创建时间',
    last_modified_by BIGINT NULL DEFAULT NULL COMMENT '修改人',
    last_modified_date datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (id)
);

/*
员工表
 */
DROP TABLE IF EXISTS hr_staff;

CREATE TABLE hr_staff
(
    id BIGINT NOT NULL COMMENT '员工ID',
    staff_code VARCHAR(50) NOT NULL UNIQUE COMMENT '员工编号',
    staff_name VARCHAR(50) NULL DEFAULT NULL COMMENT '员工姓名',
    dep_id BIGINT NULL DEFAULT NULL COMMENT '部门ID',
    company_state VARCHAR(50) NULL DEFAULT NULL COMMENT '在司状态',
    duty VARCHAR(50) NULL DEFAULT NULL COMMENT '职务',
    post VARCHAR(50) NULL DEFAULT NULL COMMENT '岗位',
    post_type VARCHAR(50) NULL DEFAULT NULL COMMENT '岗位类型',
    post_level VARCHAR(50) NULL DEFAULT NULL COMMENT '岗位等级',
    sex VARCHAR(10) NULL DEFAULT NULL COMMENT '性别',
    nation VARCHAR(10) NULL DEFAULT NULL COMMENT '民族',
    birthday date NULL DEFAULT NULL COMMENT '出生日期',
    work_date date NULL DEFAULT NULL COMMENT '开始工作时间',
    entry_date date NULL DEFAULT NULL COMMENT '入职日期',
    politics VARCHAR(50) NULL DEFAULT NULL COMMENT '政治面貌',
    education VARCHAR(50) NULL DEFAULT NULL COMMENT '最高学历',
    degree VARCHAR(50) NULL DEFAULT NULL COMMENT '学位',
    marital_status VARCHAR(50) NULL DEFAULT NULL COMMENT '婚姻状况',
    spouse_name VARCHAR(50) NULL DEFAULT NULL COMMENT '配偶名字',
    marriage_certificate VARCHAR(255) NULL DEFAULT NULL COMMENT '结婚证件',
    marriage_date date NULL DEFAULT NULL COMMENT '结婚日期',
    children_number INT NULL DEFAULT NULL COMMENT '子女人数',
    id_number VARCHAR(50) NULL DEFAULT NULL COMMENT '身份证号码',
    phone VARCHAR(30) NULL DEFAULT NULL COMMENT '联系电话',
    social_security_number VARCHAR(50) NULL DEFAULT NULL COMMENT '社保号',
    birth_address_province VARCHAR(50) NULL DEFAULT NULL COMMENT '出生地(省)',
    birth_address_city VARCHAR(50) NULL DEFAULT NULL COMMENT '出生地(市)',
    native_address_province VARCHAR(50) NULL DEFAULT NULL COMMENT '籍贯(省)',
    native_address_city VARCHAR(50) NULL DEFAULT NULL COMMENT '籍贯(市)',
    household_type VARCHAR(50) NULL DEFAULT NULL COMMENT '户口类型',
    registered_address_province VARCHAR(50) NULL DEFAULT NULL COMMENT '户口地址(省)',
    registered_address_city VARCHAR(50) NULL DEFAULT NULL COMMENT '户口地址(市)',
    registered_address_area VARCHAR(50) NULL DEFAULT NULL COMMENT '户口地址(区)',
    registered_address_detail VARCHAR(50) NULL DEFAULT NULL COMMENT '户口地址(详细)',
    home_address_province VARCHAR(50) NULL DEFAULT NULL COMMENT '家庭地址(省)',
    home_address_city VARCHAR(50) NULL DEFAULT NULL COMMENT '家庭地址(市)',
    home_address_area VARCHAR(50) NULL DEFAULT NULL COMMENT '家庭地址(区)',
    home_address_detail VARCHAR(50) NULL DEFAULT NULL COMMENT '家庭地址(详细)',
    current_address_province VARCHAR(50) NULL DEFAULT NULL COMMENT '现住地址(省)',
    current_address_city VARCHAR(50) NULL DEFAULT NULL COMMENT '现住地址(市)',
    current_address_area VARCHAR(50) NULL DEFAULT NULL COMMENT '现住地址(区)',
    current_address_detail VARCHAR(50) NULL DEFAULT NULL COMMENT '现住地址(详细)',
    postal_address_province VARCHAR(50) NULL DEFAULT NULL COMMENT '邮递地址(省)',
    postal_address_city VARCHAR(50) NULL DEFAULT NULL COMMENT '邮递地址(市)',
    postal_address_area VARCHAR(50) NULL DEFAULT NULL COMMENT '邮递地址(区)',
    postal_address_detail VARCHAR(50) NULL DEFAULT NULL COMMENT '邮递地址(详细)',
    contact_name VARCHAR(50) NULL DEFAULT NULL COMMENT '紧急联系人',
    contact_relation VARCHAR(50) NULL DEFAULT NULL COMMENT '紧急联系人关系',
    contact_phone VARCHAR(30) NULL DEFAULT NULL COMMENT '紧急联系人电话',
    created_by BIGINT NULL DEFAULT NULL COMMENT '创建人',
    created_date datetime NULL DEFAULT NULL COMMENT '创建时间',
    last_modified_by BIGINT NULL DEFAULT NULL COMMENT '修改人',
    last_modified_date datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (id)
);

/*
教育经历表
 */
DROP TABLE IF EXISTS hr_educational_experience;

CREATE TABLE hr_educational_experience
(
    id BIGINT NOT NULL COMMENT '主键ID',
    pid BIGINT NOT NULL COMMENT '关联id',
    school_name VARCHAR(50) NOT NULL COMMENT '学校',
    start_date date NULL DEFAULT NULL COMMENT '开始日期',
    end_date date NULL DEFAULT NULL COMMENT '结束日期',
    education VARCHAR(50) NULL DEFAULT NULL COMMENT '学历',
    major VARCHAR(50) NULL DEFAULT NULL COMMENT '专业',
    study_years INT NULL DEFAULT NULL COMMENT '学制',
    full_time bit(1) NULL DEFAULT NULL COMMENT '是否全日制',
    witness_name VARCHAR(50) NULL DEFAULT NULL COMMENT '证明人姓名',
    witness_phone VARCHAR(50) NULL DEFAULT NULL COMMENT '证明人电话',
    created_by BIGINT NULL DEFAULT NULL COMMENT '创建人',
    created_date datetime NULL DEFAULT NULL COMMENT '创建时间',
    last_modified_by BIGINT NULL DEFAULT NULL COMMENT '修改人',
    last_modified_date datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (id)
);

/*
工作履历表
 */
DROP TABLE IF EXISTS hr_work_experience;

CREATE TABLE hr_work_experience
(
    id BIGINT NOT NULL COMMENT '主键ID',
    pid BIGINT NOT NULL COMMENT '关联id',
    work_unit VARCHAR(50) NOT NULL COMMENT '工作单位',
    start_date date NULL DEFAULT NULL COMMENT '开始日期',
    end_date date NULL DEFAULT NULL COMMENT '结束日期',
    duty VARCHAR(50) NULL DEFAULT NULL COMMENT '职务/岗位',
    unit_type VARCHAR(50) NULL DEFAULT NULL COMMENT '单位性质',
    salary INT NULL DEFAULT NULL COMMENT '月薪',
    witness_name VARCHAR(50) NULL DEFAULT NULL COMMENT '证明人姓名',
    witness_phone VARCHAR(50) NULL DEFAULT NULL COMMENT '证明人电话',
    created_by BIGINT NULL DEFAULT NULL COMMENT '创建人',
    created_date datetime NULL DEFAULT NULL COMMENT '创建时间',
    last_modified_by BIGINT NULL DEFAULT NULL COMMENT '修改人',
    last_modified_date datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (id)
);


/*
家庭成员表
 */
DROP TABLE IF EXISTS hr_family;

CREATE TABLE hr_family
(
    id BIGINT NOT NULL COMMENT '主键ID',
    pid BIGINT NOT NULL COMMENT '关联id',
    name VARCHAR(50) NOT NULL COMMENT '姓名',
    relation VARCHAR(50) NULL DEFAULT NULL COMMENT '关系',
    birthday date NULL DEFAULT NULL COMMENT '出生日期',
    politics VARCHAR(50) NULL DEFAULT NULL COMMENT '政治面貌',
    work_unit VARCHAR(50) NOT NULL COMMENT '工作单位',
    duty VARCHAR(50) NULL DEFAULT NULL COMMENT '职务/岗位',
    mobile_phone VARCHAR(50) NULL DEFAULT NULL COMMENT '移动电话',
    landline_phone VARCHAR(50) NULL DEFAULT NULL COMMENT '固话',
    created_by BIGINT NULL DEFAULT NULL COMMENT '创建人',
    created_date datetime NULL DEFAULT NULL COMMENT '创建时间',
    last_modified_by BIGINT NULL DEFAULT NULL COMMENT '修改人',
    last_modified_date datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (id)
);

/*
职称表
 */
DROP TABLE IF EXISTS hr_certificate;

CREATE TABLE hr_certificate
(
    id BIGINT NOT NULL COMMENT '主键ID',
    pid BIGINT NOT NULL COMMENT '关联id',
    name VARCHAR(50) NOT NULL COMMENT '证件名称',
    type VARCHAR(50) NULL DEFAULT NULL COMMENT '证件类型',
    number VARCHAR(50) NULL DEFAULT NULL COMMENT '证件号',
    obtain_date date NULL DEFAULT NULL COMMENT '取证日期',
    issue_unit VARCHAR(255) NULL DEFAULT NULL COMMENT '职发证单位',
    created_by BIGINT NULL DEFAULT NULL COMMENT '创建人',
    created_date datetime NULL DEFAULT NULL COMMENT '创建时间',
    last_modified_by BIGINT NULL DEFAULT NULL COMMENT '修改人',
    last_modified_date datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (id)
);

/**
  人事调动表
 */
DROP TABLE IF EXISTS hr_transfer_record;

CREATE TABLE hr_transfer_record
(
    id BIGINT NOT NULL COMMENT '主键ID',
    staff_id BIGINT NOT NULL COMMENT '关联的员工id',
    pre_dep_id BIGINT NULL DEFAULT NULL COMMENT '变更前部门ID',
    post_dep_id BIGINT NULL DEFAULT NULL COMMENT '变更后部门ID',
    pre_duty VARCHAR(50) NULL DEFAULT NULL COMMENT '变更前职务',
    post_duty VARCHAR(50) NULL DEFAULT NULL COMMENT '变更后职务',
    pre_post VARCHAR(50) NULL DEFAULT NULL COMMENT '变更前岗位',
    post_post VARCHAR(50) NULL DEFAULT NULL COMMENT '变更后岗位',
    pre_post_type VARCHAR(50) NULL DEFAULT NULL COMMENT '变更前岗位类型',
    post_post_type VARCHAR(50) NULL DEFAULT NULL COMMENT '变更后岗位类型',
    pre_post_level VARCHAR(50) NULL DEFAULT NULL COMMENT '变更前岗位等级',
    post_post_level VARCHAR(50) NULL DEFAULT NULL COMMENT '变更后岗位等级',
    effective_date date NOT NULL COMMENT '生效日期',
    created_by BIGINT NULL DEFAULT NULL COMMENT '创建人',
    created_date datetime NULL DEFAULT NULL COMMENT '创建时间',
    last_modified_by BIGINT NULL DEFAULT NULL COMMENT '修改人',
    last_modified_date datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (id)
);

/*
简历表
 */
DROP TABLE IF EXISTS hr_resume;

CREATE TABLE hr_resume
(
    id BIGINT NOT NULL COMMENT '文件ID',
    name VARCHAR(50) NOT NULL COMMENT '姓名',
    sex VARCHAR(10) NULL DEFAULT NULL COMMENT '性别',
    nation VARCHAR(10) NULL DEFAULT NULL COMMENT '民族',
    native_address_province VARCHAR(50) NULL DEFAULT NULL COMMENT '籍贯(省)',
    native_address_city VARCHAR(50) NULL DEFAULT NULL COMMENT '籍贯(市)',
    id_number VARCHAR(50) NULL DEFAULT NULL COMMENT '身份证号码',
    birthday date NULL DEFAULT NULL COMMENT '出生日期',
    birth_address_province VARCHAR(50) NULL DEFAULT NULL COMMENT '出生地(省)',
    birth_address_city VARCHAR(50) NULL DEFAULT NULL COMMENT '出生地(市)',
    politics VARCHAR(50) NULL DEFAULT NULL COMMENT '政治面貌',
    education VARCHAR(50) NULL DEFAULT NULL COMMENT '最高学历',
    degree VARCHAR(50) NULL DEFAULT NULL COMMENT '学位',
    marital_status VARCHAR(50) NULL DEFAULT NULL COMMENT '婚姻状况',
    phone VARCHAR(50) NULL DEFAULT NULL COMMENT '联系电话',
    fertility VARCHAR(50) NULL DEFAULT NULL COMMENT '生育情况',
    children_number INT NULL DEFAULT NULL COMMENT '子女人数',
    email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    apply_for VARCHAR(50) NULL DEFAULT NULL COMMENT '应聘途径',
    qq VARCHAR(50) NULL DEFAULT NULL COMMENT 'qq',
    household_type VARCHAR(50) NULL DEFAULT NULL COMMENT '户口类型',
    registered_address_province VARCHAR(50) NULL DEFAULT NULL COMMENT '户口地址(省)',
    registered_address_city VARCHAR(50) NULL DEFAULT NULL COMMENT '户口地址(市)',
    registered_address_area VARCHAR(50) NULL DEFAULT NULL COMMENT '户口地址(区)',
    registered_address_detail VARCHAR(50) NULL DEFAULT NULL COMMENT '户口地址(详细)',
    home_address_province VARCHAR(50) NULL DEFAULT NULL COMMENT '家庭地址(省)',
    home_address_city VARCHAR(50) NULL DEFAULT NULL COMMENT '家庭地址(市)',
    home_address_area VARCHAR(50) NULL DEFAULT NULL COMMENT '家庭地址(区)',
    home_address_detail VARCHAR(50) NULL DEFAULT NULL COMMENT '家庭地址(详细)',
    current_address_province VARCHAR(50) NULL DEFAULT NULL COMMENT '现住地址(省)',
    current_address_city VARCHAR(50) NULL DEFAULT NULL COMMENT '现住地址(市)',
    current_address_area VARCHAR(50) NULL DEFAULT NULL COMMENT '现住地址(区)',
    current_address_detail VARCHAR(50) NULL DEFAULT NULL COMMENT '现住地址(详细)',
    postal_address_province VARCHAR(50) NULL DEFAULT NULL COMMENT '邮递地址(省)',
    postal_address_city VARCHAR(50) NULL DEFAULT NULL COMMENT '邮递地址(市)',
    postal_address_area VARCHAR(50) NULL DEFAULT NULL COMMENT '邮递地址(区)',
    postal_address_detail VARCHAR(50) NULL DEFAULT NULL COMMENT '邮递地址(详细)',
    expected_salary VARCHAR(50) NULL DEFAULT NULL COMMENT '期望月薪',
    service_years VARCHAR(50) NULL DEFAULT NULL COMMENT '希望服务年限',
    title VARCHAR(50) NULL DEFAULT NULL COMMENT '职称',
    specialty VARCHAR(50) NULL DEFAULT NULL COMMENT '特长',
    hobby VARCHAR(50) NULL DEFAULT NULL COMMENT '爱好',
    contact_name VARCHAR(50) NULL DEFAULT NULL COMMENT '紧急联系人',
    contact_relation VARCHAR(50) NULL DEFAULT NULL COMMENT '紧急联系人关系',
    contact_phone VARCHAR(50) NULL DEFAULT NULL COMMENT '紧急联系人电话',
    friend_name VARCHAR(50) NULL DEFAULT NULL COMMENT '亲友姓名',
    friend_relation VARCHAR(50) NULL DEFAULT NULL COMMENT '亲友关系',
    friend_department VARCHAR(50) NULL DEFAULT NULL COMMENT '亲友部门',
    friend_duty VARCHAR(50) NULL DEFAULT NULL COMMENT '亲友职务',
    parental_support VARCHAR(50) NULL DEFAULT NULL COMMENT '父母赡养情况',
    weight FLOAT NULL DEFAULT NULL COMMENT '体重kg',
    height FLOAT NULL DEFAULT NULL COMMENT '身高cm',
    vision FLOAT NULL DEFAULT NULL COMMENT '视力',
    blood_type VARCHAR(50) NULL DEFAULT NULL COMMENT '血型',
    medical_history VARCHAR(255) NULL DEFAULT NULL COMMENT '遗传病史或传染病',
    marriage_date date NULL DEFAULT NULL COMMENT '结婚日期',
    spouse_education VARCHAR(50) NULL DEFAULT NULL COMMENT '配偶学历',
    spouse_physical_condition VARCHAR(255) NULL DEFAULT NULL COMMENT '配偶身体状况',
    troop_base VARCHAR(255) NULL DEFAULT NULL COMMENT '部队驻扎地',
    enlistment_date date NULL DEFAULT NULL COMMENT '入伍时间',
    discharge_date date NULL DEFAULT NULL COMMENT '退伍时间',
    discharge_rank VARCHAR(255) NULL DEFAULT NULL COMMENT '退伍时军衔',
    honour VARCHAR(255) NULL DEFAULT NULL COMMENT '立功',
    driver_license_type VARCHAR(50) NULL DEFAULT NULL COMMENT '驾驶证类型',
    driver_license_date date NULL DEFAULT NULL COMMENT '驾驶证领证时间',
    drive_year INT NULL DEFAULT NULL COMMENT '驾龄',
    drive_lines VARCHAR(255) NULL DEFAULT NULL COMMENT '熟悉的驾驶路线',
    vehicle_type VARCHAR(50) NULL DEFAULT NULL COMMENT '驾驶车种',
    will_join bit(1) NULL DEFAULT NULL COMMENT '是否愿意加入人才库',
    created_by BIGINT NULL DEFAULT NULL COMMENT '创建人',
    created_date datetime NULL DEFAULT NULL COMMENT '创建时间',
    last_modified_by BIGINT NULL DEFAULT NULL COMMENT '修改人',
    last_modified_date datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (id)
);

















/*
初始数据插入
 */

/**
用户表初始数据
*/
INSERT INTO `sys_user`(`id`, `username`, `password`, `account_non_expired`, `account_non_locked`, `credentials_non_expired`, `enabled`, `name`, `nickname`, `sex`, `birthday`, `avatar`, `email`, `phone_number`, `address_code`, `detailed_address`, `introduction`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378349825706942465, 'admin', '$2a$10$ZHloNREZMCnmeSqGlPL4tudSt4QdR4JnFwODJnVsXoWoxAkNMaqda', b'1', b'1', b'1', b'1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

/**
角色表基础数据
 */
INSERT INTO `sys_role`(`id`, `name`, `code`, `description`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378349825706942466, '开发者', 'developer', '开发者', NULL, NULL, NULL, NULL);
INSERT INTO `sys_role`(`id`, `name`, `code`, `description`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378349825706942467, '管理员', 'admin', '管理员', NULL, NULL, NULL, NULL);

/**
用户-角色关联表基础数据
 */
INSERT INTO `sys_user_role`(`id`, `user_id`, `role_id`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378349825706942468, 1378349825706942465, 1378349825706942466, NULL, NULL, NULL, NULL);

/**
菜单表基础数据
 */
INSERT INTO `hrsdb`.`sys_menu` (`id`, `name`, `locale`, `path`, `pid`, `icon`, `priority`, `hide_in_menu`, `hide_children_in_menu`, `props`, `description`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378348387828137986, '首页', 'menu.index', '/index', NULL, 'SmileOutlined', 1, b'0', b'0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `hrsdb`.`sys_menu` (`id`, `name`, `locale`, `path`, `pid`, `icon`, `priority`, `hide_in_menu`, `hide_children_in_menu`, `props`, `description`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378348387828137987, '系统管理', 'menu.system', '/system', NULL, 'SmileOutlined', 2, b'0', b'0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `hrsdb`.`sys_menu` (`id`, `name`, `locale`, `path`, `pid`, `icon`, `priority`, `hide_in_menu`, `hide_children_in_menu`, `props`, `description`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378348387828137988, '用户管理', 'menu.system.user', '/system/user', 1378348387828137987, NULL, 1, b'0', b'0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `hrsdb`.`sys_menu` (`id`, `name`, `locale`, `path`, `pid`, `icon`, `priority`, `hide_in_menu`, `hide_children_in_menu`, `props`, `description`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378348387828137989, '角色管理', 'menu.system.role', '/system/role', 1378348387828137987, NULL, 1, b'0', b'0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `hrsdb`.`sys_menu` (`id`, `name`, `locale`, `path`, `pid`, `icon`, `priority`, `hide_in_menu`, `hide_children_in_menu`, `props`, `description`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378348387828137990, '菜单管理', 'menu.system.menu', '/system/menu', 1378348387828137987, NULL, 1, b'0', b'0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `hrsdb`.`sys_menu` (`id`, `name`, `locale`, `path`, `pid`, `icon`, `priority`, `hide_in_menu`, `hide_children_in_menu`, `props`, `description`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378348387828137991, '资源管理', 'menu.system.resource', '/system/resource', 1378348387828137987, NULL, 1, b'0', b'0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `hrsdb`.`sys_menu` (`id`, `name`, `locale`, `path`, `pid`, `icon`, `priority`, `hide_in_menu`, `hide_children_in_menu`, `props`, `description`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378348387828137992, '部门管理', 'menu.system.department', '/system/department', 1378348387828137987, NULL, 1, b'0', b'0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `hrsdb`.`sys_menu` (`id`, `name`, `locale`, `path`, `pid`, `icon`, `priority`, `hide_in_menu`, `hide_children_in_menu`, `props`, `description`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378348387828137993, '定时任务', 'menu.system.task', '/system/task', 1378348387828137987, NULL, 1, b'0', b'0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `hrsdb`.`sys_menu` (`id`, `name`, `locale`, `path`, `pid`, `icon`, `priority`, `hide_in_menu`, `hide_children_in_menu`, `props`, `description`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378348387828137994, '字典管理', 'menu.system.dictionary', '/system/dictionary', 1378348387828137987, NULL, 1, b'0', b'0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `hrsdb`.`sys_menu` (`id`, `name`, `locale`, `path`, `pid`, `icon`, `priority`, `hide_in_menu`, `hide_children_in_menu`, `props`, `description`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378348387828137995, '区域管理', 'menu.system.region', '/system/region', 1378348387828137987, NULL, 1, b'0', b'0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `hrsdb`.`sys_menu` (`id`, `name`, `locale`, `path`, `pid`, `icon`, `priority`, `hide_in_menu`, `hide_children_in_menu`, `props`, `description`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378348387828137996, '文件管理', 'menu.system.file', '/system/file', 1378348387828137987, NULL, 1, b'0', b'0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `hrsdb`.`sys_menu` (`id`, `name`, `locale`, `path`, `pid`, `icon`, `priority`, `hide_in_menu`, `hide_children_in_menu`, `props`, `description`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378348387828137997, '人事业务', 'menu.hr', '/hr', NULL, 'SmileOutlined', 3, b'0', b'0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `hrsdb`.`sys_menu` (`id`, `name`, `locale`, `path`, `pid`, `icon`, `priority`, `hide_in_menu`, `hide_children_in_menu`, `props`, `description`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378348387828137998, '员工管理', 'menu.hr.staff', '/hr/staff', 1378348387828137997, NULL, 1, b'0', b'0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `hrsdb`.`sys_menu` (`id`, `name`, `locale`, `path`, `pid`, `icon`, `priority`, `hide_in_menu`, `hide_children_in_menu`, `props`, `description`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378348387828137999, '调动记录', 'menu.hr.transfer-record', '/hr/transfer-record', 1378348387828137997, NULL, 2, b'0', b'0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `hrsdb`.`sys_menu` (`id`, `name`, `locale`, `path`, `pid`, `icon`, `priority`, `hide_in_menu`, `hide_children_in_menu`, `props`, `description`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378348387828138000, '招聘业务', 'menu.recruit', '/recruit', NULL, 'SmileOutlined', 4, b'0', b'0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `hrsdb`.`sys_menu` (`id`, `name`, `locale`, `path`, `pid`, `icon`, `priority`, `hide_in_menu`, `hide_children_in_menu`, `props`, `description`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378348387828138001, '简历库', 'menu.recruit.resume', '/recruit/resume', 1378348387828137999, 'SmileOutlined', 4, b'0', b'0', NULL, NULL, NULL, NULL, NULL, NULL);




/**
角色-菜单关联表基础数据
 */
INSERT INTO `sys_role_menu`(`id`, `role_id`, `menu_id`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378349825706942469, 1378349825706942466, 1378348387828137986, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_menu`(`id`, `role_id`, `menu_id`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378349825706942470, 1378349825706942466, 1378348387828137987, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_menu`(`id`, `role_id`, `menu_id`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378349825706942471, 1378349825706942466, 1378348387828137988, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_menu`(`id`, `role_id`, `menu_id`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378349825706942472, 1378349825706942466, 1378348387828137989, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_menu`(`id`, `role_id`, `menu_id`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378349825706942473, 1378349825706942466, 1378348387828137990, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_menu`(`id`, `role_id`, `menu_id`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378349825706942474, 1378349825706942466, 1378348387828137991, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_menu`(`id`, `role_id`, `menu_id`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378349825706942475, 1378349825706942466, 1378348387828137992, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_menu`(`id`, `role_id`, `menu_id`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378349825706942476, 1378349825706942466, 1378348387828137993, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_menu`(`id`, `role_id`, `menu_id`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378349825706942477, 1378349825706942466, 1378348387828137994, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_menu`(`id`, `role_id`, `menu_id`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378349825706942478, 1378349825706942466, 1378348387828137995, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_menu`(`id`, `role_id`, `menu_id`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378349825706942479, 1378349825706942466, 1378348387828137996, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_menu`(`id`, `role_id`, `menu_id`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378349825706942480, 1378349825706942466, 1378348387828137997, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_menu`(`id`, `role_id`, `menu_id`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378349825706942481, 1378349825706942466, 1378348387828137998, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_menu`(`id`, `role_id`, `menu_id`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378349825706942482, 1378349825706942466, 1378348387828137999, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_menu`(`id`, `role_id`, `menu_id`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378349825706942483, 1378349825706942466, 1378348387828138000, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_menu`(`id`, `role_id`, `menu_id`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378349825706942483, 1378349825706942466, 1378348387828138001, NULL, NULL, NULL, NULL);

