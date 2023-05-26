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

    @GetMapping("/section-now-count/")
    public List<Map<String, Object>> getSectionNowCount() {
        return queryService.getSectionNowCount();
    }

    @GetMapping("/student-score/")
    public List<Map<String, Object>> getStudentScoreByStudentId(@RequestParam Map<String, String> data) {
        return queryService.getStudentGrade(Integer.parseInt(data.get("student_id")));
    }

    @GetMapping("/grade-graph/")
    public List<Integer> getGradeGraph(@RequestParam Map<String, String> data) {
        return queryService.getGradeGraph(Integer.parseInt(data.get("section_id")));
    }

    @GetMapping("/time-table/")
    public List<List<Map<String, String>>> getTimeTable(@RequestParam Map<String, String> data) {
        return queryService.getTimeTable(Integer.parseInt(data.get("student_id")));
    }
}
