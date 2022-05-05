package bong.lines.sample.entity;

import bong.lines.sample.code.DeliveryStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id
    @Column(name = "DELIVERY_ID")
    private long id;

    @Embedded
    private Address address;

    @Column(name = "DELIVERY_STATUS")
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Orders orders;
}
