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
@Table(name = "Correspond_repas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Correspondrepas.findAll", query = "SELECT c FROM Correspondrepas c"),
    @NamedQuery(name = "Correspondrepas.findByIdCor", query = "SELECT c FROM Correspondrepas c WHERE c.idCor = :idCor"),
    @NamedQuery(name = "Correspondrepas.findByEchelle", query = "SELECT c FROM Correspondrepas c WHERE c.echelle = :echelle"),
    @NamedQuery(name = "Correspondrepas.findByTaux15", query = "SELECT c FROM Correspondrepas c WHERE c.taux15 = :taux15"),
    @NamedQuery(name = "Correspondrepas.findByTaux16", query = "SELECT c FROM Correspondrepas c WHERE c.taux16 = :taux16")})
public class Correspondrepas implements Serializable {
    private static final long serialVersionUID = 1L;
        @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Basic(optional = false)
    @NotNull
    @Column(name = "idCor")
    private int idCor;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "echelle")
    private Integer echelle;
    @Column(name = "taux15")
    private Integer taux15;
    @Column(name = "taux16")
    private Integer taux16;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "echelle")
    private Collection<Personnel> personnelCollection;

    public Correspondrepas() {
    }

    public Correspondrepas(Integer echelle) {
        this.echelle = echelle;
    }

    public Correspondrepas(Integer echelle, int idCor) {
        this.echelle = echelle;
        this.idCor = idCor;
    }

    public int getIdCor() {
        return idCor;
    }

    public void setIdCor(int idCor) {
        this.idCor = idCor;
    }

    public Integer getEchelle() {
        return echelle;
    }

    public void setEchelle(Integer echelle) {
        this.echelle = echelle;
    }

    public Integer getTaux15() {
        return taux15;
    }

    public void setTaux15(Integer taux15) {
        this.taux15 = taux15;
    }

    public Integer getTaux16() {
        return taux16;
    }

    public void setTaux16(Integer taux16) {
        this.taux16 = taux16;
    }

    @XmlTransient
    public Collection<Personnel> getPersonnelCollection() {
        return personnelCollection;
    }

    public void setPersonnelCollection(Collection<Personnel> personnelCollection) {
        this.personnelCollection = personnelCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (echelle != null ? echelle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Correspondrepas)) {
            return false;
        }
        Correspondrepas other = (Correspondrepas) object;
        if ((this.echelle == null && other.echelle != null) || (this.echelle != null && !this.echelle.equals(other.echelle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return echelle.toString() ;
    }
    
}
