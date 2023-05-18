package com.datamanagebackend.controller;

import com.datamanagebackend.mapper.UserMapper;
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
    private UserMapper userMapper;

    @GetMapping("/api/test/")
    public List<User> get(@RequestParam Map<String, String> data) {
        int mi = Integer.parseInt(data.get("mi"));
        int mx = Integer.parseInt(data.get("mx"));
        return userMapper.getZZP(mi, mx);
    }
}
