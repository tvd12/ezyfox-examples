package org.youngmonkeys.example.ezyhttp.website.user_management.test;

import com.tvd12.ezyhttp.client.HttpClient;
import com.tvd12.ezyhttp.client.request.PostRequest;
import com.tvd12.ezyhttp.client.request.Request;
import com.tvd12.ezyhttp.client.request.RequestEntity;
import com.tvd12.ezyhttp.core.constant.StatusCodes;
import org.youngmonkeys.example.ezyhttp.website.user_management.entity.User;


public class ApiAddUserTest {
    public static final String API_URL = "http://localhost:8080/api/v1/users/";

    public static void main(String[] args) throws Exception {
        HttpClient httpClient = HttpClient.builder().build();
        User body = new User();
        body.setUsername("tvd12");
        body.setPassword("123456");
        RequestEntity requestEntity = RequestEntity.body(body);
        Request request =  new PostRequest()
                .setURL(API_URL + "add")
                .setEntity(requestEntity)
                .setResponseType(Boolean.class)
                .setResponseType(StatusCodes.CONFLICT,String.class);
        Boolean response = httpClient.call(request);
        System.out.println("add user reponse: " + response);

    }
}
