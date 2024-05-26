module org.example.proyecto {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires java.desktop;


    opens org.example.proyecto to javafx.fxml;
    exports org.example.proyecto;
    exports org.example.proyecto.gui;
    opens org.example.proyecto.gui to javafx.fxml;
    opens org.example.proyecto.model.account to javafx.base;
    exports org.example.proyecto.model.account to javafx.base;
    opens org.example.proyecto.model.client to javafx.base;
    exports org.example.proyecto.model.client to javafx.base;
    opens org.example.proyecto.model.booking to javafx.base;
    exports org.example.proyecto.model.booking to javafx.base;
}