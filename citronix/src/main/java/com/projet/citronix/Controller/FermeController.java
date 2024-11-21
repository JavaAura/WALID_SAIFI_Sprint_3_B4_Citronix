package com.projet.citronix.Controller;

import com.projet.citronix.Dto.Request.FermeRequestDTO;
import com.projet.citronix.Dto.Response.FermeResponseDTO;
import com.projet.citronix.Service.FermeService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/fermes")
public class FermeController {

    private final FermeService fermeService;

    public FermeController(FermeService fermeService) {
        this.fermeService = fermeService;
    }

    private HashMap<String, Object> createMessage(String message, Object data, int status) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("status", status);
        response.put("message", message);
        if (data != null) {
            response.put("data", data);
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<HashMap<String, Object>> getFermeById(@PathVariable Long id) {
        FermeResponseDTO ferme = fermeService.getFermeById(id);
        if (ferme != null) {
            return ResponseEntity.ok(createMessage("Ferme trouvée avec succès", ferme, 200));
        }
        return ResponseEntity.status(404).body(createMessage("Ferme non trouvée", null, 404));
    }

    @GetMapping
    public ResponseEntity<HashMap<String, Object>> getAllFermes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        List<FermeResponseDTO> fermes = fermeService.getAllFermes(page, size);
        if (fermes.isEmpty()) {
            return ResponseEntity.status(204).body(createMessage("Aucune ferme trouvée", null, 204));
        }
        return ResponseEntity.ok(createMessage("Liste des fermes récupérée avec succès", fermes, 200));
    }

    @PostMapping
    public ResponseEntity<HashMap<String, Object>> addFerme(@Valid @RequestBody FermeRequestDTO fermeRequestDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(createMessage("Erreurs de validation", bindingResult.getAllErrors(), 400));
        }
        FermeResponseDTO savedFerme = fermeService.addFerme(fermeRequestDTO);
        return ResponseEntity.status(201).body(createMessage("Ferme ajoutée avec succès", savedFerme, 201));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HashMap<String, Object>> updateFerme(
            @PathVariable Long id,
            @Valid @RequestBody FermeRequestDTO fermeRequestDTO,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(createMessage("Erreurs de validation", bindingResult.getAllErrors(), 400));
        }
        FermeResponseDTO updatedFerme = fermeService.updateFerme(id, fermeRequestDTO);
        if (updatedFerme != null) {
            return ResponseEntity.ok(createMessage("Ferme mise à jour avec succès", updatedFerme, 200));
        }
        return ResponseEntity.status(404).body(createMessage("Ferme non trouvée pour mise à jour", null, 404));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HashMap<String, Object>> deleteFerme(@PathVariable Long id) {
        String message = fermeService.deleteFerme(id);
        if (message.contains("succès")) {
            return ResponseEntity.ok(createMessage("Ferme supprimée avec succès", null, 200));
        }
        return ResponseEntity.status(404).body(createMessage("Ferme non trouvée pour suppression", null, 404));
    }
}
