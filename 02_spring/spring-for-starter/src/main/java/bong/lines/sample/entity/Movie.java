package bong.lines.sample.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Table;


@Getter
@Setter
@Entity
@Table(name = "MOVIE")
@DiscriminatorColumn(name = "MOVIE")
public class Movie extends Item{

    @Column(name = "DIRECTOR")
    private String director;

    @Column(name = "ACTOR")
    private String actor;
}
