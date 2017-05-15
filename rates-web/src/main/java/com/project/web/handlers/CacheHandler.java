package com.project.web.handlers;

import com.project.web.rest.ResponseModel;
import java.io.InputStream;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import lombok.Getter;
import lombok.Setter;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;


@ApplicationScoped
@ManagedBean(eager = true)
@Setter
@Getter
public class CacheHandler implements Serializable {

    private static final long serialVersionUID = 8163039174281168443L;

    private CacheManager manager;
    @Setter  
    private Cache liveCache;
    @Setter    
    private Cache historicalCache;

    public CacheHandler() {
    }

    @PreDestroy
    public void destroy() {

    }

    @PostConstruct
    public void init() {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream fis = classLoader.getResourceAsStream("ehcache.xml");
            this.manager = CacheManager.create(fis);
            this.liveCache = this.manager.getCache("live.cache");
            this.historicalCache = this.manager.getCache("historical.cache");
        } catch (ClassCastException | IllegalStateException | CacheException e) {
            manager.shutdown();
        }
    }

    public boolean putLiveCache(Serializable key, ResponseModel value) {
        if (key == null || value == null) {
            return false;
        }
        if (this.liveCache == null) {
            this.liveCache = this.manager.getCache("live.cache");
        }
        liveCache.put(new Element(key, value));
        liveCache.flush();
        return true;
    }

    public Object getLiveCache(String key) {
        try {
            if (this.historicalCache == null) {
                this.historicalCache = this.manager.getCache("live.cache");
            }
            Element elem = this.historicalCache.get(key);
            if ((elem != null) && (elem.getObjectValue() != null)) {

                return elem.getObjectValue();
            }
        } catch (ClassCastException | IllegalStateException | CacheException e) {
        }
        return null;
    }

    public boolean putHistoricalCache(Serializable key, ResponseModel value) {
        if (key == null || value == null) {
            return false;
        }
        if (this.historicalCache == null) {
            this.historicalCache = this.manager.getCache("historical.cache");
        }

        historicalCache.put(new Element(key, value));
        historicalCache.flush();
        return true;
    }

    public Object getHistoricalCache(String key) {
        try {
            if (this.historicalCache == null) {
                this.historicalCache = this.manager.getCache("historical.cache");
            }
            Element elem = this.historicalCache.get(key);
            if ((elem != null) && (elem.getObjectValue() != null)) {

                return elem.getObjectValue();
            }
        } catch (ClassCastException | IllegalStateException | CacheException e) {
        }
        return null;
    }

}
