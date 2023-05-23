package com.datamanagebackend.service.impl.course;

import com.datamanagebackend.mapper.CourseMapper;
import com.datamanagebackend.pojo.Course;
import com.datamanagebackend.service.course.GetAllCoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllCoursesServiceImpl implements GetAllCoursesService {
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> getAllCourses() {
        return courseMapper.getAll();
    }
}
