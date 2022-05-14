package bong.lines.register.repository;

import bong.lines.register.dto.TeamDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static bong.lines.entity.QTeam.*;

public class TeamRepositoryImpl implements TeamRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public TeamRepositoryImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<TeamDto> getTeams(Long Id) {
        return jpaQueryFactory.select(Projections.fields(TeamDto.class, team.id.as("teamId"))).from(team).fetch();
    }
}
