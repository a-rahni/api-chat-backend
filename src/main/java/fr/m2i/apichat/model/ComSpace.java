package fr.m2i.apichat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name="com_sapce")
public class ComSpace {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false, length=100)
    private String name;

    @Column(name="description")
    private String description;

}
