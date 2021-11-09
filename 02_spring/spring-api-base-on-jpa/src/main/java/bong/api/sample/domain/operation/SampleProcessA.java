package bong.api.sample.domain.operation;

import bong.comm.operation.Command;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
public class SampleProcessA implements
<Void> {

    private final EntityManager entityManager;

    @Override
    public Void process() {

        return null;
    }
}
