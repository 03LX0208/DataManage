package com.datamanagebackend.controller.classroom;

import com.datamanagebackend.pojo.Classroom;
import com.datamanagebackend.service.classroom.GetAllClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetAllClassroomController {
    @Autowired
    private GetAllClassroomService getAllClassroomService;

    @GetMapping("/api/classroom/get-all/")
    public List<Classroom> getAllClassroom() {
        return getAllClassroomService.getAllClassroom();
    }
}
