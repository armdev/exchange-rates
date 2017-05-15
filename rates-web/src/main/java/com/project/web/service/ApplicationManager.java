package com.project.web.service;

import com.project.services.DictionaryService;
import com.project.services.UserService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ManagedBean(eager = true, name = "applicationManager")
@ApplicationScoped
@NoArgsConstructor
public class ApplicationManager implements Serializable {

    @ManagedProperty("#{userService}")
    @Setter
    @Getter
    private UserService userService = null;

    @ManagedProperty("#{dictionaryService}")
    @Setter
    @Getter
    private DictionaryService dictionaryService = null;

    @Getter
    @Setter
    private  List<String> currencyList = new ArrayList<>();

    @PostConstruct
    public void init() {
        this.getDictionaryService().findCurrencyList().forEach((c) -> {
            currencyList.add(c.getCurrency());
        });
    }

}
