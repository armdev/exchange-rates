package com.project.web.auth;

import com.project.entities.User;
import com.project.web.handlers.SessionContext;
import com.project.web.service.ApplicationManager;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author armdev
 */
@ManagedBean(name = "userLogin")
@RequestScoped
@NoArgsConstructor
public class UserLogin {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(UserLogin.class);

    @ManagedProperty("#{applicationManager}")
    @Setter
    private ApplicationManager applicationManager = null;

    @Setter
    @Getter
    private String email;
    @Setter
    @Getter
    private String password;

    @ManagedProperty("#{i18n}")
    @Setter
    private ResourceBundle bundle = null;
    @ManagedProperty("#{sessionContext}")
    @Setter
    private SessionContext sessionContext = null;

    public String loginUser() {        
        User user = applicationManager.getUserService().userLogin(email, password);
        if (user != null) {
            sessionContext.setUser(user);
            return "profile";
        }
        FacesMessage msg = new FacesMessage(bundle.getString("nouser"), bundle.getString("nouser"));
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return null;
    }

}
