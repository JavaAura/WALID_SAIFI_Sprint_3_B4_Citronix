package com.projet.citronix.Service;
import com.projet.citronix.Dto.Request.DetailRecolteRequestDTO;
import com.projet.citronix.Dto.Response.DetailRecolteReponseDTO;
import com.projet.citronix.Exception.ArbreNonTrouveException;
import com.projet.citronix.Exception.RecolteNonTrouveeException;
import com.projet.citronix.Mapper.DetailRecolteMapper;
import com.projet.citronix.Repository.ArbreRepository;
import com.projet.citronix.Repository.DetailRecolteRepository;
import com.projet.citronix.Repository.RecolteRepository;
import com.projet.citronix.entity.DetailRecolte;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        detailRecolte = detailRecolteRepository.save(detailRecolte);
        recolteRepository.ajouterQuantite(detailRecolte.getRecolte().getId(), detailRecolte.getQuantite());
        return detailRecolteMapper.toResponseDTO(detailRecolte);
    }







}
