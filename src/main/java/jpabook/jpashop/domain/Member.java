package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    // 내장 타입
    @Embedded
    private Address address;

    // 1대다
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
