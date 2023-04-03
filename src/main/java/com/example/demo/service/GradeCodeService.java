package com.example.demo.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;

@Service
public class GradeCodeService {
    public ResponseEntity<Object> gradeSubmission(Long submissionId ) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        ResponseEntity<Object> resultMap = new ResponseEntity<>(null,null,200);
        String url = "http://127.0.0.1:5000/score?submission_id=" + submissionId;

        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders header = new HttpHeaders();
            HttpEntity<?> entity = new HttpEntity<>(header);

            UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).build();

            resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Object.class);

            result.put("statusCode", resultMap.getStatusCodeValue()); //http status code를 확인
            result.put("header", resultMap.getHeaders()); //헤더 정보 확인
            result.put("body", resultMap.getBody()); //실제 데이터 정보 확인

            System.out.println("resultMap.getBody() = " + resultMap.getBody());

            //에러처리해야댐
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            result.put("statusCode", e.getRawStatusCode());
            result.put("body"  , e.getStatusText());
            System.out.println("error");
            System.out.println(e.toString());
            return resultMap;
        }
        catch (Exception e) {
            result.put("statusCode", "999");
            result.put("body", "excpetion오류");
            System.out.println(e.toString());
            return resultMap;
        }
        return resultMap;
    }
}
