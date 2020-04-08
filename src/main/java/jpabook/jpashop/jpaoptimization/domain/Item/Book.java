package jpabook.jpashop.jpaoptimization.domain.Item;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Getter
public class Book extends Item {

    private String author;
    private String isbn;

    @Builder
    public Book(String name, int price, int stockQuantity, String author, String isbn){
        super(name, price, stockQuantity);
        this.author = author;
        this.isbn = isbn;
    }
}
