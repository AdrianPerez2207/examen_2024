package org.examen_2024.app;

import org.examen_2024.entities.Tecnico;
import org.examen_2024.entities.TicketSoporte;
import org.examen_2024.entities.Usuario;
import org.examen_2024.io.TicketsSoporte;
import org.examen_2024.services.ServicioSoporte;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class App {

    public static void menu(){

        System.out.println("----Elige una opción: -----");
        System.out.println("1. Listar todos los tickets abiertos.");
        System.out.println("2. Listar técnicos agrupados por especialidad.");
        System.out.println("3. Mostrar el total de tickets resueltos.");
        System.out.println("4. Mostrar la media de resolución de tickets pidiendo la prioridad de los tickets.");
        System.out.println("5. Insertar ticket soporte: que pida todos los datos y lo inserte en BD.");
        System.out.println("6. Eliminar ticket soporte: que pida el id y lo elimine de la BD.");
        System.out.println("7. Salir: que llame a grabarCSV().");
    }
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        Integer opcion = 0;

        ServicioSoporte sp = TicketsSoporte.cargarCSV();

        while (opcion != 7){
            menu();
            opcion = Integer.parseInt(sc.nextLine());
            switch (opcion){
                case 1:
                    System.out.println("Tickets abiertos: ");
                    System.out.println(sp.getTicketAbiertos());
                    break;
                case 2:
                    System.out.println("Indica la especialidad: ");
                    Tecnico.Especialidad especialidad = Tecnico.Especialidad.valueOf(sc.nextLine());
                    System.out.println(sp.getTecnicosByEspecialidad(especialidad));
                    break;
                case 3:
                    System.out.println("Tickets cerrados: ");
                    System.out.println(sp.getTicketsCerrados());
                    break;
                case 4:
                    Integer prioridad = 0;
                    System.out.println("Indica la prioridad del Ticket: ");
                    prioridad = Integer.parseInt(sc.nextLine());
                    System.out.println("Media de resolución: " + sp.getMediaResolucionTickets(prioridad));
                    break;
                case 5:

                    System.out.println("Escribe la prioridad: ");
                    Integer prioridad2 = Integer.parseInt(sc.nextLine());
                    System.out.println("Escribe tu id de usuario: ");
                    Long idUsuario = Long.parseLong(sc.nextLine());
                    System.out.println("Escribe la id del técnico: ");
                    Long idTecnico = Long.parseLong(sc.nextLine());
                    System.out.println("Escribe un comentario: ");
                    String comentario = sc.nextLine();

                    sp.addTicketSoporte(LocalDate.now(), null, prioridad2,
                            sp.findUsuarioById(idUsuario), sp.findTecnicoById(idTecnico), comentario);
                    break;
                case 6:
                    System.out.println("Escribe el id del ticket: ");
                    Long ticket = Long.parseLong(sc.nextLine());
                    sp.deleteTicketSoporte(ticket);
                    break;
                case 7:
                    System.out.println("¡Adiós!");
                    TicketsSoporte.grabarCSV(sp);
                    System.exit(0);
                    break;
                default:
                    System.exit(0);
                    break;
            }
        }
    }
}
