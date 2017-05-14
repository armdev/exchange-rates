package com.project.web.auth;

import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import com.project.entities.LocationModel;
import java.io.File;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;

@ManagedBean(name = "locationBean")
@SessionScoped
public class LocationBean {

    private static final Logger LOGGER = Logger.getLogger(LocationBean.class);

    private FacesContext context = null;
    private ExternalContext externalContext = null;

    public LocationBean() {

    }

    public LocationModel findLocation(String ip) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        context = FacesContext.getCurrentInstance();
        externalContext = context.getExternalContext();
        String path = externalContext.getRealPath("/WEB-INF/GeoLiteCity.dat");
        LOGGER.info("GeoLiteCity file path is  " + path);
        File file = new File(path);
        if (ip == null) {
            ip = "188.72.126.151";
        }
        //-Djava.net.preferIPv4Stack=true
        if (ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
            ip = "188.72.126.151";
        }
        if (ip.equalsIgnoreCase("127.0.0.1")) {
           
            ip = "188.72.126.151";
        }
        LOGGER.info("IP PASSED " + ip);
        return getLocation(ip, file);
    }

    private LocationModel getLocation(String ipAddress, File file) {
        LocationModel locationModel = new LocationModel();
        try {
            LookupService lookup = new LookupService(file, LookupService.GEOIP_MEMORY_CACHE);
            Location locationServices = lookup.getLocation(ipAddress);
            if (locationServices != null) {
                if (locationServices.city != null) {
                    locationModel.setCity(locationServices.city);
                }
                if (locationServices.countryName != null) {
                    locationModel.setCountryName(locationServices.countryName);
                }
                if (locationServices.countryCode != null) {
                    locationModel.setCountryCode(locationServices.countryCode);
                }
                if (locationServices.postalCode != null) {
                    locationModel.setPostalCode(locationServices.postalCode);
                }
                if (locationServices.latitude != 0) {
                    locationModel.setLatitude(Double.valueOf(locationServices.latitude));
                }
                if (locationServices.longitude != 0) {
                    locationModel.setLongitude(Double.valueOf(locationServices.longitude));
                }
            }
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }
        return locationModel;

    }
}
