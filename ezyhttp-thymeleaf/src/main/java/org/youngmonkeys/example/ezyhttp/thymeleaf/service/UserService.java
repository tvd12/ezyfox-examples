package org.youngmonkeys.example.ezyhttp.thymeleaf.service;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import org.youngmonkeys.example.ezyhttp.thymeleaf.entity.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@EzySingleton
public class UserService {
    private final Map<String, User> users = new ConcurrentHashMap<>();

    public User getUser(String username) {
        return users.get(username);
    }

    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }
}
