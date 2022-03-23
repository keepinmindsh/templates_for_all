package bong.lines.sample.service.impl;

import bong.lines.sample.model.entity.Item;
import bong.lines.sample.model.entity.QItem;
import bong.lines.sample.service.ItemService;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final EntityManager entityManager;

    @Override
    public Object getHelloWorld() {

        JPAQuery<Item> query = new JPAQuery<>(entityManager);

        QItem qItem = QItem.item;

        List<Item> itemList = query.from(qItem)
                .where(qItem.id.eq(1L))
                .fetch();

        return itemList;
    }
}
