package com.project.web.auth;

import com.project.entities.LocationModel;
import com.project.entities.User;
import com.project.web.service.ApplicationManager;
import com.project.web.utils.DateUtils;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ManagedBean(name = "userRegister")
@ViewScoped
@NoArgsConstructor
public class UserRegister implements Serializable {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(UserRegister.class);
    private static final long serialVersionUID = 1L;

    @ManagedProperty("#{applicationManager}")
    @Setter
    private ApplicationManager applicationManager = null;

    @ManagedProperty("#{locationBean}")
    @Setter
    private transient LocationBean locationBean = null;

    @ManagedProperty("#{i18n}")
    @Setter
    private transient ResourceBundle bundle = null;

    @Setter
    @Getter
    private User user = null;

    @Setter
    @Getter
    private boolean agree;

    @PostConstruct
    public void init() {
        user = new User();
        LOG.info("User Register bean started");
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ex = context.getExternalContext();
        HttpServletRequest httpServletRequest = (HttpServletRequest) ex.getRequest();

        String ipAddress = httpServletRequest.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = httpServletRequest.getRemoteAddr();
        }
        LocationModel location = locationBean.findLocation(ipAddress);
        user.setCity(location.getCity());
        user.setCountry(location.getCountryName());
        user.setZipcode(location.getPostalCode());
    }

//    public int getUserRealAge(Date date) {     
//        LOG.info("Date###### " + date);
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
//        int year = cal.get(Calendar.YEAR);
//        int month = cal.get(Calendar.MONTH);
//        int day = cal.get(Calendar.DAY_OF_MONTH);
//        LocalDate birthday = LocalDate.of(year, month, day);
//        LocalDate now = LocalDate.now();
//        Period period = Period.between(birthday, now);
//        return period.getYears();
//    }
    public String saveUser() {
        if (user.getBirthDate() != null) {
            int age = DateUtils.calculateAge(user.getBirthDate());
            LOG.info("User age is " + age);
            if (age < 16) {
                FacesMessage msg = new FacesMessage(bundle.getString("tooyoung"), bundle.getString("tooyoung"));
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return null;
            }
            if (age > 80) {
                FacesMessage msg = new FacesMessage(bundle.getString("tooold"), bundle.getString("tooold"));
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return null;
            }
        }

        User u = applicationManager.getUserService().getByEmail(user.getEmail());
        if (u != null) {
            FacesMessage msg = new FacesMessage(bundle.getString("emailbusy"), bundle.getString("emailbusy"));
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        } else {
            applicationManager.getUserService().save(user);
            return "success?faces-redirect=true";
        }

    }

}
