
package disjointset_nr;

import java.util.ArrayList;

import java.util.List;

public class DisjointSet_NR {
    
    //Atributos
    private int[] parent; // Array para almacenar el padre de cada elemento
    private int[] rank; // Array para almacenar el rango de cada elemento
    private int[] initialGroup; // Array para almacenar el grupo inicial de cada elemento
    
    //Métodos    

    // Constructor
    public DisjointSet_NR(int n) {
        parent = new int[n]; // parent corresponde al representante de cada grupo
        rank = new int[n];
        initialGroup = new int[n];

        // Inicialmente cada elemento es su propio padre y representante y esta en su propio grupo
        for (int i = 0; i < n; i++) {
            parent[i] = i; 
            rank[i] = 0; // Rango inicial de cada elemento es 0
            initialGroup[i] = i;
            
        }
    }
    
    // getLength(): Para saber cuantos grupos hay
    public int getLength(){  
        return parent.length;
    }

    // find() casi constante O(1): Encontrar el representante (raíz) del conjunto al que pertenece el elemento x
    public int find(int x) {
        // Si x no es su propio padre, no es representante de su grupo
        if (parent[x] != x) {
            // Se usa recursion hasta llegar al representante del grupo
            parent[x] = find(parent[x]);
        }

        // Devuelve el índice o representante del conjunto
        return parent[x];
    }

    // union(x, y)  O(1): Unir dos conjuntos NO SE USA Union by Rank
    public void union(int x, int y) {
        // Encuentra los representantes de los conjuntos
        int parentA = find(x);
        int parentB = find(y);

        // Si los conjuntos son diferentes se realiza una unión, sino no es necesario
        if (parentA != parentB) {
            // Hace a parentB padre de parentA sin tener en cuenta el rango
            parent[parentA] = parentB;
        }
        
    }
    

    // reset(x) O(1): Devuelve un elemento a su grupo inicial
    public void reset(int x) {
        parent[x] = initialGroup[x];
    }
    
    // printGroups() O(n^2): Imprime los grupos y sus elementos
    public void printGroups() {
        //

        // Crea un array de listas para almacenar los grupos y sus elementos
        List<Integer>[] groups = new List[parent.length];

        // Ciclo para inicializar una lista para cada grupo
        for (int i = 0; i < parent.length; i++) {
            groups[i] = new ArrayList<>();
        }

        // Recorre todos los elementos y encuentra su padre para unirlo al grupo correspondiente
        for (int i = 0; i < parent.length; i++) {
        
            int root = find(i);
            groups[root].add(i);
        }

        // Imprime los grupos y sus elementos
        for (int i = 0; i < groups.length; i++) {
            // Solo imprimir el grupo si tiene elementos, para evitar errores
            if (groups[i].size() > 0) {
                System.out.println("Grupo " + i + ": " + groups[i]);
            }
        }
    }

    
    public static void main(String[] args) {
        int n = 10000;
        
        System.out.println("Para "+n+":");
        //CREAR
        long CrearI = System.nanoTime();        
        DisjointSet_NR conjuntos = new DisjointSet_NR(n);          
        long CrearO = System.nanoTime();
        
        //FIND
        long findI = System.nanoTime(); 
        conjuntos.find(n/2);
        long findO = System.nanoTime();
        
        //UNION
        long unionI = System.nanoTime(); 
        for(int i = 0; i < n-1; i++){
            conjuntos.union(i, i+1);
        }
        long unionO = System.nanoTime();
        
        //PRINTGROUPS
        long printI = System.nanoTime(); 
        conjuntos.printGroups();
        long printO = System.nanoTime();
        
        //RESET
        long resetI = System.nanoTime(); 
        for(int i = 0; i < n; i++){
            conjuntos.reset(i);
        }
        long resetO = System.nanoTime();
        
        
       
       
       
       
        long CREAR = (CrearO - CrearI);
        double crear = (double)CREAR/1000000;
        System.out.println("Crear = " + crear + " miliseconds");
        
        long FIND = (findO - findI);
        double find = (double)FIND/1000000;
        System.out.println("Find = " + find + " miliseconds");
        
        long UNION = (unionO - unionI);
        double union = (double)UNION/1000000;
        System.out.println("Union = " + union+ " miliseconds");
        
        long RESET = (resetO - resetI);
        double reset = (double)RESET/1000000;
        System.out.println("Reset = " + reset+ " miliseconds");
        
        long PRINT = (printO - printI);
        double print = (double)PRINT/1000000;
        System.out.println("PrintGroups = " + print+ " miliseconds");
    }
    
}
