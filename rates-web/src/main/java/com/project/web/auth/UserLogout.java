package com.project.web.auth;

import java.io.Serializable;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author home
 */
@ManagedBean(name = "userLogout")
@RequestScoped
public class UserLogout implements Serializable {
    
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(UserLogout.class);

    private static final long serialVersionUID = 1L;

    private transient FacesContext context = null;
    private transient ExternalContext externalContext = null;

    public UserLogout() {
    }

    public String doLogout() {
        context = FacesContext.getCurrentInstance();
        externalContext = context.getExternalContext();
     
        externalContext.getSessionMap().remove("sessionContext");

        HttpSession session = (HttpSession) externalContext.getSession(true);
        session.invalidate();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
        Cookie cookie = new Cookie("JSESSIONID", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "logout";
    }
}
