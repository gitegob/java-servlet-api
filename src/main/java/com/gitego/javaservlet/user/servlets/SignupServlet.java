package com.gitego.javaservlet.user.servlets;

import com.gitego.javaservlet.utils.ApiResponse;
import com.gitego.javaservlet.user.dtos.Role;
import com.gitego.javaservlet.user.models.Admin;
import com.gitego.javaservlet.user.models.Guest;
import com.gitego.javaservlet.user.models.User;
import com.gitego.javaservlet.utils.JsonUtil;
import com.gitego.javaservlet.utils.ResponseEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new JsonUtil().parseBodyJson(req, Guest.class);
        try {
            ApiResponse<User> result;
            if (user.getRole() == Role.ADMIN) {
                Admin admin = new Admin();
                admin.setFirstName(user.getFirstName());
                admin.setLastName(user.getLastName());
                admin.setAge(user.getAge());
                admin.setRole(user.getRole());
                admin.setCountry(user.getCountry());
                admin.setPhone(user.getPhone());
                admin.setEmail(user.getEmail());
                admin.setPassword(user.getPassword());
                admin.setGender(user.getGender());
                result = admin.register();
            } else {
                Guest guest = new Guest();
                guest.setFirstName(user.getFirstName());
                guest.setLastName(user.getLastName());
                guest.setAge(user.getAge());
                guest.setRole(user.getRole());
                guest.setCountry(user.getCountry());
                guest.setPhone(user.getPhone());
                guest.setEmail(user.getEmail());
                guest.setPassword(user.getPassword());
                guest.setGender(user.getGender());
                result = guest.register();
            }
            ResponseEntity.send(resp, result, HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseEntity.send(resp, new ApiResponse<>(e.getMessage(), null), HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
