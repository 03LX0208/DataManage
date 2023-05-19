package com.datamanagebackend.service.faculty;

import java.util.Map;

public interface CrudFacultyService {
    Map<String, String> addFaculty(Map<String, String> data);
    Map<String, String> deleteFaculty(Map<String, String> data);
    Map<String, String> updateFaculty(Map<String, String> data);
}
