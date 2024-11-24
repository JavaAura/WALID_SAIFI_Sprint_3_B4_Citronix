package com.projet.citronix.Service;

import com.projet.citronix.Dto.Request.VenteRequestDTO;
import com.projet.citronix.Dto.Response.VenteReponseDTO;
import com.projet.citronix.Exception.RecolteNonTrouveeException;
import com.projet.citronix.Mapper.VenteMapper;
import com.projet.citronix.Repository.VenteRepository;
import com.projet.citronix.entity.Vente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class VenteService {

    @Autowired
    private VenteMapper venteMapper;

    @Autowired
    private VenteRepository venteRepository;

    public VenteReponseDTO ajouterVente(VenteRequestDTO venteRequestDTO) throws RecolteNonTrouveeException {

        Vente vente = venteMapper.toEntity(venteRequestDTO);

        venteRepository.save(vente);
        VenteReponseDTO venteReponseDTO =  venteMapper.toDTO(vente);
        venteReponseDTO.setRevenu(vente.getRevenu());
        return venteReponseDTO;
    }

}
