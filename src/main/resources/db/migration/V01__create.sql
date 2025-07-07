create schema if not exists public;

CREATE TABLE IF NOT EXISTS public.Employee
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(30) NOT NULL,
    image    VARCHAR(200) NOT NULL,
    telephon VARCHAR(30),
    mail     VARCHAR(30) NOT NULL,
    english_level Integer
);
CREATE TABLE IF NOT EXISTS public.Project
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(30) NOT NULL,
    description VARCHAR(30) NOT NULL,
    id_employee BIGINT      NOT NULL,
    FOREIGN KEY (id_employee) REFERENCES Employee (id)
);
CREATE TABLE IF NOT EXISTS public.Education
(
    id            SERIAL PRIMARY KEY,
    yearStart     INT         NOT NULL,
    yearEnd       INT         NOT NULL,
    nameEducation VARCHAR(30) NOT NULL,
    degree        VARCHAR(30) NOT NULL,
    id_education  BIGINT      NOT NULL,
    FOREIGN KEY (id_education) REFERENCES Employee (id)
);
INSERT INTO Employee (id, name, image, telephon, mail)
VALUES (1, 'Masha', '/images/kat.jpg', 3742934435, 'sfhgg@mail.ru'),
       (2, 'Masha', '/images/kat.jpg', 3742465, 'rsfghg@mail.ru '),
       (3, 'Anna', '/images/kat.jpg', 37346777, 'rseg@mail.ru'),
       (4, 'Ira', '/images/kat.jpg', 374294565, 'xcnvneg@mail.ru');

INSERT INTO Project(id, name, description, id_employee)
VALUES (1, 'Masha', 'Java Developer', 1);

INSERT INTO Education (id, yearStart, yearEnd, nameEducation, degree, id_education)
VALUES (1, 2005, 2012, 'BGU', 'BACALAVR', 2),
       (2, 2004, 2016, 'BGU', 'BACALAVR', 1),
       (3, 2007, 2018, 'BGUIR', 'PROFESSOR', 1);

CREATE TABLE IF NOT EXISTS public.Skills
(
    id           SERIAL PRIMARY KEY,
    skill        INTEGER NOT NULL,
    id_employee BIGINT  NOT NULL,
    FOREIGN KEY (id_employee) REFERENCES Employee (id)
);
CREATE TABLE IF NOT EXISTS public.tasks
(
    id         SERIAL PRIMARY KEY,
    skill      INTEGER NOT NULL,
    id_project BIGINT  NOT NULL,
    FOREIGN KEY (id_project) REFERENCES Employee (id)
);
