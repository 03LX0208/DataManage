package com.datamanagebackend.service.section;

import com.datamanagebackend.pojo.Section;

import java.util.Map;

public interface SectionCrudService {
    Map<String, String> addSection(Map<String, String> data);
    Map<String, String> deleteSection(Map<String, String> data);
    Map<String, String> updateSection(Map<String, String> data);
}
