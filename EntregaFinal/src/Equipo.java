import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class Equipo {
    private int id;
    private HashSet<Persona> equipo;
    private HashSet<Proyecto> proyectos;

    public Equipo() {
        this.id=new Random().nextInt();
       equipo=new HashSet<>();
       proyectos=new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void agregarPersonasAlEquipo (Persona p){
        if (equipo.contains(p)){}else{
            equipo.add(p);
        }

    }
   public Persona buscarPersonaXDni(String dni){

       for (Persona p:equipo ) {
           if(p.getDni().equals(dni)){
               return  p;
           }

           }
       return null;
       }



   public void quitarPersonadelEquipo(Persona p){

        if (equipo.contains(p)){
            equipo.remove(p);
        }
   }
   public void agregarProyecto(Proyecto p){

        if (proyectos.contains(p)){
            System.out.println("No se agregara este proyecto por que ya hay uno creado");


        }else{
            proyectos.add(p);
        }
   }


   public  void eliminarProyecto(Proyecto p){
        if (proyectos.contains(p)){
            proyectos.remove(p);
            System.out.println("Se elimino correctamente");
        }else{
            System.out.println("El proyecto no existe");
        }

   }
   public  void mostrarIntegrantesdelEquipo(){
       for (Persona p: equipo ) {
           if (equipo.isEmpty()){
               System.out.println("No hay integrantes");
           }

           System.out.println(p.getNombre());
       }
   }
    public  void mostrarProyectosdelEquipo(){
        for (Proyecto p: proyectos ) {

            System.out.println(p.getNombre());
        }
    }
    public Proyecto buscarProyecto(String nombreProyecto){


        for (Proyecto p : proyectos ) {
            if (p.getNombre().equals(nombreProyecto)){
                return p;


            }
        }

        return null;
    }
    public  String darIntegranteDequipo(){
        String resultado="";
        for (Persona p: equipo ) {
            if (equipo.isEmpty()){
                System.out.println("No hay integrantes");
            }
             resultado += "DNI"+p.getDni();


        }
        return resultado;
    }




}
