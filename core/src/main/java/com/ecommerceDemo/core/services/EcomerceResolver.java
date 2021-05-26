package com.ecommerceDemo.core.services;

import java.util.List;
import java.util.Map;

public interface EcomerceResolver {
    public String getPagePath();
    public List<Map<String, Object>> getPageInfo();
}
