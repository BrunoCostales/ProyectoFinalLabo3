import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    //Deberia poder agregar mas tareas luego

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Equipo equipo = new Equipo();
        HashSet<Persona> personasRegistradas = new HashSet<>();
        LinkedHashSet<Equipo> equiposRegistrados = new LinkedHashSet<>();


        while (true) {
            System.out.println("---- Menú ----");
            System.out.println("0. Mostrar personas registradas");
            System.out.println("1. Crear persona");
            System.out.println("2. Crear proyecto");
            System.out.println("3. Agregar tarea a proyecto");
            System.out.println("4. Eliminar tarea de proyecto");
            System.out.println("5. Modificar tarea de proyecto");
            System.out.println("6. Mostrar todas las tareas de un proyecto");
            System.out.println("7. Mostrar todas las tareas pendientes de una persona");
            System.out.println("8. Mostrar integrantes del equipo");
            System.out.println("9. Mostrar proyectos del equipo");
            System.out.println("10. Agregar Tarea simple");
            System.out.println("11. Salir");
            System.out.print("Ingrese una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 0:
                    for (Persona p : personasRegistradas) {
                        System.out.println("Dni s Registrados:" + p.getDni());
                    }
                    break;
                case 1:
                    crearPersona(scanner, personasRegistradas);
                    break;
                case 2:
                    crearProyecto(scanner, equiposRegistrados, personasRegistradas);
                    break;
                case 3:
                    agregarTareaAProyecto(scanner, equiposRegistrados);
                    break;
                case 4:
                    eliminarTareaDeProyecto(scanner, equiposRegistrados);
                    break;
                case 5:
                    modificarTareaDeProyecto(scanner, equiposRegistrados);
                    break;
                case 6:
                    mostrarTareasDeProyecto(scanner, equiposRegistrados);
                    break;
                case 7:
                    mostrarTareasPendientesDePersona(scanner, personasRegistradas);
                    break;
                case 8:
                    mostrarIntegrantesDelEquipo(equiposRegistrados,personasRegistradas);
                    break;
                case 9:
                    mostrarProyectosDelEquipo(equiposRegistrados);
                    break;
                case 10:
                    agregarTareaSimple(scanner, personasRegistradas);
                    break;
                case 11:
                    System.out.println("Hasta luego!");
                    return;
                default:
                    System.out.println("ingrese una opción correcta.");
                    break;
            }

            System.out.println();
        }
    }

    private static void crearPersona(Scanner scanner, HashSet<Persona> personasRegistradas) {
        try {
            System.out.println("Ingrese el nombre de la persona:");
            String nombre = scanner.nextLine();

            System.out.println("Ingrese la edad de la persona:");
            int edad = scanner.nextInt();

            scanner.nextLine(); // Consumir el salto de línea


            System.out.println("Ingrese el DNI de la persona:");
            String dni = scanner.nextLine();
            Persona persona = new Persona(nombre, edad, dni);
            if (personasRegistradas.contains(persona)) {
               throw new ExcepcionPersonaexistente("La persona ya existe en la base de datos, porfavor intente de nuevo");
            } else {
                personasRegistradas.add(persona);
                System.out.println("Persona creada exitosamente.");


            }
        }catch (ExcepcionPersonaexistente e){
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println("Error al ingresar los datos, Asegurate de ingresar bien los datos");
        }



    }

    private static void crearProyecto(Scanner scanner, LinkedHashSet<Equipo> equiposRegistrados, HashSet<Persona> personasRegistradas) {
        System.out.println("Ingrese el nombre del proyecto:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese la descripción del proyecto:");
        String descripcion = scanner.nextLine();

        Proyecto proyecto = new Proyecto(nombre, descripcion);
        System.out.println("Para trabajar en proyecto debe agregar personas al equipo. Creando equipo");
        System.out.println("Ingrese DNI de las personas del equipo, cuando desee pulse n para volver.");
        boolean continuar = true;
        Equipo unEquipo = new Equipo();
        unEquipo.agregarProyecto(proyecto);


        while (continuar) {
            String dni = scanner.nextLine();

            int flag = 0;


            if (dni.equals("n")) {
                continuar = false;
                break;
            }


            for (Persona p : personasRegistradas) {
                if (p.getDni().equals(dni)) {
                    flag = 1;

                    unEquipo.agregarPersonasAlEquipo(p);
                    System.out.println("Registrado correctamente");
                    break;
                }
            }
            if (flag == 0) {
                System.out.println("La persona no se encuentra en el registro");
            }
        }
        System.out.println("Proyecto creado exitosamente.");
        if (!(equiposRegistrados.contains(unEquipo))) {
            equiposRegistrados.add(unEquipo);
        }
        System.out.println("Ingrese el dni del responsable de la tarea conjunta:");
        String dni=scanner.nextLine();
        System.out.println("Ingrese el nombre de la  tarea:");
        String nombreTarea = scanner.nextLine();

        System.out.println("Ingrese la descripción de la tarea:");
        String descripcionTarea = scanner.nextLine();



        Persona integrante =unEquipo.buscarPersonaXDni(dni);



        String responsable = integrante.getNombre();


        System.out.println("Ingrese la fecha  de la entrega de la tarea(YYYYMMDD):");
        Integer fecha = scanner.nextInt();


        TareaConjunta tareaConjunta = new TareaConjunta(nombreTarea, descripcionTarea, responsable, (int) fecha);
        proyecto.agregarTareaaProyecto(tareaConjunta);
        integrante.agregarleTareaAlaPersona(tareaConjunta);
        System.out.println("Tarea agregada exitosamente al proyecto.");
    }


    private static void agregarTareaAProyecto(Scanner scanner, LinkedHashSet<Equipo> equiposRegistrados) {

        System.out.println("Ingrese el nombre del proyecto");
        String s = scanner.nextLine();

        int flag=1;
        for (Equipo e : equiposRegistrados) {
            if (e.buscarProyecto(s) != null) {
                Proyecto p = e.buscarProyecto(s);
                System.out.println("Agregando tarea al proyecto : " + p.getNombre());
                System.out.println("Ingrese el nombre de la  tarea:");
                String nombreTarea = scanner.nextLine();

                System.out.println("Ingrese la descripción de la tarea:");
                String descripcionTarea = scanner.nextLine();
                System.out.println("Ingrese el dni del responsable de la tarea conjunta:");
                String dni=scanner.nextLine();

                System.out.println("Ingrese la fecha  de la entrega de la tarea(YYYYMMDD):");
                Integer fecha = scanner.nextInt();

                String responsable;
                Persona integranteNuevo=e.buscarPersonaXDni(dni);
                responsable=integranteNuevo.getNombre();
                TareaConjunta t = new TareaConjunta(nombreTarea, descripcionTarea, responsable, fecha);
                p.agregarTareaaProyecto(t);
                flag=0;
            } else if (flag==1){
                System.out.println("no se encontro el proyecto");
                return;
            }


        }

    }

    private static void eliminarTareaDeProyecto(Scanner scanner, LinkedHashSet<Equipo> equiposRegistrados) {
        System.out.println("Ingrese el nombre del proyecto del que desea eliminar la tarea:");
        String nombreProyecto = scanner.nextLine();

        for (Equipo e : equiposRegistrados) {
            if (e.buscarProyecto(nombreProyecto) != null) {
                Proyecto p = e.buscarProyecto(nombreProyecto);
                if (p == null) {
                    System.out.println("El proyecto no existe.");
                    return;
                } else {
                    System.out.println("Ingrese el nombre de la tarea que desea eliminar:");
                    String nombreTarea = scanner.nextLine();
                    p.eliminaTareaProyecto(nombreTarea);
                    System.out.println("Tarea eliminada exitosamente del proyecto.");
                }


            }
        }
    }

    private static void modificarTareaDeProyecto(Scanner scanner, LinkedHashSet<Equipo> equiposRegistrados) {
        System.out.println("Ingrese el nombre del proyecto del que desea modificar la tarea:");
        String nombreProyecto = scanner.nextLine();

        for (Equipo e : equiposRegistrados) {
            if (e.buscarProyecto(nombreProyecto) != null) {
                Proyecto p = e.buscarProyecto(nombreProyecto);
                if (p == null) {
                    System.out.println("El proyecto no existe.");

                } else {
                    System.out.println("Ingrese el nombre de la tarea que desea modificar:");
                    String nombreTarea = scanner.nextLine();

                    p.modificaTareaProyecto(nombreTarea);

                    System.out.println("Tarea modificada exitosamente.");
                }


            }
        }
        return;
    }


    private static void mostrarTareasDeProyecto(Scanner scanner, LinkedHashSet<Equipo> equiposRegistrados) {
        System.out.println("Ingrese el nombre del proyecto para mostrar todas las tareas:");
        String nombreProyecto = scanner.nextLine();


        for (Equipo e : equiposRegistrados) {
            Proyecto proyect = e.buscarProyecto(nombreProyecto);
            if (proyect == null) {
                System.out.println("El proyecto no existe.");
                return;
            }

            proyect.mostrarTodasTareas();
        }

    }

    private static void mostrarTareasPendientesDePersona(Scanner scanner, HashSet<Persona> personasRegistrada) {
        System.out.println("Ingrese el DNI de la persona para mostrar las tareas pendientes:");
        String dni = scanner.nextLine();
        int flag = 0;

        for (Persona p : personasRegistrada) {
            if (p.getDni().equals(dni)) {
                p.mostrarTareasPendientesDePersona();
                flag = 1;
            }
        }

        if (flag == 0) {
            System.out.println("La persona no existe.");

        }

    }

    private static void mostrarIntegrantesDelEquipo(LinkedHashSet<Equipo> equiposRegistrados, HashSet<Persona>personasRegistradas) {

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("registro.txt"));
                writer.write("Personas en el equipo (por DNI):");
                writer.newLine();
                System.out.println("Integrantes del equipo:");
                        for (Equipo e : equiposRegistrados) {
                            e.mostrarIntegrantesdelEquipo();
                            writer.write(e.darIntegranteDequipo());


                }
                for (Persona p: personasRegistradas   ) {
                    writer.write("Personas Registradas en el sistema :  ");
                    writer.write(p.getNombre());

                }
                writer.close();

                System.out.println("Equipo y tareas guardados exitosamente en el archivo: registro.txt");
            } catch (IOException e) {
                System.out.println("Error al guardar el equipo y tareas");
            }






        }




    private static void mostrarProyectosDelEquipo(LinkedHashSet<Equipo> equiposRegistrados) {
        System.out.println("Proyectos del equipo:");

        for (Equipo e : equiposRegistrados) {
            e.mostrarProyectosdelEquipo();

        }
    }

    private static void agregarTareaSimple(Scanner scanner, HashSet<Persona> personasRegistradas) {
        System.out.println("La tarea simple es la tarea donde es individual y no tiene que pertenecer al equipo.");
        System.out.println("Ingrese el nombre de la terea");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese la descripcion de la terea");
        String descripcion = scanner.nextLine();
        System.out.println("Este proyecto personal, como se llama?");
        String nombreProyecto = scanner.nextLine();
        TareaSimple t = new TareaSimple(nombre, descripcion, nombreProyecto);
        System.out.println("A que persona se asigna? Indique dni");
        String dni = scanner.nextLine();
        int flag = 0;
        for (Persona p : personasRegistradas) {
            if (p.getDni().equals(dni)) {
                flag = 1;
                p.agregarleTareaAlaPersona(t);

                System.out.println("Registrada correctamente");
                break;
            }
        }
        if (flag == 0) {
            System.out.println("La persona no se encuentra en el registro");
        }


    }
}
