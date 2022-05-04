package bong.lines.sample.entity;

import bong.lines.sample.code.OrderStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "ORDER")
public class Orders {

    @Id
    @Column(name = "ORDER_ID")
    private long id;

    @Column(name = "DATE")
    private LocalDate date;

    private OrderStatus orderStatus;
}
