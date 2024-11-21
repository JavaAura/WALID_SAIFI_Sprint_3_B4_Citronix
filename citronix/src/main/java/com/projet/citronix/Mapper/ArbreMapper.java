package com.projet.citronix.Mapper;

import com.projet.citronix.Dto.Request.ArbreRequestDTO;
import com.projet.citronix.Dto.Response.ArbreResponseDTO;
import com.projet.citronix.entity.Arbre;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArbreMapper {


    ArbreResponseDTO  toArbreRequestDTO(Arbre arbre);

    Arbre toEntity(ArbreRequestDTO arbreRequestDTO);

}
