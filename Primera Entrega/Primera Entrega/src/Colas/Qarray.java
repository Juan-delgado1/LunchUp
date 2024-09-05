package Colas;
import Usuarios.*;

//Clase Qarray: colaimplementada con arreglo circular
public class Qarray<T> {
    
    int front = 0; //casilla inicial
    int rear = 0;  //casilla final
    int count = 0; //elementos de la cola
    int N = 100000000;  //capacidad de la cola
    
    public Qarray(int N){ //Constructor para redefinir capacidad
        this.N = N;
    }
    
    
    T[] qarray = (T[]) new Object[N]; //arreglo en el que se crea la cola
    
    
    //Agregar al inicio
    public void enqueueIn(T item) {
        if(full()) {
            System.out.println("Queue is full: item not enqueued"); 
            return;
        }
        front = (front - 1 + N) % N; // Avanzamos front en sentido contrario
        qarray[front] = item;
        count++;          
    }
    
    
    //Agregar al fnal
    public void enqueue(T item){ 
        
        if(full()){
            System.out.println("Queue is full: item not enqueued"); 
        }
        qarray[rear] = item;
        rear = (rear + 1) % N;
        count++;          
                
    }
    
    
    //Eliminar al inicio
    public T dequeue(){        
        
        
        if(empty()){
            throw new RuntimeException("Queue is empty: Item not dequeued");
        }  
        
        T item = qarray[front];
        front = (front + 1) % N;
        count--;
         
        return item;
    }
    
    
    
    //Eliminar al final
    public T dequeueFi() {
        if(empty()) {
            throw new RuntimeException("Queue is empty: Item not dequeued");
        }
        
        rear = (rear - 1 + N) % N; // Retrocedemos rear en sentido contrario
        T item = qarray[rear];
        count--;
        return item;
    }
    
    
    //Buscar
    public boolean buscar(T item) { 
        
        
        boolean find = false;
        
        for (int i = 0; i < count; i++) {
            int index = (front + i) % N;
            if (qarray[index] == item) {
                find =  true;
            }
        }
        return find;
    }
     
   
     //Mostrar del final al inicio
    public void showInverse() {
        if(empty()) {
            System.out.println("Queue is empty");
            return;
        }
        int aux = rear - 1;

        System.out.println("imprimiendo la cola de usuarios activos desde el final al inicio: ");

        for (int i = 0; i < count; i++) {
            int index = (aux + N) % N;
            System.out.print("[ "+ ((Usuario)qarray[index]).getNombre() + "--> ");
            aux--;
        }
        System.out.println("]");
    }
    
    //full
    public boolean full(){
        
        return count >= N;
        
    }
    
    
    //empty
    public boolean empty(){
        
        return count <= 0;
        
    }
    

    //metodo para duplicar la cola
    public Qarray<T> duplicate() {
        Qarray<T> duplicate = new Qarray<>(this.N);
        int aux = front;
        for (int i = 0; i < count; i++) {
            int index = (aux + N) % N;
            duplicate.enqueue(this.qarray[index]);
            aux++;
        }
        return duplicate;
    }

}
