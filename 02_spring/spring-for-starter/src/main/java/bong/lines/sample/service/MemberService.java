package bong.lines.sample.service;

import bong.lines.sample.entity.Member;
import bong.lines.sample.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> byName = memberRepository.findByName(member.getName());

        if (!byName.isEmpty()) {
            throw new IllegalStateException("이지 존재하는 회원입니다. ");
        }
    }
}
