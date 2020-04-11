package jpabook.jpashop.jpaoptimization.api;

import jpabook.jpashop.jpaoptimization.api.dto.SimpleOrderDto;
import jpabook.jpashop.jpaoptimization.domain.Address;
import jpabook.jpashop.jpaoptimization.domain.Order;
import jpabook.jpashop.jpaoptimization.domain.OrderSearch;
import jpabook.jpashop.jpaoptimization.domain.OrderStatus;
import jpabook.jpashop.jpaoptimization.repository.OrderRepository;
import jpabook.jpashop.jpaoptimization.repository.OrderSimpleQueryDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;

    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> ordersV2() {
        return orderRepository.findAllByString(new OrderSearch()).stream()
                .map(SimpleOrderDto::new)
                .collect(toList());
    }

    @GetMapping("/api/v3/simple-orders")
    public List<SimpleOrderDto> ordersV3() {
        return orderRepository.findAllWithMemberDelivery().stream()
                .map(SimpleOrderDto::new)
                .collect(toList());
    }

    @GetMapping("/api/v4/simple-orders")
    public List<OrderSimpleQueryDto> ordersV4() {
        return orderRepository.findOrderDtos();
    }
}