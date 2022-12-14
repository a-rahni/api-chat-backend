package fr.m2i.apichat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="canaux")
public class Canal {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", unique = true, nullable = false)
    private  String name;

    @Column(name="description")
    private String description;

    @Temporal(TemporalType.TIMESTAMP) // on peut utiliser  @CreatedDate
    @Column(name="created_at", nullable = false, updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;

    //@LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_at", updatable = true, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date updatedAt;
    //private LocalDateTime updatedAt;


    @ManyToMany(mappedBy="canaux")
    private List<User> users=new ArrayList<>();

    @OneToMany(mappedBy = "canal", fetch=FetchType.LAZY,
                cascade = {CascadeType.REMOVE},
                orphanRemoval = true)
    private List<Message> messages=new ArrayList<>();

}
