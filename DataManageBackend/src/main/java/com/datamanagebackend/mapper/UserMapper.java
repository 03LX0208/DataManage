package com.datamanagebackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.datamanagebackend.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM user WHERE id BETWEEN #{mi} AND #{mx}")
    List<User> getZZP(@Param("mi") Integer mi, @Param("mx") Integer mx);

    @Insert(
    "INSERT INTO " +
    "user(id, username, password, identity, int_username) " +
    "VALUES(#{id}, #{username}, #{password}, #{identity}, #{int_username})"
    )
    void insertUser(@Param("id") Integer id, @Param("username") String username,
                    @Param("password") String password, @Param("identity") String identity,
                    @Param("int_username") Integer int_username);

    @Select("SELECT * FROM user WHERE int_username = #{int_username}")
    User selectUserByIntUsername(@Param("int_username") Integer int_username);

    @Select("SELECT * FROM user")
    List<User> selectAllUsers();

    @Delete("DELETE FROM user WHERE int_username = #{int_username}")
    void deleteUserByIntUsername(@Param("int_username") Integer int_username);
}