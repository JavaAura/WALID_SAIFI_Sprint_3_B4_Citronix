package com.projet.citronix.Mapper;

import com.projet.citronix.Dto.Request.FermeRequestDTO;
import com.projet.citronix.Dto.Response.FermeResponseDTO;
import com.projet.citronix.entity.Ferme;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FermeMapper {

    FermeResponseDTO toResponseDTO(Ferme ferme);

    Ferme toEntity(FermeRequestDTO fermeRequestDTO);
}
