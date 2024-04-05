package com.examplel3gl.examen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    private TextField emailtext;

    @FXML
    private TextField logintext;

    @FXML
    private TextField nomtext;
    @FXML
    private Button registerbut;
    @FXML
    private PasswordField passwordtext;

    @FXML
    private TextField prenomtext;

    @FXML
    private TextField telephonetext;
    @FXML
    private ImageView guitview;


    @FXML
    private Button loginreg;
    @FXML
    private Label registersuccess;
    private Connection connection;


    @FXML
    void loginbuttonAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
            Stage RegisterStage = new Stage();
            RegisterStage.initStyle(StageStyle.DECORATED);
            RegisterStage.setScene(new Scene(fxmlLoader.load(), 600, 400));
            RegisterStage.show();


        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        };
    }


    @FXML
    public void registerbuttonAction(ActionEvent event) {
      registeruser();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File imagesfile = new File("images/OIP.jpg");
        Image pImage = new Image(imagesfile.toURI().toString());
        guitview.setImage(pImage);
    }
    public void registeruser(){

        this.connection = new BD().getConnection();
        String nom = nomtext.getText(); // Get the user input from the text field
        String prenom = prenomtext.getText(); // Get the user input from the text field

        String telephoneInput = telephonetext.getText(); // Get the user input from the text field
        int telephone = Integer.parseInt(telephoneInput); // Convert to an integer
        String email = emailtext.getText(); // Get the user input from the text field
        String login = logintext.getText(); // Get the user input from the text field
        String password = passwordtext.getText(); // Get the user input from the text field




        try {
            String sql = "INSERT user  (nom, prenom, telephone, email, login, password) VALUES (?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nom);
            statement.setString(2, prenom);
            statement.setInt(3, telephone);
            statement.setString(4,email);
            statement.setString(5,login);
            statement.setString(6,password);

            statement.executeUpdate();
            System.out.println("user enregistrer");






        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        };
    }



}

