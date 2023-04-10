package com.example.demo.service;

import com.example.demo.dto.problem.CreateProblemDto;
import com.example.demo.dto.problem.GetProblemDto;
import com.example.demo.dto.problem.GetProblemInfoDto;
import com.example.demo.dto.problem.UpdateProblemDto;
import com.example.demo.entity.Problem;
import com.example.demo.entity.User;
import com.example.demo.repository.ProblemRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProblemService {

    private final ProblemRepository problemRepository;
    private final UserRepository userRepository;

    public CreateProblemDto saveProblem(CreateProblemDto problemDto, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElse(null);
        Problem problem = CreateProblemDto.toEntity(problemDto, user);
        return CreateProblemDto.from(problemRepository.save(problem));
    }

    public UpdateProblemDto updateProblem(UpdateProblemDto problemDto) {
        Problem updatedProblem = problemRepository
                .findById(problemDto.getProblemId()).orElse(null)
                .updateEntity(problemDto);
        return UpdateProblemDto.from(problemRepository.save(updatedProblem));
    }

    public void deleteProblem(Long problemId) {
        problemRepository.deleteById(problemId);
    }

    public GetProblemInfoDto getOneProblem(Long problemId) {
        return GetProblemInfoDto.from(problemRepository.findById(problemId).orElse(null));
    }

    public List<GetProblemDto> getProblemList(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElse(null);
        List<GetProblemDto> getProblemDtos = problemRepository.findAllByCreator(user).orElse(null);
        for (GetProblemDto pp : getProblemDtos) {
            System.out.println("pp = " + pp);
        }
        return getProblemDtos;
    }

}
