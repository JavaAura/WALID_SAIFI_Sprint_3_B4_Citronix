package com.projet.citronix.Dto.Response;

import com.projet.citronix.Dto.Request.FermeRequestDTO;
import com.projet.citronix.Dto.Response.ChampResponseDTO;
import com.projet.citronix.entity.Champ;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class FermeResponseDTO {
    private Long id;
    private String nom;
    private String localisation;
    private double superficie;
    private LocalDate dateCreation;
    private List<ChampResponseDTO> champs;
}
