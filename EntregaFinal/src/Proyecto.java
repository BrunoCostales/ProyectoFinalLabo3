import javax.swing.table.TableRowSorter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class Proyecto implements  iTareaFuncionalidad {
    private ArrayList<TareaConjunta> ListaTareas;
    private String  nombre;
    private String descripcion;
    private LocalDate entrega;
    private Boolean completada=false;

    public Proyecto( String nombre,String descripcion) {
        this.nombre=nombre;
        this.ListaTareas= new ArrayList<>();
        this.descripcion = descripcion;
        this.entrega = LocalDate.now().plusDays(7);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getEntrega() {
        return entrega;
    }

    public void setEntrega(LocalDate entrega) {
        this.entrega = entrega;
    }

    public Boolean getCompletada() {
        return completada;
    }

    public void setCompletada(Boolean completada) {
        this.completada = completada;
    }
    public void agregarTareaaProyecto(TareaConjunta t){

       if (ListaTareas.contains(t)){
           System.out.println("La tarea ya existe");
       }else {
           ListaTareas.add(t);

           t.setProyectoPerteneciente(this.nombre);
       }



    }
    public void eliminaTareaProyecto(String nombre){

        for (TareaConjunta t: ListaTareas   ) {
            if (t.getNombre() == nombre){
                ListaTareas.remove(t);
            }else{
                System.out.println("No se encontro");
            }
        }

        }

    public void modificaTareaProyecto(String nombre){


        for (TareaConjunta tarea: ListaTareas ) {
            if(tarea.getNombre().equals(nombre)){
                System.out.println("TAREA : ");
                System.out.println(tarea.toString());
                tarea.actualizarTarea();
            }

        }






    }
    public void mostrarTodasTareas (){
        for (TareaConjunta t: ListaTareas) {
            System.out.println(t.toString());

        }

    }
    public void mostrarTareaPorId(String id){
        for (TareaConjunta t: ListaTareas ) {
            if (t.equals(id)){
                System.out.println(t.toString());
            }
        }

    }
    public void terminarProyecto (){
        for (TareaConjunta t:ListaTareas ) {
            t.terminaTarea();
        }
        this.completada=true;
        System.out.println("El proyecto se completo: Datos"+ LocalDate.now()+" Y la fecha de entraga era: "+this.entrega);

    }
    public void listarTareas(){
        for (TareaConjunta t: ListaTareas ) {

            System.out.println("Nombre de Tarea :"+t.getNombre());
            System.out.println("Hola");
            System.out.println("Dia de entrega:"+t.getFechaVencimiento().toString());
            System.out.println("Entrega a cargo:"+t.getResponsable());
            if (t.getCompletada())
            System.out.println("Estado: Terminada");
            else{
                System.out.println("Estado: Pendiente");
            }
            System.out.println("________________________________________");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proyecto proyecto = (Proyecto) o;
        return nombre.equals(proyecto.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
}
