package com.projet.citronix.Service;


import com.projet.citronix.Repository.DetailRecolteRepository;
import com.projet.citronix.entity.DetailRecolte;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class DetailRecolteService {
    private DetailRecolteRepository detailRecolteRepository;

    public Optional<DetailRecolte> findDetailRecolteById(Long id){
        return detailRecolteRepository.findById(id);
    }


}
