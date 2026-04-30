package edu.fsadriann.model.graph;

import edu.fsadriann.app.graph.Vertex;
import edu.fsadriann.app.linkedlist.singly.singly.LinkedList;

public interface Graph<E> {
    boolean addEdge(E vortex1,E vortex2) throws Exception;
    boolean addEdgeWithWeight(E vortex1, E vortex2, double weight) throws Exception;
    boolean removeEdge(E vortex1, E vortex2) throws Exception;
    boolean addVortex(E vortex);
    boolean removeVortex(E vortex);
    int numberEdges();
    int numberVertex();
    boolean isAdjacent(E vortex1, E vortex2) throws Exception;
    int searchVertex(E vortex);
    String seeMatAdj();
    Vertex[] getVertexs();
    LinkedList<E> getNeighbours(E vortex);
    int outDegree(E vertex);
    int inDegree(E vertex);
    boolean existsPath(E startV, E endV);
    void dfs(E startV);
    void bfs(E startV);
    LinkedList<E> shortestPath(E startV, E endV);
    LinkedList<E> dijkstra(E startV, E endV);
    double dijkstraWeight(E startV, E endV);
    boolean isConnected();
    boolean hasCycle();
    boolean isEmpty();

}
