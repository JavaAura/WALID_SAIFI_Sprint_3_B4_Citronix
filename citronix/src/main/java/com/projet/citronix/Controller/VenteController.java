package com.projet.citronix.Controller;
import com.projet.citronix.Dto.Request.VenteRequestDTO;
import com.projet.citronix.Dto.Response.VenteReponseDTO;
import com.projet.citronix.Exception.RecolteNonTrouveeException;
import com.projet.citronix.Exception.VenteNotExitException;

import com.projet.citronix.Service.Interface.IVenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/ventes")
public class VenteController {

    @Autowired
    private IVenteService venteService;


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
    @PutMapping("/modifier/{id}")
    public ResponseEntity<HashMap<String, Object>> modifierVente(@RequestBody VenteRequestDTO venteRequestDTO,@PathVariable  Long id) {
        try {
            VenteReponseDTO venteReponseDTO = venteService.modifierVente(id, venteRequestDTO);
            return ResponseEntity.status(201).body(createMessage("Vente Modifier avec succés", venteReponseDTO, 201));
        }catch (VenteNotExitException e){
            return ResponseEntity.status(404).body(createMessage(e.getMessage(), null, 404));
        }
    }

    @GetMapping("/getall")
    public ResponseEntity<HashMap<String, Object>> getAllVentes() {

        List<VenteReponseDTO> venteReponseDTO = venteService.getVentes();

        if (venteReponseDTO.isEmpty()) {
            return ResponseEntity.ok(createMessage("Aucune vente trouvée", venteReponseDTO, 200));
        }
        return ResponseEntity.ok(createMessage("Liste des ventes récupérée avec succès", venteReponseDTO, 200));
    }

    @DeleteMapping("suppreme/{id}")
    public ResponseEntity<HashMap<String, Object>> supprimerVente(@PathVariable Long id) {
        try {
            venteService.supprimerVente(id);
            return ResponseEntity.ok(createMessage("Vente supprimé avec succès", null, 200));
        } catch (VenteNotExitException e) {
            return ResponseEntity.status(404).body(createMessage(e.getMessage(), null, 404));
        }
    }

}