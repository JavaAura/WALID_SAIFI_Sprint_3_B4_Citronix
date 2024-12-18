package com.projet.citronix.Controller;
import com.projet.citronix.Dto.Request.DetailRecolteRequestDTO;
import com.projet.citronix.Dto.Response.DetailRecolteReponseDTO;
import com.projet.citronix.Exception.DetailRecolteNonTrouverException;
import com.projet.citronix.Exception.RecolteNonTrouveeException;

import com.projet.citronix.Service.Interface.IDetailRecolteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/detail")
public class DetailRecolteController {


    @Autowired
    private IDetailRecolteService detailRecolteService;

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
            return ResponseEntity.status(201).body(createMessage("Détail de récolte ajouté avec succès.", detailRecolteResponseDTO, 201));
    }

    @PutMapping("/modifier/{id}")
    public ResponseEntity<HashMap<String,Object>> Modifier_DetailRecole(@PathVariable Long id ,@RequestBody DetailRecolteRequestDTO detailRecolteRequestDTO) throws RecolteNonTrouveeException {
            DetailRecolteReponseDTO  detailRecolteReponseDTO = detailRecolteService.Modification_DetailRecolte(id,detailRecolteRequestDTO);
            return ResponseEntity.status(201).body(createMessage("Détail de récolte Modifier avec succès.", detailRecolteReponseDTO, 201));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HashMap<String,Object>> Supprimer_DetailRecolte(@PathVariable Long id) throws DetailRecolteNonTrouverException {

            detailRecolteService.supprimer_DetailRecolte(id);
             return ResponseEntity.ok(createMessage("Detail Recole supprimée avec succès", null, 200));


    }
}
