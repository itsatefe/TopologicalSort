package com.company;

import java.util.*;

public class classGraph implements Graph{
    
    private graphType GraphType = graphType.DIRECTED;
    private int numVertices;
    private List<Node> vertexList = new ArrayList<>();
    
    public int getNumVertices() {
        return numVertices;
    }
    
    public classGraph(int numVertices,graphType GraphType) {
       this.numVertices = numVertices;
        for (int i = 0; i < numVertices; i++) {
            vertexList.add(new Node(i));
        }
        this.GraphType = GraphType;
    }

    @Override
    public Edge addEdge(int v1, int v2) {
        if(v1 >= numVertices || v1 <0 || v2 >= numVertices || v2 <0 )
            throw new IllegalArgumentException("vertex number is not valid: "+v1 + ", "+v2);
        //System.out.println("Mishee");
        vertexList.get(v1).addEdge(v2);
        if(GraphType == graphType.UNDIRECTED)
            vertexList.get(v2).addEdge(v1);
        //edge.printEdge();
        Edge edge = new Edge(v1, v2);
        return edge;
    }

    @Override
    public List<Integer> getAdjacentVertices(int  v) {
        if(v >= numVertices || v < 0)
            throw new IllegalArgumentException("vertex number is not valid: " + v);
        
        return vertexList.get(v).getAdjacentVertices();
    }
    
    public int getIndegree(int v){
        if(v >= numVertices || v < 0)
            throw new IllegalArgumentException("vertex number is not valid: " + v);
        
        int indegree = 0;
        for (int i = 0; i < numVertices; i++) {
            if(getAdjacentVertices(i).contains(v))
                indegree++;
        }
        return indegree;
    }
    
    public static List<Integer> sort(classGraph graph){
        
        if(graph.GraphType == graphType.UNDIRECTED)
            throw  new RuntimeException("the graph in undirected");
        LinkedList<Integer> queue = new LinkedList<>();
        Map<Integer, Integer> indegreeMap = new HashMap<>();
        
        for (int vertex = 0; vertex < graph.numVertices; vertex++) {
            int indegree = graph.getIndegree(vertex);
            indegreeMap.put(vertex, indegree);
            if(indegree == 0)
                queue.add(vertex);
        }
        
        List<Integer> sortedList = new ArrayList<>();
        while (!queue.isEmpty()) {            
            //dequeue of the nodes from the list if there are more than one
            //if more than one element exists then it means that the graph
            //has more than one topological sort solution
            int vertex = queue.pollLast();
            sortedList.add(vertex);
            List<Integer> adjaceVertices = graph.getAdjacentVertices(vertex);
            for (int adjaceVertex : adjaceVertices) {
                int updatedIndegree = indegreeMap.get(adjaceVertex)-1;
                indegreeMap.remove(adjaceVertex);
                indegreeMap.put(adjaceVertex, updatedIndegree);
                
                if (updatedIndegree == 0) {
                    queue.add(adjaceVertex);
                }
            }
        }
        List cycle = new ArrayList();
        
        if (sortedList.size() != graph.getNumVertices()) {
            //throw new RuntimeException("the graph had cycle");
            cycle.add(-1);
            return cycle;
        }
        
        return sortedList;
    }
    
        
//    public int[] print(List<Integer> sortedList){
//        int[] Sort = new int[sortedList.size()] ;
//        for(int i = 0 ; i < sortedList.size();i++){
//            Sort[i] = sortedList.get(i);
//        }
//        return Sort;
//   }

}

