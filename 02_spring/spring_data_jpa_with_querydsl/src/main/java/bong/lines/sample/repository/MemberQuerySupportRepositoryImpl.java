package bong.lines.sample.repository;

import bong.lines.sample.model.dto.MemberSearchCondition;
import bong.lines.sample.model.dto.MemberTeamDto;
import bong.lines.sample.model.dto.QMemberTeamDto;
import bong.lines.sample.model.entity.Member;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

import static bong.lines.sample.model.entity.QMember.member;
import static bong.lines.sample.model.entity.QTeam.team;
import static org.springframework.util.StringUtils.hasText;

// Impl은 반드시 붙여서 만들어줘야하는 규칙임
public class MemberQuerySupportRepositoryImpl extends QuerydslRepositorySupport implements MemberRepositoryCustom{

    public MemberQuerySupportRepositoryImpl() {
        super(Member.class);
    }

    @Override
    public List<MemberTeamDto> search(MemberSearchCondition condition) {
        return from(member)
                .leftJoin(member.team, team)
                .where(
                        userNameEq(condition.getUsername()),
                        teamNameEq(condition.getTeamName()),
                        ageGoe(condition.getAgeGoe()),
                        ageLoe(condition.getAgeLoe())
                )
                .select(new QMemberTeamDto(
                        member.id.as("memberId"),
                        member.username,
                        member.age,
                        team.id.as("teamId"),
                        team.name.as("teamName")))
                .fetch();
    }

    private BooleanExpression ageLoe(Integer ageLoe) {
        return ageLoe != null ? member.age.loe(ageLoe) : null;
    }

    private BooleanExpression ageGoe(Integer ageGoe) {
        return ageGoe != null ? member.age.goe(ageGoe) : null;
    }

    private BooleanExpression teamNameEq(String teamName) {
        return hasText(teamName) ? team.name.eq(teamName) : null;
    }

    private BooleanExpression userNameEq(String username) {
        return hasText(username) ? member.username.eq(username) : null;
    }

    @Override
    public Page<MemberTeamDto> searchPageSimple(MemberSearchCondition condition, Pageable pageable) {

        JPQLQuery<MemberTeamDto> select = from(member)
                .leftJoin(member.team, team)
                .where(
                        userNameEq(condition.getUsername()),
                        teamNameEq(condition.getTeamName()),
                        ageGoe(condition.getAgeGoe()),
                        ageLoe(condition.getAgeLoe())
                )
                .select(new QMemberTeamDto(
                        member.id.as("memberId"),
                        member.username,
                        member.age,
                        team.id.as("teamId"),
                        team.name.as("teamName")));


        JPQLQuery<MemberTeamDto> memberTeamDtoJPQLQuery = Objects.requireNonNull(getQuerydsl()).applyPagination(pageable, select);

        List<MemberTeamDto> fetch = memberTeamDtoJPQLQuery.fetch();

        return new PageImpl<>(fetch, pageable, fetch.size());
    }

    @Override
    public Page<MemberTeamDto> searchPageSimpleSeparateCount(MemberSearchCondition condition, Pageable pageable) {
        return null;
    }

    @Override
    public Page<MemberTeamDto> searchPageComplex(MemberSearchCondition condition, Pageable pageable) {
        return null;
    }

}
