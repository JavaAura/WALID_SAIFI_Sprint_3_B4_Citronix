package com.projet.citronix.Dto.Response;

import com.projet.citronix.entity.Arbre;
import com.projet.citronix.entity.Ferme;
import lombok.Data;

import java.util.List;

@Data
    public class ChampResponseDTO {

        private Long id;
        private double superficie;
        private String nomFerme;
        private List<ArbreResponseDTO> arbres;
    }

