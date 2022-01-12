package com.company;

public class Edge {
   private int first;
    private int second;

    public Edge(int first, int second) {
        this.first = first;
        this.second = second;
    }
    
    public void printEdge(){
        System.out.println(second + " added to " + first );
    }
    

    
}
