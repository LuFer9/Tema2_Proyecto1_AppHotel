/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Luis
 */
@Entity
@Table(name = "TIPO_COCINA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoCocina.findAll", query = "SELECT t FROM TipoCocina t"),
    @NamedQuery(name = "TipoCocina.findByNombre", query = "SELECT t FROM TipoCocina t WHERE t.nombre = :nombre")})
public class TipoCocina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(mappedBy = "tipoCocina")
    private Collection<ReservaSalon> reservaSalonCollection;

    public TipoCocina() {
    }

    public TipoCocina(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<ReservaSalon> getReservaSalonCollection() {
        return reservaSalonCollection;
    }

    public void setReservaSalonCollection(Collection<ReservaSalon> reservaSalonCollection) {
        this.reservaSalonCollection = reservaSalonCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombre != null ? nombre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoCocina)) {
            return false;
        }
        TipoCocina other = (TipoCocina) object;
        if ((this.nombre == null && other.nombre != null) || (this.nombre != null && !this.nombre.equals(other.nombre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TipoCocina[ nombre=" + nombre + " ]";
    }
    
}
