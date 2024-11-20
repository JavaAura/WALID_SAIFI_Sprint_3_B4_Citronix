package com.projet.citronix.Dto;

import lombok.Data;
import java.util.List;

@Data
public class ChampDTO {

    private Long id;
    private double superficie;
    private Long fermeId;
    private List<ArbreDTO> arbres;
}
