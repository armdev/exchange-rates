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
 * @author armdev
 */
@ManagedBean(name = "profileUpdateBean")
@ViewScoped
@NoArgsConstructor
public class ProfileUpdateBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ProfileUpdateBean.class);

    @ManagedProperty("#{applicationManager}")
    @Setter
    private ApplicationManager applicationManager = null;
    @Setter
    @ManagedProperty("#{i18n}")
    private ResourceBundle bundle = null;
    @Setter
    @Getter
    private User user = new User();
    @ManagedProperty("#{sessionContext}")
    @Setter
    private SessionContext sessionContext = null;
    @Setter
    @Getter
    private Long userId;
    private FacesContext context = null;
    private ExternalContext externalContext = null;

    @PostConstruct
    public void init() {
        context = FacesContext.getCurrentInstance();
        externalContext = context.getExternalContext();
        user = applicationManager.getUserService().findUser(sessionContext.getUser().getId());

        if (user == null) {
            user = new User();
        }

    }

    public String updateUser() {

        boolean checkEmail = applicationManager.getUserService().checkUserEmailForUpdate(userId, user.getEmail());
        if (!checkEmail) {
            applicationManager.getUserService().update(user);
            FacesMessage msg = new FacesMessage(bundle.getString("success"), bundle.getString("success"));
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage msg = new FacesMessage(bundle.getString("emailbusy"), bundle.getString("emailbusy"));
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }

        return null;
    }

    private String getRequestParameter(String paramName) {
        String returnValue = null;
        if (externalContext.getRequestParameterMap().containsKey(paramName)) {
            returnValue = (externalContext.getRequestParameterMap().get(paramName));
        }
        return returnValue;
    }

}
