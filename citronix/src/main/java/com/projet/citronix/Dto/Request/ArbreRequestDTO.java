package com.projet.citronix.Dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ArbreRequestDTO {
    private Long id;

    private LocalDate datePlantation;

    private Long ChampId;


}
