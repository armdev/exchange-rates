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
    "success",
    "terms",
    "privacy",
    "timestamp",
    "source",
    "quotes"
})
public class ResponseModel {

    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("terms")
    private String terms;
    @JsonProperty("privacy")
    private String privacy;
    @JsonProperty("timestamp")
    private Integer timestamp;
    @JsonProperty("source")
    private String source;
    @JsonProperty("quotes")
    private Quotes quotes;
    @JsonIgnore
    private final Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("success")
    public Boolean getSuccess() {
        return success;
    }

    @JsonProperty("success")
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @JsonProperty("terms")
    public String getTerms() {
        return terms;
    }

    @JsonProperty("terms")
    public void setTerms(String terms) {
        this.terms = terms;
    }

    @JsonProperty("privacy")
    public String getPrivacy() {
        return privacy;
    }

    @JsonProperty("privacy")
    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    @JsonProperty("timestamp")
    public Integer getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty("source")
    public String getSource() {
        return source;
    }

    @JsonProperty("source")
    public void setSource(String source) {
        this.source = source;
    }

    @JsonProperty("quotes")
    public Quotes getQuotes() {
        return quotes;
    }

    @JsonProperty("quotes")
    public void setQuotes(Quotes quotes) {
        this.quotes = quotes;
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
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.timestamp);
        hash = 37 * hash + Objects.hashCode(this.source);
        hash = 37 * hash + Objects.hashCode(this.quotes);
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
        final ResponseModel other = (ResponseModel) obj;
        if (!Objects.equals(this.source, other.source)) {
            return false;
        }
        if (!Objects.equals(this.timestamp, other.timestamp)) {
            return false;
        } else {
        }
        return Objects.equals(this.quotes, other.quotes);
    }

    @Override
    public String toString() {
        return "ResponseModel{" + "success=" + success + ", terms=" + terms + ", privacy=" + privacy + ", timestamp=" + timestamp + ", source=" + source + ", quotes=" + quotes + ", additionalProperties=" + additionalProperties + '}';
    }
    
    

}
