package com.projet.citronix.Dto;

import com.projet.citronix.entity.enums.Saison;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class RecolteDTO {

    private Long id;
    private Saison saison;
    private LocalDate dateRecolte;
    private double quantiteTotale;
    private List<DetailRecolteDTO> detailsRecolte;
    private List<VenteDTO> ventes;
}
