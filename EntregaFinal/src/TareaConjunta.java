import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

public class TareaConjunta extends Tarea {
    private LocalDate fechaVencimiento=null;
    private  String responsable=null;
    private String proyectoPerteneciente=null;
    public TareaConjunta(String nombre, String descripcion, String responsable, int fechaVencimiento){
        super( nombre, descripcion);
        this.responsable=responsable;
        this.fechaVencimiento=convertirFecha(fechaVencimiento);


    }
    private LocalDate convertirFecha(int fechaNumeric) {
        String fechaString = String.valueOf(fechaNumeric);
        String year = fechaString.substring(0, 4);
        String month = fechaString.substring(4, 6);
        String day = fechaString.substring(6, 8);
        return LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getProyectoPerteneciente() {
        return proyectoPerteneciente;
    }

    public void setProyectoPerteneciente(String proyectoPerteneciente) {
        this.proyectoPerteneciente = proyectoPerteneciente;
    }

    public void actualizarTarea(){

        String s="";
        System.out.println("Ingrese los datos");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nombre:");
        s=scanner.nextLine();
        this.setNombre(s);
        s="";
        System.out.println("Ingrese descripcion");
        s=scanner.nextLine();
        this.setDescripcion(s);
        System.out.println("Deseas atrasar la tarea?");
        s=scanner.nextLine();
        if (s.equals("si") || s.equals("Si")){
            System.out.println("Cuantos dias deseas atrasar la entrega de la tarea ?");
            int a= scanner.nextInt();
            this.getFechaVencimiento().plusDays(a);


        }
        System.out.println("Cambia al responsable de la tarea?");
        s="";
        s=scanner.nextLine();
        this.setResponsable(s);
        System.out.println(this.toString());

    }


    @Override
    public String toString() {
        return "TareaConjunta{" +
                "fechaVencimiento=" + fechaVencimiento +
                ", responsable='" + responsable + '\'' +
                "} " + super.toString();
    }

}
