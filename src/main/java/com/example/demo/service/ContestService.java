package com.example.demo.service;

import com.example.demo.dto.FindCourseContestDto;
import com.example.demo.dto.contest.AddContestDto;
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

    public List<AddContestDto> saveContest(List<AddContestDto> contestDtoList) {
        List<AddContestDto> result = new ArrayList<>();
        for (AddContestDto contestDto : contestDtoList) {
            Contest contest = AddContestDto.toEntity(contestDto, courseRepository.getById(contestDto.getCourseId()));
            result.add(AddContestDto.from(contestRepository.save(contest)));
        }
        return result;
    }

    public void deleteContest(Long contestId) {
        contestRepository.deleteById(contestId);
    }

    public List<FindCourseContestDto> findContestByContest(Long courseId) {
        Course findCourse = courseRepository.findById(courseId).get();
        return contestRepository.findContestByCourse(findCourse).stream()
                .map(c -> FindCourseContestDto.from(c))
                .collect(Collectors.toList());
    }
}
