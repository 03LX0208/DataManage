package com.datamanagebackend.controller.course;

import com.datamanagebackend.pojo.Course;
import com.datamanagebackend.service.course.GetAllCoursesService;
import com.datamanagebackend.service.impl.course.CourseCrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetAllCoursesController {
    @Autowired
    private GetAllCoursesService getAllCoursesService;

    @GetMapping("/api/course/get-all/")
    public List<Course> getAllCourses() {
        return getAllCoursesService.getAllCourses();
    }
}
