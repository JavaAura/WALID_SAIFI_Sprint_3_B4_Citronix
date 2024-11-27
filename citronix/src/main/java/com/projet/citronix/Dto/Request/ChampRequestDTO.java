package com.projet.citronix.Dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class ChampRequestDTO {


    @NotNull(message = "La superficie est requise.")
    @Min(value = 1000, message = "La superficie de champ doit être supérieure à 1000 m².")
    private double superficie;

    private Long fermeId;
}