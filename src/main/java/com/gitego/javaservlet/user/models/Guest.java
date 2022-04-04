package com.gitego.javaservlet.user.models;

import com.gitego.javaservlet.db.Database;
import com.gitego.javaservlet.utils.ApiResponse;
import lombok.AllArgsConstructor;

import javax.naming.AuthenticationException;
import java.util.regex.Pattern;

@AllArgsConstructor
public class Guest extends User {
    @Override
    public ApiResponse<User> register() throws Exception {
        if(Database.findUser(this.getEmail())!=null){
            throw new Exception("User already exists");
        }
        if(Database.getUsers().stream().filter(user -> user.getPhone().equals(this.getPhone())).findFirst().orElse(null)!=null){
            throw new Exception("User phone already exists");
        }
        String regex = "";
//        if(this.getRole()== Role.ADMIN) regex = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{10,}$";
        regex = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{5,}$";
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
