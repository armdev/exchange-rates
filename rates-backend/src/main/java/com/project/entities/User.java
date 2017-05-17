package com.project.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user")
@Setter
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "id")    
    @SequenceGenerator(name = "USER_SEQ", allocationSize = 1, sequenceName = "USER_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "USER_SEQ"  )
    private Long id;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;    
    @Column(unique=true, name = "email")
    private String email;
    @Column(name = "passwd")
    private String passwd;
    @Column(name = "register_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registerDate;
    @Column(name = "birth_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthDate;
    @Column(name = "last_login_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastlogindate;
    @Column(name = "country")
    private String country;
    @Column(name = "city")
    private String city;
    @Column(name = "street")
    private String street;
    @Column(name = "zipcode")
    private String zipcode;

    public User(String firstname, String lastname, String email, String passwd, Date registerDate, Date birthDate, Date lastlogindate, String country, String city, String street, String zipcode) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.passwd = passwd;
        this.registerDate = registerDate;
        this.birthDate = birthDate;
        this.lastlogindate = lastlogindate;
        this.country = country;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }


}
