package com.ecommerceDemo.core.models;

import java.util.List;
import java.util.Map;

import com.ecommerceDemo.core.services.EcomerceResolver;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class EcommerceModel {

    @OSGiService
    EcomerceResolver ecomerceResolver;

    public String getPagePath() {
        return ecomerceResolver.getPagePath();
    }

    public List<Map<String, Object>> getPageInfo() {
        return ecomerceResolver.getPageInfo();
    }
    
}
