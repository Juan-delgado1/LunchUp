package ListasEnlazadas;

public class LinkedList1 {
    // Definición de la clase de nodo
    class Node {
        int key;
        Node next;

        Node(int key) {
            this.key = key;
            this.next = null;
        }
    }

    // Referencias al inicio y al final de la lista
    private Node head;
    private Node tail;

    // Método para insertar un elemento al frente de la lista
    public void pushFront(int key) {
        Node newNode = new Node(key);
        newNode.next = head;
        head = newNode;
        if (tail == null) {
            tail = head;
        }
    }

    // Método para eliminar el primer elemento de la lista
    public void popFront() {
        if (head == null) {
            System.out.println("ERROR: empty list");
            return;
        }
        head = head.next;
        if (head == null) {
            tail = null;
        }
    }


    // Método para buscar un elemento en la lista
    public boolean buscar(int key) {
        Node current = head;
        while (current != null) {
            if (current.key == key) {
                return true; // Se encontró la clave
            }
            current = current.next;
        }
        return false; // No se encontró la clave
    }

    // Método para imprimir la lista del final al inicio
    public void printListReverse() {
        printReverseRecursive(head);
        System.out.println();
    }

    // Método recursivo para imprimir la lista del final al inicio
    private void printReverseRecursive(Node node) {
        if (node == null)
            return;
        printReverseRecursive(node.next);
        System.out.print(node.key + " ");
    }

    public static void main(String[] args) throws Exception {
        int L = 10000000;
        int i = 1;
    
        LinkedList list = new LinkedList();
    
        // Llenar la lista con elementos
        for (i = 1; i <= L; i++) {
            list.pushFront(i);
        }
    
        // Medir el tiempo antes de imprimir la lista en reversa
        long startTime = System.nanoTime();
    
        // Imprimir la lista del final al principio
        //list.printListReverse();
    
        // Medir el tiempo después de imprimir la lista en reversa
        long endTime = System.nanoTime();
    
        // Calcular el tiempo transcurrido
        long elapsedTime = endTime - startTime;
    
        // Mostrar el tiempo de impresión
        System.out.println("Tiempo de impresión de la lista en reversa en nanosegundos: " + elapsedTime);
    }
    
       //long endTime = System.currentTimeMillis();
       //long endTi = System.nanoTime();

       //System.out.println("Execution time in milliseconds: " + (endTime - startTime));
      // System.out.println("Execution time in nanoseconds: " + (endTi - startTi));
      // }
    
    //listaprueba.mostrarListaInicioFin();
    //listaprueba.mostrarListaFinInicio();

   
        //System.out.println("Lista original:");
        //list.printList();

        //list.popBack();
       // System.out.println("Lista después de eliminar el último elemento:");
        //list.printList();

        //long startTime = System.nanoTime(); // Tiempo de inicio
        //System.out.println("Lista del final al inicio:");
        //list.printListReverse();
       // long endTime = System.nanoTime(); // Tiempo de finalización

       // double elapsedTimeInMilliseconds = (endTime - startTime) / 1_000_000.0; // Convertir a milisegundos
       // System.out.println("Tiempo transcurrido en imprimir la lista del final al inicio: " + elapsedTimeInMilliseconds + " milisegundos");

       // int keyToFind = 2;
       // if (list.buscar(keyToFind)) {
         //   System.out.println("El elemento " + keyToFind + " está presente en la lista.");
       // } else {
         //   System.out.println("El elemento " + keyToFind + " no está presente en la lista.");
       // }
}




