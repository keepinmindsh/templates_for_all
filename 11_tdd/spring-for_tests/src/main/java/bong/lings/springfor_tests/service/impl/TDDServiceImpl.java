package bong.lings.springfor_tests.service.impl;

import bong.lings.springfor_tests.model.Member;
import bong.lings.springfor_tests.repository.MemberRepository;
import bong.lings.springfor_tests.service.TDDService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TDDServiceImpl implements TDDService {

    private final MemberRepository memberRepository;

    @Override
    public List<Member> member() {
        return memberRepository.findAll();
    }

    @Override
    public void memberSave(String name) {

        Member member = new Member();
        member.setName(name);

        memberRepository.save(member);
    }
}
