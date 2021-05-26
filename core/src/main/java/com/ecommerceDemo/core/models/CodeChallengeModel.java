package com.ecommerceDemo.core.models;

import com.ecommerceDemo.core.services.CodeChallengeService;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CodeChallengeModel {

    @OSGiService
    CodeChallengeService codeChallengeService;

    public String getText() {
        return codeChallengeService.getText();
    }

    public boolean getBoolean() {
        return codeChallengeService.getBoolean();
    }

    public String getSelection() {
        return codeChallengeService.getSelection();
    }

    public String[] getMultifled() {
        return codeChallengeService.getMultifled();
    }
    
}
