import java.util.Random;

public class ColaDePrioridad {
    public static int n=100000000;
    private int[] heap;
    private int size;
    private int capacity;
    private int comparaciones;  // Contador de comparaciones

    // Constructor
    public ColaDePrioridad(int capacidadInicial) {
        this.capacity = capacidadInicial;
        this.heap = new int[capacidadInicial];
        this.size = 0;
        this.comparaciones = 0;  // Inicializar el contador
    }

    public static void main(String[] args) {
        // Crear una instancia de ColaDePrioridad con capacidad inicial
        ColaDePrioridad cola = new ColaDePrioridad(n);
        
        // Generar números aleatorios y agregarlos al heap
        Random random = new Random();
        int cantidadDatos = n;
        int[] datosDesordenados = new int[cantidadDatos];
        
        // Llenar el array con números aleatorios (positivos y negativos)
        for (int i = 0; i < cantidadDatos; i++) {
            datosDesordenados[i] = random.nextInt();  // Puede ser positivo o negativo
        }
        
        // Medir el tiempo de inserción
        long inicioTiempoInsercion = System.nanoTime();
        
        for (int i = 0; i < cantidadDatos; i++) {
            cola.agregar(datosDesordenados[i]);
        }
        
        long finTiempoInsercion = System.nanoTime();
        long tiempoInsercion = finTiempoInsercion - inicioTiempoInsercion;
        
        // Imprimir resultados de inserción
        System.out.println("Tiempo de insercion para " + n +" elementos: " + tiempoInsercion / 1_000_000.0 + " ms");
        System.out.println("Size final del heap: " + cola.tamano());
        System.out.println("Numero total de comparaciones durante la insercion: " + cola.getComparaciones());
        System.out.println("Altura del heap Binario: " + cola.altura()); // Mostrar la altura del heap

        // Medir el tiempo de eliminación
        long inicioTiempoEliminacion = System.nanoTime();
        
        while (!cola.estaVacia()) {
            cola.eliminar();
        }
        
        long finTiempoEliminacion = System.nanoTime();
        long tiempoEliminacion = finTiempoEliminacion - inicioTiempoEliminacion;
        
        // Imprimir resultados de eliminación
        System.out.println("Tiempo de eliminacion de "+ n + " elementos: " + tiempoEliminacion / 1_000_000.0 + " ms");
    }
    
    // Métodos
    public void agregar(int elemento) {
        if (size == capacity) {
            redimensionar();
        }
        // Insertar el nuevo elemento al final del heap
        heap[size] = elemento;
        size++;
        // Mantener la propiedad de heap ascendiendo el elemento
        ascender(size - 1);
    }

    public Integer eliminar() {
        if (size == 0) {
            return null;
        }
        int raiz = heap[0];
        heap[0] = heap[size - 1];
        size--;
        descender(0);
        return raiz;
    }

    public Integer seleccionar() {
        if (size == 0) {
            return null;
        }
        return heap[0];
    }

    public boolean estaVacia() {
        return size == 0;
    }

    public int tamano() {
        return size;
    }

    public int getComparaciones() {
        return comparaciones;
    }

    public int altura() {
        return (int) Math.ceil(Math.log(size + 1) / Math.log(2)) - 1;
    }

    private void redimensionar() {
        int[] nuevoHeap = new int[capacity * 2];
        System.arraycopy(heap, 0, nuevoHeap, 0, size);
        heap = nuevoHeap;
        capacity *= 2;
    }

    private void ascender(int i) {
        int padre = (i - 1) / 2;
        while (i > 0 && heap[i] < heap[padre]) {
            comparaciones++;  // Contar la comparación
            intercambiar(i, padre);
            i = padre;
            padre = (i - 1) / 2;
        }
        if (i > 0) comparaciones++;  // Contar la comparación fallida al salir del bucle
    }

    private void descender(int i) {
        int menor = i;
        int izquierda = 2 * i + 1;
        int derecha = 2 * i + 2;
        // Encontrar el hijo menor
        if (izquierda < size && heap[izquierda] < heap[menor]) {
            comparaciones++;  // Contar la comparación
            menor = izquierda;
        }
        if (derecha < size && heap[derecha] < heap[menor]) {
            comparaciones++;  // Contar la comparación
            menor = derecha;
        }
        // Si el nodo actual no es el menor, intercambiar y continuar descendiendo
        if (menor != i) {
            intercambiar(i, menor);
            descender(menor);
        }
        if (izquierda < size || derecha < size) comparaciones++;  // Contar la comparación fallida al salir del bucle
    }

    private void intercambiar(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}
