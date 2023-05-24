package com.datamanagebackend.service.student_section;

import java.util.Map;

public interface AddDeleteService {
    Map<String, String> addSection(Map<String, String> data);
    Map<String, String> deleteSection(Map<String, String> data);
}
