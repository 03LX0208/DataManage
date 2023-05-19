package com.datamanagebackend.controller.admin;

import com.datamanagebackend.service.admin.GetAllUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class GetAllUsersController {
    @Autowired
    private GetAllUsersService getAllUsersService;

    @GetMapping("/api/admin/get-all-user/")
    public List<Map<String, String>> getAllUsers() {
        return getAllUsersService.getAllUsers();
    }
}
