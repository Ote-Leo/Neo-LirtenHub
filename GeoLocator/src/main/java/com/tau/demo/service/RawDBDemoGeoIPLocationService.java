package com.tau.demo.service;
import com.maxmind.geoip2.DatabaseReader;
public class RawDBDemoGeoIPLocationService {
    private DatabaseReader dbReader;
    
    public RawDBDemoGeoIPLocationService() throws IOException {
        File database = new File("your-mmdb-location");
        dbReader = new DatabaseReader.Builder(database).build();
    }
    
    public GeoIP getLocation(String ip) 
      throws IOException, GeoIp2Exception {
        InetAddress ipAddress = InetAddress.getByName(ip);
        CityResponse response = dbReader.city(ipAddress);
        
        String cityName = response.getCity().getName();
        String latitude = 
          response.getLocation().getLatitude().toString();
        String longitude = 
          response.getLocation().getLongitude().toString();
        return new GeoIP(ip, cityName, latitude, longitude);
    }
}
