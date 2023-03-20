package com.example.demo.service;

import com.example.demo.dto.contest.CreateContestDto;
import com.example.demo.dto.contest.GetContestByCourseDto;
import com.example.demo.entity.Contest;
import com.example.demo.repository.ContestRepository;
import com.example.demo.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContestService {

    private final ContestRepository contestRepository;
    private final CourseRepository courseRepository;


    public void saveContests(List<CreateContestDto> contestDtoList) {
        List<Contest> contests = contestDtoList.stream()
                .map(c -> CreateContestDto.toEntity(c, courseRepository.getById(c.getCourseId())))
                .collect(Collectors.toList());
        contestRepository.saveAll(contests);
    }

    public void deleteContest(Long contestId) {
        contestRepository.deleteById(contestId);
    }

    public List<GetContestByCourseDto> getContests(Long courseId) {
        return contestRepository.findAllContestByCourse(courseId).stream()
                .collect(Collectors.toList());
    }
}
