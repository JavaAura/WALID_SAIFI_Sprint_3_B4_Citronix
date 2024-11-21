package com.projet.citronix.Dto.Request;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@Data
public class FermeRequestDTO {
    private Long id;
    @NotNull(message = "Le nom de la ferme est requis.")
    @Size(min = 3, max = 100, message = "Le nom de la ferme doit avoir entre 3 et 100 caractères.")
    private String nom;

    @NotNull(message = "La localisation de la ferme est requise.")
    @Size(min = 3, max = 200, message = "La localisation de la ferme doit avoir entre 3 et 200 caractères.")
    private String localisation;

    @NotNull(message = "La superficie est requise.")
    @Min(value = 1000, message = "La superficie doit être supérieure à 1000.")
    private double superficie;

    @NotNull(message = "La date de création est requise.")
    private LocalDate dateCreation;





}
