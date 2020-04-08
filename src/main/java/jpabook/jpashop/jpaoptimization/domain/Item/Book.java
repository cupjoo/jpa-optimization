package jpabook.jpashop.jpaoptimization.domain.Item;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Book extends Item {

    private String author;
    private String isbn;

    @Builder
    public Book(String name, String author, String isbn){
        super(name);
        this.author = author;
        this.isbn = isbn;
    }
}
