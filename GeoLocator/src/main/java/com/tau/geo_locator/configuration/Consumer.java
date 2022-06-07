package com.tau.geo_locator.configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.util.LinkedHashMap;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.tau.geo_locator.models.GeoIP;

import java.util.LinkedHashMap;


@Component
public class Consumer {
  
    @Autowired
    RabbitTemplate template;

    @RabbitListener(queues = "geo_queue") 
    public void geoConsumer(Message message) throws IOException, GeoIp2Exception{
        String city_name = "";

        try {
            city_name = getLocation().getCity();
        } catch (Exception e) {
            city_name = "Cairo";
        }

        Message response = new Message();
        response.setMethod("automatic_location");

        LinkedHashMap data = (LinkedHashMap) message.getData();

        data.put("city_name", city_name);

        response.setData(data);
        template.convertAndSend(RabbitMQConfiguration.EXCHANGE, RabbitMQConfiguration.USER_ROUTING_KEY, response);      

        
    }

    public GeoIP getLocation() 
    throws IOException, GeoIp2Exception {
        File database = new File("GeoLite2-City.mmdb");
        DatabaseReader dbReader = new DatabaseReader.Builder(database).build();

        URL whatismyip = new URL("http://checkip.amazonaws.com");
        BufferedReader in = new BufferedReader(new InputStreamReader(
                        whatismyip.openStream()));
        
        String ip = in.readLine(); 


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
