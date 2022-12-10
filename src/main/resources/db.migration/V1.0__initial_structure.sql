#CREATE TABLE users (
#   user_id INTEGER PRIMARY KEY AUTOINCREMENT,
#   username VARCHAR(255) NOT NULL,
#   password VARCHAR(255) NOT NULL,
#   email VARCHAR(255) NOT NULL,
#   active BOOLEAN DEFAULT true,
#   profile_picture LONGBLOB,
#   profile_picture_name VARCHAR(255)
#);
#
#CREATE TABLE posts (
#   post_id INTEGER PRIMARY KEY AUTOINCREMENT,
#   user_id INTEGER NOT NULL,
#   text TEXT,
#   media LONGBLOB,
#   likes INTEGER,
#   comments INTEGER,
#   shares INTEGER,
#   FOREIGN KEY (user_id) REFERENCES users(user_id)
#);
#
#CREATE TABLE groups (
#   group_id INTEGER PRIMARY KEY AUTOINCREMENT,
#   name VARCHAR(255) NOT NULL,
#   description TEXT
#);
#
#CREATE TABLE group_members (
#   group_id INTEGER NOT NULL,
#   user_id INTEGER NOT NULL,
#   FOREIGN KEY (group_id) REFERENCES groups(group_id),
#   FOREIGN KEY (user_id) REFERENCES users(user_id)
#);
#
#CREATE TABLE events (
#   event_id INTEGER PRIMARY KEY,
#   name VARCHAR(255),
#   description VARCHAR(255),
#   location VARCHAR(255),
#   start_time DATE,
#   end_time DATE,
#   FOREIGN KEY (event_id) REFERENCES event_attendees(event_id)
#);
#
#CREATE TABLE event_attendees (
#   id INTEGER PRIMARY KEY AUTOINCREMENT,
#   event_id INTEGER,
#   user_id INTEGER,
#   FOREIGN KEY (event_id) REFERENCES events(event_id),
#   FOREIGN KEY (user_id) REFERENCES users(user_id)
#);
#
#CREATE TABLE connections (
#   connection_id INTEGER PRIMARY KEY AUTOINCREMENT,
#   user_id INTEGER,
#   connection_id INTEGER,
#   type VARCHAR(255),
#   date_established DATE,
#   notes VARCHAR(255),
#   FOREIGN KEY (user_id) REFERENCES users(user_id),
#   FOREIGN KEY (connection_id) REFERENCES users(user_id)
#);
#
#CREATE TABLE comments (
#   comment_id INTEGER PRIMARY KEY AUTOINCREMENT,
#   user_id INTEGER,
#   post_id INTEGER,
#   text VARCHAR(255),
#   likes INTEGER,
#   replies INTEGER,
#   FOREIGN KEY (user_id) REFERENCES users(user_id),
#   FOREIGN KEY (post_id) REFERENCES posts(post_id)
#);
#
--A user can make many posts, but each post is associated with only one user. This is a one-to-many relationship.
--A group can have many members, and a user can be a member of many groups. This is a many-to-many relationship.
--An event can have many attendees, and a user can attend many events. This is a many-to-many relationship.
--A user can have many connections, and each connection is associated with only one user. This is a one-to-many relationship.
--A post can have many comments, and a comment is associated with only one post. This is a one-to-many relationship.
--A user can make many comments, and a comment is associated with only one user. This is a one-to-many relationship.

-- The entities in this database are:
-- - Users: Represents the users of the system.
-- - Posts: Represents the posts made by users.
-- - Groups: Represents the groups that users can belong to.
-- - Group members: Represents the many-to-many relationship between users and groups, where a user can be a member of multiple groups and a group can have multiple members.
-- - Events: Represents events that users can attend.
-- - Event attendees: Represents the many-to-many relationship between events and users, where a user can attend multiple events and an event can have multiple attendees.
-- - Connections: Represents the connections between users.
-- - Comments: Represents the comments made on posts.
--
-- The relationships between these entities are as follows:
-- - A user can make many posts, but each post is associated with only one user. This is a one-to-many relationship.
-- - A group can have many members, and a user can be a member of many groups. This is a many-to-many relationship.
-- - An event can have many attendees, and a user can attend many events. This is a many-to-many relationship.
-- - A user can have many connections, and each connection is associated with only one user. This is a one-to-many relationship.
-- - A post can have many comments, and a comment is associated with only one post. This is a one-to-many relationship.
-- - A user can make many comments, and a comment is associated with only one user. This is a one-to-many relationship.


