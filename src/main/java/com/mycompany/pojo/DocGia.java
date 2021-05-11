/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pojo;

/**
 *
 * @author Dell
 */
public class DocGia {
    private int maDocGia;
    private String hoTen;
    private String gioiTinh;
    private String ngaySinh;
    private String diaChi;

    /**
     * @return the maDocGia
     */
    
    @Override
    public String toString() {
        return this.hoTen;
    }
    public int getMaDocGia() {
        return maDocGia;
    }

    /**
     * @param maDocGia the maDocGia to set
     */
    public void setMaDocGia(int maDocGia) {
        this.maDocGia = maDocGia;
    }

    /**
     * @return the hoTen
     */
    public String getHoTen() {
        return hoTen;
    }

    /**
     * @param hoTen the hoTen to set
     */
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    /**
     * @return the gioiTinh
     */
    public String getGioiTinh() {
        return gioiTinh;
    }

    /**
     * @param gioiTinh the gioiTinh to set
     */
    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    /**
     * @return the diaChi
     */
    public String getDiaChi() {
        return diaChi;
    }

    /**
     * @param diaChi the diaChi to set
     */
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    /**
     * @return the ngaySinh
     */
    public String getNgaySinh() {
        return ngaySinh;
    }

    /**
     * @param ngaySinh the ngaySinh to set
     */
    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

}
