package com.datamanagebackend.service.section;

import java.util.List;
import java.util.Map;

public interface QueryService {
    List<Map<String, String>> getCoursesByTeacherId(Integer teacher_id);
}
