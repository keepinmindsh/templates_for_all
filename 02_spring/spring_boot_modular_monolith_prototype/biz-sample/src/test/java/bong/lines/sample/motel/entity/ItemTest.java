package bong.lines.sample.motel.entity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import sample.model.entity.Item;
import sample.model.entity.QItem;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

// TODO 테스트 코드가 정상적으로 동작하지 않음 - 수정필요 !!!
@DataJpaTest
class ItemTest {

    @PersistenceContext
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
        Item found = queryFactory.selectFrom(qItem).fetchOne(); // (3)

        // then
        assertEquals(found, item); // (4)
    }
}
