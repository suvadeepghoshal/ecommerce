package com.ecommerceDemo.core.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.day.cq.wcm.api.Page;
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

    String path = null;

    public static final String RESOURCE_PATH = "/content/ecommerce/us/en";

    ResourceResolver resourceResolver = null;

    @Activate
    @Modified
    public void getResourceResolver() {
        LOG.info("The control is coming inside the ResourceResolver and the bundle is activated!");
        Map<String, Object> map = new HashMap<>();
        map.put(ResourceResolverFactory.SUBSERVICE, SERVICE_NAME);
        try {
            resourceResolver = resourceResolverFactory.getServiceResourceResolver(map);
            LOG.info("Resource Resolver registered");
        } catch (LoginException e) {
            LOG.error("Login Failed");
        }
    }

    @Override
    public String getPagePath() {
        try {
            LOG.info("Resource Resolver called successfully");
            Resource resource = resourceResolver.getResource(RESOURCE_PATH);
            path = resource.getPath();
            if (!path.isEmpty()) {
                return path;
            } else {
                return "Resource Resolver is NULL";
            }
        } catch (NullPointerException e) {
            LOG.error("Resource is null");
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> getPageInfo() {
        List<Map<String, Object>> pageInfo = new ArrayList<>();
        try {
            LOG.info("Resource Resolver called successfully");
            Resource resource = resourceResolver.getResource(RESOURCE_PATH);
            Page page = resource.adaptTo(Page.class);
            Iterator<Page> childPages = page.listChildren();
            while (childPages.hasNext()) {
                Page childPage = childPages.next();
                Map<String, Object> childInfo = new HashMap<>();
                childInfo.put("parent", childPage.getParent().getName());
                childInfo.put("title", childPage.getTitle());
                childInfo.put("path", childPage.getPath());
                childInfo.put("depth", childPage.getDepth());
                childInfo.put("lastModified", childPage.getLastModified().getInstance().getTime().toString());
                childInfo.put("allInfo", childPage.getProperties());
                pageInfo.add(childInfo);
            }
        } catch (NullPointerException e) {
            LOG.error("Resource is null");
        }
        return pageInfo;
    }

}
