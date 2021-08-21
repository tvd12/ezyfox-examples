package org.youngmonkeys.example.ezyhttp.login.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDataId implements Serializable {
    private long userId;
    private String key;
}
