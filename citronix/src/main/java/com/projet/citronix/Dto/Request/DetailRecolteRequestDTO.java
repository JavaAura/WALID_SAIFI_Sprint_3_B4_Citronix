package com.projet.citronix.Dto.Request;
import com.projet.citronix.entity.Recolte;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailRecolteRequestDTO {

    private Long id;

    private double quantite;

    private Long  idArbre;

    private Long idRecolte;

}
