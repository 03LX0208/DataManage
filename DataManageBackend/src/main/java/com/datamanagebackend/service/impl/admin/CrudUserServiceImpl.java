package com.datamanagebackend.service.impl.admin;

import com.datamanagebackend.mapper.StudentMapper;
import com.datamanagebackend.mapper.TeacherMapper;
import com.datamanagebackend.pojo.Student;
import com.datamanagebackend.pojo.Teacher;
import com.datamanagebackend.service.admin.CrudUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CrudUserServiceImpl implements CrudUserService {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private TeacherMapper teacherMapper;

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
            studentMapper.insertStudent(student_id, student_name, student_gender, student_enter_date, student_age, student_telephone, faculty_id);
        } else if (identity.equals("teacher")) {
            // 请开始泽普的表演

            // teacherMapper.insertTeacher(teacher_id, teacher_name, teacher_gender, teacher_age, teacher_telephone);
        } else if (identity.equals("admin")) {

        }

        return null;
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
