module com.mycompany.quanlythuvien {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    requires javafx.base;
    requires javafx.graphics;

    opens com.mycompany.quanlythuvien to javafx.fxml;
    exports com.mycompany.quanlythuvien;
    exports com.mycompany.pojo;
}
