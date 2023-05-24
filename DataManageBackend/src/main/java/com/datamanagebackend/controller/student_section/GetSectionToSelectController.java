package com.datamanagebackend.controller.student_section;

import com.datamanagebackend.service.student_section.GetSectionToSelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class GetSectionToSelectController {
    @Autowired
    private GetSectionToSelectService getSectionToSelectService;

    @GetMapping("/api/student-section/get-all-section/")
    public List<Map<String, Object>> getSection() {
        return getSectionToSelectService.getSection();
    }
}
