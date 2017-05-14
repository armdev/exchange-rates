package com.project.web.converter;

import com.project.entities.Currency;
import com.project.web.handlers.SessionContext;

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
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Home
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
    @ManagedProperty("#{i18n}")
    @Setter
    private ResourceBundle bundle = null;

    @ManagedProperty("#{sessionContext}")
    @Setter
    private SessionContext sessionContext = null;

    private FacesContext context = null;
    private ExternalContext externalContext = null;

    private List<Currency> currencyList = new ArrayList<>();

    private String currencyFirst;
    private String currencySecond;

    @PostConstruct
    public void init() {
        context = FacesContext.getCurrentInstance();
        externalContext = context.getExternalContext();
      getCurrencyList();
    }

    public List<Currency> getCurrencyList() {
        LOG.info("LIST SIZE " + applicationManager.getDictionaryService().findCurrencyList().size());
       
        return applicationManager.getDictionaryService().findCurrencyList();
    }

  

    public String getCurrencyFirst() {
        return currencyFirst;
    }

    public void setCurrencyFirst(String currencyFirst) {
        this.currencyFirst = currencyFirst;
    }

    public String getCurrencySecond() {
        return currencySecond;
    }

    public void setCurrencySecond(String currencySecond) {
        this.currencySecond = currencySecond;
    }

  

    public void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public String chpass() {

        return "settings";
    }

}
