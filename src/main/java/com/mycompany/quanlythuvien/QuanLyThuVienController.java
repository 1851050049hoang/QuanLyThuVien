package com.mycompany.quanlythuvien;

import com.mycompany.pojo.DocGia;
import com.mycompany.pojo.Sach;
import com.mycompany.pojo.PhieuMuon;
import com.mycompany.service.DocGiaService;
import com.mycompany.service.JdbcUtils;
import com.mycompany.service.SachService;
import com.mycompany.service.PhieuMuonService;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class QuanLyThuVienController implements Initializable {

    //tab1
    @FXML
    private TableView<DocGia> tbDocGia;
    @FXML
    private TextField txtMaDG;
    @FXML
    private TextField txtTenDG;
    @FXML
    private TextField txtGioiTinh;
    @FXML
    private TextField txtDiaChi;
    @FXML
    private TextField txtTimKiemDG;
    @FXML
    private TextField txtNgay;
    @FXML
    private TextField txtThang;
    @FXML
    private TextField txtNam;

    //tab2
    @FXML
    private TableView<Sach> tbSach;
    @FXML
    private TextField txtMaSach;
    @FXML
    private TextField txtTenSach;
    @FXML
    private TextField txtTacGia;
    @FXML
    private TextField txtTheLoai;
    @FXML
    private TextField txtNXB;
    @FXML
    private TextField txtGia;
    @FXML
    private TextField txtSL;
    @FXML
    private TextField txtTinhTrangSach;
    @FXML
    private TextField txtTimKiemSach;

    //tab3
    @FXML
    private TableView<PhieuMuon> tbPhieu;
    @FXML
    private TextField txtMaPhieu;
    @FXML
    private TextField txtMaDGMuon;
    @FXML
    private TextField txtTenDGMuon;
    @FXML
    private TextField txtMaSachMuon;
    @FXML
    private TextField txtTenSachMuon;
    //@FXML
    //private TextField txtNhanVien;
    @FXML
    private TextField txtSLSachMuon;
    @FXML
    private TextField txtTimKiemPhieu;
    @FXML
    private TextField txtTinhTrangPhieu;

    //tab4
    @FXML
    private TextField txtSLDG;
    @FXML
    private TextField txtSLSach;
    @FXML
    private TextField txtSLSachChoMuon;
    @FXML
    private TextField txtNgayMuon;
    @FXML
    private TextField txtHanTra;
    @FXML
    private TextField txtNgayTra;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        loadTBDocGia();
        loadDocGiaData("");

        loadTBSach();
        loadSachData("");

        loadTBPhieu();
        loadPhieuData("");
        try {
            capNhatTinhtrang();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyThuVienController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Ngay muon tra sach
        LocalDate ngayMuon = LocalDate.now();
        LocalDate hanTra = ngayMuon.plusDays(30);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-LL-yyyy");
        txtNgayMuon.setText(ngayMuon.format(dateFormatter));
        txtHanTra.setText(hanTra.format(dateFormatter));

        this.txtTimKiemDG.textProperty().addListener((obj) -> {
            loadDocGiaData(this.txtTimKiemDG.getText());
        });

        this.txtTimKiemSach.textProperty().addListener((obj) -> {
            loadSachData(this.txtTimKiemSach.getText());
        });

        this.txtTimKiemPhieu.textProperty().addListener((obj) -> {
            loadPhieuData(txtTimKiemPhieu.getText());
        });

        this.tbDocGia.setRowFactory(obj -> {
            TableRow r = new TableRow();

            r.setOnMouseClicked(evt -> {
                try {
                    Connection conn = JdbcUtils.getConn();

                    DocGia d = this.tbDocGia.getSelectionModel().getSelectedItem();

                    txtMaDG.setText(Integer.toString(d.getMaDocGia()));
                    txtTenDG.setText(d.getHoTen());
                    txtGioiTinh.setText(d.getGioiTinh());
                    txtDiaChi.setText(d.getDiaChi());
                    txtNgay.setText(d.getNgaySinh().substring(0, 2));
                    txtThang.setText(d.getNgaySinh().substring(3, 5));
                    txtNam.setText(d.getNgaySinh().substring(6, 10));

                    conn.close();

                } catch (SQLException ex) {
                    Logger.getLogger(QuanLyThuVienController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            return r;
        });

        this.tbSach.setRowFactory(obj -> {
            TableRow r = new TableRow();

            r.setOnMouseClicked(evt -> {
                try {
                    Connection conn = JdbcUtils.getConn();

                    Sach s = this.tbSach.getSelectionModel().getSelectedItem();

                    txtMaSach.setText(Integer.toString(s.getMaSach()));
                    txtTenSach.setText(s.getTenSach());
                    txtTacGia.setText(s.getTacGia());
                    txtTheLoai.setText(s.getTheLoai());
                    txtNXB.setText(s.getNhaXuatBan());
                    txtGia.setText(s.getGiaSach().toString());
                    txtSL.setText(Integer.toString(s.getSoLuong()));
                    if (s.getSoLuong() > 0) {
                        txtTinhTrangSach.setText("Còn");
                    } else {
                        txtTinhTrangSach.setText("Hết");
                    }

                    conn.close();

                } catch (SQLException ex) {
                    Logger.getLogger(QuanLyThuVienController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            return r;
        });

        this.tbPhieu.setRowFactory(obj -> {
            TableRow r = new TableRow();

            r.setOnMouseClicked(evt -> {
                try {
                    Connection conn = JdbcUtils.getConn();

                    DocGiaService d = new DocGiaService(conn);

                    SachService s = new SachService(conn);

                    PhieuMuon p = this.tbPhieu.getSelectionModel().getSelectedItem();

                    txtMaPhieu.setText(Integer.toString(p.getMaPhieuMuon()));

                    txtMaDGMuon.setText(Integer.toString(p.getMaDocGia()));
                    txtMaSachMuon.setText(Integer.toString(p.getMaSach()));
                    txtSLSachMuon.setText(Integer.toString(p.getSoLuong()));
                    txtTenDGMuon.setText(p.getTenDocGia());
                    txtTenSachMuon.setText(p.getTenSach());
                    txtTinhTrangPhieu.setText(p.getTinhTrang());
                    txtNgayMuon.setText(p.getNgayMuon());
                    txtHanTra.setText(p.getHanTra());
                    txtNgayTra.setText(p.getNgayTra());
//                    txtNhanVien.setText(Integer.toString(p.getMaNhanVien()));
                    conn.close();

                } catch (SQLException ex) {
                    Logger.getLogger(QuanLyThuVienController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            return r;
        });
    }

    private boolean kiemTra(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public void btnThemDG(ActionEvent evt) throws IOException {

        int nam = LocalDate.now().getYear();

        if (txtTenDG.getText().equals("") || txtGioiTinh.getText().equals("")
                || txtNgay.getText().equals("") || txtThang.getText().equals("")
                || txtNam.getText().equals("") || txtDiaChi.getText().equals("")) {
            Utils.getBox("Bắt buộc nhập nơi chứa dấu *", Alert.AlertType.ERROR).show();
        } else if (kiemTra(txtTenDG.getText()) || kiemTra(txtGioiTinh.getText())) {
            Utils.getBox("Vui lòng kiểm tra lại thông tin", Alert.AlertType.ERROR).show();
        } else if (Integer.parseInt(txtNam.getText()) < 1900 || Integer.parseInt(txtNam.getText()) > nam
                || (nam - Integer.parseInt(txtNam.getText()) < 6 )) {
            Utils.getBox("Vui lòng kiểm tra lại thông tin ngày sinh", Alert.AlertType.ERROR).show();
        } else if (Integer.parseInt(txtThang.getText()) < 1 || Integer.parseInt(txtThang.getText()) > 12) {
            Utils.getBox("Vui lòng kiểm tra lại thông tin ngày sinh", Alert.AlertType.ERROR).show();
        } else if ((Integer.parseInt(txtThang.getText()) == 1 || Integer.parseInt(txtThang.getText()) == 3
                || Integer.parseInt(txtThang.getText()) == 5 || Integer.parseInt(txtThang.getText()) == 7
                || Integer.parseInt(txtThang.getText()) == 8 || Integer.parseInt(txtThang.getText()) == 10
                || Integer.parseInt(txtThang.getText()) == 12) && (Integer.parseInt(txtNgay.getText()) < 1
                || Integer.parseInt(txtNgay.getText()) > 31)) {
            Utils.getBox("Vui lòng kiểm tra lại thông tin ngày sinh", Alert.AlertType.ERROR).show();
        } else if (Integer.parseInt(txtThang.getText()) == 4 || Integer.parseInt(txtThang.getText()) == 6
                || Integer.parseInt(txtThang.getText()) == 9 || Integer.parseInt(txtThang.getText()) == 11
                && Integer.parseInt(txtNgay.getText()) < 1 || Integer.parseInt(txtNgay.getText()) > 30) {
            Utils.getBox("Vui lòng kiểm tra lại thông tin ngày sinh", Alert.AlertType.ERROR).show();
        } else if (Integer.parseInt(txtThang.getText()) == 2 && ((Integer.parseInt(txtNam.getText()) % 400 == 0)
                || (Integer.parseInt(txtNam.getText()) % 4 == 0 && Integer.parseInt(txtNam.getText()) % 100 != 0)) 
                && ((Integer.parseInt(txtNgay.getText()) < 1 || Integer.parseInt(txtNgay.getText()) > 29))) {
            Utils.getBox("Vui lòng kiểm tra lại thông tin ngày sinh", Alert.AlertType.ERROR).show();
        } else if (Integer.parseInt(txtThang.getText()) == 2 && !((Integer.parseInt(txtNam.getText()) % 400 == 0)
                || (Integer.parseInt(txtNam.getText()) % 4 == 0 && Integer.parseInt(txtNam.getText()) % 100 != 0)) 
                && (Integer.parseInt(txtNgay.getText()) < 1 || Integer.parseInt(txtNgay.getText()) > 28)) {
            Utils.getBox("Vui lòng kiểm tra lại thông tin ngày sinh", Alert.AlertType.ERROR).show();
        } else {
            try {
                Connection conn = JdbcUtils.getConn();
                DocGiaService s = new DocGiaService(conn);

                DocGia d = new DocGia();

                d.setHoTen(txtTenDG.getText().trim());
                d.setGioiTinh(txtGioiTinh.getText().trim());

                if (txtNgay.getText().length() == 1) {
                    txtNgay.setText("0".concat(txtNgay.getText()));
                }
                if (txtThang.getText().length() == 1) {
                    txtThang.setText("0".concat(txtThang.getText()));
                }

                d.setNgaySinh(txtNgay.getText().trim().concat("-").concat(txtThang
                        .getText().trim().concat("-")).concat(txtNam.getText().trim()));
                d.setDiaChi(txtDiaChi.getText().trim());

                if (s.themDocGia(d) == true) {
                    Utils.getBox("Thêm thành công", Alert.AlertType.INFORMATION).show();
                    this.loadDocGiaData("");
                    resetDG();
                } else {
                    Utils.getBox("Thêm thất bại", Alert.AlertType.INFORMATION).show();
                }

            } catch (SQLException ex) {
                Logger.getLogger(QuanLyThuVienController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}

    public void btnSuaDG(ActionEvent e) throws SQLException {
        DocGia d = tbDocGia.getSelectionModel().getSelectedItem();
        Connection conn = JdbcUtils.getConn();
        DocGiaService s = new DocGiaService(conn);
        int nam = LocalDate.now().getYear();
        if (d == null) {
            Utils.getBox("Vui lòng chọn dòng cần sửa", Alert.AlertType.INFORMATION).show();
        }else if (txtTenDG.getText().equals("") || txtGioiTinh.getText().equals("")
                || txtNgay.getText().equals("") || txtThang.getText().equals("")
                || txtNam.getText().equals("") || txtDiaChi.getText().equals("")) {
            Utils.getBox("Bắt buộc nhập nơi chứa dấu *", Alert.AlertType.ERROR).show();
        } else if (kiemTra(txtTenDG.getText()) || kiemTra(txtGioiTinh.getText())) {
            Utils.getBox("Vui lòng kiểm tra lại thông tin", Alert.AlertType.ERROR).show();
        } else if (Integer.parseInt(txtNam.getText()) < 1900 || Integer.parseInt(txtNam.getText()) > nam
                || (nam - Integer.parseInt(txtNam.getText()) < 6 )) {
            Utils.getBox("Vui lòng kiểm tra lại thông tin ngày sinh", Alert.AlertType.ERROR).show();
        } else if (Integer.parseInt(txtThang.getText()) < 1 || Integer.parseInt(txtThang.getText()) > 12) {
            Utils.getBox("Vui lòng kiểm tra lại thông tin ngày sinh", Alert.AlertType.ERROR).show();
        } else if ((Integer.parseInt(txtThang.getText()) == 1 || Integer.parseInt(txtThang.getText()) == 3
                || Integer.parseInt(txtThang.getText()) == 5 || Integer.parseInt(txtThang.getText()) == 7
                || Integer.parseInt(txtThang.getText()) == 8 || Integer.parseInt(txtThang.getText()) == 10
                || Integer.parseInt(txtThang.getText()) == 12) && (Integer.parseInt(txtNgay.getText()) < 1
                || Integer.parseInt(txtNgay.getText()) > 31)) {
            Utils.getBox("Vui lòng kiểm tra lại thông tin ngày sinh", Alert.AlertType.ERROR).show();
        } else if (Integer.parseInt(txtThang.getText()) == 4 || Integer.parseInt(txtThang.getText()) == 6
                || Integer.parseInt(txtThang.getText()) == 9 || Integer.parseInt(txtThang.getText()) == 11
                && Integer.parseInt(txtNgay.getText()) < 1 || Integer.parseInt(txtNgay.getText()) > 30) {
            Utils.getBox("Vui lòng kiểm tra lại thông tin ngày sinh", Alert.AlertType.ERROR).show();
        } else if (Integer.parseInt(txtThang.getText()) == 2 && ((Integer.parseInt(txtNam.getText()) % 400 == 0)
                || (Integer.parseInt(txtNam.getText()) % 4 == 0 && Integer.parseInt(txtNam.getText()) % 100 != 0)) 
                && ((Integer.parseInt(txtNgay.getText()) < 1 || Integer.parseInt(txtNgay.getText()) > 29))) {
            Utils.getBox("Vui lòng kiểm tra lại thông tin ngày sinh", Alert.AlertType.ERROR).show();
        } else if (Integer.parseInt(txtThang.getText()) == 2 && !((Integer.parseInt(txtNam.getText()) % 400 == 0)
                || (Integer.parseInt(txtNam.getText()) % 4 == 0 && Integer.parseInt(txtNam.getText()) % 100 != 0)) 
                && (Integer.parseInt(txtNgay.getText()) < 1 || Integer.parseInt(txtNgay.getText()) > 28)) {
            Utils.getBox("Vui lòng kiểm tra lại thông tin ngày sinh", Alert.AlertType.ERROR).show();
        } else {
            Utils.getBox("Bạn có chắc chắn muốn sửa không?", Alert.AlertType.CONFIRMATION)
                    .showAndWait().ifPresent(bt -> {
                if (bt == ButtonType.OK) {

                    DocGia dd = new DocGia();

                    dd.setMaDocGia(Integer.parseInt(txtMaDG.getText()));
                    dd.setHoTen(txtTenDG.getText());
                    dd.setGioiTinh(txtGioiTinh.getText());
                    
                    if(txtNgay.getText().length()==1)
                        txtNgay.setText("0".concat(txtNgay.getText()));
                    if(txtThang.getText().length()==1)
                        txtThang.setText("0".concat(txtThang.getText()));
                    dd.setNgaySinh(txtNgay.getText().trim().concat("-").concat(txtThang
                    .getText().trim().concat("-")).concat(txtNam.getText().trim()));
                    dd.setDiaChi(txtDiaChi.getText());

                    try {
                        if (s.suaDocGia(d, dd) == true) {
                            Utils.getBox("Sửa thành công", Alert.AlertType.INFORMATION).show();
                            
                            loadDocGiaData("");
                            resetDG();

                        } else {
                            Utils.getBox("Lỗi", Alert.AlertType.ERROR).show();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(QuanLyThuVienController

.class
.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });
            conn.close();
        }
    }

    public void btnXoaDG(ActionEvent e) throws SQLException {

        DocGia selected = tbDocGia.getSelectionModel().getSelectedItem();
        Connection conn = JdbcUtils.getConn();
        DocGiaService s = new DocGiaService(conn);

        if (selected == null) {
            Utils.getBox("Vui lòng chọn dòng cần xóa", Alert.AlertType.INFORMATION).show();
        } else {
            Utils.getBox("Bạn chắc chắn muốn xóa không?", Alert.AlertType.CONFIRMATION).showAndWait().ifPresent(bt -> {
                if (bt == ButtonType.OK) {
                    try {
                        if (s.xoaDocGia(selected.getMaDocGia())) {
                            Utils.getBox("SUCCESSFUL", Alert.AlertType.INFORMATION).show();
                            
                            loadDocGiaData("");
                            resetDG();

                        } else {
                            Utils.getBox("FAILED", Alert.AlertType.ERROR).show();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(QuanLyThuVienController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }
        conn.close();
    }
    
    public void resetDG(){
        txtMaDG.clear();
        txtTenDG.clear();
        txtGioiTinh.clear();
        txtDiaChi.clear();
        txtNgay.clear();
        txtThang.clear();
        txtNam.clear();
    }
    
    public void resetSach(){
        txtMaSach.clear();
        txtTenSach.clear();
        txtTacGia.clear();
        txtTheLoai.clear();
        txtNXB.clear();
        txtGia.clear();
        txtSL.clear();
        txtTinhTrangSach.clear();
    }
    
    public void resetPM(){
        txtMaPhieu.clear();
        txtMaDGMuon.clear();
        txtMaSachMuon.clear();
        txtSLSachMuon.clear();
        txtTenDGMuon.clear();
        txtTenSachMuon.clear();
        txtTinhTrangPhieu.clear();
        LocalDate ngayMuon = LocalDate.now();
        LocalDate hanTra = ngayMuon.plusDays(30);
        
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-LL-yyyy");
        txtNgayMuon.setText(ngayMuon.format(dateFormatter));
        txtHanTra.setText(hanTra.format(dateFormatter));
    }

    public void btnResetDG(ActionEvent e) {
        resetDG();
    }

    public void btnResetSach(ActionEvent e) {
        resetSach();
    }

    public void btnResetMuonSach(ActionEvent e) {
        resetPM();
    }

    public void loadDocGiaData(String kw) {
        try {
            this.tbDocGia.getItems().clear();

            Connection conn = JdbcUtils.getConn();
            DocGiaService ds = new DocGiaService(conn);

            this.tbDocGia.setItems(FXCollections.observableList(ds.getDocGia(kw)));

            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(QuanLyThuVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadTBDocGia() {
        TableColumn colId = new TableColumn("Mã ĐG");
        colId.setCellValueFactory(new PropertyValueFactory("maDocGia"));
        colId.setPrefWidth(100);

        TableColumn colName = new TableColumn("Tên ĐG");
        colName.setCellValueFactory(new PropertyValueFactory("hoTen"));
        colName.setPrefWidth(300);

        TableColumn colGT = new TableColumn("Giới Tính");
        colGT.setCellValueFactory(new PropertyValueFactory("gioiTinh"));
        colGT.setPrefWidth(200);

        TableColumn colNS = new TableColumn("Năm Sinh");
        colNS.setCellValueFactory(new PropertyValueFactory("ngaySinh"));
        colNS.setPrefWidth(200);

        TableColumn colDC = new TableColumn("Địa Chỉ");
        colDC.setCellValueFactory(new PropertyValueFactory("diaChi"));
        colDC.setPrefWidth(548);

        this.tbDocGia.getColumns().addAll(colId, colName, colGT, colNS, colDC);
    }

    public void btnThemSach(ActionEvent evt) throws SQLException {
        Connection conn = JdbcUtils.getConn();
        SachService ds = new SachService(conn);
        
        if("".equals(txtTenSach.getText()) ||"".equals(txtTacGia.getText())
        || "".equals(txtTheLoai.getText()) || "".equals(txtNXB.getText()) || "".equals(txtGia.getText())
        || "".equals(txtSL.getText()) || "".equals(txtTinhTrangSach.getText()))
            Utils.getBox("Vui lòng nhập đầy đủ thông tin", Alert.AlertType.INFORMATION).show();
        else if(!kiemTra(txtSL.getText()) || !kiemTra(txtGia.getText()) || kiemTra(txtTheLoai.getText()))
            Utils.getBox("Vui lòng kiểm tra lại thông tin", Alert.AlertType.INFORMATION).show();
        else {
            try {
                Sach s = new Sach();
                s.setTenSach(txtTenSach.getText().trim());
                s.setTacGia(txtTacGia.getText().trim());
                s.setTheLoai(txtTheLoai.getText().trim());
                s.setNhaXuatBan(txtNXB.getText().trim());
                s.setGiaSach(new BigDecimal(txtGia.getText().trim()));
                s.setSoLuong(Integer.parseInt(txtSL.getText().trim()));

                if (ds.themSach(s) == true) {
                    Utils.getBox("Thêm thành công", Alert.AlertType.INFORMATION).show();
                    this.loadSachData("");
                    resetSach();
                } else {
                    Utils.getBox("Thêm thất bại", Alert.AlertType.INFORMATION).show();
                }

            } catch (SQLException ex) {
                Logger.getLogger(QuanLyThuVienController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void btnXoaSach(ActionEvent e) throws SQLException {

        Sach selected = tbSach.getSelectionModel().getSelectedItem();
        Connection conn = JdbcUtils.getConn();
        SachService s = new SachService(conn);

        if (selected == null) {
            Utils.getBox("Vui lòng chọn dòng cần xóa", Alert.AlertType.INFORMATION).show();
        } else {
            Utils.getBox("Bạn có chắc chắn muốn xóa không?", Alert.AlertType.CONFIRMATION)
                    .showAndWait().ifPresent(bt -> {
                if (bt == ButtonType.OK) 

                    try {
                        if (s.xoaSach(selected.getMaSach())) {
                            Utils.getBox("Xóa hoàn tất", Alert.AlertType.INFORMATION).show();
                            
                            loadSachData("");
                            resetSach();

                        } else {
                            Utils.getBox("Lỗi", Alert.AlertType.ERROR).show();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(QuanLyThuVienController

.class
.getName()).log(Level.SEVERE, null, ex);
                    }
                    });
        }
    }
    
    public void btnSuaSach(ActionEvent e) throws SQLException {
        Sach s = tbSach.getSelectionModel().getSelectedItem();
        Connection conn = JdbcUtils.getConn();
        SachService sss = new SachService(conn);

        if (s == null) {
            Utils.getBox("Vui lòng chọn dòng cần sửa", Alert.AlertType.INFORMATION).show();
        } else {
            Utils.getBox("Bạn có chắc chắn muốn sửa không?", Alert.AlertType.CONFIRMATION)
                    .showAndWait().ifPresent(bt -> {
                if (bt == ButtonType.OK) {
                    try {
                        Sach ss = new Sach();

                        ss.setMaSach(Integer.parseInt(txtMaSach.getText()));
                        ss.setTenSach(txtTenSach.getText());
                        ss.setTacGia(txtTacGia.getText());
                        ss.setTheLoai(txtTheLoai.getText());
                        ss.setNhaXuatBan(txtNXB.getText());
                        ss.setGiaSach(new BigDecimal(txtGia.getText()));
                        ss.setSoLuong(Integer.parseInt(txtSL.getText()));

                        if (sss.suaSach(s, ss) == true) {
                            Utils.getBox("Sửa thành công", Alert.AlertType.INFORMATION).show();
                            loadSachData("");
                            resetSach();
                        } else {
                            Utils.getBox("Lỗi", Alert.AlertType.ERROR).show();
                        }

                        conn.close();

                    } catch (SQLException ex) {
                        Logger.getLogger(QuanLyThuVienController

.class
.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }
    }
    
    public void loadSachData(String kw) {
        try {
            this.tbSach.getItems().clear();

            Connection conn = JdbcUtils.getConn();
            SachService ds = new SachService(conn);

            this.tbSach.setItems(FXCollections.observableList(ds.getSach(kw)));
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyThuVienController

.class
.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadTBSach() {
        TableColumn colId = new TableColumn("Mã Sách");
        colId.setCellValueFactory(new PropertyValueFactory("maSach"));
        colId.setPrefWidth(100);

        TableColumn colName = new TableColumn("Tên Sách");
        colName.setCellValueFactory(new PropertyValueFactory("tenSach"));

        TableColumn colTG = new TableColumn("Tác Giả");
        colTG.setCellValueFactory(new PropertyValueFactory("tacGia"));
        colTG.setPrefWidth(300);

        TableColumn colTL = new TableColumn("Thể Loại");
        colTL.setCellValueFactory(new PropertyValueFactory("theLoai"));
        colTL.setPrefWidth(200);

        TableColumn colNXB = new TableColumn("NXB");
        colNXB.setCellValueFactory(new PropertyValueFactory("nhaXuatBan"));
        colNXB.setPrefWidth(200);

        TableColumn colGia = new TableColumn("Giá");
        colGia.setCellValueFactory(new PropertyValueFactory("giaSach"));
        colGia.setPrefWidth(135);

        TableColumn colSL = new TableColumn("SL");
        colSL.setCellValueFactory(new PropertyValueFactory("soLuong"));
        colSL.setPrefWidth(100);

//        TableColumn colTT = new TableColumn("Tình Trạng");
//        colTT.setCellValueFactory(new PropertyValueFactory("tinhTrang"));
        this.tbSach.getColumns().addAll(colId, colName, colTG, colTL, colNXB, colGia, colSL);
    }

    public void loadPhieuData(String maPhieu) {
        try {
            this.tbPhieu.getItems().clear();

            Connection conn = JdbcUtils.getConn();
            PhieuMuonService ps = new PhieuMuonService(conn);

            this.tbPhieu.setItems(FXCollections.observableList(ps.getPhieuMuon(maPhieu)));
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyThuVienController

.class
.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadTBPhieu() {
        TableColumn colMaPhieu = new TableColumn("MPM");
        colMaPhieu.setCellValueFactory(new PropertyValueFactory("maPhieuMuon"));
        colMaPhieu.setPrefWidth(50);

        TableColumn colMaDG = new TableColumn("MĐG");
        colMaDG.setCellValueFactory(new PropertyValueFactory("maDocGia"));
        colMaDG.setPrefWidth(50);

        TableColumn colTenDG = new TableColumn("Tên ĐG");
        colTenDG.setCellValueFactory(new PropertyValueFactory("tenDocGia"));
        colTenDG.setPrefWidth(250);

        TableColumn colTenSach = new TableColumn("Tên Sách");
        colTenSach.setCellValueFactory(new PropertyValueFactory("tenSach"));
        colTenSach.setPrefWidth(280);

        TableColumn colSL = new TableColumn("SL");
        colSL.setCellValueFactory(new PropertyValueFactory("soLuong"));
        colSL.setPrefWidth(50);

        TableColumn colTT = new TableColumn("Tình Trạng");
        colTT.setCellValueFactory(new PropertyValueFactory("tinhTrang"));
        colTT.setPrefWidth(140);
        
        TableColumn colNM = new TableColumn("Ngày Mượn");
        colNM.setCellValueFactory(new PropertyValueFactory("ngayMuon"));
        colNM.setPrefWidth(140);
        
        TableColumn colHT = new TableColumn("Hạn Trả");
        colHT.setCellValueFactory(new PropertyValueFactory("hanTra"));
        colHT.setPrefWidth(140);
        
        TableColumn colNT = new TableColumn("Ngày Trả");
        colNT.setCellValueFactory(new PropertyValueFactory("ngayTra"));
        colNT.setPrefWidth(140);
        
        TableColumn colGC = new TableColumn("Ghi Chú");
        colGC.setCellValueFactory(new PropertyValueFactory("ghiChu"));
        colGC.setPrefWidth(140);
        

        this.tbPhieu.getColumns().addAll(colMaPhieu, colMaDG, colTenDG, 
                colTenSach, colSL, colNM, colHT, colNT, colTT, colGC);
    }

    public void btnThemPhieuMuon(ActionEvent evt) throws SQLException {
        
        Connection conn = JdbcUtils.getConn();
        PhieuMuonService ds = new PhieuMuonService(conn);
        SachService ss = new SachService(conn);
        if("".equals(txtMaDGMuon.getText()) ||"".equals(txtMaSachMuon.getText())
        || "".equals(txtTenDGMuon.getText()) || "".equals(txtTenSachMuon.getText()) 
        || "".equals(txtSLSachMuon.getText()))
            Utils.getBox("Vui lòng nhập đầy đủ thông tin", Alert.AlertType.INFORMATION).show();
        else if(!kiemTra(txtSLSachMuon.getText()) || Integer.parseInt(txtSLSachMuon.getText()) < 1)
            Utils.getBox("Vui lòng kiểm tra lại thông tin", Alert.AlertType.INFORMATION).show();
        else if(Integer.parseInt(txtSLSachMuon.getText())
                > ss.getSLSachById(Integer.parseInt(txtMaSachMuon.getText())))
            Utils.getBox("Số lượng sách còn lại không đủ", Alert.AlertType.INFORMATION).show();
        else {
            try {
                PhieuMuon p = new PhieuMuon();
                Sach s = new Sach();

                p.setMaDocGia(Integer.parseInt(txtMaDGMuon.getText().trim()));
                p.setMaSach(Integer.parseInt(txtMaSachMuon.getText().trim()));
                p.setSoLuong(Integer.parseInt(txtSLSachMuon.getText().trim()));
                p.setTenDocGia(txtTenDGMuon.getText().trim());
                p.setTenSach(txtTenSachMuon.getText().trim());
                p.setNgayMuon(txtNgayMuon.getText().trim());
                p.setHanTra(txtHanTra.getText().trim());
                //p.setMaNhanVien(Integer.parseInt(txtMaDGMuon.getText()));

                s.setMaSach(Integer.parseInt(txtMaSachMuon.getText().trim()));
                s.setSoLuong(ss.getSLSachById(p.getMaSach()) - p.getSoLuong());

                if (ds.themPhieuMuon(p) == true && ss.suaSLSach(s) == true) {
                    Utils.getBox("Thêm thành công", Alert.AlertType.INFORMATION).show();
                    this.loadPhieuData("");
                    this.loadSachData("");
                    resetPM();
                } else {
                    Utils.getBox("Thêm thất bại", Alert.AlertType.INFORMATION).show();
                }

            } catch (SQLException ex) {
                Logger.getLogger(QuanLyThuVienController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void btnKTMaDG(ActionEvent evt) {
        if ("".equals(txtMaDGMuon.getText())) {
            Utils.getBox("Chưa nhập mã độc giả", Alert.AlertType.INFORMATION).show();
        }else if(!kiemTra(txtMaDGMuon.getText()))
            Utils.getBox("Vui lòng nhập đúng định dạng mã độc giả", Alert.AlertType.INFORMATION).show();
        else
            try {
            Connection conn = JdbcUtils.getConn();
            DocGiaService d = new DocGiaService(conn);

            if ("0".equals(d.getTenDGById(Integer.parseInt(txtMaDGMuon.getText())))) {
                Utils.getBox("Sai mã độc giả hoặc độc giả chưa đăng kí", Alert.AlertType.INFORMATION).show();
            } else {
                txtTenDGMuon.setText(d.getTenDGById(Integer.parseInt(txtMaDGMuon.getText())));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyThuVienController

.class
.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void btnKTMaSach(ActionEvent evt) {
        if ("".equals(txtMaSachMuon.getText())) {
            Utils.getBox("Chưa nhập mã độc giả", Alert.AlertType.INFORMATION).show();
        }else if(!kiemTra(txtMaSachMuon.getText()))
            Utils.getBox("Vui lòng nhập đúng định dạng mã độc giả", Alert.AlertType.INFORMATION).show();
        else
            try {
            Connection conn = JdbcUtils.getConn();
            SachService s = new SachService(conn);

            if ("0".equals(s.getTenSachById(Integer.parseInt(txtMaSachMuon.getText())))) {
                Utils.getBox("Sai mã sách", Alert.AlertType.INFORMATION).show();
            } else {
                txtTenSachMuon.setText(s.getTenSachById(Integer.parseInt(txtMaSachMuon.getText())));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyThuVienController

.class
.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void btnTra(ActionEvent evt) throws SQLException {
        
        PhieuMuon p = tbPhieu.getSelectionModel().getSelectedItem();
        Connection conn = JdbcUtils.getConn();
        PhieuMuonService ds = new PhieuMuonService(conn);
        
        LocalDate ngayTra = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-LL-yyyy");
        String nt = ngayTra.format(dateFormatter);

        if (p == null) {
            Utils.getBox("Vui lòng chọn hóa đơn cần trả", Alert.AlertType.INFORMATION).show();
        } else {
            SachService ss = new SachService(conn);
            Sach s = new Sach();

            s.setMaSach(Integer.parseInt(txtMaSachMuon.getText()));
            s.setSoLuong(ss.getSLSachById(p.getMaSach()) + p.getSoLuong());

            if (ds.traSach(p.getMaPhieuMuon(), nt) == true && ss.suaSLSach(s) == true) {
                Utils.getBox("Cập nhật thành công", Alert.AlertType.INFORMATION).show();
                this.loadPhieuData("");
                this.loadSachData("");
                resetPM();
            } else {
                Utils.getBox("Sửa thất bại", Alert.AlertType.INFORMATION).show();
            }
        }
    }

    public void btnXoaPhieu(ActionEvent e) throws SQLException {
        
        PhieuMuon p = tbPhieu.getSelectionModel().getSelectedItem();
        Connection conn = JdbcUtils.getConn();
        PhieuMuonService ss = new PhieuMuonService(conn);
        if (p == null) {
            Utils.getBox("Vui lòng chọn dòng cần xóa", Alert.AlertType.INFORMATION).show();
        }else if(p.getTinhTrang().equals("Chưa trả")){
            Utils.getBox("Sách chưa trả, không được phép xóa hóa đơn", Alert.AlertType.WARNING).show();
        }
        else {
            Utils.getBox("Ban chac chan xoa khong?", Alert.AlertType.CONFIRMATION)
                    .showAndWait().ifPresent(bt -> {
                if (bt == ButtonType.OK) 

                    try {
                        if (ss.xoaPhieu(p.getMaPhieuMuon())) {
                            Utils.getBox("SUCCESSFUL", Alert.AlertType.INFORMATION).show();
                            
                            loadPhieuData("");
                            resetPM();

                        } else {
                            Utils.getBox("FAILED", Alert.AlertType.ERROR).show();
                        }
                } catch (SQLException ex) {
                    Logger.getLogger(QuanLyThuVienController

.class
.getName()).log(Level.SEVERE, null, ex);
                }

            });
            conn.close();
        }
    }

    public void btnSuaPhieu(ActionEvent e) throws SQLException {
        PhieuMuon p = tbPhieu.getSelectionModel().getSelectedItem();
        Connection conn = JdbcUtils.getConn();
        if (p == null) {
            Utils.getBox("Vui lòng chọn dòng cần xóa", Alert.AlertType.INFORMATION).show();
        } else {
            Utils.getBox("Bạn có chắc chắn muốn sửa không?", Alert.AlertType.CONFIRMATION)
                    .showAndWait().ifPresent(bt -> {
                if (bt == ButtonType.OK) {
                        
                    PhieuMuon ps = new PhieuMuon();
                    PhieuMuonService pms = new PhieuMuonService(conn);
                    
                    Sach s = new Sach();
                    SachService ss = new SachService(conn);

                    ps.setMaPhieuMuon(Integer.parseInt(txtMaPhieu.getText()));
                    ps.setMaDocGia(Integer.parseInt(txtMaDGMuon.getText()));
                    ps.setTenDocGia(txtTenDGMuon.getText());
                    ps.setMaSach(Integer.parseInt(txtMaSachMuon.getText()));
                    ps.setTenSach(txtTenSachMuon.getText());
                    ps.setSoLuong(Integer.parseInt(txtSLSachMuon.getText()));
                    ps.setTinhTrang(txtTinhTrangPhieu.getText());
                    ps.setTenSach(txtTenSachMuon.getText());
                    ps.setNgayMuon(txtNgayMuon.getText());
                    ps.setHanTra(txtHanTra.getText());

                    if (p.getSoLuong() < ps.getSoLuong()) {
                        s.setMaSach(p.getMaSach());
                        try {
                            s.setSoLuong(ss.getSLSachById(s.getMaSach())
                                    - (ps.getSoLuong() - p.getSoLuong()));
                        } catch (SQLException ex) {
                            Logger.getLogger(QuanLyThuVienController

.class
.getName()).log(Level.SEVERE, null, ex);
                        }

                        try {
                            ss.suaSLSach(s);
                        } catch (SQLException ex) {
                            Logger.getLogger(QuanLyThuVienController

.class
.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (p.getSoLuong() > ps.getSoLuong()) {
                        s.setMaSach(p.getMaSach());
                        try {
                            s.setSoLuong(ss.getSLSachById(s.getMaSach())
                                    + (p.getSoLuong() - ps.getSoLuong()));
                        } catch (SQLException ex) {
                            Logger.getLogger(QuanLyThuVienController

.class
.getName()).log(Level.SEVERE, null, ex);
                        }

                        try {
                            ss.suaSLSach(s);
                        } catch (SQLException ex) {
                            Logger.getLogger(QuanLyThuVienController

.class
.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    try {
                        if (pms.suaPhieu(p, ps) == true) {
                            Utils.getBox("Sửa thành công", Alert.AlertType.INFORMATION).show();
                            loadPhieuData("");
                            loadSachData("");
                            
                            resetPM();
                        } else {
                            Utils.getBox("Lỗi", Alert.AlertType.ERROR).show();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(QuanLyThuVienController

.class
.getName()).log(Level.SEVERE, null, ex);
                    }
            }
        });
        conn.close();
        }
    }
    
    public void capNhatTinhtrang() throws SQLException {
        Connection conn = JdbcUtils.getConn();
        PhieuMuonService ps = new PhieuMuonService(conn);
        
        ps.capNhatTinhTrang();
        loadPhieuData("");
    }

    public void btnThongKe(ActionEvent e) throws SQLException {
        Connection conn = JdbcUtils.getConn();
        DocGiaService ds = new DocGiaService(conn);
        SachService ss = new SachService(conn);
        PhieuMuonService ps = new PhieuMuonService(conn);

        txtSLDG.setText(Integer.toString(ds.demDocGia()));
        txtSLSach.setText(Integer.toString(ss.demSLSach()));
        txtSLSachChoMuon.setText(Integer.toString(ps.demSLSachChoMuon()));
    }
    
    public void thoat(ActionEvent evt){
        Utils.getBox("Bạn có chắc chắn muốn thoát không?", Alert.AlertType.CONFIRMATION)
                .showAndWait().ifPresent(bt -> {
            if (bt == ButtonType.OK) {
                System.exit(0);
            }
        });
    }
}
