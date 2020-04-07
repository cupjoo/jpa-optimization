package jpabook.jpashop.jpaoptimization.domain.Item;

import lombok.Getter;

import javax.persistence.Entity;

@Entity
@Getter
public class Album extends Item {

    private String artist;
    private String etc;
}
