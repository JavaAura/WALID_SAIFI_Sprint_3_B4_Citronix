package com.projet.citronix.Controller;

import com.projet.citronix.Dto.Request.ChampRequestDTO;
import com.projet.citronix.Dto.Response.ChampResponseDTO;
import com.projet.citronix.Exception.SuperficieExceededException;
import com.projet.citronix.Service.ChampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/champs")
public class ChampController {

    @Autowired
    private ChampService champService;

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
    public ResponseEntity<HashMap<String, Object>> ajouterChamp(@RequestBody ChampRequestDTO champRequestDTO) {
        try {
            ChampResponseDTO champResponseDTO = champService.ajouterChamp(champRequestDTO);
            return ResponseEntity.status(201).body(createMessage("Champ ajouté avec succès", champResponseDTO, 201));
        } catch (SuperficieExceededException ex) {
            return ResponseEntity.badRequest().body(createMessage(ex.getMessage(), null, 400));
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(createMessage("Une erreur interne est survenue. Veuillez réessayer plus tard.", null, 500));
        }
    }

    @PutMapping("/modifier/{id}")
    public ResponseEntity<HashMap<String, Object>> updateChamp(@PathVariable Long id, @RequestBody ChampRequestDTO champRequestDTO) {
        try {
            ChampResponseDTO champResponseDTO = champService.updateChamp(id, champRequestDTO);
            return ResponseEntity.ok(createMessage("Champ mis à jour avec succès", champResponseDTO, 200));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(404).body(createMessage(ex.getMessage(), null, 404));
        } catch (SuperficieExceededException ex) {
            return ResponseEntity.badRequest().body(createMessage(ex.getMessage(), null, 400));
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(createMessage("Une erreur interne est survenue. Veuillez réessayer plus tard.", null, 500));
        }
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<HashMap<String, Object>> deleteChamp(@PathVariable Long id) {
        try {
            champService.deleteChamp(id);
            return ResponseEntity.ok(createMessage("Champ supprimé avec succès", null, 200));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(404).body(createMessage(ex.getMessage(), null, 404));
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(createMessage("Une erreur interne est survenue. Veuillez réessayer plus tard.", null, 500));
        }
    }

    @GetMapping
    public ResponseEntity<HashMap<String, Object>> getAllChamps() {
        try {
            List<ChampResponseDTO> champs = champService.getAllChamps();
            if (champs.isEmpty()) {
                return ResponseEntity.status(204).body(createMessage("Aucun champ trouvé", null, 204));
            }
            return ResponseEntity.ok(createMessage("Liste des champs récupérée avec succès", champs, 200));
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(createMessage("Une erreur interne est survenue. Veuillez réessayer plus tard.", null, 500));
        }
    }
}
