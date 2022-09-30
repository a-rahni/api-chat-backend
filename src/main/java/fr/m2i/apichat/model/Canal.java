package fr.m2i.apichat.model;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "canaux")
public class Canal implements Serializable {

    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @ToString.Include
    @NotEmpty(message = "Le nom est obligatoire... ")
    @Column(name = "name", nullable = false, length = 50)
    private  String name;

    @ToString.Include
    @Column(name = "description", length = 50)
    private String description;

    @ToString.Include
    @Temporal(TemporalType.DATE) // on peut utiliser  @CreatedDate
    @Column(name="created_at", nullable = false, updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;

    @Temporal(TemporalType.TIME)
    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToMany(mappedBy="canaux")
    private Set<User> users = new java.util.LinkedHashSet<>();

    @OneToMany(mappedBy = "canal")
    private Set<Message> messages = new java.util.LinkedHashSet<>();


}
