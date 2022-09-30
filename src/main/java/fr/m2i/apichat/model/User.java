package fr.m2i.apichat.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "users")
public class User implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name="username", nullable = false, length=100)
        private String username;

        @Email(message = "saisir un format email valide svp", regexp = ".+@.+\\..+")
        @Column(name="email", length = 100)
        private String email;

        @Length(message = "Le mot de passe est entre 4..8", min = 4, max = 8)
        @Column(name="password", length = 50)
        private String password;

        @ManyToMany
        @JoinTable(name= "user_canal",
                   joinColumns = {@JoinColumn(name= "user_id", referencedColumnName = "id")},
                   inverseJoinColumns = {@JoinColumn(name= "canal_id", referencedColumnName = "id")}    )
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private Set<Canal> canaux = new LinkedHashSet<>();

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private Set<Message> messages = new LinkedHashSet<>();

        /*@ManyToMany(fetch= FetchType.EAGER)
        private Set<Role> roles=new LinkedHashSet<>();*/

        //private String profilePicture;

        //private Boolean isOnline;





}
