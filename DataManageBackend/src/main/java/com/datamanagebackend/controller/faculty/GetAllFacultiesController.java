package com.datamanagebackend.controller.faculty;

import com.datamanagebackend.pojo.Faculty;
import com.datamanagebackend.service.faculty.GetAllFacultiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetAllFacultiesController {
    @Autowired
    private GetAllFacultiesService getAllFacultiesService;

    @GetMapping("/api/faculty/get-all/")
    public List<Faculty> getAll() {
        return getAllFacultiesService.gerAll();
    }
}
