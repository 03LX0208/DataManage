package com.datamanagebackend.service.user;

import java.util.Map;

public interface LoginService {
    Map<String, String> getToken(String username, String password);
}
