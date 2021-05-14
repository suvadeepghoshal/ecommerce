package com.ecommerceDemo.core.services.impl;

import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.WCMException;
import com.ecommerceDemo.core.models.PageModel;
import com.ecommerceDemo.core.services.CsvToAemComponent;
import com.day.cq.wcm.api.Page;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.sling.api.resource.LoginException;

@Component(service = CsvToAemComponent.class, immediate = true)
public class CsvToAemComponentImpl implements CsvToAemComponent {

    @Reference
    ResourceResolverFactory resourceResolverFactory;

    private static final Logger LOG = LoggerFactory.getLogger(CsvToAemComponentImpl.class);

    public static final String SERVICE_NAME = "suvadeepSysUsr";

    public static final String RESOURCE_PATH = "/content/dam/ecommerce/us/en/resolver/mapping.csv";

    ResourceResolver resourceResolver = null;

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

    public List<PageModel> getCsvContent() {

        List<PageModel> pageProperties = null;

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

            pageProperties = new LinkedList<>(); // importance of linkedlist over here

            pageProperties = bufferedReader.lines().skip(1).map(singleLine -> {
                String[] arr = singleLine.split(",");
                PageModel pageModel = new PageModel();
                pageModel.setParentPage(arr[0].trim());
                pageModel.setPageName(arr[1].trim());
                pageModel.setWhichTemplate(arr[2].trim());
                pageModel.setPageTitle(arr[3].trim());
                return pageModel;
            }).collect(Collectors.toList());
        } catch (Exception e) {
            LOG.error("We failed to get the CSV datas");
        } finally {
            try {
                if (bufferedReader != null)
                    bufferedReader.close();
                if (inputStreamReader != null)
                    inputStreamReader.close();
                if (inputStream != null)
                    inputStream.close();
            } catch (Exception e) {
                LOG.error("Resources could not be released properly");
            }
        }

        return pageProperties;
    }

    /* =================This is just for testing purpose=============== */

    public static final String PARENT_PATH = "/content/ecommerce/us/en";
    public static final String PAGE_NAME = "mypage";
    public static final String WHICH_TEMPLATE = "/conf/ecommerce/settings/wcm/templates/blank";
    public static final String PAGE_TITLE = "mypage";

    /* End of dummy data */

    @Override
    public List<Page> addPage() {
        List<Page> pagesCreated = new LinkedList<>();
        List<PageModel> pageProperties = getCsvContent(); // excluded the csv for now
        PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
        try {
            for (PageModel pageModel : pageProperties) {

                Page page = pageManager.create(pageModel.getParentPage(), pageModel.getPageName(),
                        pageModel.getWhichTemplate(), pageModel.getPageTitle());

                // Page page = pageManager.create(PARENT_PATH, PAGE_NAME, WHICH_TEMPLATE,
                // PAGE_TITLE);

                if (page != null) {
                    pagesCreated.add(page);
                }
            }
            return pagesCreated;
        } catch (WCMException e) {
            LOG.error("Page not created");
        }
        return Collections.emptyList(); // also gives null value
    }
}
