module com.examplel3gl.examen {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.pdfbox;
    requires mysql.connector.j;
    requires poi.ooxml;
    requires poi;


    opens com.examplel3gl.examen to javafx.fxml;
    exports com.examplel3gl.examen;
}