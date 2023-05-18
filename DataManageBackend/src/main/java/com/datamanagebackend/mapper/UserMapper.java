package com.datamanagebackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.datamanagebackend.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM user WHERE id BETWEEN #{mi} AND #{mx}")
    List<User> getZZP(@Param("mi") Integer mi, @Param("mx") Integer mx);
}
