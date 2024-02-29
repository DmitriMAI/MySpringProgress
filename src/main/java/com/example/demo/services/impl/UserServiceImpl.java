package com.example.demo.services.impl;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.enums.UserRole;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserEntity userEntity){
        String password = userEntity.getPassword();
        userEntity.setPassword(passwordEncoder.encode(password));
        userEntity.setRole(UserRole.USER);
        userRepository.save(userEntity);
    }
}
