package com.sahanbcs.controller;


import com.sahanbcs.models.SampleRes;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestConsume {

    @GetMapping("/")
    public String hello(){
        return  "<h1>Hello Sahan</h1>";
    }

    @GetMapping("/smssend")
    public void smsSend(){
        final String uri = "http://localhost:8800/";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        System.out.println(result);
    }


    @GetMapping("/smssendrest")
    public void smsSendRest(){

        RestTemplate restTemplate = new RestTemplate();



        String url = "http://localhost:8080/sms";
        String requestJson = "{\"name\":\"sahan\" , \"age\":\"3\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
        SampleRes answer = restTemplate.postForObject(url, entity, SampleRes.class);
        System.out.println(answer.getStatus());
    }






}
