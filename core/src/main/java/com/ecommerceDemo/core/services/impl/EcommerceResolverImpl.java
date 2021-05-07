package com.ecommerceDemo.core.services.impl;

import java.util.HashMap;
import java.util.Map;

import com.ecommerceDemo.core.services.EcomerceResolver;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = EcomerceResolver.class, immediate = true)
public class EcommerceResolverImpl implements EcomerceResolver {

    @Reference
    ResourceResolverFactory resourceResolverFactory;

    private static final Logger LOG = LoggerFactory.getLogger(EcommerceResolverImpl.class);

    public static final String SERVICE_NAME = "suvadeepSysUsr";

    String title = "This is demo String";

    public static final String PATH = "/content/ecommerce/us/en/resolver";

    @Activate
    @Modified
    public ResourceResolver getResourceResolver() {
        LOG.info("The control is coming inside the ResourceResolver and the bundle is activated!");
        Map<String, Object> map = new HashMap<>();
        map.put(ResourceResolverFactory.SUBSERVICE, SERVICE_NAME);
        try {
            ResourceResolver resourceResolver = resourceResolverFactory.getServiceResourceResolver(map);
            LOG.info("Resource Resolver registered");
            return resourceResolver;
        } catch (LoginException e) {
            LOG.error("Login Failed");
        }
        return null;
    }

    @Override
    public String getPageTitle() {
        ResourceResolver resourceResolver = null;
        try {
            resourceResolver = getResourceResolver();
            LOG.info("Resource Resolver called successfully");
            Resource resource = resourceResolver.getResource(PATH);
            title = resource.getName();
            if (!title.isEmpty()) {
                return title;
            } else {
                return "Resource Resolver is NULL";
            }
        } catch (NullPointerException e) {
            LOG.error("Resource is null");
        }
        return title;
    }

}
