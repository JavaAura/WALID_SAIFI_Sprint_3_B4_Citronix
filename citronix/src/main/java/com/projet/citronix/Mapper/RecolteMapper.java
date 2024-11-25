package com.projet.citronix.Mapper;

import com.projet.citronix.Dto.Request.RecolteRequestDTO;
import com.projet.citronix.Dto.Response.RecolteResponseDTO;
import com.projet.citronix.entity.Recolte;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RecolteMapper {

    Recolte toEntity(RecolteRequestDTO requestDTO);

    @Mapping(target = "detailsRecolte", source = "detailsRecolte")

    RecolteResponseDTO toResponseDTO(Recolte entity);
}
