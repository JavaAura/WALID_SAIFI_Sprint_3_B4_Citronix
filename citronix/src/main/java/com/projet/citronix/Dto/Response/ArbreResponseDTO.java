package com.projet.citronix.Dto.Response;

import com.projet.citronix.entity.DetailRecolte;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ArbreResponseDTO{
    private Long id;
    private LocalDate datePlantation;
    private int age;
    private Double etatProductivite;
    private List<DetailRecolte> detailsRecolte;


}
