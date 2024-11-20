package com.projet.citronix.Dto;


import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class ArbreDTO {

    private Long id;
    private LocalDate datePlantation;
    private int age;
    private Double etatProductivite;
    private Long champId;
    private List<DetailRecolteDTO> detailsRecolte;
}
