module com.examplel3gl.examen {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.pdfbox;
    requires poi;
    requires poi.ooxml;
    requires mysql.connector.j;


    opens com.examplel3gl.examen to javafx.fxml;
    exports com.examplel3gl.examen;
}