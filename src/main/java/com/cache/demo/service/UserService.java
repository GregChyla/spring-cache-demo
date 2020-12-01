package com.cache.demo.service;

import com.cache.demo.exception.UserNotFoundException;
import com.cache.demo.model.User;
import com.cache.demo.model.UserModifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@CacheConfig(cacheNames = {"users"})
@Slf4j
public class UserService {

    private final List<User> userList = new ArrayList<>();

    @PostConstruct
    private void fillUsers() {
        userList.add(User.builder().id(UUID.randomUUID()).age(10).name("Tom").build());
        userList.add(User.builder().id(UUID.randomUUID()).age(23).name("Ana").build());
        userList.add(User.builder().id(UUID.randomUUID()).age(45).name("Greg").build());
        userList.add(User.builder().id(UUID.randomUUID()).age(27).name("Lisa").build());

    }

    @Cacheable
    public List<User> findAllUsers() {
        delayResponse();
        return Collections.unmodifiableList(userList);
    }

    private void delayResponse() {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
    }

    @CachePut
    public User addUser(UserModifier userModifier) {
                var user = User.builder()
                        .id(UUID.randomUUID())
                        .age(userModifier.getAge())
                        .name(userModifier.getName())
                        .build();
        userList.add(user);
        return user;
    }

    @CachePut
    public boolean removeUserById(UUID id) {
        try {
            return userList.remove(findUserById(id).get());
        } catch (NoSuchElementException exception) {
            throw new UserNotFoundException(String.format("User with id %s not found", id), exception.getCause());

        }
    }

    public Optional<User> findUserById(UUID id) {
        return userList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

}
