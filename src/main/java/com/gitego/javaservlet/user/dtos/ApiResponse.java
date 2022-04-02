package com.gitego.javaservlet.user.dtos;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ApiResponse<T> {
    String message;
    T payload;

}
