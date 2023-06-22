import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public abstract class Tarea {
    //Esta va a ser la tarea que se debe completar

    private String  nombre;
    private String id;
    private String descripcion;
    private Boolean completada=false;


    public Tarea(String nombre, String descripcion) {

        this.id=UUID.randomUUID().toString();
        this.nombre = nombre;
        this.descripcion = descripcion;

    }

    public String getId() {
        return id;
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

    public Boolean getCompletada() {
        return completada;
    }

    public void setCompletada(Boolean completada) {
        this.completada = completada;
    }
    public void terminaTarea(){
        this.completada=true;
    }


    @Override
    public String toString() {
        return "Tarea{" +
                "nombre='" + nombre + '\'' +
                ", id='" + id + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", completada=" + completada +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tarea tarea = (Tarea) o;
        return id.equals(tarea.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
