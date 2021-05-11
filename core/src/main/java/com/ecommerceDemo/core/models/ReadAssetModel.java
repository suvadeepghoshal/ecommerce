package com.ecommerceDemo.core.models;

import java.util.List;

import com.ecommerceDemo.core.services.ReadAsset;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ReadAssetModel {

    @OSGiService
    ReadAsset readAsset;

    public String getAsset() {
        return readAsset.getAsset();
    }

    public List<CsvModel> getProperCsv() {
        return readAsset.getProperCsv();
    }
}
