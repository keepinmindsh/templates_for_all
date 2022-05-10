package bong.lines.sample.repository;

import bong.lines.sample.model.dto.MemberSearchCondition;
import bong.lines.sample.model.dto.MemberTeamDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberRepositoryCustom {

    List<MemberTeamDto> search(MemberSearchCondition condition);
    Page<MemberTeamDto> searchPageSimple(MemberSearchCondition condition, Pageable pageable);
    Page<MemberTeamDto> searchPageSimpleSeparateCount(MemberSearchCondition condition, Pageable pageable);
    Page<MemberTeamDto> searchPageComplex(MemberSearchCondition condition, Pageable pageable);
}
