package com.datamanagebackend.service.impl.admin;

import com.datamanagebackend.mapper.UserMapper;
import com.datamanagebackend.pojo.User;
import com.datamanagebackend.service.admin.GetAllUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetAllUsersServiceImpl implements GetAllUsersService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAllUsers() {
        List<User> res = null;
        res = userMapper.selectList(null);
        return res;
    }
}
