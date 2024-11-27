package com.projet.citronix.Service.Implementation;

import com.projet.citronix.Dto.Request.FermeRequestDTO;
import com.projet.citronix.Dto.Response.FermeResponseDTO;
import com.projet.citronix.Mapper.FermeMapper;
import com.projet.citronix.Repository.FermeRepository;
import com.projet.citronix.Service.Interface.IFermeService;
import com.projet.citronix.entity.Ferme;
import com.projet.citronix.entity.enums.Saison;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@AllArgsConstructor

@Service
public class FermeService   implements IFermeService {
    @Autowired
    private final FermeRepository fermeRepository;
    @Autowired
    private final FermeMapper fermeMapper;

 @Override
    public FermeResponseDTO addFerme(FermeRequestDTO fermeRequestDTO) {
        Ferme ferme = fermeMapper.toEntity(fermeRequestDTO);
        Ferme savedFerme = fermeRepository.save(ferme);
        return fermeMapper.toResponseDTO(savedFerme);
    }

    @Override
    public FermeResponseDTO updateFerme(Long id, FermeRequestDTO fermeRequestDTO) {
        Optional<Ferme> optionalFerme = fermeRepository.findById(id);
        if (optionalFerme.isPresent()) {
            Ferme ferme = optionalFerme.get();
            ferme.setNom(fermeRequestDTO.getNom());
            ferme.setLocalisation(fermeRequestDTO.getLocalisation());
            ferme.setSuperficie(fermeRequestDTO.getSuperficie());
            ferme.setDateCreation(fermeRequestDTO.getDateCreation());
            Ferme updatedFerme = fermeRepository.save(ferme);
            return fermeMapper.toResponseDTO(updatedFerme);
        }
        return null;
    }

    @Override

    public FermeResponseDTO getFermeById(Long id) {
        Optional<Ferme> ferme = fermeRepository.findById(id);
        return ferme.map(fermeMapper::toResponseDTO).orElse(null);
    }

    @Override
    public String deleteFerme(Long id) {
        Optional<Ferme> optionalFerme = fermeRepository.findById(id);
        if (optionalFerme.isPresent()) {
            fermeRepository.deleteById(id);
            return "Ferme supprimée avec succès";
        }
        return "Ferme non trouvée";
    }

    @Override
    public List<FermeResponseDTO> getAllFermes(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Ferme> fermesPage = fermeRepository.findAll(pageable);
        return fermesPage.stream()
                .map(fermeMapper::toResponseDTO)
                .collect(Collectors.toList());
    }




    List<Ferme> fermesAvecRecolteHiver() {
        List<Ferme> fermes = fermeRepository.findAll();
        return fermes.stream()
                .filter(ferme -> ferme.getChamps().stream()
                        .flatMap(champ -> champ.getArbres().stream())
                        .flatMap(arbre -> arbre.getDetailsRecolte().stream())
                        .anyMatch(detailRecolte -> detailRecolte.getRecolte().getSaison() == Saison.HIVER))
                        .collect(Collectors.toList());
    }

    List<Ferme> fermesAvecRecolteHdddiver() {
        List<Ferme> fermes = fermeRepository.findAll();
        return fermes.stream()
                .filter(ferme -> ferme.getChamps().stream()
                        .flatMap(champ -> champ.getArbres().stream())
                        .count() > 10)
                .collect(Collectors.toList());
    }


    List<Ferme> arbre10nombre(){
     List<Ferme> fermes = fermeRepository.findAll();
    return   fermes.stream()
             .filter(ferme -> ferme.getChamps().stream()
                     .flatMap(champ -> champ.getArbres().stream())
                     .count() == 10)
             .collect(Collectors.toList());
    }



}
