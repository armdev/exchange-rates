package com.project.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author armdev
 */
@Entity
@Table(name = "historical")
public class Historical implements Serializable {

    private static final long serialVersionUID = 1L;
   
    @Id
    @Basic(optional = false)
    @Column(name = "id")    
    @SequenceGenerator(name = "HIS_SEQ", allocationSize = 1, sequenceName = "HIS_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "HIS_SEQ"  )
    private Long id;    
    @Column(name = "usdeur")
    private Double usdeur;
    @Column(name = "usdamd")
    private Double usdamd;
    @Column(name = "usdnzd")
    private Double usdnzd;
    @Column(name = "usdjpy")
    private Double usdjpy;
    @Column(name = "usdhuf")
    private Double usdhuf;
    @Column(name = "usdcad")
    private Double usdcad;
    @Column(name = "usdaud")
    private Double usdaud;
    @Size(max = 20)
    @Column(name = "historical_date")
    private String historicalDate;

    public Historical() {
    }

    public Historical(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getUsdeur() {
        return usdeur;
    }

    public void setUsdeur(Double usdeur) {
        this.usdeur = usdeur;
    }

    public Double getUsdamd() {
        return usdamd;
    }

    public void setUsdamd(Double usdamd) {
        this.usdamd = usdamd;
    }

    public Double getUsdnzd() {
        return usdnzd;
    }

    public void setUsdnzd(Double usdnzd) {
        this.usdnzd = usdnzd;
    }

    public Double getUsdjpy() {
        return usdjpy;
    }

    public void setUsdjpy(Double usdjpy) {
        this.usdjpy = usdjpy;
    }

    public Double getUsdhuf() {
        return usdhuf;
    }

    public void setUsdhuf(Double usdhuf) {
        this.usdhuf = usdhuf;
    }

    public Double getUsdcad() {
        return usdcad;
    }

    public void setUsdcad(Double usdcad) {
        this.usdcad = usdcad;
    }

    public Double getUsdaud() {
        return usdaud;
    }

    public void setUsdaud(Double usdaud) {
        this.usdaud = usdaud;
    }

    public String getHistoricalDate() {
        return historicalDate;
    }

    public void setHistoricalDate(String historicalDate) {
        this.historicalDate = historicalDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Historical)) {
            return false;
        }
        Historical other = (Historical) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.project.entities.Historical[ id=" + id + " ]";
    }
    
}
