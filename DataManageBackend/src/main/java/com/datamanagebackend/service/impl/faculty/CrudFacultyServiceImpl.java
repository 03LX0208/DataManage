package com.datamanagebackend.service.impl.faculty;

import com.datamanagebackend.mapper.FacultyMapper;
import com.datamanagebackend.mapper.StudentMapper;
import com.datamanagebackend.pojo.Faculty;
import com.datamanagebackend.pojo.Student;
import com.datamanagebackend.service.faculty.CrudFacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CrudFacultyServiceImpl implements CrudFacultyService {
    @Autowired
    private FacultyMapper facultyMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Map<String, String> addFaculty(Map<String, String> data) {
        Map<String, String> res = new HashMap<>();

        String _faculty_id = data.get("faculty_id");
        if (_faculty_id == null || _faculty_id.trim().length() == 0) {
            res.put("error_message", "学院编号不能为空！");
            return res;
        }
        for (int i = 0; i < _faculty_id.length(); i++) {
            if (!(_faculty_id.charAt(i) >= '0' && _faculty_id.charAt(i) <= '9')) {
                res.put("error_message", "学院编号只能由数字组成！");
                return res;
            }
        }
        Integer faculty_id = Integer.parseInt(_faculty_id);
        Faculty faculty = facultyMapper.selectFacultyByFacultyId(faculty_id);
        if (faculty != null) {
            res.put("error_message", "学院编号已存在！");
            return res;
        }

        String faculty_name = data.get("faculty_name");
        if (faculty_name == null || faculty_name.trim().length() == 0) {
            res.put("error_message", "学院编号不能为空！");
            return res;
        }
        faculty_name = faculty_name.trim();

        String faculty_site = data.get("faculty_site");
        if (faculty_site == null || faculty_site.trim().length() == 0) {
            res.put("error_message", "学院编号不能为空！");
            return res;
        }
        faculty_site = faculty_site.trim();

        facultyMapper.insertFaculty(faculty_id, faculty_name, faculty_site);

        res.put("error_message", "success");
        return res;
    }

    @Override
    public Map<String, String> deleteFaculty(Map<String, String> data) {
        Map<String, String> res = new HashMap<>();
        if (data.get("faculty_id") == null) {
            res.put("error_message", "学院不存在！");
            return res;
        }

        Integer faculty_id = Integer.parseInt(data.get("faculty_id"));

        List<Student> studentList = studentMapper.selectStudentByFacultyId(faculty_id);
        if (studentList != null) {
            res.put("error_message", "仍有该学院的学生！");
            return res;
        }

        facultyMapper.deleteFacultyByFacultyId(faculty_id);
        res.put("error_message", "success");
        return res;
    }

    @Override
    public Map<String, String> updateFaculty(Map<String, String> data) {
        return null;
    }
}
