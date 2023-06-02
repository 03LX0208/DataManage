package com.datamanagebackend.controller.admin;

import com.datamanagebackend.service.admin.SearcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class SearcherController {
    @Autowired
    private SearcherService searcherService;

    @GetMapping("/api/admin/search/condition/")
    public List<Map<String, String>> getUsersByCondition(@RequestParam Map<String, String> data) {
        return searcherService.getUsersByCondition(data);
    }

    @GetMapping("/api/admin/search/name/")
    public List<Map<String, String>> getUsersByName(@RequestParam Map<String, String> data) {
        return searcherService.getUsersByName(data);
    }
}
