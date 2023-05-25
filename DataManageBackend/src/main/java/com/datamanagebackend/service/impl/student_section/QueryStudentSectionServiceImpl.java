package com.datamanagebackend.service.impl.student_section;

import com.datamanagebackend.mapper.StudentSectionMapper;
import com.datamanagebackend.service.student_section.QueryStudentSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QueryStudentSectionServiceImpl implements QueryStudentSectionService {
    @Autowired
    private StudentSectionMapper studentSectionMapper;

    @Override
    public List<Map<String, Object>> selectAllSectionByStudentId(Integer student_id) {
        return studentSectionMapper.getStudentSectionByStudentId(student_id);
    }

    @Override
    public List<Map<String, Object>> getSectionNowCount() {
        return studentSectionMapper.getSectionNowCountList();
    }

    @Override
    public List<Map<String, Object>> getStudentGrade(Integer student_id) {
        return studentSectionMapper.getStudentGradeByStudentId(student_id);
    }
}
