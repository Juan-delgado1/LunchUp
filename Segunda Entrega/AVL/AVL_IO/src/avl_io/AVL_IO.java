package avl_io;
import java.util.Random;


class Nodo {
    int key;
    int height;
    Nodo left, right;

    Nodo(int d) {
        key = d;
        height = 1; 
    }
}


class AVL_IO {
    private Nodo raiz;

    //Obtener altura O(1): 
    private int height(Nodo n) { 
        return (n == null) ? 0 : n.height;
    }

    // Obtener balance de la raíz O(1):
    public int balance() {
        return balance(raiz);
    }
    
    private int balance(Nodo n) {
        return (n == null) ? 0 : height(n.left) - height(n.right);
    }

    // Rotación a la derecha O(1):
    private Nodo RotateRight(Nodo y) {
        Nodo x = y.left;
        Nodo T2 = x.right;
        
        x.right = y;
        y.left = T2;
        
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // Rotación a la izquierda O(1):
    private Nodo RotateLeft(Nodo x) {
        Nodo y = x.right;
        Nodo T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // insertar un hijo O(log n):
    public void insert(int key) {
        raiz = insert(raiz, key);
    }

   
    private Nodo insert(Nodo nodo, int key) {
        

        // Insertar el nodo
        if (nodo == null) {
            return new Nodo(key);
        }

        if (key < nodo.key) {
            nodo.left = insert(nodo.left, key);
        } else if (key > nodo.key) {
            nodo.right = insert(nodo.right, key);
        } else { 
            return nodo;
        }

        //actualizar alturas 
        nodo.height = 1 + Math.max(height(nodo.left), height(nodo.right));

        // verificar que el arból esté balanceado
        int Bal = balance(nodo); 

        // Caso Izquierda-Izquierda
        if (Bal > 1 && key < nodo.left.key) {
            return RotateRight(nodo);
        }

        // Caso Derecha-Derecha
        if (Bal < -1 && key > nodo.right.key) {
            return RotateLeft(nodo);
        }

        // Caso Izquierda-Derecha
        if (Bal > 1 && key > nodo.left.key) {
            nodo.left = RotateLeft(nodo.left);
            return RotateRight(nodo);
        }

        // Caso Derecha-Izquierda
        if (Bal < -1 && key < nodo.right.key) {
            nodo.right = RotateRight(nodo.right);
            return RotateLeft(nodo);
        }

        return nodo; 
    }

    // Buscar un valor en el arbol O(log n):
    public boolean search(int key) {
        return search(raiz, key) != null;
    }

    private Nodo search(Nodo nodo, int key) {
        if (nodo == null || nodo.key == key) {
            return nodo;
        }

        if (key < nodo.key) {
            return search(nodo.left, key);
        } else {
            return search(nodo.right, key);
        }
    }

    // Eliminar un nodo O(log n):
    public void delete(int key) {
        raiz = delete(raiz, key);
    }

    private Nodo delete(Nodo nodo, int key) {
        
        // eliminar nodo
        if (nodo == null) {
            return nodo;
        }

        if (key < nodo.key) {
            nodo.left = delete(nodo.left, key);
        } else if (key > nodo.key) {
            nodo.right = delete(nodo.right, key);
        } else {
            // Nodo con un solo hijo o sin hijos
            if ((nodo.left == null) || (nodo.right == null)) {
                Nodo temp = (nodo.left != null) ? nodo.left : nodo.right;

                // sin hijos
                if (temp == null) {
                    temp = nodo;
                    nodo = null;
                } else { // un solo hijo
                    nodo = temp;
                }
            } else {
                // dos hijos, sucesor InOrder
                Nodo temp = minVal(nodo.right);
                nodo.key = temp.key;
                nodo.right = delete(nodo.right, temp.key);
            }
        }

        // Si el árobl es de un solo nodo
        if (nodo == null) {
            return nodo;
        }




        //actualizar alturas
        nodo.height = Math.max(height(nodo.left), height(nodo.right)) + 1;

        // verificar que este balanceado
        int Bal = balance(nodo);

        // Caso Izquierda-Izquierda
        if (Bal > 1 && balance(nodo.left) >= 0) {
            return RotateRight(nodo);
        }

        // Caso Izquierda-Derecha
        if (Bal > 1 && balance(nodo.left) < 0) {
            nodo.left = RotateLeft(nodo.left);
            return RotateRight(nodo);
        }

        // Caso Derecha-Derecha
        if (Bal < -1 && balance(nodo.right) <= 0) {
            return RotateLeft(nodo);
        }

        // Caso Derecha-Izquierda
        if (Bal < -1 && balance(nodo.right) > 0) {
            nodo.right = RotateRight(nodo.right);
            return RotateLeft(nodo);
        }

        return nodo; 
    }

    // nodo con menor valor O(log n):
    private Nodo minVal(Nodo nodo) {
        Nodo actual = nodo;
        while (actual.left != null) {
            actual = actual.left;
        }
        return actual;
    }

    // Imprimir elementos InOrder O(n):
    public void InOrderRec() {
        InOrderRec(raiz);
    }

    private void InOrderRec(Nodo nodo) {
        if (nodo != null) {
            InOrderRec(nodo.left);
            System.out.print(nodo.key + " ");
            InOrderRec(nodo.right);
        }
    }


    public static void main(String[] args) {
        AVL_IO arbol = new AVL_IO();
        Random rand = new Random();

        // Medir tiempo total antes de la inserción
        long totalStartTime = System.nanoTime();

        // Insertar números aleatorios 
        for (int i = 0; i < 100000000; i++) {
            int numeroAleatorio = rand.nextInt(1000) + 1;
            arbol.insert(numeroAleatorio);
        }

        // Medir tiempo total después de la inserción
        long totalEndTime = System.nanoTime();

        // Recorrido in-order
        System.out.println("Recorrido in-order del AVL (recursivo):");
        
        arbol.InOrderRec();
        
        System.out.println(" ");
        

        // Calcular y mostrar el tiempo total de inserción
        long totalDuration = (totalEndTime - totalStartTime) ;
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


