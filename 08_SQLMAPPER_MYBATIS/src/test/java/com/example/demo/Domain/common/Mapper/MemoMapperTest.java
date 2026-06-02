package com.example.demo.Domain.common.Mapper;

import com.example.demo.Domain.common.Dtos.MemoDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class MemoMapperTest {
    @Autowired
    private MemoMapper memoMapper;

    @Test
    public void t1(){
        memoMapper.insert(new MemoDTO(55L,"TITLE55","a@a.com","text55", LocalDateTime.now()));
    }

    @Test
    public void t2(){
        memoMapper.update(new MemoDTO(55L,null,null,"text55!!!!!!!!!", null));
    }

    @Test
    public void t3(){
        memoMapper.delete(55L);
    }

    @Test
    public void t4(){
        List<MemoDTO> list = memoMapper.selectALL();
        list.forEach(System.out::println);
    }

    @Test
    public void t5(){
        MemoDTO dto = new MemoDTO(null,"TITLE55","a@a.com","text55", LocalDateTime.now());
        System.out.println(dto);
        memoMapper.insert(dto);
        System.out.println(dto);
    }

    @Test
    public void t6(){
        List<Map<String, Object>> list = memoMapper.selectAllWithResultMap();
        list.forEach(System.out::println);
    }
}