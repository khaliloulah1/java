package com.examplel3gl.examen;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

import static com.examplel3gl.examen.Produitrepository.getCat;
import static com.examplel3gl.examen.Produitrepository.getCat1;
import static com.examplel3gl.examen.StatRepository.getCap;
import static com.examplel3gl.examen.StatRepository.getCap2;

public class StatistiqueController implements Initializable {
    @FXML
    private PieChart pieChart;
    @FXML
    private BarChart<String, Number> barChart;
    private Produitrepository Produitrepository;
    private com.examplel3gl.examen.StatRepository StatRepository;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
        for (i = 0; i <= j; i++) {
            int p = Integer.parseInt(list1.get(i));
            String l = list.get(i);
            ObservableList<PieChart.Data> pieChartData;
            pieChartData = FXCollections.observableArrayList(

                    new PieChart.Data(l, p));


            pieChartData.forEach(data ->
                    data.nameProperty().bind(
                            Bindings.concat(
                                    data.getName(), " amount: ", data.pieValueProperty()
                            )
                    )
            );

            pieChart.getData().addAll(pieChartData);
        }





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