package bong.lines.sample.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String username;
}
