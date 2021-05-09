package com.ecommerceDemo.core.services.impl;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;
import com.ecommerceDemo.core.services.ReadAsset;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

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

@Component(service = ReadAsset.class, immediate = true)
public class ReadAssetImpl implements ReadAsset {

    @Reference
    ResourceResolverFactory resourceResolverFactory;

    private static final Logger LOG = LoggerFactory.getLogger(ReadAssetImpl.class);

    public static final String SERVICE_NAME = "suvadeepSysUsr";

    // Path to the DAM Asset in this case this would be an CSV file
    public static final String RESOURCE_PATH = "/content/dam/ecommerce/us/en/resolver/Sample100.csv";

    ResourceResolver resourceResolver = null;

    @Activate
    @Modified
    public void activate() {
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
    public void readAsset() {
        try {
            Resource resource = resourceResolver.getResource(RESOURCE_PATH);
            LOG.info("resource is coming");
            Asset asset = resource.adaptTo(Asset.class);
            LOG.info("Converted into Asset");
            Rendition rendition = asset.getOriginal();
            LOG.info("Converted into rendition");
            InputStream inputStream = rendition.adaptTo(InputStream.class);
            LOG.info("input stream");
        } catch (Exception e) {
            // TODO: handle exception
            LOG.error("Something went wrong in the readAsset function");
        }
    }
}
