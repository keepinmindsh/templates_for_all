package bong.lines.domain.jpa.controller;

import bong.lines.domain.jpa.model.dto.MemberSearchCondition;
import bong.lines.domain.jpa.model.dto.MemberTeamDto;
import bong.lines.domain.jpa.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class JPAController {

    private final MemberJpaRepository memberJpaRepository;

    @GetMapping("/v1/members")
    public List<MemberTeamDto> searchMemberv1(MemberSearchCondition condition){
        return memberJpaRepository.searchWithWhere(condition);
    }
}
