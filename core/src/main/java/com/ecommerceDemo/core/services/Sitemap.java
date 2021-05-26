package com.ecommerceDemo.core.services;

import java.io.Writer;

import org.apache.sling.api.resource.Resource;

public interface Sitemap {
    
    void getSitemap(Resource resourceFromSite, Writer writer);
}
