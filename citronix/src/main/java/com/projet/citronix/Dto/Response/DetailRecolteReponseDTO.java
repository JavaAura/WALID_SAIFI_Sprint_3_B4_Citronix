package com.projet.citronix.Dto.Response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailRecolteReponseDTO {

    private Long id;

    private double quantite;

    private Long idArbre;

    private Long idRecolte;


}
