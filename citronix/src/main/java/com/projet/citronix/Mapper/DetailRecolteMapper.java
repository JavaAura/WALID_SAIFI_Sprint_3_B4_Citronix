package com.projet.citronix.Mapper;

import com.projet.citronix.Dto.Request.DetailRecolteRequestDTO;
import com.projet.citronix.Dto.Response.DetailRecolteReponseDTO;
import com.projet.citronix.entity.DetailRecolte;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface DetailRecolteMapper {


    @Mapping(target = "arbre.id", source = "idArbre")
    @Mapping(target = "recolte.id", source = "idRecolte")
    DetailRecolte toEntity(DetailRecolteRequestDTO requestDTO);

    @Mapping(target = "recolte", source = "recolte")
    @Mapping(target = "recolte.detailsRecolte", ignore = true)
    DetailRecolteReponseDTO toResponseDTO(DetailRecolte detailRecolte);


}
