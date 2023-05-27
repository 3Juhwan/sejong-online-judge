package com.example.demo.service;

import com.example.demo.dto.grade.GetGradedScoreBoardListResponseDto;
import com.example.demo.dto.grade.GetGradedScoreBoardResponseDto;
import com.example.demo.dto.grade.PostGradedScoreBoardRequestDto;
import com.example.demo.entity.Contest;
import com.example.demo.entity.Grade;
import com.example.demo.entity.User;
import com.example.demo.repository.ContestRepository;
import com.example.demo.repository.GradeRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final GradeRepository gradeRepository;
    private final UserRepository userRepository;
    private final ContestRepository contestRepository;
    private final SubmissionService submissionService;

    public List<GetGradedScoreBoardListResponseDto> getGradedScoreBoardList(Principal principal) {
        Optional<User> userOptional = userRepository.findByUsername(principal.getName());
        if (userOptional.isEmpty()) {
            System.out.println("User가 없습니다.");
            return null;
        }
        User user = userOptional.get();
        Optional<List<Grade>> gradeOptional = gradeRepository.findAllByCreator(user);
        if (gradeOptional.isEmpty()) {
            System.out.println("Grade가 없습니다. ");
            return null;
        }
        List<Grade> gradeList = gradeOptional.get();
        return gradeList.stream()
                .map(grade -> GetGradedScoreBoardListResponseDto.from(grade, contestRepository.getById(grade.getContest().getId()))).toList();
    }

    public List<GetGradedScoreBoardResponseDto> saveGradedScoreBoard(PostGradedScoreBoardRequestDto requestDto, Principal principal) {
        Optional<Contest> contestOptional = contestRepository.findById(requestDto.getContestId());
        if (contestOptional.isEmpty()) {
            System.out.println("Contest가 없습니다. ");
        }
        Contest contest = contestOptional.get();
        Optional<User> userOptional = userRepository.findByUsername(principal.getName());
        if (userOptional.isEmpty()) {
            System.out.println("User가 없습니다. ");
        }
        User user = userOptional.get();
        Grade grade = PostGradedScoreBoardRequestDto.toEntity(requestDto, contest, user);
        Grade save = gradeRepository.save(grade);
        return submissionService.getGradedScoreBoard(save.getId());
    }
}
