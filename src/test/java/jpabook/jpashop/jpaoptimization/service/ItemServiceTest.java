package jpabook.jpashop.jpaoptimization.service;

import jpabook.jpashop.jpaoptimization.domain.Item.Item;
import jpabook.jpashop.jpaoptimization.domain.Item.Movie;
import jpabook.jpashop.jpaoptimization.exception.NotEnoughStockException;
import jpabook.jpashop.jpaoptimization.repository.ItemRepository;
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
public class ItemServiceTest {

    @Autowired ItemService itemService;
    @Autowired ItemRepository itemRepository;

    @Test
    public void 영화_생성() {
        // given
        Movie movie = Movie.builder()
                .name("장고").actor("레오나르도 디카프리오")
                .director("쿠엔틴 타란티노").build();
        // when
        itemService.saveItem(movie);

        // then
        assertEquals(movie, itemService.findOne(movie.getId()));
    }

    @Test
    public void 상품_수정(){
        // given
        Movie movie = Movie.builder()
                .name("장고").actor("레오나르도 디카프리오")
                .director("쿠엔틴 타란티노").build();
        // when
        itemService.saveItem(movie);
        movie.addStock(10);
        itemService.saveItem(movie);
        Item selectMovie = itemService.findOne(movie.getId());

        // then
        assertEquals(selectMovie.getStockQuantity(), 10);
    }

    @Test(expected = NotEnoughStockException.class)
    public void 재고_음수_오류(){
        // given
        Movie movie = Movie.builder()
                .name("장고").actor("레오나르도 디카프리오")
                .director("쿠엔틴 타란티노").build();

        // when
        movie.removeStock(10);

        // then
        fail("예외가 발생해야 한다");
    }
}