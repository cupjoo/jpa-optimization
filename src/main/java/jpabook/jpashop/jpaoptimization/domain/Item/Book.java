package jpabook.jpashop.jpaoptimization.domain.Item;

import lombok.Getter;

import javax.persistence.Entity;

@Entity
@Getter
public class Book extends Item {

    private String author;
    private String isbn;
}
