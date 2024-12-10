package ru.balev.springcourse.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.balev.springcourse.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

@Repository
public class DaoImpl implements Dao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> getUsers() {
        return em.createQuery("FROM User", User.class).getResultList();
    }

    @Transactional
    @Override
    public void addUser(User user) {
        em.persist(user);
    }

    @Override
    public User getUser(Long id) {
        return em.find(User.class, id);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        User user = em.find(User.class, id);
        if (user != null) {
            em.remove(user);
        }
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        em.merge(user);
    }
}
