/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author machd
 */
@Entity
@Table(name = "Trimestre")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trimestre.findAll", query = "SELECT t FROM Trimestre t"),
    @NamedQuery(name = "Trimestre.findByIdTrim", query = "SELECT t FROM Trimestre t WHERE t.idTrim = :idTrim"),
    @NamedQuery(name = "Trimestre.findByDateDebut", query = "SELECT t FROM Trimestre t WHERE t.dateDebut = :dateDebut"),
    @NamedQuery(name = "Trimestre.findByDateFin", query = "SELECT t FROM Trimestre t WHERE t.dateFin = :dateFin"),
    @NamedQuery(name = "Trimestre.findByEtat", query = "SELECT t FROM Trimestre t WHERE t.etat = :etat")})
public class Trimestre implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idTrim")
    private Integer idTrim;
    @Column(name = "dateDebut")
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @Column(name = "dateFin")
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    @Column(name = "etat")
    private Integer etat;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trim1")
    private Collection<OrdreMission> ordreMissionCollection;

    public Trimestre() {
    }

    public Trimestre(Integer idTrim) {
        this.idTrim = idTrim;
    }

    public Integer getIdTrim() {
        return idTrim;
    }

    public void setIdTrim(Integer idTrim) {
        this.idTrim = idTrim;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Integer getEtat() {
        return etat;
    }

    public void setEtat(Integer etat) {
        this.etat = etat;
    }

    @XmlTransient
    public Collection<OrdreMission> getOrdreMissionCollection() {
        return ordreMissionCollection;
    }

    public void setOrdreMissionCollection(Collection<OrdreMission> ordreMissionCollection) {
        this.ordreMissionCollection = ordreMissionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTrim != null ? idTrim.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trimestre)) {
            return false;
        }
        Trimestre other = (Trimestre) object;
        if ((this.idTrim == null && other.idTrim != null) || (this.idTrim != null && !this.idTrim.equals(other.idTrim))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Trimestre[ idTrim=" + idTrim + " ]";
    }
    
}
