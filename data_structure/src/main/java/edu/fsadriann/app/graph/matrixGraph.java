package edu.fsadriann.app.graph;

import edu.fsadriann.app.linkedlist.singly.singly.LinkedList;
import edu.fsadriann.app.queue.list.Queue;
import edu.fsadriann.model.graph.Graph;

public class matrixGraph<E> implements Graph<E> {
    private int numVerts;
    private int numEdges;
    private static int maxVerts=20;
    private Vertex[] vertexs;
    double[][] matAdj;

    public matrixGraph() {
        this(maxVerts);
    }
    public matrixGraph(int maxVerts) {
        matAdj=new double[maxVerts][maxVerts];
        vertexs=new Vertex[maxVerts];
        for(int i=0;i<maxVerts;i++){
            for(int j=0;j<maxVerts;j++){
                matAdj[i][j]=0;
            }
        }
        numVerts=0;
        numEdges=0;
    }


    @Override
    public boolean addEdge(E vortex1, E vortex2) throws Exception {
        int va,vb;
        va=searchVertex(vortex1);
        vb=searchVertex(vortex2);
        if(va<0||vb<0){
            throw new Exception("Vertice no encontrado");
        }
        if(matAdj[va][vb]==0){
            numEdges++;
            matAdj[va][vb]=1;
            return true;
        }
        return false;
    }

    @Override
    public boolean addEdgeWithWeight(E vortex1, E vortex2, double weight) throws Exception {
        int va,vb;
        va=searchVertex(vortex1);
        vb=searchVertex(vortex2);
        if(va<0||vb<0){
            throw new Exception("Vertice no encontrado");
        }
        if(matAdj[va][vb]==0){
            matAdj[va][vb]=weight;
            numEdges++;
            return true;
        }
        return false;
    }
    @Override
    public boolean isAdjacent(E vortex1, E vortex2) throws Exception {
        int va,vb;
        va=searchVertex(vortex1);
        vb=searchVertex(vortex2);
        if(va<0||vb<0){
            throw new Exception("Vertice no existe");
        }
        return matAdj[va][vb]!=0;
    }

    @Override
    public boolean removeEdge(E vortex1, E vortex2) throws Exception{
        int va,vb;
        va=searchVertex(vortex1);
        vb=searchVertex(vortex2);
        if(va<0||vb<0){
            throw new Exception("Vertice no existe");
        }
        if(matAdj[va][vb]==0){
            return false;
        }
        matAdj[va][vb]=0;
        numEdges--;
        return true;
    }

    @Override
    public boolean addVortex(E vortex) {
        if(numVerts<maxVerts){
            Vertex<E> vertex=new Vertex<>(vortex);
            vertex.setNumVertex(numVerts);
            vertexs[numVerts++]=vertex;
            return true;
        }
        return false;
    }

    @Override
    public int searchVertex(E vortex){
        boolean found=false;
        int i=0;
        for(;(i<numVerts)&&(!found);){
            found=vertexs[i].get().equals(vortex);
            if(!found){
                i++;
            }
        }
        return (i<numVerts)?i:-1;
    }

    @Override
    public String seeMatAdj() {
        String text="";
        for(int i=0;i<numVerts;i++){
            for(int j=0;j<numVerts;j++){
                text+="["+matAdj[i][j]+"]";
            }
            text+="\n";
        }
        return text;
    }

    @Override
    public Vertex[] getVertexs() {
        return vertexs;
    }

    @Override
    public boolean removeVortex(E vortex) {
        int va=searchVertex(vortex);
        if(va==-1){
            return false;
        }
        for(int i=va;i<numVerts-1;i++){
            vertexs[i]=vertexs[i+1];
        }
        vertexs[numVerts-1]=null;
        for(int i=0;i<numVerts;i++){
            if(matAdj[va][i]!=0){
                numEdges--;
            }
        }
        for(int i=0;i<numVerts;i++){
            if(matAdj[i][va]!=0&&i!=va){
                numEdges--;
            }
        }

        for(int i=va;i<numVerts-1;i++){
            for(int j=0;j<numVerts;j++){
                matAdj[i][j]=matAdj[i+1][j];
            }
        }
        for(int i=0;i<numVerts;i++){
            for(int j=va;j<numVerts-1;j++){
                matAdj[i][j]=matAdj[i][j+1];
            }
        }
        numVerts--;
        return true;
    }

