package com.datamanagebackend.service.student_section;

import java.util.List;
import java.util.Map;

public interface QueryStudentSectionService {
    List<Map<String, Object>> selectAllSectionByStudentId(Integer student_id);
    List<Map<String, Object>> getSectionNowCount();
    List<Map<String, Object>> getStudentGrade(Integer student_id);
}
