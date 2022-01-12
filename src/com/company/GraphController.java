package com.company;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class GraphController implements Initializable {
    
    
    int VertexCounter = -1;
    int NumberOfVertex = 0;
    int cp;
    
    List<Circle> toDetectFirstAndSecondClick = new ArrayList<Circle>();
    
    @FXML
    private BorderPane borderpane;
    @FXML
    private AnchorPane pane;
    @FXML
    private Button sumbitNodes;
    @FXML
    private Button SortNodes;
    @FXML
    private Button confNumBtn;
    @FXML
    private TextField numNode;
    @FXML
     private Label cycleLb;
    
        
    classGraph graph = new classGraph(5,Graph.graphType.DIRECTED);
    
    private List<Circle> listOfCircles = new LinkedList<Circle>();
    private List<Line> listOfLine = new LinkedList<Line>();
    private List<Label> listOfLabel = new ArrayList<>();
    private  List<Rectangle> listOfRectangle = new ArrayList<>();
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void confNumNode(ActionEvent event){
        NumberOfVertex = Integer.parseInt(numNode.getText());
        //System.out.println(NumberOfVertex);
        pane.getChildren().remove(confNumBtn);
        pane.getChildren().remove(numNode);
    }
    

    @FXML
    private void toSumbitNodes(ActionEvent event) {
        pane.setOnMouseClicked(null);
        pane.getChildren().remove(sumbitNodes);
    }

    @FXML
    private void MouseClickedMakeNode(MouseEvent event) {
            VertexCounter++;
            
            Node node = new Node(VertexCounter);
            Circle cr = new Circle(10.00);
            cr.setCenterX(event.getX());
            cr.setCenterY(event.getY());
            cr.setFill(Color.web("#88beff"));
            cr.setId(String.valueOf(VertexCounter));
            listOfCircles.add(cr);
            //System.out.println(node.getVertexId());
            
            Label label = new Label(String.valueOf(VertexCounter));
            label.setTextFill(Color.web("#000000"));
            label.setLayoutX(event.getX()-15.00);
            label.setLayoutY(event.getY()+5.00);
            listOfLabel.add(label);
            
            EventHandler<MouseEvent> crEvent = (MouseEvent) -> {
                
                toDetectFirstAndSecondClick.add(cr);
                if(toDetectFirstAndSecondClick.size() == 2){
                    System.out.print("first "+toDetectFirstAndSecondClick.get(0).getId());
                    System.out.print("  second " +toDetectFirstAndSecondClick.get(1).getId());
                    graph.addEdge(Integer.parseInt(toDetectFirstAndSecondClick.get(0).getId()), Integer.parseInt(toDetectFirstAndSecondClick.get(1).getId()));
                    Line edgeLine = new Line(toDetectFirstAndSecondClick.get(0).getCenterX(),toDetectFirstAndSecondClick.get(0).getCenterY(), toDetectFirstAndSecondClick.get(1).getCenterX(), toDetectFirstAndSecondClick.get(1).getCenterY());
                    listOfLine.add(edgeLine);
                    Rectangle EndNode = new Rectangle(toDetectFirstAndSecondClick.get(1).getCenterX(), toDetectFirstAndSecondClick.get(1).getCenterY(), 5.00, 5.00);
                    listOfRectangle.add(EndNode);
                    edgeLine.setFill(Color.web("#88beff"));
                    edgeLine.setVisible(true);
                    pane.getChildren().add(edgeLine);
                    pane.getChildren().add(EndNode);
                    toDetectFirstAndSecondClick.clear();
                }
            };
            cr.addEventHandler(MouseEvent.MOUSE_CLICKED,crEvent);
            pane.getChildren().add(label);
            pane.getChildren().add(cr);
   }
    
    @FXML
    private void toTopologicallySort(ActionEvent event){
        int count = 0 ;
        List<Integer> sort1 = classGraph.sort(graph);
        
        if(sort1.get(0) == -1){
           cycleLb.setText("The Graph had Cycle");
           pane.getChildren().add(cycleLb);
        }
        for (int i = 0; i < listOfLine.size(); i++) {
            listOfLine.get(i).setVisible(false);
        }
        
        for (int i = 0; i < listOfRectangle.size(); i++) {
            listOfRectangle.get(i).setVisible(false);
        }
        
        for(int i = 0 ; i< classGraph.sort(graph).size();i++){
            //System.out.print(sort1.get(i)+ " ");
            for(int j = 0 ; j< listOfCircles.size();j++){
                if(sort1.get(i) == Integer.parseInt(listOfCircles.get(j).getId())){
                    listOfCircles.get(sort1.get(i)).setCenterX(0);
                    listOfCircles.get(sort1.get(i)).setCenterY(0);
                    TranslateTransition transitCr = new TranslateTransition(Duration.seconds(1), listOfCircles.get(sort1.get(i)));
                    transitCr.setToX(70+ count *60);
                    transitCr.setToY(190);
                     transitCr.play();

                     listOfLabel.get(sort1.get(i)).setLayoutX(0);
                     listOfLabel.get(sort1.get(i)).setLayoutY(0);
                     TranslateTransition transitLb = new TranslateTransition(Duration.seconds(1), listOfLabel.get(sort1.get(i)));
                     transitLb.setToX(60+ count *60);
                     transitLb.setToY(200);
                     transitLb.play();
                     count++;
               
                }
           }    
         }

    }
}

    

