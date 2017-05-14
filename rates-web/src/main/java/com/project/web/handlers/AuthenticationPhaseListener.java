package com.project.web.handlers;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javax.el.ELException;
import javax.faces.FacesException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import org.apache.log4j.Logger;

public class AuthenticationPhaseListener implements PhaseListener {

    private static final long serialVersionUID = 1L;
    private static HashMap<String, String> pagePermissionMapping = null;

    private static void redirect(FacesContext facesContext, String url) {
        try {
            facesContext.getExternalContext().redirect(url);
        } catch (IOException e) {
            throw new FacesException("Cannot redirect to " + url + " due to IO exception.", e);
        }
    }
    private final Logger LOG = Logger.getLogger(AuthenticationPhaseListener.class);

    @SuppressWarnings("unchecked")
    private void pagePermissionMapping() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (pagePermissionMapping == null) {
            pagePermissionMapping = new HashMap();
            try {
                ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "accessProp");
                if (bundle != null) {
                    Enumeration e = bundle.getKeys();
                    while (e.hasMoreElements()) {
                        String key = (String) e.nextElement();
                        String value = bundle.getString(key);
                        pagePermissionMapping.put(key, value);
                    }
                }
            } catch (NullPointerException | FacesException e) {
            }
        }
    }

    @Override
    public void afterPhase(PhaseEvent event) {
    }

    @Override
    public synchronized void beforePhase(PhaseEvent event) {
        FacesContext context = event.getFacesContext();
        ExternalContext ex = context.getExternalContext();
        try {
            pagePermissionMapping();

            String viewId = "/index.xhtml";

            if (context.getViewRoot() != null && context.getViewRoot().getViewId() != null) {
                viewId = context.getViewRoot().getViewId();
            }
            String permissions = pagePermissionMapping.get(viewId);
            SessionContext sessionContext = (SessionContext) ex.getSessionMap().get("sessionContext");

            if (sessionContext == null && !viewId.contains("index.xhtml") && !viewId.contains("public.xhtml")
                    && !viewId.contains("register.xhtml")) {
            }
            if (permissions != null && permissions.contains("PUBLIC")) {
                return;
            }
            
            if (sessionContext.getUser().getId() == null && !viewId.contains("index.xhtml") || !permissions.contains("LOGGED")) {
                FacesContext.getCurrentInstance().getExternalContext().redirect(ex.getRequestContextPath() + "/index.jsf?illegalAccess");
            }

        } catch (IOException | ELException ex1) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(ex.getRequestContextPath() + "/index.jsf?error");
            } catch (IOException ex2) {
                java.util.logging.Logger.getLogger(AuthenticationPhaseListener.class.getName()).log(Level.SEVERE, null, ex2);
            }
        }
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }
}
