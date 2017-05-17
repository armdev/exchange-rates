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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author armdev
 */
@Entity
@Table(name = "historical")
@Setter
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Historical implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @SequenceGenerator(name = "HIS_SEQ", allocationSize = 1, sequenceName = "HIS_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "HIS_SEQ")
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
    @Column(unique = true, name = "historical_date")
    private String historicalDate;

    public Historical(Double usdeur, Double usdamd, Double usdnzd, Double usdjpy, Double usdhuf, Double usdcad, Double usdaud, String historicalDate) {
        this.usdeur = usdeur;
        this.usdamd = usdamd;
        this.usdnzd = usdnzd;
        this.usdjpy = usdjpy;
        this.usdhuf = usdhuf;
        this.usdcad = usdcad;
        this.usdaud = usdaud;
        this.historicalDate = historicalDate;
    }

}
