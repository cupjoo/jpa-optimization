package jpabook.jpashop.jpaoptimization.domain.Item;

import jpabook.jpashop.jpaoptimization.domain.Category;
import jpabook.jpashop.jpaoptimization.exception.NotEnoughPriceException;
import jpabook.jpashop.jpaoptimization.exception.NotEnoughStockException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    public Item(String name){
        this.name = name;
    }

    // 비즈니스 로직
    public void changeName(String name){
        this.name = name;
    }

    public void changePrice(int price){
        if(price < 0){
            throw new NotEnoughPriceException("can't decrease price");
        }
        this.price = price;
    }

    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        if(restStock < 0){
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
