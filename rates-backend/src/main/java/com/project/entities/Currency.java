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
@Table(name = "currency")
@Setter
@Getter
@AllArgsConstructor()
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Currency implements Serializable {

    private static final long serialVersionUID = 1L;
   
    @Id
    @Basic(optional = false)
    @Column(name = "id")    
    @SequenceGenerator(name = "CUR_SEQ", allocationSize = 1, sequenceName = "CUR_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "CUR_SEQ"  )
    private Long id;
    @Size(max = 3)
    @Column(name = "currency")
    private String currency;

  
}
