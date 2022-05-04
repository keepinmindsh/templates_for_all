package bong.lines.sample.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.InheritanceType.JOINED;

@Getter
@Setter
@Entity
@Table(name = "ITEM")
@Inheritance(strategy = JOINED)
public abstract class Item {

    @Id
    @Column(name = "ITEM_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ITEM_NAME")
    private String name;

    @Column(name = "price")
    private int price;

    @Column(name = "STOCK_QUANTITY")
    private int stockQuantity;
}
