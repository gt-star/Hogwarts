--liquibase formatted sql

-- --changeSet akorotkov:1
-- CREATE INDEX search_student ON student(name);
--
-- --changeSet akorotkov:2
-- CREATE INDEX search_faculty_of_color_or_name ON faculty(color, name);

--changeset akorotkov:3

CREATE TABLE students(
    id INTEGER,
    name varchar
);
