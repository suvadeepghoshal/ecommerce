package com.ecommerceDemo.core.services;

import java.util.List;

import com.ecommerceDemo.core.models.CsvModel;

public interface ReadAsset {
    public String getAsset();

    public List<CsvModel> getProperCsv();
}
