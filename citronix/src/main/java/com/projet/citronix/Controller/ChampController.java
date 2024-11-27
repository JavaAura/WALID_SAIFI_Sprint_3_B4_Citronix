package com.projet.citronix.Controller;

import com.projet.citronix.Dto.Request.ChampRequestDTO;
import com.projet.citronix.Dto.Response.ChampResponseDTO;
import com.projet.citronix.Exception.SuperficieExceededException;
import com.projet.citronix.Service.Interface.IChampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/champs")
public class ChampController {

    @Autowired
    private IChampService champService;

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
            ChampResponseDTO champResponseDTO = champService.ajouterChamp(champRequestDTO);
            return ResponseEntity.status(201).body(createMessage("Champ ajouté avec succès", champResponseDTO, 201));

    }

    @PutMapping("/modifier/{id}")
    public ResponseEntity<HashMap<String, Object>> updateChamp(@PathVariable Long id, @RequestBody ChampRequestDTO champRequestDTO) {

            ChampResponseDTO champResponseDTO = champService.updateChamp(id, champRequestDTO);
            return ResponseEntity.ok(createMessage("Champ mis à jour avec succès", champResponseDTO, 200));
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<HashMap<String, Object>> deleteChamp(@PathVariable Long id) {

            champService.deleteChamp(id);
            return ResponseEntity.ok(createMessage("Champ supprimé avec succès", null, 200));

    }

    @GetMapping
    public ResponseEntity<HashMap<String, Object>> getAllChamps() {

            List<ChampResponseDTO> champs = champService.getAllChamps();
            if (champs.isEmpty()) {
                return ResponseEntity.status(204).body(createMessage("Aucun champ trouvé", null, 204));
            }
            return ResponseEntity.ok(createMessage("Liste des champs récupérée avec succès", champs, 200));

    }
}
