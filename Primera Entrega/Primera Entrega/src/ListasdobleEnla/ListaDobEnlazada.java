package ListasdobleEnla ;

//lista doblemente enlazada en singular;
public class ListaDobEnlazada {
 
    private NodoDoble inicio;
    private NodoDoble fin;
    private int size;

    // metodo para inicializar la lista
    public ListaDobEnlazada(){
        inicio=null;
        fin=null;
        size=0;
        //size es la cantidad de nodos de la lista
    }
 
    //metodo para saber si esta vacia
    public boolean estaVacia(){
        return inicio==null;
    }

    //metodo para saber la cantidad de nodos de la lista
    public int getSize() {
        return size;
    }


    public void agregarAlInicio(int dato){
        if (estaVacia()) {
            inicio= new NodoDoble(dato);
            fin=inicio;
        } else {
            NodoDoble nuevo= new NodoDoble(dato);
            nuevo.next=inicio;
            inicio.prev=nuevo;
            inicio=nuevo;
        }
        size++;
    }



    public void agregarAlFinal(int dato){
        if (estaVacia()) {
            inicio= new NodoDoble(dato);
            fin=inicio;
        } else {
            NodoDoble nuevo= new NodoDoble(dato);
            fin.next=nuevo;
            nuevo.prev=fin;
            fin=nuevo;
        }
        size++; 
    }

    public void mostrarListaInicioFin(){
        if (!estaVacia()) {
            NodoDoble aux=inicio;
            System.out.println("Mostrando datos de inicio a fin:");
            while (aux!=null) {
                System.out.print("["+aux.dato+"]-->");
                aux=aux.next;
            }
        } else {
            System.out.println("La lista esta vacia, porfavor agregue elementos");
        }
    }


    public void mostrarListaFinInicio(){
        if (!estaVacia()) {
            NodoDoble aux=fin;
            System.out.println("Mostrando datos de fin a inicio:");
            while (aux!=null) {
                System.out.print("["+aux.dato+"]-->");
                aux=aux.prev;
            }
        } else {
            System.out.println("La lista esta vacia, porfavor agregue elementos");
        }
    }

    //eliminar datos del inicio , verificar lo del int
    public int eliminarInicio(){
        int elemento=inicio.dato;
        if (inicio==fin) {
            inicio=null;
            fin=null;
        } else {
            inicio=inicio.next; //primero corro el inicio
            inicio.prev=null; //borro la referencia prev de a donde corri el inicio
        }
        size--; 
        return elemento;
    }

    //eliminar datos del final revisar despues el int 
    public int eliminarFin(){
        int elemento=fin.dato;
        if (inicio==fin) {
            inicio=null;
            fin=null;
        } else {
            fin=fin.prev;
            fin.next=null;
        }
        size--; 
        return elemento;
    }

    //codigo para buscar dentro de la lista con el dato que le pase

    public boolean buscar(int dato){
        NodoDoble aux=inicio;
        boolean encontrado=false;
        while (aux!=null && encontrado!=true) {
            if (dato==aux.dato) {
                encontrado=true;
            }

            aux=aux.next;
            if (aux.next==null){
                System.out.println("El dato no se encuentra en la lista");
            }
        }
        return encontrado;
    }

  
   //metodo para eliminar cualquier dato en el medio 
    public void eliminarDato(int dato){
        NodoDoble aux=inicio;
        boolean encontrado=false;
        while (aux!=null && encontrado!=true) {
            if (dato==aux.dato) {
                encontrado=true;
            }
            aux=aux.next;
        }
        if (encontrado) {
            if (inicio==fin) {
                inicio=null;
                fin=null;
            } else if (aux==inicio) {
                inicio=inicio.next;
                inicio.prev=null;
            } else if (aux==fin) {
                fin=fin.prev;
                fin.next=null;
            } else {
                aux.prev.next=aux.next;
                aux.next.prev=aux.prev;
            }
            size--; 
        }
    }


}
