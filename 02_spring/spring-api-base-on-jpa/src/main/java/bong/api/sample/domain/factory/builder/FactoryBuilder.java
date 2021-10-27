package bong.api.sample.domain.factory.builder;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.EntityManager;

@Getter
@Builder
public class FactoryBuilder {
    private final Object requestParam;
    private final EntityManager entityManager;
}
