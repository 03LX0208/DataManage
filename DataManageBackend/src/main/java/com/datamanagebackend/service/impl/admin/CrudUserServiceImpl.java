package com.datamanagebackend.service.impl.admin;

import com.datamanagebackend.mapper.StudentMapper;
import com.datamanagebackend.mapper.TeacherMapper;
import com.datamanagebackend.mapper.UserMapper;
import com.datamanagebackend.pojo.Student;
import com.datamanagebackend.pojo.Teacher;
import com.datamanagebackend.pojo.User;
import com.datamanagebackend.service.admin.CrudUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@Service
public class CrudUserServiceImpl implements CrudUserService {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Map<String, String> addUserService(Map<String, String> data) {
        String identity = data.get("identity");
        Map<String, String> res = new HashMap<>();
        if (identity == null || identity.trim().length() == 0) {
            res.put("error_message", "身份不能为空！");
            return res;
        }

        if (identity.equals("student")) {
            if (data.get("student_id") == null) {
                res.put("error_message", "教学号不能为空！");
                return res;
            }

            String _student_id = data.get("student_id");
            if (_student_id == null || _student_id.trim().length() == 0) {
                res.put("error_message", "教学号不能为空！");
                return res;
            }
            for (int i = 0; i < _student_id.length(); i++) {
                if (!(_student_id.charAt(i) >= '0' && _student_id.charAt(i) <= '9')) {
                    res.put("error_message", "教学号只能由数字组成！");
                    return res;
                }
            }
            Integer student_id = Integer.parseInt(_student_id);
            User student = userMapper.selectUserByIntUsername(student_id);
            if (student != null) {
                res.put("error_message", "该教学号已存在！");
                return res;
            }

            String student_name = data.get("student_name");
            if (student_name == null || student_name.trim().length() == 0) {
                res.put("error_message", "名称不能为空！");
                return res;
            }
            student_name = student_name.trim();

            String student_gender = data.get("student_gender");
            if (student_gender == null || student_gender.length() == 0) {
                res.put("error_message", "性别不能为空！");
                return res;
            }

            String student_enter_date = data.get("student_enter_date");
            if (student_enter_date == null || student_enter_date.length() == 0) {
                res.put("error_message", "入学日期不能为空！");
                return res;
            }

            String _student_age = data.get("student_age");
            if (_student_age == null || _student_age.trim().length() == 0) {
                res.put("error_message", "年龄不能为空！");
                return res;
            }
            for (int i = 0; i < _student_age.length(); i++) {
                if (!(_student_age.charAt(i) >= '0' && _student_age.charAt(i) <= '9')) {
                    res.put("error_message", "年龄只能由数字组成！");
                    return res;
                }
            }
            Integer student_age = Integer.parseInt(_student_age);

            String student_telephone = data.get("student_telephone");
            if (student_telephone == null || student_telephone.trim().length() == 0) {
                res.put("error_message", "联系方式不能为空！");
                return res;
            }
            student_telephone = student_telephone.trim();
            for (int i = 0; i < student_telephone.length(); i++) {
                if (!(student_telephone.charAt(i) >= '0' && student_telephone.charAt(i) <= '9')) {
                    res.put("error_message", "联系方式只能由数字组成！");
                    return res;
                }
            }

            String _faculty_id = data.get("faculty_id");
            if (_faculty_id == null || _faculty_id.trim().length() == 0) {
                res.put("error_message", "学院不能为空！");
                return res;
            }
            for (int i = 0; i < _faculty_id.length(); i++) {
                if (!(_faculty_id.charAt(i) >= '0' && _faculty_id.charAt(i) <= '9')) {
                    res.put("error_message", "学院编号只能由数字组成！");
                    return res;
                }
            }
            Integer faculty_id = Integer.parseInt(_faculty_id);

            String encodeedPassword = passwordEncoder.encode("123456");
            userMapper.insertUser(null,student_id.toString(),encodeedPassword,identity,student_id);
            studentMapper.insertStudent(student_id, student_name, student_gender, student_enter_date, student_age, student_telephone, faculty_id);
        } else if (identity.equals("teacher")) {
            if (data.get("teacher_id") == null) {
                res.put("error_message", "教师号不能为空！");
                return res;
            }

            String _teacher_id = data.get("teacher_id");
            if (_teacher_id == null || _teacher_id.trim().length() == 0) {
                res.put("error_message", "教师号不能为空！");
                return res;
            }
            for (int i = 0; i < _teacher_id.length(); i++) {
                if (!(_teacher_id.charAt(i) >= '0' && _teacher_id.charAt(i) <= '9')) {
                    res.put("error_message", "教学只能由数字组成！");
                    return res;
                }
            }
            Integer teacher_id = Integer.parseInt(_teacher_id);
            User teacher = userMapper.selectUserByIntUsername(teacher_id);
            if (teacher != null) {
                res.put("error_message", "该教学号已存在！");
                return res;
            }

            String teacher_name = data.get("teacher_name");
            if (teacher_name == null || teacher_name.trim().length() == 0) {
                res.put("error_message", "名称不能为空！");
                return res;
            }
            teacher_name = teacher_name.trim();

            String teacher_gender = data.get("teacher_gender");
            if (teacher_gender == null || teacher_gender.length() == 0) {
                res.put("error_message", "性别不能为空！");
                return res;
            }

            String _teacher_age = data.get("teacher_age");
            if (_teacher_age == null || _teacher_age.trim().length() == 0) {
                res.put("error_message", "年龄不能为空！");
                return res;
            }
            for (int i = 0; i < _teacher_age.length(); i++) {
                if (!(_teacher_age.charAt(i) >= '0' && _teacher_age.charAt(i) <= '9')) {
                    res.put("error_message", "年龄只能由数字组成！");
                    return res;
                }
            }
            Integer teacher_age = Integer.parseInt(_teacher_age);

            String teacher_telephone = data.get("teacher_telephone");
            if (teacher_telephone == null || teacher_telephone.trim().length() == 0) {
                res.put("error_message", "联系方式不能为空！");
                return res;
            }
            teacher_telephone = teacher_telephone.trim();
            for (int i = 0; i < teacher_telephone.length(); i++) {
                if (!(teacher_telephone.charAt(i) >= '0' && teacher_telephone.charAt(i) <= '9')) {
                    res.put("error_message", "联系方式只能由数字组成！");
                    return res;
                }
            }

            String encodeedPassword = passwordEncoder.encode("123456");
            userMapper.insertUser(null, teacher_id.toString(), encodeedPassword, identity, teacher_id);
            teacherMapper.insertTeacher(teacher_id, teacher_name, teacher_gender, teacher_age, teacher_telephone);
        } else if (identity.equals("admin")) {
            res.put("error_message", "滚");
            return res;
        }

        res.put("error_message", "success");
        return res;
    }

    @Override
    public Map<String, String> deleteUserService(Map<String, String> data) {
        return null;
    }

    @Override
    public Map<String, String> updateUserService(Map<String, String> data) {
        return null;
    }
}
