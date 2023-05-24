package com.datamanagebackend.service.impl.section;

import com.datamanagebackend.mapper.ClassroomMapper;
import com.datamanagebackend.mapper.CourseMapper;
import com.datamanagebackend.mapper.FacultyMapper;
import com.datamanagebackend.mapper.SectionMapper;
import com.datamanagebackend.pojo.Classroom;
import com.datamanagebackend.pojo.Course;
import com.datamanagebackend.pojo.Faculty;
import com.datamanagebackend.pojo.Section;
import com.datamanagebackend.service.impl.section.Util.Timeline;
import com.datamanagebackend.service.section.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Override
    public List<Timeline> getTimelineByTeacherId(Integer teacher_id) {
        List<Timeline> res = new ArrayList<>();
        List<Section> sections = sectionMapper.selectSectionsByTeacherId(teacher_id);

        for (Section section : sections) {
            String[] times = section.getSectionTime().split("/");
            Course course = courseMapper.selectCourseByCourseId(section.getCourseId());
            String name = course.getCourseName();
            Classroom classroom = classroomMapper.selectClassroomByClassroomId(section.getClassroomId());
            String site = classroom.getClassroomSite() + classroom.getClassroomName();
            for (int i = 0; i < times.length; i += 2) {
                res.add(new Timeline(times[i] + times[i + 1], site, name));
            }
        }

        res.sort((o1, o2) -> {
            String[] days = {"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
            String[] hours = {"8:00-8:50", "9:00-9:50", "10:00-10:50", "11:00-11:50", "14:00-14:50", "15:00-15:50", "16:00-16:50", "17:00-17:50"};
            int d1 = 0, d2 = 0;
            String day1 = o1.getTime().substring(0, 2), day2 = o2.getTime().substring(0, 2);
            for (int i = 0; i < days.length; i++) {
                if (days[i].equals(day1)) d1 = i;
                if (days[i].equals(day2)) d2 = i;
            }
            if (d1 != d2) return Integer.compare(d1, d2);
            else {
                int h1 = 0, h2 = 0;
                String t1 = o1.getTime().substring(2), t2 = o2.getTime().substring(2);
                for (int i = 0; i < hours.length; i++) {
                    if (hours[i].equals(t1)) h1 = i;
                    if (hours[i].equals(t2)) h2 = i;
                }
                return Integer.compare(h1, h2);
            }
        });

        return res;
    }
}
