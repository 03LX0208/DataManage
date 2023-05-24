package com.datamanagebackend.service.impl.student_section;

import com.datamanagebackend.mapper.SectionMapper;
import com.datamanagebackend.service.student_section.GetSectionToSelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GetSectionToSelectServiceImpl implements GetSectionToSelectService {
    @Autowired
    private SectionMapper sectionMapper;

    @Override
    public List<Map<String, Object>> getSection() {
        return sectionMapper.getSectionToSelect();
    }
}
