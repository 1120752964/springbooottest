package com.ldl.springboottest;

import com.ldl.springboottest.entity.User;
import com.ldl.springboottest.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.util.List;

@SpringBootTest
class SpringboottestApplicationTests {

    private UserRepository  userRepository;

    @Test
    void md5(){
        List<User> users =  userRepository.findAll();
        for (int i = 0; i < users.size(); i++) {
            User user1 = users.get(i);
            user1.setUpaypassword(DigestUtils.md5DigestAsHex(user1.getUpassword().getBytes()));
            userRepository.save(user1);
        }
//        String string = DigestUtils.md5DigestAsHex("1".getBytes());
//        System.out.println("-------------------"+string);
    }


//    @Autowired
//    private BookRepository repository;
//
//    @Test
//    void contextLoads() {
//    }
//
//    @Test
//    void save(){
//        Book book = new Book();
//        book.setName("Spring boot");
//        book.setAuthor("张三");
//        Book book1 = repository.save(book);
//        System.out.println(book1);
//    }
//
//    @Test
//    void findById(){
//        Book book = repository.findById(1).get();
//        System.out.println(book);
//    }
//
//    @Test
//    void update(){
//        Book book = new Book();
//        book.setId(15);
//        book.setName("物种起源");
//        book.setAuthor("查尔斯·达尔文");
//        Book book1 = repository.save(book);
//        System.out.println(book1);
//    }
//    @Test
//    void delete(){
//        repository.deleteById(14);
//    }
}
