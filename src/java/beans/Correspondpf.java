/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author machd
 */
@Entity
@Table(name = "Correspond_pf")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Correspondpf.findAll", query = "SELECT c FROM Correspondpf c"),
    @NamedQuery(name = "Correspondpf.findByIdCorpf", query = "SELECT c FROM Correspondpf c WHERE c.idCorpf = :idCorpf"),
    @NamedQuery(name = "Correspondpf.findByPf", query = "SELECT c FROM Correspondpf c WHERE c.pf = :pf"),
    @NamedQuery(name = "Correspondpf.findByTaux", query = "SELECT c FROM Correspondpf c WHERE c.taux = :taux")})
public class Correspondpf implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idCorpf")
    private int idCorpf;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "taux")
    private Double taux;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pf")
    private Integer pf;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pf")
    private Collection<Voiture> voitureCollection;

    public Correspondpf() {
    }

    public Correspondpf(Integer pf) {
        this.pf = pf;
    }


    public Integer getPf() {
        return pf;
    }

    public void setPf(Integer pf) {
        this.pf = pf;
    }


    @XmlTransient
    public Collection<Voiture> getVoitureCollection() {
        return voitureCollection;
    }

    public void setVoitureCollection(Collection<Voiture> voitureCollection) {
        this.voitureCollection = voitureCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pf != null ? pf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Correspondpf)) {
            return false;
        }
        Correspondpf other = (Correspondpf) object;
        if ((this.pf == null && other.pf != null) || (this.pf != null && !this.pf.equals(other.pf))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Correspondpf[ pf=" + pf + " ]";
    }

    public int getIdCorpf() {
        return idCorpf;
    }

    public void setIdCorpf(int idCorpf) {
        this.idCorpf = idCorpf;
    }

    public Double getTaux() {
        return taux;
    }

    public void setTaux(Double taux) {
        this.taux = taux;
    }
    
}
