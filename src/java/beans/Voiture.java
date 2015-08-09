/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author machd
 */
@Entity
@Table(name = "Voiture")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Voiture.findAll", query = "SELECT v FROM Voiture v"),
    @NamedQuery(name = "Voiture.findByIdVoiture", query = "SELECT v FROM Voiture v WHERE v.idVoiture = :idVoiture"),
    @NamedQuery(name = "Voiture.findByMatricule", query = "SELECT v FROM Voiture v WHERE v.matricule = :matricule"),
    @NamedQuery(name = "Voiture.findByMarque", query = "SELECT v FROM Voiture v WHERE v.marque = :marque")})
public class Voiture implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idVoiture")
    private Integer idVoiture;
    @Size(max = 50)
    @Column(name = "matricule")
    private String matricule;
    @Size(max = 100)
    @Column(name = "marque")
    private String marque;
    @JoinColumn(name = "pf", referencedColumnName = "pf")
    @ManyToOne(optional = false)
    private Correspondpf pf;
    @JoinColumn(name = "personne", referencedColumnName = "idPerson")
    @ManyToOne(optional = false)
    private Personnel personne;

    public Voiture() {
    }

    public Voiture(Integer idVoiture) {
        this.idVoiture = idVoiture;
    }

    public Integer getIdVoiture() {
        return idVoiture;
    }

    public void setIdVoiture(Integer idVoiture) {
        this.idVoiture = idVoiture;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public Correspondpf getPf() {
        return pf;
    }

    public void setPf(Correspondpf pf) {
        this.pf = pf;
    }

    public Personnel getPersonne() {
        return personne;
    }

    public void setPersonne(Personnel personne) {
        this.personne = personne;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVoiture != null ? idVoiture.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Voiture)) {
            return false;
        }
        Voiture other = (Voiture) object;
        if ((this.idVoiture == null && other.idVoiture != null) || (this.idVoiture != null && !this.idVoiture.equals(other.idVoiture))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Voiture[ idVoiture=" + idVoiture + " ]";
    }

}
