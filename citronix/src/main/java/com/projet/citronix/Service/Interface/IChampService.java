package com.projet.citronix.Service.Interface;
import com.projet.citronix.Dto.Request.ChampRequestDTO;
import com.projet.citronix.Dto.Response.ChampResponseDTO;

import java.util.List;

public interface IChampService {

    ChampResponseDTO ajouterChamp(ChampRequestDTO champRequestDTO);

    ChampResponseDTO updateChamp(Long id, ChampRequestDTO champRequestDTO);

    void deleteChamp(Long id);


    List<ChampResponseDTO> getAllChamps();
}
