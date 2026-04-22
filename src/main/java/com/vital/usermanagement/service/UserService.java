package com.vital.usermanagement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.vital.usermanagement.dto.UserRequestDTO;
import com.vital.usermanagement.dto.UserResponseDTO;
import com.vital.usermanagement.entity.User;
import com.vital.usermanagement.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDTO createUser(UserRequestDTO dto) {

        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();

        userRepository.save(user);

        return mapToResponse(user);
    }

    public List<UserResponseDTO> findAll() {

        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public UserResponseDTO findById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return mapToResponse(user);
    }

    public void delete(Long id) {

        userRepository.deleteById(id);
    }

    private UserResponseDTO mapToResponse(User user) {

        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

}