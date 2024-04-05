package com.examplel3gl.examen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import static com.examplel3gl.examen.Produitrepository.*;
import static com.examplel3gl.examen.StatRepository.getCap;
import static com.examplel3gl.examen.StatRepository.getCap2;

public class Statistique2Controller implements Initializable {


    @FXML
    private BarChart<String, Number> barChart;


    @FXML
    private Button categoriebtn;

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
    private ImageView prodlogo;

    @FXML
    private Button produitbtn;

    @FXML
    private Button statistiquebtn;

    @FXML
    private ImageView statlogo;
    private com.examplel3gl.examen.StatRepository StatRepository;

    @FXML
    void categorieAction(ActionEvent event) {

    }

    @FXML
    void extraire_ducmentAction(ActionEvent event) {

    }

    @FXML
    void homeAction(ActionEvent event) {

    }

    @FXML
    void produitAction(ActionEvent event) {

    }

    @FXML
    void statistiqueAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.StatRepository = new StatRepository();
        ObservableList<String> list4 = FXCollections.observableArrayList();

        ObservableList<String> list3 = FXCollections.observableArrayList();
        list3=getCap();
        list4=getCap2();


        list3.forEach( (libelle) -> System.out.println(libelle) );
        list4.forEach( (libelle) -> System.out.println(libelle) );



        //Creating the Bar chart
        barChart.setTitle(" diagramme de charts sous forme de diagramme de barre qui\n" +
                "représente le nombre de produit ajouté par mois en 2024");



        int z=list4.size();
        z=z-1;
        System.out.println(z);
        for(int e=0;e<=z;e++) {
            int p= Integer.parseInt(list3.get(e));
            String l=list4.get(e);
            XYChart.Series<String, Number> series1 = new XYChart.Series<>();
            series1.setName(l);
            series1.getData().add(new XYChart.Data<>("JANVIER", 0));
            series1.getData().add(new XYChart.Data<>("FEVRIER", 0));

            series1.getData().add(new XYChart.Data<>(l, p));
            series1.getData().add(new XYChart.Data<>("AVRIL", 0));
            series1.getData().add(new XYChart.Data<>("MAI", 0));
            series1.getData().add(new XYChart.Data<>("JUIN", 0));
            series1.getData().add(new XYChart.Data<>("JUILLET", 0));
            series1.getData().add(new XYChart.Data<>("AOUT", 0));
            series1.getData().add(new XYChart.Data<>("SEPTEMBRE", 0));
            series1.getData().add(new XYChart.Data<>("OCTOBRE", 0));
            series1.getData().add(new XYChart.Data<>("NOVEMBRE", 0));
            series1.getData().add(new XYChart.Data<>("DECEMBRE", 0));






            //Setting the data to bar chart
        barChart.getData().addAll(series1);}


    }
}
