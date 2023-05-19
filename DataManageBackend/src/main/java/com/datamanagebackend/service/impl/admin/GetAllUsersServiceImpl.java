package com.datamanagebackend.service.impl.admin;

import com.datamanagebackend.mapper.AdminMapper;
import com.datamanagebackend.mapper.StudentMapper;
import com.datamanagebackend.mapper.TeacherMapper;
import com.datamanagebackend.mapper.UserMapper;
import com.datamanagebackend.pojo.Admin;
import com.datamanagebackend.pojo.Student;
import com.datamanagebackend.pojo.Teacher;
import com.datamanagebackend.pojo.User;
import com.datamanagebackend.service.admin.GetAllUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GetAllUsersServiceImpl implements GetAllUsersService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public List<Map<String, String>> getAllUsers() {
        List<User> userList = userMapper.selectAllUsers();

        List<Map<String, String>> res = new ArrayList<>();
        for (User user : userList) {
            Map<String, String> ans = new HashMap<>();
            if (user.getIdentity().equals("student")) {
                Student student = studentMapper.selectStudentByStudentId(user.getIntUsername());
                ans.put("id", student.getStudentId().toString());
                ans.put("name", student.getStudentName());
                ans.put("gender", student.getStudentGender());
                ans.put("age", student.getStudentAge().toString());
                ans.put("faculty_id", student.getFacultyId().toString());
                ans.put("enter_date", student.getStudentEnterDate());
                ans.put("telephone", student.getStudentTelephone());
                ans.put("identity", "学生");
            } else if (user.getIdentity().equals("teacher")) {
                Teacher teacher = teacherMapper.selectTeacherByTeacherId(user.getIntUsername());
                ans.put("id", teacher.getTeacherId().toString());
                ans.put("name", teacher.getTeacherName());
                ans.put("gender", teacher.getTeacherGender());
                ans.put("age", teacher.getTeacherAge().toString());
                ans.put("telephone", teacher.getTeacherTelephone());
                ans.put("identity", "老师");
            } else if (user.getIdentity().equals("admin")) {
                Admin admin = adminMapper.selectAdminByAdminId(user.getIntUsername());
                ans.put("id", admin.getAdminId().toString());
                ans.put("name", admin.getAdminName());
                ans.put("gender", admin.getAdminGender());
                ans.put("age", admin.getAdminAge().toString());
                ans.put("telephone", admin.getAdminTelephone());
                ans.put("identity", "管理员");
            }
            res.add(ans);
        }

        return res;
    }
}
