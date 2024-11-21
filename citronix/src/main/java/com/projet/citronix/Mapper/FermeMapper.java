package com.projet.citronix.Mapper;

import com.projet.citronix.Dto.Request.FermeRequestDTO;
import com.projet.citronix.Dto.Response.FermeResponseDTO;
import com.projet.citronix.entity.Ferme;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FermeMapper {
    @Mapping(target = "champs", source = "champs")
    FermeResponseDTO toResponseDTO(Ferme ferme);

    Ferme toEntity(FermeRequestDTO fermeRequestDTO);
}
