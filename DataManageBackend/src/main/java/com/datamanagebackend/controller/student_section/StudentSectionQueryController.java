package com.datamanagebackend.controller.student_section;

import com.datamanagebackend.service.student_section.QueryStudentSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/student-section/query/")
public class StudentSectionQueryController {
    @Autowired
    private QueryStudentSectionService queryService;

    @GetMapping("/all-section-by-student-id/")
    public List<Map<String, Object>> selectAllSectionByStudentId(@RequestParam Map<String, String> data) {
        return queryService.selectAllSectionByStudentId(Integer.parseInt(data.get("student_id")));
    }
}
