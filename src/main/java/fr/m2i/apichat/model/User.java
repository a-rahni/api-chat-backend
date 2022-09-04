package fr.m2i.apichat.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "users")
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name="username", nullable = false, length=100)
        private String username;

        @Column(name="email", length = 100)
        private String email;

        @Column(name="password", length = 50)
        private String password;

        @ManyToMany
        @JoinTable(name="user_channel",
                   joinColumns = {@JoinColumn(name="user_id")},
                   inverseJoinColumns = {@JoinColumn(name="canal_id")}    )
        private List<Canal> canaux=new ArrayList<>();

        @OneToMany(mappedBy = "user", fetch=FetchType.LAZY)
        private List<Message> messages=new ArrayList<>();

}
