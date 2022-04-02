package com.gitego.javaservlet.user.servlets;

import com.gitego.javaservlet.db.Database;
import com.gitego.javaservlet.user.dtos.ApiResponse;
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
        Guest guest = new JsonUtil().parseBodyJson(req, Guest.class);
        try {
            ApiResponse<User> result = guest.register();
            ResponseEntity.send(resp, result, HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseEntity.send(resp, new ApiResponse<>(e.getMessage(), null), HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseEntity.send(resp, new ApiResponse<>("Success", Database.getUsers()), HttpServletResponse.SC_OK);
    }
}
