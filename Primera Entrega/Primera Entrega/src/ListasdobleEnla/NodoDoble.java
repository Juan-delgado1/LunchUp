package ListasdobleEnla ;

public class NodoDoble {
    public int dato;
    public NodoDoble next;
    public NodoDoble prev;

    //constructor cuando ya hay nodos
    public NodoDoble(int elemento, NodoDoble n, NodoDoble p){
        this.dato= elemento;
        this.next= n;
        this.prev= p;
    }

    // constructor cuando no hay nodos aun 
    public NodoDoble(int elemento){
        this.dato=elemento;
        this.next=null;
        this.prev=null;
    }

    //metodo de get valor
    public int getdato () {
        return this.dato;
    }	

    
    //metodo obtener el siguiente
    public NodoDoble getNext(){
        return this.next;
    }

    //metodo obtener el anterior
    public NodoDoble getprev(){
        return this.prev;
    }

    public void setnext(NodoDoble n) {
        this.next=n;
    }
    
    public void setprev(NodoDoble p) {
        this.next=p;
    }

}