package jpabook.jpashop.jpaoptimization.service;

import jpabook.jpashop.jpaoptimization.domain.Item.Item;
import jpabook.jpashop.jpaoptimization.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price){
        Item findItem = itemRepository.findOne(itemId);
        findItem.changeName(name);
        findItem.changePrice(price);
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }
}
