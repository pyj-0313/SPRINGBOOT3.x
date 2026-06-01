package com.example.demo.Domain.common.Daos;

import com.example.demo.Domain.common.Dtos.MemoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository
public class MemoDAO {
    @Autowired
    private DataSource dataSource3;
    //CRUD FUNCTION
    public int insert(MemoDTO dto) throws SQLException {
        Connection conn = dataSource3.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("insert into tbl_memo values(null,?,?,?,?)");
        pstmt.setString(1,dto.getTitle());
        pstmt.setString(2,dto.getWriter());
        pstmt.setString(3,dto.getText());
        pstmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
        int result = pstmt.executeUpdate();
        return result;
    }
}
