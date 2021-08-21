package org.youngmonkeys.example.ezyhttp.login.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_data")
@IdClass(UserDataId.class)
@EqualsAndHashCode(of = { "userId", "key" }, callSuper = false)
public class UserData extends BaseEntity {
    @Id
    private long userId;

    @Id
    @Column(name = "data_key")
    private String key;

    @Column(name = "data_value")
    private String value;
}
