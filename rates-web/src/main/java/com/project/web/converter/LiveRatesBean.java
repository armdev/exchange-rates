package com.project.web.converter;

import com.project.web.handlers.RateCacheBean;
import com.project.web.handlers.SessionContext;
import com.project.web.rest.RESTClientBean;
import com.project.web.rest.ResponseModel;
import com.project.web.service.ApplicationManager;
import java.io.Serializable;
import java.util.Date;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author armdev
 */
@ManagedBean(eager = true, name = "liveRatesBean")
@ViewScoped
@NoArgsConstructor
public class LiveRatesBean implements Serializable {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(LiveRatesBean.class);
    private static final long serialVersionUID = 1L;

    @ManagedProperty("#{applicationManager}")
    @Setter
    private ApplicationManager applicationManager = null;

    @ManagedProperty("#{restClient}")
    @Setter
    private RESTClientBean restClient = null;

    @ManagedProperty("#{i18n}")
    @Setter
    private transient ResourceBundle bundle = null;

    @ManagedProperty("#{sessionContext}")
    @Setter
    private SessionContext sessionContext = null;

    @ManagedProperty("#{rateCacheBean}")
    @Setter
    private RateCacheBean cacheHandler = null;

    private transient FacesContext context = null;
    private transient ExternalContext externalContext = null;
    @Setter
    @Getter
    private ResponseModel responseModel;
    @Setter
    @Getter
    private Date timeStamp;

    @PostConstruct
    public void init() {
        context = FacesContext.getCurrentInstance();
        externalContext = context.getExternalContext();
        responseModel = (ResponseModel) cacheHandler.getLiveCache(sessionContext.getUser().getEmail());
        if (responseModel == null) {
            LOG.info("@@@@@Loading response model from API");
            responseModel = restClient.getLiveRates();
            cacheHandler.putLiveCache(sessionContext.getUser().getEmail(), responseModel);
        } else {
            LOG.info("@@@@@Loading response model from cache");
        }
        if (responseModel.getTimestamp() != null) {
             
            timeStamp = new java.util.Date((long) responseModel.getTimestamp() * 1000);
            LOG.info("@@@@@PUT NEW TIMESTAMP " +timeStamp);
        }
    }
    
    public void calllistener(){
         LOG.info("@@@@@Listsner called");
    }

}
