package com.gitego.javaservlet.user.models;

import com.gitego.javaservlet.db.Database;
import com.gitego.javaservlet.user.dtos.ApiResponse;

import javax.naming.AuthenticationException;
import java.util.regex.Pattern;

public class Admin extends User {
    @Override
    public ApiResponse<User> register() throws Exception {
        if (!Pattern.matches("^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{10,}$", this.getPassword())) {
            throw new Exception("Password rules not met");
        }

        this.encryptPwd();
        Database.addUser(this);
        return new ApiResponse<>("Admin Signup successful", Database.findUser(this.getEmail()));
    }

    @Override
    public ApiResponse<String> login(String email, String password) throws AuthenticationException {
        User foundUser = Database.findUser(email);
        if (foundUser == null) throw new AuthenticationException("Invalid credentials");
        if (!password.equals(foundUser.decryptPwd())) throw new AuthenticationException("Invalid credentials");
        return new ApiResponse<>("Login Successful", foundUser.getId());
    }
}
