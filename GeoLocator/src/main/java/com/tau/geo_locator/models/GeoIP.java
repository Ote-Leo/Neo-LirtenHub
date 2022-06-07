package com.tau.geo_locator.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GeoIP {
    private String ipAddress;
    private String city;
    private String latitude;
    private String longitude;
}
