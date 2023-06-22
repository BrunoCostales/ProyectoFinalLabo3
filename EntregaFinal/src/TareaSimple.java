public class TareaSimple extends  Tarea {
    private String proyectoAcargo;
    private String creador;
    public TareaSimple(String nombre, String descripcion, String proyectoAcargo){
        super(nombre,descripcion);
        this.proyectoAcargo=proyectoAcargo;
        this.creador=nombre;
    }

    public String getProyectoAcargo() {
        return proyectoAcargo;
    }

    public void setProyectoAcargo(String proyectoAcargo) {
        this.proyectoAcargo = proyectoAcargo;
    }

    public String getCreador() {
        return creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

}
