-- liquibase formatted sql

-- changeset akorotkov:1

CREATE INDEX search_faculty_of_color_or_name ON faculties(color, name)