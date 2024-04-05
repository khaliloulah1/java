package com.examplel3gl.examen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private Connection connection;
    private Node buttonSave;

    public static class Login {
    }
    @FXML
    private TextField logintext;

    @FXML
    private PasswordField passwordtext;
    @FXML
    private Label loginalert;
    @FXML
    private Button se_connecter;

    @FXML
    private Button sinscrire;
    @FXML
    private ImageView guitview;



    @FXML
    void LoginButtonOnAction(ActionEvent event) {
        if(!logintext.getText().isBlank() && !passwordtext.getText().isBlank()){
            this.connection = new BD().getConnection();
            String verifylogin = "SELECT count(1) FROM user WHERE login = '" + logintext.getText() + "' AND password = '" + passwordtext.getText() + "'";
            try {
                Statement statement =connection.createStatement();
                ResultSet queryResult= statement.executeQuery(verifylogin);
                while (queryResult.next()){
                    if (queryResult.getInt(1)==1){
                        loginalert.setText("congrat");
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.close();
                        HomePage();
                    }else {
                        showAlertWithoutHeaderText();
                    }


                }
            }catch (Exception e){
                e.printStackTrace();
                e.getCause();
            };
      }else {
          loginalert.setText("Entre ton Login ou mot de passe correct");


      }
    }

    @FXML
    void registerbuttonAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    createaccountForm();
    }
    public void ValidationLogin(){

        this.connection = new BD().getConnection();
        String verifylogin = "SELECT count(1) FROM user WHERE login = '" + logintext.getText() + "' AND password = '" + passwordtext.getText() + "'";
        try {
            Statement statement =connection.createStatement();
            ResultSet queryResult= statement.executeQuery(verifylogin);
            while (queryResult.next()){
                if (queryResult.getInt(1)==1){
                    loginalert.setText("congrat");

                    HomePage();
                }else {
                    showAlertWithoutHeaderText();
                }


            }
        }catch (Exception e){
e.printStackTrace();
e.getCause();
        };
    }
    private void showAlertWithoutHeaderText() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Connection");

        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText("« Login ou mot de passe incorrect!");

        alert.showAndWait();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
File imagesfile = new File("images/OIP.jpg");
Image pImage = new Image(imagesfile.toURI().toString());
guitview.setImage(pImage);
    }

    public void createaccountForm(){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("register.fxml"));
            Stage RegisterStage = new Stage();

            RegisterStage.initStyle(StageStyle.DECORATED);
            RegisterStage.setScene(new Scene(fxmlLoader.load(), 600, 400));
            RegisterStage.show();



        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        };
    }
    public void HomePage(){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("acceuil.fxml"));
            Stage RegisterStage = new Stage();
            RegisterStage.initStyle(StageStyle.UNDECORATED);
            RegisterStage.setScene(new Scene(fxmlLoader.load(), 600, 400));
            RegisterStage.show();



        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        };
    }

    public void categoriepage(){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("categorie.fxml"));
            Stage RegisterStage = new Stage();
            RegisterStage.initStyle(StageStyle.DECORATED);
            RegisterStage.setScene(new Scene(fxmlLoader.load(), 520, 400));
            RegisterStage.show();



        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        };
    }

    public void produitPage(){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("produit.fxml"));
            Stage RegisterStage = new Stage();
            RegisterStage.initStyle(StageStyle.DECORATED);
            RegisterStage.setScene(new Scene(fxmlLoader.load(), 520, 400));
            RegisterStage.show();



        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        };
    }
}

