package com.example.demo.Domain.Common.Entity;

import com.example.demo.Domain.Common.Repository.BookRepository;
import com.example.demo.Domain.Common.Repository.LendRepository;
import com.example.demo.Domain.Common.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class LendTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private LendRepository lendRepository;


    @Test
    public void t1(){
        //INSERT
        User user = userRepository.findById("user1").get();
        Book book = bookRepository.findById(1111L).get();
        Lend lend = Lend.builder()
                .user(user)
                .book(book)
                .build();
        lendRepository.save(lend);
    }
    @Test
    public void t2(){
        //유저이름으로 대여 내역 조회
//        List<Lend> list = lendRepository.findAllendsByUser("user1");
//        list.forEach(System.out::println);

        //도서이름으로 대여 내역 조회
        List<Lend> list = lendRepository.findAllendsByBook("JAVA의정석");
        list.forEach(System.out::println);
    }

    @Test
    @Transactional
    public void t3(){

        System.out.println("1 start-------------");
        Lend lend = lendRepository.findById(1L).get();
        System.out.println("1 end-------------");

        System.out.println("2 start-------------");
        System.out.println(lend.getUser().getUsername());
        System.out.println("2 end-------------");

        System.out.println("3 start-------------");
        System.out.println(lend.getBook().getBookName());
        System.out.println("3 end-------------");
    }
}