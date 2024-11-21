package com.projet.citronix.Mapper;

import com.projet.citronix.Dto.Request.ArbreRequestDTO;
import com.projet.citronix.Dto.Response.ArbreResponseDTO;
import com.projet.citronix.entity.Arbre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArbreMapper {

    @Mapping(target = "age", expression = "java(java.time.Period.between(arbre.getDatePlantation(), java.time.LocalDate.now()).getYears())")
    ArbreResponseDTO  toArbreRequestDTO(Arbre arbre);

    @Mapping(target = "champ.id", source = "champId")
    Arbre toEntity(ArbreRequestDTO arbreRequestDTO);

}
