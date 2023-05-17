create table student
(
    student_id     varchar(20)             not null,
    student_name   varchar(20)             not null,
    student_gender enum ('male', 'female') not null,
    constraint student_student_id_uindex
        unique (student_id)
);

create table user
(
    id       int auto_increment
        primary key,
    username varchar(20)                          not null,
    password varchar(100)                         not null,
    identity enum ('student', 'teacher', 'admin') not null,
    constraint user_id_uindex
        unique (id)
);

