package org.examen_2024.services;

import org.examen_2024.entities.Tecnico;
import org.examen_2024.entities.TicketSoporte;
import org.examen_2024.entities.Usuario;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class ServicioSoporte {

    private List<TicketSoporte> tickets;
    private Set<Usuario> usuarios;
    private Set<Tecnico> tecnicos;

    public ServicioSoporte() {
        this.tickets = new ArrayList<>();
        this.usuarios = new HashSet<>();
        this.tecnicos = new HashSet<>();
    }

    public List<TicketSoporte> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketSoporte> tickets) {
        this.tickets = tickets;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Set<Tecnico> getTecnicos() {
        return tecnicos;
    }

    public void setTecnicos(Set<Tecnico> tecnicos) {
        this.tecnicos = tecnicos;
    }

    //Métodos

    /**
     * Añadimos un usuario
     * @param usuario
     */
    public void addUsuario(Usuario usuario){
        this.usuarios.add(usuario);
    }

    /**
     * Filtramos los usuarios buscando el que coincida con la id, al encontrarlo procedemos a borrarlo.
     * @param id
     */

    public void delUsuario(Long id){
        /*Usuario usuarioDelete = usuarios.stream()
                .filter(usu -> usu.getId().equals(id))
                .findFirst()
                .orElseThrow();*/
        this.usuarios.remove(findUsuarioById(id));
        this.tickets.removeIf(ticket -> ticket.getUsuarioSolicitante().getId().equals(id));
    }

    /**
     * Añadimos un técnico
     * @param tecnico
     */

    public void addTecnico(Tecnico tecnico){
        this.tecnicos.add(tecnico);
    }
    /**
     * Filtramos los técnicos buscando el que coincida con la id, al encontrarlo procedemos a borrarlo.
     * @param id
     */

    public void delTecnico(Long id){
        /*Tecnico tecnicoDelete = tecnicos.stream()
                .filter(tec -> tec.getId().equals(id))
                .findFirst()
                .orElseThrow();*/
        this.tecnicos.remove(findTecnicoById(id));
        this.tickets.removeIf(ticket -> ticket.getTenicoAsignado().getId().equals(id));

        /*this.tecnicos.removeIf(tecnico -> {
            findTecnicoById(id);
            tickets.stream()
                    .filter(ticket -> ticket.getTenicoAsignado().equals(id))
                    .re
        })*/
    }

    /**
     * Añadimos un nuevo ticket creando un objeto nuevo, pasándole por parámetro los atributos,
     * el estado al ser un ticket nuevo, está en abierto, y el id, vemos el tamaño de los tickets y le sumamos
     * 1L (es un Long).
     * @param fechaCreacion
     * @param fehcaFinalizacion
     * @param prioridad
     * @param usuarioSolicitante
     * @param tenicoAsignado
     * @param comentarios
     */

    public void addTicketSoporte(LocalDate fechaCreacion, LocalDate fehcaFinalizacion, Integer prioridad,
                                 Usuario usuarioSolicitante, Tecnico tenicoAsignado, String comentarios){
        tickets.add(new TicketSoporte(tickets.size() + 1L ,fechaCreacion, fehcaFinalizacion,
                TicketSoporte.Estado.ABIERTO, prioridad, usuarioSolicitante, tenicoAsignado, comentarios));

    }

    /**
     * Buscamos el ticket que queremos borrar, si lo encontramos lo borramos lo borramos.
     * @param id
     */

    public void deleteTicketSoporte(Long id){

        tickets.remove(findTicketById(id));

        /*tickets.remove(tickets.stream()
                .filter(tic -> tic.getId().equals(id))
                .findFirst()
                .orElseThrow());*/
    }

    /**
     * Filtramos a los técnicos por su id, si lo encontramos lo devolvemos.
     * @param id
     * @return tecnico que coincida con el id.
     */
    public Tecnico findTecnicoById(Long id){

        return tecnicos.stream()
                .filter(tec -> tec.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

    /**
     * Filtramos a los usuarios por su id, si lo encontramos lo devolvemos.
     * @param id
     * @return usuario que coincida con el id.
     */

    public Usuario findUsuarioById(Long id){

        return usuarios.stream()
                .filter(usu -> usu.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

    /**
     * Filtramos a los técnicos por su especialidad.
     * @param especialidad
     * @return todos los técnicos que tengan esa especialidad.
     */
    public List<Tecnico> getTecnicosByEspecialidad(Tecnico.Especialidad especialidad) {

        return tecnicos.stream()
                .filter(tec -> tec.getEspecialidad().equals(especialidad))
                .toList();
    }

    /**
     * Guardamos dentro de un mapa los técnicos agrupados por especialidad,
     * después ordenamos a los técnicos por su valoración.
     * @return Una lista de técnicos agrupados por especialidad y ordenados por valoración.
     */

    public Map<Tecnico.Especialidad, List<Tecnico>> getTecnicoGroupByEspecialidad(){

        Map<Tecnico.Especialidad, List<Tecnico>> tecnicosAgrupados = tecnicos.stream()
                .collect(Collectors.groupingBy(Tecnico::getEspecialidad));

        tecnicosAgrupados.forEach((k, v) -> {
            v.sort(Comparator.comparing(Tecnico::getValoracion));
        });
        return tecnicosAgrupados;
    }

    /**
     * filtramos y buscamos el ticket que nos coincida con el id indicado.
     * @param id
     * @return el ticket encontrado.
     */

    public TicketSoporte findTicketById(Long id){

        return tickets.stream()
                .filter(ticket -> ticket.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

    /**
     * Filtramos los tickets, nos quedamos con los que estén abiertos,
     * los ordenados por fecha de creación descendentemente.
     * @return todos los tickets abiertos.
     */
    public List<TicketSoporte> getTicketAbiertos(){

        return tickets.stream()
                .filter(ticket -> ticket.getEstado().equals(TicketSoporte.Estado.ABIERTO))
                .sorted(Comparator.comparing(TicketSoporte::getFechaCreacion).reversed())
                .toList();

    }

    /**
     * Filtramos los tickets, nos quedamos con los que estén resueltos,
     * los ordenados por fecha de finalización descendentemente.
     * @return todos los tickets ya resueltos.
     */
    public List<TicketSoporte> getTicketsCerrados(){

        return tickets.stream()
                .filter(ticket -> ticket.getEstado().equals(TicketSoporte.Estado.RESUELTO))
                .sorted(Comparator.comparing(TicketSoporte::getFehcaFinalizacion).reversed())
                .toList();
    }

    /**
     * Nos quedamos con los tickets que estén en Proceso, buscamos los que tengan asigando
     * un técnico de la especialidad de informática.
     * @return todos los tickets que coincidan con lo que buscamos.
     */
    public List<TicketSoporte> getTicketsEnProcesoTecnicoInformatico(){

        return tickets.stream()
                .filter(ticket -> ticket.getEstado().equals(TicketSoporte.Estado.ENPROCESO))
                .filter(ticket -> ticket.getTenicoAsignado().getEspecialidad().equals(Tecnico.Especialidad.INFORMATICA))
                .toList();
    }

    /**
     * Cogemos los tickets resueltos, filtramos por la prioridad indicada y contamos cuántos son.
     * @param prioridad
     * @return el número de tickets encontrados.
     */
    public Integer getTotalTicketsResueltos(Integer prioridad){

        Long resueltos = tickets.stream()
                .filter(ticket -> ticket.getEstado().equals(TicketSoporte.Estado.RESUELTO))
                .filter(ticket -> ticket.getPrioridad().equals(prioridad))
                .count();
        return resueltos.intValue();
    }

    /**
     * Filtramos el estado y la prioridad, ordenamos los tickets por fecha de creeacion ascenentemente.
     * @param estado
     * @param prioridad
     * @return un Set de los tickets encontados.
     */
    public Set<TicketSoporte> findTicketsByEstadoAndPrioridad(TicketSoporte.Estado estado, Integer prioridad) {

        return tickets.stream()
                .filter(ticket -> ticket.getEstado().equals(estado))
                .filter(ticket -> ticket.getPrioridad().equals(prioridad))
                .sorted(Comparator.comparing(TicketSoporte::getFechaCreacion))
                .collect(Collectors.toCollection(LinkedHashSet::new));

    }

    /**
     * Filtramos todos los tickets que tengan técnicos asignados.
     * Nos quedamos solo con los técnicos agrupados por especialidad.
     * @return
     */
    public Map<Tecnico.Especialidad, List<Tecnico>> findTecnicoSinTickets(){

        return tickets.stream()
                .filter(ticket -> ticket.getTenicoAsignado() != null)
                .map(TicketSoporte::getTenicoAsignado)
                .distinct()
                .collect(Collectors.groupingBy(Tecnico::getEspecialidad));
    }

    /**
     * filtramos los tickets que se han resuelto en menos de 5 días,
     * nos quedamos con los técnicos y los guardamos.
     * @return los técnicos que resuelven los tickets más rápi
     */
    public Set<Tecnico> findTecnicosRapidos(){

        Set<Tecnico> tecnicosRapidos = tickets.stream()
                .filter(ticket -> Period.between(ticket.getFechaCreacion(), ticket.getFehcaFinalizacion()).getDays() <= 5)
                .map(TicketSoporte::getTenicoAsignado)
                .collect(Collectors.toSet());
        return tecnicosRapidos;
    }

    /**
     * filtramos primeros los tickets que estén resueltos, depués filtramos los que han tardado mas de una semana,
     * los contamos.
     * @return el número de tickets que han tardado más de una semana en resolver.
     */
    public Integer getTotalTicketsRetardados(){

        Long ticketsRetardados = tickets.stream()
                .filter(ticket -> ticket.getEstado().equals(TicketSoporte.Estado.RESUELTO))
                .filter(ticket -> Period.between(ticket.getFechaCreacion(), ticket.getFehcaFinalizacion()).getDays() > 7)
                .count();
        return ticketsRetardados.intValue();
    }

    /**
     * Filtramos los ticket por prioridad, calculamos la media de los días en que esos tickets
     * se resuelven.
     * @param prioridad
     * @return la media de los días.
     */
    public Double getMediaResolucionTickets(Integer prioridad){


        return tickets.stream()
                .filter(ticket -> ticket.getEstado().equals(TicketSoporte.Estado.RESUELTO))
                .filter(ticket -> ticket.getPrioridad().equals(prioridad))
                .collect(Collectors.averagingLong(ticket -> {
                    Integer numDias = Period.between(ticket.getFechaCreacion(), ticket.getFehcaFinalizacion()).getDays();
                    return numDias;
                }));
    }

    /**
     * Filtramos los tickets resueltos, agrupamos por técnico y
     * calculamos la media de días en las que resuelve los tickets.
     * @return el técnico y la media de días en las que resuelve tickets.
     */
    public Map<Tecnico, Double> getMediaResolucionTicketsGroupByTecnico(){

        return tickets.stream()
                .filter(ticket -> ticket.getEstado().equals(TicketSoporte.Estado.RESUELTO))
                .collect(Collectors.groupingBy(TicketSoporte::getTenicoAsignado,
                        Collectors.averagingLong(ticket -> {
                            Integer numDias = Period.between(ticket.getFechaCreacion(), ticket.getFehcaFinalizacion()).getDays();
                            return numDias;
                        })));
    }

    /**
     * filtramos los tickets que estén resueltos.
     * @return true si todos los elementos cumplen con lo indicado.
     * False, si no todos lo cumplen.
     */
    public Boolean areAllTicketsFinidhedLessThanTenDays(){

        return tickets.stream()
                .filter(ticket -> ticket.getEstado().equals(TicketSoporte.Estado.RESUELTO))
                .allMatch(ticket -> Period.between(ticket.getFechaCreacion(), ticket.getFehcaFinalizacion()).getDays() < 10);
    }

    /**
     * Filtramos los tickets y buscamos si hay alguno que haya sido resuelto el primer día.
     * @return Si lo encontramos lo devolvemos.
     */
    public Optional<TicketSoporte> getFirstTicketSolvedOneDay(){

        return tickets.stream()
                .filter(ticket -> Period.between(ticket.getFechaCreacion(), ticket.getFehcaFinalizacion()).getDays() <= 1)
                .findFirst();
    }





}
