package com.projet.citronix.Service.Implementation;

import com.projet.citronix.Dto.Request.RecolteRequestDTO;
import com.projet.citronix.Dto.Response.RecolteResponseDTO;
import com.projet.citronix.Exception.MiseAJourRecolteException;
import com.projet.citronix.Exception.RecolteNonTrouveeException;
import com.projet.citronix.Exception.SuppressionRecolteException;
import com.projet.citronix.Mapper.RecolteMapper;
import com.projet.citronix.Repository.RecolteRepository;
import com.projet.citronix.Service.Interface.IRecolteService;
import com.projet.citronix.entity.Recolte;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class RecolteService implements IRecolteService {

    @Autowired
    private final RecolteRepository recolteRepository;
    @Autowired
    private final RecolteMapper recolteMapper;


    public RecolteResponseDTO createRecolte(RecolteRequestDTO requestDTO) {
        Recolte recolte = recolteMapper.toEntity(requestDTO);
        recolte = recolteRepository.save(recolte);
        return recolteMapper.toResponseDTO(recolte);
    }

    public List<RecolteResponseDTO> getAllRecoltes() {
        return recolteRepository.findAll()
                .stream()
                .map(recolteMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public RecolteResponseDTO getRecolteById(Long id) throws RecolteNonTrouveeException {
        Recolte recolte = recolteRepository.findById(id)
                .orElseThrow(() -> new RecolteNonTrouveeException(id));
        return recolteMapper.toResponseDTO(recolte);
    }

    public RecolteResponseDTO updateRecolte(Long id, RecolteRequestDTO requestDTO) throws MiseAJourRecolteException {
        Recolte existingRecolte = recolteRepository.findById(id)
                .orElseThrow(() -> new  MiseAJourRecolteException(id));
        existingRecolte.setSaison(requestDTO.getSaison());
        existingRecolte.setDateRecolte(requestDTO.getDateRecolte());
        existingRecolte.setQuantiteTotale(requestDTO.getQuantiteTotale());
        Recolte updatedRecolte = recolteRepository.save(existingRecolte);
        return recolteMapper.toResponseDTO(updatedRecolte);
    }

    public void deleteRecolte(Long id) throws SuppressionRecolteException {
        if (!recolteRepository.existsById(id)) {
            throw new SuppressionRecolteException(id);
        }
        recolteRepository.deleteById(id);
    }
}
