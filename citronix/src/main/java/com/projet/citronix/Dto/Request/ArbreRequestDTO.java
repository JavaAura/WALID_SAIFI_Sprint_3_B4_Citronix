package com.projet.citronix.Dto.Request;

import com.projet.citronix.Dto.DetailRecolteDTO;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
public class ArbreRequestDTO {
    private LocalDate datePlantation;
    private int age;
    private Double etatProductivite;
    private Long champId;
}
