CREATE TABLE users
(
    id       bigint(20)   NOT NULL AUTO_INCREMENT,
    name     varchar(255) NOT NULL,
    email    varchar(255) NOT NULL UNIQUE,
    password varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE connections
(
    id            BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id       BIGINT       NOT NULL,
    connection_id BIGINT       NOT NULL,
    status        VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (connection_id) REFERENCES users (id)
);

CREATE TABLE events
(
    id       BIGINT       NOT NULL AUTO_INCREMENT,
    name     VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    date     DATETIME     NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE attendees
(
    user_id  BIGINT NOT NULL,
    event_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, event_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (event_id) REFERENCES events (id)
);

CREATE TABLE friendships
(
    id       BIGINT       NOT NULL AUTO_INCREMENT,
    user1_id BIGINT       NOT NULL,
    user2_id BIGINT       NOT NULL,
    status   VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user1_id) REFERENCES users (id),
    FOREIGN KEY (user2_id) REFERENCES users (id)
);

CREATE TABLE t_groups
(
    id   BIGINT(20)   NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE group_members
(
    group_id BIGINT(20) NOT NULL,
    user_id  BIGINT(20) NOT NULL,
    PRIMARY KEY (group_id, user_id),
    FOREIGN KEY (group_id) REFERENCES t_groups (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE post
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    text       VARCHAR(255),
    media      VARCHAR(255),
    likes      INTEGER,
    comments   INTEGER,
    shares     INTEGER,
    created_at DATETIME,
    user_id    BIGINT,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE comments
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    text       VARCHAR(255) NOT NULL,
    created_at DATETIME     NOT NULL,
    user_id    BIGINT       NOT NULL,
    post_id    BIGINT       NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (post_id) REFERENCES post (id)
);