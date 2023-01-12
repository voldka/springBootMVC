package com.example.springmvccrud.converter;

import com.example.springmvccrud.dto.UserDTO;
import com.example.springmvccrud.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public UserDTO toDto(UserEntity entity) {
        UserDTO result = new UserDTO();
        result.setId(entity.getId());
        result.setFullName(entity.getFullName());
        result.setPassword(entity.getPassword());
        result.setUserName(entity.getUserName());
        result.setEmail(entity.getEmail());
        result.setAddress(entity.getAddress());
        return result;
    }

    public UserEntity toEntity(UserDTO dto) {
        UserEntity result = new UserEntity();
        result.setId(dto.getId());
        result.setAddress(dto.getAddress());
        result.setEmail(dto.getEmail());
        result.setUserName(dto.getUserName());
        result.setPassword(dto.getPassword());
        result.setFullName(dto.getFullName());
        return result;
    }

    public UserEntity toEntity(UserEntity result, UserDTO dto) {
        result.setId(dto.getId());
        result.setAddress(dto.getAddress());
        result.setEmail(dto.getEmail());
        result.setUserName(dto.getUserName());
        result.setPassword(dto.getPassword());
        result.setFullName(dto.getFullName());
        return result;
    }
}
