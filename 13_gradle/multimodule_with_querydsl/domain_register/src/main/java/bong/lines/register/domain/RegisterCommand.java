package bong.lines.register.domain;

import bong.lines.entity.Team;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
@Transactional
public class RegisterCommand {

    private final EntityManager entityManager;

    public RegisterCommand(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void regist(){
        Team team = new Team();

        entityManager.persist(team);

        entityManager.flush();
    }
}
