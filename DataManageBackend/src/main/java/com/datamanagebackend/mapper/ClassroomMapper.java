package com.datamanagebackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.datamanagebackend.pojo.Classroom;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClassroomMapper extends BaseMapper<Classroom> {
    @Insert("INSERT INTO " +
    "classroom(classroom_id, classroom_name, classroom_site) " +
    "VALUES(#{classroom_id}, #{classroom_name}, #{classroom_site})")
    void insertClassroom(@Param("classroom_id") Integer classroom_id, @Param("classroom_name") String classroom_name,
                         @Param("classroom_site") String classroom_site);

    @Select("SELECT * FROM classroom")
    List<Classroom> getAll();

    @Select("SELECT * FROM classroom WHERE classroom_id = #{classroom_id}")
    Classroom selectClassroomByClassroomId(@Param("classroom_id") Integer classroom_id);

    @Delete("DELETE FROM classroom WHERE classroom_id = #{classroom_id}")
    void deleteClassroomByClassroomId(@Param("classroom_id") Integer classroom_id);
}
