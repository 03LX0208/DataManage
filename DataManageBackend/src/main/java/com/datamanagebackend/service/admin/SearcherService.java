package com.datamanagebackend.service.admin;

import java.util.List;
import java.util.Map;

public interface SearcherService {
    List<Map<String, String>> getUsersByCondition(Map<String, String> data);
    List<Map<String, String>> getUsersByName(Map<String, String> data);
}
