package com.datamanagebackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.datamanagebackend.pojo.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {
    @Insert
    ("INSERT INTO " +
    "student(student_id, student_name, student_gender, student_enter_date, student_age, student_telephone, faculty_id) " +
    "VALUES(#{student_id}, #{student_name}, #{student_gender}, #{student_enter_date}, #{student_age}, #{student_telephone}, #{faculty_id})"
    )
    void insertStudent(@Param("student_id") Integer student_id, @Param("student_name") String student_name,
                       @Param("student_gender") String student_gender, @Param("student_enter_date") String student_enter_date,
                       @Param("student_age") Integer student_age, @Param("student_telephone") String student_telephone,
                       @Param("faculty_id") Integer faculty_id);
    @Select("SELECT * FROM student WHERE student_id = #{student_id}")
    Student selectStudentByStudentId(@Param("student_id") Integer student_id);

    @Select("SELECT * FROM student WHERE faculty_id = #{faculty_id}")
    List<Student> selectStudentByFacultyId(@Param("faculty_id") Integer faculty_id);

    @Delete("DELETE FROM student WHERE student_id = #{student_id}")
    void deleteStudentByStudentId(@Param("student_id") Integer student_id);

    @Update("UPDATE student " +
    "SET student_name = #{student_name}, student_gender = #{student_gender}, student_enter_date = #{student_enter_date}, " +
    "student_age = #{student_age}, student_telephone = #{student_telephone}, faculty_id = #{faculty_id} " +
    "WHERE student_id = #{student_id}")
    void updateStudentByStudentId(@Param("student_id") Integer student_id, @Param("student_name") String student_name,
                                  @Param("student_gender") String student_gender, @Param("student_enter_date") String student_enter_date,
                                  @Param("student_age") Integer student_age, @Param("student_telephone") String student_telephone,
                                  @Param("faculty_id") Integer faculty_id);
}
