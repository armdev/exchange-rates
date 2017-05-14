package com.project.entities;

import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@SuppressWarnings("serial")
public class LocationModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String countryCode;
    private String countryName;
    private String city;
    private Double latitude;
    private Double longitude;
    private String postalCode;

}
