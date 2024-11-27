package com.projet.citronix.Service.Interface;

import com.projet.citronix.Dto.Request.RecolteRequestDTO;
import com.projet.citronix.Dto.Response.RecolteResponseDTO;
import com.projet.citronix.Exception.MiseAJourRecolteException;
import com.projet.citronix.Exception.RecolteNonTrouveeException;
import com.projet.citronix.Exception.SuppressionRecolteException;

import java.util.List;

public interface IRecolteService {

    RecolteResponseDTO createRecolte(RecolteRequestDTO requestDTO);

    List<RecolteResponseDTO> getAllRecoltes();

    RecolteResponseDTO getRecolteById(Long id) throws RecolteNonTrouveeException;

    RecolteResponseDTO updateRecolte(Long id, RecolteRequestDTO requestDTO) throws MiseAJourRecolteException;

    void deleteRecolte(Long id) throws SuppressionRecolteException;
}
