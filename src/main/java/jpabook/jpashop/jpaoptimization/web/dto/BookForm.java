package jpabook.jpashop.jpaoptimization.web.dto;

import jpabook.jpashop.jpaoptimization.domain.Item.Book;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class BookForm {
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
    private String author;
    private String isbn;

    @Builder
    public BookForm(String name, int price, int stockQuantity, String author, String isbn){
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.author = author;
        this.isbn = isbn;
    }

    public void loadId(Long id){
        this.id = id;
    }

    public Book toEntity(){
        Book book = Book.builder()
                .name(name).author(author).isbn(isbn).build();
        book.changePrice(price);
        book.addStock(stockQuantity);
        return book;
    }
}