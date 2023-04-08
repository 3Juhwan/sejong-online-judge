package com.example.demo.service;

import com.example.demo.dto.courseuser.SaveCourseUserDto;
import com.example.demo.dto.courseuser.SaveCourseUserResponseDto;
import com.example.demo.dto.user.GetListOfCourseUserDto;
import com.example.demo.entity.Course;
import com.example.demo.entity.CourseUser;
import com.example.demo.entity.User;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.CourseUserRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CourseUserService {

    private final CourseUserRepository courseUserRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    private static List<String> splitCourseUser(String joinedCourseUser) {
        return Arrays.stream(joinedCourseUser.split("\n")).toList();
    }

    private static String joinCourseUser(List<String> splitCourseUser) {
        return String.join("\n", splitCourseUser);
    }

    private static Boolean isNumericString(String str) {
        if (str == null) {
            return false;
        }
        int len = str.length();
        if (len == 0) {
            return false;
        }
        for (int i = 0; i < len; i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public GetListOfCourseUserDto getListOfCourseUser(Long courseId) {
        Course course = courseRepository.findById(courseId).orElse(null);

        List<String> courseUserList = courseUserRepository.findAllCourseUsersByCourse(course).stream()
                .map(cu -> cu.getUser().getUsername())
                .filter(username -> isNumericString(username))
                .toList();

        String ta = courseUserList.stream()
                .filter(c -> c.startsWith("TA"))
                .findFirst()
                .orElse(null);

        if (ta != null) {
            courseUserList.remove(ta);
        }

        return GetListOfCourseUserDto.builder()
                .studentList(joinCourseUser(courseUserList))
                .teachingAssistantList(ta)
                .build();
    }

    public SaveCourseUserResponseDto saveCourseUser(SaveCourseUserDto courseUserDto) {
        SaveCourseUserResponseDto responseDto = new SaveCourseUserResponseDto();
        Course course = courseRepository.getById(courseUserDto.getCourseId());

//        if(course == null) {
//
//        }

        // 현재 등록된 유저 정보
        List<CourseUser> currentCourseUserList = courseUserRepository.findAllCourseUsersByCourse(course);

        HashMap<String, CourseUser> currentUserMap = new HashMap<>();
        for (CourseUser courseUser : currentCourseUserList) {
            try {
                String username = courseUser.getUser().getUsername();
                currentUserMap.put(username, courseUser);
            } catch (Exception e) {
                System.out.println("courseUser 에 등록된 사용자가 존재하지 않습니다. ");
            }
        }

        // 등록 요청된 유저 정보
        List<String> requestedCourseUserList = new ArrayList<>();
        requestedCourseUserList.addAll(splitCourseUser(courseUserDto.getStudentList()));
        requestedCourseUserList.addAll(splitCourseUser(courseUserDto.getTeachingAssistantList()));

        // 요청된 유저 등록 및 중복 유저 제거
        for (String username : requestedCourseUserList) {
            CourseUser removedUser = currentUserMap.remove(username);
            if (removedUser != null) {
                continue;
            }
            User user = userRepository.findByUsername(username).orElse(null);
            if (user == null) {
                responseDto.addUnregisteredUser(username);
                continue;
            }
            courseUserRepository.save(new CourseUser(user, course));
            responseDto.addSavedUser(username);
        }

        // 기존 유저 중 미등록 유저 제거
        Set<String> unregisteredUser = currentUserMap.keySet();
        for (String username : unregisteredUser) {
            CourseUser courseUser = currentUserMap.get(username);
            courseUserRepository.delete(courseUser);
            responseDto.addDeletedUser(username);
        }

        // 삭제된 유저, 새로 등록된 유저, 미등록 유저
        return responseDto;
    }

}
