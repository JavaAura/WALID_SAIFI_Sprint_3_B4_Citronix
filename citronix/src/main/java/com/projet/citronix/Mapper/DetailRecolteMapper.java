package com.projet.citronix.Mapper;

import com.projet.citronix.Dto.Request.DetailRecolteRequestDTO;
import com.projet.citronix.Dto.Response.DetailRecolteReponseDTO;
import com.projet.citronix.entity.DetailRecolte;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DetailRecolteMapper {

    DetailRecolte toEntity(DetailRecolteRequestDTO requestDTO);

    DetailRecolteReponseDTO toResponseDTO(DetailRecolte detailRecolte);
}
