package com.project.web.service;

import com.project.services.UserService;
import java.io.Serializable;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ManagedBean(eager = false, name = "applicationManager")
@ApplicationScoped()
@NoArgsConstructor
public class ApplicationManager implements Serializable {

    @ManagedProperty("#{userService}")
    @Setter
    @Getter
    private UserService userService = null;   



}
