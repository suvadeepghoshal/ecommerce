package com.ecommerceDemo.core.services.impl;

import java.util.List;
import java.util.ArrayList;

import com.ecommerceDemo.core.services.HitApiService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = HitApiService.class, immediate = true)
public class HitApiServiceImpl implements HitApiService {
    private static final Logger LOG = LoggerFactory.getLogger(HitApiServiceImpl.class);
    final String URL = "https://restcountries.eu/rest/v2/all";
    DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
    StatusLine statusLine = null;
    int statusCode = 0;

    @Activate
    @Modified
    public void activate() {
        getCountryName();
    }

    @Override
    public List<String> getCountryName() {
        List<String> countries = new ArrayList<>();
        try {
            HttpGet httpGet = new HttpGet(URL);
            httpGet.addHeader("accept", "application/json");
            HttpResponse httpResponse = defaultHttpClient.execute(httpGet);
            statusLine = httpResponse.getStatusLine();
            statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
                HttpEntity httpEntity = httpResponse.getEntity();
                String apiOutput = EntityUtils.toString(httpEntity);
                LOG.info(apiOutput);
                countries.addAll(parJson(apiOutput));
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return countries;
    }

    private List<String> parJson(String apiOutput) {
        List<String> countryNames = new ArrayList<>();
        JsonParser jsonParser = new JsonParser();
        JsonArray countries = (JsonArray) jsonParser.parse(apiOutput);
        for (Object c : countries) {
            JsonObject country = (JsonObject) c;
            String name = country.get("name").toString();
            countryNames.add(name);
        }
        return countryNames;
    }

}
