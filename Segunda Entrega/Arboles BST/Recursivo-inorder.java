import java.util.Random;

class Nodo {
    int valor;
    Nodo izquierdo, derecho;

    public Nodo(int item) {
        valor = item;
        izquierdo = derecho = null;
    }
}

class BSTRecursivo {
    Nodo raiz;

    public BSTRecursivo() {
        raiz = null;
    }

    
    Nodo insertarRec(Nodo raiz, int valor) {
        if (raiz == null) {
            raiz = new Nodo(valor);
            return raiz;
        }

        if (valor < raiz.valor)
            raiz.izquierdo = insertarRec(raiz.izquierdo, valor);
        else if (valor > raiz.valor)
            raiz.derecho = insertarRec(raiz.derecho, valor);

        return raiz;
    }

    
    void insertar(int valor) {
        raiz = insertarRec(raiz, valor);
    }

    void inOrderRec(Nodo raiz) {
        if (raiz != null) {
            inOrderRec(raiz.izquierdo);
            System.out.print(raiz.valor + " ");
            inOrderRec(raiz.derecho);
        }
    }


    void inOrder() {
        inOrderRec(raiz);
    }

    
    boolean searchRec(Nodo raiz, int valor) {
        if (raiz == null) return false;
        if (valor == raiz.valor) return true;
        if (valor < raiz.valor) return searchRec(raiz.izquierdo, valor);
        return searchRec(raiz.derecho, valor);
    }

   
    boolean search(int valor) {
        return searchRec(raiz, valor);
    }

    
    Nodo deleteRec(Nodo raiz, int valor) {
        if (raiz == null) return null;

        if (valor < raiz.valor)
            raiz.izquierdo = deleteRec(raiz.izquierdo, valor);
        else if (valor > raiz.valor)
            raiz.derecho = deleteRec(raiz.derecho, valor);
        else {
            if (raiz.izquierdo == null) return raiz.derecho;
            if (raiz.derecho == null) return raiz.izquierdo;

            Nodo temp = findMinRec(raiz.derecho);
            raiz.valor = temp.valor;
            raiz.derecho = deleteRec(raiz.derecho, temp.valor);
        }

        return raiz;
    }

    
    void delete(int valor) {
        raiz = deleteRec(raiz, valor);
    }

    
    int heightRec(Nodo raiz) {
        if (raiz == null) return 0;
        return 1 + Math.max(heightRec(raiz.izquierdo), heightRec(raiz.derecho));
    }

    
    int height() {
        return heightRec(raiz);
    }


    int balanceRec(Nodo raiz) {
        if (raiz == null) return 0;
        return heightRec(raiz.izquierdo) - heightRec(raiz.derecho);
    }

    
    int balance() {
        return balanceRec(raiz);
    }
}

public class MainRecursivo {
    public static void main(String[] args) {
        BSTRecursivo arbol = new BSTRecursivo();
        Random rand = new Random();

        // Medir tiempo total antes de la inserción
        long totalStartTime = System.nanoTime();

        // Insertar números aleatorios 
        for (int i = 0; i < 1000; i++) {
            int numeroAleatorio = rand.nextInt(1000) + 1;
            arbol.insertar(numeroAleatorio);
        }

        // Medir tiempo total después de la inserción
        long totalEndTime = System.nanoTime();

        // Recorrido in-order
        System.out.println("Recorrido in-order del BST (recursivo):");
        arbol.inOrder();
        System.out.println();

        // Calcular y mostrar el tiempo total de inserción
        long totalDuration = totalEndTime - totalStartTime;
        System.out.println("Tiempo total de inserción (recursivo): " + totalDuration + " nanosegundos.");

        // Buscar un solo elemento específico
        int elementoABuscar = rand.nextInt(1000) + 1;
        long searchStartTime = System.nanoTime();
        boolean encontrado = arbol.search(elementoABuscar);
        long searchEndTime = System.nanoTime();
        long searchDuration = searchEndTime - searchStartTime;
        System.out.println("Tiempo de búsqueda (recursivo) del elemento " + elementoABuscar + ": " + searchDuration + " nanosegundos. Encontrado: " + encontrado);

        // Eliminar un solo elemento específico
        long deleteStartTime = System.nanoTime();
        arbol.delete(elementoABuscar);
        long deleteEndTime = System.nanoTime();
        long deleteDuration = deleteEndTime - deleteStartTime;
        System.out.println("Tiempo de eliminación (recursivo) del elemento " + elementoABuscar + ": " + deleteDuration + " nanosegundos.");

        // Calcular el balance del árbol
        long balanceStartTime = System.nanoTime();
        int balance = arbol.balance();
        long balanceEndTime = System.nanoTime();
        long balanceDuration = balanceEndTime - balanceStartTime;
        System.out.println("Tiempo para calcular el balance del árbol (recursivo): " + balanceDuration + " nanosegundos. Balance: " + balance);
    }
}


