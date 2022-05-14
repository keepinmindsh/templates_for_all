package bong.lines.register.repository;

import bong.lines.entity.Team;
import bong.lines.register.dto.TeamDto;

import java.util.List;

public interface TeamRepositoryCustom {

    List<TeamDto> getTeams(Long Id);
}
