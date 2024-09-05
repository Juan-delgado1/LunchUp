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

    
    void preOrder() {
        if (raiz == null) return;

        Nodo[] pila = new Nodo[1000];
        int top = -1;

        pila[++top] = raiz;

        while (top >= 0) {
            Nodo actual = pila[top--];
            System.out.print(actual.valor + " ");

            
            if (actual.derecho != null) pila[++top] = actual.derecho;
            if (actual.izquierdo != null) pila[++top] = actual.izquierdo;
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
        Nodo actual = raiz;
        Nodo padre = null;
        boolean esIzquierdo = false;

        while (actual != null && actual.valor != valor) {
            padre = actual;
            if (valor < actual.valor) {
                esIzquierdo = true;
                actual = actual.izquierdo;
            } else {
                esIzquierdo = false;
                actual = actual.derecho;
            }
        }

        if (actual == null) return; 

        
        if (actual.izquierdo == null && actual.derecho == null) {
            if (actual == raiz) {
                raiz = null;
            } else if (esIzquierdo) {
                padre.izquierdo = null;
            } else {
                padre.derecho = null;
            }
        }

        
        else if (actual.izquierdo == null) {
            if (actual == raiz) {
                raiz = actual.derecho;
            } else if (esIzquierdo) {
                padre.izquierdo = actual.derecho;
            } else {
                padre.derecho = actual.derecho;
            }
        } else if (actual.derecho == null) {
            if (actual == raiz) {
                raiz = actual.izquierdo;
            } else if (esIzquierdo) {
                padre.izquierdo = actual.izquierdo;
            } else {
                padre.derecho = actual.izquierdo;
            }
        }

        
        else {
            Nodo sucesor = getSucesor(actual);

            if (actual == raiz) {
                raiz = sucesor;
            } else if (esIzquierdo) {
                padre.izquierdo = sucesor;
            } else {
                padre.derecho = sucesor;
            }

            sucesor.izquierdo = actual.izquierdo;
        }
    }

    
    Nodo getSucesor(Nodo nodo) {
        Nodo sucesorPadre = nodo;
        Nodo sucesor = nodo;
        Nodo actual = nodo.derecho;

        while (actual != null) {
            sucesorPadre = sucesor;
            sucesor = actual;
            actual = actual.izquierdo;
        }

        if (sucesor != nodo.derecho) {
            sucesorPadre.izquierdo = sucesor.derecho;
            sucesor.derecho = nodo.derecho;
        }

        return sucesor;
    }

    
    void balance() {
        int[] valores = new int[1000];
        int[] indice = {0};
        almacenarInOrder(raiz, valores, indice);
        raiz = balancearDesdeArray(valores, 0, indice[0] - 1);
    }

    
    void almacenarInOrder(Nodo nodo, int[] valores, int[] indice) {
        Nodo[] pila = new Nodo[1000];
        int top = -1;
        Nodo actual = nodo;

        while (actual != null || top >= 0) {
            while (actual != null) {
                pila[++top] = actual;
                actual = actual.izquierdo;
            }
            actual = pila[top--];
            valores[indice[0]++] = actual.valor;
            actual = actual.derecho;
        }
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

        // Medir tiempo total antes de la inserción
        long totalStartTime = System.nanoTime();

        // Insertar números aleatorios 
        for (int i = 0; i < 100000000; i++) {
            int numeroAleatorio = rand.nextInt(1000);
            arbol.insertar(numeroAleatorio);
        }

        // Medir tiempo total después de la inserción
        long totalEndTime = System.nanoTime();

        // Recorrido pre-order
        System.out.println("Recorrido pre-order del BST (iterativo):");
        arbol.preOrder();
        System.out.println();

        // Calcular y mostrar el tiempo total de inserción
        long totalDuration = totalEndTime - totalStartTime;
        System.out.println("Tiempo total de inserción (iterativo): " + totalDuration + " nanosegundos.");

        // Buscar un solo elemento específico
        int elementoABuscar = 999;
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