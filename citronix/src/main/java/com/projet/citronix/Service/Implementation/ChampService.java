package com.projet.citronix.Service.Implementation;
import com.projet.citronix.Dto.Request.ChampRequestDTO;
import com.projet.citronix.Dto.Response.ChampResponseDTO;
import com.projet.citronix.Exception.FermeNomTrouverException;
import com.projet.citronix.Exception.NombreDeChampsMaximumDepasseException;
import com.projet.citronix.Exception.SuperficieExceededException;
import com.projet.citronix.Mapper.ChampMapper;
import com.projet.citronix.Repository.ChampRepository;
import com.projet.citronix.Repository.FermeRepository;
import com.projet.citronix.Service.Interface.IChampService;
import com.projet.citronix.entity.Champ;

import com.projet.citronix.entity.Ferme;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ChampService  implements IChampService {

    @Autowired
    private ChampRepository champRepository;

    @Autowired
    private FermeRepository fermeRepository;

    @Autowired
    private ChampMapper champMapper;

    @Override
    public ChampResponseDTO ajouterChamp(@Valid ChampRequestDTO champRequestDTO) {
        Ferme ferme = fermeRepository.findById(champRequestDTO.getFermeId())
                .orElseThrow(() -> new FermeNomTrouverException("Ferme introuvable avec l'ID : " + champRequestDTO.getFermeId()));

        double superficieTotaleFerme = ferme.getSuperficie();
        double superficieTotaleChamps = ferme.getChamps().stream()
                .mapToDouble(Champ::getSuperficie)
                .sum();

        if (champRequestDTO.getSuperficie() > (0.5 * superficieTotaleFerme)) {
            throw new SuperficieExceededException("La superficie du champ ne peut pas dépasser 50% de la superficie totale de la ferme.");
        }

        long nombreDeChamps = fermeRepository.countChampsByFermeId(champRequestDTO.getFermeId());

        if (nombreDeChamps >= 10) {
            throw new NombreDeChampsMaximumDepasseException("Une ferme ne peut contenir plus de 10 champs ! .");
        }

        Champ champ = champMapper.toEntity(champRequestDTO);
        champ.setFerme(ferme);

        champ = champRepository.save(champ);

        return champMapper.toResponseDTO(champ);
    }


    @Override
    public ChampResponseDTO updateChamp(Long id,  ChampRequestDTO champRequestDTO) {
        Champ existingChamp = champRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Champ introuvable avec l'ID : " + id));

        Ferme ferme = fermeRepository.findById(champRequestDTO.getFermeId())
                .orElseThrow(() -> new IllegalArgumentException("Ferme introuvable avec l'ID : " + champRequestDTO.getFermeId()));

        double superficieTotaleFerme = ferme.getSuperficie();
        double superficieTotaleChamps = ferme.getChamps().stream()
                .filter(champ -> !champ.getId().equals(id))
                .mapToDouble(Champ::getSuperficie)
                .sum();

        if (champRequestDTO.getSuperficie() > (0.5 * superficieTotaleFerme)) {
            throw new SuperficieExceededException("La superficie du champ ne peut pas dépasser 50% de la superficie totale de la ferme.");
        }


        existingChamp.setSuperficie(champRequestDTO.getSuperficie());
        existingChamp.setFerme(ferme);

        existingChamp = champRepository.save(existingChamp);

        return champMapper.toResponseDTO(existingChamp);
    }

    @Override
    public void deleteChamp(Long id) {
        Champ champ = champRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Champ introuvable avec l'ID : " + id));

        champRepository.delete(champ);
    }
    @Override

    public List<ChampResponseDTO> getAllChamps() {
        List<Champ> champs = champRepository.findAll();

        return champs.stream()
                .map(champ -> champMapper.toResponseDTO(champ))
                .collect(Collectors.toList());
    }

}



