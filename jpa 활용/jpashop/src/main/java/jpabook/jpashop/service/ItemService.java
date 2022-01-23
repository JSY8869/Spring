package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    // 변경 감지 기능 사용
    @Transactional
    public void updateItem(long itemId, Book bookParam){
        Item findItem = itemRepository.findOne(itemId);
        findItem.setPrice(bookParam.getPrice());
        findItem.setStockQuantity(bookParam.getStockQuantity());
        findItem.setName(bookParam.getName());
        findItem.setCategories(bookParam.getCategories());
        findItem.setId(bookParam.getId());
    }

    @Transactional(readOnly = true)
    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }
}
