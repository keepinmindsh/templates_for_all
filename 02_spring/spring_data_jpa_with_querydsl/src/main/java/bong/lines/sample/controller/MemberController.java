package bong.lines.sample.controller;

import bong.lines.sample.model.dto.MemberSearchCondition;
import bong.lines.sample.model.dto.MemberTeamDto;
import bong.lines.sample.repository.MemberJpaRepository;
import bong.lines.sample.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberJpaRepository memberJpaRepository;
    private final MemberRepository memberRepository;

    @GetMapping("/v1/members")
    public List<MemberTeamDto> searchMemberv1(MemberSearchCondition condition){
        return memberJpaRepository.searchWithWhere(condition);
    }

    @GetMapping("/v2/members")
    public Page<MemberTeamDto> searchMemberv2(MemberSearchCondition condition, Pageable pageable){
        return memberRepository.searchPageSimple(condition, pageable);
    }

    @GetMapping("/v3/members")
    public Page<MemberTeamDto> searchMemberv3(MemberSearchCondition condition, Pageable pageable){
        return memberRepository.searchPageComplex(condition, pageable);
    }
}
