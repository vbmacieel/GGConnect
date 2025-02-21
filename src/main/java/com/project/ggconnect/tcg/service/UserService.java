package com.project.ggconnect.tcg.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.ggconnect.tcg.dto.auth.AuthLoginDTO;
import com.project.ggconnect.tcg.dto.auth.AuthRegisterDTO;
import com.project.ggconnect.tcg.dto.auth.AuthResponseDTO;
import com.project.ggconnect.tcg.exception.EmailAlreadyExistsException;
import com.project.ggconnect.tcg.exception.InvalidPasswordException;
import com.project.ggconnect.tcg.exception.UserNotFoundException;
import com.project.ggconnect.tcg.model.User;
import com.project.ggconnect.tcg.repository.UserRepository;
import com.project.ggconnect.tcg.security.JwtTokenService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final JwtTokenService tokenService;
    private final PasswordEncoder encoder;

    public void registerUser(AuthRegisterDTO dto) {
        Optional<User> optionalUser = this.repository.findByEmail(dto.getEmail());

        if (optionalUser.isPresent())
            throw new EmailAlreadyExistsException("There is already an user with this email!");

        User user = new User(dto.getUsername(), dto.getEmail(), encoder.encode(dto.getPassword()));
        repository.save(user);
    }

    public AuthResponseDTO loginUser(AuthLoginDTO dto) {
        User user = this.repository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found!"));

        if (!encoder.matches(dto.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException("Invalid password!");
        }

        String token = tokenService.generateToken(user);
        return new AuthResponseDTO(token, user.getUsername());
    }

    public void checkIfUserExists(Long id) {
        if (!repository.existsById(id)) throw new UserNotFoundException("User not found!");
    }

    public User findUserById(Long id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found!"));
    }
}
