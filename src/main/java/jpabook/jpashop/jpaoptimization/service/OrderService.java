package jpabook.jpashop.jpaoptimization.service;

import jpabook.jpashop.jpaoptimization.domain.*;
import jpabook.jpashop.jpaoptimization.domain.Item.Item;
import jpabook.jpashop.jpaoptimization.repository.ItemRepository;
import jpabook.jpashop.jpaoptimization.repository.MemberRepository;
import jpabook.jpashop.jpaoptimization.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    public Long order(Long memberId, Long itemId, int count){
        Address address2 = Address.builder()
                .city("Nowon").street("rojingst").zipcode("B123").build();
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        Delivery delivery = Delivery.builder().address(member.getAddress()).build();
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
        Order order = Order.createOrder(member, delivery, orderItem);

        orderRepository.save(order);
        return order.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId){
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }

    public List<Order> findOrders(){
        
        return null;
    }
}
