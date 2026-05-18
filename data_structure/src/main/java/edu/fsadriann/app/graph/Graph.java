package edu.fsadriann.app.graph;

import edu.fsadriann.app.linkedlist.singly.singly.LinkedList;
import edu.fsadriann.app.queue.list.Queue;
import edu.fsadriann.model.graph.GraphInterface;

public class Graph<E> implements GraphInterface<E> {
    private int numVerts;
    private int numEdges;
    private int maxVerts;
    private Vertex[] vertexs;
    double[][] matAdj;

    public Graph() {
        this(20);
    }

    public Graph(int maxVerts) {
        this.maxVerts = maxVerts;
        matAdj = new double[maxVerts][maxVerts];
        vertexs = new Vertex[maxVerts];
        numVerts = 0;
        numEdges = 0;
    }

    @Override
    public boolean addVortex(E vortex) {
        if (numVerts < maxVerts) {
            Vertex<E> vertex = new Vertex<>(vortex);
            vertex.setNumVertex(numVerts);
            vertexs[numVerts++] = vertex;
            return true;
        }
        return false;
    }




    @Override
    public boolean removeVortex(E vortex) {
        int va = searchVertex(vortex);
        if (va == -1) return false;

        for (int i = 0; i < numVerts; i++) {
            if (matAdj[va][i] != 0) numEdges--;
            if (matAdj[i][va] != 0 && i != va) numEdges--;
        }

        for (int i = va; i < numVerts - 1; i++) {
            vertexs[i] = vertexs[i + 1];
        }
        vertexs[numVerts - 1] = null;

        for (int i = va; i < numVerts - 1; i++)
            for (int j = 0; j < numVerts; j++)
                matAdj[i][j] = matAdj[i + 1][j];

        for (int i = 0; i < numVerts; i++)
            for (int j = va; j < numVerts - 1; j++)
                matAdj[i][j] = matAdj[i][j + 1];

        numVerts--;
        return true;
    }

    @Override
    public int searchVertex(E vortex) {
        for (int i = 0; i < numVerts; i++) {
            if (vertexs[i].get().equals(vortex)) return i;
        }
        return -1;
    }

    @Override
    public Vertex[] getVertexs() {
        return vertexs;
    }

    @Override
    public boolean addEdge(E vortex1, E vortex2) throws Exception {
        return addEdgeWithWeight(vortex1, vortex2, 1);
    }

    @Override
    public boolean addEdgeWithWeight(E vortex1, E vortex2, double weight) throws Exception {
        int va = searchVertex(vortex1);
        int vb = searchVertex(vortex2);
        if (va < 0 || vb < 0) throw new Exception("Vertice no encontrado");
        if (matAdj[va][vb] != 0) return false;
        matAdj[va][vb] = weight;
        numEdges++;
        return true;
    }

    @Override
    public boolean removeEdge(E vortex1, E vortex2) throws Exception {
        int va = searchVertex(vortex1);
        int vb = searchVertex(vortex2);
        if (va < 0 || vb < 0) throw new Exception("Vertice no existe");
        if (matAdj[va][vb] == 0) return false;
        matAdj[va][vb] = 0;
        numEdges--;
        return true;
    }

    @Override
    public boolean isAdjacent(E vortex1, E vortex2) throws Exception {
        int va = searchVertex(vortex1);
        int vb = searchVertex(vortex2);
        if (va < 0 || vb < 0) throw new Exception("Vertice no existe");
        return matAdj[va][vb] != 0;
    }

    @Override
    public int outDegree(E vertex) {
        int va = searchVertex(vertex);
        if (va == -1) return -1;
        int degree = 0;
        for (int i = 0; i < numVerts; i++)
            if (matAdj[va][i] != 0) degree++;
        return degree;
    }

    @Override
    public int inDegree(E vortex) {
        int va = searchVertex(vortex);
        if (va == -1) return -1;
        int degree = 0;
        for (int i = 0; i < numVerts; i++)
            if (matAdj[i][va] != 0) degree++;
        return degree;
    }

