package com.mycompany.quanlythuvien;

import com.mycompany.service.JdbcUtils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class DangNhapController {

    @FXML
    private TextField txttenDangNhap;
    @FXML
    private TextField txtmatKhau;

    public void btDangNhap(ActionEvent evt) throws IOException {

        if (txttenDangNhap.getText().equals("")) {
            Utils.getBox("Vui lòng nhập tên đăng nhập", Alert.AlertType.WARNING).show();
        }
        else if (txttenDangNhap.getText().length() < 6 || txttenDangNhap.getText().length() > 30) {
            Utils.getBox("Độ dài username từ 6 đến 30", Alert.AlertType.WARNING).show();
        }
        else if (txtmatKhau.getText().equals("")) {
            Utils.getBox("Vui lòng nhập mật khẩu", Alert.AlertType.WARNING).show();
        }
        else if (txtmatKhau.getText().length() < 8 || txtmatKhau.getText().length() > 20) {
            Utils.getBox("Độ dài password từ 8 đến 20", Alert.AlertType.WARNING).show();
        }
        else
        {
            try {
                Connection conn = JdbcUtils.getConn();
                String sql = "SELECT * FROM nhanvien WHERE tenDangNhap = ? AND matKhau = ?";

                PreparedStatement ps = conn.prepareStatement(sql);

                ps.setString(1, txttenDangNhap.getText());
                ps.setString(2, txtmatKhau.getText());

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    Utils.getBox("Đăng nhập thành công", Alert.AlertType.INFORMATION).show();
                    App.setRoot("QuanLyThuVien");
                } else {
                    Utils.getBox("Sai tên đăng nhập hoặc mật khẩu", Alert.AlertType.INFORMATION).show();

                }
            } catch (SQLException ex) {
                Logger.getLogger(DangNhapController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
//
//@FXML
//        private void switchToSecondary() throws IOException {
//        App.setRoot("secondary");
//    }
//}
