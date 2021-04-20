package com.sahanbcs.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sahanbcs.models.Enomorators.DiliveryStatus;
import com.sahanbcs.models.Enomorators.Encoding;
import com.sahanbcs.models.sample.SampleReq;
import com.sahanbcs.models.sample.SampleRes;
import com.sahanbcs.models.send.RequestSendSMS;
import com.sahanbcs.models.send.ResponceSendSMS;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

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


    @GetMapping("/samplesend")
    public void samplesend() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/sms";
        SampleReq ss = new SampleReq("Udesh" ,"22");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(ss);
        String requestJson = "{\"name\":\"sahan\" , \"age\":\"3\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(json,headers);
        SampleRes answer = restTemplate.postForObject(url, entity, SampleRes.class);
        System.out.println(answer.getStatus());
    }


    @GetMapping("/smssend")
    public void smsSendRest() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/smssend";
        List<String>  sse = new ArrayList<String>( );
        sse.add("tel:94774747447 ");
        RequestSendSMS rr = new RequestSendSMS("APP_999999","password","1.0",sse,"Hello","77000", DiliveryStatus.DELIVERYREPORTREQUIRED, Encoding.Binarysms,15.75);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(rr);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(json,headers);
        ResponceSendSMS  answer = restTemplate.postForObject(url, entity, ResponceSendSMS.class);
        System.out.println(answer.toString());
    }




}
