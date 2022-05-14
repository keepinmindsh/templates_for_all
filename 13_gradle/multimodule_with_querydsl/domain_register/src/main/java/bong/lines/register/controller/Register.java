package bong.lines.register.controller;

import bong.lines.entity.Team;
import bong.lines.register.domain.RegisterCommand;
import bong.lines.register.dto.TeamDto;
import bong.lines.register.repository.TeamRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class Register {

    private final TeamRepository teamRepository;
    private final RegisterCommand registerCommand;

    public Register(TeamRepository teamRepository, RegisterCommand registerCommand) {
        this.teamRepository = teamRepository;
        this.registerCommand = registerCommand;
    }

    @GetMapping("/teams")
    public List<TeamDto> getTeams(){
        registerCommand.regist();

        return teamRepository.getTeams(1L);
    }
}
