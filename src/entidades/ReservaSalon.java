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
@Table(name = "RESERVA_SALON")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReservaSalon.findAll", query = "SELECT r FROM ReservaSalon r"),
    @NamedQuery(name = "ReservaSalon.findByCodSalon", query = "SELECT r FROM ReservaSalon r WHERE r.codSalon = :codSalon"),
    @NamedQuery(name = "ReservaSalon.findByTipoEvento", query = "SELECT r FROM ReservaSalon r WHERE r.tipoEvento = :tipoEvento"),
    @NamedQuery(name = "ReservaSalon.findByNumPersonas", query = "SELECT r FROM ReservaSalon r WHERE r.numPersonas = :numPersonas"),
    @NamedQuery(name = "ReservaSalon.findByFechaEvento", query = "SELECT r FROM ReservaSalon r WHERE r.fechaEvento = :fechaEvento"),
    @NamedQuery(name = "ReservaSalon.findByHabitaciones", query = "SELECT r FROM ReservaSalon r WHERE r.habitaciones = :habitaciones"),
    @NamedQuery(name = "ReservaSalon.findByNumHabitaciones", query = "SELECT r FROM ReservaSalon r WHERE r.numHabitaciones = :numHabitaciones"),
    @NamedQuery(name = "ReservaSalon.findByNumDias", query = "SELECT r FROM ReservaSalon r WHERE r.numDias = :numDias")})
public class ReservaSalon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "COD_SALON")
    private Integer codSalon;
    @Basic(optional = false)
    @Column(name = "TIPO_EVENTO")
    private String tipoEvento;
    @Basic(optional = false)
    @Column(name = "NUM_PERSONAS")
    private int numPersonas;
    @Basic(optional = false)
    @Column(name = "FECHA_EVENTO")
    @Temporal(TemporalType.DATE)
    private Date fechaEvento;
    @Column(name = "HABITACIONES")
    private Boolean habitaciones;
    @Column(name = "NUM_HABITACIONES")
    private Integer numHabitaciones;
    @Column(name = "NUM_DIAS")
    private Integer numDias;
    @JoinColumn(name = "DNI_CLIENTE", referencedColumnName = "DNI")
    @ManyToOne(optional = false)
    private Persona dniCliente;
    @JoinColumn(name = "TIPO_COCINA", referencedColumnName = "NOMBRE")
    @ManyToOne
    private TipoCocina tipoCocina;

    public ReservaSalon() {
    }

    public ReservaSalon(Integer codSalon) {
        this.codSalon = codSalon;
    }

    public ReservaSalon(Integer codSalon, String tipoEvento, int numPersonas, Date fechaEvento) {
        this.codSalon = codSalon;
        this.tipoEvento = tipoEvento;
        this.numPersonas = numPersonas;
        this.fechaEvento = fechaEvento;
    }

    public Integer getCodSalon() {
        return codSalon;
    }

    public void setCodSalon(Integer codSalon) {
        this.codSalon = codSalon;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public int getNumPersonas() {
        return numPersonas;
    }

    public void setNumPersonas(int numPersonas) {
        this.numPersonas = numPersonas;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public Boolean getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(Boolean habitaciones) {
        this.habitaciones = habitaciones;
    }

    public Integer getNumHabitaciones() {
        return numHabitaciones;
    }

    public void setNumHabitaciones(Integer numHabitaciones) {
        this.numHabitaciones = numHabitaciones;
    }

    public Integer getNumDias() {
        return numDias;
    }

    public void setNumDias(Integer numDias) {
        this.numDias = numDias;
    }

    public Persona getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(Persona dniCliente) {
        this.dniCliente = dniCliente;
    }

    public TipoCocina getTipoCocina() {
        return tipoCocina;
    }

    public void setTipoCocina(TipoCocina tipoCocina) {
        this.tipoCocina = tipoCocina;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codSalon != null ? codSalon.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservaSalon)) {
            return false;
        }
        ReservaSalon other = (ReservaSalon) object;
        if ((this.codSalon == null && other.codSalon != null) || (this.codSalon != null && !this.codSalon.equals(other.codSalon))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ReservaSalon[ codSalon=" + codSalon + " ]";
    }
    
}
