package com.project.web.validators;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author armdev
 */
@FacesConverter("timeConverter")
public class TimeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
       
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date nDate = null;
        if (value == null || value.trim().length() == 0) {
            return nDate;
        }
        try {
            nDate = sdf.parse(value);
        } catch (ParseException ex) {
        }
        return value;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uIComponent, Object value) {

      
        return null;
    }

}
