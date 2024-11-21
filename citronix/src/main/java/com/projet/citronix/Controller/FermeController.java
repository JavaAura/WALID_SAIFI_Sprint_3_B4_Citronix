package com.projet.citronix.Controller;

import com.projet.citronix.Dto.Request.FermeRequestDTO;
import com.projet.citronix.Dto.Response.FermeResponseDTO;
import com.projet.citronix.Service.FermeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/fermes")
public class FermeController {

    private final FermeService fermeService;

    public FermeController(FermeService fermeService) {
        this.fermeService = fermeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FermeResponseDTO> getFermeById(@PathVariable Long id) {
        FermeResponseDTO ferme = fermeService.getFermeById(id);
        if (ferme != null) {
            return ResponseEntity.ok(ferme);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<FermeResponseDTO>> getAllFermes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        List<FermeResponseDTO> fermes = fermeService.getAllFermes(page, size);
        if (fermes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(fermes);
    }

    @PostMapping
    public ResponseEntity<?> addFerme(@Valid @RequestBody FermeRequestDTO fermeRequestDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        FermeResponseDTO savedFerme = fermeService.addFerme(fermeRequestDTO);
        return new ResponseEntity<>(savedFerme, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFerme(
            @PathVariable Long id,
            @Valid @RequestBody FermeRequestDTO fermeRequestDTO,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        FermeResponseDTO updatedFerme = fermeService.updateFerme(id, fermeRequestDTO);
        if (updatedFerme != null) {
            return ResponseEntity.ok(updatedFerme);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFerme(@PathVariable Long id) {
        String message = fermeService.deleteFerme(id);
        if (message.contains("succès")) {
            return ResponseEntity.ok(message);
        }
        return ResponseEntity.notFound().build();
    }
}
