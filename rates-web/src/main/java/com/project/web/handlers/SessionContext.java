package com.project.web.handlers;

import com.project.entities.User;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author armdev
 */
@ManagedBean(name = "sessionContext")
@SessionScoped
public class SessionContext implements Serializable {

    private static final long serialVersionUID = 1L;

  
    @Setter
    @Getter
    private User user;
    @Setter
    @Getter
    private Integer activeIndex = 0;
   

    public SessionContext() {      
        user = new User();      
    }

    public void onChange(TabChangeEvent event) {
        switch (event.getTab().getId()) {
            case "settings":
                activeIndex = 1;
                break;
            case "dataManagement":
                activeIndex = 0;
                break;
            case "statistic":
                activeIndex = 2;
                break;
            case "configs":
                activeIndex = 3;
                break;
            default:
                activeIndex = 4;
                break;
        }

    }

   

}
