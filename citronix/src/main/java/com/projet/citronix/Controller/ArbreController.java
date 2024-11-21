package com.projet.citronix.Controller;

import com.projet.citronix.Dto.Request.ArbreRequestDTO;
import com.projet.citronix.Dto.Response.ArbreResponseDTO;
import com.projet.citronix.Service.ArbreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/arbres")
public class ArbreController {

    @Autowired
    private ArbreService arbreService;

    @PostMapping(value = "/ajouter")
    public ArbreResponseDTO ajouterArbre(@RequestBody ArbreRequestDTO arbreRequestDTO) {
        ArbreResponseDTO arbreResponseDTO = arbreService.ajouterArbre(arbreRequestDTO);
        return arbreResponseDTO;
    }


    @PutMapping("/{id}")
    public ResponseEntity<ArbreResponseDTO> updateArbre(
            @PathVariable Long id,
            @RequestBody ArbreRequestDTO arbreRequestDTO) {

            ArbreResponseDTO updatedArbre = arbreService.updateArbre(id, arbreRequestDTO);
            return ResponseEntity.ok(updatedArbre);
    }



}
