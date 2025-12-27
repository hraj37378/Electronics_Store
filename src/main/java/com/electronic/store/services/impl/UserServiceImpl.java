package com.electronic.store.services.impl;

import com.electronic.store.dtos.UserDto;
import com.electronic.store.entities.User;
import com.electronic.store.repositories.UserRepository;
import com.electronic.store.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        String userId = UUID.randomUUID().toString();
        userDto.setUserId(userId);
        User user = dtoToEntity(userDto);
        userRepository.save(user);  // save method requires entity and we have passed dto
        UserDto newUserDto = entityToDto(user);
        return newUserDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));
        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());
        user.setGender(userDto.getGender());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        User updatedUser = userRepository.save(user);
        UserDto updatedUserDto = entityToDto(updatedUser);
        return updatedUserDto;
    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));
        userRepository.delete(user);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = userRepository.findAll();
        List<UserDto> usersDto = users.stream().map(user -> entityToDto(user)).collect(Collectors.toList());
        return usersDto;
    }

    @Override
    public UserDto getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return entityToDto(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        return entityToDto(user);
    }

    @Override
    public List<UserDto> searchUser(String keyword) {
        List<User> users = userRepository.findByNameContaining(keyword);
        List<UserDto> usersDto = users.stream().map(user -> entityToDto(user)).collect(Collectors.toList());
        return usersDto;
    }

    private UserDto entityToDto(User savedUser){
        /*
        UserDto userDto = UserDto.builder()
                .userId(savedUser.getUserId())
                .name(savedUser.getName())
                .gender(savedUser.getGender())
                .password(savedUser.getPassword())
                .email(savedUser.getEmail()).build();

         */
        return mapper.map(savedUser, UserDto.class);
    }

    private User dtoToEntity(UserDto userDto){
        /*
        User user = User.builder()
                .userId(userDto.getUserId())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .about(userDto.getAbout())
                .gender(userDto.getGender()).build();

         */
        return mapper.map(userDto, User.class);
    }
}
