/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis
 */
@Entity
@Table(name = "RESERVA_HABITACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReservaHabitacion.findAll", query = "SELECT r FROM ReservaHabitacion r"),
    @NamedQuery(name = "ReservaHabitacion.findByCodHabitacion", query = "SELECT r FROM ReservaHabitacion r WHERE r.codHabitacion = :codHabitacion"),
    @NamedQuery(name = "ReservaHabitacion.findByFechaLlegada", query = "SELECT r FROM ReservaHabitacion r WHERE r.fechaLlegada = :fechaLlegada"),
    @NamedQuery(name = "ReservaHabitacion.findByFechaSalida", query = "SELECT r FROM ReservaHabitacion r WHERE r.fechaSalida = :fechaSalida"),
    @NamedQuery(name = "ReservaHabitacion.findByRegimen", query = "SELECT r FROM ReservaHabitacion r WHERE r.regimen = :regimen"),
    @NamedQuery(name = "ReservaHabitacion.findByNumeroHabitaciones", query = "SELECT r FROM ReservaHabitacion r WHERE r.numeroHabitaciones = :numeroHabitaciones"),
    @NamedQuery(name = "ReservaHabitacion.findByFumador", query = "SELECT r FROM ReservaHabitacion r WHERE r.fumador = :fumador")})
public class ReservaHabitacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "COD_HABITACION")
    private Integer codHabitacion;
    @Basic(optional = false)
    @Column(name = "FECHA_LLEGADA")
    @Temporal(TemporalType.DATE)
    private Date fechaLlegada;
    @Basic(optional = false)
    @Column(name = "FECHA_SALIDA")
    @Temporal(TemporalType.DATE)
    private Date fechaSalida;
    @Basic(optional = false)
    @Column(name = "REGIMEN")
    private String regimen;
    @Basic(optional = false)
    @Column(name = "NUMERO_HABITACIONES")
    private int numeroHabitaciones;
    @Column(name = "FUMADOR")
    private Boolean fumador;
    @JoinColumn(name = "DNI_CLIENTE", referencedColumnName = "DNI")
    @ManyToOne(optional = false)
    private Persona dniCliente;
    @JoinColumn(name = "TIPO_HABITACION", referencedColumnName = "NOMBRE")
    @ManyToOne(optional = false)
    private TipoHabitacion tipoHabitacion;

    public ReservaHabitacion() {
    }

    public ReservaHabitacion(Integer codHabitacion) {
        this.codHabitacion = codHabitacion;
    }

    public ReservaHabitacion(Integer codHabitacion, Date fechaLlegada, Date fechaSalida, String regimen, int numeroHabitaciones) {
        this.codHabitacion = codHabitacion;
        this.fechaLlegada = fechaLlegada;
        this.fechaSalida = fechaSalida;
        this.regimen = regimen;
        this.numeroHabitaciones = numeroHabitaciones;
    }

    public Integer getCodHabitacion() {
        return codHabitacion;
    }

    public void setCodHabitacion(Integer codHabitacion) {
        this.codHabitacion = codHabitacion;
    }

    public Date getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(Date fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getRegimen() {
        return regimen;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    public int getNumeroHabitaciones() {
        return numeroHabitaciones;
    }

    public void setNumeroHabitaciones(int numeroHabitaciones) {
        this.numeroHabitaciones = numeroHabitaciones;
    }

    public Boolean getFumador() {
        return fumador;
    }

    public void setFumador(Boolean fumador) {
        this.fumador = fumador;
    }

    public Persona getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(Persona dniCliente) {
        this.dniCliente = dniCliente;
    }

    public TipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codHabitacion != null ? codHabitacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservaHabitacion)) {
            return false;
        }
        ReservaHabitacion other = (ReservaHabitacion) object;
        if ((this.codHabitacion == null && other.codHabitacion != null) || (this.codHabitacion != null && !this.codHabitacion.equals(other.codHabitacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ReservaHabitacion[ codHabitacion=" + codHabitacion + " ]";
    }
    
}
