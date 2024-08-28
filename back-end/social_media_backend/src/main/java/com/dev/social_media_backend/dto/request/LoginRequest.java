package com.dev.social_media_backend.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequest {
    private String email;
    private String password;
}
