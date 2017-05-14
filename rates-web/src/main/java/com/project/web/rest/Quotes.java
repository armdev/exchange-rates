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
    "USDEUR"
})
public class Quotes {

    @JsonProperty("USDUSD")
    private Integer uSDUSD;
    @JsonProperty("USDEUR")
    private Double uSDEUR;
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
