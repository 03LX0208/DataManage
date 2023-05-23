package com.datamanagebackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.datamanagebackend.pojo.Course;
import com.datamanagebackend.pojo.Faculty;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.parameters.P;

import java.util.List;

@Mapper
public interface CourseMapper extends BaseMapper<Course> {
    @Insert(
    "INSERT INTO " +
    "course(course_id, course_name, course_period, course_credit, faculty_id) " +
    "VALUES(#{course_id}, #{course_name}, #{course_period}, #{course_credit}, #{faculty_id})")
    void insertCourse(@Param("course_id") Integer course_id, @Param("course_name") String course_name,
                      @Param("course_period") Integer course_period, @Param("course_credit") Integer course_credit,
                      @Param("faculty_id") Integer faculty_id);

    @Select("SELECT * FROM course")
    List<Course> getAll();

    @Select("SELECT * FROM course WHERE faculty_id = #{faculty_id}")
    List<Course> selectCoursesByFacultyId(@Param("faculty_id") Integer faculty_id);

    @Select("SELECT * FROM course WHERE course_id = #{course_id}")
    Course selectCourseByCourseId(@Param("course_id") Integer course_id);

    @Delete("DELETE FROM course WHERE course_id = #{course_id}")
    void deleteCourseByCourseId(@Param("course_id") Integer course_id);

    @Update(
    "UPDATE course " +
    "SET course_name = #{course_name}, course_period = #{course_period}, course_credit = #{course_credit}, faculty_id = #{faculty_id} " +
    "WHERE course_id = #{course_id}")
    void updateCourseByCourseId(@Param("course_id") Integer course_id, @Param("course_name") String course_name,
                                @Param("course_period") Integer course_period, @Param("course_credit") Integer course_credit,
                                @Param("faculty_id") Integer faculty_id);
}
