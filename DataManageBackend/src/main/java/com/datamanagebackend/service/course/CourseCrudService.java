package com.datamanagebackend.service.course;

import java.util.Map;

public interface CourseCrudService {
    Map<String, String> updateCourse(Map<String, String> data);
    Map<String, String> addCourse(Map<String, String> data);
    Map<String, String> deleteCourse(Map<String, String> data);
}
