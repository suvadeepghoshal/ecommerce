package com.ecommerceDemo.core.services.impl;

/* Import Starts */

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.ecommerceDemo.core.services.Sitemap;

import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import java.io.Writer;

import org.apache.sling.api.resource.Resource;

import java.util.Map;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.XMLStreamException;

import java.util.HashMap;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* Import Ends */

@Component(service = Sitemap.class, immediate = true)
public class SitemapImpl implements Sitemap {

    @Reference
    ResourceResolverFactory resourceResolverFactory;

    /* === Global Values === */

    ResourceResolver resourceResolver = null;

    /* Logger LOG */
    private static final Logger LOG = LoggerFactory.getLogger(SitemapImpl.class);

    private static final String SITEMAP_LOCATION = "http://localhost:4505/";

    private static final String SITEMAP_NAMESPACE = "http://www.sitemaps.org/schemas/sitemap/0.9";

    private static FastDateFormat date = FastDateFormat.getInstance("yyyyMMdd");

    public static final String SERVICE_NAME = "suvadeepSysUsr";

    /* === Ends here === */

    /* On Activation */

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
    public void getSitemap(Resource resourceFromSite, Writer writer) {

        XMLOutputFactory xmlOutputFactory = null;

        XMLStreamWriter xmlStreamWriter = null;

        Page mainPage = null;

        PageManager pageManager = null;

        try {

            /* === Some required values === */

            final String ENCODING = "UTF-8";

            final String VERSION = "1.0";

            final String URL_SET = "urlset";

            final String PREFIX = "";

            /* === Ends here === */

            pageManager = resourceResolver.adaptTo(PageManager.class);

            mainPage = pageManager.getContainingPage(resourceFromSite);

            xmlOutputFactory = XMLOutputFactory.newFactory();

            xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(writer);

            /* === URL forming === */

            xmlStreamWriter.writeStartDocument(VERSION);
            xmlStreamWriter.writeStartElement("", URL_SET, SITEMAP_NAMESPACE);
            xmlStreamWriter.writeNamespace(PREFIX, SITEMAP_NAMESPACE);

            getInnerPage(mainPage, xmlStreamWriter);

            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeEndDocument();

            /* === URL forming ends === */

        } catch (XMLStreamException e) {
            LOG.error("XMLStreamException: ", e);
        } finally {
            if (xmlStreamWriter != null) {
                try {
                    xmlStreamWriter.close();
                } catch (XMLStreamException e) {
                    LOG.error("XMLStreamException -> Login Failed: ", e);
                }
            }
        }
    }

    private void getInnerPage(Page mainPage, XMLStreamWriter xmlStreamWriter) {

        xmlWriter(mainPage, xmlStreamWriter);

        for (Iterator<Page> children = mainPage.listChildren(); children.hasNext();) {

            Page childPage = children.next();

            if (childPage.listChildren().hasNext()) {
                getInnerPage(mainPage, xmlStreamWriter);
            } else {
                xmlWriter(mainPage, xmlStreamWriter);
            }

        }

    }

    private void xmlWriter(Page mainPage, XMLStreamWriter xmlStreamWriter) {

        final String URL = "url";

        final String LAST_MODIFIED = "last_modified";

        final String LAST_MODIFIED_VALUE = date.format(mainPage.getLastModified());

        final String CURRENT_PAGE_NAME = mainPage.getName();

        final String LOCATION = "location";

        final String CHANGE_FREQUENCY = "change frequency";

        final String CHANGE_FREQUENCY_AFTER = "weekly";

        try {
            xmlStreamWriter.writeStartElement(SITEMAP_NAMESPACE, URL);

            xmlElementWriter(xmlStreamWriter, LOCATION, SITEMAP_LOCATION + CURRENT_PAGE_NAME);
            xmlElementWriter(xmlStreamWriter, LAST_MODIFIED, LAST_MODIFIED_VALUE);
            xmlElementWriter(xmlStreamWriter, CHANGE_FREQUENCY, CHANGE_FREQUENCY_AFTER);

            xmlStreamWriter.writeEndElement();
        } catch (XMLStreamException e) {
            LOG.error("XMLStreamException -> xmlWriter(): ", e);
        }

    }

    private void xmlElementWriter(XMLStreamWriter xmlStreamWriter, String key, String value) {

        try {
            xmlStreamWriter.writeStartElement(SITEMAP_NAMESPACE, key);

            xmlStreamWriter.writeCharacters(value);

            xmlStreamWriter.writeEndElement();
        } catch (XMLStreamException e) {
            LOG.error("XMLStreamException -> XmlElementWriter(): ", e);
        }

    }

}
