CREATE database server;

DROP TABLE tb_probe_memory;
DROP TABLE tb_probe_jvm;
DROP TABLE tb_probe_cpu;
DROP TABLE tb_probe_os;
DROP TABLE tb_probe_task_queue;
DROP TABLE tb_probe_info;

CREATE TABLE server.tb_probe_info
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    probe_id INT,
    status INT DEFAULT 1,
    create_time datetime default current_timestamp comment 'create time',
    update_time datetime default current_timestamp on update current_timestamp comment 'update time'
);

CREATE TABLE server.tb_probe_memory
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    probe_info_id INT,
    total BIGINT,
    used BIGINT,
    free BIGINT,
    create_time datetime default current_timestamp comment 'create time',
    update_time datetime default current_timestamp on update current_timestamp comment 'update time',
    CONSTRAINT fk_tb_probe_info_memory FOREIGN KEY (probe_info_id) REFERENCES server.tb_probe_info(id)
);

CREATE TABLE server.tb_probe_jvm
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    probe_info_id INT,
    total BIGINT,
    max BIGINT,
    free BIGINT,
    version TEXT NOT NULL ,
    home TEXT NOT NULL ,
    create_time datetime default current_timestamp comment 'create time',
    update_time datetime default current_timestamp on update current_timestamp comment 'update time',
    CONSTRAINT fk_tb_probe_info_jvm FOREIGN KEY (probe_info_id) REFERENCES server.tb_probe_info(id)
);

CREATE TABLE server.tb_probe_cpu
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    probe_info_id INT,
    cpu_num INT,
    total DECIMAL(10,2),
    sys DECIMAL(10,2),
    used DECIMAL(10,2),
    wait DECIMAL(10,2),
    free DECIMAL(10,2),
    create_time datetime default current_timestamp comment 'create time',
    update_time datetime default current_timestamp on update current_timestamp comment 'update time',
    CONSTRAINT fk_tb_probe_info_cpu FOREIGN KEY (probe_info_id) REFERENCES server.tb_probe_info(id)
);

CREATE TABLE server.tb_probe_os
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    probe_info_id INT,
    computer_name TEXT,
    computer_ip TEXT,
    user_dir TEXT,
    os_name TEXT,
    os_arch TEXT,
    create_time datetime default current_timestamp comment 'create time',
    update_time datetime default current_timestamp on update current_timestamp comment 'update time',
    CONSTRAINT fk_tb_probe_info_os FOREIGN KEY (probe_info_id) REFERENCES server.tb_probe_info(id)
);

CREATE TABLE server.tb_probe_task_queue (
    id INT PRIMARY KEY AUTO_INCREMENT,
    probe_info_id INT,
    probe_id INT,
    job_name TEXT,
    status INT,
    create_time datetime default current_timestamp comment 'create time',
    update_time datetime default current_timestamp on update current_timestamp comment 'update time',
    CONSTRAINT fk_tb_probe_info_task FOREIGN KEY (probe_info_id) REFERENCES server.tb_probe_info(id)
);








