package sample.service.impl;


import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sample.model.entity.Item;
import sample.model.entity.QItem;
import sample.service.ItemService;

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
