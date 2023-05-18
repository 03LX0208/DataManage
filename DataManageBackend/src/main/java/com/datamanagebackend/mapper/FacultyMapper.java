package com.datamanagebackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.datamanagebackend.pojo.Faculty;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FacultyMapper extends BaseMapper<Faculty> {
    @Select("SELECT * FROM faculty")
    List<Faculty> getAll();
}
