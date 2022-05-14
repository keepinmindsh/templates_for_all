package bong.lines.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Member {

    @Id
    @Column(name = "MEMBER_ID")
    private Long id;
}
