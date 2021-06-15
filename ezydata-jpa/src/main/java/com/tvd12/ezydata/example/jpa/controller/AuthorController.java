package com.tvd12.ezydata.example.jpa.controller;

import com.tvd12.ezydata.example.jpa.converter.DataToResponseConverter;
import com.tvd12.ezydata.example.jpa.converter.RequestToDataConverter;
import com.tvd12.ezydata.example.jpa.data.AddAuthorData;
import com.tvd12.ezydata.example.jpa.data.AuthorData;
import com.tvd12.ezydata.example.jpa.request.AddAuthorRequest;
import com.tvd12.ezydata.example.jpa.response.AuthorResponse;
import com.tvd12.ezydata.example.jpa.service.AuthorService;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoPost;
import com.tvd12.ezyhttp.server.core.annotation.RequestBody;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller("/api/v1/author")
public class AuthorController {
	private final AuthorService authorService;
	private final RequestToDataConverter requestToDataConverter;
	private final DataToResponseConverter dataToResponseConverter;

    @DoPost("/add")
    public AuthorResponse addAuthor(@RequestBody AddAuthorRequest request) {
        final AddAuthorData addAuthorData = requestToDataConverter.toData(request);
        final AuthorData authorData = authorService.saveAuthor(addAuthorData);
        return dataToResponseConverter.toResponse(authorData);
    }

}