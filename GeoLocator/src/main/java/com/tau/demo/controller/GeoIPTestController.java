package com.tau.demo.controller;
import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tau.demo.models.GeoIP;
import com.tau.demo.service.RawDBDemoGeoIPLocationService;

@RestController
public class GeoIPTestController {
    private RawDBDemoGeoIPLocationService locationService;
    
    public GeoIPTestController() throws IOException {
        locationService = new RawDBDemoGeoIPLocationService();
    }
    
    @PostMapping("/GeoIPTest")
    public GeoIP getLocation(
      @RequestParam(value="ipAddress", required=true) String ipAddress
    ) throws Exception {
      
        RawDBDemoGeoIPLocationService locationService 
          = new RawDBDemoGeoIPLocationService();
        return locationService.getLocation(ipAddress);
    }
}
