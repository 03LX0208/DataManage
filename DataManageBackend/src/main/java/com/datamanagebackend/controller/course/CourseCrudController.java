package com.datamanagebackend.controller.course;

import com.datamanagebackend.service.course.CourseCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/course/")
public class CourseCrudController {
    @Autowired
    private CourseCrudService courseCrudService;

    @PostMapping("/add/")
    public Map<String, String> addCourse(@RequestParam Map<String, String> data) {
        return courseCrudService.addCourse(data);
    }

    @PostMapping("/delete/")
    public Map<String, String> deleteCourse(@RequestParam Map<String, String> data) {
        return courseCrudService.deleteCourse(data);
    }

    @PostMapping("/update/")
    public Map<String, String> updateCourse(@RequestParam Map<String, String> data) {
        return courseCrudService.updateCourse(data);
    }
}