package com.projet.citronix.Service;
import com.projet.citronix.Dto.Request.DetailRecolteRequestDTO;
import com.projet.citronix.Dto.Response.DetailRecolteReponseDTO;
import com.projet.citronix.Exception.ArbreNonTrouveException;
import com.projet.citronix.Exception.DetailRecolteNonTrouverException;
import com.projet.citronix.Exception.RecolteNonTrouveeException;
import com.projet.citronix.Mapper.DetailRecolteMapper;
import com.projet.citronix.Repository.ArbreRepository;
import com.projet.citronix.Repository.DetailRecolteRepository;
import com.projet.citronix.Repository.RecolteRepository;
import com.projet.citronix.entity.Arbre;
import com.projet.citronix.entity.DetailRecolte;
import com.projet.citronix.entity.Recolte;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DetailRecolteService {
    @Autowired
    private  final DetailRecolteRepository detailRecolteRepository;
    @Autowired
    private  final DetailRecolteMapper detailRecolteMapper;
    @Autowired
    private  final ArbreRepository arbreRepository;
    @Autowired
    private  final RecolteRepository recolteRepository;

    public DetailRecolteReponseDTO Create_DetailRecolte(DetailRecolteRequestDTO detailRecolteRequestDTO) throws RecolteNonTrouveeException {

        DetailRecolte detailRecolte = detailRecolteMapper.toEntity(detailRecolteRequestDTO);
        if (!recolteRepository.existsById(detailRecolte.getRecolte().getId())) {
            throw new RecolteNonTrouveeException(detailRecolte.getRecolte().getId());
        }
        if (!arbreRepository.existsById(detailRecolte.getArbre().getId())) {
            throw new ArbreNonTrouveException("L'arbre avec l'ID " + detailRecolte.getArbre().getId() + " n'a pas été trouvé.");
        }
        recolteRepository.ajouterQuantite(detailRecolte.getRecolte().getId(), detailRecolte.getQuantite());

         Optional<Recolte> recolte = recolteRepository.findById(detailRecolteRequestDTO.getIdRecolte());
         detailRecolte.setRecolte(recolte.get());
        detailRecolte = detailRecolteRepository.save(detailRecolte);

        return detailRecolteMapper.toResponseDTO(detailRecolte);
    }


    public DetailRecolteReponseDTO Modification_DetailRecolte(Long id, DetailRecolteRequestDTO detailRecolteRequestDTO) throws RecolteNonTrouveeException, ArbreNonTrouveException {

        DetailRecolte existingDetailRecolte = detailRecolteRepository.findById(id).orElseThrow(() ->
                new RecolteNonTrouveeException("Détail de récolte avec l'ID " + id + " non trouvé.")
        );

        Arbre arbre = arbreRepository.findById(detailRecolteRequestDTO.getIdArbre())
                .orElseThrow(() -> new ArbreNonTrouveException("Arbre non trouvé avec l'ID " + detailRecolteRequestDTO.getIdArbre()));

        Recolte recolte = recolteRepository.findById(detailRecolteRequestDTO.getIdRecolte())
                .orElseThrow(() -> new RecolteNonTrouveeException(detailRecolteRequestDTO.getIdRecolte()));

        existingDetailRecolte.setQuantite(detailRecolteRequestDTO.getQuantite());
        existingDetailRecolte.setArbre(arbre);
        existingDetailRecolte.setRecolte(recolte);

        existingDetailRecolte = detailRecolteRepository.save(existingDetailRecolte);

        return detailRecolteMapper.toResponseDTO(existingDetailRecolte);
    }


    public void supprimer_DetailRecolte(Long id) throws DetailRecolteNonTrouverException {
        DetailRecolte existingDetailRecolte = detailRecolteRepository.findById(id).orElseThrow(() ->
                new DetailRecolteNonTrouverException("Détail de récolte avec l'ID " + id + " non trouvé.")
        );
        detailRecolteRepository.delete(existingDetailRecolte);
    }








}
