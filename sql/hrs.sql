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
    express VARCHAR(255) NULL DEFAULT NULL COMMENT '字典表达值',
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
    sex VARCHAR(10) NULL DEFAULT NULL COMMENT '性别',
    birthday date NULL DEFAULT NULL COMMENT '出生日期',
    id_number VARCHAR(50) NULL DEFAULT NULL COMMENT '身份证号码',
    dep_id BIGINT NULL DEFAULT NULL COMMENT '部门ID',
    company_state VARCHAR(50) NULL DEFAULT NULL COMMENT '在司状态',
    duty VARCHAR(50) NULL DEFAULT NULL COMMENT '职务',
    post VARCHAR(50) NULL DEFAULT NULL COMMENT '岗位',
    post_type VARCHAR(50) NULL DEFAULT NULL COMMENT '岗位类型',
    post_level VARCHAR(50) NULL DEFAULT NULL COMMENT '岗位等级',
    work_date date NULL DEFAULT NULL COMMENT '开始工作时间',
    entry_date date NULL DEFAULT NULL COMMENT '入职日期',
    departure_date date NULL DEFAULT NULL COMMENT '离职日期',
    social_security_number VARCHAR(50) NULL DEFAULT NULL COMMENT '社保号',
    personal_photo BIGINT NULL DEFAULT NULL COMMENT '个人照片',
    labor_contract BIGINT NULL DEFAULT NULL COMMENT '劳动合同',
    created_by BIGINT NULL DEFAULT NULL COMMENT '创建人',
    created_date datetime NULL DEFAULT NULL COMMENT '创建时间',
    last_modified_by BIGINT NULL DEFAULT NULL COMMENT '修改人',
    last_modified_date datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (id)
);

/*
员工信息表
 */
DROP TABLE IF EXISTS hr_staff_info;

CREATE TABLE hr_staff_info
(
    id BIGINT NOT NULL COMMENT '员工ID',
    staff_id BIGINT NOT NULL UNIQUE COMMENT '关联的员工id',
    nation VARCHAR(10) NULL DEFAULT NULL COMMENT '民族',
    politics VARCHAR(50) NULL DEFAULT NULL COMMENT '政治面貌',
    education VARCHAR(50) NULL DEFAULT NULL COMMENT '最高学历',
    degree VARCHAR(50) NULL DEFAULT NULL COMMENT '学位',
    parental_support VARCHAR(50) NULL DEFAULT NULL COMMENT '父母赡养情况',
    physical_condition VARCHAR(50) NULL DEFAULT NULL COMMENT '本人身体状况',
    medical_history VARCHAR(255) NULL DEFAULT NULL COMMENT '遗传病史或传染病',
    weight FLOAT NULL DEFAULT NULL COMMENT '体重kg',
    height FLOAT NULL DEFAULT NULL COMMENT '身高cm',
    vision FLOAT NULL DEFAULT NULL COMMENT '视力',
    blood_type VARCHAR(50) NULL DEFAULT NULL COMMENT '血型',
    specialty VARCHAR(50) NULL DEFAULT NULL COMMENT '特长',
    hobby VARCHAR(50) NULL DEFAULT NULL COMMENT '爱好',
    phone VARCHAR(50) NULL DEFAULT NULL COMMENT '联系电话',
    home_phone VARCHAR(50) NULL DEFAULT NULL COMMENT '家庭电话',
    email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    qq VARCHAR(50) NULL DEFAULT NULL COMMENT 'QQ',
    native_address_province VARCHAR(50) NULL DEFAULT NULL COMMENT '籍贯(省)',
    native_address_city VARCHAR(50) NULL DEFAULT NULL COMMENT '籍贯(市)',
    birth_address_province VARCHAR(50) NULL DEFAULT NULL COMMENT '出生地(省)',
    birth_address_city VARCHAR(50) NULL DEFAULT NULL COMMENT '出生地(市)',
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
    emergency_contact_name VARCHAR(50) NULL DEFAULT NULL COMMENT '紧急联系人姓名',
    emergency_contact_relation VARCHAR(50) NULL DEFAULT NULL COMMENT '紧急联系人关系',
    emergency_contact_phone VARCHAR(30) NULL DEFAULT NULL COMMENT '紧急联系人电话',
    marital_status VARCHAR(50) NULL DEFAULT NULL COMMENT '婚姻状况',
    marriage_date date NULL DEFAULT NULL COMMENT '结婚日期',
    spouse_name VARCHAR(50) NULL DEFAULT NULL COMMENT '配偶名字',
    spouse_education VARCHAR(50) NULL DEFAULT NULL COMMENT '配偶学历',
    spouse_physical_condition VARCHAR(255) NULL DEFAULT NULL COMMENT '配偶身体状况',
    marriage_certificate BIGINT NULL DEFAULT NULL COMMENT '结婚证件',
    fertility VARCHAR(50) NULL DEFAULT NULL COMMENT '生育情况',
    children_number INT NULL DEFAULT NULL COMMENT '子女人数',
    in_army bit(1) NULL DEFAULT NULL COMMENT '是否服兵役',
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
    file_id BIGINT NULL DEFAULT NULL COMMENT '附件',
    created_by BIGINT NULL DEFAULT NULL COMMENT '创建人',
    created_date datetime NULL DEFAULT NULL COMMENT '创建时间',
    last_modified_by BIGINT NULL DEFAULT NULL COMMENT '修改人',
    last_modified_date datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (id)
);

