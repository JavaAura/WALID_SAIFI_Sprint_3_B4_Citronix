package com.projet.citronix.Service.Interface;

import com.projet.citronix.Dto.Request.FermeRequestDTO;
import com.projet.citronix.Dto.Response.FermeResponseDTO;
import com.projet.citronix.entity.Ferme;

import java.util.List;

public interface IFermeService {

    FermeResponseDTO addFerme(FermeRequestDTO fermeRequestDTO);

    FermeResponseDTO updateFerme(Long id, FermeRequestDTO fermeRequestDTO);

    FermeResponseDTO getFermeById(Long id);

    String deleteFerme(Long id);

    List<FermeResponseDTO> getAllFermes(int page, int size);


}
