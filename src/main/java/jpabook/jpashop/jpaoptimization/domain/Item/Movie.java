package jpabook.jpashop.jpaoptimization.domain.Item;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Movie extends Item {

    private String director;
    private String actor;

    @Builder
    public Movie(String name, String director, String actor){
        super(name);
        this.director = director;
        this.actor = actor;
    }
}
