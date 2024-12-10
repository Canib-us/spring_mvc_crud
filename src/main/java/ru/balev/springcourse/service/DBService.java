package ru.balev.springcourse.service;

import ru.balev.springcourse.model.User;

import java.util.List;

public interface DBService {
    List<User> getUsers();
    void addUser(User user);
    User getUser(Long id);
    void deleteUser(Long id);
    void updateUser(User user);
}
