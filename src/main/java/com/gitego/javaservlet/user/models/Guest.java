package com.gitego.javaservlet.user.models;

import com.gitego.javaservlet.db.Database;
import com.gitego.javaservlet.user.dtos.ApiResponse;
import com.gitego.javaservlet.user.dtos.Role;

import javax.naming.AuthenticationException;
import java.util.regex.Pattern;

public class Guest extends User {
    @Override
    public ApiResponse<User> register() throws Exception {
        if(Database.findUser(this.getEmail())!=null){
            throw new Exception("User already exists");
        }
        String regex = "";
        if(this.getRole()== Role.ADMIN) regex = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{10,}$";
        else regex = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{5,}$";
        if (!Pattern.matches(regex, this.getPassword())) {
            throw new Exception("Password rules not met");
        }

        this.encryptPwd();
        Database.addUser(this);
        return new ApiResponse<>("Signup successful", Database.findUser(this.getEmail()));
    }

    @Override
    public ApiResponse<String> login(String email, String password) throws AuthenticationException {
        User foundUser = Database.findUser(email);
        if (foundUser == null) throw new AuthenticationException("Invalid credentials");
        if (!password.equals(foundUser.decryptPwd())) throw new AuthenticationException("Invalid credentials");
        return new ApiResponse<>("Login Successful", foundUser.getEmail());
    }
}
