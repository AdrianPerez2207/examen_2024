package org.examen_2024.io;

import org.examen_2024.entities.Tecnico;
import org.examen_2024.entities.TicketSoporte;
import org.examen_2024.entities.Usuario;
import org.examen_2024.services.ServicioSoporte;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TicketsSoporte {

    public static ServicioSoporte cargarCSV() throws IOException {
        /**
         * Leemos todos los ficheros
         */

        Path fileUsuarios = Paths.get(".","src", "main", "java", "org","examen_2024", "resources", "usuarios.csv");
        Path fileTecnicos = Paths.get(".","src", "main", "java", "org","examen_2024", "resources", "tecnico.csv");
        Path fileTickets = Paths.get(".","src", "main", "java", "org","examen_2024", "resources", "tickets.csv");

        /**
         * Creamos el objeto servicioSoporte
         */
        ServicioSoporte ss = new ServicioSoporte();


        /**
         * Dividimos todas las líneas, las añadimos al usuario y metemos todos los usuarios en ServicioSoporte.
         */
        Set<Usuario> usuarios = Files.lines(fileUsuarios)
                .map(str -> {
                    String[] cad = str.split(",");
                    Usuario usuario = new Usuario(Long.parseLong(cad[0]), cad[1], cad[2],
                            cad[3], cad[4], LocalDate.parse(cad[5]));
                    return usuario;
                })
                .collect(Collectors.toSet());
        ss.setUsuarios(usuarios);

        /**
         * Dividimos todas las líneas, las añadimos al técnico y metemos todos los técnicos en ServicioSoporte.
         */
        Set<Tecnico> tecnicos = Files.lines(fileTecnicos)
                .map(str -> {
                    String[] cad = str.split(",");
                    Tecnico tecnico = new Tecnico(Long.parseLong(cad[0]), cad[1], cad[2],
                            cad[3], cad[4], Tecnico.Especialidad.valueOf(cad[5]), Integer.parseInt(cad[6]));
                    return tecnico;
                })
                .collect(Collectors.toSet());
        ss.setTecnicos(tecnicos);

        /**
         * Dividimos todas las líneas, las añadimos al ticket y metemos todos los tickets en ServicioSoporte.
         */
        List<TicketSoporte> tickets = Files.lines(fileTickets)
                .map(str -> {
                    String[] cad = str.split(",");
                    TicketSoporte ticket = new TicketSoporte(Long.parseLong(cad[0]),LocalDate.parse(cad[1]), LocalDate.parse(cad[2]),
                            TicketSoporte.Estado.valueOf(cad[3]), Integer.parseInt(cad[4]),
                            ss.findUsuarioById(Long.parseLong(cad[5])), ss.findTecnicoById(Long.parseLong(cad[6])), cad[7]);
                    return ticket;
                })
                .toList();
        ss.setTickets(tickets);
        return ss;
    }

    public static void grabarCSV(ServicioSoporte ss) throws IOException {

        /**
         * Leemos todos los ficheros
         */

        Path fileUsuarios = Paths.get("src", "main", "java", "org","examen_2024", "resources", "usuarios.csv");
        Path fileTecnicos = Paths.get("src", "main", "java", "org","examen_2024", "resources", "tecnico.csv");
        Path fileTickets = Paths.get("src", "main", "java", "org","examen_2024", "resources", "tickets.csv");

        Files.deleteIfExists(fileUsuarios);
        BufferedWriter bwUsuarios = Files.newBufferedWriter(fileUsuarios,
                StandardOpenOption.CREATE,
                StandardOpenOption.WRITE);

        StringBuffer sbUsuario = new StringBuffer();
        for (Usuario usuario : ss.getUsuarios()){
            sbUsuario.append(usuario.getId()).append(",");
            sbUsuario.append(usuario.getNombre()).append(",");
            sbUsuario.append(usuario.getApellidos()).append(",");
            sbUsuario.append(usuario.getEmail()).append(",");
            sbUsuario.append(usuario.getMovil()).append(",");
            sbUsuario.append(usuario.getFechaAlta()).append("\n");
        }
        bwUsuarios.write(sbUsuario.toString());
        bwUsuarios.close();

        Files.deleteIfExists(fileTecnicos);
        BufferedWriter bwTecnicos = Files.newBufferedWriter(fileTecnicos,
                StandardOpenOption.CREATE,
                StandardOpenOption.WRITE);

        StringBuffer sbTecnico = new StringBuffer();
        for (Tecnico tecnico : ss.getTecnicos()){
            sbTecnico.append(tecnico.getId()).append(",");
            sbTecnico.append(tecnico.getNombre()).append(",");
            sbTecnico.append(tecnico.getApellidos()).append(",");
            sbTecnico.append(tecnico.getEmail()).append(",");
            sbTecnico.append(tecnico.getMovil()).append(",");
            sbTecnico.append(tecnico.getEspecialidad()).append(",");
            sbTecnico.append(tecnico.getValoracion()).append("\n");
        }
        bwTecnicos.write(sbTecnico.toString());
        bwTecnicos.close();

        Files.deleteIfExists(fileTickets);
        BufferedWriter bwTickets = Files.newBufferedWriter(fileTickets,
                StandardOpenOption.CREATE,
                StandardOpenOption.WRITE);

        StringBuffer sbTickets = new StringBuffer();
        for (TicketSoporte ticket : ss.getTickets()){
            sbTickets.append(ticket.getId()).append(",");
            sbTickets.append(ticket.getFechaCreacion()).append(",");
            sbTickets.append(ticket.getFehcaFinalizacion()).append(",");
            sbTickets.append(ticket.getEstado()).append(",");
            sbTickets.append(ticket.getPrioridad()).append(",");
            sbTickets.append(ticket.getUsuarioSolicitante().getId()).append(",");
            sbTickets.append(ticket.getTenicoAsignado().getId()).append(",");
            sbTickets.append(ticket.getComentarios()).append("\n");
        }
        bwTickets.write(sbTickets.toString());
        bwTickets.close();

    }
}
