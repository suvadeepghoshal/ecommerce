package com.ecommerceDemo.core.services.impl;

import com.ecommerceDemo.core.config.ReviewConfig;
import com.ecommerceDemo.core.services.ReviewService;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

@Component(service = ReviewService.class, immediate = true)
@Designate(ocd = ReviewConfig.class)
public class ReviewServicesImpl implements ReviewService {

    private String text;
    private boolean demoBoolean;
    private String selection;
    private String[] multifield;

    @Activate
    protected void activate(final ReviewConfig reviewConfig) {
        text = reviewConfig.getText();
        demoBoolean = reviewConfig.getBoolean();
        selection = reviewConfig.getSelection();
        multifield = reviewConfig.getMultifled();
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public boolean getBoolean() {
        return demoBoolean;
    }

    @Override
    public String getSelection() {
        return selection;
    }

    @Override
    public String[] getMultifled() {
        return multifield;
    }

}
