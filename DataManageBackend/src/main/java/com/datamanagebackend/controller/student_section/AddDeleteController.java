package com.datamanagebackend.controller.student_section;

import com.datamanagebackend.service.student_section.AddDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/student-section/")
public class AddDeleteController {
    @Autowired
    private AddDeleteService addDeleteService;

    @PostMapping("/add/")
    public Map<String, String> addSection(@RequestParam Map<String, String> data) {
        return addDeleteService.addSection(data);
    }

    @PostMapping("/delete/")
    public Map<String, String> deleteSection(@RequestParam Map<String, String> data) {
        return addDeleteService.deleteSection(data);
    }
}
