/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.DocGia;
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
public class DocGiaService {
    private Connection conn;
    
    public DocGiaService(Connection conn){
        this.conn = conn;
    }
    
    public List<DocGia> getDocGia(String kw) throws SQLException{
        
        if (kw == null)
            throw new SQLDataException();
        
        String sql = "SELECT * FROM docgia WHERE hoTen like concat('%', ?, '%') ORDER BY maDocGia ASC";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setString(1, kw);
        
        ResultSet r = stm.executeQuery();
        
        List<DocGia> dg = new ArrayList<>();
        
        while(r.next()){
            DocGia d = new DocGia();
            d.setMaDocGia(r.getInt("maDocGia"));
            d.setHoTen(r.getString("hoTen"));
            d.setGioiTinh(r.getString("gioiTinh"));
            d.setNgaySinh(r.getString("namSinh"));
            d.setDiaChi(r.getString("diaChi"));
            
            dg.add(d);
        }
        return dg;
    }
    
    public boolean themDocGia(DocGia d) throws SQLException {
        String sql = "INSERT INTO docgia(hoTen, gioiTinh, namSinh, diaChi) VALUES(?, ?, ?, ?)";
        
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setString(1, d.getHoTen());
        stm.setString(2, d.getGioiTinh());
        stm.setString(3, d.getNgaySinh());
        stm.setString(4, d.getDiaChi());
        
        int row = stm.executeUpdate();
        
        return row > 0;
    }
    
    public boolean suaDocGia(DocGia d, DocGia dd) throws SQLException{
        String sql = "UPDATE docgia SET hoTen=?, gioiTinh=?, namSinh=?, diaChi=? WHERE maDocGia=?";

        PreparedStatement stm = this.conn.prepareStatement(sql);
        
        stm.setString(1, dd.getHoTen());
        stm.setString(2, dd.getGioiTinh());
        stm.setString(3, dd.getNgaySinh());
        stm.setString(4, dd.getDiaChi());
        stm.setInt(5, d.getMaDocGia());
        
        int row = stm.executeUpdate();
        return row > 0;
    }
    
    public boolean xoaDocGia(int maDocGia) throws SQLException {
        String sql = "DELETE FROM docgia WHERE maDocGia=?";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setInt(1, maDocGia);
        
        int row = stm.executeUpdate();
        
        return row > 0;
    }
    
    public String getTenDGById(int id) throws SQLException {
        
        String sql = "SELECT * FROM docgia WHERE maDocGia=?";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setInt(1, id);
        
        ResultSet rs = stm.executeQuery();
        DocGia d = null;
        while (rs.next()) {
            d = new DocGia();
            d.setMaDocGia(rs.getInt("maDocGia"));
            d.setHoTen(rs.getString("hoTen"));
            
        }
        if(d == null)
            return "0";
        return d.getHoTen();
    }
    
    public int demDocGia() throws SQLException {
        
        String sql = "SELECT * FROM docgia";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        
        ResultSet rs = stm.executeQuery();
        
        int d = 0;
        
        while (rs.next()){
            ++d;
        }
        return d;
    }
}
