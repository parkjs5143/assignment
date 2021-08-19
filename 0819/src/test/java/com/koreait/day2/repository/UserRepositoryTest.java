package com.koreait.day2.repository;

import com.koreait.day2.Day2ApplicationTests;
import com.koreait.day2.model.entity.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends Day2ApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){
//        Users user = new Users();
//        user.setUserid("banana");
//        user.setUserpw("1234");
//        user.setHp("010-2222-2222");
//        user.setEmail("banana@banana.com");
//        user.setRegDate(LocalDateTime.now());
//
//        Users newUser = userRepository.save(user);

        Users user = Users.builder()
                .userid("melon")
                .userpw("1212")
                .hp("010-3333-3333")
                .email("orange@orange.com")
                .regDate(LocalDateTime.now())
                .build();
        Users newUser = userRepository.save(user);
    }

    @Test
    public void read(){
        // select * from users where userid=?
//        Optional<Users> user = userRepository.findByUserid("banana");
//        user.ifPresent(selectUser -> {
//            System.out.println("users : " + selectUser);
//            System.out.println("userid : " + selectUser.getUserid());
//            System.out.println("userpw : " + selectUser.getUserpw());
//            System.out.println("hp : " + selectUser.getHp());
//            System.out.println("email : " + selectUser.getEmail());
//        });

        Users user = userRepository.findFirstByHpOrderByIdDesc("010-2222-1111");
        if(user != null){
            System.out.println("데이터가 존재합니다!");
        }else{
            System.out.println("데이터가 존재하지 않습니다!");
        }
    }

    @Test
    public void update(){
        Optional<Users> user = userRepository.findByUserid("banana");
        user.ifPresent(selectUser -> {
            selectUser.setEmail("banana@naver.com");
            selectUser.setHp("010-0000-0000");
            selectUser.setUpdateDate(LocalDateTime.now());
            userRepository.save(selectUser);
        });
    }

    @Test
    public void delete(){
        Optional<Users> user = userRepository.findByUserid("banana");

        user.ifPresent(selectUser -> {
            userRepository.delete(selectUser);
        });

        Optional<Users> deleteUser = userRepository.findByUserid("banana");
        if(deleteUser.isPresent()){
            System.out.println("삭제실패!");
        }else{
            System.out.println("삭제성공!");
        }
    }
}
