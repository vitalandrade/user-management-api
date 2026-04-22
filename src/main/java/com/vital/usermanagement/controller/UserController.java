package com.vital.usermanagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vital.usermanagement.dto.UserRequestDTO;
import com.vital.usermanagement.dto.UserResponseDTO;
import com.vital.usermanagement.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(
            @RequestBody UserRequestDTO dto) {

        return ResponseEntity.ok(userService.createUser(dto));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {

        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(
            @PathVariable Long id) {

        return ResponseEntity.ok(userService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        userService.delete(id);

        return ResponseEntity.noContent().build();
    }

}