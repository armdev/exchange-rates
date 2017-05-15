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
import javax.faces.application.FacesMessage;
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
    private ResourceBundle bundle = null;
    @ManagedProperty("#{sessionContext}")
    @Setter
    private SessionContext sessionContext = null;
    private FacesContext context = null;
    private ExternalContext externalContext = null;
    private final List<String> currencyList = new ArrayList<>();
    @Setter
    @Getter
    private String currencyFirst;
    @Setter
    @Getter
    private String currencySecond;
    @Setter
    @Getter
    private double amount = 1d;
    @Setter
    @Getter
    private double rate = 0l;

    @PostConstruct
    public void init() {
        context = FacesContext.getCurrentInstance();
        externalContext = context.getExternalContext();
        applicationManager.getDictionaryService().findCurrencyList().forEach((c) -> {
            currencyList.add(c.getCurrency());
        });
    }
    //http://apilayer.net/api/live?access_key=f4446d2499d427eca4efee698b587c1e&currencies=AMD,EUR,CAD&format=1

    public void doAction() {
        ResponseModel model = restClient.getLiveRates(currencyFirst);
        System.out.println(model.toString());
        if (model.getQuotes() != null) {
            if (currencyFirst.equalsIgnoreCase("EUR")) {
                rate = model.getQuotes().getUSDEUR();
            } else if (currencyFirst.equalsIgnoreCase("AMD")) {
                rate = model.getQuotes().getUSDAMD();
            } else if (currencyFirst.equalsIgnoreCase("CBP")) {
                rate = model.getQuotes().getUSDCBP();
            } else if (currencyFirst.equalsIgnoreCase("NZD")) {
                rate = model.getQuotes().getUSDNZD();
            } else if (currencyFirst.equalsIgnoreCase("AUD")) {
                rate = model.getQuotes().getUSDAUD();
            } else if (currencyFirst.equalsIgnoreCase("JPY")) {
                rate = model.getQuotes().getUSDJPY();
            } else if (currencyFirst.equalsIgnoreCase("HUF")) {
                rate = model.getQuotes().getUSDHUF();
            } else if (currencyFirst.equalsIgnoreCase("ZWD")) {
                rate = model.getQuotes().getUSDZWD();
            } else if (currencyFirst.equalsIgnoreCase("CAD")) {
                rate = model.getQuotes().getUSDCAD();
            } else if (currencyFirst.equalsIgnoreCase("USD")) {
                rate = model.getQuotes().getUSDUSD();
            } else {
                rate = 0d;
            }

            if (model.getQuotes().getUSDUSD() != null) {
                amount *= model.getQuotes().getUSDUSD();
            }
        }
    }

    public List<String> getCurrencyList() {
        return currencyList;
    }

    public void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
