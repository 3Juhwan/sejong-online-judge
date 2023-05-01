package com.example.demo.service;

import com.example.demo.dto.submission.CreateHiddenSubmissionDto;
import com.example.demo.dto.submission.CreateSampleSubmissionDto;
import com.example.demo.dto.submission.CreateSampleSubmissionDto.SampleCase;
import com.example.demo.dto.submission.GetSubmissionDto;
import com.example.demo.entity.ContestProblem;
import com.example.demo.entity.Problem;
import com.example.demo.entity.Submission;
import com.example.demo.entity.User;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final UserRepository userRepository;
    private final ContestProblemRepository contestProblemRepository;
    private final GradeCodeService gradeCodeService;
    private final ProblemRepository problemRepository;
    private final TestDataRepository testDataRepository;


    public Object getSampleSubmission(CreateSampleSubmissionDto submissionDto) {
        return gradeCodeService.gradeSubmission(submissionDto.getSourceCode(), submissionDto.getSampleCaseList()).getBody();
    }

    @Transactional
    public Object getHiddenSubmission(CreateSampleSubmissionDto submissionDto, Principal principal) {
        ContestProblem contestProblem = contestProblemRepository.findById(submissionDto.getContestProblemId()).orElse(null);
        Problem problem = problemRepository.findById(contestProblem.getId()).orElse(null);
        List<SampleCase> sampleCases = testDataRepository.findAllTestDataIdByProblem(problem).orElse(null).stream()
                .map(x -> SampleCase.builder().dataSequence(x.getDataSequence()).inputData(x.getInputData()).outputData(x.getOutputData()).build()).toList();
        return gradeCodeService.gradeSubmission(submissionDto.getSourceCode(), sampleCases).getBody();
    }

    public Submission saveSubmission(CreateHiddenSubmissionDto submissionDto, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElse(null);
        ContestProblem contestProblem = contestProblemRepository.findById(submissionDto.getContestProblemId()).orElse(null);
        Submission submission = CreateHiddenSubmissionDto.toEntity(submissionDto, user, contestProblem, contestProblem.getProblem());
        return submissionRepository.save(submission);
    }

    public List<GetSubmissionDto> getSubmissionByCondition(Principal principal, Long contestProblemId, String username, String status, Pageable pageable) {
        User user = userRepository.findByUsername(username).orElse(null);
        ContestProblem contestProblem = contestProblemRepository.findById(contestProblemId).orElse(null);
        return submissionRepository.findAllByConditions(user, contestProblem, status, pageable).orElse(null)
                .stream()
                .map(submission -> GetSubmissionDto.from(submission, principal, false))
                .collect(Collectors.toList());
    }

    public GetSubmissionDto getSubmissionWithSourceCode(Principal principal, Long submissionId) {
        return GetSubmissionDto.from(submissionRepository.findById(submissionId).orElse(null), principal, true);
    }

}
