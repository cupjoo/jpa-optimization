package jpabook.jpashop.jpaoptimization.api;

import jpabook.jpashop.jpaoptimization.repository.OrderQueryRepository;
import jpabook.jpashop.jpaoptimization.repository.dto.OrderQueryDto;
import jpabook.jpashop.jpaoptimization.api.dto.OrderDto;
import jpabook.jpashop.jpaoptimization.api.dto.SimpleOrderDto;
import jpabook.jpashop.jpaoptimization.domain.Order;
import jpabook.jpashop.jpaoptimization.domain.OrderSearch;
import jpabook.jpashop.jpaoptimization.repository.OrderRepository;
import jpabook.jpashop.jpaoptimization.repository.dto.OrderSimpleQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;

    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> simpleOrdersV2() {
        return orderRepository.findAllByString(new OrderSearch()).stream()
                .map(SimpleOrderDto::new)
                .collect(toList());
    }

    @GetMapping("/api/v3/simple-orders")
    public List<SimpleOrderDto> simpleOrdersV3() {
        return orderRepository.findAllWithMemberDelivery().stream()
                .map(SimpleOrderDto::new)
                .collect(toList());
    }

    @GetMapping("/api/v4/simple-orders")
    public List<OrderSimpleQueryDto> simpleOrdersV4() {
        return orderRepository.findOrderDtos();
    }

    @GetMapping("/api/v2/orders")
    public List<OrderDto> ordersV2() {  // N+1 문제 발생
        return orderRepository.findAllByString(new OrderSearch()).stream()
                .map(OrderDto::new)
                .collect(toList());
    }

    @GetMapping("/api/v3/orders")
    public List<OrderDto> ordersV3() {  // 페이징 불가
        List<Order> orders = orderRepository.findAllWithItem();
        return orders.stream()
                .map(OrderDto::new)
                .collect(toList());
    }

    @GetMapping("/api/v3.1/orders")
    public List<OrderDto> ordersV3_page(
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "limit", defaultValue = "100") int limit) {

        List<Order> orders = orderRepository.findAllWithMemberDelivery(offset, limit);
        return orders.stream()
                .map(OrderDto::new)
                .collect(toList());
    }

    @GetMapping("/api/v4/orders")
    public List<OrderQueryDto> ordersV4() {
        return orderQueryRepository.findOrderQueryDtos();
    }
}