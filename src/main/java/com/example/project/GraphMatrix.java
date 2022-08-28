package com.example.project;

import java.util.ArrayList;

public class GraphMatrix implements Graph {

    private int numVertices;
    private int[][] adjacency;
    
    //ArrayList de vertices que se actualizara al hacer el recorrido
    private ArrayList<Integer> vertices = new ArrayList <Integer>(); 

    public GraphMatrix(int numVertices) {
        this.numVertices = numVertices;
        this.adjacency = new int[numVertices][numVertices];

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                this.adjacency[i][j] = 0;
            }
        }
        
        //Se inicializa el ArrayList con los vertices
        for (int i = 0; i < numVertices; i++) {
        	vertices.add(i);
        }
    }

    @Override
    public boolean addEdge(int from, int to) {
        if (this.vertexDoesExist(from) && this.vertexDoesExist(to)) {
            this.adjacency[from][to] = 1;
            this.adjacency[to][from] = 1;
            return true;
        }
        return false;
    }

    @Override
    public boolean removeEdge(int from, int to) {
        if (this.vertexDoesExist(from) && this.vertexDoesExist(to)) {
            this.adjacency[from][to] = 0;
            this.adjacency[to][from] = 0;
            return true;
        }
        return false;
    }

    public boolean vertexDoesExist(int aVertex) {
        if (aVertex >= 0 && aVertex < this.numVertices) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Integer> depthFirstSearch(int n) {
        return this.depthFirstSearch(n, new ArrayList<Integer>());
    }

    public ArrayList<Integer> depthFirstSearch(int n, ArrayList<Integer> visited) {
        visited.add(n);
        //Un ciclo for que buscara el vertice de partida en el ArrayList y se elminara de la lista al ecnontrarlo
        for(int i = 0; i < this.vertices.size(); i++) {
        	if(this.vertices.get(i) == n) {
        		vertices.remove(i);
        	}
        }
        
        for (int i = 0; i < this.numVertices; i++) {
            if (this.adjacency[n][i] == 1 && !visited.contains(i)) {
                depthFirstSearch(i, visited);
            }
        }
        return visited;
    }

    public String toString() {
        String s = "    ";
        for (int i = 0; i < this.numVertices; i++) {
            s = s + String.valueOf(i) + " ";
        }
        s = s + " \n";

        for (int i = 0; i < this.numVertices; i++) {
            s = s + String.valueOf(i) + " : ";
            for (int j = 0; j < this.numVertices; j++) {
                s = s + String.valueOf(this.adjacency[i][j]) + " ";
            }
            s = s + "\n";
        }
        return s;
    }

    public int countConnectedComponents() {
    	int contador = 0; // Inicializamos con 0 porque se comenzo a contar nigun grafo

    	while(!vertices.isEmpty()) {
    		depthFirstSearch(vertices.get(0));
    		/*/Aumaentamos el contador por cada depthFirstSearch
    		   comenzando desde la posicion 0 del ArrayList de vertices, actualizable /*/
    		contador++;
    	}
    	return contador;
    }

    public static void main(String args[]) {
        GraphMatrix graph = new GraphMatrix(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(0, 4);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        System.out.println("The graph matrix:");
        System.out.println(graph);
        System.out.println("DFS:");
        System.out.println(graph.depthFirstSearch(0));
    }

}
