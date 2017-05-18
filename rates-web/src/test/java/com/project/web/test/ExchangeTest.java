package com.project.web.test;

import com.project.web.rest.RESTClientBean;
import java.io.IOException;
import java.util.logging.Level;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

public class ExchangeTest {

    private final String SERVICE_PATH = "http://apilayer.net/api/";

    private final String ACCESS_KEY = "f4446d2499d427eca4efee698b587c1e";

    @Test
    public void testHistoricalRates() {
        CloseableHttpClient CLIENT = HttpClients.createDefault();
        try {

            HttpGet request = new HttpGet(SERVICE_PATH + "historical?access_key=" + ACCESS_KEY + "&currencies=" + "AMD,EUR" + "&date=" + "2005-07-07" + "&format=1");
            request.addHeader("charset", "UTF-8");
            HttpResponse response = CLIENT.execute(request);
            response.addHeader("content-type", "application/json;charset=UTF-8");
            HttpEntity entity = response.getEntity();
            ObjectMapper mapper = new ObjectMapper();
            Assert.assertNotNull(EntityUtils.toString(entity));

        } catch (IOException | ParseException ex) {
            try {
                CLIENT.close();
            } catch (IOException ex1) {
                java.util.logging.Logger.getLogger(RESTClientBean.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                CLIENT.close();
            } catch (IOException ex1) {
                java.util.logging.Logger.getLogger(RESTClientBean.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }

    }

}
