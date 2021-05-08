package com.ecommerceDemo.core.servlets.assets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;

@Component(service = Servlet.class, immediate = true)
@SlingServletPaths(value = { "/bin/readcsvasset" })
@ServiceDescription("Reading the CSV Asset")
public class ReadCSVAsset extends SlingSafeMethodsServlet {

    @Reference
    private transient ResourceResolverFactory resourceResolverFactory;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
        super.doGet(request, response);
        PrintWriter out = response.getWriter();
        try {
            final Map<String, Object> map = Collections.singletonMap(ResourceResolverFactory.SUBSERVICE,
                    "getResourceResolver");
            ResourceResolver resourceResolver = resourceResolverFactory.getServiceResourceResolver(map);
            String path = resourceResolver.getResource("/content/dam/ecommerce/us/en/resolver/Sample100.csv").getPath();
            out.write(path);
        } catch (Exception e) {
            out.write(e.getMessage());
        }
    }
}
