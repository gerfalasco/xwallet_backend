package com.xwallet.xwallet.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CredentialsDTO {
    private String email;
    private String password;
}