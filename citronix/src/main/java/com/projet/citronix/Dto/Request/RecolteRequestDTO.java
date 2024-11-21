package com.projet.citronix.Dto.Request;

import com.projet.citronix.entity.DetailRecolte;
import com.projet.citronix.entity.Vente;
import com.projet.citronix.entity.enums.Saison;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

public class RecolteRequestDTO {


        private Long id;
        private Saison saison;
        private LocalDate dateRecolte;
        private double quantiteTotale;

}
