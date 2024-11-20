package com.projet.citronix.Service;

import com.projet.citronix.Dto.FermeDTO;
import com.projet.citronix.Mapper.FermeMapper;
import com.projet.citronix.Repository.FermeRepository;
import com.projet.citronix.entity.Ferme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FermeService {

    private final FermeRepository fermeRepository;
    private final FermeMapper fermeMapper;

    @Autowired
    public FermeService(FermeRepository fermeRepository, FermeMapper fermeMapper) {
        this.fermeRepository = fermeRepository;
        this.fermeMapper = fermeMapper;
    }

    public FermeDTO addFerme(FermeDTO fermeDTO) {
        Ferme ferme = fermeMapper.toEntity(fermeDTO);
        Ferme savedFerme = fermeRepository.save(ferme);
        return fermeMapper.toDTO(savedFerme);
    }

    public FermeDTO updateFerme(Long id, FermeDTO fermeDTO) {
        Optional<Ferme> optionalFerme = fermeRepository.findById(id);
        if (optionalFerme.isPresent()) {
            Ferme ferme = optionalFerme.get();
            ferme.setNom(fermeDTO.getNom());
            ferme.setLocalisation(fermeDTO.getLocalisation());
            ferme.setSuperficie(fermeDTO.getSuperficie());
            ferme.setDateCreation(fermeDTO.getDateCreation());
            Ferme updatedFerme = fermeRepository.save(ferme);
            return fermeMapper.toDTO(updatedFerme);
        } else {
            return null;
        }
    }

    public FermeDTO getFermeById(Long id) {
        Optional<Ferme> ferme = fermeRepository.findById(id);
        return ferme.map(fermeMapper::toDTO).orElse(null);
    }


    public String deleteFerme(Long id) {
        Optional<Ferme> optionalFerme = fermeRepository.findById(id);
        if (optionalFerme.isPresent()) {
            fermeRepository.deleteById(id);
            return "Ferme supprimée avec succès";
        }
        return "Ferme non trouvée";
    }

    public List<FermeDTO> getAllFermes(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Ferme> fermesPage = fermeRepository.findAll(pageable);
        return fermesPage.stream()
                .map(fermeMapper::toDTO)
                .collect(Collectors.toList());
    }



}
