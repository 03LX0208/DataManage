package com.datamanagebackend.controller.classroom;

import com.datamanagebackend.service.classroom.ClassroomCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/classroom/")
public class ClassroomCrudController {
    @Autowired
    private ClassroomCrudService classroomCrudService;

    @PostMapping("/add/")
    public Map<String, String> addClassroom(@RequestParam Map<String, String> data) {
        return classroomCrudService.addClassroom(data);
    }

    @PostMapping("/delete/")
    public Map<String, String> deleteClassroom(@RequestParam Map<String, String> data) {
        return classroomCrudService.deleteClassroom(data);
    }
}
