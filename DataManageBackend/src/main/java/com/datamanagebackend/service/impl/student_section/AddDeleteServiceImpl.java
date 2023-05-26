package com.datamanagebackend.service.impl.student_section;

import com.datamanagebackend.mapper.SectionMapper;
import com.datamanagebackend.mapper.StudentSectionMapper;
import com.datamanagebackend.pojo.Section;
import com.datamanagebackend.pojo.StudentSection;
import com.datamanagebackend.service.student_section.AddDeleteService;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class AddDeleteServiceImpl implements AddDeleteService {
    @Autowired
    private StudentSectionMapper studentSectionMapper;

    @Autowired
    private SectionMapper sectionMapper;

    @Override
    public Map<String, String> addSection(Map<String, String> data) {
        Map<String, String> res = new HashMap<>();
        Integer student_id = Integer.parseInt(data.get("student_id"));
        Integer section_id = Integer.parseInt(data.get("section_id"));

        List<StudentSection> studentSectionList = studentSectionMapper.selectListByStudentId(student_id);
        for (StudentSection section : studentSectionList) {
            if (Objects.equals(section.getSectionId(), section_id)) {
                res.put("error_message", "您已经选过该课程了!");
                return res;
            }
        }



        Section section = sectionMapper.selectSectionBySectionId(section_id);
        List<StudentSection> list = studentSectionMapper.selectListBySectionId(section_id);
        if (list.size() + 1 > section.getCapacity()) {
            res.put("error_message", "该课程容量已满!");
            return res;
        }

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
