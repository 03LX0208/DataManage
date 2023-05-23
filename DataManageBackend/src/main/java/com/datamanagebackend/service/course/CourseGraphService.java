package com.datamanagebackend.service.course;

import java.util.List;
import java.util.Map;

public interface CourseGraphService {
    Map<String, List<Map<String, String>>> GetCourseGraph(Map<String, String> data);
}