    // ─── RECORRIDOS ──────────────────────────────────────────────────────────

    @Override
    public void dfs(E startV) {
        int start = searchVertex(startV);
        if (start == -1) return;
        boolean[] visited = new boolean[numVerts];
        dfsRecursive(start, visited);
    }

    private void dfsRecursive(int current, boolean[] visited) {
        visited[current] = true;
        System.out.print(vertexs[current].get() + " -> ");
        for (int i = 0; i < numVerts; i++)
            if (matAdj[current][i] != 0 && !visited[i])
                dfsRecursive(i, visited);
    }

    private void dfsVisit(int current, boolean[] visited) {
        visited[current] = true;
        for (int i = 0; i < numVerts; i++)
            if (matAdj[current][i] != 0 && !visited[i])
                dfsVisit(i, visited);
    }

    @Override
    public void bfs(E startV) {
        int start = searchVertex(startV);
        if (start == -1) return;
        boolean[] visited = new boolean[numVerts];
        Queue<Integer> queue = new Queue<>();
        queue.insert(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int current = queue.extract();
            System.out.print(vertexs[current].get() + " -> ");
            for (int i = 0; i < numVerts; i++) {
                if (matAdj[current][i] != 0 && !visited[i]) {
                    queue.insert(i);
                    visited[i] = true;
                }
            }
        }
    }

    @Override
    public boolean existsPath(E startV, E endV) {
        int start = searchVertex(startV);
        int end = searchVertex(endV);
        if (start == -1 || end == -1) return false;
        boolean[] visited = new boolean[numVerts];
        dfsVisit(start, visited);
        return visited[end];
    }

    @SuppressWarnings("unchecked")
    @Override
    public LinkedList<E> shortestPath(E startV, E endV) {
        LinkedList<E> path = new LinkedList<>();
        int start = searchVertex(startV);
        int end = searchVertex(endV);
        if (start == -1 || end == -1) return path;

        boolean[] visited = new boolean[numVerts];
        int[] parent = new int[numVerts];
        for (int i = 0; i < numVerts; i++) parent[i] = -1;

        Queue<Integer> queue = new Queue<>();
        queue.insert(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int current = queue.extract();
            if (current == end) break;
            for (int i = 0; i < numVerts; i++) {
                if (matAdj[current][i] != 0 && !visited[i]) {
                    visited[i] = true;
                    parent[i] = current;
                    queue.insert(i);
                }
            }
        }
        if (!visited[end]) return path;
        for (int v = end; v != -1; v = parent[v])
            path.addFirst((E) vertexs[v].get());
        return path;
    }

    @Override
    public double dijkstraWeight(E startV, E endV) {
        int start = searchVertex(startV);
        int end = searchVertex(endV);
        if (start == -1 || end == -1) return -1;

        double[] dist = new double[numVerts];
        boolean[] visited = new boolean[numVerts];
        int[] parent = new int[numVerts];

        for (int i = 0; i < numVerts; i++) {
            dist[i] = Double.MAX_VALUE;
            parent[i] = -1;
        }
        dist[start] = 0;

        for (int count = 0; count < numVerts; count++) {
            int u = minDistance(dist, visited);
            if (u == -1) break;
            visited[u] = true;
            for (int v = 0; v < numVerts; v++) {
                if (matAdj[u][v] != 0 && !visited[v]) {
                    double newDist = dist[u] + matAdj[u][v];
                    if (newDist < dist[v]) {
                        dist[v] = newDist;
                        parent[v] = u;
                    }
                }
            }
            if (u == end) break;
        }
        return dist[end] == Double.MAX_VALUE ? -1 : dist[end];
    }

