package com.projet.citronix.Service;

import com.projet.citronix.Dto.Request.ChampRequestDTO;
import com.projet.citronix.Dto.Response.ChampResponseDTO;
import com.projet.citronix.Exception.SuperficieExceededException;
import com.projet.citronix.Mapper.ChampMapper;
import com.projet.citronix.Repository.ChampRepository;
import com.projet.citronix.Repository.FermeRepository;
import com.projet.citronix.entity.Champ;

import com.projet.citronix.entity.Ferme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChampService {

    @Autowired
    private ChampRepository champRepository;

    @Autowired
    private FermeRepository fermeRepository;

    @Autowired
    private ChampMapper champMapper;

    public ChampResponseDTO ajouterChamp(ChampRequestDTO champRequestDTO) {
        Ferme ferme = fermeRepository.findById(champRequestDTO.getFermeId())
                .orElseThrow(() -> new IllegalArgumentException("Ferme introuvable avec l'ID : " + champRequestDTO.getFermeId()));

        double superficieTotaleFerme = ferme.getSuperficie();
        double superficieTotaleChamps = ferme.getChamps().stream()
                .mapToDouble(Champ::getSuperficie)
                .sum();

        if (champRequestDTO.getSuperficie() > (0.5 * superficieTotaleFerme)) {
            throw new SuperficieExceededException("La superficie du champ ne peut pas dépasser 50% de la superficie totale de la ferme.");
        }

        Champ champ = champMapper.toEntity(champRequestDTO);
        champ.setFerme(ferme);

        champ = champRepository.save(champ);

        return champMapper.toResponseDTO(champ);
    }

}
