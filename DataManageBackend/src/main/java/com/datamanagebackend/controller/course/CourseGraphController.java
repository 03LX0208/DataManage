package com.datamanagebackend.controller.course;

import com.datamanagebackend.service.course.CourseGraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CourseGraphController {
    @Autowired
    private CourseGraphService courseGraphService;

    @GetMapping("/api/course/graph/")
    public Map<String, List<Map<String, String>>> courseGraph(@RequestParam Map<String, String> data) {
        return courseGraphService.GetCourseGraph(data);
    }
}
