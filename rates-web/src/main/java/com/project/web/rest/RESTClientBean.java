package com.project.web.rest;

import com.project.web.service.ApplicationManager;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;

@ManagedBean(eager = false, name = "restClient")
@ApplicationScoped
public class RESTClientBean implements Serializable {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(RESTClientBean.class);

    private static final long serialVersionUID = 1L;

    @ManagedProperty("#{applicationManager}")
    @Setter
    private ApplicationManager applicationManager = null;

    private final String SERVICE_PATH = "http://apilayer.net/api/";

    private final String ACCESS_KEY = "f4446d2499d427eca4efee698b587c1e";

    public RESTClientBean() {

    }

    @PostConstruct
    public void init() {

    }

    public ResponseModel getHistoricalRates(String date) {
        date = "2005-02-01";
        CloseableHttpClient CLIENT = HttpClients.createDefault();
        ResponseModel model = new ResponseModel();
        try {
            String currencies = StringUtils.join(applicationManager.getCurrencyList(), ',');
            HttpGet request = new HttpGet(SERVICE_PATH + "historical?access_key=" + ACCESS_KEY + "&currencies=" + currencies + "&date=" + date + "&format=1");
            request.addHeader("charset", "UTF-8");
            HttpResponse response = CLIENT.execute(request);
            response.addHeader("content-type", "application/json;charset=UTF-8");
            HttpEntity entity = response.getEntity();
            ObjectMapper mapper = new ObjectMapper();
            model = mapper.readValue(EntityUtils.toString(entity), ResponseModel.class);
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
        return model;
    }

    public ResponseModel getLiveRates() {
        CloseableHttpClient CLIENT = HttpClients.createDefault();
        ResponseModel model = new ResponseModel();
        try {
            String currencies = StringUtils.join(applicationManager.getCurrencyList(), ',');
            HttpGet request = new HttpGet(SERVICE_PATH + "live?access_key=" + ACCESS_KEY + "&currencies=" + currencies + "&format=1");
            request.addHeader("charset", "UTF-8");
            HttpResponse response = CLIENT.execute(request);
            response.addHeader("content-type", "application/json;charset=UTF-8");
            HttpEntity entity = response.getEntity();
            ObjectMapper mapper = new ObjectMapper();
            model = mapper.readValue(EntityUtils.toString(entity), ResponseModel.class);
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
        return model;
    }

    public ResponseModel getConvertRates(String firstCurrency) {
        CloseableHttpClient CLIENT = HttpClients.createDefault();
        ResponseModel model = new ResponseModel();
        try {
            LOG.info("firstCurrency " + firstCurrency);
            HttpGet request = new HttpGet(SERVICE_PATH + "live?access_key=" + ACCESS_KEY + "&currencies=" + firstCurrency + "&format=1");
            request.addHeader("charset", "UTF-8");
            HttpResponse response = CLIENT.execute(request);
            response.addHeader("content-type", "application/json;charset=UTF-8");
            HttpEntity entity = response.getEntity();
            ObjectMapper mapper = new ObjectMapper();
            model = mapper.readValue(EntityUtils.toString(entity), ResponseModel.class);
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
        return model;
    }

}
