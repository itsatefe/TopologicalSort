package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Node {
    private final int vertexId;
    private final List<Integer> adjacencyList = new LinkedList<>() ;

    public Node(int vertexId) {
        this.vertexId = vertexId;
    }
    
    public int getVertexId(){
        return vertexId;
    }
    
    public void addEdge(int vertexId){
        adjacencyList.add(vertexId);
    }
    
    public List<Integer> getAdjacentVertices(){
        List<Integer> sortedList = new ArrayList<>(adjacencyList);
        Collections.sort(sortedList);
        return sortedList;
    }
    
    
    
//    public void print(List<Integer> adjacentVertices){
//        for (Integer adjacentVertex : adjacentVertices) {
//            System.out.println(adjacentVertex);
//        }
    }



    
    

 

