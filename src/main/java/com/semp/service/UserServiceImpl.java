package com.semp.service;

import com.semp.entity.Role;
import com.semp.entity.User;
import com.semp.repository.RoleRepository;
import com.semp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;


import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUsername(username);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UsernameNotFoundException("Not found user!");
        }
    }

    @Override
    public User saveUser(User user) {
        Optional<User> user1 = userRepo.findByUsername(user.getUsername());
        if (user1.isPresent()) {
            return null;
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setEnable(true);
            addRoleToUser(user.getUsername(), "ROLE_USER");
            return userRepo.save(user);
        }
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        Optional<User> user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        user.ifPresent(value -> value.addAuthorities(role));
    }

    @Override
    public User getUser(String username) {
        return userRepo.findByUsername(username).orElseThrow();
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }


}
