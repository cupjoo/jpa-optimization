package jpabook.jpashop.jpaoptimization.domain.Item;

import lombok.Getter;

import javax.persistence.Entity;

@Entity
@Getter
public class Movie extends Item {

    private String director;
    private String actor;
}
