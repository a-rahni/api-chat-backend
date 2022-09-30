package fr.m2i.apichat.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="messages")
public class Message implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Message ne peut pas être vide")
    @Column(name="content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Temporal(TemporalType.DATE)
    @Column(name="created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;

   /* @Temporal(TemporalType.TIME)
    @Column(name="created_at_time", nullable = false, columnDefinition="TIME DEFAULT CURRENT_TIME")
    private Date CreatedAtTime;*/

    @LastModifiedDate
    @Column(name="updated_at", updatable = true)
    private Date updatedAt;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn (name="user_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn (name="canal_id", nullable = false)
    private Canal canal;


}
