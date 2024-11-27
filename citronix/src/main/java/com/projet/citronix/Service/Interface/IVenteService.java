package com.projet.citronix.Service.Interface;

import com.projet.citronix.Dto.Request.VenteRequestDTO;
import com.projet.citronix.Dto.Response.VenteReponseDTO;
import com.projet.citronix.Exception.RecolteNonTrouveeException;
import com.projet.citronix.Exception.VenteNotExitException;

import java.util.List;

public interface IVenteService {

    VenteReponseDTO ajouterVente(VenteRequestDTO venteRequestDTO) throws RecolteNonTrouveeException;

    VenteReponseDTO modifierVente(Long id, VenteRequestDTO venteRequestDTO) throws VenteNotExitException;

    void supprimerVente(Long id);

    List<VenteReponseDTO> getVentes();
}
