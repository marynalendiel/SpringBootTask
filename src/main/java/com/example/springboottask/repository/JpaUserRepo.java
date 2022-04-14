package com.example.springboottask.repository;

import com.example.springboottask.entity.User;
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
    public List<User> findAll() {
        TypedQuery<User> query = entityManager.createQuery(
                "select u from User u", User.class);

        return query.getResultList();
    }

    @Override
    public void save(User user) {
        if (user.getId() == null) {
            entityManager.persist(user);
        }
        else {
            entityManager.merge(user);
        }
    }

    @Override
    public User findById(Long userId) {
        TypedQuery<User> query = entityManager.createQuery(
                "select u from User u where u.id = :userId", User.class);
        query.setParameter("userId", userId);

        return query.getSingleResult();
    }

    @Override
    public void deleteById(Long userId) {
        User user = findById(userId);
        entityManager.remove(user);
    }
}
