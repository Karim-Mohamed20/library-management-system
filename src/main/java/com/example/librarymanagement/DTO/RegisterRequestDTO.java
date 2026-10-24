package com.example.librarymanagement.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterRequestDTO {

    private String username;
    private String email;
    private String password;
}
