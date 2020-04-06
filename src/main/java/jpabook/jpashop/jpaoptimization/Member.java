package jpabook.jpashop.jpaoptimization;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@Entity
@Getter
public class Member {

    @Id @GeneratedValue
    private Long id;
    private String username;

    @Builder
    public Member(String username){
        this.username = username;
    }
}
