package com.projet.citronix.Service;


import com.projet.citronix.Dto.Request.ArbreRequestDTO;
import com.projet.citronix.Dto.Response.ArbreResponseDTO;
import com.projet.citronix.Mapper.ArbreMapper;
import com.projet.citronix.Repository.ArbreRepository;
import com.projet.citronix.entity.Arbre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArbreService {

    @Autowired
    private ArbreRepository arbreRepository;
    @Autowired
    private ArbreMapper arbreMapper;

    public ArbreResponseDTO ajouterArbre(ArbreRequestDTO arbreRequestDTO) {

        Arbre arbre = arbreMapper.toEntity(arbreRequestDTO);
        arbre.getAges();
        Arbre savedArbre = arbreRepository.save(arbre);

        return arbreMapper.toArbreRequestDTO(savedArbre);
    }


    public ArbreResponseDTO updateArbre(Long id, ArbreRequestDTO arbreRequestDTO) {
        Optional<Arbre> existingArbreOpt = arbreRepository.findById(id);
            Arbre existingArbre = existingArbreOpt.get();
            Arbre arbreToUpdate = arbreMapper.toEntity(arbreRequestDTO);
            existingArbre.setDatePlantation(arbreToUpdate.getDatePlantation());
            existingArbre.setEtatProductivite(arbreToUpdate.getEtatProductivite());
            existingArbre.setChamp(arbreToUpdate.getChamp());
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





