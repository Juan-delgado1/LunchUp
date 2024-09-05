package Stacks;
import Usuarios.*;

public class LinkedStack<T> {

    class Node {
      T elem;
      Node Next;
  
      public Node(T o) {
        elem = o;
        Next = null;
      }
    }
  
    Node end;
    int size;
  
    public LinkedStack() {
      end = null;
      size = 0;
    }
  
    // Método push para insertar objetos en el stack
    public void push(T o) {
      Node nodo = new Node(o);
      if (end == null)
        end = nodo;
      else {
        nodo.Next = end;
        end = nodo;
      }
      size++;
    }
  
    // Método pop para sacar objetos de la pila
    public T pop() {
      if (end == null)
        return null;
      T o = end.elem;
      end = end.Next;
      size--;
      return o;
    }
  
    // Método isEmpty para ver si está vacía
    public boolean isEmpty() {
      return (size == 0);
    }
    
    // Método size
    public int size() {
      return size;
    }
  
    // Método top para ver el objeto más arriba de la pila sin sacarlo
    public T top() {
      if (end == null)
        return null;
      else
        return end.elem;
    }

    // Método search para buscar un objeto en la pila
    public int search(T o){
      Node aux = end;
      int n = 1;
      while(aux != null){
        if ( o.equals(aux.elem) ){
          return n;         
        }
        else{
          aux = aux.Next;
          n++;
        }
      }
      return -1; // return -1 if the object is not found
    }

    // Método printAll para imprimir de final a inicio
    public void printToBack(){
      Node aux = end;
      for(int i = 1; i <= this.size; i++){
        T o = aux.elem;
        if (o instanceof Usuario) {
          Usuario user = (Usuario) o;
          System.out.println("[ "+user.getNombre()+ " ]");
        } else {
          System.out.println(o);
        }
        aux = aux.Next;
      }
    }
  
    public static void main(String[] args) {
      LinkedStack<String> lstack = new LinkedStack<>();

      int n = 10000000;
      int x = 0;
      for (int i = 1; i<=n ; i++){
        x++;
        lstack.push("Holi" + x);
      }

      long startTime = System.currentTimeMillis();
      long startTi = System.nanoTime();

      lstack.printToBack();

      long endTi = System.nanoTime();
      long endTime = System.currentTimeMillis();
      System.out.println("Execution time in milliseconds: " + (endTime - startTime));
      System.out.println("Execution time in nanoseconds: " + (endTi - startTi));
      System.out.println("Terminado");
    }
}