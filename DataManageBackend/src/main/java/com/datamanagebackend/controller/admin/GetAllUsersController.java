package com.datamanagebackend.controller.admin;

import com.datamanagebackend.pojo.User;
import com.datamanagebackend.service.admin.GetAllUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetAllUsersController {
    @Autowired
    private GetAllUsersService getAllUsersService;

    @GetMapping("/api/admin/get-all-user/")
    public List<User> getAllUsers() {
        return getAllUsersService.getAllUsers();
    }
}
