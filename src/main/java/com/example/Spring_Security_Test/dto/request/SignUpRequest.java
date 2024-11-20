package com.example.Spring_Security_Test.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpRequest {
    private String name;
    private String surname;
    private String email;
    private String password;
}
