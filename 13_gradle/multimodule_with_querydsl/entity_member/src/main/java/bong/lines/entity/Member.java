package bong.lines.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Member {

    @Column(name = "MEMBER_ID")
    private Long id;
}
