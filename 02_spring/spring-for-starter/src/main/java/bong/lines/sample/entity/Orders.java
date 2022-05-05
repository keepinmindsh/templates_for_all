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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

    // 연관 관계 편의 메서드
    public void setMember(Member member){
        this.member = member;
        this.member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrders(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrders(this);
    }
}
