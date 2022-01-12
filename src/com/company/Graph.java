package com.company;

import java.util.List;

public interface Graph {
     enum graphType{
        DIRECTED,
        UNDIRECTED,
    }
    
    Edge addEdge(int v1,int v2);
    List<Integer> getAdjacentVertices(int v);
}
