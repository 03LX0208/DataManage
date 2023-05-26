package com.datamanagebackend.service.impl.student_section;

import com.datamanagebackend.mapper.*;
import com.datamanagebackend.pojo.*;
import com.datamanagebackend.service.student_section.QueryStudentSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QueryStudentSectionServiceImpl implements QueryStudentSectionService {
    @Autowired
    private StudentSectionMapper studentSectionMapper;

    @Autowired
    private SectionMapper sectionMapper;

    @Autowired
    private ClassroomMapper classroomMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Map<String, Object>> selectAllSectionByStudentId(Integer student_id) {
        return studentSectionMapper.getStudentSectionByStudentId(student_id);
    }

    @Override
    public List<Map<String, Object>> getSectionNowCount() {
        return studentSectionMapper.getSectionNowCountList();
    }

    @Override
    public List<Map<String, Object>> getStudentGrade(Integer student_id) {
        return studentSectionMapper.getStudentGradeByStudentId(student_id);
    }

    @Override
    public List<Integer> getGradeGraph(Integer section_id) {
        List<Integer> res = new ArrayList<>();
        int cnt1 = 0, cnt2 = 0, cnt3 = 0, cnt4 = 0, cnt5 = 0;
        List<StudentSection> sections = studentSectionMapper.selectListBySectionId(section_id);
        for (StudentSection section : sections) {
            if (section.getGrade() == null) continue;
            Integer x = section.getGrade();
            if (x < 60) ++cnt1;
            else if (x < 70) ++cnt2;
            else if (x < 80) ++cnt3;
            else if (x < 90) ++cnt4;
            else ++cnt5;
        }
        res.add(cnt1);
        res.add(cnt2);
        res.add(cnt3);
        res.add(cnt4);
        res.add(cnt5);
        return res;
    }

    @Override
    public List<List<Map<String, String>>> getTimeTable(Integer student_id) {
        List<List<Map<String, String>>> res = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            List<Map<String, String>> col = new ArrayList<>();
            for (int j = 0; j < 7; j++) {
                col.add(new HashMap<>());
            }
            res.add(col);
        }


        String[] days = {"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
        String[] hours = {"8:00-8:50", "9:00-9:50", "10:00-10:50", "11:00-11:50", "14:00-14:50", "15:00-15:50", "16:00-16:50", "17:00-17:50"};
        Map<String, Integer> has = new HashMap<>();

        int idx = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 8; j++) {
                has.put(days[i] + hours[j], idx++);
            }
        }

        List<StudentSection> studentSectionList = studentSectionMapper.selectListByStudentId(student_id);
        for (StudentSection studentSection : studentSectionList) {
            Section section = sectionMapper.selectSectionBySectionId(studentSection.getSectionId());
            if (section.getIsCompleted() == 1) continue;
            String[] sectionTime = section.getSectionTime().split("/");
            for (int i = 0; i < sectionTime.length; i += 2) {
                int k = has.get(sectionTime[i] + sectionTime[i + 1]);
                Classroom classroom = classroomMapper.selectClassroomByClassroomId(section.getClassroomId());
                Teacher teacher = teacherMapper.selectTeacherByTeacherId(section.getTeacherId());
                Course course = courseMapper.selectCourseByCourseId(section.getCourseId());
                res.get(k % 8).get(k / 8).put("course_name", course.getCourseName());
                res.get(k % 8).get(k / 8).put("teacher_name", teacher.getTeacherName());
                res.get(k % 8).get(k / 8).put("classroom", classroom.getClassroomSite() + classroom.getClassroomName());
            }
        }

        return res;
    }
}
