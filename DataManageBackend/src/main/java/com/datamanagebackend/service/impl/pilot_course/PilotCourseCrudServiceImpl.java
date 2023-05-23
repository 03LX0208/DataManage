package com.datamanagebackend.service.impl.pilot_course;

import com.datamanagebackend.mapper.CourseMapper;
import com.datamanagebackend.mapper.PilotCourseMapper;
import com.datamanagebackend.pojo.Course;
import com.datamanagebackend.pojo.Faculty;
import com.datamanagebackend.service.pilot_course.PilotCourseCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PilotCourseCrudServiceImpl implements PilotCourseCrudService {
    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private PilotCourseMapper pilotCourseMapper;
    @Override
    public Map<String, String> addPilotCourse(Map<String, String> data) {
        Map<String, String> res = new HashMap<>();

        String _wanted_course= data.get("wanted_course");
        if (_wanted_course == null || _wanted_course.trim().length() == 0) {
            res.put("error_message", "课程号不能为空！");
            return res;
        }
        for (int i = 0; i < _wanted_course.length(); i++) {
            if (!(_wanted_course.charAt(i) >= '0' && _wanted_course.charAt(i) <= '9')) {
                res.put("error_message", "课程号只能由数字构成！");
                return res;
            }
        }
        Integer wanted_course = Integer.parseInt(_wanted_course);
        Course course = courseMapper.selectCourseByCourseId(wanted_course);
        if (course == null) {
            res.put("error_message", "课程不存在！");
            return res;
        }

        String _needed_course= data.get("needed_course");
        if (_needed_course == null || _needed_course.trim().length() == 0) {
            res.put("error_message", "课程号不能为空！");
            return res;
        }
        for (int i = 0; i < _needed_course.length(); i++) {
            if (!(_needed_course.charAt(i) >= '0' && _needed_course.charAt(i) <= '9')) {
                res.put("error_message", "课程号只能由数字构成！");
                return res;
            }
        }
        Integer needed_course = Integer.parseInt(_needed_course);
        Course course1 = courseMapper.selectCourseByCourseId(needed_course);
        if (course1 == null) {
            res.put("error_message", "课程不存在！");
            return res;
        }

        pilotCourseMapper.insertPilotCourse(wanted_course,needed_course);

        res.put("error_message", "success");
        return res;
    }

    @Override
    public Map<String, String> deletePilotCourse(Map<String, String> data) {
        Map<String, String> res = new HashMap<>();

        String _wanted_course= data.get("wanted_course");
        if (_wanted_course == null || _wanted_course.trim().length() == 0) {
            res.put("error_message", "课程号不能为空！");
            return res;
        }
        for (int i = 0; i < _wanted_course.length(); i++) {
            if (!(_wanted_course.charAt(i) >= '0' && _wanted_course.charAt(i) <= '9')) {
                res.put("error_message", "课程号只能由数字构成！");
                return res;
            }
        }
        Integer wanted_course = Integer.parseInt(_wanted_course);
        Course course = courseMapper.selectCourseByCourseId(wanted_course);
        if (course == null) {
            res.put("error_message", "课程不存在！");
            return res;
        }

        String _needed_course= data.get("needed_course");
        if (_needed_course == null || _needed_course.trim().length() == 0) {
            res.put("error_message", "课程号不能为空！");
            return res;
        }
        for (int i = 0; i < _needed_course.length(); i++) {
            if (!(_needed_course.charAt(i) >= '0' && _needed_course.charAt(i) <= '9')) {
                res.put("error_message", "课程号只能由数字构成！");
                return res;
            }
        }
        Integer needed_course = Integer.parseInt(_needed_course);
        Course course1 = courseMapper.selectCourseByCourseId(needed_course);
        if (course1 == null) {
            res.put("error_message", "课程不存在！");
            return res;
        }

        pilotCourseMapper.deletePilotByCourseId(wanted_course,needed_course);

        res.put("error_message", "success");
        return res;
    }

    @Override
    public Map<String, String> updatePilotCourse(Map<String, String> data) {
        return null;
    }
}
