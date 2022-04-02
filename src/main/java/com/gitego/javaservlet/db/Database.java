package com.gitego.javaservlet.db;

import com.gitego.javaservlet.user.models.User;
import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter
public class Database {
    private static Map<String, User> users = new LinkedHashMap<>();

    public static void addUser(User user) {
        users.put(user.getEmail(), user);
    }

    public static User findUser(String email) {
        return users.get(email);
    }

    public static List<User> getUsers() {
        return (List<User>) users.values();
    }
}

