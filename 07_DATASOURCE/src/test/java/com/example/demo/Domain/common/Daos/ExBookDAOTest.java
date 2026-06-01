package com.example.demo.Domain.common.Daos;

import com.example.demo.Domain.common.Dtos.BookDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExBookDAOTest {
    @Autowired
    private ExBookDAO exBookDAO;

    @Test
    public void t1() throws SQLException {
        exBookDAO.insert(new BookDTO(null,"제목!","작가",null));
    }
}