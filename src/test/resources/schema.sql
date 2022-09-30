
    CREATE TABLE canaux
(
    id            BIGINT AUTO_INCREMENT   NOT NULL,
    name          VARCHAR(50)             NOT NULL,
    `description` VARCHAR(50)             NULL,
    created_at    timestamp DEFAULT NOW() NOT NULL,
    updated_at    time                    NULL,
    CONSTRAINT pk_canaux PRIMARY KEY (id)
);


CREATE TABLE messages
(
    id         BIGINT AUTO_INCREMENT   NOT NULL,
    content    TEXT                    NOT NULL,
    created_at timestamp DEFAULT NOW() NOT NULL,
    updated_at datetime                NULL,
    user_id    BIGINT                  NOT NULL,
    canal_id   BIGINT                  NOT NULL,
    CONSTRAINT pk_messages PRIMARY KEY (id)
);

CREATE TABLE user_canal
(
    canal_id BIGINT NOT NULL,
    user_id  BIGINT NOT NULL,
    CONSTRAINT pk_user_canal PRIMARY KEY (canal_id, user_id)
);

CREATE TABLE users
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    username VARCHAR(100)          NOT NULL,
    email    VARCHAR(100)          NULL,
    password VARCHAR(8)            NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE messages
    ADD CONSTRAINT FK_MESSAGES_ON_CANAL FOREIGN KEY (canal_id) REFERENCES canaux (id);

ALTER TABLE messages
    ADD CONSTRAINT FK_MESSAGES_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE user_canal
    ADD CONSTRAINT fk_usecan_on_canal FOREIGN KEY (canal_id) REFERENCES canaux (id);

ALTER TABLE user_canal
    ADD CONSTRAINT fk_usecan_on_user FOREIGN KEY (user_id) REFERENCES users (id);