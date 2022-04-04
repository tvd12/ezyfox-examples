package com.tvd12.user_management.test;

import com.tvd12.ezyhttp.client.request.PostRequest;
import com.tvd12.ezyhttp.client.request.Request;
import com.tvd12.ezyhttp.client.request.RequestEntity;
import com.tvd12.ezyhttp.core.constant.StatusCodes;
import com.tvd12.user_management.entity.User;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;

public class ApiAddUserTest {
    public static final String API_URL = "http://localhost:8080/api/v1/users/";

    public static void main(String[] args) {
        HttpClient httpClient = HttpClient.newBuilder().build();
        User body = new User();
        body.setUsername("tvd12");
        body.setPassword("123456");
        RequestEntity requestEntity = RequestEntity.body(body);
        Request request =  new PostRequest()
                .setURL(API_URL + "add")
                .setEntity(requestEntity)
                .setResponseType(Boolean.class)
                .setResponseType(StatusCodes.CONFLICT,String.class);
        Boolean response = httpClient.send(request, HttpResponse.getBo<Boolean.class>);


    }
}
