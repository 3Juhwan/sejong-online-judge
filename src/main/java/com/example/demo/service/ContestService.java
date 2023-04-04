package com.example.demo.service;

import com.example.demo.dto.contest.CreateContestDto;
import com.example.demo.dto.contest.GetContestByCourseDto;
import com.example.demo.dto.contest.GetContestDetailDto;
import com.example.demo.dto.contest.UpdateContestSequenceDto;
import com.example.demo.dto.contestProblem.GetContestProblemByContestDto;
import com.example.demo.dto.submitstatus.GetSubmitStatusByUserDto;
import com.example.demo.entity.Contest;
import com.example.demo.entity.Course;
import com.example.demo.repository.ContestRepository;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.SubmitStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class ContestService {

    private final ContestRepository contestRepository;
    private final CourseRepository courseRepository;
    private final SubmitStatusRepository submitStatusRepository;
    private final ContestProblemService contestProblemService;


    public CreateContestDto saveContest(CreateContestDto contestDto) {
        Course course = courseRepository.getById(contestDto.getCourseId());
        contestDto.setSequence(course.getNumberOfContest() + 1);
        Contest savedContest = contestRepository.save(CreateContestDto.toEntity(contestDto, course));
        courseRepository.save(course.updateEntity(savedContest.getSequence()));
        return CreateContestDto.from(savedContest);
    }

    public void updateContest(CreateContestDto contestDto, Long contestId) {
        Contest contest = contestRepository.findById(contestId).orElse(null);
        contestRepository.save(contest.updateEntity(contestDto));
    }

    public void updateContestSequence(UpdateContestSequenceDto contestDto, Long contestId) {
        Contest contest = contestRepository.findById(contestId).orElse(null);
        contestRepository.save(contest.updateEntity(contestDto));
    }

    public void deleteContest(Long contestId) {
        contestRepository.deleteById(contestId);
    }

    public List<GetContestByCourseDto> getContests(Long courseId) {
        return contestRepository.findAllContestByCourse(courseId).stream().collect(Collectors.toList());
    }

    public GetContestDetailDto getContestDetail(Long contestId, Principal principal) {
        Contest contest = contestRepository.findById(contestId).orElse(null);
        String username = principal.getName();

        List<GetContestProblemByContestDto> contestProblems = contestProblemService.getContestProblems(contestId, principal);
        List<GetSubmitStatusByUserDto> submitStatuses = contestProblems.stream()
                .map(contestProblem -> submitStatusRepository.
                        findByUserAndContestProblem(username, contestProblem.getContestProblemId()).orElse(null))
                .toList();

        GetContestDetailDto contestDetail = GetContestDetailDto.builder()
                .title(contest.getTitle())
                .startingTime(contest.getStartingTime())
                .endingTime(contest.getEndingTime())
                .contestProblemDtoList(new ArrayList<>())
                .build();

        IntStream.range(0, contestProblems.size())
                .forEach(i -> contestDetail.from(contestProblems.get(i), submitStatuses.get(i)));

        return contestDetail;
    }

}
