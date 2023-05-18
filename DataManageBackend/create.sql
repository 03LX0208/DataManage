create table classroom
(
    classroom_id   int         not null
        primary key,
    classroom_name int         not null,
    classroom_site varchar(20) not null
);

create table appoint_classroom
(
    appoint_id   int auto_increment
        primary key,
    user_id      int          not null,
    classroom_id int          not null,
    appoint_time varchar(100) not null,
    constraint appoint_classroom_classroom_classroom_id_fk
        foreign key (classroom_id) references classroom (classroom_id)
            on update cascade on delete cascade
);

create index appoint_classroom_user_username_fk
    on appoint_classroom (user_id);

create table faculty
(
    faculty_id   int         not null
        primary key,
    faculty_name varchar(20) not null,
    faculty_site varchar(20) not null
);

create table course
(
    course_id     int         not null
        primary key,
    class_name    varchar(20) not null,
    class_lessons int         not null,
    class_credit  int         not null,
    faculty_id    int         not null,
    constraint course_faculties_faculties_id_fk
        foreign key (faculty_id) references faculty (faculty_id)
            on update cascade
);

create table pilot_course
(
    wanted_course int not null,
    needed_course int not null,
    primary key (wanted_course, needed_course),
    constraint pilot_lessons_course_course_id_fk
        foreign key (wanted_course) references course (course_id)
            on update cascade on delete cascade,
    constraint pilot_lessons_course_course_id_fk2
        foreign key (needed_course) references course (course_id)
            on update cascade
);

create table user
(
    id           int auto_increment
        primary key,
    username     varchar(20)                          not null,
    password     varchar(100)                         not null,
    identity     enum ('admin', 'teacher', 'student') not null,
    int_username int                                  not null,
    constraint user_id_uindex
        unique (id),
    constraint user_pk
        unique (int_username),
    constraint user_username_uindex
        unique (username),
    constraint user_username_unique
        unique (username)
);

create table student
(
    student_id         int                     not null
        primary key,
    student_name       varchar(20)             not null,
    student_gender     enum ('male', 'female') not null,
    student_enter_date varchar(20)             not null,
    student_age        int                     not null,
    student_telephone  varchar(20)             not null,
    faculty_id         int                     not null,
    constraint student_student_id_uindex
        unique (student_id),
    constraint student_faculties_faculties_id_fk
        foreign key (faculty_id) references faculty (faculty_id)
            on update cascade,
    constraint student_user_int_username_fk
        foreign key (student_id) references user (int_username)
            on update cascade
);

create table teacher
(
    teacher_id        int                     not null
        primary key,
    teacher_name      varchar(20)             not null,
    teacher_gender    enum ('male', 'famale') not null,
    teacher_age       int                     not null,
    teacher_telephone varchar(20)             not null,
    constraint teacher__fk
        foreign key (teacher_id) references user (int_username)
            on update cascade
);

create table section
(
    teacher_id   int          not null,
    course_id    int          not null,
    section_id   int          not null,
    classroom_id int          not null,
    section_time varchar(100) not null,
    primary key (teacher_id, course_id, section_id),
    constraint section_pk
        unique (section_id, course_id),
    constraint section___fk
        foreign key (teacher_id) references teacher (teacher_id)
            on update cascade,
    constraint section_classroom_classroom_id_fk
        foreign key (classroom_id) references classroom (classroom_id)
            on update cascade,
    constraint section_course_course_id_fk
        foreign key (course_id) references course (course_id)
            on update cascade
);

create table student_section
(
    student_id    int not null,
    course_id     int not null,
    section_id    int not null,
    student_grade int null,
    primary key (student_id, section_id, course_id),
    constraint student_section_course_id_section_id_uindex
        unique (course_id, section_id),
    constraint student_id
        foreign key (student_id) references student (student_id)
            on update cascade,
    constraint student_section_section_section_id_course_id_fk
        foreign key (section_id, course_id) references section (section_id, course_id)
            on update cascade
);

create index student_section_course_id_section_id_index
    on student_section (course_id, section_id);

