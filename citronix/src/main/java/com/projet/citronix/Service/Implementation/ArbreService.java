package com.projet.citronix.Service.Implementation;


import com.projet.citronix.Dto.Request.ArbreRequestDTO;
import com.projet.citronix.Dto.Response.ArbreResponseDTO;
import com.projet.citronix.Exception.ArbreNonTrouveException;
import com.projet.citronix.Exception.ChampNonTrouveException;
import com.projet.citronix.Exception.DensiteArbresDepasseeException;
import com.projet.citronix.Exception.PeriodePlantationInvalideException;
import com.projet.citronix.Mapper.ArbreMapper;
import com.projet.citronix.Repository.ArbreRepository;
import com.projet.citronix.Repository.ChampRepository;
import com.projet.citronix.Service.Interface.IArbreService;
import com.projet.citronix.entity.Arbre;
import com.projet.citronix.entity.Champ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

@Service
public class ArbreService implements IArbreService {

    @Autowired
    private ArbreRepository arbreRepository;
    @Autowired
    private ArbreMapper arbreMapper;

    @Autowired
    private ChampRepository champRepository;


    private boolean isPeriodePlantationValide(LocalDate datePlantation) {
        if (datePlantation == null) {
            return false;
        }
        Month mois = datePlantation.getMonth();
        return mois.equals(Month.MARCH) || mois.equals(Month.APRIL) || mois.equals(Month.MAY);
    }


    @Override
    public ArbreResponseDTO ajouterArbre(ArbreRequestDTO arbreRequestDTO) {
        LocalDate datePlantation = arbreRequestDTO.getDatePlantation();

        if (!isPeriodePlantationValide(datePlantation)) {
            throw new PeriodePlantationInvalideException("La période de plantation est invalide. Les arbres ne peuvent être plantés qu'entre mars et mai.");
        }


        Champ champ = champRepository.findById(arbreRequestDTO.getChampId())
                .orElseThrow(() -> new ChampNonTrouveException("Champ introuvable avec l'ID : " + arbreRequestDTO.getChampId()));

        long nombreArbresExistants = champRepository.NombreArbresParChampId(champ.getId());

        double superficieChamp = champ.getSuperficie();
        double densiteActuelle = (double) (nombreArbresExistants + 1) / (superficieChamp / 1000.0);

        if (densiteActuelle > 10) {
            throw new DensiteArbresDepasseeException(
                    "La densité maximale autorisée de 10 arbres par 1000 m² a été dépassée. Champ ID : " + champ.getId()
            );
        }




        Arbre arbre = arbreMapper.toEntity(arbreRequestDTO);
        arbre.updateAge();
        arbre.updateEtatProductivite();
        Arbre savedArbre = arbreRepository.save(arbre);
        return arbreMapper.toArbreRequestDTO(savedArbre);
    }


    @Override
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


    @Override
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





