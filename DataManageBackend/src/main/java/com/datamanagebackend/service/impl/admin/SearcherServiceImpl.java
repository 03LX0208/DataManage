package com.datamanagebackend.service.impl.admin;

import com.datamanagebackend.service.admin.GetAllUsersService;
import com.datamanagebackend.service.admin.SearcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearcherServiceImpl implements SearcherService {
    @Autowired
    private GetAllUsersService getAllUsersService;

    @Override
    public List<Map<String, String>> getUsersByCondition(Map<String, String> data) {
        List<Map<String, String>> allUsers = getAllUsersService.getAllUsers();
        List<Map<String, String>> res = new ArrayList<>();

        // 什么都没选表示全部
        if (data.get("male").equals("false") && data.get("female").equals("false")
                && data.get("admin").equals("false") && data.get("student").equals("false") && data.get("teacher").equals("false")) {
            return allUsers;
        }

        // 性别没选表示无所谓性别
        if (data.get("male").equals("false") && data.get("female").equals("false")) {
            for (Map<String, String> user : allUsers) {
                String identity = user.get("identity");
                if (identity.equals("管理员") && data.get("admin").equals("true")) res.add(user);
                else if (identity.equals("学生") && data.get("student").equals("true")) res.add(user);
                else if (identity.equals("老师") && data.get("teacher").equals("true")) res.add(user);
            }
            return res;
        }

        // 身份没选表示无所谓身份 只在乎性别
        if (data.get("admin").equals("false") && data.get("student").equals("false") && data.get("teacher").equals("false")) {
            for (Map<String, String> user : allUsers) {
                String gender = user.get("gender");
                if (data.get(gender).equals("true")) res.add(user);
            }
            return res;
        }

        // 都选了表示并且
        for (Map<String, String> user : allUsers) {
            String identity = user.get("identity");
            String gender = user.get("gender");
            if (data.get(gender).equals("true")) {
                if (identity.equals("管理员") && data.get("admin").equals("true")) res.add(user);
                else if (identity.equals("学生") && data.get("student").equals("true")) res.add(user);
                else if (identity.equals("老师") && data.get("teacher").equals("true")) res.add(user);
            }
        }

        return res;
    }

    @Override
    public List<Map<String, String>> getUsersByName(Map<String, String> data) {
        List<Map<String, String>> allUsers = getAllUsersService.getAllUsers();
        List<Map<String, String>> res = new ArrayList<>();

        String text = data.get("target");
        if (text == null || text.trim().length() == 0) {
            return allUsers;
        }

        for (Map<String, String> user : allUsers) {
            if (user.get("id").contains(text) || user.get("name").contains(text)) {
                res.add(user);
            }
        }

        return res;
    }
}
