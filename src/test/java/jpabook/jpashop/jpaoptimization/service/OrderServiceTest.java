package jpabook.jpashop.jpaoptimization.service;

import jpabook.jpashop.jpaoptimization.domain.Address;
import jpabook.jpashop.jpaoptimization.domain.Item.Book;
import jpabook.jpashop.jpaoptimization.domain.Member;
import jpabook.jpashop.jpaoptimization.domain.Order;
import jpabook.jpashop.jpaoptimization.domain.OrderStatus;
import jpabook.jpashop.jpaoptimization.exception.NotEnoughStockException;
import jpabook.jpashop.jpaoptimization.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired MemberService memberService;
    @Autowired ItemService itemService;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception {
        // given
        Member member = createMember();
        Book book = createBook(10, 10000);

        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        // when
        Order getOrder = orderRepository.findOne(orderId);

        // then
        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.getStatus());
        assertEquals("주문한 상품 종류 수가 정확해야 한다.", 1, getOrder.getOrderItems().size());
        assertEquals("주문 가격은 가격 * 수량이다.", 10000*orderCount, getOrder.getTotalPrice());
        assertEquals("주문 수량만큼 재고가 줄어야 한다.", 8, book.getStockQuantity());
    }

    @Test
    public void 주문취소() throws Exception {
        // given
        Member member = createMember();
        Book book = createBook(10, 10000);
        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        // when
        orderService.cancelOrder(orderId);

        // then
        Order order = orderRepository.findOne(orderId);
        assertEquals("주문 취소 시 상태는 CANCEL이다.", OrderStatus.CANCEL, order.getStatus());
        assertEquals("주문이 취소된 상픔은 그만큼 재고가 있어야 한다.", 10, book.getStockQuantity());
    }

    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량_초과() throws Exception {
        // given
        Member member = createMember();
        Book book = createBook(11, 20000);
        int orderCount = 20;

        // when
        orderService.order(member.getId(), book.getId(), orderCount);

        // then
        fail("재고 수량 부족 예외가 발생해야 한다.");
    }

    private Book createBook(int quantity, int amount) {
        Book book = Book.builder().author("작가1").isbn("A123")
                .name("책 1").build();
        book.addStock(quantity);
        book.changePrice(amount);
        itemService.saveItem(book);
        return book;
    }

    private Member createMember() {
        Address address = Address.builder().city("서울").street("강가")
                .zipcode("123-123").build();
        Member member = Member.builder().name("회원 1")
                .address(address).build();
        memberService.join(member);
        return member;
    }
}