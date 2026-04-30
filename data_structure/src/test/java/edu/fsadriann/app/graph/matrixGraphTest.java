package edu.fsadriann.app.graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class matrixGraphTest {

    @Test
    void addEdge() throws Exception {
        matrixGraph<String> graph = new matrixGraph<>(10);
        assertTrue(graph.addVortex("1"));
        assertTrue(graph.addVortex("2"));
        assertTrue(graph.addVortex("3"));
        assertTrue(graph.addVortex("4"));
        assertTrue(graph.addVortex("5"));
        assertTrue(graph.addVortex("6"));
        assertTrue(graph.addVortex("7"));
        assertTrue(graph.addVortex("8"));
        assertTrue(graph.addVortex("9"));
        assertTrue(graph.addVortex("10"));
        System.out.println(graph.seeMatAdj());
        graph.addEdgeWithWeight("1","2",30.0);
        graph.addEdge("1","2");
        graph.addEdge("1","2");
        graph.addEdge("1","2");
        graph.addEdge("1","2");
        graph.addEdge("1","2");
        graph.addEdge("1","3");
        graph.addEdge("5","5");
        System.out.println(graph.seeMatAdj());


    }

    @Test
    void addEdgeWithWeight() {
    }

    @Test
    void isAdjacent() {
    }

    @Test
    void removeEdge() {

    }

    @Test
    void addVortex() {
    }

    @Test
    void searchVertex() {
    }

    @Test
    void seeMatAdj() {
    }

    @Test
    void removeVortex() throws Exception {
        matrixGraph<String> g = new matrixGraph<>();

        // 🔹 1. Agregar vértices
        g.addVortex("A");
        g.addVortex("B");
        g.addVortex("C");
        g.addVortex("D");
        g.addVortex("E");

        // 🔹 2. Agregar aristas (con pesos)
        g.addEdgeWithWeight("A", "B", 2);
        g.addEdgeWithWeight("A", "C", 5);
        g.addEdgeWithWeight("B", "C", 1);
        g.addEdgeWithWeight("C", "D", 3);
        g.addEdgeWithWeight("D", "E", 4);

        System.out.println("Matriz de adyacencia:");
        System.out.println(g.seeMatAdj());

        System.out.println("Vecinos de A: " + g.getNeighbours("A"));

        System.out.println("OutDegree A: " + g.outDegree("A"));
        System.out.println("InDegree C: " + g.inDegree("C"));

        System.out.print("DFS desde A: ");
        g.dfs("A");
        System.out.println();

        System.out.print("BFS desde A: ");
        g.bfs("A");
        System.out.println();

        System.out.println("Shortest path A -> D: " + g.shortestPath("A", "D"));

        System.out.println("Dijkstra A -> D: " + g.dijkstra("A", "D"));

        System.out.println("Existe camino A -> E: " + g.existsPath("A", "E"));
        System.out.println("Existe camino E -> A: " + g.existsPath("E", "A"));

        System.out.println("¿Es conectado?: " + g.isConnected());

        System.out.println("¿Tiene ciclo?: " + g.hasCycle());

        g.addEdge("E", "B");
        System.out.println("¿Tiene ciclo ahora?: " + g.hasCycle());

        g.removeEdge("E", "B");
        System.out.println("¿Tiene ciclo después de eliminar?: " + g.hasCycle());

        g.removeVortex("C");
        System.out.println("Matriz después de eliminar C:");
        System.out.println(g.seeMatAdj());

        System.out.println("Existe camino A -> D: " + g.existsPath("A", "D"));

        matrixGraph<Integer> g2 = new matrixGraph<>();
        System.out.println("Grafo vacío conectado: " + g2.isConnected());
    }

    @Test
    void numberEdges() {
    }

    @Test
    void numberVertex() {
    }
}