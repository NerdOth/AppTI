/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author machd
 */
@Entity
@Table(name = "OrdreMission")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdreMission.findAll", query = "SELECT o FROM OrdreMission o"),
    @NamedQuery(name = "OrdreMission.findByIdOrdre", query = "SELECT o FROM OrdreMission o WHERE o.idOrdre = :idOrdre"),
    @NamedQuery(name = "OrdreMission.findByMontant", query = "SELECT o FROM OrdreMission o WHERE o.montant = :montant"),
    @NamedQuery(name = "OrdreMission.findByVille", query = "SELECT o FROM OrdreMission o WHERE o.ville = :ville"),
    @NamedQuery(name = "OrdreMission.findByDateAller", query = "SELECT o FROM OrdreMission o WHERE o.dateAller = :dateAller"),
    @NamedQuery(name = "OrdreMission.findByHeureAller", query = "SELECT o FROM OrdreMission o WHERE o.heureAller = :heureAller"),
    @NamedQuery(name = "OrdreMission.findByObjetMission", query = "SELECT o FROM OrdreMission o WHERE o.objetMission = :objetMission"),
    @NamedQuery(name = "OrdreMission.findByMoyenTransport", query = "SELECT o FROM OrdreMission o WHERE o.moyenTransport = :moyenTransport"),
    @NamedQuery(name = "OrdreMission.findByKilometres", query = "SELECT o FROM OrdreMission o WHERE o.kilometres = :kilometres"),
    @NamedQuery(name = "OrdreMission.findByNum", query = "SELECT o FROM OrdreMission o WHERE o.num = :num")})
public class OrdreMission implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idOrdre")
    private Integer idOrdre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "montant")
    private Float montant;
    @Size(max = 50)
    @Column(name = "ville")
    private String ville;
    @Column(name = "dateAller")
    @Temporal(TemporalType.DATE)
    private Date dateAller;
    @Column(name = "heureAller")
    @Temporal(TemporalType.TIME)
    private Date heureAller;
    @Size(max = 150)
    @Column(name = "objetMission")
    private String objetMission;
    @Size(max = 100)
    @Column(name = "moyenTransport")
    private String moyenTransport;
    @Column(name = "kilometres")
    private Float kilometres;
    @Size(max = 50)
    @Column(name = "num")
    private String num;
    @JoinColumn(name = "person", referencedColumnName = "idPerson")
    @ManyToOne(optional = false)
    private Personnel person;
    @JoinColumn(name = "trim", referencedColumnName = "idTrim")
    @ManyToOne(optional = false)
    private Trimestre trim1;

    public OrdreMission() {
    }

    public OrdreMission(Integer idordre, Float montant, String ville, Date dateAller, Date heureAller, String objetMission, String moyenTransport, Float kilometres, String num, Personnel person, Trimestre trim1) {
        this.idOrdre = idordre;
        this.montant = montant;
        this.ville = ville;
        this.dateAller = dateAller;
        this.heureAller = heureAller;
        this.objetMission = objetMission;
        this.moyenTransport = moyenTransport;
        this.kilometres = kilometres;
        this.num = num;
        this.person = person;
        this.trim1 = trim1;
    }

    public OrdreMission(Integer idOrdre) {
        this.idOrdre = idOrdre;
    }

    public Integer getIdOrdre() {
        return idOrdre;
    }

    public void setIdOrdre(Integer idOrdre) {
        this.idOrdre = idOrdre;
    }

    public Float getMontant() {
        return montant;
    }

    public void setMontant(Float montant) {
        this.montant = montant;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getDateAller() {
        if (dateAller != null) {

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       
        String formatedDate = sdf.format(dateAller);

            return formatedDate;
        } else {
            return null;
        }
    }

    public void setDateAller(Date dateAller) {
        this.dateAller = dateAller;
    }

    public String getHeureAller() {

        if (heureAller!=null) {
            
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
       
        String formatedDate = sdf.format(heureAller);


        return formatedDate;
        }else {
            return null;
        }
        
    }

    public void setHeureAller(Date heureAller) {
        this.heureAller = heureAller;
    }

    public String getObjetMission() {
        return objetMission;
    }

    public void setObjetMission(String objetMission) {
        this.objetMission = objetMission;
    }

    public String getMoyenTransport() {
        return moyenTransport;
    }

    public void setMoyenTransport(String moyenTransport) {
        this.moyenTransport = moyenTransport;
    }

    public Float getKilometres() {
        return kilometres;
    }

    public void setKilometres(Float kilometres) {
        this.kilometres = kilometres;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Personnel getPerson() {
        return person;
    }

    public void setPerson(Personnel person) {
        this.person = person;
    }

    public Trimestre getTrim1() {
        return trim1;
    }

    public void setTrim1(Trimestre trim1) {
        this.trim1 = trim1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrdre != null ? idOrdre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdreMission)) {
            return false;
        }
        OrdreMission other = (OrdreMission) object;
        if ((this.idOrdre == null && other.idOrdre != null) || (this.idOrdre != null && !this.idOrdre.equals(other.idOrdre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.OrdreMission[ idOrdre=" + idOrdre + " ]";
    }

}
