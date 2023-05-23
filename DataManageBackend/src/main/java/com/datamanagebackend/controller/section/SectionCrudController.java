package com.datamanagebackend.controller.section;

import com.datamanagebackend.pojo.Section;
import com.datamanagebackend.service.section.SectionCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/section/")
public class SectionCrudController {
    @Autowired
    private SectionCrudService sectionCrudService;

    @PostMapping("/add/")
    public Map<String, String> addSection(@RequestParam Map<String, String> data) {
        return sectionCrudService.addSection(data);
    }

    @PostMapping("/delete/")
    public Map<String, String> deleteSection(@RequestParam Map<String, String> data) {
        return sectionCrudService.deleteSection(data);
    }

    @PostMapping("/update/")
    public Map<String, String> updateSection(@RequestParam Map<String, String> data) {
        return sectionCrudService.updateSection(data);
    }
}
