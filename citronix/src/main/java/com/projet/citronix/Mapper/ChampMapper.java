package com.projet.citronix.Mapper;

import com.projet.citronix.Dto.Request.ChampRequestDTO;
import com.projet.citronix.Dto.Response.ChampResponseDTO;
import com.projet.citronix.entity.Champ;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChampMapper {

    @Mapping(target = "nomFerme", source = "ferme.nom")
    ChampResponseDTO toResponseDTO(Champ champ);

    Champ toEntity(ChampRequestDTO champRequestDTO);
}

