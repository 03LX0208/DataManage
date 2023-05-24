package com.datamanagebackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.datamanagebackend.pojo.Section;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface SectionMapper extends BaseMapper<Section> {
    @Insert("INSERT INTO " +
    "section(section_id, course_id, teacher_id, classroom_id, section_time, is_completed) " +
    "VALUES(#{section_id}, #{course_id}, #{teacher_id}, #{classroom_id}, #{section_time}, 0)")
    void insertSection(@Param("section_id") Integer section_id, @Param("course_id") Integer course_id,
                       @Param("teacher_id") Integer teacher_id, @Param("classroom_id") Integer classroom_id,
                       @Param("section_time") String section_time);

    @Select("SELECT * FROM section WHERE section_id = #{section_id}")
    Section selectSectionBySectionId(@Param("section_id") Integer section_id);

    @Select("SELECT * FROM section WHERE course_id = #{course_id}")
    List<Section> selectSectionByCourseId(@Param("course_id") Integer course_id);

    @Select("SELECT * FROM section WHERE teacher_id = #{teacher_id}")
    List<Section> selectSectionsByTeacherId(@Param("teacher_id") Integer teacher_id);

    @Select("SELECT * FROM section WHERE classroom_id = #{classroom_id}")
    List<Section> selectSectionsByClassroomId(@Param("classroom_id") Integer classroom_id);

    @Select("SELECT  \n" +
            "    section.section_id,\n" +
            "    section.section_time,\n" +
            "    course.course_name,\n" +
            "    course.course_period,\n" +
            "    course.course_credit,\n" +
            "    teacher.teacher_name,\n" +
            "    faculty.faculty_name,\n" +
            "    classroom.classroom_name,\n" +
            "    classroom.classroom_site\n" +
            "FROM section\n" +
            "LEFT JOIN course ON section.course_id = course.course_id\n" +
            "LEFT JOIN teacher ON section.teacher_id = teacher.teacher_id\n" +
            "LEFT JOIN classroom ON section.classroom_id = classroom.classroom_id\n" +
            "LEFT JOIN faculty ON course.faculty_id = faculty.faculty_id\n" +
            "WHERE section.is_completed = 0\n" +
            "ORDER BY section.section_id\n")
    List<Map<String, Object>> getSectionToSelect();


    @Select("SELECT * FROM section")
    List<Section> getAll();

    @Delete("DELETE FROM section where section_id = #{section_id}")
    void deleteSectionBySectionId(@Param("section_id") Integer section_id);

    @Update("UPDATE section " +
    "SET course_id = #{course_id}, teacher_id = #{teacher_id}, classroom_id = #{classroom_id}, section_time = #{section_time} " +
    "WHERE section_id = #{section_id}")
    void updateSectionBySectionId(@Param("section_id") Integer section_id, @Param("course_id") Integer course_id,
                       @Param("teacher_id") Integer teacher_id, @Param("classroom_id") Integer classroom_id,
                       @Param("section_time") String section_time);
}
