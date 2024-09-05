package ListasEnlazadas;
import java.util.Random;

public class LinkedList {

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
  
    // Método para insertar 100.000.000 elementos al frente de la lista
    public void pushFrontBulk(int numElements) {
      Random random = new Random();
      for (int i = 0; i < numElements; i++) {
        pushFront(random.nextInt());
      }
    }
  
    // Método para imprimir la lista
    public void printAll() {
      Node current = head;
      while (current != null) {
        System.out.println(current.key);
        current = current.next;
      }
    }
  
    // Método toString para concatenar las representaciones de los nodos
    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      Node current = head;
      while (current != null) {
        sb.append(current.key).append(" ");
        current = current.next;
      }
      return sb.toString();
    }
  
    public static void main(String[] args) {
      LinkedList list = new LinkedList();
      list.pushFrontBulk(100_000_000);
  
      // Opción 1: Imprimir usando printAll()
      list.printAll();
  
      // Opción 2: Imprimir usando toString()
      //String listString = list.toString();
      //System.out.println(listString);
    }
  }
  