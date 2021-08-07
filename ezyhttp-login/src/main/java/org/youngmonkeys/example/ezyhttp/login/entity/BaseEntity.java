package org.youngmonkeys.example.ezyhttp.login.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass

// Base of entity
public class BaseEntity {
    // create time
    private LocalDateTime createTime;
    // update time
    private LocalDateTime updateTime;
    // delete flg
    private Boolean deleted = false;
    // version
    private int version;
    // deleted date
    private LocalDateTime deleteDate;
}
