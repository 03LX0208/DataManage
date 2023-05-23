package com.datamanagebackend.service.pilot_course;

import java.util.Map;

public interface PilotCourseCrudService {
    Map<String, String> addPilotCourse(Map<String, String> data);
    Map<String, String> deletePilotCourse(Map<String, String> data);
    Map<String, String> updatePilotCourse(Map<String, String> data);
}
