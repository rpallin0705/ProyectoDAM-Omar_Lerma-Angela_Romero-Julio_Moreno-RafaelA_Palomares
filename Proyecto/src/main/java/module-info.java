module org.example.proyecto {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    opens org.example.proyecto to javafx.fxml;
    exports org.example.proyecto;
    exports org.example.proyecto.gui;
    opens org.example.proyecto.gui to javafx.fxml;
}