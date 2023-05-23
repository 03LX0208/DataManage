package com.datamanagebackend.controller;

import com.datamanagebackend.mapper.StudentMapper;
import com.datamanagebackend.mapper.UserMapper;
import com.datamanagebackend.pojo.Student;
import com.datamanagebackend.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    @Autowired
    private StudentMapper studentMapper;

    @GetMapping("/api/test/")
    public List<Student> get(@RequestParam Map<String, String> data) {
        int id = Integer.parseInt(data.get("faculty_id"));
        return studentMapper.selectStudentByFacultyId(id);
    }
}
