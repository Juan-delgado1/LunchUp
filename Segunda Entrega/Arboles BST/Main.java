import java.util.Random;

class Nodo {
    int valor;
    Nodo izquierdo, derecho;

    public Nodo(int item) {
        valor = item;
        izquierdo = derecho = null;
    }
}

class BST {
    Nodo raiz;

    public BST() {
        raiz = null;
    }

    
    void insertar(int valor) {
        Nodo nuevoNodo = new Nodo(valor);
        
        if (raiz == null) {
            raiz = nuevoNodo;
            return;
        }

        Nodo actual = raiz;
        Nodo padre = null;

        while (true) {
            padre = actual;

            if (valor < actual.valor) {
                actual = actual.izquierdo;
                if (actual == null) {
                    padre.izquierdo = nuevoNodo;
                    break;
                }
            } else if (valor > actual.valor) {
                actual = actual.derecho;
                if (actual == null) {
                    padre.derecho = nuevoNodo;
                    break;
                }
            } else {
                break; 
            }
        }
    }

    
    void inOrder() {
        if (raiz == null)
            return;

        Nodo actual = raiz;
        Nodo pre;

        while (actual != null) {
            if (actual.izquierdo == null) {
                System.out.print(actual.valor + " ");
                actual = actual.derecho;
            } else {
                pre = actual.izquierdo;
                while (pre.derecho != null && pre.derecho != actual) {
                    pre = pre.derecho;
                }

                if (pre.derecho == null) {
                    pre.derecho = actual;
                    actual = actual.izquierdo;
                } else {
                    pre.derecho = null;
                    System.out.print(actual.valor + " ");
                    actual = actual.derecho;
                }
            }
        }
    }

    
    boolean search(int valor) {
        Nodo actual = raiz;
        while (actual != null) {
            if (valor == actual.valor) return true;
            if (valor < actual.valor) actual = actual.izquierdo;
            else actual = actual.derecho;
        }
        return false;
    }

    
    void delete(int valor) {
        raiz = deleteRec(raiz, valor);
    }

    
    Nodo deleteRec(Nodo raiz, int valor) {
        if (raiz == null) return raiz;

        if (valor < raiz.valor) {
            raiz.izquierdo = deleteRec(raiz.izquierdo, valor);
        } else if (valor > raiz.valor) {
            raiz.derecho = deleteRec(raiz.derecho, valor);
        } else {
            
            if (raiz.izquierdo == null)
                return raiz.derecho;
            else if (raiz.derecho == null)
                return raiz.izquierdo;

           
            raiz.valor = findMin(raiz.derecho);

            
            raiz.derecho = deleteRec(raiz.derecho, raiz.valor);
        }
        return raiz;
    }

    
    int findMin(Nodo raiz) {
        int minv = raiz.valor;
        while (raiz.izquierdo != null) {
            minv = raiz.izquierdo.valor;
            raiz = raiz.izquierdo;
        }
        return minv;
    }


    void balance() {
        int[] valores = new int[1000];
        int[] indice = {0};
        almacenarInOrder(raiz, valores, indice);
        raiz = balancearDesdeArray(valores, 0, indice[0] - 1);
    }

    
    void almacenarInOrder(Nodo nodo, int[] valores, int[] indice) {
        if (nodo == null) return;

        almacenarInOrder(nodo.izquierdo, valores, indice);
        valores[indice[0]++] = nodo.valor;
        almacenarInOrder(nodo.derecho, valores, indice);
    }

    
    Nodo balancearDesdeArray(int[] valores, int inicio, int fin) {
        if (inicio > fin) return null;

        int medio = (inicio + fin) / 2;
        Nodo nodo = new Nodo(valores[medio]);

        nodo.izquierdo = balancearDesdeArray(valores, inicio, medio - 1);
        nodo.derecho = balancearDesdeArray(valores, medio + 1, fin);

        return nodo;
    }
}

public class Main {
    public static void main(String[] args) {
        BST arbol = new BST();
        Random rand = new Random();

        
        long totalStartTime = System.nanoTime();


        for (int i = 0; i < 10000; i++) {
            int numeroAleatorio = rand.nextInt(1000);
            arbol.insertar(numeroAleatorio);
        }


        long totalEndTime = System.nanoTime();

        
        System.out.println("Recorrido in-order del BST (iterativo):");
        arbol.inOrder();
        System.out.println();

        // Calcular y mostrar el tiempo total de inserción
        long totalDuration = totalEndTime - totalStartTime;
        System.out.println("Tiempo total de inserción (iterativo): " + totalDuration + " nanosegundos.");

        // Buscar un solo elemento específico
        int elementoABuscar = 1000;
        long searchStartTime = System.nanoTime();
        boolean encontrado = arbol.search(elementoABuscar);
        long searchEndTime = System.nanoTime();
        long searchDuration = searchEndTime - searchStartTime;
        System.out.println("Tiempo de búsqueda (iterativo) del elemento " + elementoABuscar + ": " + searchDuration + " nanosegundos. Encontrado: " + encontrado);

        // Eliminar un solo elemento específico
        long deleteStartTime = System.nanoTime();
        arbol.delete(elementoABuscar);
        long deleteEndTime = System.nanoTime();
        long deleteDuration = deleteEndTime - deleteStartTime;
        System.out.println("Tiempo de eliminación (iterativo) del elemento " + elementoABuscar + ": " + deleteDuration + " nanosegundos.");

        // Balancear el árbol
        long balanceStartTime = System.nanoTime();
        arbol.balance();
        long balanceEndTime = System.nanoTime();
        long balanceDuration = balanceEndTime - balanceStartTime;
        System.out.println("Tiempo de balanceo del árbol: " + balanceDuration + " nanosegundos.");
    }
}