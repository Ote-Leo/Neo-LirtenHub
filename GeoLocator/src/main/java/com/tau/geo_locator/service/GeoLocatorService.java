package com.tau.geo_locator.service;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.tau.geo_locator.models.GeoIP;
import java.net.*;
import java.io.*;

public class GeoLocatorService {
  private DatabaseReader dbReader;

  @Async("asyncExecutor")
  public GeoLocatorService() throws IOException {
    File database = new File("GeoLite2-City.mmdb");
    dbReader = new DatabaseReader.Builder(database).build();
  }

  public String getIp() throws IOException {

    URL whatismyip = new URL("http://checkip.amazonaws.com");
    BufferedReader in = new BufferedReader(new InputStreamReader(
        whatismyip.openStream()));

    String ip = in.readLine();
    System.out.println(ip);
    return ip;

  }

  @Async("asyncExecutor")
  public GeoIP getLocation()
      throws IOException, GeoIp2Exception {
    InetAddress ipAddress = InetAddress.getByName(getIp());

    CityResponse response = dbReader.city(ipAddress);

    String cityName = response.getCity().getName();

    String latitude = response.getLocation().getLatitude().toString();

    String longitude = response.getLocation().getLongitude().toString();
    return new GeoIP(getIp(), cityName, latitude, longitude);
  }
}
