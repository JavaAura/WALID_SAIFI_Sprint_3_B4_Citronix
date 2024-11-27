package com.projet.citronix;

import com.projet.citronix.Dto.Request.FermeRequestDTO;
import com.projet.citronix.Dto.Response.FermeResponseDTO;
import com.projet.citronix.Mapper.FermeMapper;
import com.projet.citronix.Repository.FermeRepository;
import com.projet.citronix.Service.Implementation.FermeService;
import com.projet.citronix.entity.Ferme;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FermeServiceTest {

    @Mock
    private FermeRepository fermeRepository;

    @Mock
    private FermeMapper fermeMapper;

    @InjectMocks
    private FermeService fermeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddFerme() {
        // Données d'entrée
        FermeRequestDTO requestDTO = new FermeRequestDTO();
        requestDTO.setNom("Ferme 1");
        requestDTO.setLocalisation("Localisation 1");
        requestDTO.setSuperficie(100.0);
        requestDTO.setDateCreation(LocalDate.of(2023, 1, 1));

        Ferme ferme = new Ferme();
        ferme.setNom("Ferme 1");
        ferme.setLocalisation("Localisation 1");
        ferme.setSuperficie(100.0);
        ferme.setDateCreation(LocalDate.of(2023, 1, 1));

        Ferme savedFerme = new Ferme();
        savedFerme.setId(1L);
        savedFerme.setNom("Ferme 1");
        savedFerme.setLocalisation("Localisation 1");
        savedFerme.setSuperficie(100.0);
        savedFerme.setDateCreation(LocalDate.of(2023, 1, 1));

        FermeResponseDTO responseDTO = new FermeResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setNom("Ferme 1");
        responseDTO.setLocalisation("Localisation 1");
        responseDTO.setSuperficie(100.0);
        responseDTO.setDateCreation(LocalDate.of(2023, 1, 1));

        // Mock des comportements
        when(fermeMapper.toEntity(requestDTO)).thenReturn(ferme);
        when(fermeRepository.save(ferme)).thenReturn(savedFerme);
        when(fermeMapper.toResponseDTO(savedFerme)).thenReturn(responseDTO);

        // Appel de la méthode
        FermeResponseDTO result = fermeService.addFerme(requestDTO);

        // Vérifications
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Ferme 1", result.getNom());
        verify(fermeRepository, times(1)).save(ferme);
    }

    @Test
    void testUpdateFerme() {
        // Données d'entrée
        Long fermeId = 1L;
        FermeRequestDTO requestDTO = new FermeRequestDTO();
        requestDTO.setNom("Ferme Modifiée");
        requestDTO.setLocalisation("Nouvelle Localisation");
        requestDTO.setSuperficie(150.0);
        requestDTO.setDateCreation(LocalDate.of(2023, 5, 1));

        Ferme existingFerme = new Ferme();
        existingFerme.setId(fermeId);
        existingFerme.setNom("Ferme Originale");
        existingFerme.setLocalisation("Localisation Originale");
        existingFerme.setSuperficie(100.0);
        existingFerme.setDateCreation(LocalDate.of(2023, 1, 1));

        Ferme updatedFerme = new Ferme();
        updatedFerme.setId(fermeId);
        updatedFerme.setNom("Ferme Modifiée");
        updatedFerme.setLocalisation("Nouvelle Localisation");
        updatedFerme.setSuperficie(150.0);
        updatedFerme.setDateCreation(LocalDate.of(2023, 5, 1));

        FermeResponseDTO responseDTO = new FermeResponseDTO();
        responseDTO.setId(fermeId);
        responseDTO.setNom("Ferme Modifiée");
        responseDTO.setLocalisation("Nouvelle Localisation");
        responseDTO.setSuperficie(150.0);
        responseDTO.setDateCreation(LocalDate.of(2023, 5, 1));

        // Mock des comportements
        when(fermeRepository.findById(fermeId)).thenReturn(Optional.of(existingFerme));
        when(fermeRepository.save(existingFerme)).thenReturn(updatedFerme);
        when(fermeMapper.toResponseDTO(updatedFerme)).thenReturn(responseDTO);

        // Appel de la méthode
        FermeResponseDTO result = fermeService.updateFerme(fermeId, requestDTO);

        // Vérifications
        assertNotNull(result);
        assertEquals(fermeId, result.getId());
        assertEquals("Ferme Modifiée", result.getNom());
        verify(fermeRepository, times(1)).save(existingFerme);
    }



}
