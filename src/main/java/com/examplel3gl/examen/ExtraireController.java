package com.examplel3gl.examen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.apache.pdfbox.pdmodel.font.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;

import com.mysql.cj.BindValue;
import com.sun.javafx.css.StyleManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;




import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.examplel3gl.examen.Produitrepository.getCat;
import static com.examplel3gl.examen.Produitrepository.getCat1;


public class ExtraireController implements Initializable {
    @FXML
    private Button categoriebtn;

    @FXML
    private ImageView categorylogo;

    @FXML
    private ImageView doclogo;

    @FXML
    private Button excel;

    @FXML
    private Button extraire_ducmentbtn;

    @FXML
    private Button homebtn;

    @FXML
    private ImageView homelogo;



    @FXML
    private Button pdf;

    @FXML
    private ImageView prodlogo;

    @FXML
    private Button produitbtn;

    @FXML
    private Button statistiquebtn;

    @FXML
    private ImageView statlogo;
    private Connection connection;
    private BindValue resultSet;
    private StyleManager PdfWriter;
    private com.examplel3gl.examen.Produitrepository Produitrepository;


    private void generatePdf() {
        this.connection = new BD().getConnection();

        try {

            String sql =  "SELECT p.id, p.libelle, p.quantite,p.prix_unitaire,c.libelle as libellec FROM produit as p,categorie as c where p.idcategorie = c.id";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.beginText(); // Ajoutez cette ligne pour commencer le texte

            float yPosition = page.getMediaBox().getHeight() - 50;
            contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 14); // Use PDType1Font.HELVETICA_BOLD

            contentStream.newLineAtOffset(50, yPosition);
            contentStream.showText("Product List:");
// ...


            while (resultSet.next()) {
                yPosition -= 20;
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("ID: " + resultSet.getInt("id") +
                        ", Name: " + resultSet.getString("libelle") +
                        ", Quantity: " + resultSet.getInt("quantite") +
                        ", prix_unitaire: " + resultSet.getInt("prix_unitaire") +

                        ", Categorie: " + resultSet.getString("libellec"));
            }

            contentStream.close();
            document.save("product_list.pdf");
            document.close();

            System.out.println("PDF generated successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void pdfAction(ActionEvent event) throws IOException {

        generatePdf();

        VBox root = new VBox(10);
        root.getChildren().add(pdf);

    }

  

    @FXML
    void produitAction(ActionEvent event) {

    }

    @FXML
    void statistiqueAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void excelAction(ActionEvent actionEvent) {
        this.Produitrepository = new Produitrepository();
        ObservableList<String> list = FXCollections.observableArrayList();
        ObservableList<String> list1 = FXCollections.observableArrayList();
        list = getCat();
        list1 = getCat1();

        list.forEach((libelle) -> System.out.println(libelle));

        list1.forEach((libelle) -> System.out.println(libelle));
        int i = 0;
        int j = list.size();
        j = j - 1;
        System.out.println(j);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Product Data");

        // Add column headers
        int rowNum = 1;
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Product libelle");
        headerRow.createCell(1).setCellValue("Product number");
        for (i = 0; i <= j; i++) {
            int p = Integer.parseInt(list1.get(i));
            String l = list.get(i);
            Row dataRow = sheet.createRow(rowNum++);
            dataRow.createCell(0).setCellValue(l);
            dataRow.createCell(1).setCellValue(p);


            // Remplissez les données dans le classeur
            // Exemple : ajoutez des valeurs à partir du TableView
            // ...

            // Sauvegardez le classeur dans un fichier
            try (FileOutputStream fileOut = new FileOutputStream("donnees.xlsx")) {
                workbook.write(fileOut);
                System.out.println("Données exportées avec succès vers donnees.xlsx");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
