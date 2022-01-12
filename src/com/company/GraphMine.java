package com.company;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


public class GraphMine extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = null;
        try {
           root = FXMLLoader.load(getClass().getResource("Graph.fxml"));
        } catch (IOException ex) {
            System.out.println("Cant load");
        }

        Scene scene = new Scene(root);
        primaryStage.setTitle("Graph");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/company/spider-graph.jpg")));
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
