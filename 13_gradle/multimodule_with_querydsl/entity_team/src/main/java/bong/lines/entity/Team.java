package bong.lines.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Team {

    @Id
    @Column(name = "TEAM_ID")
    private Long id;
}
