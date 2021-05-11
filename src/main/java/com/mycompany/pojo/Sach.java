/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pojo;

import java.math.BigDecimal;

/**
 *
 * @author Dell
 */
public class Sach {
    private int maSach;
    private String tenSach;
    private String tacGia;
    private String theLoai;
    private String nhaXuatBan;
    private BigDecimal giaSach;
    private int soLuong;
    private boolean tinhTrang;

    /**
     * @return the maSach
     */
    
    @Override
    public String toString() {
        return this.tenSach;
    }
    
    public int getMaSach() {
        return maSach;
    }

    /**
     * @param maSach the maSach to set
     */
    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    /**
     * @return the tenSach
     */
    public String getTenSach() {
        return tenSach;
    }

    /**
     * @param tenSach the tenSach to set
     */
    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    /**
     * @return the tacGia
     */
    public String getTacGia() {
        return tacGia;
    }

    /**
     * @param tacGia the tacGia to set
     */
    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    /**
     * @return the theLoai
     */
    public String getTheLoai() {
        return theLoai;
    }

    /**
     * @param theLoai the theLoai to set
     */
    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    /**
     * @return the nhaXuatBan
     */
    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    /**
     * @param nhaXuatBan the nhaXuatBan to set
     */
    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    /**
     * @return the giaSach
     */
    public BigDecimal getGiaSach() {
        return giaSach;
    }

    /**
     * @param giaSach the giaSach to set
     */
    public void setGiaSach(BigDecimal giaSach) {
        this.giaSach = giaSach;
    }

    /**
     * @return the soLuong
     */
    public int getSoLuong() {
        return soLuong;
    }

    /**
     * @param soLuong the soLuong to set
     */
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    /**
     * @return the tinhTrang
     */
    public boolean isTinhTrang() {
        return tinhTrang;
    }

    /**
     * @param tinhTrang the tinhTrang to set
     */
    public void setTinhTrang(boolean tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
}
