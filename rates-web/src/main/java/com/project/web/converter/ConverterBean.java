package com.project.web.converter;

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

    @PostConstruct
    public void init() {
        context = FacesContext.getCurrentInstance();
        externalContext = context.getExternalContext();     
        applicationManager.getDictionaryService().findCurrencyList().forEach((c) -> {
            currencyList.add(c.getCurrency());
        });
    }

    public List<String> getCurrencyList() {
        return currencyList;
    }

    public void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
