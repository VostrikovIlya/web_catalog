package com.semp.service;

import com.semp.entity.Role;
import com.semp.entity.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    Role saveRole(Role role);

    void addRoleToUser(String login, String roleName);

    User getUser(String login);

    List<User> getUsers();

}
