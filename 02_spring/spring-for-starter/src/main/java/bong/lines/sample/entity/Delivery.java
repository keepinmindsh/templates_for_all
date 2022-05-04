package bong.lines.sample.entity;

import bong.lines.sample.code.DeliveryStatus;

import javax.persistence.*;

@Entity
public class Delivery {

    @Id
    @Column(name = "DELIVERY_ID")
    private long id;

    @Embedded
    private Address address;

    @Column(name = "DELIVERY_STATUS")
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;
}
