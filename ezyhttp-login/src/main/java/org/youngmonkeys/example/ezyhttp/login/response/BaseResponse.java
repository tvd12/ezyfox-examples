package org.youngmonkeys.example.ezyhttp.login.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
// Base response for api
public class BaseResponse {
    // 200: success, other: failure
    private int Status;
    // Error code
    private int ErrorCode;
    // Error reason
    private String ErrorReason;
    // Time of response
    private LocalDateTime ResponseTime;
}
