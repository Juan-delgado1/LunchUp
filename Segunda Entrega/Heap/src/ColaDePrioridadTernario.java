import java.util.Random;

public class ColaDePrioridadTernario {
    public static int n=100000000;
    private int[] heap;
    private int size;
    private int capacity;
    private int comparaciones;  // Contador de comparaciones

    // Constructor
    public ColaDePrioridadTernario(int capacidadInicial) {
        this.capacity = capacidadInicial;
        this.heap = new int[capacidadInicial];
        this.size = 0;
        this.comparaciones = 0;  // Inicializar el contador
    }

    public static void main(String[] args) {
        // Crear una instancia de ColaDePrioridadTernario con capacidad inicial
        ColaDePrioridadTernario cola = new ColaDePrioridadTernario(n);
        
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
        System.out.println("Tiempo de insercion para " + n + " elementos: " + tiempoInsercion / 1_000_000.0 + " ms");
        System.out.println("Size final del heap: " + cola.tamano());
        System.out.println("Numero total de comparaciones durante la insercion: " + cola.getComparaciones());
        System.out.println("Altura del heap ternario: " + cola.calcularAltura());

        // Medir el tiempo de eliminación
        long inicioTiempoEliminacion = System.nanoTime();
        
        while (!cola.estaVacia()) {
            cola.eliminar();
        }
        
        long finTiempoEliminacion = System.nanoTime();
        long tiempoEliminacion = finTiempoEliminacion - inicioTiempoEliminacion;
        
        // Imprimir resultados de eliminación
        System.out.println("Tiempo de eliminacion de "+ n +" elementos: " + tiempoEliminacion / 1_000_000.0 + " ms");
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

    public int calcularAltura() {
        return (int) Math.ceil(Math.log(size) / Math.log(3));
    }

    private void redimensionar() {
        int[] nuevoHeap = new int[capacity * 2];
        System.arraycopy(heap, 0, nuevoHeap, 0, size);
        heap = nuevoHeap;
        capacity *= 2;
    }

    private void ascender(int i) {
        int padre = (i - 1) / 3;
        while (i > 0 && heap[i] < heap[padre]) {
            comparaciones++;  // Contar la comparación
            intercambiar(i, padre);
            i = padre;
            padre = (i - 1) / 3;
        }
        if (i > 0) comparaciones++;  // Contar la comparación fallida al salir del bucle
    }

    private void descender(int i) {
        int menor = i;
        int hijo1 = 3 * i + 1;
        int hijo2 = 3 * i + 2;
        int hijo3 = 3 * i + 3;
        
        // Encontrar el hijo menor entre los tres hijos posibles
        if (hijo1 < size && heap[hijo1] < heap[menor]) {
            comparaciones++;  // Contar la comparación
            menor = hijo1;
        }
        if (hijo2 < size && heap[hijo2] < heap[menor]) {
            comparaciones++;  // Contar la comparación
            menor = hijo2;
        }
        if (hijo3 < size && heap[hijo3] < heap[menor]) {
            comparaciones++;  // Contar la comparación
            menor = hijo3;
        }
        // Si el nodo actual no es el menor, intercambiar y continuar descendiendo
        if (menor != i) {
            intercambiar(i, menor);
            descender(menor);
        }
        if (hijo1 < size || hijo2 < size || hijo3 < size) comparaciones++;  // Contar la comparación fallida al salir del bucle
    }

    private void intercambiar(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}
