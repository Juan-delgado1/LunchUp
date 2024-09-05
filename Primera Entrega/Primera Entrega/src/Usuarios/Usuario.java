package Usuarios;
import Colas.*;
import Stacks.LinkedStack;


public class Usuario {

    public String nombre;
    public String universidad;
    public String facultad;
    public String restaurante;
    public LinkedStack<Usuario>UsuariosPotenciales;
    public LinkedStack<Usuario> UsuariosGustados;

    // Constructor
    public Usuario(String nombre, String universidad, String facultad, String restaurante) {
        this.nombre = nombre;
        this.universidad = universidad;
        this.facultad = facultad;
        this.restaurante = restaurante;
        this.UsuariosPotenciales = new LinkedStack<Usuario>();
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getUniversidad() {
        return universidad;
    }

    public String getFacultad() {
        return facultad;
    }
    
    public String getRestaurante() {
        return restaurante;
    }

    public LinkedStack<Usuario> getUsuariosPotenciales() {
        return  UsuariosPotenciales;
    }

    public void compareTo(Usuario firstUser, Qarray<Usuario> colaUsuarios) {
        Qarray<Usuario> duplicate = colaUsuarios.duplicate();
        while (!duplicate.empty()) {
            Usuario currentUser = duplicate.dequeue();
            if (currentUser == firstUser) {
                continue;
            }
            if (currentUser.universidad.equals(firstUser.universidad) && currentUser.restaurante.equals(firstUser.restaurante)) {
                firstUser.UsuariosPotenciales.push(currentUser);
            }
        }
    }

    public static void main(String[] args) {
        // Crear una cola de usuarios
        Qarray<Usuario> colaUsuarios = new Qarray<>(50);

        // Crear los objetos Usuario y agregarlos a la cola
        Usuario Alejandro = new Usuario( "Alejandro", "Universidad de los Andes", "Derecho", "Plazoleta Lleras");
        Usuario Ana= new Usuario("Ana", "Universidad Nacional", "Ingenieria", "Yutakeuchi");
        Usuario Ezequiel = new Usuario("Ezequiel", "Universidad Javeriana", "Artes", "Comedor de Artes");
        Usuario Camila = new Usuario("Camila", "Universidad de Antioquia", "Humanas", "Comedor Central");
        Usuario Diego = new Usuario("Diego", "Universidad del Rosario", "Ciencias", "Comedores de loto");
        Usuario Mariana = new Usuario("Mariana", "Universidad icesi", "Derecho", "Altos del palo");
        Usuario Beatriz = new Usuario("Beatriz", "Universidad del Norte", "Ingenieria", "Le petit");
        Usuario David = new Usuario("David", "Universidad de la Costa", "Artes", "comedor H");
        Usuario Claudia = new Usuario("Claudia", "Universidad Bolivariana", "Humanas", "Comedor General");
        Usuario Esteban = new Usuario("Esteban", "Universidad Externado", "Ciencias", "Comedor A");
        Usuario Andrea = new Usuario("Andrea", "Universidad Externado", "Ciencias", "Comedor A");
        Usuario Carolina= new Usuario("Carolina", "Universidad Nacional", "Ingenieria", "Yutakeuchi");
        Usuario Andres = new Usuario("Andres", "Universidad Nacional", "Ciencias", "Biologia");
        Usuario Bruno = new Usuario("Bruno", "Universidad Nacional", "Ingenieria", "Comedor Central");
        Usuario Julia = new Usuario("Julia", "Universidad Nacional", "Ingenieria", "Yutakeuchi");
        Usuario Fernando = new Usuario("Fernando", "Universidad Nacional", "Ingenieria", "Yutakeuchi");
        Usuario Juan = new Usuario("Juan", "Universidad Nacional", "Ciencias", "Biologia");
        Usuario Erika = new Usuario("Erika", "Universidad de los Andes", "Ingenieria", "Las Monas");
        Usuario Cecilia = new Usuario("Cecilia", "Universidad Nacional", "Derecho", "Comedor de Economicas");
        Usuario Adriana = new Usuario("Adriana", "Universidad Javeriana", "Ingenieria", "Comedor de Artes");
        Usuario Leonardo = new Usuario("Leonardo", "Universidad de Antioquia", "Artes", "Comedor Central");
        Usuario Enrique = new Usuario("Enrique", "Universidad del Rosario", "Ingenieria", "Comedores de loto");
        Usuario Diana = new Usuario("Diana", "Universidad icesi", "Ciencias", "Altos del Palo");
        Usuario Maria = new Usuario("Maria", "Universidad del Norte", "Derecho", "Le petit");
        Usuario Gonzalo = new Usuario("Gonzalo", "Universidad de la Costa", "Ingenieria", "Comedor H");
        Usuario Laura = new Usuario("Laura", "Universidad Bolivariana", "Artes", "Comedor General");
        Usuario Aron = new Usuario("Aron", "Universidad Externado", "Humanas", "Comedor G");
        Usuario Luisa = new Usuario("Luisa", "Universidad de los Andes", "Ciencias", "Plazoleta Lleras");
        Usuario Francisco = new Usuario("Francisco", "Universidad de los Andes", "Derecho", "Crepes");
        Usuario Guillermo = new Usuario("Guillermo", "Universidad de los Andes", "Ingenieria", "Las Monas");
        Usuario Daniela = new Usuario("Daniela", "Universidad de los Andes", "Artes", "Crepes");
        Usuario Eduardo = new Usuario("Eduardo", "Universidad Nacional", "Humanas", "Central");
        Usuario Felipe = new Usuario("Felipe", "Universidad Javeriana", "Ciencias", "Subway");
        Usuario Natalia= new Usuario("Natalia", "Universidad de Antioquia", "Derecho", "Comedor Central");
        Usuario Antonio= new Usuario("Antonio", "Universidad del Rosario", "Ingenieria", "Comedores de loto");
        Usuario Rafael= new Usuario("Rafael", "Universidad icesi", "Artes", "Altos del Palo");
        Usuario Jorge= new Usuario("Jorge", "Universidad del Norte", "Humanas", "Le petit");
        Usuario Estefania = new Usuario("Estefania", "Universidad de la Costa", "Ciencias", "Comedor H");
        Usuario Ignacio= new Usuario("Ignacio", "Universidad Bolivariana", "Derecho", "Comedor General");
        Usuario Isabel= new Usuario("Isabel", "Universidad Externado", "Ingenieria", "Comedor G");
        Usuario Kevin= new Usuario("Kevin", "Universidad de los Andes", "Artes", "Crepes");
        Usuario Jessica = new Usuario("Jessica", "Universidad Nacional", "Humanas", "Comedor Central");
        Usuario Helena = new Usuario("Helena", "Universidad Javeriana", "Ciencias", "Subway");
        Usuario Gabriel = new Usuario("Gabriel", "Universidad de Antioquia", "Humanas", "Comedor Central");
        Usuario Alicia = new Usuario("Alicia", "Universidad del Rosario", "Derecho", "Comedores de loto");
        Usuario Juliana = new Usuario("Juliana", "Universidad icesi", "Ingenieria", "Comedor Central");
        Usuario Carlos = new Usuario("Carlos", "Universidad del Norte", "Artes", "Le petit");
        Usuario Martin = new Usuario("Martin", "Universidad de la costa", "Humanas", "Comedor Sabroso");
        Usuario Olivia = new Usuario("Olivia", "Universidad Bolivariana", "Ciencias", "Comedor General");
        Usuario Santiago = new Usuario("Santiago", "Universidad Externado", "Ingenieria", "Comedor G");
        

            // Colas de usuarios activos
        colaUsuarios.enqueue(Alejandro);
        colaUsuarios.enqueue(Ana);
        colaUsuarios.enqueue(Ezequiel);
        colaUsuarios.enqueue(Camila);
        colaUsuarios.enqueue(Diego);
        colaUsuarios.enqueue(Mariana);
        colaUsuarios.enqueue(Beatriz);
        colaUsuarios.enqueue(David);
        colaUsuarios.enqueue(Claudia); 
        colaUsuarios.enqueue(Esteban);
        colaUsuarios.enqueue(Andrea);
        colaUsuarios.enqueue(Carolina); 
        colaUsuarios.enqueue(Andres);
        colaUsuarios.enqueue(Bruno);
        colaUsuarios.enqueue(Julia); 
        colaUsuarios.enqueue(Fernando);
        colaUsuarios.enqueue(Juan);
        colaUsuarios.enqueue(Erika);
        colaUsuarios.enqueue(Cecilia);
        colaUsuarios.enqueue(Adriana);
        colaUsuarios.enqueue(Leonardo);
        colaUsuarios.enqueue(Enrique);
        colaUsuarios.enqueue(Diana);
        colaUsuarios.enqueue(Maria);
        colaUsuarios.enqueue(Gonzalo);
        colaUsuarios.enqueue(Laura);
        colaUsuarios.enqueue(Aron);
        colaUsuarios.enqueue(Luisa);
        colaUsuarios.enqueue(Francisco);
        colaUsuarios.enqueue(Guillermo);
        colaUsuarios.enqueue(Daniela);
        colaUsuarios.enqueue(Eduardo);
        colaUsuarios.enqueue(Felipe);
        colaUsuarios.enqueue(Natalia);
        colaUsuarios.enqueue(Antonio);
        colaUsuarios.enqueue(Rafael);
        colaUsuarios.enqueue(Jorge);
        colaUsuarios.enqueue(Estefania);
        colaUsuarios.enqueue(Ignacio);
        colaUsuarios.enqueue(Isabel);
        colaUsuarios.enqueue(Kevin);
        colaUsuarios.enqueue(Jessica);
        colaUsuarios.enqueue(Helena);
        colaUsuarios.enqueue(Gabriel);
        colaUsuarios.enqueue(Alicia);
        colaUsuarios.enqueue(Juliana);
        colaUsuarios.enqueue(Carlos);
        colaUsuarios.enqueue(Martin);
        colaUsuarios.enqueue(Olivia);
        colaUsuarios.enqueue(Santiago);

        // Imprimir la cola de usuarios activos
        
        colaUsuarios.showInverse();

         Alejandro.compareTo(Alejandro,colaUsuarios);
         Ana.compareTo(Ana,colaUsuarios);
         Ezequiel.compareTo(Ezequiel,colaUsuarios);
         Camila.compareTo(Camila,colaUsuarios);
         Diego.compareTo(Diego,colaUsuarios);
         Mariana.compareTo(Mariana,colaUsuarios);
         Beatriz.compareTo(Beatriz,colaUsuarios);
         David.compareTo(David,colaUsuarios);
         Claudia.compareTo(Claudia,colaUsuarios);
         Esteban.compareTo(Esteban,colaUsuarios);
         Andrea.compareTo(Andrea,colaUsuarios);
         Carolina.compareTo(Carolina,colaUsuarios);
         Andres.compareTo(Andres,colaUsuarios);
         Bruno.compareTo(Bruno,colaUsuarios);
         Julia.compareTo(Julia,colaUsuarios);
         Fernando.compareTo(Fernando,colaUsuarios);
         Juan.compareTo(Juan,colaUsuarios);
         Erika.compareTo(Erika,colaUsuarios);
         Cecilia.compareTo(Cecilia,colaUsuarios);
         Adriana.compareTo(Adriana,colaUsuarios);
         Leonardo.compareTo(Leonardo,colaUsuarios);
         Enrique.compareTo(Enrique,colaUsuarios);
         Diana.compareTo(Diana,colaUsuarios);
         Maria.compareTo(Maria,colaUsuarios);
         Gonzalo.compareTo(Gonzalo,colaUsuarios);
         Laura.compareTo(Laura,colaUsuarios);
         Aron.compareTo(Aron,colaUsuarios);
         Luisa.compareTo(Luisa,colaUsuarios);
         Francisco.compareTo(Francisco,colaUsuarios);
         Guillermo.compareTo(Guillermo,colaUsuarios);
         Daniela.compareTo(Daniela,colaUsuarios);
         Eduardo.compareTo(Eduardo,colaUsuarios);
         Felipe.compareTo(Felipe,colaUsuarios);
         Natalia.compareTo(Natalia,colaUsuarios);
         Antonio.compareTo(Antonio,colaUsuarios);
         Rafael.compareTo(Rafael,colaUsuarios);
         Jorge.compareTo(Jorge,colaUsuarios);
         Estefania.compareTo(Estefania,colaUsuarios);
         Ignacio.compareTo(Ignacio,colaUsuarios);
         Isabel.compareTo(Isabel,colaUsuarios);
         Kevin.compareTo(Kevin,colaUsuarios);
         Jessica.compareTo(Jessica,colaUsuarios);
         Helena.compareTo(Helena,colaUsuarios);
         Gabriel.compareTo(Gabriel,colaUsuarios);
         Alicia.compareTo(Alicia,colaUsuarios);
         Juliana.compareTo(Juliana,colaUsuarios);
         Carlos.compareTo(Carlos,colaUsuarios);
         Martin.compareTo(Martin,colaUsuarios);
         Olivia.compareTo(Olivia,colaUsuarios);
         Santiago.compareTo(Santiago,colaUsuarios);
         
       //imprimir los usuariosPotenciales de cada uno
     System.out.println("Los usuarios Potenciales de "+ Alejandro.nombre +" son");
        Alejandro.UsuariosPotenciales.printToBack();
     System.out.println("Los usuarios Potenciales de "+ Ana.nombre +" son");
        Ana.UsuariosPotenciales.printToBack();
     System.out.println("Los usuarios Potenciales de "+ Ezequiel.nombre +" son");   
        Ezequiel.UsuariosPotenciales.printToBack();
     System.out.println("Los usuarios Potenciales de "+ Camila.nombre +" son");
        Camila.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Diego.nombre +" son");
        Diego.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Mariana.nombre +" son");
        Mariana.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Beatriz.nombre +" son");
        Beatriz.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ David.nombre +" son");
        David.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Claudia.nombre +" son");
        Claudia.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Esteban.nombre +" son");
        Esteban.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Andrea.nombre +" son");
        Andrea.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Carolina.nombre +" son");
        Carolina.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Andres.nombre +" son");
        Andres.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Bruno.nombre +" son");
        Bruno.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Julia.nombre +" son");
        Julia.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Fernando.nombre +" son");
        Fernando.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Juan.nombre +" son");
        Juan.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Erika.nombre +" son");
        Erika.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Cecilia.nombre +" son");
        Cecilia.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Adriana.nombre +" son");
        Adriana.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Leonardo.nombre +" son");
        Leonardo.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Enrique.nombre +" son");
        Enrique.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Diana.nombre +" son");
        Diana.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Maria.nombre +" son");
        Maria.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Gonzalo.nombre +" son");
        Gonzalo.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Laura.nombre +" son");
        Laura.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Aron.nombre +" son");
        Aron.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Luisa.nombre +" son");
        Luisa.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Francisco.nombre +" son");
        Francisco.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Guillermo.nombre +" son");
        Guillermo.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Daniela.nombre +" son");
        Daniela.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Eduardo.nombre +" son");
        Eduardo.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Felipe.nombre +" son");
        Felipe.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Natalia.nombre +" son");
        Natalia.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Antonio.nombre +" son");
        Antonio.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Rafael.nombre +" son");
        Rafael.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Jorge.nombre +" son");
        Jorge.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Estefania.nombre +" son");
        Estefania.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Ignacio.nombre +" son");
        Ignacio.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Isabel.nombre +" son");
        Isabel.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Kevin.nombre +" son");
        Kevin.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Jessica.nombre +" son");
        Jessica.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Helena.nombre +" son");
        Helena.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Gabriel.nombre +" son");
        Gabriel.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Alicia.nombre +" son");
        Alicia.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Juliana.nombre +" son");
        Juliana.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Carlos.nombre +" son");
        Carlos.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Martin.nombre +" son");
        Martin.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Olivia.nombre +" son");
        Olivia.UsuariosPotenciales.printToBack();
        System.out.println("Los usuarios Potenciales de "+ Santiago.nombre +" son");
        Santiago.UsuariosPotenciales.printToBack();
    }

}
