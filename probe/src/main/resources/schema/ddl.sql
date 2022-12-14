CREATE database probe;

DROP TABLE tb_task_queue;

CREATE TABLE probe.tb_task_queue (
    id INT PRIMARY KEY AUTO_INCREMENT,
    probe_id INT,
    customer_id INT,
    task_name TEXT,
    status INT DEFAULT 0,
    create_time datetime default current_timestamp comment 'create time',
    update_time datetime default current_timestamp on update current_timestamp comment 'update time'
);