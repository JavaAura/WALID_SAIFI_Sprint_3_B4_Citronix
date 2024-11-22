package com.projet.citronix.Dto.Request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ArbreRequestDTO {
    private LocalDate datePlantation;
    private Long champId;
}