    @Override
    public int numberEdges() {
        return numEdges;
    }

    @Override
    public int numberVertex() {
        return numVerts;
    }

    @SuppressWarnings("unchecked")
    @Override
    public LinkedList<E> getNeighbours(E vortex) {
        LinkedList<E> neighbours=new LinkedList<>();
        int va=searchVertex(vortex);
        if(va==-1){
            return neighbours;
        }
        for(int i=0;i<numVerts;i++){
            if(matAdj[va][i]!=0){
                neighbours.add((E) vertexs[i].get());
            }
        }
        return neighbours;
    }

    @Override
    public int outDegree(E vertex) {
        int outDegree=0;
        int va=searchVertex(vertex);
        if(va==-1){
            return -1;
        }
        for(int i=0;i<numVerts;i++){
            if(matAdj[va][i]!=0){
                outDegree++;
            }
        }
        return outDegree;
    }

    @Override
    public int inDegree(E vortex){
        int inDegree=0;
        int va=searchVertex(vortex);
        if(va==-1){
            return -1;
        }
        for(int i=0;i<numVerts;i++){
            if(matAdj[i][va]!=0){
                inDegree++;
            }
        }
        return inDegree;
    }

    @Override
    public boolean existsPath(E startV, E endV) {
        int start=searchVertex(startV);
        int end=searchVertex(endV);

        if(start==-1||end==-1){
            return false;
        }
        boolean[] visited=new boolean[numVerts];
        Queue<Integer> queue=new Queue<>();
        queue.insert(start);
        visited[start]=true;

        while(!queue.isEmpty()){
            int current=queue.extract();
            if(current==end){
                return true;
            }

            for(int i=0;i<numVerts;i++){
                if(matAdj[current][i]!=0&&!visited[i]){
                    queue.insert(i);
                    visited[i]=true;
                }
            }
        }
        return false;
    }

    @Override
    public void dfs(E startV) {
        int start=searchVertex(startV);
        if(start==-1){
            return;
        }
        boolean[] visited=new boolean[numVerts];
        dfsRecursive(start,visited);


    }
    private void dfsRecursive(int current, boolean[] visited){
        visited[current]=true;
        System.out.print(vertexs[current].get()+"- > ");

        for(int i=0;i<numVerts;i++){
            if(matAdj[current][i]!=0&&!visited[i]){
                dfsRecursive(i,visited);
            }
        }
    }

    @Override
    public void bfs(E startV) {
        int start=searchVertex(startV);
        if(start==-1){
            return;
        }
        boolean[] visited=new boolean[numVerts];
        Queue<Integer> queue=new Queue<>();
        queue.insert(start);
        visited[start]=true;

        while(!queue.isEmpty()){
            int current=queue.extract();

            System.out.print(vertexs[current].get()+"- > ");
            for(int i=0;i<numVerts;i++){
                if(matAdj[current][i]!=0&&!visited[i]){
                    queue.insert(i);
                    visited[i]=true;
                }
            }
        }


    }

    @SuppressWarnings("unchecked")
    @Override
    public LinkedList<E> shortestPath(E startV, E endV) {
        LinkedList<E> path=new LinkedList<>();
        int start=searchVertex(startV);
        int end=searchVertex(endV);

        if(start==-1||end==-1){
            return path;
        }
        boolean[] visited=new boolean[numVerts];
        int[] parent=new int[numVerts];

        for(int i=0;i<numVerts;i++){
            parent[i]=-1;
        }

        Queue<Integer> queue=new Queue<>();
        queue.insert(start);
        visited[start]=true;

        while(!queue.isEmpty()){
            int current=queue.extract();
            if(current==end){
                break;
            }

            for(int i=0;i<numVerts;i++){
                if(matAdj[current][i]!=0&&!visited[i]){
                    visited[i]=true;
                    parent[i]=current;
                    queue.insert(i);
                }
            }
        }
        if(!visited[end]){
            return path;
        }
        for(int v=end;v!=-1;v=parent[v]){
            path.addFirst((E) vertexs[v].get());
        }
        return path;
    }

