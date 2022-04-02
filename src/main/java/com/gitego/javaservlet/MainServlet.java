package com.gitego.javaservlet;

import com.gitego.javaservlet.user.dtos.ApiResponse;
import com.gitego.javaservlet.utils.ResponseEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseEntity.send(resp, new ApiResponse<>("Hey There!", null), HttpServletResponse.SC_OK);
    }
}
