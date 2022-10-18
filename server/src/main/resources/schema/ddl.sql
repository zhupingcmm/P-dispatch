CREATE database server;

DROP TABLE tb_dispatch_metric;
DROP TABLE tb_probe_task_queue;
DROP TABLE tb_probe_info;

CREATE TABLE server.tb_probe_info
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT UNIQUE NOT NULL,
    probe_id INT UNIQUE NOT NULL,
    status INT DEFAULT 0,
    create_time datetime default current_timestamp comment 'create time',
    update_time datetime default current_timestamp on update current_timestamp comment 'update time'
);

CREATE TABLE server.tb_probe_task_queue (
    id INT PRIMARY KEY AUTO_INCREMENT,
    probe_info_id INT,
    probe_id INT,
    customer_id INT,
    task_name TEXT,
    status INT DEFAULT 0,
    create_time datetime default current_timestamp comment 'create time',
    update_time datetime default current_timestamp on update current_timestamp comment 'update time',
    CONSTRAINT fk_tb_probe_info_task FOREIGN KEY (probe_info_id) REFERENCES server.tb_probe_info(id)
);

CREATE TABLE server.tb_dispatch_metric (
           id INT PRIMARY KEY AUTO_INCREMENT,
           probe_info_id INT,
           name TEXT,
           use_ratio DECIMAL(10,2),
           create_time datetime default current_timestamp comment 'create time',
           update_time datetime default current_timestamp on update current_timestamp comment 'update time',
           CONSTRAINT fk_tb_probe_info_metrics FOREIGN KEY (probe_info_id) REFERENCES server.tb_probe_info(id)
);








