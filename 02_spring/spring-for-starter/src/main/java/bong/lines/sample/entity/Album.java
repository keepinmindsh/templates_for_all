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
@Table(name = "ALBUM")
@DiscriminatorColumn(name = "ALBUM")
public class Album extends Item{

    @Column(name = "ARTIST")
    private String artist;

    @Column(name = "ETC")
    private String etc;
}
