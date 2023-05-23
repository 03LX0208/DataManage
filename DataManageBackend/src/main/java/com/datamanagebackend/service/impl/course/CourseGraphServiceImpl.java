package com.datamanagebackend.service.impl.course;

import com.datamanagebackend.mapper.CourseMapper;
import com.datamanagebackend.mapper.PilotCourseMapper;
import com.datamanagebackend.pojo.Course;
import com.datamanagebackend.pojo.PilotCourse;
import com.datamanagebackend.service.course.CourseGraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseGraphServiceImpl implements CourseGraphService {
    @Autowired
    private PilotCourseMapper pilotCourseMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Map<String, List<Map<String, String>>> GetCourseGraph(Map<String, String> data) {
        Map<String, List<Map<String, String>>> res = new HashMap<>();
        List<Map<String, String>> Coordinate = new ArrayList<>();
        List<Map<String, String>> Link = new ArrayList<>();

        List<List<String>> list = new ArrayList<>();

        // 确定依赖关系
        Integer courseId = Integer.parseInt(data.get("course_id"));
        Map<Integer, Boolean> visit = new HashMap<>();
        Queue<Integer> qu = new LinkedList<>();
        qu.add(courseId);

        while (!qu.isEmpty()) {
            int size = qu.size();
            List<String> floor = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Integer wanted = qu.poll();
                if (visit.containsKey(wanted)) continue;
                visit.put(wanted, true);
                String targetName = courseMapper.selectCourseByCourseId(wanted).getCourseName();
                floor.add(targetName);

                List<PilotCourse> pilotCourseList = pilotCourseMapper.selectPilotCoursesByWantedCourse(wanted);
                for (PilotCourse course : pilotCourseList) {
                    int id = course.getNeededCourse();
                    qu.add(id);
                    String sourceName = courseMapper.selectCourseByCourseId(id).getCourseName();
                    Map<String, String> temp = new HashMap<>();
                    temp.put("source", sourceName);
                    temp.put("target", targetName);
                    Link.add(temp);
                }
            }
            list.add(floor);
        }

        int Y = 100;
        for (List<String> strings : list) {
            int x = 100;
            for (String name : strings) {
                Map<String, String> node = new HashMap<>();
                node.put("name", name);
                if (Y == 100) {
                    x = 500;
                    node.put("x", Integer.toString(x));
                } else {
                    node.put("x", Integer.toString(x));
                    x += 100;
                }
                node.put("y", Integer.toString(Y));
                Coordinate.add(node);
            }
            Y += 100;
        }

        // 偏移
        int idx = 0;
        for (List<String> strings : list) {
            int sz = strings.size();
            int delta = 500 - (Integer.parseInt(Coordinate.get(idx + sz - 1).get("x")) + Integer.parseInt(Coordinate.get(idx).get("x"))) / 2;
            for (int i = 0; i < sz; i++) {
                Coordinate.get(idx).put("x", Integer.toString(Integer.parseInt(Coordinate.get(idx).get("x")) + delta));
                idx++;
            }
        }

        res.put("coordinate", Coordinate);
        res.put("link_", Link);

        return res;
    }
}
