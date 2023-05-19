package com.datamanagebackend.service.impl.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.datamanagebackend.mapper.AdminMapper;
import com.datamanagebackend.mapper.UserMapper;
import com.datamanagebackend.pojo.User;
import com.datamanagebackend.service.user.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Map<String, String> register(String username, String password, String confirmedPassword, String identity) {
        Map<String, String> map = new HashMap<>();
        if (username == null || username.trim().length() == 0) {
            map.put("error_message", "教学号不能为空");
            return map;
        }

        if (password == null || confirmedPassword == null) {
            map.put("error_message", "密码不能为空");
            return map;
        }

        username = username.trim();
        if (username.length() == 0) {
            map.put("error_message", "教学号不能为空");
            return map;
        }

        for (int i = 0; i < username.length(); i++) {
            if (!(username.charAt(i) >= '0' && username.charAt(i) <= '9')) {
                map.put("error_message", "教学号只能包含数字！");
                return map;
            }
        }

        if (password.length() == 0 || confirmedPassword.length() == 0) {
            map.put("error_message", "密码不能为空");
            return map;
        }

        if (username.length() > 20) {
            map.put("error_message", "教学号长度不能超过20");
            return map;
        }

        if (password.length() > 100 || confirmedPassword.length() > 100) {
            map.put("error_message", "密码长度不能超过100");
            return map;
        }

        if (!password.equals(confirmedPassword)) {
            map.put("error_message", "两次输入的密码不一致");
            return map;
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        List<User> list = userMapper.selectList(queryWrapper);
        if (!list.isEmpty()) {
            map.put("error_message", "教学号已存在");
            return map;
        }

        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(null, username, encodedPassword, identity, Integer.parseInt(username));
        userMapper.insert(user);
        adminMapper.insertAdmin(Integer.parseInt(username), "管理员普の", "male", 18, "110");

        map.put("error_message", "success");
        return map;
    }
}