package com.examplel3gl.examen;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CategoryController implements Initializable {
    private Categorierepository Categorierepository;

    @FXML
    private Button btnajouter;
    @FXML
    private Button btneffacer;


    @FXML
    private Button btnmodifier;

    @FXML
    private TextField idtxt;


    @FXML
    private ImageView doclogo;

    @FXML
    private Button extraire_ducmentbtn;

    @FXML
    private Button homebtn;

    @FXML
    private TableView<categorie> tablefx;

    @FXML
    private ImageView homelogo;

    @FXML
    private TableColumn<categorie, Integer> idtable;

    @FXML
    private TableColumn<categorie, String> libelletable;

    @FXML
    private TextField libelletxt;



    @FXML
    private AnchorPane pane2;

    @FXML
    private ImageView prodlogo;

    @FXML
    private Button produitbtn;

    @FXML
    private Button statistiquebtn;

    @FXML
    private ImageView statlogo;




    public void produitpage(){

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
    public void statistiquepage(){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("statistique.fxml"));
            Stage RegisterStage = new Stage();
            RegisterStage.initStyle(StageStyle.DECORATED);
            RegisterStage.setScene(new Scene(fxmlLoader.load(), 520, 400));
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
    public void extraire_documentpage(){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("extraire_document.fxml"));
            Stage RegisterStage = new Stage();
            RegisterStage.initStyle(StageStyle.DECORATED);
            RegisterStage.setScene(new Scene(fxmlLoader.load(), 520, 400));
            RegisterStage.show();



        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        };
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {


        this.Categorierepository = new Categorierepository();
        this.Afficher();
    }
    @FXML
    void charge(MouseEvent event) {
        categorie categorie = this.tablefx.getSelectionModel().getSelectedItem();
        if (event.getClickCount() == 2) {
            libelletxt.setText(categorie.getLibelle());

            idtxt.setText(categorie.getId() + "");
        }
    }


    @FXML
    void modifAction(ActionEvent event) {
        categorie categorie;
        categorie = new categorie(libelletxt.getText());
        categorie.setId(Integer.parseInt(idtxt.getText()));

        Categorierepository.UpdateCategorie(categorie);
        Afficher();
        EffacerAction(event);


    }
    @FXML
    void SupprimeAction(ActionEvent event) {
        int id = this.tablefx.getSelectionModel().getSelectedItem().getId();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment supprimer cet élément ?");
        alert.setContentText("Cette action est irréversible.");

        // Personnalisation des boutons
        ButtonType buttonTypeYes = new ButtonType("Oui");
        ButtonType buttonTypeNo = new ButtonType("Non");

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeYes) {
        Categorierepository.delete(id);
            System.out.println("Élément supprimé !");
        } else {
            // Annulation de la suppression
            System.out.println("Suppression annulée.");
        }
        this.Afficher();
        EffacerAction(event);
    }

    @FXML
    void ajoutAction(ActionEvent event) {
        categorie categorie = new categorie(libelletxt.getText());

        Categorierepository.AjoutCategorie(categorie);
        this.EffacerAction(event);
        this.Afficher();
    }

void Afficher(){


    ObservableList<categorie> list = com.examplel3gl.examen.Categorierepository.getAll();
    idtable.setCellValueFactory(new PropertyValueFactory<categorie, Integer>("id"));
    libelletable.setCellValueFactory(new PropertyValueFactory<categorie, String>("libelle"));

    tablefx.setItems(list);

}
    @FXML
    void EffacerAction(ActionEvent event) {
        libelletxt.setText("");

    }


}