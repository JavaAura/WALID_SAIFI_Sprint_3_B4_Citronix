package com.projet.citronix.Controller;
import com.projet.citronix.Dto.Request.RecolteRequestDTO;
import com.projet.citronix.Dto.Response.RecolteResponseDTO;
import com.projet.citronix.Exception.MiseAJourRecolteException;
import com.projet.citronix.Exception.RecolteNonTrouveeException;
import com.projet.citronix.Exception.SuppressionRecolteException;
import com.projet.citronix.Service.RecolteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/recoltes")
public class RecolteController {

    @Autowired
    private RecolteService recolteService;

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
    public ResponseEntity<HashMap<String, Object>> ajouterRecolte(@RequestBody RecolteRequestDTO recolteRequestDTO) {
        try {
            RecolteResponseDTO recolteResponseDTO = recolteService.createRecolte(recolteRequestDTO);
            return ResponseEntity.status(201).body(createMessage("Récolte ajoutée avec succès", recolteResponseDTO, 201));
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(createMessage("Erreur lors de l'ajout de la récolte", null, 500));
        }
    }

    @GetMapping
    public ResponseEntity<HashMap<String, Object>> getAllRecoltes() {
        try {
            List<RecolteResponseDTO> recoltes = recolteService.getAllRecoltes();
            return ResponseEntity.ok(createMessage("Liste des récoltes récupérée avec succès", recoltes, 200));
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(createMessage("Erreur lors de la récupération des récoltes", null, 500));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<HashMap<String, Object>> getRecolteById(@PathVariable Long id) {
        try {
            RecolteResponseDTO recolteResponseDTO = recolteService.getRecolteById(id);
            return ResponseEntity.ok(createMessage("Récolte récupérée avec succès", recolteResponseDTO, 200));
        } catch (RecolteNonTrouveeException ex) {
            return ResponseEntity.status(404).body(createMessage(ex.getMessage(), null, 404));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<HashMap<String, Object>> updateRecolte(
            @PathVariable Long id,
            @RequestBody RecolteRequestDTO recolteRequestDTO) {
        try {
            RecolteResponseDTO updatedRecolte = recolteService.updateRecolte(id, recolteRequestDTO);
            return ResponseEntity.ok(createMessage("Récolte mise à jour avec succès", updatedRecolte, 200));
        } catch (MiseAJourRecolteException ex) {
            return ResponseEntity.status(404).body(createMessage(ex.getMessage(), null, 404));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HashMap<String, Object>> deleteRecolte(@PathVariable Long id) {
        try {
            recolteService.deleteRecolte(id);
            return ResponseEntity.ok(createMessage("Récolte supprimée avec succès", null, 200));
        } catch (SuppressionRecolteException ex) {
            return ResponseEntity.status(404).body(createMessage(ex.getMessage(), null, 404));
        }
    }
}
