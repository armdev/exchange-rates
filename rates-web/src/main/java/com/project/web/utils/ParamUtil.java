
package com.project.web.utils;

import java.text.NumberFormat;
import java.text.ParseException;

/**
 *
 * @author armdev
 */
public class ParamUtil {

    static public Long longValue(String strValue) {
        Long reValue = null;

        if ((strValue == null) || (strValue.trim().equals(""))) {
            strValue = null;
        } else if (strValue == null) {
            return null;
        }

        NumberFormat nf = NumberFormat.getInstance();

        try {
            reValue = (Long) nf.parse(strValue).longValue();
        } catch (ParseException ex) {
        }

        return reValue;
    }

    static public Long longValue(Object strValue) {
        return longValue((strValue != null)
                ? strValue.toString()
                : null);
    }

}
