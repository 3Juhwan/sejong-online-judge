//package com.example.demo.service;
//
//
//import com.example.demo.dto.SubmissionDto;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//public class SubmissionServiceTest {
//
//    private static String username = "3juhwan";
//    private static Long problemId = 1L;
//    private static String code = "package com.jaytech.codepractice; import java.util.stream.Stream; import lombok.Getter; @Getter public enum AccountStatus { INUSE(\\\"사용중\\\"), UNUSED(\\\"미사용\\\"), DELETED(\\\"삭제\\\"), UNKNOWN(\\\"알수없음\\\"); private final String description; AccountStatus(String description) { this.description = description; } public static AccountStatus find(String description) { return Arrays.stream(values()).filter(accountStatus -> accountStatus.description.equals(description)).findAny().orElse(UNKNOWN); } }";
//    private static String language = "cpp";
//
//    @Autowired
//    SubmissionService submissionService;
//
//    @Test
//    public void 제출_등록() throws Exception {
//        // given
//        SubmissionDto submissionDto = SubmissionDto.builder()
//                .username(username)
//                .problemId(problemId)
//                .code(code)
//                .language(language)
//                .build();
//
//        // when
//        SubmissionDto submissionDto1 = submissionService.saveSubmission(submissionDto);
//
//        // then
//
//    }
//}
