package ListasdobleEnla;

//main principal
public class ListDoEn {

    public static void main(String[] args) throws Exception { 
        int i=1;
        int L=1000; //Limite 10 millones
        ListaDobEnlazada listaprueba= new ListaDobEnlazada();
    
        for ( i=1; i<=L; i++) {
            listaprueba.agregarAlFinal(i);
        }

        long startTime = System.currentTimeMillis();
        long startTi = System.nanoTime();

        listaprueba.mostrarListaFinInicio();
        long endTime = System.currentTimeMillis();
        long endTi = System.nanoTime();

        System.out.println("Execution time in milliseconds: " + (endTime - startTime));
        System.out.println("Execution time in nanoseconds: " + (endTi - startTi));
    }
}
