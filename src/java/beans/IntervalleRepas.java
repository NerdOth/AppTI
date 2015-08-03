/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author machd
 */
@Entity
@Table(name = "IntervalleRepas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IntervalleRepas.findAll", query = "SELECT i FROM IntervalleRepas i"),
    @NamedQuery(name = "IntervalleRepas.findByIdRepas", query = "SELECT i FROM IntervalleRepas i WHERE i.idRepas = :idRepas"),
    @NamedQuery(name = "IntervalleRepas.findByHeureDebut", query = "SELECT i FROM IntervalleRepas i WHERE i.heureDebut = :heureDebut"),
    @NamedQuery(name = "IntervalleRepas.findByHeureFin", query = "SELECT i FROM IntervalleRepas i WHERE i.heureFin = :heureFin")})
public class IntervalleRepas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idRepas")
    private Integer idRepas;
    @Column(name = "heureDebut")
    @Temporal(TemporalType.TIME)
    private Date heureDebut;
    @Column(name = "heureFin")
    @Temporal(TemporalType.TIME)
    private Date heureFin;

    public IntervalleRepas() {
    }

    public IntervalleRepas(Integer idRepas) {
        this.idRepas = idRepas;
    }

    public Integer getIdRepas() {
        return idRepas;
    }

    public void setIdRepas(Integer idRepas) {
        this.idRepas = idRepas;
    }

    public Date getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(Date heureDebut) {
        this.heureDebut = heureDebut;
    }

    public Date getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(Date heureFin) {
        this.heureFin = heureFin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRepas != null ? idRepas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IntervalleRepas)) {
            return false;
        }
        IntervalleRepas other = (IntervalleRepas) object;
        if ((this.idRepas == null && other.idRepas != null) || (this.idRepas != null && !this.idRepas.equals(other.idRepas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.IntervalleRepas[ idRepas=" + idRepas + " ]";
    }
    
}