    @SuppressWarnings("unchecked")
    @Override
    public LinkedList<E> dijkstra(E startV,E endV) {
        LinkedList<E> dijkstra=new LinkedList<>();
        int start=searchVertex(startV);
        int end=searchVertex(endV);
        if(start==-1||end==-1){
            return dijkstra;
        }
        double[] dist=new double[numVerts];
        int[] parent=new int[numVerts];
        boolean[] visited=new boolean[numVerts];
        for(int i=0;i<numVerts;i++){
            dist[i]=Double.MAX_VALUE;
            parent[i]=-1;
        }
        dist[start]=0;

        for(int count=0;count<numVerts;count++){
            int u=-1;
            double min=Double.MAX_VALUE;

            for(int i=0;i<numVerts;i++){
                if(!visited[i]&&dist[i]<min){
                    min=dist[i];
                    u=i;
                }
            }
            if(u==-1){
                break;
            }
            visited[u]=true;
            for(int v=0;v<numVerts;v++){
                if(matAdj[u][v]!=0&&!visited[v]){
                    double newDist=dist[u]+matAdj[u][v];
                    if(newDist<dist[v]){
                        dist[v]=newDist;
                        parent[v]=u;
                    }
                }
            }
            if(u==end){
                break;
            }
        }
        if(dist[end]==Double.MAX_VALUE){
            return dijkstra;
        }
        for(int v=end;v!=-1;v=parent[v]){
            dijkstra.addFirst((E) vertexs[v].get());
        }
        return dijkstra;
    }
    @Override
    public double dijkstraWeight(E startV, E endV){
        int start=searchVertex(startV);
        int end=searchVertex(endV);
        if(start==-1||end==-1){
            return Double.MAX_VALUE;
        }
        double[] dist= new double[numVerts];
        boolean[] visited=new boolean[numVerts];

        for(int i=0;i<numVerts;i++){
            dist[i]=Double.MAX_VALUE;
        }
        dist[start]=0;
        for(int count=0;count<numVerts;count++){
            int u=-1;
            double min=Double.MAX_VALUE;

            for(int i=0;i<numVerts;i++){
                if(!visited[i]&&dist[i]<min){
                    min=dist[i];
                    u=i;
                }
            }
            if(u==-1){
                break;
            }
            visited[u]=true;

            for(int v=0;v<numVerts;v++){
                if(matAdj[u][v]!=0&&!visited[v]){
                    double newDist=dist[u]+matAdj[u][v];
                    if(newDist<dist[v]){
                        dist[v]=newDist;
                    }
                }
            }
        }
        if(dist[end]==Double.MAX_VALUE){
            return -1;
        }
        return dist[end];
    }

    @Override
    public boolean isConnected() {
        if(isEmpty()){
            return false;
        }
        int start=0;
        boolean[] visited=new boolean[numVerts];
        Queue<Integer> queue=new Queue<>();
        queue.insert(start);
        visited[start]=true;

        while(!queue.isEmpty()){
            int current=queue.extract();
            for(int i=0;i<numVerts;i++){
                if((matAdj[current][i]!=0||matAdj[i][current]!=0)&&!visited[i]){
                    queue.insert(i);
                    visited[i]=true;
                }
            }
        }

        for(boolean x:visited){
            if(!x){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean hasCycle() {
        boolean[] visited=new boolean[numVerts];
        boolean[] recStack=new boolean[numVerts];
        for(int i=0;i<numVerts;i++){
            if(!visited[i]){
                if(hasCycleDFS(i,visited,recStack)){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean hasCycleDFS(int i,boolean[] visited,boolean[] recStack){
        visited[i]=true;
        recStack[i]=true;
        for(int j=0;j<numVerts;j++){
            if(matAdj[i][j]!=0){
                if(!visited[j]){
                    if(hasCycleDFS(j,visited,recStack)){
                        return true;
                    }
                }else if(recStack[j]){
                    return true;
                }
            }
        }
        recStack[i]=false;
        return false;
    }
    @Override
    public boolean isEmpty(){
        return numVerts==0;
    }
}