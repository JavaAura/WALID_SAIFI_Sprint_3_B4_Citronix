package com.projet.citronix.Dto.Response;

import com.projet.citronix.entity.Recolte;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VenteReponseDTO {

    private Long id;

    private LocalDate date;

    private double prixUnitaire;

    private double quantite;

    private String client;

    private  Double Revenu;



}
