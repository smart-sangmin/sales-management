CREATE TABLE visit
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    create_at     datetime              NULL,
    update_at     datetime              NULL,
    menu          VARCHAR(255)          NULL,
    price         INT                   NOT NULL,
    date          date                  NULL,
    `description` VARCHAR(255)          NULL,
    start_at      time                  NULL,
    end_at        time                  NULL,
    CONSTRAINT pk_visit PRIMARY KEY (id)
);
