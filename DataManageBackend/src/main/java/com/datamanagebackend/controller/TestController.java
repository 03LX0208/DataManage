package com.datamanagebackend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.datamanagebackend.mapper.StudentMapper;
import com.datamanagebackend.mapper.UserMapper;
import com.datamanagebackend.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {
    @Autowired
    private StudentMapper studentMapper;

    @GetMapping("/test/")
    public Map<String, String> test(@RequestParam Map<String, String> data) {
        Map<String, String> res = new HashMap<>();

        Integer id = Integer.parseInt(data.get("id"));
        String name = data.get("name");
        String sex = data.get("sex");

        studentMapper.insert(new Student(id, name, sex));

        res.put("message", "success");
        return res;
    }
}
