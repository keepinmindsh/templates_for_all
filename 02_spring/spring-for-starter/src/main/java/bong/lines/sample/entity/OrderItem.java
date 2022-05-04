package bong.lines.sample.entity;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ORDER_PRICE")
    private Long orderPrice;

    @Column(name = "ORDER_COUNT")
    private int count;
}
