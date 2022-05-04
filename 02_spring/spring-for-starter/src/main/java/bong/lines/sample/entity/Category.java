package bong.lines.sample.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter @Setter
@Table(name = "CATEGORY")
public class Category {

    @Id
    @Column(name = "CATEGORY_ID")
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    @Column(name = "CATEGORY_NAME")
    private String name;

}
