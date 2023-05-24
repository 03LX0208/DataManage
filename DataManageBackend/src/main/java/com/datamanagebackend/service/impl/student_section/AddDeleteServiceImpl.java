package com.datamanagebackend.service.impl.student_section;

import com.datamanagebackend.mapper.StudentSectionMapper;
import com.datamanagebackend.service.student_section.AddDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AddDeleteServiceImpl implements AddDeleteService {
    @Autowired
    private StudentSectionMapper studentSectionMapper;

    @Override
    public Map<String, String> addSection(Map<String, String> data) {
        Map<String, String> res = new HashMap<>();
        Integer student_id = Integer.parseInt(data.get("student_id"));
        Integer section_id = Integer.parseInt(data.get("section_id"));
        studentSectionMapper.insertStudentSection(student_id, section_id, null);

        res.put("error_message", "success");
        return res;
    }

    @Override
    public Map<String, String> deleteSection(Map<String, String> data) {
        Map<String, String> res = new HashMap<>();

        Integer student_id = Integer.parseInt(data.get("student_id"));
        Integer section_id = Integer.parseInt(data.get("section_id"));
        studentSectionMapper.deleteByStudentIdSectionId(student_id, section_id);

        res.put("error_message", "success");
        return res;
    }
}
