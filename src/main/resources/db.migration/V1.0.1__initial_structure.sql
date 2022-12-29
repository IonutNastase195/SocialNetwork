DROP TABLE IF EXISTS users;
CREATE TABLE users
(
    id       INTEGER(20)  NOT NULL AUTO_INCREMENT,
    name     varchar(255) NOT NULL,
    email    varchar(255) NOT NULL UNIQUE,
    password varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS connections;
CREATE TABLE connections
(
    id            INTEGER      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id       INTEGER      NOT NULL,
    connection_id INTEGER      NOT NULL,
    status        VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (connection_id) REFERENCES users (id)
);

DROP TABLE IF EXISTS events;
CREATE TABLE events
(
    id       INTEGER      NOT NULL AUTO_INCREMENT,
    name     VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    date     DATETIME     NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS attendees;
CREATE TABLE attendees
(
    user_id  INTEGER NOT NULL,
    event_id INTEGER NOT NULL,
    PRIMARY KEY (user_id, event_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (event_id) REFERENCES events (id)
);

DROP TABLE IF EXISTS friendships;
CREATE TABLE friendships
(
    id       INTEGER      NOT NULL AUTO_INCREMENT,
    user1_id INTEGER      NOT NULL,
    user2_id INTEGER      NOT NULL,
    status   VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user1_id) REFERENCES users (id),
    FOREIGN KEY (user2_id) REFERENCES users (id)
);

DROP TABLE IF EXISTS t_groups;
CREATE TABLE t_groups
(
    id   INTEGER(20)  NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS group_members;
CREATE TABLE group_members
(
    group_id INTEGER(20) NOT NULL,
    user_id  INTEGER(20) NOT NULL,
    PRIMARY KEY (group_id, user_id),
    FOREIGN KEY (group_id) REFERENCES t_groups (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS post;
CREATE TABLE post
(
    id         INTEGER PRIMARY KEY AUTO_INCREMENT,
    text       VARCHAR(255),
    media      VARCHAR(255),
    likes      INTEGER,
    comments   INTEGER,
    shares     INTEGER,
    created_at DATETIME,
    user_id    INTEGER,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

DROP TABLE IF EXISTS comments;
CREATE TABLE comments
(
    id         INTEGER PRIMARY KEY AUTO_INCREMENT,
    text       VARCHAR(255) NOT NULL,
    created_at DATETIME     NOT NULL,
    user_id    INTEGER      NOT NULL,
    post_id    INTEGER      NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (post_id) REFERENCES post (id)
);