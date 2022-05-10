package bong.lines.sample.controller;

import bong.lines.sample.model.dto.MemberSearchCondition;
import bong.lines.sample.model.dto.MemberTeamDto;
import bong.lines.sample.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberJpaRepository memberJpaRepository;

    @GetMapping("/v1/members")
    public List<MemberTeamDto> searchMemberv1(MemberSearchCondition condition){
        return memberJpaRepository.searchWithWhere(condition);
    }
}
