package com.projet.citronix.entity;

import com.projet.citronix.entity.enums.Saison;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Recolte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Saison saison;

    @Column(nullable = false)
    private LocalDate dateRecolte;

    @Column
    private double quantiteTotale = 0.0;

    @OneToMany(mappedBy = "recolte", cascade = CascadeType.ALL)
    private List<DetailRecolte> detailsRecolte;

    @OneToMany(mappedBy = "recolte", cascade = CascadeType.ALL)
    private List<Vente> ventes;


}