/**
  人事调动表
 */
DROP TABLE IF EXISTS hr_staff_change;

CREATE TABLE hr_staff_change
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
    change_date date NOT NULL COMMENT '变更日期',
    executed bit(1) NOT NULL DEFAULT 0 COMMENT '是否已执行',
    remarks VARCHAR(255) NULL DEFAULT NULL COMMENT '备注',
    created_by BIGINT NULL DEFAULT NULL COMMENT '创建人',
    created_date datetime NULL DEFAULT NULL COMMENT '创建时间',
    last_modified_by BIGINT NULL DEFAULT NULL COMMENT '修改人',
    last_modified_date datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (id)
);


/**
 员工薪资表
*/
DROP TABLE IF EXISTS hr_salary_staff;

CREATE TABLE hr_salary_staff
(
    id BIGINT NOT NULL COMMENT '主键ID',
    staff_id BIGINT NOT NULL COMMENT '关联的员工id',
    basic_salary decimal(10, 5) NOT NULL DEFAULT 0 COMMENT '基础工资',
    post_salary decimal(10, 5) NOT NULL DEFAULT 0 COMMENT '岗位工资',
    accumulation_fund decimal(10, 5) NOT NULL DEFAULT 0 COMMENT '公积金基数',
    have_one_child_allowance bit(1) NOT NULL DEFAULT 0 COMMENT '是否享有独生子女津贴',
    safety_allowance VARCHAR(255) NULL DEFAULT NULL COMMENT '安全津贴档次',
    high_temperature_allowance VARCHAR(255) NULL DEFAULT NULL COMMENT '高温津贴档次',
    remarks VARCHAR(255) NULL DEFAULT NULL COMMENT '备注',
    created_by BIGINT NULL DEFAULT NULL COMMENT '创建人',
    created_date datetime NULL DEFAULT NULL COMMENT '创建时间',
    last_modified_by BIGINT NULL DEFAULT NULL COMMENT '修改人',
    last_modified_date datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (id)
);


/**
  薪资变动表
 */
DROP TABLE IF EXISTS hr_salary_change;

CREATE TABLE hr_salary_change
(
    id BIGINT NOT NULL COMMENT '主键ID',
    staff_id BIGINT NOT NULL COMMENT '关联的员工id',
    pre_basic_salary decimal(10, 5) NULL DEFAULT NULL COMMENT '变更前基础工资',
    post_basic_salary decimal(10, 5) NULL DEFAULT NULL COMMENT '变更后基础工资',
    pre_post_salary decimal(10, 5) NULL DEFAULT NULL COMMENT '变更前岗位工资',
    post_post_salary decimal(10, 5) NULL DEFAULT NULL COMMENT '变更后岗位工资',
    pre_accumulation_fund decimal(10, 5) NOT NULL DEFAULT 0 COMMENT '变更前公积金基数',
    post_accumulation_fund decimal(10, 5) NOT NULL DEFAULT 0 COMMENT '变更后公积金基数',
    pre_have_one_child_allowance bit(1) NOT NULL DEFAULT 0 COMMENT '变更前是否享有独生子女津贴',
    post_have_one_child_allowance bit(1) NOT NULL DEFAULT 0 COMMENT '变更后是否享有独生子女津贴',
    pre_safety_allowance VARCHAR(255) NULL DEFAULT NULL COMMENT '变更前安全津贴档次',
    post_safety_allowance VARCHAR(255) NULL DEFAULT NULL COMMENT '变更后安全津贴档次',
    pre_high_temperature_allowance VARCHAR(255) NULL DEFAULT NULL COMMENT '变更前高温津贴档次',
    post_high_temperature_allowance VARCHAR(255) NULL DEFAULT NULL COMMENT '变更后高温津贴档次',
    effective_date date NOT NULL COMMENT '生效日期',
    change_date date NOT NULL COMMENT '变更日期',
    executed bit(1) NOT NULL DEFAULT 0 COMMENT '是否已执行',
    remarks VARCHAR(255) NULL DEFAULT NULL COMMENT '备注',
    created_by BIGINT NULL DEFAULT NULL COMMENT '创建人',
    created_date datetime NULL DEFAULT NULL COMMENT '创建时间',
    last_modified_by BIGINT NULL DEFAULT NULL COMMENT '修改人',
    last_modified_date datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (id)
);

