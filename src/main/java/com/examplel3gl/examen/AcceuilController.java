package com.examplel3gl.examen;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AcceuilController implements Initializable {

    @FXML
    private AnchorPane dynamique;
    @FXML
    private ImageView logo;
        @FXML
    private Label MenuBack;
    @FXML
    private Label MenuBack1;

    @FXML
    private Button categoriebtn;

    @FXML
    private ImageView categorylogo;

    @FXML
    private ImageView doclogo;

    @FXML
    private Button extraire_ducmentbtn;

    @FXML
    private ImageView fermer;
    @FXML
    private ImageView menu;
    @FXML
    private ImageView menu1;

    @FXML
    private Button homebtn;

    @FXML
    private ImageView homelogo;
    @FXML
    private ImageView ic1;

    @FXML
    private ImageView ic2;

    @FXML
    private ImageView ic3;

    @FXML
    private ImageView ic4;

    @FXML
    private ImageView ic5;
    @FXML
    private ImageView prodlogo;

    @FXML
    private Button produitbtn;

    @FXML
    private AnchorPane slider;

    @FXML
    private Button statistiquebtn;

    @FXML
    private ImageView statlogo;
    private Connection connection;

    @FXML
    void categorieAction(ActionEvent event) throws IOException {
        Parent fxml=FXMLLoader.load(getClass().getResource("categorie.fxml"));
        dynamique.getChildren().removeAll();
        dynamique.getChildren().setAll(fxml);
    }

    @FXML
    void extraire_ducmentAction(ActionEvent event) throws IOException {

        Parent fxml=FXMLLoader.load(getClass().getResource("extraire_document.fxml"));
        dynamique.getChildren().removeAll();
        dynamique.getChildren().setAll(fxml);
    }

    @FXML
    void homeAction(ActionEvent event) throws IOException {
        Parent fxml=FXMLLoader.load(getClass().getResource("home.fxml"));
dynamique.getChildren().removeAll();
dynamique.getChildren().setAll(fxml);
    }

    @FXML
    void produitAction(ActionEvent event) throws IOException {
        Parent fxml=FXMLLoader.load(getClass().getResource("produit.fxml"));
        dynamique.getChildren().removeAll();
        dynamique.getChildren().setAll(fxml);
    }

    @FXML
    void statistiqueAction(ActionEvent event) throws IOException {
        Parent fxml=FXMLLoader.load(getClass().getResource("statistique.fxml"));
        dynamique.getChildren().removeAll();
        dynamique.getChildren().setAll(fxml);

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File imagesfile = new File("images/se-initial-letter-gold-calligraphic-feminine-floral-hand-drawn-heraldic-monogram-antique-vintage-style-luxury-logo-design-premium-vector.jpg");
        Image pImage = new Image(imagesfile.toURI().toString());
        logo.setImage(pImage);
        File imagesmenufile = new File("images/télécharger.jpg");
        Image pmenuImage = new Image(imagesmenufile.toURI().toString());
        menu.setImage(pmenuImage);
        File imagesmenu1file = new File("images/télécharger.jpg");
        Image pmenu1Image = new Image(imagesmenu1file.toURI().toString());
        menu1.setImage(pmenu1Image);
        File imagesfermerfile = new File("images/fermer.jpg");
        Image pfermerImage = new Image(imagesfermerfile.toURI().toString());
        fermer.setImage(pfermerImage);
        File imagesic1file = new File("images/icons8-home-100.png");
        Image Iic1 = new Image(imagesic1file.toURI().toString());
        ic1.setImage(Iic1);
        File imagesic2file = new File("images/icons8-stock-48.png");
        Image Iic2 = new Image(imagesic2file.toURI().toString());
        ic2.setImage(Iic2);
        File imagesic3file = new File("images/icons8-choice-64.png");
        Image Iic3 = new Image(imagesic3file.toURI().toString());
        ic3.setImage(Iic3);
        File imagesic4file = new File("images/icons8-analysis-64.png");
        Image Iic4 = new Image(imagesic4file.toURI().toString());
        ic4.setImage(Iic4);
        File imagesic5file = new File("images/icons8-winrar-50.png");
        Image Iic5 = new Image(imagesic5file.toURI().toString());
        ic5.setImage(Iic5);

        fermer.setOnMouseClicked(event -> {
            System.exit(0);
        });
        MenuBack.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(0);
            slide.play();

            slider.setTranslateX(-176);

            slide.setOnFinished((ActionEvent e)-> {
                MenuBack.setVisible(false);
                MenuBack1.setVisible(true);
            });
        });

        MenuBack1.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(-176);
            slide.play();

            slider.setTranslateX(0);

            slide.setOnFinished((ActionEvent e)-> {
                MenuBack.setVisible(true);
                MenuBack1.setVisible(false);
            });
        });

        ValidationLogin();


    }

    public void ValidationLogin(){

        this.connection = new BD().getConnection();
        String verifylogin = "SELECT * FROM produit";
        try {
            Statement statement =connection.createStatement();
            ResultSet queryResult= statement.executeQuery(verifylogin);
            while (queryResult.next()){
               int p= queryResult.getInt("quantite");
                if (p<5){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Alert");
                    String pr = queryResult.getString("libelle");

                    // Header Text: null
                    alert.setHeaderText(null);
                    alert.setContentText("Produit "+pr+" a un stock insuffisant");

                    alert.showAndWait();

                }else {
                }


            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        };
    }



}
