package jpabook.jpashop.domain.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        Item findItem = itemRepository.findOne(itemId); // 영속상태!
        // setter 없이 엔티티내에서 변경할 수 있게 수정해야 한다.
        /*findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);*/
        log.info("findItem = {}", findItem);
        findItem.changeItemInfo(name, price, stockQuantity);

        // @Transactional 에 의해 commit이 실행됨.
        // => flush가 일어나고 더티체킹을 해서 값이 변경되었는지 찾아서 업데이트함.
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
