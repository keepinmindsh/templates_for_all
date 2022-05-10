package bong.lines.sample.repository;

import bong.lines.sample.model.dto.MemberSearchCondition;
import bong.lines.sample.model.dto.MemberTeamDto;
import java.util.List;

public interface MemberRepositoryCustom {

    List<MemberTeamDto> search(MemberSearchCondition condition);
}
