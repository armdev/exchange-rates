package com.project.web.rest;


import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;


@ManagedBean(eager = true, name = "restClient")
@ApplicationScoped
public class RESTClientBean implements Serializable {

    private static final long serialVersionUID = 1L;
  
    private final String SERVICE_PATH = "http://apilayer.net/api/";

  
    public RESTClientBean() {
    
    }

    @PostConstruct
    public void init() {
     
    }

    public List<String> getRates() {
        CloseableHttpClient CLIENT = HttpClients.createDefault();
     
     
        try {
            HttpGet request = new HttpGet(SERVICE_PATH + "live?access_key=f4446d2499d427eca4efee698b587c1e&currencies=USD,EUR&format=1");
            request.addHeader("charset", "UTF-8");
            HttpResponse response = CLIENT.execute(request);
            response.addHeader("content-type", "application/json;charset=UTF-8");
            HttpEntity entity = response.getEntity();
            ObjectMapper mapper = new ObjectMapper();
           
            ResponseModel model = mapper.readValue(EntityUtils.toString(entity), ResponseModel.class);
          System.out.println(model.toString());
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
        return null;
    }

    

}
