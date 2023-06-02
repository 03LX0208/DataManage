package com.datamanagebackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.datamanagebackend.pojo.Admin;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
    @Insert(
    "INSERT INTO " +
    "admin(admin_id, admin_name, admin_gender, admin_age, admin_telephone) " +
    "VALUES(#{admin_id}, #{admin_name}, #{admin_gender}, #{admin_age}, #{admin_telephone})")
    void insertAdmin(@Param("admin_id") Integer admin_id, @Param("admin_name") String admin_name,
                     @Param("admin_gender") String admin_gender, @Param("admin_age") Integer admin_age,
                     @Param("admin_telephone") String admin_telephone);

    @Select("SELECT * FROM admin WHERE admin_id = #{admin_id}")
    Admin selectAdminByAdminId(@Param("admin_id") Integer admin_id);

    @Delete("DELETE FROM admin WHERE admin_id = #{admin_id}")
    void deleteAdminByAdminId(@Param("admin_id") Integer admin_id);

    @Update("UPDATE admin " +
    "SET admin_name = #{admin_name}, admin_gender = #{admin_gender}, admin_age = #{admin_age}, admin_telephone = #{admin_telephone} " +
    "WHERE admin_id = #{admin_id}")
    void updateAdminByAdminId(@Param("admin_id") Integer admin_id, @Param("admin_name") String admin_name,
                              @Param("admin_gender") String admin_gender, @Param("admin_age") Integer admin_age,
                              @Param("admin_telephone") String admin_telephone);
}


