package com.projet.citronix.Controller;

import com.projet.citronix.Dto.Request.ChampRequestDTO;
import com.projet.citronix.Dto.Response.ChampResponseDTO;
import com.projet.citronix.Exception.SuperficieExceededException;
import com.projet.citronix.Service.ChampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
