package com.datamanagebackend.service.impl.section;

import com.datamanagebackend.mapper.SectionMapper;
import com.datamanagebackend.service.section.CompleteSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CompleteServiceImpl implements CompleteSectionService {
    @Autowired
    private SectionMapper sectionMapper;

    @Override
    public Map<String, String> completeSection(Integer section_id) {
        Map<String, String> res = new HashMap<>();
        sectionMapper.completeSectionBySectionId(section_id);
        res.put("error_message", "success");
        return res;
    }
}
