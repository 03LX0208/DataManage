package com.datamanagebackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.datamanagebackend.pojo.Teacher;
import org.apache.ibatis.annotations.*;

@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {
    @Insert(
    "INSERT INTO " +
    "teacher(teacher_id, teacher_name, teacher_gender, teacher_age, teacher_telephone) " +
    "VALUES(#{teacher_id}, #{teacher_name}, #{teacher_gender}, #{teacher_age}, #{teacher_telephone})"
    )
    void insertTeacher(@Param("teacher_id") Integer teacher_id, @Param("teacher_name") String teacher_name,
                       @Param("teacher_gender") String teacher_gender, @Param("teacher_age") Integer teacher_age,
                       @Param("teacher_telephone") String teacher_telephone);

    @Select("SELECT * FROM teacher WHERE teacher_id = #{teacher_id}")
    Teacher selectTeacherByTeacherId(@Param("teacher_id") Integer teacher_id);

    @Delete("DELETE FROM teacher WHERE teacher_id = #{teacher_id}")
    void deleteTeacherByTeacherId(@Param("teacher_id") Integer teacher_id);

    @Update("UPDATE teacher " +
    "SET teacher_name = #{teacher_name}, teacher_gender = #{teacher_gender}, teacher_age = #{teacher_age}, teacher_telephone = #{teacher_telephone} " +
    "WHERE teacher_id = #{teacher_id}")
    void updateTeacherByTeacherId(@Param("teacher_id") Integer teacher_id, @Param("teacher_name") String teacher_name,
                                  @Param("teacher_gender") String teacher_gender, @Param("teacher_age") Integer teacher_age,
                                  @Param("teacher_telephone") String teacher_telephone);
}