    @SuppressWarnings("unchecked")
    @Override
    public LinkedList<E> dijkstra(E startV, E endV) {
        LinkedList<E> path = new LinkedList<>();
        int start = searchVertex(startV);
        int end = searchVertex(endV);
        if (start == -1 || end == -1) return path;

        double[] dist = new double[numVerts];
        boolean[] visited = new boolean[numVerts];
        int[] parent = new int[numVerts];

        for (int i = 0; i < numVerts; i++) {
            dist[i] = Double.MAX_VALUE;
            parent[i] = -1;
        }
        dist[start] = 0;

        for (int count = 0; count < numVerts; count++) {
            int u = minDistance(dist, visited);
            if (u == -1) break;
            visited[u] = true;
            for (int v = 0; v < numVerts; v++) {
                if (matAdj[u][v] != 0 && !visited[v]) {
                    double newDist = dist[u] + matAdj[u][v];
                    if (newDist < dist[v]) {
                        dist[v] = newDist;
                        parent[v] = u;
                    }
                }
            }
            if (u == end) break;
        }

        if (dist[end] == Double.MAX_VALUE) return path;
        for (int v = end; v != -1; v = parent[v])
            path.addFirst((E) vertexs[v].get());
        return path;
    }

    private int minDistance(double[] dist, boolean[] visited) {
        int u = -1;
        double min = Double.MAX_VALUE;
        for (int i = 0; i < numVerts; i++) {
            if (!visited[i] && dist[i] < min) {
                min = dist[i];
                u = i;
            }
        }
        return u;
    }

    @Override
    public boolean isConnected() {
        if (isEmpty()) return false;
        boolean[] visited = new boolean[numVerts];
        dfsVisit(0, visited);
        for (boolean v : visited)
            if (!v) return false;
        return true;
    }

    @Override
    public boolean hasCycle() {
        boolean[] visited = new boolean[numVerts];
        boolean[] recStack = new boolean[numVerts];
        for (int i = 0; i < numVerts; i++)
            if (!visited[i] && hasCycleDFS(i, visited, recStack))
                return true;
        return false;
    }

    private boolean hasCycleDFS(int i, boolean[] visited, boolean[] recStack) {
        visited[i] = true;
        recStack[i] = true;
        for (int j = 0; j < numVerts; j++) {
            if (matAdj[i][j] != 0) {
                if (!visited[j] && hasCycleDFS(j, visited, recStack)) return true;
                else if (recStack[j]) return true;
            }
        }
        recStack[i] = false;
        return false;
    }

    public boolean isEulerian() {
        if (!isConnected()) return false;
        for (int i = 0; i < numVerts; i++) {
            int degree = 0;
            for (int j = 0; j < numVerts; j++)
                if (matAdj[i][j] != 0) degree++;
            if (degree % 2 != 0) return false;
        }
        return true;
    }

    public boolean isBridge(E vortex1, E vortex2) throws Exception {
        int va = searchVertex(vortex1);
        int vb = searchVertex(vortex2);
        if (va == -1 || vb == -1) throw new Exception("Vertice no encontrado");
        if (matAdj[va][vb] == 0) return false;

        double weight = matAdj[va][vb];
        matAdj[va][vb] = 0;
        matAdj[vb][va] = 0;

        boolean[] visited = new boolean[numVerts];
        dfsVisit(va, visited);
        boolean bridge = !visited[vb];

        matAdj[va][vb] = weight;
        matAdj[vb][va] = weight;

        return bridge;
    }

    @Override
    public boolean isEmpty() {
        return numVerts == 0;
    }

    @Override
    public int numberEdges() {
        return numEdges;
    }

    @Override
    public int numberVertex() {
        return numVerts;
    }

    @Override
    public String seeMatAdj() {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < numVerts; i++) {
            for (int j = 0; j < numVerts; j++)
                text.append("[").append(matAdj[i][j]).append("]");
            text.append("\n");
        }
        return text.toString();
    }

    @SuppressWarnings("unchecked")
    @Override
    public LinkedList<E> getNeighbours(E vortex) {
        LinkedList<E> neighbours = new LinkedList<>();
        int va = searchVertex(vortex);
        if (va == -1) return neighbours;
        for (int i = 0; i < numVerts; i++)
            if (matAdj[va][i] != 0)
                neighbours.add((E) vertexs[i].get());
        return neighbours;
    }
}