package bong.lines.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Team {

    @Column(name = "TEAM_ID")
    private Long id;
}
