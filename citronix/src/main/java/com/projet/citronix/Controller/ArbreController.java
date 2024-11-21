package com.projet.citronix.Controller;

import com.projet.citronix.Dto.Request.ArbreRequestDTO;
import com.projet.citronix.Dto.Response.ArbreResponseDTO;
import com.projet.citronix.Service.ArbreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;

@RestController
@RequestMapping("/api/arbres")
public class ArbreController {

    @Autowired
    private ArbreService arbreService;

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
    public ResponseEntity<HashMap<String, Object>> ajouterArbre(@RequestBody ArbreRequestDTO arbreRequestDTO) {
        try {
            ArbreResponseDTO arbreResponseDTO = arbreService.ajouterArbre(arbreRequestDTO);
            return ResponseEntity.status(201).body(createMessage("Arbre ajouté avec succès", arbreResponseDTO, 201));
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(createMessage("Erreur lors de l'ajout de l'arbre", null, 500));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<HashMap<String, Object>> updateArbre(
            @PathVariable Long id,
            @RequestBody ArbreRequestDTO arbreRequestDTO) {
        try {
            ArbreResponseDTO updatedArbre = arbreService.updateArbre(id, arbreRequestDTO);
            return ResponseEntity.ok(createMessage("Arbre mis à jour avec succès", updatedArbre, 200));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(404).body(createMessage("Arbre non trouvé", null, 404));
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(createMessage("Erreur lors de la mise à jour de l'arbre", null, 500));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HashMap<String, Object>> deleteArbre(@PathVariable Long id) {
        try {
            boolean isDeleted = arbreService.deleteArbre(id);
            if (isDeleted) {
                return ResponseEntity.ok(createMessage("Arbre supprimé avec succès", null, 200));
            } else {
                return ResponseEntity.status(404).body(createMessage("Arbre non trouvé", null, 404));
            }
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(createMessage("Erreur lors de la suppression de l'arbre", null, 500));
        }
    }
}
