/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.Sach;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dell
 */
public class SachService {
    
    private Connection conn;
    
    public SachService(Connection conn){
        this.conn = conn;
    }
    
    public List<Sach> getSach(String kw) throws SQLException {
        if (kw == null)
            throw new SQLDataException();
        
        String sql = "SELECT * FROM sach WHERE tenSach like concat('%', ?, '%') ORDER BY maSach ASC";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setString(1, kw);
        
        ResultSet rs = stm.executeQuery();
        List<Sach> sach = new ArrayList<>();
        while (rs.next()) {
            Sach s = new Sach();
            s.setMaSach(rs.getInt("maSach"));
            s.setTenSach(rs.getString("tenSach"));
            s.setTacGia(rs.getString("tacGia"));
            s.setTheLoai(rs.getString("theLoai"));
            s.setNhaXuatBan(rs.getString("nhaXuatBan"));
            s.setGiaSach(rs.getBigDecimal("giaSach"));
            s.setSoLuong(rs.getInt("soLuong"));
            sach.add(s);
        }
        return sach;
    }
    
    public boolean themSach(Sach s) throws SQLException {
        String sql = "INSERT INTO sach(tenSach, tacGia, theLoai, "
                + "nhaXuatBan, giaSach, soLuong) VALUES(?, ?, ?, ?, ?, ?)";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setString(1, s.getTenSach());
        stm.setString(2, s.getTacGia());
        stm.setString(3, s.getTheLoai());
        stm.setString(4, s.getNhaXuatBan());
        stm.setBigDecimal(5, s.getGiaSach());
        stm.setInt(6, s.getSoLuong());
        
        int row = stm.executeUpdate();
        
        return row > 0;
    }
    
    public boolean xoaSach(int maSach) throws SQLException {
        String sql = "DELETE FROM sach WHERE maSach=?";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setInt(1, maSach);
        
        int row = stm.executeUpdate();
        
        return row > 0;
    }
    
    public String getTenSachById(int id) throws SQLException {
        String sql = "SELECT * FROM sach WHERE maSach=?";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setInt(1, id);
        
        ResultSet rs = stm.executeQuery();
        Sach s = null;
        while (rs.next()) {
            s = new Sach();
            s.setMaSach(rs.getInt("maSach"));
            s.setTenSach(rs.getString("tenSach"));
            
        }
        if(s == null)
            return "0";
        return s.getTenSach();
    }
    
    public int getSLSachById(int id) throws SQLException {
        String sql = "SELECT * FROM sach WHERE maSach=?";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setInt(1, id);
        
        ResultSet rs = stm.executeQuery();
        Sach s = null;
        while (rs.next()) {
            s = new Sach();
            s.setMaSach(rs.getInt("maSach"));
            s.setSoLuong(rs.getInt("soLuong"));
            
        }
        return s.getSoLuong();
    }
    
    public boolean suaSLSach(Sach s) throws SQLException{
        String sql = "UPDATE sach SET soLuong=? WHERE maSach=?";

        PreparedStatement stm = this.conn.prepareStatement(sql);
        
        stm.setInt(1, s.getSoLuong());
        stm.setInt(2, s.getMaSach());
        
        int row = stm.executeUpdate();
        return row > 0;
    }
    
    public int demSLSach() throws SQLException{
        String sql = "SELECT * FROM sach";

        PreparedStatement stm = this.conn.prepareStatement(sql);
        
        ResultSet rs = stm.executeQuery();
        
        int sl = 0;
        while (rs.next()) {        
            Sach s = new Sach();
            s.setSoLuong(rs.getInt("soLuong"));
            sl += s.getSoLuong();
        }
        
        PhieuMuonService ps = new PhieuMuonService(conn);
        
        return sl + ps.demSLSachChoMuon();
    }
    
    public boolean suaSach(Sach s, Sach ss) throws SQLException{
        String sql = "UPDATE sach SET tenSach=?, tacGia=?, theLoai=?, nhaXuatBan=?,"
                + " giaSach=?, soLuong=? WHERE maSach=?";

        PreparedStatement stm = this.conn.prepareStatement(sql);
        
        stm.setString(1, ss.getTenSach());
        stm.setString(2, ss.getTacGia());
        stm.setString(3, ss.getTheLoai());
        stm.setString(4, ss.getNhaXuatBan());
        stm.setBigDecimal(5, ss.getGiaSach());
        stm.setInt(6, ss.getSoLuong());
        stm.setInt(7, s.getMaSach());
        
        int row = stm.executeUpdate();
        return row > 0;
    }
}
