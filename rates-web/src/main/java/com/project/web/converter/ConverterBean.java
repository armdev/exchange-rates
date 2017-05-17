package com.project.web.converter;

import com.project.web.handlers.SessionContext;
import com.project.web.rest.RESTClientBean;
import com.project.web.rest.ResponseModel;
import com.project.web.service.ApplicationManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author armdev
 */
@ManagedBean(name = "rateConverterBean")
@SessionScoped
@NoArgsConstructor
public class ConverterBean implements Serializable {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ConverterBean.class);
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
    private transient FacesContext context = null;
    private transient ExternalContext externalContext = null;
    private final List<String> currencyList = new ArrayList<>();
    @Setter
    @Getter
    private String currencyFirst;
    @Setter
    @Getter
    private double amount = 1d;
    @Setter
    @Getter
    private double rate = 1d;

    @PostConstruct
    public void init() {
        context = FacesContext.getCurrentInstance();
        externalContext = context.getExternalContext();
    }

    public void doAction() {
        ResponseModel model = restClient.getConvertRates(currencyFirst);
        if (model.getQuotes() != null) {
            sessionContext.setRate(0);
            switch (currencyFirst) {
                case "EUR":
                    rate = model.getQuotes().getUSDEUR();
                    break;
                case "AMD":
                    rate = model.getQuotes().getUSDAMD();
                    break;
                case "NZD":
                    rate = model.getQuotes().getUSDNZD();
                    break;
                case "JPY":
                    rate = model.getQuotes().getUSDJPY();
                    break;
                case "HUF":
                    rate = model.getQuotes().getUSDHUF();
                    break;
                case "CAD":
                    rate = model.getQuotes().getUSDCAD();
                    break;
                case "AUD":
                    rate = model.getQuotes().getUSDAUD();
                    break;
                default:
                    rate = 0d;
                    break;
            }
            amount = 1;
            sessionContext.setRate(rate);
        }
    }

    public void handleBlurEvent() {
        LOG.info("Rate " + sessionContext.getRate());
        rate = amount * sessionContext.getRate();
        LOG.info("Final Rate " + sessionContext.getRate());
    }

    public List<String> getCurrencyList() {
        return applicationManager.getCurrencyList();
    }

}
