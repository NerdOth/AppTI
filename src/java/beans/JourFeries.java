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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author machd
 */
@Entity
@Table(name = "JourFeries")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JourFeries.findAll", query = "SELECT j FROM JourFeries j"),
    @NamedQuery(name = "JourFeries.findByIdF", query = "SELECT j FROM JourFeries j WHERE j.idF = :idF"),
    @NamedQuery(name = "JourFeries.findByJourDepart", query = "SELECT j FROM JourFeries j WHERE j.jourDepart = :jourDepart"),
    @NamedQuery(name = "JourFeries.findByNbrJours", query = "SELECT j FROM JourFeries j WHERE j.nbrJours = :nbrJours"),
    @NamedQuery(name = "JourFeries.findByEvenement", query = "SELECT j FROM JourFeries j WHERE j.evenement = :evenement")})
public class JourFeries implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Basic(optional = false)
    @NotNull
    @Column(name = "idF")
    private Integer idF;
    @Column(name = "jourDepart")
    @Temporal(TemporalType.DATE)
    private Date jourDepart;
    @Column(name = "nbrJours")
    private Integer nbrJours;
    @Size(max = 150)
    @Column(name = "evenement")
    private String evenement;

    public JourFeries() {
    }

    public JourFeries(Integer idF) {
        this.idF = idF;
    }

    public Integer getIdF() {
        return idF;
    }

    public void setIdF(Integer idF) {
        this.idF = idF;
    }

    public Date getJourDepart() {
        return jourDepart;
    }

    public void setJourDepart(Date jourDepart) {
        this.jourDepart = jourDepart;
    }

    public Integer getNbrJours() {
        return nbrJours;
    }

    public void setNbrJours(Integer nbrJours) {
        this.nbrJours = nbrJours;
    }

    public String getEvenement() {
        return evenement;
    }

    public void setEvenement(String evenement) {
        this.evenement = evenement;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idF != null ? idF.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JourFeries)) {
            return false;
        }
        JourFeries other = (JourFeries) object;
        if ((this.idF == null && other.idF != null) || (this.idF != null && !this.idF.equals(other.idF))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.JourFeries[ idF=" + idF + " ]";
    }
    
}
