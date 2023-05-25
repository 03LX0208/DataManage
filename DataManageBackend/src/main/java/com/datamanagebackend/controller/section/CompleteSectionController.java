package com.datamanagebackend.controller.section;

import com.datamanagebackend.service.section.CompleteSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CompleteSectionController {
    @Autowired
    private CompleteSectionService completeSectionService;

    @PostMapping("/api/section/complete/")
    public Map<String, String> completeSection(@RequestParam Map<String, String> data) {
        return completeSectionService.completeSection(Integer.parseInt(data.get("section_id")));
    }
}
