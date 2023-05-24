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

    @Delete("DELETE FROM student_section WHERE student_id = #{student_id} AND section_id = #{section_id}")
    void deleteByStudentIdSectionId(@Param("student_id") Integer student_id, @Param("section_id") Integer section_id);
}
