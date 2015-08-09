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
import javax.persistence.Lob;
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
@Table(name = "Compte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compte.findAll", query = "SELECT c FROM Compte c"),
    @NamedQuery(name = "Compte.findByIdLogin", query = "SELECT c FROM Compte c WHERE c.idLogin = :idLogin"),
    @NamedQuery(name = "Compte.findByLogin", query = "SELECT c FROM Compte c WHERE c.login = :login")})
public class Compte implements Serializable {
    @Lob
    @Column(name = "mp")
    private byte[] mp;
    private static final long serialVersionUID = 1L;
    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Basic(optional = false)
    @NotNull
    @Column(name = "idLogin")
    private Integer idLogin;
    @Size(max = 50)
    @Column(name = "login")
    private String login;

    public Compte() {
    }

    public Compte(Integer idLogin) {
        this.idLogin = idLogin;
    }

    public Integer getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(Integer idLogin) {
        this.idLogin = idLogin;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLogin != null ? idLogin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compte)) {
            return false;
        }
        Compte other = (Compte) object;
        if ((this.idLogin == null && other.idLogin != null) || (this.idLogin != null && !this.idLogin.equals(other.idLogin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Compte[ idLogin=" + idLogin + " ]";
    }

    public byte[] getMp() {
        return mp;
    }

    public void setMp(byte[] mp) {
        this.mp = mp;
    }
    
}
