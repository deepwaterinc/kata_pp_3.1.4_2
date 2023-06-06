package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {

    void saveUser(User user);

    User showById(Long id);

    void updateUserById(Long id, User user);

    void removeUserById(Long id);

    List<User> getAllUsers();

    User findByUsername(String username);

}
