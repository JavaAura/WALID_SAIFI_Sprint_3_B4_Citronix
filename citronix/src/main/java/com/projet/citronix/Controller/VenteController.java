package com.projet.citronix.Controller;
import com.projet.citronix.Dto.Request.VenteRequestDTO;
import com.projet.citronix.Dto.Response.VenteReponseDTO;
import com.projet.citronix.Exception.RecolteNonTrouveeException;
import com.projet.citronix.Service.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api/ventes")
public class VenteController {

    @Autowired
    private VenteService venteService;


    private HashMap<String, Object> createMessage(String message, Object data, int status) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("status", status);
        response.put("message", message);
        if (data != null) {
            response.put("data", data);
        }
        return response;
    }
    @PostMapping("/ajouter")
    public ResponseEntity<HashMap<String, Object>> ajouterVente(@RequestBody VenteRequestDTO venteRequestDTO) {
        try {
            VenteReponseDTO venteReponseDTO = venteService.ajouterVente(venteRequestDTO);
            return ResponseEntity.status(201).body(createMessage("Vente ajoutée avec succès", venteReponseDTO, 201));
        } catch (RecolteNonTrouveeException ex) {
            return ResponseEntity.status(404).body(createMessage(ex.getMessage(), null, 404));
        }
    }
}