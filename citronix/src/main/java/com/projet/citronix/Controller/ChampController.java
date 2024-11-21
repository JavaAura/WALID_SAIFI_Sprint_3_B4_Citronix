package com.projet.citronix.Controller;

import com.projet.citronix.Dto.Request.ChampRequestDTO;
import com.projet.citronix.Dto.Response.ChampResponseDTO;
import com.projet.citronix.Exception.SuperficieExceededException;
import com.projet.citronix.Service.ChampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/champs")
public class ChampController {

    @Autowired
    private ChampService champService;

    @PostMapping("/ajouter")
    public ResponseEntity<?> ajouterChamp(@RequestBody ChampRequestDTO champRequestDTO) {
        try {
            ChampResponseDTO champResponseDTO = champService.ajouterChamp(champRequestDTO);
            return new ResponseEntity<>(champResponseDTO, HttpStatus.CREATED);
        } catch (SuperficieExceededException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception ex) {
            return new ResponseEntity<>("Une erreur interne est survenue. Veuillez réessayer plus tard.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/modifier/{id}")
    public ResponseEntity<?> updateChamp(@PathVariable Long id, @RequestBody ChampRequestDTO champRequestDTO) {
        try {
            ChampResponseDTO champResponseDTO = champService.updateChamp(id, champRequestDTO);
            return new ResponseEntity<>(champResponseDTO, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (SuperficieExceededException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>("Une erreur interne est survenue. Veuillez réessayer plus tard.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<?> deleteChamp(@PathVariable Long id) {
        try {
            champService.deleteChamp(id);
            return new ResponseEntity<>("Champ supprimé avec succès.", HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>("Une erreur interne est survenue. Veuillez réessayer plus tard.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<ChampResponseDTO>> getAllChamps() {
        try {
            List<ChampResponseDTO> champs = champService.getAllChamps();

            if (champs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(champs, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
