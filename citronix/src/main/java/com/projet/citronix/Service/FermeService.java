package com.projet.citronix.Service;

import com.projet.citronix.Dto.Request.FermeRequestDTO;
import com.projet.citronix.Dto.Response.FermeResponseDTO;
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

    public FermeResponseDTO addFerme(FermeRequestDTO fermeRequestDTO) {
        Ferme ferme = fermeMapper.toEntity(fermeRequestDTO);
        Ferme savedFerme = fermeRepository.save(ferme);
        return fermeMapper.toResponseDTO(savedFerme);
    }

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

    public FermeResponseDTO getFermeById(Long id) {
        Optional<Ferme> ferme = fermeRepository.findById(id);
        return ferme.map(fermeMapper::toResponseDTO).orElse(null);
    }

    public String deleteFerme(Long id) {
        Optional<Ferme> optionalFerme = fermeRepository.findById(id);
        if (optionalFerme.isPresent()) {
            fermeRepository.deleteById(id);
            return "Ferme supprimée avec succès";
        }
        return "Ferme non trouvée";
    }

    public List<FermeResponseDTO> getAllFermes(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Ferme> fermesPage = fermeRepository.findAll(pageable);
        return fermesPage.stream()
                .map(fermeMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public long getNombreChampsParFerme(Long fermeId) {
        return fermeRepository.countChampsByFermeId(fermeId);
    }
}
