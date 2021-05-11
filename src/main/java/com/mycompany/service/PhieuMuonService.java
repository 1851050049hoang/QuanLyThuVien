/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.PhieuMuon;
import com.mycompany.pojo.Sach;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dell
 */
public class PhieuMuonService {
    private Connection conn;
    
    public PhieuMuonService(Connection conn){
        this.conn = conn;
    }
    
    public List<PhieuMuon> getPhieuMuon(String maPhieu) throws SQLException {
        if (maPhieu == null)
            throw new SQLDataException();
        
        String sql = "SELECT * FROM phieumuon WHERE maPhieuMuon like concat('%', ?, '%')";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setString(1, maPhieu);
        
        ResultSet rs = stm.executeQuery();
        List<PhieuMuon> phieu = new ArrayList<>();
        while (rs.next()) {
            PhieuMuon p = new PhieuMuon();
            p.setMaPhieuMuon(rs.getInt("maPhieuMuon"));
            p.setMaDocGia(rs.getInt("maDocGia"));
            p.setMaSach(rs.getInt("maSach"));
            p.setSoLuong(rs.getInt("soLuong"));
            p.setTenDocGia(rs.getString("tenDocGia"));
            p.setTenSach(rs.getString("tenSach"));
            p.setTinhTrang(rs.getString("tinhTrang"));
            p.setNgayMuon(rs.getString("ngayMuon"));
            p.setHanTra(rs.getString("hanTra"));
            p.setNgayTra(rs.getString("ngayTra"));
            p.setGhiChu(rs.getString("ghiChu"));
            //p.setMaNhanVien(rs.getInt("maNhanVien"));
            
            phieu.add(p);
        }
        return phieu;
    }
    
    public boolean themPhieuMuon(PhieuMuon s) throws SQLException {
        String sql = "INSERT INTO phieumuon(maDocGia, maSach, soLuong, tenDocGia,"
                + " tenSach, tinhTrang, ngayMuon, hanTra) "
                + "VALUES(?, ?, ?, ?, ?, 'Chưa trả', ?, ?)";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        
        stm.setInt(1, s.getMaDocGia());
        stm.setInt(2, s.getMaSach());
        stm.setInt(3, s.getSoLuong());
        stm.setString(4, s.getTenDocGia());
        stm.setString(5, s.getTenSach());
        stm.setString(6, s.getNgayMuon());
        stm.setString(7, s.getHanTra());
        
        int row = stm.executeUpdate();
        
        return row > 0;
    }
    
    public boolean xoaPhieu(int maPhieu) throws SQLException {
        String sql = "DELETE FROM phieumuon WHERE maPhieuMuon=?";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setInt(1, maPhieu);
        
        int row = stm.executeUpdate();
        
        return row > 0;
    }
    
    public boolean traSach(int maPhieu, String ngayTra) throws SQLException {
        String sql = "UPDATE phieumuon SET tinhTrang='Đã trả', ngayTra=? WHERE maPhieuMuon=?";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setString(1, ngayTra);
        stm.setInt(2, maPhieu);
        
        int row = stm.executeUpdate();
        
        return row > 0;
    }
    
    public boolean suaPhieu(PhieuMuon p, PhieuMuon pp) throws SQLException{
        String sql = "UPDATE phieumuon SET maDocGia=?, maSach=?, soLuong=?, tenDocGia=?,"
                + " tenSach=?, tinhTrang=? WHERE maPhieuMuon=?";

        PreparedStatement stm = this.conn.prepareStatement(sql);
        
        stm.setInt(1, pp.getMaDocGia());
        stm.setInt(2, pp.getMaSach());
        stm.setInt(3, pp.getSoLuong());
        stm.setString(4, pp.getTenDocGia());
        stm.setString(5, pp.getTenSach());
        stm.setString(6, pp.getTinhTrang());
        stm.setInt(7, p.getMaPhieuMuon());
        
        int row = stm.executeUpdate();
        return row > 0;
    }
    
    public int demSLSachChoMuon() throws SQLException{
        String sql = "SELECT * FROM phieumuon where tinhTrang = 'Chưa Trả'";

        PreparedStatement stm = this.conn.prepareStatement(sql);
        
        ResultSet rs = stm.executeQuery();
        
        int sl = 0;
        while (rs.next()) {        
            Sach s = new Sach();
            s.setSoLuong(rs.getInt("soLuong"));
            sl += s.getSoLuong();
        }
        return sl;
    }
    
    public void updateS(int maPhieu) throws SQLException{
        String sql = "UPDATE phieumuon SET ghiChu='Đã quá hạn' WHERE maPhieuMuon=?";
        
        PreparedStatement stm = this.conn.prepareStatement(sql);
        
        stm.setInt(1, maPhieu);
        
        stm.executeUpdate();
    }
    
    public void capNhatTinhTrang() throws SQLException{
        String sql = "SELECT * FROM phieumuon WHERE tinhTrang='Chưa trả'";

        PreparedStatement stm = this.conn.prepareStatement(sql);
        
        ResultSet rs = stm.executeQuery();
        
        LocalDate lcD = LocalDate.now();
        

        while(rs.next()){
            PhieuMuon p = new PhieuMuon();
            
            p.setHanTra(rs.getString("hanTra"));
            p.setMaPhieuMuon(rs.getInt("maPhieuMuon"));
            
            LocalDate localDate = LocalDate.parse(p.getHanTra(),DateTimeFormatter.ofPattern("dd-LL-yyyy"));
            if(lcD.isAfter(localDate)){
                updateS(p.getMaPhieuMuon());
            }
        }
    }
}
