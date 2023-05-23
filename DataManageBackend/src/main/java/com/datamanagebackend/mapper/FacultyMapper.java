package com.datamanagebackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.datamanagebackend.pojo.Faculty;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FacultyMapper extends BaseMapper<Faculty> {
    @Insert(
    "INSERT INTO " +
    "faculty(faculty_id, faculty_name, faculty_site) " +
    "VALUES(#{faculty_id}, #{faculty_name}, #{faculty_site})")
    void insertFaculty(@Param("faculty_id") Integer faculty_id, @Param("faculty_name") String faculty_name,
                       @Param("faculty_site") String faculty_site);

    @Delete("DELETE FROM faculty WHERE faculty_id = #{faculty_id}")
    void deleteFacultyByFacultyId(@Param("faculty_id") Integer faculty_id);

    @Select("SELECT * FROM faculty")
    List<Faculty> getAll();

    @Select("SELECT * FROM faculty WHERE faculty_id = #{faculty_id}")
    Faculty selectFacultyByFacultyId(@Param("faculty_id") Integer faculty_id);

    @Update(
    "UPDATE faculty " +
    "SET faculty_name = #{faculty_name}, faculty_site = #{faculty_site} " +
    "WHERE faculty_id = #{faculty_id}")
    void updateFacultyByFacultyId(@Param("faculty_id") Integer faculty_id, @Param("faculty_name") String faculty_name,
                                  @Param("faculty_site") String faculty_site);
}
