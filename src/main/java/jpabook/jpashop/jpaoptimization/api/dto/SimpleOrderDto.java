package jpabook.jpashop.jpaoptimization.api.dto;

import jpabook.jpashop.jpaoptimization.domain.Address;
import jpabook.jpashop.jpaoptimization.domain.Order;
import jpabook.jpashop.jpaoptimization.domain.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SimpleOrderDto {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    public SimpleOrderDto(Order order) {
        orderId = order.getId();
        name = order.getMember().getName();
        orderDate = order.getOrderDate();
        orderStatus = order.getStatus();
        address = order.getDelivery().getAddress();
    }
}
