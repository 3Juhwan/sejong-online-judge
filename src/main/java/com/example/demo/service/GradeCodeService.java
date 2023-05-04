package com.example.demo.service;

import com.example.demo.dto.submission.CreateSampleSubmissionDto.SampleCase;
import com.example.demo.dto.submission.SubmissionResponseDto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GradeCodeService {
    public List<SubmissionResponseDto> gradeSubmission(String sourceCode, List<SampleCase> sampleCaseList) {
        ResponseEntity<Object> resultMap = new ResponseEntity<>(null, null, 200);
        String url = "http://127.0.0.1:5000/sample";

        JSONObject body = new JSONObject();

        body.put("source_code", sourceCode);

        JSONArray jsonArray = new JSONArray();
        for (SampleCase sampleCase : sampleCaseList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("data_sequence", sampleCase.getDataSequence());
            jsonObject.put("input_data", sampleCase.getInputData());
            jsonObject.put("output_data", sampleCase.getOutputData());
            jsonArray.put(jsonObject);
        }
        body.put("test_data_list", jsonArray);

        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<?> entity = new HttpEntity<>(body.toString(), header);
            List<SubmissionResponseDto> body1 = restTemplate.exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<List<SubmissionResponseDto>>() {
            }).getBody();
            return body1;

            //에러처리해야댐
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            System.out.println("error");
            System.out.println(e);
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
