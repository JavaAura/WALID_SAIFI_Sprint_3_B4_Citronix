package com.projet.citronix.Service;


import com.projet.citronix.Dto.Request.ArbreRequestDTO;
import com.projet.citronix.Dto.Response.ArbreResponseDTO;
import com.projet.citronix.Mapper.ArbreMapper;
import com.projet.citronix.Repository.ArbreRepository;
import com.projet.citronix.entity.Arbre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
