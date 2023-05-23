package com.datamanagebackend.service.impl.section;

import com.datamanagebackend.mapper.ClassroomMapper;
import com.datamanagebackend.mapper.CourseMapper;
import com.datamanagebackend.mapper.FacultyMapper;
import com.datamanagebackend.mapper.SectionMapper;
import com.datamanagebackend.pojo.Classroom;
import com.datamanagebackend.pojo.Course;
import com.datamanagebackend.pojo.Faculty;
import com.datamanagebackend.pojo.Section;
import com.datamanagebackend.service.section.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QueryServiceImpl implements QueryService {
    @Autowired
    private SectionMapper sectionMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private FacultyMapper facultyMapper;

    @Autowired
    private ClassroomMapper classroomMapper;

    @Override
    public List<Map<String, String>> getCoursesByTeacherId(Integer teacher_id) {
        List<Map<String, String>> res = new ArrayList<>();
        List<Section> sections = sectionMapper.selectSectionsByTeacherId(teacher_id);

        for (Section section : sections) {
            Map<String, String> one = new HashMap<>();
            Course course = courseMapper.selectCourseByCourseId(section.getCourseId());
            one.put("course_id", course.getCourseId().toString());
            one.put("section_id", section.getSectionId().toString());
            one.put("course_name", course.getCourseName());
            one.put("course_credit", course.getCourseCredit().toString());
            one.put("course_period", course.getCoursePeriod().toString());
            Faculty faculty = facultyMapper.selectFacultyByFacultyId(course.getFacultyId());
            Classroom classroom = classroomMapper.selectClassroomByClassroomId(section.getClassroomId());
            one.put("classroom_name", classroom.getClassroomSite()+classroom.getClassroomName());
            one.put("faculty_name", faculty.getFacultyName());

            StringBuilder time = new StringBuilder();
            String[] sec_time = section.getSectionTime().split("/");
            for (int i = 0; i < sec_time.length; i += 2) {
                time.append(sec_time[i]).append("「").append(sec_time[i + 1]).append("」 ");
            }
            one.put("section_time", time.toString());

            res.add(one);
        }

        return res;
    }
}
