package com.projet.citronix.Dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VenteRequestDTO {

    private Long id;

    private LocalDate date;

    private double prixUnitaire;

    private double quantite;

    private String client;

    private Long id_recolte;

}
