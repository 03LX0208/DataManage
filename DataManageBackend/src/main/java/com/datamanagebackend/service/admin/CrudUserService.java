package com.datamanagebackend.service.admin;

import java.util.Map;

public interface CrudUserService {
    Map<String, String> addUserService(Map<String, String> data);
    Map<String, String> deleteUserService(Map<String, String> data);
    Map<String, String> updateUserService(Map<String, String> data);
}
