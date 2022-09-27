CREATE TABLE probe.tb_task_queue (
    id INT PRIMARY KEY AUTO_INCREMENT,
    probe_id INT,
    job_name TEXT,
    status INT DEFAULT 0,
    create_time datetime default current_timestamp comment 'create time',
    update_time datetime default current_timestamp on update current_timestamp comment 'update time'
);