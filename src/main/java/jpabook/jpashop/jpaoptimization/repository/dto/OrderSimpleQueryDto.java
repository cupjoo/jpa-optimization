package jpabook.jpashop.jpaoptimization.repository.dto;

import jpabook.jpashop.jpaoptimization.domain.Address;
import jpabook.jpashop.jpaoptimization.domain.Order;
import jpabook.jpashop.jpaoptimization.domain.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderSimpleQueryDto {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    public OrderSimpleQueryDto(Long orderId, String name, LocalDateTime
            orderDate, OrderStatus orderStatus, Address address) {
        this.orderId = orderId;
        this.name = name;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;
    }
}
