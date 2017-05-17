package com.project.web.user;

import com.project.entities.User;
import com.project.web.handlers.SessionContext;

import com.project.web.service.ApplicationManager;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Home
 */
@ManagedBean(name = "chpassUserBean")
@ViewScoped
@NoArgsConstructor
public class ChpassUserBean implements Serializable {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ChpassUserBean.class);
    private static final long serialVersionUID = 1L;
    @ManagedProperty("#{applicationManager}")
    @Setter    
    private ApplicationManager applicationManager = null;
    @ManagedProperty("#{i18n}")
    @Setter
    private transient ResourceBundle bundle = null;
    private User user = new User();
    @ManagedProperty("#{sessionContext}")
    @Setter
    private SessionContext sessionContext = null;
    @Setter
    @Getter
    private Long userId;
    private transient FacesContext context = null;
    private transient ExternalContext externalContext = null;
    @Setter
    @Getter
    private String passwd;

 
    @PostConstruct
    public void init() {
        context = FacesContext.getCurrentInstance();
        externalContext = context.getExternalContext();

    }

    public void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public String chpass() {
        applicationManager.getUserService().updatePassword(sessionContext.getUser().getId(), passwd);
        FacesMessage msg = new FacesMessage(bundle.getString("success"), bundle.getString("success"));
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "settings";
    }  

}
