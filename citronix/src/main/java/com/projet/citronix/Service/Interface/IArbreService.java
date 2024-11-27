package com.projet.citronix.Service.Interface;

import com.projet.citronix.Dto.Request.ArbreRequestDTO;
import com.projet.citronix.Dto.Response.ArbreResponseDTO;

public interface IArbreService {

    ArbreResponseDTO ajouterArbre(ArbreRequestDTO arbreRequestDTO);

    ArbreResponseDTO updateArbre(Long id, ArbreRequestDTO arbreRequestDTO);


    boolean deleteArbre(Long id);
}
