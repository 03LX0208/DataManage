package com.datamanagebackend.service.impl.user;

import com.datamanagebackend.mapper.AdminMapper;
import com.datamanagebackend.mapper.StudentMapper;
import com.datamanagebackend.mapper.TeacherMapper;
import com.datamanagebackend.pojo.Admin;
import com.datamanagebackend.pojo.Student;
import com.datamanagebackend.pojo.Teacher;
import com.datamanagebackend.pojo.User;
import com.datamanagebackend.service.user.GetUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GetUserInfoServiceImpl implements GetUserInfoService {
    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public Map<String, String> getUserInfo() {
        //从上下文找到这个已登录的用户
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        User user = loginUser.getUser();

        Map<String, String> map = new HashMap<>();
        map.put("error_message", "success");
        map.put("id", user.getId().toString());
        map.put("username", user.getUsername());
        map.put("identity", user.getIdentity());

        if (user.getIdentity().equals("student")) {
            Student student = studentMapper.selectStudentByStudentId(user.getIntUsername());
            map.put("name", student.getStudentName());
        } else if (user.getIdentity().equals("teacher")) {
            Teacher teacher = teacherMapper.selectTeacherByTeacherId(user.getIntUsername());
            map.put("name", teacher.getTeacherName());
        } else if (user.getIdentity().equals("admin")) {
            Admin admin = adminMapper.selectAdminByAdminId(user.getIntUsername());
            map.put("name", admin.getAdminName());
        }

        return map;
    }
}