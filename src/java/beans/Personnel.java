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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author machd
 */
@Entity
@Table(name = "Personnel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personnel.findAll", query = "SELECT p FROM Personnel p"),
    @NamedQuery(name = "Personnel.findByIdPerson", query = "SELECT p FROM Personnel p WHERE p.idPerson = :idPerson"),
    @NamedQuery(name = "Personnel.findByNom", query = "SELECT p FROM Personnel p WHERE p.nom = :nom"),
    @NamedQuery(name = "Personnel.findByPrenom", query = "SELECT p FROM Personnel p WHERE p.prenom = :prenom"),
    @NamedQuery(name = "Personnel.findByMatricule", query = "SELECT p FROM Personnel p WHERE p.matricule = :matricule"),
    @NamedQuery(name = "Personnel.findByCin", query = "SELECT p FROM Personnel p WHERE p.cin = :cin"),
    @NamedQuery(name = "Personnel.findBySexe", query = "SELECT p FROM Personnel p WHERE p.sexe = :sexe"),
    @NamedQuery(name = "Personnel.findByResidence", query = "SELECT p FROM Personnel p WHERE p.residence = :residence")})
public class Personnel implements Serializable {
    @Size(max = 500)
    @Column(name = "grade")
    private String grade;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPerson")
    private Integer idPerson;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nom")
    private String nom;
    @Size(max = 50)
    @Column(name = "prenom")
    private String prenom;
    @Column(name = "matricule")
    private String matricule;
    @Size(max = 50)
    @Column(name = "cin")
    private String cin;
    @Size(max = 10)
    @Column(name = "sexe")
    private String sexe;
    @Size(max = 100)
    @Column(name = "residence")
    private String residence;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private Collection<OrdreMission> ordreMissionCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "personne")
    private Voiture voiture;
    @JoinColumn(name = "echelle", referencedColumnName = "echelle")
    @ManyToOne(optional = false)
    private Correspondrepas echelle;

    
  
    
    public Personnel() {
    }

    public Personnel(Integer idPerson) {
        this.idPerson = idPerson;
    }

    public Personnel(Integer idPerson, String nom) {
        this.idPerson = idPerson;
        this.nom = nom;
    }

    public Integer getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    @XmlTransient
    public Collection<OrdreMission> getOrdreMissionCollection() {
        return ordreMissionCollection;
    }

    public void setOrdreMissionCollection(Collection<OrdreMission> ordreMissionCollection) {
        this.ordreMissionCollection = ordreMissionCollection;
    }

    @XmlTransient
    public Voiture getVoiture() {
        return voiture;
    }

    
    
    public void setVoiture(Voiture voiture) {
        this.voiture = voiture;
    }

    public Correspondrepas getEchelle() {
        return echelle;
    }

    public void setEchelle(Correspondrepas echelle) {
        this.echelle = echelle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPerson != null ? idPerson.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personnel)) {
            return false;
        }
        Personnel other = (Personnel) object;
        if ((this.idPerson == null && other.idPerson != null) || (this.idPerson != null && !this.idPerson.equals(other.idPerson))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return matricule;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
    
}
