package com.projet.citronix.Dto.Request;
import com.projet.citronix.entity.enums.Saison;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecolteRequestDTO {

        private Long id;
        private Saison saison;
        private LocalDate dateRecolte;
        private double quantiteTotale;

}
