package com.sahanbcs.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sahanbcs.models.Enomorators.RDiliveryStatus;
import com.sahanbcs.models.delivey.RequestDiliveryStatusReport;
import com.sahanbcs.models.delivey.ResponceDiliveryStatusReport;
import com.sahanbcs.models.recive.RequestReciveSMS;
import com.sahanbcs.models.recive.ResponceReciveSMS;
import com.sahanbcs.models.send.RequestSendSMS;
import com.sahanbcs.models.send.ResponceSendSMS;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class Restservice {

    public ResponceSendSMS sendAndgetResoncesSms() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/smssend";
        List<String> sse = new ArrayList<String>( );
        sse.add("tel:94774747447 ");
        RequestSendSMS rr = new RequestSendSMS("APP_999999","password","1.0",sse,"Hello","77000", "1", "245",15.75);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(rr);
        System.out.println("SSSSSSSSSSSSS  " + json);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(json,headers);
        ResponceSendSMS answer = restTemplate.postForObject(url, entity, ResponceSendSMS.class);
        System.out.println ( "THe Responce \n\t"+ answer.toString());
        return answer;
    }

    public  ResponceReciveSMS reciveAndGetResponces() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/smsrecive";

        RequestReciveSMS rr = new RequestReciveSMS("1.0","23112" ,"tel:94777323654","my testing message from app1","APP_000001","0");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(rr);
        System.out.println("rrrrrrr  " + json);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(json,headers);
        ResponceReciveSMS answer = restTemplate.postForObject(url, entity, ResponceReciveSMS.class);
        System.out.println ( "THe Responce \n\t"+ answer.toString());
        return  answer;
    }


    public ResponceDiliveryStatusReport getDiliveryReport() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/smsdiliverystatusreport";

        RequestDiliveryStatusReport rr = new RequestDiliveryStatusReport( "tel:94774747447","20120113082110","MSG_000111", RDiliveryStatus.DELIVERED);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(rr);
        System.out.println("rrrrrrr  " + json);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(json,headers);
        ResponceDiliveryStatusReport answer = restTemplate.postForObject(url, entity, ResponceDiliveryStatusReport.class);
        System.out.println ( "THe Responce \n\t"+ answer.toString());
        return answer;
    }

}
