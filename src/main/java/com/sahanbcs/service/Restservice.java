package com.sahanbcs.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sahanbcs.mapper.StatusMapper;
import com.sahanbcs.models.Enomorators.DiliveryStatus;
import com.sahanbcs.models.Enomorators.Encoding;
import com.sahanbcs.models.Enomorators.RDiliveryStatus;
import com.sahanbcs.models.delivey.RequestDiliveryStatusReport;
import com.sahanbcs.models.delivey.ResponceDiliveryStatusReport;
import com.sahanbcs.models.recive.RequestReciveSMS;
import com.sahanbcs.models.recive.ResponceReciveSMS;
import com.sahanbcs.models.send.RequestSendSMS;
import com.sahanbcs.models.send.ResponceSendSMS;
import com.sahanbcs.util.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class Restservice {

    public ResponceSendSMS sendAndgetResoncesSms()    {
        ResponceSendSMS answer = null;
        try {

            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8080/smssend";
            List<String> sse = new ArrayList<String>();
            sse.add("tel:94774747447 ");
            RequestSendSMS rr = new RequestSendSMS("APP_999999", "password", "1.0", sse, "Hello", "77000", DiliveryStatus.DELIVERYREPORTREQUIRED, Encoding.Binarysms, 15.75);
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(rr);
            Logger.loginfo(Restservice.class ,"ResponceSendSMS.class  sendAndgetResoncesSms to json \n \t " + json);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<String>(json, headers);
             answer = restTemplate.postForObject(url, entity, ResponceSendSMS.class);
            if(false== StatusMapper.statusSuccess(answer.getStatusCode())){
                Logger.logerror(Restservice.class ,"THe Responce for Service Fails \n\t" + answer.toString() + "\n\t\tThe Error Message was \t" + StatusMapper.statusMapper(answer.getStatusCode()) );
            }else{
                Logger.loginfo(Restservice.class ,"THe Responce for Service Success \n\t" + answer.toString() + "\n\t\tThe Success Message was\t" +answer.getStatusCode() + "\t" + StatusMapper.statusMapper(answer.getStatusCode()) );
            }


        }catch (JsonProcessingException e){
            Logger.logerror(Restservice.class,e.getMessage());
        }catch (Exception e){
            Logger.logerror(Restservice.class,e.getMessage());
        }
        return answer;
    }

    public  ResponceReciveSMS reciveAndGetResponces()  {
        ResponceReciveSMS answer = null;
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8080/smsrecive";

            RequestReciveSMS rr = new RequestReciveSMS("1.0","23112" ,"tel:94777323654","my testing message from app1","APP_000001",Encoding.Text);
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(rr);
            Logger.loginfo(Restservice.class,"Restservice.class  ResponceReciveSMS json Request \n\t" + json);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<String>(json,headers);

              answer = restTemplate.postForObject(url, entity, ResponceReciveSMS.class);

            if(false== StatusMapper.statusSuccess(answer.getStatusCode())){
                Logger.logerror(Restservice.class ,"Restservice.class  ResponceReciveSMS THe Responce THe Responce for Sms Recive Fails \n\t" + answer.toString() + "\n\t\tThe Error Message was\t" + StatusMapper.statusMapper(answer.getStatusCode()) );
            }else{
                Logger.loginfo(Restservice.class, " Restservice.class  ResponceReciveSMS THe Responce THe Responce for Sms Recive Success  \n\t"+ answer.toString()+ "\n\t\tThe Success Message was\t" + StatusMapper.statusMapper(answer.getStatusCode()));
            }

        }catch (JsonProcessingException e){
            Logger.logerror(Restservice.class,e.getMessage());
        }catch (Exception e){
            Logger.logerror(Restservice.class,e.getMessage());
        }
        return  answer;
    }


    public ResponceDiliveryStatusReport getDiliveryReport() throws JsonProcessingException {
        ResponceDiliveryStatusReport answer  = null;
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8080/smsdiliverystatusreport";

            RequestDiliveryStatusReport rr = new RequestDiliveryStatusReport("tel:94774747447", "20120113082110", "MSG_000111", RDiliveryStatus.DELIVERED);
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(rr);
            Logger.loginfo(Restservice.class,"Restservice.class  ResponceDiliveryStatusReport json Request \n\t" + json);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<String>(json, headers);
              answer = restTemplate.postForObject(url, entity, ResponceDiliveryStatusReport.class);
            if(false== StatusMapper.statusSuccess(answer.getStatusCode())){
                Logger.logerror(Restservice.class ,"Restservice.class  ResponceDiliveryStatusReport THe Responce for Sms Recive Fails \n\t" + answer.toString() + "\n\t\tThe Error Message was\t" + StatusMapper.statusMapper(answer.getStatusCode()) );
            }else {
                Logger.loginfo(Restservice.class,"Restservice.class  ResponceDiliveryStatusReport THe Responce THe Responce for Sms  status dilivery Success  \n" + answer.toString() + "\n\t\tThe Success Message was\t" + StatusMapper.statusMapper(answer.getStatusCode()));
            }

        }catch (JsonProcessingException e){
            Logger.logerror(Restservice.class,e.getMessage());
        }catch (Exception e){
            Logger.logerror(Restservice.class,e.getMessage());
        }
        return answer;
    }

}
