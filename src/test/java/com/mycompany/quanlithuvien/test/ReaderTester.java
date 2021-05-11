package com.mycompany.quanlithuvien.test;

import com.mycompany.pojo.DocGia;
import com.mycompany.service.DocGiaService;
import com.mycompany.service.JdbcUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.sql.Connection;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReaderTester {
    private static Connection CONN;

    @BeforeAll
    public static void setUp() {
        try {
            CONN = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(ReaderTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @AfterAll
    public static void tearDownClass() {
        if (CONN != null)
            try {
                CONN.close();
            } catch (SQLException ex) {
                Logger.getLogger(ReaderTester.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    @Test
    public void testWithKeyWord() {
        try {
            DocGiaService ds = new DocGiaService(CONN);
            List<DocGia> DocGia = ds.getDocGia("đặng");

            DocGia.forEach(p -> {
                Assertions.assertTrue(p.getHoTen().toLowerCase().contains("đặng"));
            });
        } catch (SQLException ex) {
            Logger.getLogger(ReaderTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void testUnknownWithKeyWord() {
        try {
            DocGiaService ds = new DocGiaService(CONN);
            List<DocGia> DocGia = ds.getDocGia("43*&^&^GYabcxyzYGFHGD%$");

            Assertions.assertEquals(0, DocGia.size());
        } catch (SQLException ex) {
            Logger.getLogger(ReaderTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testException() {
        Assertions.assertThrows(SQLDataException.class, () -> {
            new DocGiaService(CONN).getDocGia(null);
        });
    }

    @Test
    public void testTimeout() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            new DocGiaService(CONN).getDocGia("");
        });
    }

    @Test
    @DisplayName("Test chuc nang them doc gia voi name = null")
    @Tag("critical")
    public void testAddReaderNameNull() {
        try {
            DocGia dg = new DocGia();
            dg.setHoTen(null);
            dg.setGioiTinh("Nam");
            dg.setNgaySinh("28-10-2000");
            dg.setDiaChi("HCM");

            DocGiaService ds = new DocGiaService(CONN);
            Assertions.assertFalse(ds.themDocGia(dg));
        } catch (SQLException ex) {
            Logger.getLogger(ReaderTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    @DisplayName("Test chuc nang them doc gia voi gioi tinh = null")
    @Tag("critical")
    public void testAddReaderGioiTinhNull() {
        try {
            DocGia dg = new DocGia();
            dg.setHoTen("Test thêm độc giả");
            dg.setGioiTinh(null);
            dg.setNgaySinh("28-10-2000");
            dg.setDiaChi("HCM");

            DocGiaService ds = new DocGiaService(CONN);
            Assertions.assertFalse(ds.themDocGia(dg));
        } catch (SQLException ex) {
            Logger.getLogger(ReaderTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    @DisplayName("Test chuc nang them doc gia voi address = null")
    @Tag("critical")
    public void testAddReaderAddressNull() {
        try {
            DocGia dg = new DocGia();
            dg.setHoTen("Test thêm độc giả");
            dg.setGioiTinh("Nam");
            dg.setNgaySinh("28-10-2000");
            dg.setDiaChi(null);

            DocGiaService ds = new DocGiaService(CONN);
            Assertions.assertFalse(ds.themDocGia(dg));
        } catch (SQLException ex) {
            Logger.getLogger(ReaderTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testAddReader() {
        try {
            DocGia dg = new DocGia();
            dg.setHoTen("Test them");
            dg.setGioiTinh("Nam");
            dg.setNgaySinh("28-10-2000");
            dg.setDiaChi("HCM");

            DocGiaService ds = new DocGiaService(CONN);
            Assertions.assertTrue(ds.themDocGia(dg));
        } catch (SQLException ex) {
            Logger.getLogger(ReaderTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @ParameterizedTest
    @CsvSource({"RD1,Nam,1987,Hà Nội", "RD2,Nữ,2001,HCM"})
    public void testAddBatchProduct(String name, String gt, int ns , String dc) {
        try {
            DocGia dg = new DocGia();
            dg.setHoTen(name);
            dg.setGioiTinh(gt);
            dg.setNgaySinh("28-10-2000");
            dg.setDiaChi(dc);

            DocGiaService ds = new DocGiaService(CONN);
            Assertions.assertTrue(ds.themDocGia(dg));
        } catch (SQLException ex) {
            Logger.getLogger(BookTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}