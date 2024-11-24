package com.projet.citronix.Controller;


import com.projet.citronix.Dto.Request.DetailRecolteRequestDTO;
import com.projet.citronix.Dto.Response.DetailRecolteReponseDTO;
import com.projet.citronix.Exception.RecolteNonTrouveeException;
import com.projet.citronix.Service.DetailRecolteService;
import com.projet.citronix.entity.DetailRecolte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api/detail")
public class DetailRecolteController {


    @Autowired
    private DetailRecolteService detailRecolteService;

    private HashMap<String, Object> createMessage(String message, Object data, int status) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("status", status);
        response.put("message", message);
        if (data != null) {
            response.put("data", data);
        }
        return response;
    }

    @PostMapping
    public ResponseEntity<HashMap<String, Object>> ajouterDetailRecolte(@RequestBody DetailRecolteRequestDTO detailRecolteRequestDTO) throws RecolteNonTrouveeException {
            DetailRecolteReponseDTO detailRecolteResponseDTO = detailRecolteService.Create_DetailRecolte(detailRecolteRequestDTO);
            return ResponseEntity.status(201).body(createMessage("Détail de récolte ajouté avec succès", detailRecolteResponseDTO, 201));
    }

}
