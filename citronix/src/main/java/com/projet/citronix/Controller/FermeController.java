package com.projet.citronix.Controller;

import com.projet.citronix.Dto.FermeDTO;
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
    public ResponseEntity<FermeDTO> getFermeById(@PathVariable Long id) {
        FermeDTO ferme = fermeService.getFermeById(id);
        if (ferme != null) {
            return ResponseEntity.ok(ferme);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<FermeDTO>> getAllFermes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        List<FermeDTO> fermes = fermeService.getAllFermes(page, size);
        if (fermes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(fermes);
    }

    @PostMapping
    public ResponseEntity<?> addFerme(@Valid @RequestBody FermeDTO fermeDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Return a Bad Request response with validation error details
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        FermeDTO savedFerme = fermeService.addFerme(fermeDTO);
        return new ResponseEntity<>(savedFerme, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFerme(@PathVariable Long id, @Valid @RequestBody FermeDTO fermeDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Return a Bad Request response with validation error details
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        FermeDTO updatedFerme = fermeService.updateFerme(id, fermeDTO);
        if (updatedFerme != null) {
            return new ResponseEntity<>(updatedFerme, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
