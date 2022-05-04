package bong.lines.sample.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity @Getter @Setter
@Table(name = "CATEGORY_ITEM")
public class CategoryItem {

    @Id
    @Column(name = "CATEGORY_ITEM_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CATEGORY_ID")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;
}
