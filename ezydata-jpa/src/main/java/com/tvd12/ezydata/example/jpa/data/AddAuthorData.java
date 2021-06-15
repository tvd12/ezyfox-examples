package com.tvd12.ezydata.example.jpa.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddAuthorData {
    private final String authorName;
}
