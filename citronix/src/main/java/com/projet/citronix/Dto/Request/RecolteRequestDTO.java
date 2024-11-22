package com.projet.citronix.Dto.Request;
import com.projet.citronix.entity.enums.Saison;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RecolteRequestDTO {

        private Long id;
        private Saison saison;
        private LocalDate dateRecolte;
        private double quantiteTotale;

}
