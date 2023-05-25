create table classroom
(
    classroom_id   int         not null
        primary key,
    classroom_name varchar(20) not null,
    classroom_site varchar(20) not null
);

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
    course_name   varchar(20) not null,
    course_period int         not null,
    course_credit int         not null,
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

create table admin
(
    admin_id        int                     not null
        primary key,
    admin_name      varchar(20)             not null,
    admin_gender    enum ('male', 'female') not null,
    admin_age       int                     not null,
    admin_telephone varchar(20)             not null,
    constraint admin_admin_id_uindex
        unique (admin_id),
    constraint admin_user_int_username_fk
        foreign key (admin_id) references user (int_username)
            on update cascade
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
    teacher_gender    enum ('male', 'female') not null,
    teacher_age       int                     not null,
    teacher_telephone varchar(20)             not null,
    constraint teacher__fk
        foreign key (teacher_id) references user (int_username)
            on update cascade
);

create table section
(
    section_id   int auto_increment
        primary key,
    course_id    int                  not null,
    teacher_id   int                  not null,
    classroom_id int                  not null,
    section_time varchar(100)         null,
    capacity     int                  not null,
    is_completed tinyint(1) default 0 not null,
    constraint section_section_id_uindex
        unique (section_id),
    constraint section_classroom_classroom_id_fk
        foreign key (classroom_id) references classroom (classroom_id),
    constraint section_course_course_id_fk
        foreign key (course_id) references course (course_id),
    constraint section_teacher_teacher_id_fk
        foreign key (teacher_id) references teacher (teacher_id)
);

create table student_section
(
    student_id int not null,
    section_id int not null,
    grade      int null,
    constraint student_section_section_section_id_fk
        foreign key (section_id) references section (section_id),
    constraint student_section_student_student_id_fk
        foreign key (student_id) references student (student_id)
);





