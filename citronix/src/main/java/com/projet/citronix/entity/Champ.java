package com.projet.citronix.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@NoArgsConstructor
@Entity
public class Champ {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double superficie;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ferme_id")
    private Ferme ferme;

    @OneToMany(mappedBy = "champ", cascade = CascadeType.ALL)
    private List<Arbre> arbres;

}



