package com.project.web.service;

import com.project.entities.Historical;
import com.project.services.DictionaryService;
import com.project.services.HistoricalService;
import com.project.services.UserService;
import com.project.web.rest.Quotes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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

    private static final long serialVersionUID = 1L;

    @ManagedProperty("#{userService}")
    @Setter
    @Getter
    private transient UserService userService = null;

    @ManagedProperty("#{dictionaryService}")
    @Setter
    @Getter
    private transient DictionaryService dictionaryService = null;

    @ManagedProperty("#{historicalService}")
    @Setter
    @Getter
    private transient HistoricalService historicalService = null;

    @Getter
    @Setter
    private List<String> currencyList = new ArrayList<>();

    @PostConstruct
    public void init() {
        this.getDictionaryService().findCurrencyList().forEach((c) -> {
            currencyList.add(c.getCurrency());
        });
    }

    /**
     *
     * @param date
     * @param q
     * @Store entity asynchronous
     */
    public void storeHistoricalData(final String date, final Quotes q) {
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(() -> {
            Historical returnedEntity = historicalService.findByHistoricalDate(date);
            if (returnedEntity == null) {
                Historical historicalDate = new Historical();
                historicalDate.setHistoricalDate(date);
                historicalDate.setUsdamd(q.getUSDAMD());
                historicalDate.setUsdaud(q.getUSDAUD());
                historicalDate.setUsdcad(q.getUSDCAD());
                historicalDate.setUsdeur(q.getUSDEUR());
                historicalDate.setUsdhuf(q.getUSDHUF());
                historicalDate.setUsdjpy(q.getUSDJPY());
                historicalDate.setUsdnzd(q.getUSDNZD());
                historicalService.save(historicalDate);
            }
        });

    }

}
