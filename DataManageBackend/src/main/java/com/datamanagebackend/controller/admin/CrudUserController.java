package com.datamanagebackend.controller.admin;

import com.datamanagebackend.service.admin.CrudUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin/user/")
public class CrudUserController {
    @Autowired
    private CrudUserService crudUserService;

    @PostMapping("/add/")
    public Map<String, String> addUser(@RequestParam Map<String, String> data) {
        return crudUserService.addUserService(data);
    }

    @PostMapping("/delete/")
    public Map<String, String> deleteUser(@RequestParam Map<String, String> data) {
        return crudUserService.deleteUserService(data);
    }

    @PostMapping("/update/")
    public Map<String, String> updateUser(@RequestParam Map<String, String> data) {
        return crudUserService.updateUserService(data);
    }

    @GetMapping("/info/")
    public Map<String, String> getInfo(@RequestParam Map<String, String> data) {
        return crudUserService.getInfoService(data);
    }
}
