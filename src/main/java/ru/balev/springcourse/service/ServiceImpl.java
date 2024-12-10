package ru.balev.springcourse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.balev.springcourse.dao.Dao;
import ru.balev.springcourse.model.User;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.List;

@Transactional
@Service
public class ServiceImpl implements DBService{

    @Autowired
    private final Dao dao;

    public ServiceImpl(Dao dao) {
        this.dao = dao;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getUsers() {
        return dao.getUsers();
    }

    @Transactional
    @Override
    public void addUser(User user) {
        dao.addUser(user);
    }

    @Transactional
    @Override
    public User getUser(Long id) {
        return dao.getUser(id);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        dao.deleteUser(id);
    }

    @Override
    public void updateUser(User user) {
        dao.updateUser(user);
    }
}
