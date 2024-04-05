package com.examplel3gl.examen;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.examplel3gl.examen.Produitrepository.getCategories;
import static com.examplel3gl.examen.Produitrepository.getCategories1;

public class ProduitController implements Initializable {
    @FXML
    private Button btnajouter;
    @FXML
    private TableView<produit> tablefx;


    @FXML
    private ComboBox<String> categorietxt;

    @FXML
    private TextField idpr;

    @FXML
    private ImageView categorylogo;

    @FXML
    private ImageView doclogo;

    @FXML
    private Button extraire_ducmentbtn;

    @FXML
    private Button homebtn;

    @FXML
    private ImageView homelogo;
    @FXML
    private Button searchButton;

    @FXML
    private TextField searchField;

    @FXML
    private TextField libelletxt;

    @FXML
    private AnchorPane pane2;
    @FXML
    private TextField filterField;
    @FXML
    private TextField prixtxt;

    @FXML
    private ImageView prodlogo;

    @FXML
    private Button produitbtn;

    @FXML
    private TextField quantitetxt;

    @FXML
    private Button statistiquebtn;

    @FXML
    private ImageView statlogo;
    private static Connection connection;



    @FXML
    void SupprimeAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment supprimer cet élément ?");
        alert.setContentText("Click sur l'option oui pour confimer");
        ButtonType buttonTypeYes = new ButtonType("Oui");
        ButtonType buttonTypeNo = new ButtonType("Non");

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();
        int id = this.tablefx.getSelectionModel().getSelectedItem().getId();
        if (result.isPresent() && result.get() == buttonTypeYes) {
        Produitrepository.delete(id);
            System.out.println("Élément supprimé !");
        } else {
            // Annulation de la suppression
            System.out.println("Suppression annulée.");
        }
        this.affiche();
    }
    @FXML
    void EffacerAction(ActionEvent event) {
        libelletxt.setText("");
        quantitetxt.setText("");
        prixtxt.setText("");
        categorietxt.setValue( "");
    }








    @FXML
    void modifAction(ActionEvent event) {
        try {
            String t= categorietxt.getValue();
            String sql = "SELECT id, libelle FROM categorie where libelle LIKE CONCAT(?, '%')"; // Query to fetch category names and IDs
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, t);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int categoryId = result.getInt("id");
                String categoryName = result.getString("libelle");
        produit produit = new produit(libelletxt.getText(),
                Integer.parseInt(quantitetxt.getText()) , Integer.parseInt(prixtxt.getText()),
                (categoryId));
        produit.setId(Integer.parseInt(idpr.getText()));

        Produitrepository.Updateproduit(produit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        affiche();
        EffacerAction(event);
    }





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
    private Produitrepository Produitrepository;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.connection = new BD().getConnection();

        this.Produitrepository = new Produitrepository();
        this.affiche();
        categorietxt.setItems(getCategories1());
        searchButton.setOnAction(event -> {
            produit produit=new produit();
            String prd= produit.getLibelle();
            String keyword = searchField.getText();
                ObservableList<produit> list = FXCollections.observableArrayList();
                try {

                    String sql = "SELECT * FROM produit as p,categorie as c WHERE p.libelle LIKE CONCAT(?, '%') AND p.idcategorie=c.id";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, keyword);
                    ResultSet result = statement.executeQuery();

                    while(result.next()) {

                        produit p =  new produit();
                        p.setId(result.getInt("id"));
                        p.setLibelle(result.getString("p.libelle"));
                        p.setQuantite(result.getInt("quantite"));
                        p.setPrix_unitaire(result.getInt("prix_unitaire"));
                        p.setIdcategorie(result.getInt("idcategorie"));
                        p.setLibellec(result.getString("c.libelle"));



                        list.add(p);

                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            idtable.setCellValueFactory(new PropertyValueFactory<produit, Integer>("id"));
            libelletable.setCellValueFactory(new PropertyValueFactory<produit, String>("libelle"));
            quantitetable.setCellValueFactory(new PropertyValueFactory<produit, Integer>("quantite"));
            prixtable.setCellValueFactory(new PropertyValueFactory<produit, Integer>("prix_unitaire"));
            categorietable.setCellValueFactory(new PropertyValueFactory<produit, String>("libellec"));
            tablefx.setItems(list);

            System.out.println("Recherche avec le mot-clé : " + keyword);
        });


    }



    @FXML
    void ajoutAction(ActionEvent event) {
        try {
            String t= categorietxt.getValue();
            String sql = "SELECT id, libelle FROM categorie where libelle LIKE CONCAT(?, '%')"; // Query to fetch category names and IDs
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, t);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int categoryId = result.getInt("id");
                String categoryName = result.getString("libelle");

        produit produit = new produit(libelletxt.getText(),
                Integer.parseInt(quantitetxt.getText()) , Integer.parseInt(prixtxt.getText()),

                (categoryId));
                Produitrepository.AddProduit(produit);
                this.EffacerAction(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.affiche();
    }

    @FXML
    void charge(MouseEvent event) {
        produit produit =this.tablefx.getSelectionModel().getSelectedItem();
        if(event.getClickCount() == 2){
            libelletxt.setText(produit.getLibelle()+"");
            quantitetxt.setText(produit.getQuantite()+"");
            prixtxt.setText(produit.getPrix_unitaire()+"");
            categorietxt.setValue(produit.getLibellec());
            idpr.setText(produit.getId()+"");
        }
    }



    @FXML
    private TableColumn<produit, Integer> idtable;
    @FXML
    private TableColumn<produit, String> libelletable;
    @FXML
    private TableColumn<produit, Integer> quantitetable;
    @FXML
    private TableColumn<produit, Integer> prixtable;
    @FXML
    private TableColumn<produit, String> categorietable;;

    void affiche(){
        ObservableList<produit> list = Produitrepository.getAll();
       idtable.setCellValueFactory(new PropertyValueFactory<produit, Integer>("id"));
        libelletable.setCellValueFactory(new PropertyValueFactory<produit, String>("libelle"));
        quantitetable.setCellValueFactory(new PropertyValueFactory<produit, Integer>("quantite"));
        prixtable.setCellValueFactory(new PropertyValueFactory<produit, Integer>("prix_unitaire"));
        categorietable.setCellValueFactory(new PropertyValueFactory<produit, String>("libellec"));

        tablefx.setItems(list);

    }





    @FXML
    void CategoryId(ActionEvent event) {

    }

    @FXML
    void CategoryName(ActionEvent event) {

    }
}
