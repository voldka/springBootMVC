package com.example.springmvccrud.repository;

import com.example.springmvccrud.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity,Long> {

    UserEntity findOneByUserNameAndPassword(String username, String password);
    UserEntity findByUserName(String username);
    UserEntity save(UserEntity user);

}

