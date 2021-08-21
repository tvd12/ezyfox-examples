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
public class BaseEntity {
    // create time
    private LocalDateTime createTime = LocalDateTime.now();
    // update time
    private LocalDateTime updateTime = LocalDateTime.now();
    // delete flg
    private Boolean deleted;
}
