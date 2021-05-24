package com.ecommerceDemo.core.services.impl;

import com.ecommerceDemo.core.config.CodeChallengeConfig;
import com.ecommerceDemo.core.services.CodeChallengeService;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;


@Component(service = CodeChallengeService.class, immediate = true)
@Designate(ocd = CodeChallengeConfig.class)
public class CodeChallengeServiceImpl implements CodeChallengeService {

    private String text;
    private boolean demoBoolean;
    private String selection;
    private String[] multifield;

    @Activate
    protected void activate(final CodeChallengeConfig codeChallengeConfig) {
        text = codeChallengeConfig.getText();
        demoBoolean = codeChallengeConfig.getBoolean();
        selection = codeChallengeConfig.getSelection();
        multifield = codeChallengeConfig.getMultifled();
    }

    @Override
    public String getText() {
        // TODO Auto-generated method stub
        return text;
    }

    @Override
    public boolean getBoolean() {
        // TODO Auto-generated method stub
        return demoBoolean;
    }

    @Override
    public String getSelection() {
        // TODO Auto-generated method stub
        return selection;
    }

    @Override
    public String[] getMultifled() {
        // TODO Auto-generated method stub
        return multifield;
    }
    
}
