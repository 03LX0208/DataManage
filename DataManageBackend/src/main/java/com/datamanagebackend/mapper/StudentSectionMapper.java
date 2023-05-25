package com.datamanagebackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.datamanagebackend.pojo.StudentSection;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentSectionMapper extends BaseMapper<StudentSection> {
    @Insert(
    "INSERT INTO " +
    "student_section(student_id, section_id, grade) " +
    "VALUES(#{student_id}, #{section_id}, #{grade})")
    void insertStudentSection(@Param("student_id") Integer student_id, @Param("section_id") Integer section_id,
                              @Param("grade") String grade);

    @Select("SELECT \n" +
            "    t.section_id,\n" +
            "    t.section_time,\n" +
            "    t.course_name,\n" +
            "    t.course_period,\n" +
            "    t.course_credit,\n" +
            "    t.teacher_name,\n" +
            "    t.faculty_name,\n" +
            "    t.classroom_name,\n" +
            "    t.classroom_site\n" +
            "FROM student_section\n" +
            "LEFT JOIN (\n" +
            "    SELECT  \n" +
            "        section.section_id,\n" +
            "        section.section_time,\n" +
            "        course.course_name,\n" +
            "        course.course_period,\n" +
            "        course.course_credit,\n" +
            "        teacher.teacher_name,\n" +
            "        faculty.faculty_name,\n" +
            "        classroom.classroom_name,\n" +
            "        classroom.classroom_site\n" +
            "    FROM section\n" +
            "    LEFT JOIN course ON section.course_id = course.course_id\n" +
            "    LEFT JOIN teacher ON section.teacher_id = teacher.teacher_id\n" +
            "    LEFT JOIN classroom ON section.classroom_id = classroom.classroom_id\n" +
            "    LEFT JOIN faculty ON course.faculty_id = faculty.faculty_id\n" +
            "    WHERE section.is_completed = 0\n" +
            "    ORDER BY section.section_id\n" +
            ") AS t ON student_section.section_id = t.section_id\n" +
            "WHERE student_section.student_id = #{student_id}\n" +
            "ORDER BY t.section_id")
    List<Map<String, Object>> getStudentSectionByStudentId(@Param("student_id") Integer student_id);

    @Select("SELECT\n" +
            "    t.section_id,\n" +
            "    teacher.teacher_name,\n" +
            "    course.course_name,\n" +
            "    course_credit\n" +
            "    course_period,\n" +
            "    faculty_name,\n" +
            "    t.grade\n" +
            "FROM (\n" +
            "    SELECT\n" +
            "        section.section_id,\n" +
            "        section.course_id,\n" +
            "        section.teacher_id,\n" +
            "        student_section.grade\n" +
            "    FROM\n" +
            "        student_section\n" +
            "    LEFT JOIN section ON section.section_id = student_section.section_id\n" +
            "    WHERE student_section.student_id = #{student_id} AND student_section.grade IS NOT NULL\n" +
            ") AS t \n" +
            "LEFT JOIN course ON course.course_id = t.course_id\n" +
            "LEFT JOIN teacher ON teacher.teacher_id = t.teacher_id\n" +
            "LEFT JOIN faculty ON faculty.faculty_id = course.faculty_id\n" +
            "ORDER BY t.section_id")
    List<Map<String, Object>> getStudentGradeByStudentId(@Param("student_id") Integer student_id);

    @Select("SELECT \n" +
            "    section.section_id,\n" +
            "    section.capacity AS total_capacity,\n" +
            "    IFNULL(t.now_count, 0) AS now_capacity\n" +
            "FROM \n" +
            "    section\n" +
            "LEFT JOIN (\n" +
            "    SELECT\n" +
            "        section_id,\n" +
            "        IFNULL(COUNT(*), 0) AS now_count\n" +
            "    FROM\n" +
            "        student_section\n" +
            "    GROUP BY\n" +
            "        section_id\n" +
            ") AS t ON t.section_id = section.section_id;")
    List<Map<String, Object>> getSectionNowCountList();

    @Select("SELECT\n" +
            "    student.student_id,\n" +
            "    student.student_name,\n" +
            "    faculty.faculty_name,\n" +
            "    IFNULL(student_section.grade, -1) AS grade\n" +
            "FROM\n" +
            "    student_section\n" +
            "LEFT JOIN student ON student_section.student_id = student.student_id\n" +
            "LEFT JOIN faculty ON student.faculty_id = faculty.faculty_id\n" +
            "WHERE student_section.section_id = #{section_id}")
    List<Map<String, Object>> getSectionScoreBySectionId(@Param("section_id") Integer section_id);

    @Select("SELECT * FROM student_section WHERE section_id = #{section_id}")
    List<StudentSection> selectListBySectionId(@Param("section_id") Integer section_id);

    @Select("SELECT * FROM student_section WHERE student_id = #{student_id}")
    List<StudentSection> selectListByStudentId(@Param("student_id") Integer student_id);

    @Delete("DELETE FROM student_section WHERE student_id = #{student_id} AND section_id = #{section_id}")
    void deleteByStudentIdSectionId(@Param("student_id") Integer student_id, @Param("section_id") Integer section_id);

    @Update("UPDATE student_section SET grade = #{grade} WHERE student_id = #{student_id} AND section_id = #{section_id}")
    void updateGrade(@Param("student_id") Integer student_id, @Param("section_id") Integer section_id, @Param("grade") Integer grade);
}
