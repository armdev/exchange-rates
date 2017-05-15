package com.project.web.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
    "USDUSD",
    "USDEUR",
    "USDAMD",
    "USDCBP",
    "USDNZD",
    "USDJPY",
    "USDHUF",
    "USDZWD",
    "USDCAD"
})
public class Quotes {

    @JsonProperty("USDUSD")
    private Integer uSDUSD;
    @JsonProperty("USDEUR")
    private Double uSDEUR;
    @JsonProperty("USDAMD")
    private Double uSDAMD;
    @JsonProperty("USDCBP")
    private Double uSDCBP;
    @JsonProperty("USDNZD")
    private Double uSDNZD;
    @JsonProperty("USDAUD")
    private Double uSDAUD;
    @JsonProperty("USDJPY")
    private Double uSDJPY;
    @JsonProperty("USDHUF")
    private Double uSDHUF;
    @JsonProperty("USDZWD")
    private Double uSDZWD;
    @JsonProperty("USDCAD")
    private Double uSDCAD;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("USDUSD")
    public Integer getUSDUSD() {
        return uSDUSD;
    }

    @JsonProperty("USDUSD")
    public void setUSDUSD(Integer uSDUSD) {
        this.uSDUSD = uSDUSD;
    }

    @JsonProperty("USDEUR")
    public Double getUSDEUR() {
        return uSDEUR;
    }

    @JsonProperty("USDEUR")
    public void setUSDEUR(Double uSDEUR) {
        this.uSDEUR = uSDEUR;
    }   


    public Double getUSDAMD() {
        return uSDAMD;
    }

    public void setUSDAMD(Double uSDAMD) {
        this.uSDAMD = uSDAMD;
    }

    public Double getUSDCBP() {
        return uSDCBP;
    }

    public void setUSDCBP(Double uSDCBP) {
        this.uSDCBP = uSDCBP;
    }

    public Double getUSDNZD() {
        return uSDNZD;
    }

    public void setUSDNZD(Double uSDNZD) {
        this.uSDNZD = uSDNZD;
    }

    public Double getUSDAUD() {
        return uSDAUD;
    }

    public void setUSDAUD(Double uSDAUD) {
        this.uSDAUD = uSDAUD;
    }

    public Double getUSDJPY() {
        return uSDJPY;
    }

    public void setUSDJPY(Double uSDJPY) {
        this.uSDJPY = uSDJPY;
    }

    public Double getUSDHUF() {
        return uSDHUF;
    }

    public void setUSDHUF(Double uSDHUF) {
        this.uSDHUF = uSDHUF;
    }

    public Double getUSDZWD() {
        return uSDZWD;
    }

    public void setUSDZWD(Double uSDZWD) {
        this.uSDZWD = uSDZWD;
    }

    public Double getUSDCAD() {
        return uSDCAD;
    }

    public void setUSDCAD(Double uSDCAD) {
        this.uSDCAD = uSDCAD;
    }

    
    
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "Quotes{" + "uSDUSD=" + uSDUSD + ", uSDEUR=" + uSDEUR + ", additionalProperties=" + additionalProperties + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.uSDUSD);
        hash = 79 * hash + Objects.hashCode(this.uSDEUR);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Quotes other = (Quotes) obj;
        if (!Objects.equals(this.uSDUSD, other.uSDUSD)) {
            return false;
        }
        return Objects.equals(this.uSDEUR, other.uSDEUR);
    }

}
