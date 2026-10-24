package com.example.librarymanagement.Service;

import com.example.librarymanagement.DTO.LoginRequestDTO;
import com.example.librarymanagement.DTO.LoginResponseDTO;
import com.example.librarymanagement.DTO.RegisterRequestDTO;
import com.example.librarymanagement.Entity.User;
import com.example.librarymanagement.JWT.JwtService;
import com.example.librarymanagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public User registerNormalUser (RegisterRequestDTO registerRequestDTO) {

        if(userRepository.findByUserName(registerRequestDTO.getUsername()).isPresent()) {
            throw new RuntimeException("Username is already registered");
        }
        Set<String> roles = new HashSet<String>();
        roles.add("ROLE_USER");

        User user = new User();
        user.setUserName(registerRequestDTO.getUsername());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setRoles(roles);

        return userRepository.save(user);
    }

    public User registerAdminUser (@RequestBody RegisterRequestDTO registerRequestDTO) {
        if(userRepository.findByUserName(registerRequestDTO.getUsername()).isPresent()) {
            throw new RuntimeException("Username is already registered");
        }
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_ADMIN");
        roles.add("ROLE_USER");

        User user = new User();
        user.setUserName(registerRequestDTO.getUsername());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setRoles(roles);

        return userRepository.save(user);
    }

    public LoginResponseDTO login (LoginRequestDTO loginRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.getUsername(),
                        loginRequestDTO.getPassword())
        );

        User user = userRepository.findByUserName(loginRequestDTO.getUsername())
                .orElseThrow(()-> new RuntimeException("Username not found"));

        String token = jwtService.generateToken(user);

        return LoginResponseDTO.builder()
                .token(token)
                .username(user.getUsername())
                .roles(user.getRoles())
                .build();
    }

}
