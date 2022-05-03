package bong.lines.sample.item.test;

import bong.lines.sample.model.entity.Item;
import bong.lines.sample.model.entity.QItem;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@Commit
class ItemTest {

    @Autowired
    EntityManager entityManager;

    @DisplayName("Item Test 실행")
    @Test
    void test() {
        // given
        Item item = new Item();
        entityManager.persist(item);

        // when
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager); // (1)
        QItem qItem = new QItem("i"); // (2)

        QItem qItemWithOtherWay = QItem.item;  // (2) 의 다른 방식

        Item found = queryFactory.selectFrom(qItem).fetchOne(); // (3)

        // then
        assertEquals(found, item); // (4)
    }
}
