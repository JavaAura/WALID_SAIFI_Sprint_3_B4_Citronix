package com.projet.citronix.Controller;

import com.projet.citronix.Dto.Request.ArbreRequestDTO;
import com.projet.citronix.Dto.Response.ArbreResponseDTO;
import com.projet.citronix.Service.ArbreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/arbres")
public class ArbreController {

    @Autowired
    private ArbreService arbreService;

    @PostMapping("/ajouter")
    public ResponseEntity<ArbreResponseDTO> ajouterArbre(@RequestBody ArbreRequestDTO arbreRequestDTO) {

        ArbreResponseDTO arbreResponseDTO = arbreService.ajouterArbre(arbreRequestDTO);

        return new ResponseEntity<>(arbreResponseDTO, HttpStatus.CREATED);
    }


}
