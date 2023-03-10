package com.example.demo.service;

import com.example.demo.dto.GetCourseContestDto;
import com.example.demo.dto.contest.CreateContestDto;
import com.example.demo.entity.Contest;
import com.example.demo.entity.Course;
import com.example.demo.repository.ContestRepository;
import com.example.demo.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContestService {

    private final ContestRepository contestRepository;
    private final CourseRepository courseRepository;

    public List<CreateContestDto> saveContests(List<CreateContestDto> contestDtoList) {
        List<CreateContestDto> result = new ArrayList<>();
        for (CreateContestDto contestDto : contestDtoList) {
            Contest contest = CreateContestDto.toEntity(contestDto, courseRepository.getById(contestDto.getCourseId()));
            result.add(CreateContestDto.from(contestRepository.save(contest)));
        }
        return result;
    }

    public void deleteContest(Long contestId) {
        contestRepository.deleteById(contestId);
    }

    public List<GetCourseContestDto> getContests(Long courseId) {
        Course findCourse = courseRepository.findById(courseId).get();
        return contestRepository.findContestByCourse(findCourse).stream()
                .map(c -> GetCourseContestDto.from(c))
                .collect(Collectors.toList());
    }
}
