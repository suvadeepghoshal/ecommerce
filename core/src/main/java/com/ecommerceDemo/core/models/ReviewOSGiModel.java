package com.ecommerceDemo.core.models;

import com.ecommerceDemo.core.services.ReviewService;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ReviewOSGiModel {
    @OSGiService
    ReviewService reviewService;

    public String getText() {
        return reviewService.getText();
    }

    public boolean getBoolean() {
        return reviewService.getBoolean();
    }

    public String getSelection() {
        return reviewService.getSelection();
    }

    public String[] getMultifled() {
        return reviewService.getMultifled();
    }
}
