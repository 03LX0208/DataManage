package com.datamanagebackend.service.impl.user;

import com.datamanagebackend.pojo.User;
import com.datamanagebackend.service.user.GetUserInfoService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GetUserInfoServiceImpl implements GetUserInfoService {
    @Override
    public Map<String, String> getUserInfo() {
        //从上下文找到这个已登录的用户
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        User user = loginUser.getUser();

        Map<String, String> map = new HashMap<>();
        map.put("error_message", "success");
        map.put("id", user.getId().toString());
        map.put("username", user.getUsername());
        map.put("identity", user.getIdentity());
        return map;
    }
}