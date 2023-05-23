package com.datamanagebackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.datamanagebackend.pojo.PilotCourse;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PilotCourseMapper extends BaseMapper<PilotCourse> {
    @Insert(
    "INSERT INTO " +
    "pilot_course(wanted_course, needed_course) " +
    "VALUES(#{wanted_course}, #{needed_course})")
    void insertPilotCourse(@Param("wanted_course") Integer wanted_course, @Param("needed_course") Integer needed_course);
    @Delete("DELETE FROM pilot_course WHERE wanted_course = #{wanted_course} AND needed_course = #{needed_course}")
    void deletePilotByCourseId(@Param("wanted_course") Integer wanted_course, @Param("needed_course") Integer needed_course);

    @Select("SELECT * FROM pilot_course WHERE wanted_course = #{wanted_course}")
    List<PilotCourse> selectPilotCoursesByWantedCourse(@Param("wanted_course") Integer wanted_course);
}
