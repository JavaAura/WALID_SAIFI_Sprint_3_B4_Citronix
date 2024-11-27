package com.projet.citronix.Service.Interface;

import com.projet.citronix.Dto.Request.DetailRecolteRequestDTO;
import com.projet.citronix.Dto.Response.DetailRecolteReponseDTO;
import com.projet.citronix.Exception.ArbreNonTrouveException;
import com.projet.citronix.Exception.DetailRecolteNonTrouverException;
import com.projet.citronix.Exception.RecolteNonTrouveeException;

public interface IDetailRecolteService {


    DetailRecolteReponseDTO Create_DetailRecolte(DetailRecolteRequestDTO detailRecolteRequestDTO) throws RecolteNonTrouveeException;

    DetailRecolteReponseDTO Modification_DetailRecolte(Long id, DetailRecolteRequestDTO detailRecolteRequestDTO) throws RecolteNonTrouveeException, ArbreNonTrouveException;

    void supprimer_DetailRecolte(Long id) throws DetailRecolteNonTrouverException;
}
