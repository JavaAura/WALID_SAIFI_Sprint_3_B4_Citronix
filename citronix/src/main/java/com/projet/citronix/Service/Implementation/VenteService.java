package com.projet.citronix.Service.Implementation;

import com.projet.citronix.Dto.Request.VenteRequestDTO;
import com.projet.citronix.Dto.Response.VenteReponseDTO;
import com.projet.citronix.Exception.RecolteNonTrouveeException;
import com.projet.citronix.Exception.VenteNotExitException;
import com.projet.citronix.Mapper.VenteMapper;
import com.projet.citronix.Repository.VenteRepository;
import com.projet.citronix.Service.Interface.IVenteService;
import com.projet.citronix.entity.Vente;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

@AllArgsConstructor
public class VenteService  implements IVenteService {

    @Autowired
    private VenteMapper venteMapper;

    @Autowired
    private VenteRepository venteRepository;

    @Override
    public VenteReponseDTO ajouterVente(VenteRequestDTO venteRequestDTO) throws RecolteNonTrouveeException {

        Vente vente = venteMapper.toEntity(venteRequestDTO);
        venteRepository.save(vente);
        VenteReponseDTO venteReponseDTO =  venteMapper.toDTO(vente);
        venteReponseDTO.setRevenu(vente.getRevenu());
        return venteReponseDTO;
    }




    @Override
    public VenteReponseDTO modifierVente(Long id, VenteRequestDTO venteRequestDTO) throws VenteNotExitException {
        Vente venteExistante = venteRepository.findById(id)
                .orElseThrow(() -> new VenteNotExitException("Vente avec l'ID " + id + " introuvable"));

        Vente venteMiseAJour = venteMapper.toEntity(venteRequestDTO);
        venteExistante.setDate(venteMiseAJour.getDate());
        venteExistante.setPrixUnitaire(venteMiseAJour.getPrixUnitaire());
        venteExistante.setQuantite(venteMiseAJour.getQuantite());
        venteExistante.setClient(venteMiseAJour.getClient());
        venteExistante.setRecolte(venteMiseAJour.getRecolte());
        Vente venteSauvegardee = venteRepository.save(venteExistante);
        VenteReponseDTO venteReponseDTO = venteMapper.toDTO(venteSauvegardee);
        venteReponseDTO.setRevenu(venteSauvegardee.getRevenu());
        return venteReponseDTO;
    }

    @Override
    public void supprimerVente(Long id) {
        Vente vente = venteRepository.findById(id)
                .orElseThrow(() -> new VenteNotExitException("Vente avec l'ID " + id + " introuvable"));
        venteRepository.delete(vente);
    }

    @Override
    public List<VenteReponseDTO> getVentes() {
        List<Vente> ventes = venteRepository.findAll();
        return  ventes.stream().
                map(vente -> venteMapper.toDTO(vente)).collect(Collectors.toList());
    }




}