/**
  薪资表
 */
DROP TABLE IF EXISTS hr_allowance;

CREATE TABLE hr_allowance
(
    id BIGINT NOT NULL COMMENT '主键ID',
    staff_id BIGINT NOT NULL COMMENT '关联的员工id',
    month date NOT NULL COMMENT '发放月份',
    communication_allowance decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '通讯补贴',
    other_allowance decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '其他补贴',
    birthday_card decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '生日卡',
    cool_drink decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '清凉饮料',
    condolence_goods decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '慰问品',
    rent decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '房租',
    phone_bill decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '话费',
    remarks VARCHAR(255) NULL DEFAULT NULL COMMENT '备注',
    created_by BIGINT NULL DEFAULT NULL COMMENT '创建人',
    created_date datetime NULL DEFAULT NULL COMMENT '创建时间',
    last_modified_by BIGINT NULL DEFAULT NULL COMMENT '修改人',
    last_modified_date datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (id)
);


/**
  薪资表
 */
DROP TABLE IF EXISTS hr_salary;

CREATE TABLE hr_salary
(
    id BIGINT NOT NULL COMMENT '主键ID',
    staff_id BIGINT NOT NULL COMMENT '关联的员工id',
    month date NOT NULL COMMENT '发放月份',
    type VARCHAR(255) NULL DEFAULT NULL COMMENT '发薪类型（工资、奖金）',
    appraise VARCHAR(255) NULL DEFAULT NULL COMMENT '考核等级',
    freeze bit(1) NOT NULL DEFAULT 0 COMMENT '是否冻结',
    basic_salary decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '基础工资',
    post_salary decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '岗位工资',
    merit_salary decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '绩效工资',
    sick_salary decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '病假工资',
    back_salary decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '补发工资',
    annual_bonus decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '年终奖',
    safety_bonus decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '安全奖',
    stability_bonus decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '综治奖',
    family_planning_bonus decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '计生奖',
    excellence_bonus decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '先进奖',
    special_bonus decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '专项奖',
    one_child_allowance decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '独生子女津贴',
    hot_weather_allowance decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '高温津贴',
    full_attendance_allowance decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '全勤津贴',
    night_shift_allowance decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '夜班津贴',
    on_duty_allowance decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '值班补贴',
    meal_allowance decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '就餐补贴',
    traffic_allowance decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '交通补贴',
    festival_allowance decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '节日慰问金',
    safety_allowance decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '安全岗岗位津贴',
    communication_allowance decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '通讯补贴',
    other_allowance decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '其他',
    sick_leave_deduct decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '扣病假工资',
    entry_exit_deduct decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '扣试用期/入离职结算',
    full_attendance_deduct decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '扣全勤',
    merit_deduct decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '扣季度绩效',
    birthday_card decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '生日卡',
    cool_drink decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '清凉饮料',
    condolence_goods decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '慰问品',
    accumulation_fund decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '公积金',
    endowment_insurance decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '养老保险',
    medical_insurance decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '医疗保险',
    union_fees decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '工会费',
    rent decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '房租',
    phone_bill decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '话费',
    individual_income_tax decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '个税',
    other_aft_tax_deduct decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '其他税后应扣',
    should_salary decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '应发工资',
    actual_salary decimal(10, 5) NOT NULL DEFAULT 0 NULL COMMENT '实发工资',
    remarks VARCHAR(255) NULL DEFAULT NULL COMMENT '备注',
    created_by BIGINT NULL DEFAULT NULL COMMENT '创建人',
    created_date datetime NULL DEFAULT NULL COMMENT '创建时间',
    last_modified_by BIGINT NULL DEFAULT NULL COMMENT '修改人',
    last_modified_date datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (id)
);


/**
  薪资表
 */
DROP TABLE IF EXISTS hr_salary_setting;

