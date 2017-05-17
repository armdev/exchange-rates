package com.project.web.handlers;

import com.project.web.rest.ResponseModel;
import java.io.InputStream;
import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
public class RateCacheBean implements Serializable {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(RateCacheBean.class);
    private static final long serialVersionUID = 8163039174281168443L;

    private transient CacheManager manager;
    @Setter
    private transient Cache liveCache;
    @Setter
    private transient Cache historicalCache;

    public RateCacheBean() {
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
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(() -> {
            if (liveCache == null) {
                liveCache = manager.getCache("live.cache");
            }
            LOG.info("PUTTING CACHE " + key);
            LOG.info("PUTTING CACHE " + value.toString());
            liveCache.put(new Element(key, value));
            liveCache.flush();
        });
        return true;
    }

    public Object getLiveCache(String key) {
        if (key == null) {
            return null;
        }
        try {
            if (this.liveCache == null) {
                this.liveCache = this.manager.getCache("live.cache");
            }
            Element elem = this.liveCache.get(key);

            LOG.info("getLiveCache " + key);

            if ((elem != null) && (elem.getObjectValue() != null)) {
                LOG.info("getLiveCache### " + elem.toString());
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
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(() -> {
            if (historicalCache == null) {
                historicalCache = manager.getCache("historical.cache");
            }
            historicalCache.put(new Element(key, value));
            historicalCache.flush();
        });

        return true;
    }

    public Object getHistoricalCache(String key) {
        if (key == null) {
            return null;
        }
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
