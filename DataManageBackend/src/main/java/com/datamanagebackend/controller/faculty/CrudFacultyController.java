package com.datamanagebackend.controller.faculty;

import com.datamanagebackend.service.faculty.CrudFacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/faculty/")
public class CrudFacultyController {
    @Autowired
    private CrudFacultyService crudFacultyService;

    @PostMapping("/add/")
    public Map<String, String> addFaculty(@RequestParam Map<String, String> data) {
        return crudFacultyService.addFaculty(data);
    }

    @PostMapping("/delete/")
    public Map<String, String> deleteFaculty(@RequestParam Map<String, String> data) {
        return crudFacultyService.deleteFaculty(data);
    }

    @PostMapping("/update/")
    public Map<String, String> updateFaculty(@RequestParam Map<String, String> data) {
        return crudFacultyService.updateFaculty(data);
    }
}
