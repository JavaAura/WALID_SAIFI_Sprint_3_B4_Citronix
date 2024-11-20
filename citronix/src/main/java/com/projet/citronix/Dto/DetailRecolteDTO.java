package com.projet.citronix.Dto;

import lombok.Data;

@Data
public class DetailRecolteDTO {

    private Long id;
    private double quantite;
    private Long arbreId;
    private Long recolteId;
}
