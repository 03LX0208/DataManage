package com.datamanagebackend.controller.user;

import com.datamanagebackend.service.user.GetUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GetUserInfoController {
    @Autowired
    private GetUserInfoService getUserInfoService;

    @GetMapping("/api/user/info/")
    public Map<String, String> getUserInfo() {
        return getUserInfoService.getUserInfo();
    }
}