CREATE TABLE hr_salary_setting
(
    id BIGINT NOT NULL COMMENT '主键ID',
    merit_salary decimal(10, 5) NOT NULL DEFAULT 0 COMMENT '绩效工资基数',
    merit_a int NOT NULL DEFAULT 0 COMMENT '绩效A发放比例',
    merit_b int NOT NULL DEFAULT 0 COMMENT '绩效B发放比例',
    merit_c int NOT NULL DEFAULT 0 COMMENT '绩效C发放比例',
    merit_d int NOT NULL DEFAULT 0 COMMENT '绩效D发放比例',
    merit_e int NOT NULL DEFAULT 0 COMMENT '绩效E发放比例',
    merit_f int NOT NULL DEFAULT 0 COMMENT '绩效F发放比例',
    full_attendance_allowance decimal(10, 5) NOT NULL DEFAULT 0 COMMENT '全勤津贴标准（元/月）',
    meal_allowance decimal(10, 5) NOT NULL DEFAULT 0 COMMENT '就餐补贴（元/月）',
    traffic_allowance_own_a decimal(10, 5) NOT NULL DEFAULT 0 COMMENT '交通补贴（自行到达）_a（元/月）',
    traffic_allowance_own_b decimal(10, 5) NOT NULL DEFAULT 0 COMMENT '交通补贴（自行到达）_b（元/月）',
    traffic_allowance_own_c decimal(10, 5) NOT NULL DEFAULT 0 COMMENT '交通补贴（自行到达）_c（元/月）',
    traffic_allowance_bus_a decimal(10, 5) NOT NULL DEFAULT 0 COMMENT '交通补贴（乘坐接驳车）_a（元/月）',
    traffic_allowance_bus_b decimal(10, 5) NOT NULL DEFAULT 0 COMMENT '交通补贴（乘坐接驳车）_b（元/月）',
    traffic_allowance_bus_c decimal(10, 5) NOT NULL DEFAULT 0 COMMENT '交通补贴（乘坐接驳车）_c（元/月）',
    safety_allowance_a decimal(10, 5) NOT NULL DEFAULT 0 COMMENT '安全岗岗位津贴_a',
    safety_allowance_b decimal(10, 5) NOT NULL DEFAULT 0 COMMENT '安全岗岗位津贴_b',
    safety_allowance_c decimal(10, 5) NOT NULL DEFAULT 0 COMMENT '安全岗岗位津贴_c',
    one_child_allowance decimal(10, 5) NOT NULL DEFAULT 0 COMMENT '独生子女津贴标准（元/天）',
    high_temperature_start_date date NOT NULL COMMENT '高温津贴开始生效月份',
    high_temperature_end_date date NOT NULL COMMENT '高温津贴结束生效月份',
    high_temperature_allowance_a decimal(10, 5) NOT NULL DEFAULT 0 COMMENT '高温津贴_a标准',
    high_temperature_allowance_b decimal(10, 5) NOT NULL DEFAULT 0 COMMENT '高温津贴_b标准',
    high_temperature_allowance_c decimal(10, 5) NOT NULL DEFAULT 0 COMMENT '高温津贴_c标准',
    duty_week_fee decimal(10, 5) NOT NULL DEFAULT 0 COMMENT '值班费（工作日）（元/天）',
    duty_before_week_fee decimal(10, 5) NOT NULL DEFAULT 0 COMMENT '值班费（休息日前一天）（元/天）',
    duty_before_festival_fee decimal(10, 5) NOT NULL DEFAULT 0 COMMENT '值班费（法定节假日前一天）（元/天）',
    duty_weekend_fee decimal(10, 5) NOT NULL DEFAULT 0 COMMENT '值班费（休息日）（元/天）',
    duty_festival_fee decimal(10, 5) NOT NULL DEFAULT 0 COMMENT '值班费（法定节假日（春节假期除外））（元/天）',
    duty_out_spring_fee decimal(10, 5) NOT NULL DEFAULT 0 COMMENT '值班费（春节假期（不含除夕、初一、初二））（元/天）',
    duty_in_spring_fee decimal(10, 5) NOT NULL DEFAULT 0 COMMENT '值班费（春节假期（除夕、初一、初二））（元/天）',
    union_fees decimal(10, 5) NOT NULL DEFAULT 0 COMMENT '工会费',
    remarks VARCHAR(255) NULL DEFAULT NULL COMMENT '备注',
    created_by BIGINT NULL DEFAULT NULL COMMENT '创建人',
    created_date datetime NULL DEFAULT NULL COMMENT '创建时间',
    last_modified_by BIGINT NULL DEFAULT NULL COMMENT '修改人',
    last_modified_date datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (id)
);


/**
  绩效表
 */
DROP TABLE IF EXISTS hr_performance;

