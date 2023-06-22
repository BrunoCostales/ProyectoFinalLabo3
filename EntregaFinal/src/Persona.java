import java.util.HashSet;
import java.util.Objects;


public class Persona <TareaAsignadas extends  Tarea>{
    private String nombre;
    private Integer edad;
    private String  dni;
    private HashSet<TareaAsignadas> tareas;

    public Persona(String nombre, Integer edad, String dni){
        this.nombre=nombre;
        this.edad=edad;
        this.dni=dni;
        this.tareas=new HashSet<>();

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getDni() {
        return dni;
    }


    public void agregarleTareaAlaPersona(TareaAsignadas t){

        tareas.add(t);

    }
    public void quitarTareaAlaPersona(TareaAsignadas t){
        tareas.remove(t);
    }
    public void mostrarTareasPendientesDePersona() {
        for (TareaAsignadas tarea : tareas) {

            System.out.println("Nombre de Tareaa :" + tarea.getNombre());
            System.out.println("Descripcion:" + tarea.getDescripcion());
            if (tarea.getCompletada())
                System.out.println("Estado: Terminada");
            else{
                System.out.println("Estado: Pendiente");
            }
            if (tarea instanceof TareaSimple) {
                System.out.println("Nombre de proyecto simple :" + ((TareaSimple) tarea).getProyectoAcargo());

            } else if (tarea instanceof TareaConjunta) {
                System.out.println("Proyecto: " + ((TareaConjunta) tarea).getProyectoPerteneciente() + ".  A cargo de : " + ((TareaConjunta) tarea).getResponsable());

            }
            System.out.println("___________________________________________");


        }
    }





    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return dni.equals(persona.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }
}
