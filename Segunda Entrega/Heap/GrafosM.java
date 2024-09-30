import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

class GraphAdjacencyMatrix {
    private int[][] adjacencyMatrix;
    private Map<Integer, String> vertexMap;
    private int vertexCount;
    public static final DecimalFormat df = new DecimalFormat("0.0000"); 

    public GraphAdjacencyMatrix(int size) {
        this.adjacencyMatrix = new int[size][size];
        this.vertexMap = new HashMap<>();
        this.vertexCount = 0;
    }

    
    public double addVertex(String vertex) {
        long startTime = System.nanoTime();
        vertexMap.put(vertexCount++, vertex);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000.0; 
    }

    
    public double addEdge(int vertex1, int vertex2) {
        long startTime = System.nanoTime();
        adjacencyMatrix[vertex1][vertex2] = 1;
        adjacencyMatrix[vertex2][vertex1] = 1; 
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000.0; 
    }

    
    public double deleteVertex(int vertex) {
        long startTime = System.nanoTime();
        vertexMap.remove(vertex);
        for (int i = 0; i < vertexCount; i++) {
            adjacencyMatrix[vertex][i] = 0;
            adjacencyMatrix[i][vertex] = 0;
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000.0; 
    }

    
    public double deleteEdge(int vertex1, int vertex2) {
        long startTime = System.nanoTime();
        adjacencyMatrix[vertex1][vertex2] = 0;
        adjacencyMatrix[vertex2][vertex1] = 0;
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000.0; 
    }

    
    @SuppressWarnings("unused")
    public double findVertex(int vertex) {
        long startTime = System.nanoTime();
        boolean found = vertexMap.containsKey(vertex);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000.0; 
    }

    
    @SuppressWarnings("unused")
    public double findEdge(int vertex1, int vertex2) {
        long startTime = System.nanoTime();
        boolean found = adjacencyMatrix[vertex1][vertex2] == 1;
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000.0; 
    }

    
    public double routeDFS(int startVertex) {
        long startTime = System.nanoTime();
        boolean[] visited = new boolean[vertexCount];
        dfsHelper(startVertex, visited);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000.0; 
    }

    private void dfsHelper(int vertex, boolean[] visited) {
        if (!visited[vertex]) {
            visited[vertex] = true;
            for (int i = 0; i < vertexCount; i++) {
                if (adjacencyMatrix[vertex][i] == 1 && !visited[i]) {
                    dfsHelper(i, visited);
                }
            }
        }
    }
}

public class GrafosM {
    public static void main(String[] args) {
        GraphAdjacencyMatrix graph = new GraphAdjacencyMatrix(10000); 

        
        double totalVertexTime = 0;
        for (int i = 0; i < 10000; i++) {
            totalVertexTime += graph.addVertex("V" + i);
        }
        System.out.println("Tiempo total para agregar 100,000 vértices: " + GraphAdjacencyMatrix.df.format(totalVertexTime) + " ms");

        
        double totalEdgeTime = 0;
        for (int i = 0; i < 9999; i++) {
            totalEdgeTime += graph.addEdge(i, i + 1); 
        }
        System.out.println("Tiempo total para agregar 99,999 aristas: " + GraphAdjacencyMatrix.df.format(totalEdgeTime) + " ms");

        
        double deleteVertexTime = graph.deleteVertex(5000); 
        System.out.println("Tiempo para eliminar vértice 50,000: " + GraphAdjacencyMatrix.df.format(deleteVertexTime) + " ms");

        
        double deleteEdgeTime = graph.deleteEdge(1000, 2000);
        System.out.println("Tiempo para eliminar arista entre 100 y 200: " + GraphAdjacencyMatrix.df.format(deleteEdgeTime) + " ms");

        
        double findVertexTime = graph.findVertex(1000);
        System.out.println("Tiempo para encontrar vértice 10,000: " + GraphAdjacencyMatrix.df.format(findVertexTime) + " ms");

        
        double findEdgeTime = graph.findEdge(1000, 2000);
        System.out.println("Tiempo para encontrar arista entre 100 y 200: " + GraphAdjacencyMatrix.df.format(findEdgeTime) + " ms");

        
        double dfsTime = graph.routeDFS(0);
        System.out.println("Tiempo para DFS desde 0: " + GraphAdjacencyMatrix.df.format(dfsTime) + " ms");
    }
}

