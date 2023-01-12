package com.example.springmvccrud;

import com.example.springmvccrud.entity.UserEntity;
import com.example.springmvccrud.repository.UserRepository;
import org.apache.catalina.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testAddUser(){
        UserEntity user = new UserEntity();
        user.setFullName("tran gian khang");
        user.setUserName("trangiakhang");
        user.setAddress("lo1,ham nghi");
        user.setEmail("tailx2301@gmai.com");
        user.setPassword("matkhau");
        UserEntity savedUser = userRepository.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }
    @Test
    public void testListAll(){
        Iterable<UserEntity> users = userRepository.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);
        for (UserEntity user: users) {
            System.out.println(user.toString() );
        }
    }
    @Test
    public void testUpdate(){
        long userId =1;
        Optional<UserEntity>  optionalUserEntity = userRepository.findById(userId);
        UserEntity user = optionalUserEntity.get();
        user.setFullName("nguyen quoc cuong");
        userRepository.save(user);

        UserEntity updateUser = userRepository.findById(userId).get();
        Assertions.assertThat(updateUser.getFullName()).isEqualTo("nguyen quoc cuong");
    }
    @Test
    public void testGet(){
        long userId=2;
        Optional<UserEntity>  optionalUserEntity = userRepository.findById(userId);
        Assertions.assertThat(optionalUserEntity).isPresent();
        System.out.println(optionalUserEntity.get().toString());
    }
    @Test
    public void testDeleta(){
        long userId=2;
        userRepository.deleteById(userId);

        Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
        Assertions.assertThat(optionalUserEntity).isNotPresent();
    }
}
