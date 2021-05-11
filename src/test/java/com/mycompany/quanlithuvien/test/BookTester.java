package com.mycompany.quanlithuvien.test;

import com.mycompany.pojo.Sach;
import com.mycompany.service.JdbcUtils;
import com.mycompany.service.SachService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookTester {
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
    public void testWithKeyWord() {
        try {
            SachService ss = new SachService(CONN);
            List<Sach> sach = ss.getSach("toán cao");

            sach.forEach(p -> {
                Assertions.assertTrue(p.getTenSach().toLowerCase().contains("toán cao"));
            });
        } catch (SQLException ex) {
            Logger.getLogger(BookTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testUnknownWithKeyWord() {
        try {
            SachService ss = new SachService(CONN);
            List<Sach> sach = ss.getSach("^&^GyzYGFHGD%$");
            Assertions.assertEquals(0, sach.size());
        } catch (SQLException ex) {
            Logger.getLogger(BookTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testException() {
        Assertions.assertThrows(SQLDataException.class, () -> {
            new SachService(CONN).getSach(null);
        });
    }

    @Test
    public void testTimeout() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            new SachService(CONN).getSach("");
        });
    }

    @Test
    @DisplayName("Test chuc nang them sach voi name = null")
    @Tag("critical")
    public void testAddBookNameNull() {
        try {
            Sach s = new Sach();
            s.setTenSach(null);
            s.setTacGia("Adam Khoo");
            s.setTheLoai("Tâm lý");
            s.setNhaXuatBan("Việt Nam");
            s.setGiaSach(new BigDecimal(10000));
            s.setSoLuong(123);

            SachService ss = new SachService(CONN);
            Assertions.assertFalse(ss.themSach(s));
        } catch (SQLException ex) {
            Logger.getLogger(BookTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    @DisplayName("Test chuc nang them sach voi tac gia = null")
    @Tag("critical")
    public void testAddBookTGNull() {
        try {
            Sach s = new Sach();
            s.setTenSach("Test them");
            s.setTacGia(null);
            s.setTheLoai("Tâm lý");
            s.setNhaXuatBan("Việt Nam");
            s.setGiaSach(new BigDecimal(10000));
            s.setSoLuong(123);

            SachService ss = new SachService(CONN);
            Assertions.assertFalse(ss.themSach(s));
        } catch (SQLException ex) {
            Logger.getLogger(BookTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    @DisplayName("Test chuc nang them sach voi nha xuat ban = null")
    @Tag("critical")
    public void testAddBookNXBNull() {
        try {
            Sach s = new Sach();
            s.setTenSach("Test them");
            s.setTacGia("Adam Khoo");
            s.setTheLoai("Tâm lý");
            s.setNhaXuatBan(null);
            s.setGiaSach(new BigDecimal(10000));
            s.setSoLuong(123);

            SachService ss = new SachService(CONN);
            Assertions.assertFalse(ss.themSach(s));
        } catch (SQLException ex) {
            Logger.getLogger(BookTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    @DisplayName("Test chuc nang them sach voi the loai = null")
    @Tag("critical")
    public void testAddBookTLNull() {
        try {
            Sach s = new Sach();
            s.setTenSach("Test them");
            s.setTacGia("Adam Khoo");
            s.setTheLoai(null);
            s.setNhaXuatBan("Việt Nam");
            s.setGiaSach(new BigDecimal(10000));
            s.setSoLuong(123);

            SachService ss = new SachService(CONN);
            Assertions.assertFalse(ss.themSach(s));
        } catch (SQLException ex) {
            Logger.getLogger(BookTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testAddBook() {
        try {
            Sach s = new Sach();
            s.setTenSach("Test thêm sách");
            s.setTacGia("Adam Khoo");
            s.setTheLoai("Tâm lý");
            s.setNhaXuatBan("Việt Nam");
            s.setGiaSach(new BigDecimal(10000));
            s.setSoLuong(123);

            SachService ss = new SachService(CONN);
            Assertions.assertTrue(ss.themSach(s));
        } catch (SQLException ex) {
            Logger.getLogger(BookTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @ParameterizedTest
    @CsvSource({"S1,Thang,Truyện tranh,VN,200000,123", "S2,Hoang,Cổ tích,VN,200000,45"})
    public void testAddBatchProduct(String name, String tg, String tl , String nxb ,BigDecimal price, int sl ) {
        try {
            Sach s = new Sach();
            s.setTenSach(name);
            s.setTacGia(tg);
            s.setTheLoai(tl);
            s.setNhaXuatBan(nxb);
            s.setGiaSach(price);
            s.setSoLuong(sl);

            SachService ss = new SachService(CONN);
            Assertions.assertTrue(ss.themSach(s));
        } catch (SQLException ex) {
            Logger.getLogger(BookTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
