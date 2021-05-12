package com.ecommerceDemo.core.models;

import com.ecommerceDemo.core.services.CsvToAemComponent;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CsvToAemComponentModel {

    @OSGiService
    CsvToAemComponent csvToAemComponent;

    public String addPage() {
        return csvToAemComponent.addPage();
    }

}