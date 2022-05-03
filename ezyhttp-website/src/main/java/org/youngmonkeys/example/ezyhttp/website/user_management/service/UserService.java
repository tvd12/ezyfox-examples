package org.youngmonkeys.example.ezyhttp.website.user_management.service;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import org.youngmonkeys.example.ezyhttp.website.user_management.entity.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@EzySingleton
public class UserService {
    protected final Map<String, User> users = new ConcurrentHashMap<>();

    public User addUser(User user) {
        return users.put(user.getUsername(), user);
    }

    public User getUser(String username) {
        return users.get(username);
    }
}