CREATE TABLE hr_performance
(
    id BIGINT NOT NULL COMMENT '主键ID',
    staff_id BIGINT NOT NULL COMMENT '关联的员工id',
    year int NULL DEFAULT NULL COMMENT '考核年度',
    quarter int NULL DEFAULT NULL COMMENT '考核季度',
    appraise VARCHAR(255) NULL DEFAULT NULL COMMENT '考核等级',
    remarks VARCHAR(255) NULL DEFAULT NULL COMMENT '备注',
    created_by BIGINT NULL DEFAULT NULL COMMENT '创建人',
    created_date datetime NULL DEFAULT NULL COMMENT '创建时间',
    last_modified_by BIGINT NULL DEFAULT NULL COMMENT '修改人',
    last_modified_date datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (id)
);

/**
  绩效设置表
 */
DROP TABLE IF EXISTS hr_performance_setting;

CREATE TABLE hr_performance_setting
(
    id BIGINT NOT NULL COMMENT '主键ID',
    name VARCHAR(255) NOT NULL COMMENT '名称',
    start_month date NULL DEFAULT NULL COMMENT '开始考核月份',
    end_month date NULL DEFAULT NULL COMMENT '结束考核月份',
    year int NULL DEFAULT NULL COMMENT '考核年度',
    quarter int NULL DEFAULT NULL COMMENT '考核季度',
    remarks VARCHAR(255) NULL DEFAULT NULL COMMENT '备注',
    created_by BIGINT NULL DEFAULT NULL COMMENT '创建人',
    created_date datetime NULL DEFAULT NULL COMMENT '创建时间',
    last_modified_by BIGINT NULL DEFAULT NULL COMMENT '修改人',
    last_modified_date datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (id)
);

/**
  月度考勤表
 */
DROP TABLE IF EXISTS hr_attendance_month;

CREATE TABLE hr_attendance_month
(
    id BIGINT NOT NULL COMMENT '主键ID',
    staff_id BIGINT NOT NULL COMMENT '关联的员工id',
    month date NOT NULL COMMENT '考勤月份',
    should_attendance_days int NULL DEFAULT 0 COMMENT '应出勤天数',
    actual_attendance_days float NULL DEFAULT 0 COMMENT '实出勤天数',
    absenteeism_days float NULL DEFAULT 0 COMMENT '旷工天数',
    travel_days float NULL DEFAULT 0 COMMENT '出差天数',
    out_days float NULL DEFAULT 0 COMMENT '外出天数',
    late_times int NULL DEFAULT 0 COMMENT '迟到次数',
    late_minutes int NULL DEFAULT 0 COMMENT '迟到分钟数',
    leave_early_times int NULL DEFAULT 0 COMMENT '早退次数',
    leave_early_minutes int NULL DEFAULT 0 COMMENT '迟到分钟数',
    card_miss_times int NULL DEFAULT 0 COMMENT '缺卡次数',
    sign_card_times int NULL DEFAULT 0 COMMENT '签卡次数',
    overtime_week_hours float NULL DEFAULT 0 COMMENT '平时加班时数',
    overtime_weekend_hours float NULL DEFAULT 0 COMMENT '周末加班时数',
    overtime_festival_hours float NULL DEFAULT 0 COMMENT '节日加班时数',
    annual_leave_days float NULL DEFAULT 0 COMMENT '年假天数',
    compensatory_leave_days float NULL DEFAULT 0 COMMENT '调休假天数',
    family_planning_leave_days float NULL DEFAULT 0 COMMENT '计生假天数',
    maternity_leave_days float NULL DEFAULT 0 COMMENT '产假天数',
    holiday_leave_days float NULL DEFAULT 0 COMMENT '节假日请假天数',
    sick_leave_days float NULL DEFAULT 0 COMMENT '病假天数',
    absence_leave_days float NULL DEFAULT 0 COMMENT '事假天数',
    exceptional_case_days float NULL DEFAULT 0 COMMENT '特殊情况请假天数',
    injury_leave_days float NULL DEFAULT 0 COMMENT '工伤假天数',
    marriage_leave_days float NULL DEFAULT 0 COMMENT '婚假天数',
    lactation_leave_days float NULL DEFAULT 0 COMMENT '哺乳假天数',
    only_child_leave_days float NULL DEFAULT 0 COMMENT '独生子女父母陪护假天数',
    nursing_leave float NULL DEFAULT 0 COMMENT '看护假天数',
    bereavement_leave float NULL DEFAULT 0 COMMENT '丧假天数',
    duty_week int NULL DEFAULT 0 COMMENT '值班（工作日）天数',
    duty_before_week int NULL DEFAULT 0 COMMENT '值班（休息日前一天）天数',
    duty_before_festival int NULL DEFAULT 0 COMMENT '值班（法定节假日前一天）天数',
    duty_weekend int NULL DEFAULT 0 COMMENT '值班（休息日）天数',
    duty_festival int NULL DEFAULT 0 COMMENT '值班（法定节假日（春节假期除外））天数',
    duty_out_spring int NULL DEFAULT 0 COMMENT '值班（春节假期（不含除夕、初一、初二））天数',
    duty_in_spring int NULL DEFAULT 0 COMMENT '值班（春节假期（除夕、初一、初二））天数',
    remarks VARCHAR(255) NULL DEFAULT NULL COMMENT '备注',
    created_by BIGINT NULL DEFAULT NULL COMMENT '创建人',
    created_date datetime NULL DEFAULT NULL COMMENT '创建时间',
    last_modified_by BIGINT NULL DEFAULT NULL COMMENT '修改人',
    last_modified_date datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (id)
);

