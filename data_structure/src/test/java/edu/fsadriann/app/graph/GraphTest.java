package edu.fsadriann.app.graph;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    Graph<String> buildGraph() throws Exception {
        Graph<String> g = new Graph<>();
        g.addVortex("A");
        g.addVortex("B");
        g.addVortex("C");
        g.addVortex("D");
        g.addVortex("E");
        g.addEdgeWithWeight("A", "B", 2);
        g.addEdgeWithWeight("A", "C", 5);
        g.addEdgeWithWeight("B", "C", 1);
        g.addEdgeWithWeight("C", "D", 3);
        g.addEdgeWithWeight("D", "E", 4);
        return g;
    }

    @Test
    void addEdge() throws Exception {
        Graph<String> g = new Graph<>(10);
        assertTrue(g.addVortex("1"));
        assertTrue(g.addVortex("2"));
        assertTrue(g.addVortex("3"));

        // Agregar arista nueva debe retornar true
        assertTrue(g.addEdge("1", "2"));

        // Agregar arista duplicada debe retornar false
        assertFalse(g.addEdge("1", "2"));

        // Arista con peso
        assertTrue(g.addEdgeWithWeight("2", "3", 30.0));

        // Vértice inexistente debe lanzar excepción
        assertThrows(Exception.class, () -> g.addEdge("1", "99"));
    }

    @Test
    void addEdgeWithWeight() throws Exception {
        Graph<String> g = new Graph<>();
        g.addVortex("A");
        g.addVortex("B");

        assertTrue(g.addEdgeWithWeight("A", "B", 5.0));

        assertFalse(g.addEdgeWithWeight("A", "B", 10.0));

        assertEquals(1, g.numberEdges());

        assertThrows(Exception.class, () -> g.addEdgeWithWeight("A", "Z", 1.0));
    }

    @Test
    void removeEdge() throws Exception {
        Graph<String> g = buildGraph();
        int edgesBefore = g.numberEdges();

        assertTrue(g.removeEdge("A", "B"));
        assertEquals(edgesBefore - 1, g.numberEdges());
        assertFalse(g.isAdjacent("A", "B"));

        assertFalse(g.removeEdge("A", "B"));

        assertThrows(Exception.class, () -> g.removeEdge("A", "Z"));
    }

    @Test
    void isAdjacent() throws Exception {
        Graph<String> g = buildGraph();

        assertTrue(g.isAdjacent("A", "B"));
        assertTrue(g.isAdjacent("C", "D"));
        assertFalse(g.isAdjacent("A", "D"));
        assertFalse(g.isAdjacent("E", "A"));
        assertThrows(Exception.class, () -> g.isAdjacent("A", "Z"));
    }

    @Test
    void addVortex() {
        Graph<String> g = new Graph<>(3);

        assertTrue(g.addVortex("A"));
        assertTrue(g.addVortex("B"));
        assertTrue(g.addVortex("C"));

        assertFalse(g.addVortex("D"));

        assertEquals(3, g.numberVertex());
    }

    @Test
    void searchVertex() {
        Graph<String> g = new Graph<>();
        g.addVortex("A");
        g.addVortex("B");
        g.addVortex("C");

        assertEquals(0, g.searchVertex("A"));
        assertEquals(1, g.searchVertex("B"));
        assertEquals(2, g.searchVertex("C"));
        assertEquals(-1, g.searchVertex("Z"));
    }

    @Test
    void removeVortex() throws Exception {
        Graph<String> g = buildGraph();

        assertTrue(g.removeVortex("C"));
        assertEquals(-1, g.searchVertex("C"));
        assertEquals(4, g.numberVertex());

        assertFalse(g.existsPath("A", "C"));

        assertFalse(g.removeVortex("Z"));

        Graph<String> empty = new Graph<>();
        assertFalse(empty.removeVortex("A"));
    }

    @Test
    void numberEdges() throws Exception {
        Graph<String> g = new Graph<>();
        g.addVortex("A");
        g.addVortex("B");
        g.addVortex("C");

        assertEquals(0, g.numberEdges());

        g.addEdge("A", "B");
        assertEquals(1, g.numberEdges());

        g.addEdge("B", "C");
        assertEquals(2, g.numberEdges());

        g.removeEdge("A", "B");
        assertEquals(1, g.numberEdges());
    }

    @Test
    void numberVertex() {
        Graph<String> g = new Graph<>();
        assertEquals(0, g.numberVertex());

        g.addVortex("A");
        assertEquals(1, g.numberVertex());

        g.addVortex("B");
        assertEquals(2, g.numberVertex());

        g.removeVortex("A");
        assertEquals(1, g.numberVertex());
    }

    @Test
    void outDegree() throws Exception {
        Graph<String> g = buildGraph();

        assertEquals(2, g.outDegree("A"));
        assertEquals(1, g.outDegree("B"));
        assertEquals(1, g.outDegree("C"));
        assertEquals(1, g.outDegree("D"));
        assertEquals(0, g.outDegree("E"));

        assertEquals(-1, g.outDegree("Z"));
    }

    @Test
    void inDegree() throws Exception {
        Graph<String> g = buildGraph();

        assertEquals(0, g.inDegree("A"));
        assertEquals(1, g.inDegree("B"));
        assertEquals(2, g.inDegree("C"));
        assertEquals(1, g.inDegree("D"));
        assertEquals(1, g.inDegree("E"));

        assertEquals(-1, g.inDegree("Z"));
    }

    @Test
    void existsPath() throws Exception {
        Graph<String> g = buildGraph();

        assertTrue(g.existsPath("A", "E"));
        assertTrue(g.existsPath("A", "D"));
        assertFalse(g.existsPath("E", "A"));

        // Vértice aislado
        g.addVortex("X");
        assertFalse(g.existsPath("A", "X"));
    }

    @Test
    void shortestPath() throws Exception {
        Graph<String> g = buildGraph();

        var path = g.shortestPath("A", "D");
        assertFalse(path.isEmpty());
        System.out.println("Shortest path A->D: " + path);

        // Camino inexistente retorna lista vacía
        var noPath = g.shortestPath("E", "A");
        assertTrue(noPath.isEmpty());
    }

    @Test
    void dijkstra() throws Exception {
        Graph<String> g = new Graph<>();
        g.addVortex("A");
        g.addVortex("B");
        g.addVortex("C");
        g.addVortex("D");
        g.addEdgeWithWeight("A", "B", 1);
        g.addEdgeWithWeight("A", "C", 4);
        g.addEdgeWithWeight("B", "C", 2);
        g.addEdgeWithWeight("B", "D", 5);
        g.addEdgeWithWeight("C", "D", 1);

        var path = g.dijkstra("A", "D");
        assertFalse(path.isEmpty());
        System.out.println("Dijkstra A->D: " + path);

        var noPath = g.dijkstra("D", "A");
        assertTrue(noPath.isEmpty());
    }

    @Test
    void dijkstraWeight() throws Exception {
        Graph<String> g = new Graph<>();
        g.addVortex("A");
        g.addVortex("B");
        g.addVortex("C");
        g.addVortex("D");
        g.addEdgeWithWeight("A", "B", 1);
        g.addEdgeWithWeight("A", "C", 4);
        g.addEdgeWithWeight("B", "C", 2);
        g.addEdgeWithWeight("B", "D", 5);
        g.addEdgeWithWeight("C", "D", 1);

        assertEquals(4.0, g.dijkstraWeight("A", "D"));

        assertEquals(1.0, g.dijkstraWeight("A", "B"));

        assertEquals(-1.0, g.dijkstraWeight("D", "A"));
    }

    @Test
    void seeMatAdj() throws Exception {
        Graph<String> g = buildGraph();
        String mat = g.seeMatAdj();
        assertNotNull(mat);
        assertFalse(mat.isEmpty());
        System.out.println("Matriz de adyacencia:\n" + mat);
    }

    @Test
    void isConnected() throws Exception {
        // Grafo conexo
        Graph<String> connected = new Graph<>();
        connected.addVortex("A");
        connected.addVortex("B");
        connected.addVortex("C");
        connected.addEdge("A", "B"); connected.addEdge("B", "A");
        connected.addEdge("B", "C"); connected.addEdge("C", "B");
        assertTrue(connected.isConnected());

        // Grafo no conexo
        Graph<String> disconnected = new Graph<>();
        disconnected.addVortex("A");
        disconnected.addVortex("B");
        disconnected.addVortex("C");
        disconnected.addEdge("A", "B");
        assertFalse(disconnected.isConnected());

        // Grafo vacío
        Graph<String> empty = new Graph<>();
        assertFalse(empty.isConnected());
    }

    @Test
    void hasCycle() throws Exception {
        // Con ciclo: A->B->C->A
        Graph<String> withCycle = new Graph<>();
        withCycle.addVortex("A");
        withCycle.addVortex("B");
        withCycle.addVortex("C");
        withCycle.addEdge("A", "B");
        withCycle.addEdge("B", "C");
        withCycle.addEdge("C", "A");
        assertTrue(withCycle.hasCycle());

        // Sin ciclo: A->B->C
        Graph<String> noCycle = new Graph<>();
        noCycle.addVortex("A");
        noCycle.addVortex("B");
        noCycle.addVortex("C");
        noCycle.addEdge("A", "B");
        noCycle.addEdge("B", "C");
        assertFalse(noCycle.hasCycle());
    }


    @Test
    void isEulerian() throws Exception {
        Graph<String> eulerian = new Graph<>();
        eulerian.addVortex("A");
        eulerian.addVortex("B");
        eulerian.addVortex("C");
        eulerian.addVortex("D");
        eulerian.addEdge("A", "B"); eulerian.addEdge("B", "A");
        eulerian.addEdge("B", "C"); eulerian.addEdge("C", "B");
        eulerian.addEdge("C", "D"); eulerian.addEdge("D", "C");
        eulerian.addEdge("D", "A"); eulerian.addEdge("A", "D");
        assertTrue(eulerian.isEulerian());

        Graph<String> notEulerian = new Graph<>();
        notEulerian.addVortex("A");
        notEulerian.addVortex("B");
        notEulerian.addVortex("C");
        notEulerian.addEdge("A", "B"); notEulerian.addEdge("B", "A");
        notEulerian.addEdge("B", "C");
        assertFalse(notEulerian.isEulerian());

        Graph<String> disconnected = new Graph<>();
        disconnected.addVortex("A");
        disconnected.addVortex("B");
        disconnected.addVortex("C");
        disconnected.addVortex("D");
        disconnected.addEdge("A", "B"); disconnected.addEdge("B", "A");
        disconnected.addEdge("C", "D"); disconnected.addEdge("D", "C");
        assertFalse(disconnected.isEulerian());
    }

    @Test
    void isBridge() throws Exception {
        Graph<String> g = new Graph<>();
        g.addVortex("A");
        g.addVortex("B");
        g.addVortex("C");
        g.addEdge("A", "B"); g.addEdge("B", "A");
        g.addEdge("B", "C"); g.addEdge("C", "B");

        assertTrue(g.isBridge("A", "B"));
        assertTrue(g.isBridge("B", "C"));

        g.addEdge("A", "C"); g.addEdge("C", "A");
        assertFalse(g.isBridge("A", "B"));
        assertFalse(g.isBridge("B", "C"));

        // Vértice inexistente lanza excepción
        assertThrows(Exception.class, () -> g.isBridge("A", "Z"));
    }

    @Test
    void removeVortexOriginal() throws Exception {
        Graph<String> g = new Graph<>();
        g.addVortex("A"); g.addVortex("B"); g.addVortex("C");
        g.addVortex("D"); g.addVortex("E");
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
        System.out.print("DFS desde A: "); g.dfs("A"); System.out.println();
        System.out.print("BFS desde A: "); g.bfs("A"); System.out.println();
        System.out.println("Shortest path A->D: " + g.shortestPath("A", "D"));
        System.out.println("Dijkstra A->D: " + g.dijkstra("A", "D"));
        System.out.println("Existe camino A->E: " + g.existsPath("A", "E"));
        System.out.println("Existe camino E->A: " + g.existsPath("E", "A"));
        System.out.println("¿Es conectado?: " + g.isConnected());
        System.out.println("¿Tiene ciclo?: " + g.hasCycle());

        g.addEdge("E", "B");
        System.out.println("¿Tiene ciclo ahora?: " + g.hasCycle());
        g.removeEdge("E", "B");
        System.out.println("¿Tiene ciclo después de eliminar?: " + g.hasCycle());
        g.removeVortex("C");
        System.out.println("Matriz después de eliminar C:");
        System.out.println(g.seeMatAdj());
        System.out.println("Existe camino A->D: " + g.existsPath("A", "D"));

        Graph<Integer> g2 = new Graph<>();
        System.out.println("Grafo vacío conectado: " + g2.isConnected());
    }
}