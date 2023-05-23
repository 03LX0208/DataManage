package com.datamanagebackend.service.classroom;

import java.util.Map;

public interface ClassroomCrudService {
    Map<String, String> addClassroom(Map<String, String> data);
    Map<String, String> deleteClassroom(Map<String, String> data);
}