/**
  季度考勤表
 */
DROP TABLE IF EXISTS hr_attendance_quarter;

CREATE TABLE hr_attendance_quarter
(
    id BIGINT NOT NULL COMMENT '主键ID',
    staff_id BIGINT NOT NULL COMMENT '关联的员工id',
    year int NOT NULL COMMENT '考勤年度',
    quarter int NOT NULL COMMENT '考勤季度',
    should_attendance_days int NULL DEFAULT 0 COMMENT '应出勤天数',
    actual_attendance_days float NULL DEFAULT 0 COMMENT '实出勤天数',
    freeze bit(1) NOT NULL DEFAULT 0 COMMENT '是否冻结',
    remarks VARCHAR(255) NULL DEFAULT NULL COMMENT '备注',
    created_by BIGINT NULL DEFAULT NULL COMMENT '创建人',
    created_date datetime NULL DEFAULT NULL COMMENT '创建时间',
    last_modified_by BIGINT NULL DEFAULT NULL COMMENT '修改人',
    last_modified_date datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (id)
);

/**
简历表
 */
DROP TABLE IF EXISTS hr_resume;

CREATE TABLE hr_resume
(
    id BIGINT NOT NULL COMMENT '简历ID',
    wechat_id VARCHAR(50) NOT NULL COMMENT '微信id',
    name VARCHAR(50) NOT NULL COMMENT '姓名',
    personal_photo BIGINT NULL DEFAULT NULL COMMENT '个人照片',
    sex VARCHAR(10) NULL DEFAULT NULL COMMENT '性别',
    nation VARCHAR(10) NULL DEFAULT NULL COMMENT '民族',
    birthday date NULL DEFAULT NULL COMMENT '出生日期',
    id_number VARCHAR(50) NULL DEFAULT NULL COMMENT '身份证号码',
    politics VARCHAR(50) NULL DEFAULT NULL COMMENT '政治面貌',
    education VARCHAR(50) NULL DEFAULT NULL COMMENT '最高学历',
    degree VARCHAR(50) NULL DEFAULT NULL COMMENT '学位',
    phone VARCHAR(50) NULL DEFAULT NULL COMMENT '联系电话',
    home_phone VARCHAR(50) NULL DEFAULT NULL COMMENT '家庭电话',
    email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    qq VARCHAR(50) NULL DEFAULT NULL COMMENT 'QQ',
    native_address_province VARCHAR(50) NULL DEFAULT NULL COMMENT '籍贯(省)',
    native_address_city VARCHAR(50) NULL DEFAULT NULL COMMENT '籍贯(市)',
    birth_address_province VARCHAR(50) NULL DEFAULT NULL COMMENT '出生地(省)',
    birth_address_city VARCHAR(50) NULL DEFAULT NULL COMMENT '出生地(市)',
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
    apply_for VARCHAR(50) NULL DEFAULT NULL COMMENT '应聘途径',
    expected_salary VARCHAR(50) NULL DEFAULT NULL COMMENT '期望月薪',
    service_years VARCHAR(50) NULL DEFAULT NULL COMMENT '希望服务年限',
    title VARCHAR(50) NULL DEFAULT NULL COMMENT '职称',
    emergency_contact_name VARCHAR(50) NULL DEFAULT NULL COMMENT '紧急联系人',
    emergency_contact_relation VARCHAR(50) NULL DEFAULT NULL COMMENT '紧急联系人关系',
    emergency_contact_phone VARCHAR(50) NULL DEFAULT NULL COMMENT '紧急联系人电话',
    parental_support VARCHAR(50) NULL DEFAULT NULL COMMENT '父母赡养情况',
    physical_condition VARCHAR(50) NULL DEFAULT NULL COMMENT '本人身体状况',
    medical_history VARCHAR(255) NULL DEFAULT NULL COMMENT '遗传病史或传染病',
    weight FLOAT NULL DEFAULT NULL COMMENT '体重kg',
    height FLOAT NULL DEFAULT NULL COMMENT '身高cm',
    vision FLOAT NULL DEFAULT NULL COMMENT '视力',
    blood_type VARCHAR(50) NULL DEFAULT NULL COMMENT '血型',
    specialty VARCHAR(50) NULL DEFAULT NULL COMMENT '特长',
    hobby VARCHAR(50) NULL DEFAULT NULL COMMENT '爱好',
    have_friend bit(1) NULL DEFAULT NULL COMMENT '是否有亲友在司',
    friend_name VARCHAR(50) NULL DEFAULT NULL COMMENT '亲友姓名',
    friend_relation VARCHAR(50) NULL DEFAULT NULL COMMENT '亲友关系',
    friend_department VARCHAR(50) NULL DEFAULT NULL COMMENT '亲友部门',
    friend_duty VARCHAR(50) NULL DEFAULT NULL COMMENT '亲友职务',
    marital_status VARCHAR(50) NULL DEFAULT NULL COMMENT '婚姻状况',
    marriage_date date NULL DEFAULT NULL COMMENT '结婚日期',
    spouse_name VARCHAR(50) NULL DEFAULT NULL COMMENT '配偶姓名',
    spouse_education VARCHAR(50) NULL DEFAULT NULL COMMENT '配偶学历',
    spouse_physical_condition VARCHAR(255) NULL DEFAULT NULL COMMENT '配偶身体状况',
    marriage_certificate BIGINT NULL DEFAULT NULL COMMENT '结婚证件',
    fertility VARCHAR(50) NULL DEFAULT NULL COMMENT '生育情况',
    children_number INT NULL DEFAULT NULL COMMENT '子女人数',
    in_army bit(1) NULL DEFAULT NULL COMMENT '是否服兵役',
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
INSERT INTO `hrsdb`.`sys_menu` (`id`, `name`, `locale`, `path`, `pid`, `icon`, `priority`, `hide_in_menu`, `hide_children_in_menu`, `props`, `description`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378348387828137999, '调动记录', 'menu.hr.staff-change', '/hr/staff-change', 1378348387828137997, NULL, 2, b'0', b'0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `hrsdb`.`sys_menu` (`id`, `name`, `locale`, `path`, `pid`, `icon`, `priority`, `hide_in_menu`, `hide_children_in_menu`, `props`, `description`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378348387828138001, '薪资管理', 'menu.salary', '/salary', NULL, 'SmileOutlined', 4, b'0', b'0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `hrsdb`.`sys_menu` (`id`, `name`, `locale`, `path`, `pid`, `icon`, `priority`, `hide_in_menu`, `hide_children_in_menu`, `props`, `description`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378348387828138002, '员工薪资', 'menu.salary.salary-staff', '/salary/salary-staff', 1378348387828138001, NULL, 1, b'0', b'0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `hrsdb`.`sys_menu` (`id`, `name`, `locale`, `path`, `pid`, `icon`, `priority`, `hide_in_menu`, `hide_children_in_menu`, `props`, `description`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378348387828138003, '调薪记录', 'menu.salary.salary-change', '/salary/salary-change', 1378348387828138001, NULL, 2, b'0', b'0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `hrsdb`.`sys_menu` (`id`, `name`, `locale`, `path`, `pid`, `icon`, `priority`, `hide_in_menu`, `hide_children_in_menu`, `props`, `description`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378348387828138004, '薪资发放', 'menu.salary.salary', '/salary/salary', 1378348387828138001, NULL, 3, b'0', b'0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `hrsdb`.`sys_menu` (`id`, `name`, `locale`, `path`, `pid`, `icon`, `priority`, `hide_in_menu`, `hide_children_in_menu`, `props`, `description`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378348387828138005, '薪资发放', 'menu.salary.allowance', '/salary/allowance', 1378348387828138001, NULL, 4, b'0', b'0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `hrsdb`.`sys_menu` (`id`, `name`, `locale`, `path`, `pid`, `icon`, `priority`, `hide_in_menu`, `hide_children_in_menu`, `props`, `description`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378348387828138006, '薪资配置', 'menu.salary.salary-setting', '/salary/salary-setting', 1378348387828138001, NULL, 5, b'0', b'0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `hrsdb`.`sys_menu` (`id`, `name`, `locale`, `path`, `pid`, `icon`, `priority`, `hide_in_menu`, `hide_children_in_menu`, `props`, `description`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378348387828138007, '绩效管理', 'menu.performance', '/performance', NULL, 'SmileOutlined', 5, b'0', b'0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `hrsdb`.`sys_menu` (`id`, `name`, `locale`, `path`, `pid`, `icon`, `priority`, `hide_in_menu`, `hide_children_in_menu`, `props`, `description`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378348387828138008, '绩效评价', 'menu.performance.performance', '/performance/performance', 1378348387828138007, NULL, 1, b'0', b'0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `hrsdb`.`sys_menu` (`id`, `name`, `locale`, `path`, `pid`, `icon`, `priority`, `hide_in_menu`, `hide_children_in_menu`, `props`, `description`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378348387828138009, '绩效设置', 'menu.performance.performance-setting', '/performance/performance-setting', 1378348387828138007, NULL, 2, b'0', b'0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `hrsdb`.`sys_menu` (`id`, `name`, `locale`, `path`, `pid`, `icon`, `priority`, `hide_in_menu`, `hide_children_in_menu`, `props`, `description`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378348387828138010, '考勤管理', 'menu.attendance', '/attendance', NULL, 'SmileOutlined', 6, b'0', b'0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `hrsdb`.`sys_menu` (`id`, `name`, `locale`, `path`, `pid`, `icon`, `priority`, `hide_in_menu`, `hide_children_in_menu`, `props`, `description`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378348387828138011, '月度考勤', 'menu.attendance.attendance-month', '/attendance/attendance-month', 1378348387828138010, NULL, 1, b'0', b'0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `hrsdb`.`sys_menu` (`id`, `name`, `locale`, `path`, `pid`, `icon`, `priority`, `hide_in_menu`, `hide_children_in_menu`, `props`, `description`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378348387828138012, '季度考勤', 'menu.attendance.attendance-quarter', '/attendance/attendance-quarter', 1378348387828138010, NULL, 2, b'0', b'0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `hrsdb`.`sys_menu` (`id`, `name`, `locale`, `path`, `pid`, `icon`, `priority`, `hide_in_menu`, `hide_children_in_menu`, `props`, `description`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378348387828138013, '招聘业务', 'menu.recruit', '/recruit', NULL, 'SmileOutlined', 7, b'0', b'0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `hrsdb`.`sys_menu` (`id`, `name`, `locale`, `path`, `pid`, `icon`, `priority`, `hide_in_menu`, `hide_children_in_menu`, `props`, `description`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378348387828138014, '简历库', 'menu.recruit.resume', '/recruit/resume', 1378348387828138013, NULL, 1, b'0', b'0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `hrsdb`.`sys_menu` (`id`, `name`, `locale`, `path`, `pid`, `icon`, `priority`, `hide_in_menu`, `hide_children_in_menu`, `props`, `description`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378348387828138015, '招聘维护', 'menu.recruit.maintain', '/recruit/maintain', 1378348387828138013, NULL, 2, b'0', b'0', NULL, NULL, NULL, NULL, NULL, NULL);

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
INSERT INTO `sys_role_menu`(`id`, `role_id`, `menu_id`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378349825706942484, 1378349825706942466, 1378348387828138001, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_menu`(`id`, `role_id`, `menu_id`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378349825706942485, 1378349825706942466, 1378348387828138002, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_menu`(`id`, `role_id`, `menu_id`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378349825706942486, 1378349825706942466, 1378348387828138003, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_menu`(`id`, `role_id`, `menu_id`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378349825706942487, 1378349825706942466, 1378348387828138004, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_menu`(`id`, `role_id`, `menu_id`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378349825706942488, 1378349825706942466, 1378348387828138005, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_menu`(`id`, `role_id`, `menu_id`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378349825706942489, 1378349825706942466, 1378348387828138006, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_menu`(`id`, `role_id`, `menu_id`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378349825706942490, 1378349825706942466, 1378348387828138007, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_menu`(`id`, `role_id`, `menu_id`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378349825706942491, 1378349825706942466, 1378348387828138008, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_menu`(`id`, `role_id`, `menu_id`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378349825706942492, 1378349825706942466, 1378348387828138009, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_menu`(`id`, `role_id`, `menu_id`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378349825706942493, 1378349825706942466, 1378348387828138010, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_menu`(`id`, `role_id`, `menu_id`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378349825706942494, 1378349825706942466, 1378348387828138011, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_menu`(`id`, `role_id`, `menu_id`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378349825706942495, 1378349825706942466, 1378348387828138012, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_menu`(`id`, `role_id`, `menu_id`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378349825706942496, 1378349825706942466, 1378348387828138013, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_menu`(`id`, `role_id`, `menu_id`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378349825706942497, 1378349825706942466, 1378348387828138014, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_menu`(`id`, `role_id`, `menu_id`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378349825706942498, 1378349825706942466, 1378348387828138015, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_menu`(`id`, `role_id`, `menu_id`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378349825706942499, 1378349825706942466, 1378348387828138016, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_menu`(`id`, `role_id`, `menu_id`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378349825706942500, 1378349825706942466, 1378348387828138017, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_menu`(`id`, `role_id`, `menu_id`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1378349825706942501, 1378349825706942466, 1378348387828138018, NULL, NULL, NULL, NULL);

/**
  字典表数据
 */
