package com.projet.citronix.Mapper;

import com.projet.citronix.Dto.Request.VenteRequestDTO;
import com.projet.citronix.Dto.Response.VenteReponseDTO;
import com.projet.citronix.entity.Vente;
import com.projet.citronix.entity.Recolte;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VenteMapper {


     @Mapping(source = "id_recolte", target = "recolte.id")
     Vente toEntity(VenteRequestDTO venteRequestDTO);
     @Mapping(target = "recolte", source = "recolte")
     VenteReponseDTO toDTO(Vente vente);
}
