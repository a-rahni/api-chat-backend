package fr.m2i.apichat.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
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
public class Canal  implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Le nom est obligatoire... ")
    @Column(name="name", nullable = false)
    private  String name;

    @Column(name="description")
    private String description;

    @Temporal(TemporalType.DATE) // on peut utiliser  @CreatedDate
    @Column(name="created_at", nullable = false, updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;

    @LastModifiedDate
    @Column(name="updated_at", updatable = true )
    private Date updatedAt;

    @ManyToMany(mappedBy="canaux")
    private List<User> users=new ArrayList<>();

    @OneToMany(mappedBy = "canal", fetch=FetchType.LAZY)
    private List<Message> messages=new ArrayList<>();


}
