package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item itemA = new Item("itemA", 10000, 10);
        //when
        Item saveItem = itemRepository.save(itemA);
        //then
        Item findItem = itemRepository.findById(itemA.getId());
        assertThat(saveItem).isEqualTo(findItem);
    }

    @Test
    void findAll() {
        //given
        Item itemA = new Item("itemA", 10000, 10);
        Item itemB = new Item("itemB", 20000, 50);
        itemRepository.save(itemA);
        itemRepository.save(itemB);

        //when
        List<Item> itemList = itemRepository.findAll();

        //then
        assertThat(itemList.size()).isEqualTo(2);
        assertThat(itemList).containsExactly(itemA, itemB);
    }

    @Test
    void updateItem() {
        //given
        Item itemA = new Item("itemA", 10000, 10);
        Item save = itemRepository.save(itemA);
        Long itemId = save.getId();

        //when
        Item itemB = new Item("itemB", 23000, 20);
        itemRepository.update(itemA.getId(),itemB);

        //then
        Item findItem = itemRepository.findById(itemId);
        assertThat(findItem.getItemName()).isEqualTo(itemB.getItemName());
    }

}