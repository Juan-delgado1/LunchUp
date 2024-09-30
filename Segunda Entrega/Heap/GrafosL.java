import java.text.DecimalFormat;
import java.util.*;

class GraphAdjacencyList {
    private Map<String, List<String>> adjacencyList;
    @SuppressWarnings("unused")
    public static final DecimalFormat df = new DecimalFormat("0.0000");

    public GraphAdjacencyList() {
        this.adjacencyList = new HashMap<>();
    }

   
    public double addVertex(String vertex) {
        long startTime = System.nanoTime();
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000.0; 
    }

    
    public double addEdge(String vertex1, String vertex2) {
        long startTime = System.nanoTime();
        adjacencyList.putIfAbsent(vertex1, new ArrayList<>());
        adjacencyList.putIfAbsent(vertex2, new ArrayList<>());
        adjacencyList.get(vertex1).add(vertex2);
        adjacencyList.get(vertex2).add(vertex1); 
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000.0; 
    }

    
    public double deleteVertex(String vertex) {
        long startTime = System.nanoTime();
        adjacencyList.remove(vertex);
        for (List<String> neighbors : adjacencyList.values()) {
            neighbors.remove(vertex);
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000.0; 
    }

    
    public double deleteEdge(String vertex1, String vertex2) {
        long startTime = System.nanoTime();
        adjacencyList.getOrDefault(vertex1, new ArrayList<>()).remove(vertex2);
        adjacencyList.getOrDefault(vertex2, new ArrayList<>()).remove(vertex1);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000.0; 
    }

    
    public double findVertex(String vertex) {
        long startTime = System.nanoTime();
        @SuppressWarnings("unused")
        boolean found = adjacencyList.containsKey(vertex);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000.0; 
    }

    
    public double findEdge(String vertex1, String vertex2) {
        long startTime = System.nanoTime();
        @SuppressWarnings("unused")
        boolean found = adjacencyList.getOrDefault(vertex1, new ArrayList<>()).contains(vertex2);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000.0; 
    }

    
    public double routeDFS(String startVertex) {
        long startTime = System.nanoTime();
        Set<String> visited = new HashSet<>();
        dfsHelper(startVertex, visited);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000.0; 
    }

    private void dfsHelper(String vertex, Set<String> visited) {
        if (!visited.contains(vertex)) {
            visited.add(vertex);
            for (String neighbor : adjacencyList.getOrDefault(vertex, new ArrayList<>())) {
                dfsHelper(neighbor, visited);
            }
        }
    }
}

public class GrafosL {
    public static void main(String[] args) {
        GraphAdjacencyList graph = new GraphAdjacencyList();

        
        double totalVertexTime = 0;
        for (int i = 0; i < 10000; i++) {
            totalVertexTime += graph.addVertex("V" + i);
        }
        System.out.println("Tiempo total para agregar vértices: " + GraphAdjacencyList.df.format(totalVertexTime) + " ms");

        
        double totalEdgeTime = 0;
        for (int i = 0; i < 9999; i++) {
            totalEdgeTime += graph.addEdge("V" + i, "V" + (i + 1));
        }
        System.out.println("Tiempo total para agregar aristas: " + GraphAdjacencyList.df.format(totalEdgeTime) + " ms");

        
        String vertexToDelete = "V50000";
        double deleteVertexTime = graph.deleteVertex(vertexToDelete);
        System.out.println("Tiempo para eliminar vértice '" + vertexToDelete + "': " + GraphAdjacencyList.df.format(deleteVertexTime) + " ms");

        
        String vertex1 = "V100";
        String vertex2 = "V101";
        double deleteEdgeTime = graph.deleteEdge(vertex1, vertex2);
        System.out.println("Tiempo para eliminar arista entre '" + vertex1 + "' y '" + vertex2 + "': " + GraphAdjacencyList.df.format(deleteEdgeTime) + " ms");

        
        double findVertexTime = graph.findVertex("V1000");
        System.out.println("Tiempo para encontrar vértice : " + GraphAdjacencyList.df.format(findVertexTime) + " ms");

        
        double findEdgeTime = graph.findEdge("V100", "V101");
        System.out.println("Tiempo para encontrar arista : " + GraphAdjacencyList.df.format(findEdgeTime) + " ms");

        
        double dfsTime = graph.routeDFS("V0");
        System.out.println("Tiempo para DFS desde : " + GraphAdjacencyList.df.format(dfsTime) + " ms");
    }
}



