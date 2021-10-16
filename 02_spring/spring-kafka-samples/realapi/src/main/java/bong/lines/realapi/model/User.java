package bong.lines.realapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TB_ZZ_USER")
public class User {
    @Id
    @Column(name="USER_ID")
    private String userId;
}
