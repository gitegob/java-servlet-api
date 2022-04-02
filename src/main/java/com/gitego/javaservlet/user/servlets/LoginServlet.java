package com.gitego.javaservlet.user.servlets;

import com.gitego.javaservlet.user.dtos.ApiResponse;
import com.gitego.javaservlet.user.models.Guest;
import com.gitego.javaservlet.utils.JsonUtil;
import com.gitego.javaservlet.utils.ResponseEntity;

import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Guest guest = new JsonUtil().parseBodyJson(req, Guest.class);
        try {
            ApiResponse<String> result = guest.login(guest.getEmail(), guest.getPassword());
            ResponseEntity.send(resp, result, HttpServletResponse.SC_OK);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            ResponseEntity.send(resp, new ApiResponse<>(e.getMessage(), null), HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
