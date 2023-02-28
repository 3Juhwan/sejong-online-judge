package com.example.demo.repository;


import com.example.demo.dto.SubmissionDto;
import com.example.demo.entity.Problem;
import com.example.demo.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SubmissionRepositoryTest {

    private static Long problemId = 1L;
    private static String code = "package com.jaytech.codepractice; import java.util.stream.Stream; import lombok.Getter; @Getter public enum AccountStatus { INUSE(\\\"사용중\\\"), UNUSED(\\\"미사용\\\"), DELETED(\\\"삭제\\\"), UNKNOWN(\\\"알수없음\\\"); private final String description; AccountStatus(String description) { this.description = description; } public static AccountStatus find(String description) { return Arrays.stream(values()).filter(accountStatus -> accountStatus.description.equals(description)).findAny().orElse(UNKNOWN); } }";
    private static String language = "cpp";
    private static String username = "22011655";
    private static String email = "hello1@naver.com";
    private static String password = "dmswlqkqh12!";

    @Autowired
    SubmissionRepository submissionRepository;

    @Test
    public void 정상_등록_리포_테스트() throws Exception {
        // given
        User user = User.builder()
                .email(email)
                .password(password)
                .id(2L)
                .username(username)
                .email(email)
                .build();

        Problem problem = Problem.builder()
                .title("Hello")
                .build();

        // when

        // then

    }


}
