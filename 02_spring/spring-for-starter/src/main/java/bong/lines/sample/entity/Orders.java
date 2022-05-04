package bong.lines.sample.entity;

import bong.lines.sample.code.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter @Setter
@Table(name = "ORDER")
public class Orders {

    @Id
    @Column(name = "ORDER_ID")
    private long id;

    @Column(name = "DATE")
    private LocalDate date;

    private OrderStatus orderStatus;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orders")
    private List<OrderItem> list = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;
}
