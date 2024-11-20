package com.projet.citronix.Dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VenteDTO {

    private Long id;
    private LocalDate date;
    private double prixUnitaire;
    private double quantite;
    private String client;
    private Long recolteId; // ID de la récolte pour éviter de charger l'entité entière

    // Inclure le revenu calculé si nécessaire
    private double revenu;
}
