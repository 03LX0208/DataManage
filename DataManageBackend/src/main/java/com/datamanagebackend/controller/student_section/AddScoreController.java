package com.datamanagebackend.controller.student_section;

import com.datamanagebackend.service.student_section.AddScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AddScoreController {
    @Autowired
    private AddScoreService addScoreService;

    @PostMapping("/api/student-section/add-score/")
    public Map<String, String> addScore(@RequestParam Map<String, String> data) {
        return addScoreService.addScore(data);
    }
}
