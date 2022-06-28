package com.example.springboottask.repository;

import com.example.springboottask.entity.UserEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository("jpa")
@Transactional
public class JpaUserRepo implements UserRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UserEntity> findAll() {
        TypedQuery<UserEntity> query = entityManager.createQuery(
                "select u from UserEntity u", UserEntity.class);

        return query.getResultList();
    }

    @Override
    public void save(UserEntity user) {
        if (user.getId() == null) {
            entityManager.persist(user);
        }
        else {
            entityManager.merge(user);
        }
    }

    @Override
    public UserEntity findById(Long userId) {
        TypedQuery<UserEntity> query = entityManager.createQuery(
                "select u from UserEntity u where u.id = :userId", UserEntity.class);
        query.setParameter("userId", userId);

        return query.getSingleResult();
    }

    @Override
    public void deleteById(Long userId) {
        UserEntity user = findById(userId);
        entityManager.remove(user);
    }
}
