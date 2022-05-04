package bong.lines.sample.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Getter @Setter
public class Member {

    @Id
    @Column(name = "MEMBER_ID")
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @Embedded
    private Address address;

    @Column(name = "MEMBER_NAME")
    private String name;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Orders> ordersList = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;
}
