package jpabook.jpashop.jpaoptimization.domain.Item;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Getter
public class Movie extends Item {

    private String director;
    private String actor;

    @Builder
    public Movie(String name, int price, int stockQuantity, String director, String actor){
        super(name, price, stockQuantity);
        this.director = director;
        this.actor = actor;
    }
}
