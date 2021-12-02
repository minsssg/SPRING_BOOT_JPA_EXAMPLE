package jpabook.jpashop.domain.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        // 저장하기 전까지 item값이 없기 때문에 null
        if (item.getId() == null) {
            em.persist(item);
        } else {
            em.merge(item); // update와 비슷하다.
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    // 여러건을 찾을때는 JPQL을 작성해야 한다.
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
