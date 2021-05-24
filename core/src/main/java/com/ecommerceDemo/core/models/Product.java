package com.ecommerceDemo.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Product {

    @Inject
    String title;

    @Inject
    String name;

    @Inject
    String language;

    @Inject
    String price;

    @Inject
    String description;

    @Inject
    String fileReference;

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public String getLanguage() {
        return language;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getFileName() {
        return fileReference;
    }

}
