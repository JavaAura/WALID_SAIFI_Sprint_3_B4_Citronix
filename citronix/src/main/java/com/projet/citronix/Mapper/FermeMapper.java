package com.projet.citronix.Mapper;

import com.projet.citronix.Dto.FermeDTO;
import com.projet.citronix.entity.Ferme;
import org.mapstruct.Mapper;



@Mapper(componentModel = "spring")
public interface FermeMapper {

    FermeDTO toDTO(Ferme ferme);

    Ferme toEntity(FermeDTO fermeDTO);
}
