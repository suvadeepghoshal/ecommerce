package com.ecommerceDemo.core.services.impl;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;
import com.ecommerceDemo.core.models.CsvModel;
import com.ecommerceDemo.core.services.ReadAsset;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = ReadAsset.class, immediate = true)
public class ReadAssetImpl implements ReadAsset {

    @Reference
    ResourceResolverFactory resourceResolverFactory;

    private static final Logger LOG = LoggerFactory.getLogger(ReadAssetImpl.class);

    public static final String SERVICE_NAME = "suvadeepSysUsr";

    // Path to the DAM Asset in this case this would be an CSV file
    public static final String RESOURCE_PATH = "/content/dam/ecommerce/us/en/resolver/Sample100.csv";

    ResourceResolver resourceResolver = null;

    String csvFile = null;
    String str = null;

    @Activate
    @Modified
    public void activate() {
        LOG.info("The control is coming inside the ResourceResolver and the bundle is activated!");
        Map<String, Object> map = new HashMap<>();
        map.put(ResourceResolverFactory.SUBSERVICE, SERVICE_NAME);
        try {
            resourceResolver = resourceResolverFactory.getServiceResourceResolver(map);
            LOG.info("Resource Resolver registered");
        } catch (LoginException e) {
            LOG.error("Login Failed");
        }
    }

    @Override
    public String getAsset() {
        try {
            Resource resource = resourceResolver.getResource(RESOURCE_PATH);
            LOG.info("resource is coming");
            Asset asset = resource.adaptTo(Asset.class);
            Rendition rendition = asset.getOriginal();
            InputStream inputStream = rendition.adaptTo(InputStream.class);
            if (inputStream != null) {
                csvFile = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
                return csvFile;
            } else {
                return "We have failed";
            }
        } catch (Exception e) {
            LOG.error("Something went wrong in the readAsset function");
        }
        return "Nothing is happening";
    }

    @Override
    public List<CsvModel> getProperCsv() {

        List<CsvModel> csvList = null;

        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;

        try {
            Resource resource = resourceResolver.getResource(RESOURCE_PATH);
            LOG.info("resource is coming");
            Asset asset = resource.adaptTo(Asset.class);
            Rendition rendition = asset.getOriginal();
            inputStream = rendition.adaptTo(InputStream.class);
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);

            csvList = new ArrayList<>();

            /* =======================Java 8 Features====================== */

            csvList = bufferedReader.lines().skip(1).map(csvLineString -> {
                String[] csvArrayofValues = csvLineString.split(",");
                CsvModel csvModel = new CsvModel();
                csvModel.setSerialNumber(Long.parseLong(csvArrayofValues[0].trim()));
                csvModel.setCompanyName(csvArrayofValues[1].trim());
                csvModel.setEmployee(csvArrayofValues[2].trim());
                csvModel.setDescription(csvArrayofValues[3].trim());
                csvModel.setLeave(Byte.parseByte(csvArrayofValues[4].trim()));
                return csvModel;
            }).collect(Collectors.toList());

            /* =======================Traditional Method====================== */

            // List<String> csvLineString =
            // bufferedReader.lines().skip(1).collect(Collectors.toList());
            // for (String str : csvLineString) {
            // String[] csvArrayofValues = str.split(",");
            // CsvModel csvModel = new CsvModel();
            // csvModel.setSerialNumber(csvArrayofValues[0].trim());
            // csvModel.setCompanyName(csvArrayofValues[1].trim());
            // csvModel.setEmployee(csvArrayofValues[2].trim());
            // csvModel.setDescription(csvArrayofValues[3].trim());
            // csvModel.setLeave(Byte.parseByte(csvArrayofValues[4].trim()));
            // csvList.add(csvModel);
            // }

        } catch (Exception e) {
            LOG.error("Something went wrong in the readAsset function");
        } finally {
            try {
                if (bufferedReader != null)
                    bufferedReader.close();
                if (inputStreamReader != null)
                    inputStreamReader.close();
                if (inputStream != null)
                    inputStream.close();
            } catch (Exception e) {
                LOG.error("Something is wrong");
            }
        }
        return csvList;
    }
}