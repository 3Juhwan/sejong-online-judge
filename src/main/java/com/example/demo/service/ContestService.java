package com.example.demo.service;

import com.example.demo.dto.contest.CreateContestDto;
import com.example.demo.dto.contest.GetContestByCourseDto;
import com.example.demo.dto.contest.GetContestDetailDto;
import com.example.demo.dto.contest.UpdateContestSequenceDto;
import com.example.demo.dto.contestProblem.GetContestProblemByContestDto;
import com.example.demo.dto.submitstatus.GetSubmitStatusByUserDto;
import com.example.demo.entity.Contest;
import com.example.demo.entity.Course;
import com.example.demo.entity.SubmitStatus;
import com.example.demo.repository.*;
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
    private final ContestProblemRepository contestProblemRepository;
    private final UserRepository userRepository;


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
        return contestRepository.findAllContestByCourse(courseId).stream().toList();
    }

    public GetContestDetailDto getContestDetail(Long contestId, Principal principal) {
        Contest contest = contestRepository.findById(contestId).orElse(null);
        String username = principal.getName();

        List<GetContestProblemByContestDto> contestProblemList = contestProblemService.getContestProblemList(contestId);

        List<GetSubmitStatusByUserDto> submitStatuseList = contestProblemList.stream()
                .map(contestProblem -> {
                    GetSubmitStatusByUserDto getSubmitStatusByUserDto = submitStatusRepository.findByUserAndContestProblem(username, contestProblem.getContestProblemId()).orElse(null);
                    if (getSubmitStatusByUserDto == null) {
                        SubmitStatus submitStatus = new SubmitStatus(userRepository.findByUsername(username).orElse(null), contestProblemRepository.findById(contestProblem.getContestProblemId()).orElse(null));
                        submitStatusRepository.save(submitStatus);
                    }
                    return submitStatusRepository.findByUserAndContestProblem(username, contestProblem.getContestProblemId()).orElse(null);
                })
                .toList();

        GetContestDetailDto contestDetail = GetContestDetailDto.builder()
                .title(contest.getTitle())
                .startingTime(contest.getStartingTime())
                .endingTime(contest.getEndingTime())
                .contestProblemDtoList(new ArrayList<>())
                .build();

        IntStream.range(0, contestProblemList.size())
                .forEach(i -> contestDetail.from(contestProblemList.get(i), submitStatuseList.get(i)));

        return contestDetail;
    }

}
