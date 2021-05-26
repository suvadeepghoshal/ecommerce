package com.ecommerceDemo.core.models;

import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.settings.SlingSettingsService;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.REQUIRED)
public class DynamicPageInfo {

    @OSGiService
    private SlingSettingsService slingSettingsService;

    @SlingObject
    private Resource resource;

    @SlingObject
    private ResourceResolver resourceResolver;

    public String getPageTitle() {

        PageManager pageManager = resourceResolver.adaptTo(PageManager.class);

        Page page = pageManager.getContainingPage(resource);

        return page.getTitle();
    }

    public String getPageDescription() {

        PageManager pageManager = resourceResolver.adaptTo(PageManager.class);

        Page page = pageManager.getContainingPage(resource);

        return page.getDescription();
    }

}
