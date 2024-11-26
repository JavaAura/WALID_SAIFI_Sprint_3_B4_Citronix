package com.projet.citronix.Dto.Response;


import com.projet.citronix.entity.Recolte;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailRecolteReponseDTO {

    private Long id;

    private double quantite;


}
