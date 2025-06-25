CREATE SCHEMA s;
CREATE TABLE s.users
(
    id                BIGINT PRIMARY KEY,
    tg_id             BIGINT      NULL,
    User_Name         VARCHAR(30),
    tg_name           VARCHAR(30) NOT NULL,
    entry_date        date,
    name              VARCHAR(30) NOT NULL,
    date_start        DATE        NOT NULL,
    field_of_activity VARCHAR(100)
);

CREATE TABLE s.conference
(
    id     BIGINT PRIMARY KEY,
    theme  VARCHAR(60) NOT NULL,
    status VARCHAR(30) NOT NULL,
    data   DATE        NOT NULL,
    link   VARCHAR(60)
);
/*
CREATE TABLE s.results_conference
(
    id            BIGINT PRIMARY KEY,
    participant   BIGINT NULL,
    justification VARCHAR(30),
    balls         INT    NOT NULL,
    FOREIGN KEY (id) REFERENCES s.users (id)
);
*/
CREATE TABLE s.ball
(
    id     BIGINT PRIMARY KEY,
    reason VARCHAR(250),
    balls  INT NOT NULL
);

CREATE TABLE s.user_conference
(
    id               BIGINT PRIMARY KEY,
    user_id          BIGINT NOT NULL,
    conference_id    BIGINT NOT NULL,
    exist_user       boolean,
    number           BIGINT NOT NULL,
    speaker_number   BIGINT NOT NULL,
    moderator_number BIGINT NOT NULL,
    time             VARCHAR(30),
    ball_id          BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES s.users (id)
 /*FOREIGN KEY (user_id) REFERENCES s.conference (id)*/
);

CREATE TABLE s.schedule
(
    id      BIGINT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    time    TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES s.users (id)
);

CREATE TABLE s.vote
(
    id                 BIGINT PRIMARY KEY,
    conference_id      BIGINT NOT NULL,
    user_id            BIGINT NOT NULL,
    best_speaker_id    BIGINT NOT NULL,
    worst_speaker_id   BIGINT NOT NULL,
    best_moderator_id  BIGINT NOT NULL,
    worst_moderator_id BIGINT NOT NULL,

    FOREIGN KEY (user_id) REFERENCES s.users (id),
    FOREIGN KEY (best_speaker_id) REFERENCES s.users (id),
    FOREIGN KEY (worst_speaker_id) REFERENCES s.users (id),
    FOREIGN KEY (best_moderator_id) REFERENCES s.users (id),
    FOREIGN KEY (worst_moderator_id) REFERENCES s.users (id)
);

CREATE TABLE s.topic
(
    id         BIGINT PRIMARY KEY,
    user_id    BIGINT NOT NULL,
    topic      VARCHAR(255),
    count_user BIGINT NOT NULL default 0,
    FOREIGN KEY (user_id) REFERENCES s.users (id)
);



CREATE TABLE s.proposals_for_change
(
    id                    BIGINT PRIMARY KEY,
    data                  DATE   NOT NULL,
    user_id               BIGINT NOT NULL,
    n_rule                BIGINT NOT NULL,
    formulation           VARCHAR(250),
    suggested_formulation VARCHAR(250),
    argument              VARCHAR(250),
    time                  DATE   NOT NULL,
    voise_Before          INT    NOT NULL,
    voise                 INT    NOT NULL,
    status                INT    NOT NULL,
    FOREIGN KEY (user_id) REFERENCES s.users (id)
);

INSERT INTO s.users (id, tg_id, User_Name, tg_name, name, entry_date, field_of_activity, date_start)
VALUES (1, 12123, '@Vasya', 'Vasya2', 'Vasya', '2023-12-12', 'management', '2024-12-14'),
       (2, 12123, '@Masha', 'Masha134', 'Masha', '2024-12-23', 'coking', '2024-12-14'),
       (3, 12123, '@Vera', 'Vera1345', 'Vera', '2023-12-12', 'painting', '2024-12-14'),
       (4, 12123, '@Dima', 'Dimon', 'Dima', '2023-12-12', 'drawing', '2024-12-14');

INSERT INTO s.topic(id, user_id, topic, count_user)
VALUES (37, 1, 'Погода', 3),
       (84, 2, 'нукн', 4),
       (59, 4, 'аправпраы', 6),
       (12563, 3, 'чапоаыпоаоачо', 7);



INSERT INTO s.ball(id, reason, balls)
VALUES (1, 'лучший модератор', 1),
       (2, 'лучший спикер', 3),
       (3, 'худший модератор', -1),
       (4, 'худший спикер', -3),
       (5, 'не присудствовал', -5),
       (6, 'луший спикер и модератор', 4),
       (7, 'луший спикер но худший модератор', 2),
       (8, 'луший модератор но худший спикер', -2);

/*INSERT INTO s.results_conference(id, participant, justification, balls)
VALUES (1, 2, 'не самый лучший модератор', 3),
       (2, 5, 'не лучший модератор', 4),
       (3, 4, 'лучший модератор', 5),
       (4, 1, 'худший модератор', 2);*/
INSERT INTO s.user_conference(id, user_id, conference_id, exist_user, number, speaker_number, moderator_number, time,
                              ball_id)
VALUES (1, 2, 3, true, 2, 7, 5, '2023-12-12', 2),
       (2, 2, 2, true, 2, 5, 4, '2023-12-12', 1),
       (3, 4, 3, true, 3, 4, 3, '2023-12-12', 4),
       (4, 2, 4, true, 4, 3, 2, '2023-12-12', 5),
       (5, 2, 5, true, 5, 2, 4, '2023-12-12', 2),
       (6, 1, 6, false, 6, 3, 4, '2023-12-12', 3),
       (7, 2, 1, false, 7, 1, 4, '2023-12-12', 5);
/*
SELECT *
FROM s.users;
SELECT u.name,
       t.topic,
       t.count_user
FROM s.topic t
         join s.users u on u.id = t.user_id;

SELECT (SELECT name from s.users u WHERE u.id = t.user_id),
       t.topic,
       t.count_user
FROM s.topic t
*/
/*
SELECT u.name,
       r.participant,
       r.justification,
       r.balls
FROM s.results_conference r
         join s.users u on u.id = r.id;

 */
SELECT uc.user_id,
       u.user_name,
       b.reason,
       b.balls
FROM s.user_conference uc
         JOIN s.users u ON u.id = uc.user_id
         JOIN s.ball b on b.id = uc.ball_id;

SELECT uc.user_id,
       u.user_name,
       u.name,
       u.date_start,
       u.field_of_activity,
       count(uc.id),
       sum(b.balls),
       array_agg(uc.id)
FROM s.user_conference uc
         JOIN s.users u ON u.id = uc.user_id
         JOIN s.ball b on b.id = uc.ball_id
WHERE u.name='Masha'
GROUP BY uc.user_id, u.user_name, u.name, u.date_start, u.field_of_activity
HAVING sum(b.balls)<3
order by name DESC ;

SELECT distinct uc.user_id,
                uc.exist_user,
       u.user_name,
       u.name,
       u.date_start,
       u.field_of_activity,
       count  (uc.id) over(partition by uc.user_id),
       sum(b.balls)over(partition by uc.user_id),
       array_agg(uc.id)over(partition by uc.user_id)
FROM s.user_conference uc
         JOIN s.users u ON u.id = uc.user_id
         JOIN s.ball b on b.id = uc.ball_id
order by name DESC ;
/*

SELECT con.topic,
       con.count_user

FROM s.user_conference uc
         JOIN s.conference con ON con.id = uc.user_id