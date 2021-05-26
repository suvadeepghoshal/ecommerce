package com.ecommerceDemo.core.servlets;

/* Import Starts */

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import com.ecommerceDemo.core.services.Sitemap;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/* Import Ends */

@Component(service = Servlet.class)
@SlingServletResourceTypes(resourceTypes = "weretail/components/structure/page", selectors = "sitemap", extensions = "xml")
public class SitemapServlet extends SlingSafeMethodsServlet {

    @Reference
    transient Sitemap sitemap;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {

        final String CONTENT_TYPE = "application/xml";

        response.setContentType(CONTENT_TYPE);

        Resource resource = request.getResource();
        PrintWriter writer = response.getWriter();

        sitemap.getSitemap(resource, writer);

        if (writer != null) {
            writer.close();
        }
    }

}
