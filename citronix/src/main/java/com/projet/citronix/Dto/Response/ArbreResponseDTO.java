package com.projet.citronix.Dto.Response;

import com.projet.citronix.entity.DetailRecolte;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArbreResponseDTO{
    private Long id;
    private LocalDate datePlantation;
    private int age;
    private Double etatProductivite;

}
