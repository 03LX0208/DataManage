package com.datamanagebackend.controller.pilot_course;

import com.datamanagebackend.service.pilot_course.PilotCourseCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/pilot-course/")
public class PilotCourseCrudController {
    @Autowired
    private PilotCourseCrudService pilotCourseCrudService;

    @PostMapping("/add/")
    public Map<String, String> addPilotCourse(@RequestParam Map<String, String> data) {
        return pilotCourseCrudService.addPilotCourse(data);
    }

    @PostMapping("/delete/")
    public Map<String, String> deletePilotCourse(@RequestParam Map<String, String> data) {
        return pilotCourseCrudService.deletePilotCourse(data);
    }

    @PostMapping("/update/")
    public Map<String, String> updatePilotCourse(@RequestParam Map<String, String> data) {
        return pilotCourseCrudService.updatePilotCourse(data);
    }
}
