package com.Citronix.app.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
public class Vente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private double prixUnitaire;

    @Column(nullable = false)
    private double quantite;

    @Column(nullable = false)
    private String client;

    @ManyToOne
    @JoinColumn(name = "recolte_id", nullable = false)
    private  Recolte recolte;

    // Méthode calculant le revenu total de la vente
    public double getRevenu() {
        return this.quantite * this.prixUnitaire;
    }
}
