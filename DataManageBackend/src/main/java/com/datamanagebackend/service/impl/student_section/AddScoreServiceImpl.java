package com.datamanagebackend.service.impl.student_section;

import com.datamanagebackend.mapper.StudentSectionMapper;
import com.datamanagebackend.service.student_section.AddScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AddScoreServiceImpl implements AddScoreService {
    @Autowired
    private StudentSectionMapper studentSectionMapper;

    @Override
    public Map<String, String> addScore(Map<String, String> data) {
        Map<String, String> res = new HashMap<>();

        Integer section_id = Integer.parseInt(data.get("section_id"));
        Integer student_id = Integer.parseInt(data.get("student_id"));
        String _grade = data.get("grade");
        if (_grade == null || _grade.trim().length() == 0) {
            res.put("error_message", "成绩不能为空！");
            return res;
        }
        for (int i = 0; i < _grade.length(); i++) {
            if (!(_grade.charAt(i) >= '0' && _grade.charAt(i) <= '9')) {
                res.put("error_message", "成绩只能由数字组成！");
                return res;
            }
        }

        studentSectionMapper.updateGrade(student_id, section_id, Integer.parseInt(_grade));
        res.put("error_message", "success");
        return res;
    }
}
