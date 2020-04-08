package jpabook.jpashop.jpaoptimization.domain;

import jpabook.jpashop.jpaoptimization.domain.Item.Item;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;
    private int count;

    public static OrderItem createOrderItem(Item item, int orderPrice, int count){
        OrderItem orderItem = new OrderItem();
        orderItem.changeItem(item);
        orderItem.changeOrderPrice(orderPrice);
        orderItem.changeCount(count);

        item.removeStock(count);
        return orderItem;
    }

    public void changeItem(Item item){
        this.item = item;
    }
    public void changeOrder(Order order){
        this.order = order;
    }

    public void changeOrderPrice(int orderPrice){
        this.orderPrice = orderPrice;
    }

    public void changeCount(int count){
        this.count = count;
    }

    public void cancel(){
        item.addStock(count);
    }

    public int getTotalPrice(){
        return orderPrice * count;
    }
}
