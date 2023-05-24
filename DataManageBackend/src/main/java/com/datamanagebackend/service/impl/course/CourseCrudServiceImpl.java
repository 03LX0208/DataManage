package com.datamanagebackend.service.impl.course;

import com.datamanagebackend.mapper.CourseMapper;
import com.datamanagebackend.mapper.FacultyMapper;
import com.datamanagebackend.mapper.PilotCourseMapper;
import com.datamanagebackend.mapper.SectionMapper;
import com.datamanagebackend.pojo.Course;
import com.datamanagebackend.pojo.Faculty;
import com.datamanagebackend.pojo.Section;
import com.datamanagebackend.pojo.Student;
import com.datamanagebackend.service.course.CourseCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseCrudServiceImpl implements CourseCrudService {
    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private FacultyMapper facultyMapper;

    @Autowired
    private PilotCourseMapper pilotCourseMapper;

    @Autowired
    private SectionMapper sectionMapper;

    @Override
    public Map<String, String> addCourse(Map<String, String> data) {
        Map<String, String> res = new HashMap<>();
        String _course_id = data.get("course_id");
        if (_course_id == null || _course_id.trim().length() == 0) {
            res.put("error_message", "课程编号不能为空！");
            return res;
        }
        for (int i = 0; i < _course_id.length(); i++) {
            if (!(_course_id.charAt(i) >= '0' && _course_id.charAt(i) <= '9')) {
                res.put("error_message", "课程编号只能由数字组成！");
                return res;
            }
        }
        Integer course_id = Integer.parseInt(_course_id);
        Course course = courseMapper.selectCourseByCourseId(course_id);
        if (course != null) {
            res.put("error_message", "课程编号已存在！");
            return res;
        }

        String course_name = data.get("course_name");
        if (course_name == null || course_name.trim().length() == 0) {
            res.put("error_message", "课程名称不能为空！");
            return res;
        }
        course_name = course_name.trim();

        String _course_period = data.get("course_period");
        if (_course_period == null || _course_period.trim().length() == 0) {
            res.put("error_message", "课程学时不能为空！");
            return res;
        }
        for (int i = 0; i < _course_period.length(); i++) {
            if (!(_course_period.charAt(i) >= '0' && _course_period.charAt(i) <= '9')) {
                res.put("error_message", "课程学时只能由数字组成！");
                return res;
            }
        }
        Integer course_period = Integer.parseInt(_course_period);

        String _course_credit = data.get("course_credit");
        if (_course_credit == null || _course_credit.trim().length() == 0) {
            res.put("error_message", "课程学分不能为空！");
            return res;
        }
        for (int i = 0; i < _course_credit.length(); i++) {
            if (!(_course_credit.charAt(i) >= '0' && _course_credit.charAt(i) <= '9')) {
                res.put("error_message", "课程学分只能由数字组成！");
                return res;
            }
        }
        Integer course_credit = Integer.parseInt(_course_credit);

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
        Faculty faculty = facultyMapper.selectFacultyByFacultyId(faculty_id);
        if (faculty == null) {
            res.put("error_message", "学院不存在！");
            return res;
        }

        courseMapper.insertCourse(course_id, course_name, course_period,course_credit,faculty_id);
        if (data.get("pilot_course_id") != null) {
            String pilot_course_id = data.get("pilot_course_id");
            if (pilot_course_id.trim().length() == 0) {
                res.put("error_message", "success");
                return res;
            }

            String[] s = pilot_course_id.split(" ");
            for (String t : s) {
                Integer pilotCourseId = Integer.parseInt(t);
                pilotCourseMapper.insertPilotCourse(course_id, pilotCourseId);
            }
        }

        res.put("error_message", "success");
        return res;
    }

    @Override
    public Map<String, String> deleteCourse(Map<String, String> data) {
        Map<String, String> res = new HashMap<>();
        Integer course_id = Integer.parseInt(data.get("course_id"));



        List<Section> sectionList = sectionMapper.selectSectionByCourseId(course_id);
        if (sectionList.size() != 0) {
            res.put("error_message", "该课程已开课！");
            return res;
        }

        courseMapper.deleteCourseByCourseId(course_id);
        res.put("error_message", "success");
        return res;
    }

    @Override
    public Map<String, String> updateCourse(Map<String, String> data) {
        Map<String, String> res = new HashMap<>();
        String _course_id = data.get("course_id");
        if (_course_id == null || _course_id.trim().length() == 0) {
            res.put("error_message", "课程编号不能为空！");
            return res;
        }
        for (int i = 0; i < _course_id.length(); i++) {
            if (!(_course_id.charAt(i) >= '0' && _course_id.charAt(i) <= '9')) {
                res.put("error_message", "课程编号只能由数字组成！");
                return res;
            }
        }
        Integer course_id = Integer.parseInt(_course_id);

        String course_name = data.get("course_name");
        if (course_name == null || course_name.trim().length() == 0) {
            res.put("error_message", "学院名称不能为空！");
            return res;
        }
        course_name = course_name.trim();

        String _course_period = data.get("course_period");
        if (_course_period == null || _course_period.trim().length() == 0) {
            res.put("error_message", "课程学时不能为空！");
            return res;
        }
        for (int i = 0; i < _course_period.length(); i++) {
            if (!(_course_period.charAt(i) >= '0' && _course_period.charAt(i) <= '9')) {
                res.put("error_message", "课程学时只能由数字组成！");
                return res;
            }
        }
        Integer course_period = Integer.parseInt(_course_period);

        String _course_credit = data.get("course_credit");
        if (_course_credit == null || _course_credit.trim().length() == 0) {
            res.put("error_message", "课程学分不能为空！");
            return res;
        }
        for (int i = 0; i < _course_credit.length(); i++) {
            if (!(_course_credit.charAt(i) >= '0' && _course_credit.charAt(i) <= '9')) {
                res.put("error_message", "课程学分只能由数字组成！");
                return res;
            }
        }
        Integer course_credit = Integer.parseInt(_course_credit);

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

        courseMapper.updateCourseByCourseId(course_id, course_name, course_period,course_credit,faculty_id);

        res.put("error_message", "success");
        return res;
    }
}
