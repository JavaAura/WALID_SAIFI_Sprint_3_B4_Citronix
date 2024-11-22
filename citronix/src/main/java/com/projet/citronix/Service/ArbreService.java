package com.projet.citronix.Service;


import com.projet.citronix.Dto.Request.ArbreRequestDTO;
import com.projet.citronix.Dto.Response.ArbreResponseDTO;
import com.projet.citronix.Exception.ArbreNonTrouveException;
import com.projet.citronix.Exception.ChampNonTrouveException;
import com.projet.citronix.Mapper.ArbreMapper;
import com.projet.citronix.Repository.ArbreRepository;
import com.projet.citronix.Repository.ChampRepository;
import com.projet.citronix.entity.Arbre;
import com.projet.citronix.entity.Champ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ArbreService {

    @Autowired
    private ArbreRepository arbreRepository;
    @Autowired
    private ArbreMapper arbreMapper;

    @Autowired
    private ChampRepository champRepository;

    public ArbreResponseDTO ajouterArbre(ArbreRequestDTO arbreRequestDTO) {

        Arbre arbre = arbreMapper.toEntity(arbreRequestDTO);
        arbre.updateAge();
        arbre.updateEtatProductivite();
        Arbre savedArbre = arbreRepository.save(arbre);
        return arbreMapper.toArbreRequestDTO(savedArbre);
    }


    public ArbreResponseDTO updateArbre(Long id, ArbreRequestDTO arbreRequestDTO) {

        Arbre existingArbre = arbreRepository.findById(id)
                .orElseThrow(() -> new ArbreNonTrouveException("Arbre avec l'ID " + id + " introuvable"));

        if (arbreRequestDTO.getChampId() != null) {
            Champ champ = champRepository.findById(arbreRequestDTO.getChampId())
                    .orElseThrow(() -> new ChampNonTrouveException("Champ avec l'ID " + arbreRequestDTO.getChampId() + " introuvable"));
            existingArbre.setChamp(champ);
        }

        if (arbreRequestDTO.getDatePlantation() != null) {
            existingArbre.setDatePlantation(arbreRequestDTO.getDatePlantation());
        }

        existingArbre.updateAge();
        existingArbre.updateEtatProductivite();
        Arbre updatedArbre = arbreRepository.save(existingArbre);
        return arbreMapper.toArbreRequestDTO(updatedArbre);
    }


    public boolean deleteArbre(Long id) {
        Optional<Arbre> existingArbreOpt = arbreRepository.findById(id);

        if (existingArbreOpt.isPresent()) {
            arbreRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }


}





