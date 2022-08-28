package com.example.project;

import java.util.ArrayList;

public class GraphAdjacentList implements Graph {

    private ArrayList<Vertex> vertices;
    private int numVertices;

    public GraphAdjacentList() {
        vertices = new ArrayList<>();
    }

    // Agregar una arista desde un vertice 'from' a un vertice 'to'
    public boolean addEdge(int from, int to) {
        Vertex fromV = null, toV = null;
        for (Vertex v : vertices) {
            if (from == v.data) { // verificando si 'from' existe
                fromV = v;
            } else if (to == v.data) { // verificando si 'to' existe
                toV = v;
            }
            if (fromV != null && toV != null) {
                break; // ambos nodos deben existir, si no paramos
            }
        }
        if (fromV == null) {
            fromV = new Vertex(from);
            vertices.add(fromV);
            numVertices++;
        }
        if (toV == null) {
            toV = new Vertex(to);
            vertices.add(toV);
            numVertices++;
        }        
        return fromV.addAdjacentVertex(toV);
    }

    // Eliminamos la arista del vertice 'from' al vertice 'to'
    public boolean removeEdge(int from, int to) {
        Vertex fromV = null;
        for (Vertex v : vertices) {
            if (from == v.data) {
                fromV = v;
                break;
            }
        }
        if (fromV == null) {
            return false;
        }
        return fromV.removeAdjacentVertex(to);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Vertex v : vertices) {
            sb.append("Vertex: ");
            sb.append(v.data);
            sb.append("\n");
            sb.append("Adjacent vertices: ");
            for (Vertex v2 : v.adjacentVertices) {
                sb.append(v2.data);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public int getNumEdges(){
        int count = 0;
        for(int i = 0; i < this.vertices.size(); i++){
            count += this.vertices.get(i).adjacentVertices.size();
        }
        return count;
    }

    public int getNumVertices() {
        return numVertices;
    }

    public void setNumVertices(int numVertices) {
        this.numVertices = numVertices;
    }
    
        public ArrayList<Vertex> depthFirstSearch(Vertex n) {
        return this.depthFirstSearch(n, new ArrayList<Vertex>());
    }

    public ArrayList<Vertex> depthFirstSearch(Vertex n, ArrayList<Vertex> visited) {
        visited.add(n);
        //Un ciclo for que buscara el vertice de partida en el ArrayList y se elminara de la lista al ecnontrarlo
        for(int i = 0; i < this.vertices.size(); i++) {
        	if(this.vertices.get(i) == n) {
        		vertices.remove(i);
        	}
        }
        for (Vertex vertex : n.adjacentVertices) { 
            if (!vertices.contains(vertex)) {
                depthFirstSearch(n, visited); 
            }
        }
        return visited;
    }


    public int countConnectedComponents(){
        int contador = 0; // Inicializamos con 0 porque se comenzo a contar nigun grafo

	    ArrayList <Vertex> visitados = new ArrayList<Vertex>();
	     
	    for(Vertex v : vertices){    
	    	if (!visitados.contains(v)) {
            depthFirstSearch(v, visitados);
            contador++;
	    	}
	    }
		return contador;
    }

    public boolean removeVertex(int vertex){
        return false;
    }

    public static void main(String args[]) {
        GraphAdjacentList graph = new GraphAdjacentList();
        graph.addEdge(1, 2);
        graph.addEdge(1, 5);
        graph.addEdge(2, 5);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 1);        
        System.out.println(graph);
    }
}
