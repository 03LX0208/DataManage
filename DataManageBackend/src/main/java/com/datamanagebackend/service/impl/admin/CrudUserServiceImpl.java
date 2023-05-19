package com.datamanagebackend.service.impl.admin;

import com.datamanagebackend.mapper.AdminMapper;
import com.datamanagebackend.mapper.StudentMapper;
import com.datamanagebackend.mapper.TeacherMapper;
import com.datamanagebackend.mapper.UserMapper;
import com.datamanagebackend.pojo.Admin;
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
    private AdminMapper adminMapper;

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
            if (data.get("admin_id") == null) {
                res.put("error_message", "教学号不能为空！");
                return res;
            }

            String _admin_id = data.get("admin_id");
            if (_admin_id == null || _admin_id.trim().length() == 0) {
                res.put("error_message", "教师号不能为空！");
                return res;
            }
            for (int i = 0; i < _admin_id.length(); i++) {
                if (!(_admin_id.charAt(i) >= '0' && _admin_id.charAt(i) <= '9')) {
                    res.put("error_message", "教学只能由数字组成！");
                    return res;
                }
            }
            Integer admin_id = Integer.parseInt(_admin_id);
            User admin = userMapper.selectUserByIntUsername(admin_id);
            if (admin != null) {
                res.put("error_message", "该教学号已存在！");
                return res;
            }

            String admin_name = data.get("admin_name");
            if (admin_name == null || admin_name.trim().length() == 0) {
                res.put("error_message", "名称不能为空！");
                return res;
            }
            admin_name = admin_name.trim();

            String admin_gender = data.get("admin_gender");
            if (admin_gender == null || admin_gender.length() == 0) {
                res.put("error_message", "性别不能为空！");
                return res;
            }

            String _admin_age = data.get("admin_age");
            if (_admin_age == null || _admin_age.trim().length() == 0) {
                res.put("error_message", "年龄不能为空！");
                return res;
            }
            for (int i = 0; i < _admin_age.length(); i++) {
                if (!(_admin_age.charAt(i) >= '0' && _admin_age.charAt(i) <= '9')) {
                    res.put("error_message", "年龄只能由数字组成！");
                    return res;
                }
            }
            Integer admin_age = Integer.parseInt(_admin_age);

            String admin_telephone = data.get("admin_telephone");
            if (admin_telephone == null || admin_telephone.trim().length() == 0) {
                res.put("error_message", "联系方式不能为空！");
                return res;
            }
            admin_telephone = admin_telephone.trim();
            for (int i = 0; i < admin_telephone.length(); i++) {
                if (!(admin_telephone.charAt(i) >= '0' && admin_telephone.charAt(i) <= '9')) {
                    res.put("error_message", "联系方式只能由数字组成！");
                    return res;
                }
            }

            String encodeedPassword = passwordEncoder.encode("123456");
            userMapper.insertUser(null, admin_id.toString(), encodeedPassword, identity, admin_id);
            adminMapper.insertAdmin(admin_id, admin_name, admin_gender, admin_age, admin_telephone);
        }

        res.put("error_message", "success");
        return res;
    }

    @Override
    public Map<String, String> deleteUserService(Map<String, String> data) {
        Map<String, String> res = new HashMap<>();
        if (data.get("id") == null) {
            res.put("error_message", "滚");
            return res;
        }

        // 得到身份 然后删除
        User user = userMapper.selectUserByIntUsername(Integer.parseInt(data.get("id")));
        if (user.getIdentity().equals("student")) {
            studentMapper.deleteStudentByStudentId(user.getIntUsername());
        } else if (user.getIdentity().equals("teacher")) {
            teacherMapper.deleteTeacherByTeacherId(user.getIntUsername());
        } else if (user.getIdentity().equals("admin")) {
            adminMapper.deleteAdminByAdminId(user.getIntUsername());
        }

        userMapper.deleteUserByIntUsername(user.getIntUsername());
        res.put("error_message", "success");

        return res;
    }

    @Override
    public Map<String, String> updateUserService(Map<String, String> data) {
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

            studentMapper.updateStudentByStudentId(student_id, student_name, student_gender, student_enter_date, student_age, student_telephone, faculty_id);
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

            teacherMapper.updateTeacherByTeacherId(teacher_id, teacher_name, teacher_gender, teacher_age, teacher_telephone);
        } else if (identity.equals("admin")) {
            if (data.get("admin_id") == null) {
                res.put("error_message", "教学号不能为空！");
                return res;
            }

            String _admin_id = data.get("admin_id");
            if (_admin_id == null || _admin_id.trim().length() == 0) {
                res.put("error_message", "教师号不能为空！");
                return res;
            }
            for (int i = 0; i < _admin_id.length(); i++) {
                if (!(_admin_id.charAt(i) >= '0' && _admin_id.charAt(i) <= '9')) {
                    res.put("error_message", "教学只能由数字组成！");
                    return res;
                }
            }
            Integer admin_id = Integer.parseInt(_admin_id);

            String admin_name = data.get("admin_name");
            if (admin_name == null || admin_name.trim().length() == 0) {
                res.put("error_message", "名称不能为空！");
                return res;
            }
            admin_name = admin_name.trim();

            String admin_gender = data.get("admin_gender");
            if (admin_gender == null || admin_gender.length() == 0) {
                res.put("error_message", "性别不能为空！");
                return res;
            }

            String _admin_age = data.get("admin_age");
            if (_admin_age == null || _admin_age.trim().length() == 0) {
                res.put("error_message", "年龄不能为空！");
                return res;
            }
            for (int i = 0; i < _admin_age.length(); i++) {
                if (!(_admin_age.charAt(i) >= '0' && _admin_age.charAt(i) <= '9')) {
                    res.put("error_message", "年龄只能由数字组成！");
                    return res;
                }
            }
            Integer admin_age = Integer.parseInt(_admin_age);

            String admin_telephone = data.get("admin_telephone");
            if (admin_telephone == null || admin_telephone.trim().length() == 0) {
                res.put("error_message", "联系方式不能为空！");
                return res;
            }
            admin_telephone = admin_telephone.trim();
            for (int i = 0; i < admin_telephone.length(); i++) {
                if (!(admin_telephone.charAt(i) >= '0' && admin_telephone.charAt(i) <= '9')) {
                    res.put("error_message", "联系方式只能由数字组成！");
                    return res;
                }
            }

            adminMapper.updateAdminByAdminId(admin_id, admin_name, admin_gender, admin_age, admin_telephone);
        }

        res.put("error_message", "success");
        return res;
    }

    @Override
    public Map<String, String> getInfoService(Map<String, String> data) {
        Map<String, String> ans = new HashMap<>();

        Integer id = Integer.parseInt(data.get("id"));
        String identity = data.get("identity");
        if (identity.equals("student")) {
            Student student = studentMapper.selectStudentByStudentId(id);
            ans.put("id", student.getStudentId().toString());
            ans.put("name", student.getStudentName());
            ans.put("gender", student.getStudentGender());
            ans.put("age", student.getStudentAge().toString());
            ans.put("faculty_id", student.getFacultyId().toString());
            ans.put("enter_date", student.getStudentEnterDate());
            ans.put("telephone", student.getStudentTelephone());
            ans.put("identity", "学生");
        } else if (identity.equals("teacher")) {
            Teacher teacher = teacherMapper.selectTeacherByTeacherId(id);
            ans.put("id", teacher.getTeacherId().toString());
            ans.put("name", teacher.getTeacherName());
            ans.put("gender", teacher.getTeacherGender());
            ans.put("age", teacher.getTeacherAge().toString());
            ans.put("telephone", teacher.getTeacherTelephone());
            ans.put("identity", "老师");
        } else if (identity.equals("admin")) {
            Admin admin = adminMapper.selectAdminByAdminId(id);
            ans.put("id", admin.getAdminId().toString());
            ans.put("name", admin.getAdminName());
            ans.put("gender", admin.getAdminGender());
            ans.put("age", admin.getAdminAge().toString());
            ans.put("telephone", admin.getAdminTelephone());
            ans.put("identity", "管理员");
        }

        return ans;
    }
}
