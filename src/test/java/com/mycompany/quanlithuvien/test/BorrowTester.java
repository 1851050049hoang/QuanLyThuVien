package com.mycompany.quanlithuvien.test;

import com.mycompany.service.JdbcUtils;
import com.mycompany.service.SachService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BorrowTester {
    private static Connection CONN;

    @BeforeAll
    public static void setUp() {
        try {
            CONN = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(BookTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @AfterAll
    public static void tearDownClass() {
        if (CONN != null)
            try {
                CONN.close();
            } catch (SQLException ex) {
                Logger.getLogger(BookTester.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    @Test
    public void testCheckBookById(){
        SachService ss = new SachService(CONN);
        
        String name;
        try {
            name = ss.getTenSachById(1);
            Assertions.assertNotEquals("", name);
            
            System.out.println("Test successful");
        } catch (SQLException ex) {
            System.out.println("Test fail");
            Logger.getLogger(BorrowTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    @Test
//    public void testCheckBookById(){
//        SachService ss = new SachService(CONN);
//        
//        String name;
//        try {
//            name = ss.getTenSachById(1);
//            Assertions.assertNotEquals("", name);
//            
//            System.out.println("Test successful");
//        } catch (SQLException ex) {
//            System.out.println("Test fail");
//            Logger.getLogger(BorrowTester.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
