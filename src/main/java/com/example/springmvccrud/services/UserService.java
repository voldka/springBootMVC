package com.example.springmvccrud.services;

import com.example.springmvccrud.converter.UserConverter;
import com.example.springmvccrud.dto.UserDTO;
import com.example.springmvccrud.entity.UserEntity;
import com.example.springmvccrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> listAll(){
        List<UserDTO> list =new ArrayList<>();
        Iterable<UserEntity> iterable = userRepository.findAll();
        for (UserEntity item : iterable) {
            list.add(userConverter.toDto(item));
        }
        return list;
    }
    public boolean save(UserDTO userDTO){
        UserEntity userEntity = new UserEntity();
        userEntity = userConverter.toEntity(userDTO);
        if(userEntity.getAddress()==null){
            userEntity.setAddress("chưa cập nhật");
        }
        UserEntity users = userRepository.save(userEntity);
        if(users != null &&users.getId()!=0){
            return true;
        }else{
            return false;
        }
    }
}
