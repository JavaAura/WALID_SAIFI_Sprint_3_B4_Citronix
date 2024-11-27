package com.projet.citronix;

import com.projet.citronix.Dto.Request.VenteRequestDTO;
import com.projet.citronix.Dto.Response.VenteReponseDTO;
import com.projet.citronix.Exception.RecolteNonTrouveeException;
import com.projet.citronix.Exception.VenteNotExitException;
import com.projet.citronix.Mapper.VenteMapper;
import com.projet.citronix.Repository.VenteRepository;
import com.projet.citronix.Service.Implementation.VenteService;
import com.projet.citronix.entity.Vente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VenteServiceTest {

    @Mock
    private VenteMapper venteMapper;

    @Mock
    private VenteRepository venteRepository;

    @InjectMocks
    private VenteService venteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAjouterVente() throws RecolteNonTrouveeException {
        // Données d'entrée
        VenteRequestDTO requestDTO = new VenteRequestDTO();
        requestDTO.setDate(LocalDate.of(2024, 11, 1));
        requestDTO.setPrixUnitaire(10.0);
        requestDTO.setQuantite(5);

        Vente vente = new Vente();
        vente.setDate(LocalDate.of(2024, 11, 1));
        vente.setPrixUnitaire(10.0);
        vente.setQuantite(5);

        VenteReponseDTO responseDTO = new VenteReponseDTO();
        responseDTO.setDate(LocalDate.of(2024, 11, 1));
        responseDTO.setPrixUnitaire(10.0);
        responseDTO.setQuantite(5);
        responseDTO.setRevenu(50.0);

        // Mock des comportements
        when(venteMapper.toEntity(requestDTO)).thenReturn(vente);
        when(venteRepository.save(vente)).thenReturn(vente);
        when(venteMapper.toDTO(vente)).thenReturn(responseDTO);

        // Appel de la méthode
        VenteReponseDTO result = venteService.ajouterVente(requestDTO);

        // Vérifications
        assertNotNull(result);
        assertEquals(50.0, result.getRevenu());
        verify(venteRepository, times(1)).save(vente);
    }

    @Test
    void testModifierVente() throws VenteNotExitException {
        // Données d'entrée
        Long venteId = 1L;
        VenteRequestDTO requestDTO = new VenteRequestDTO();
        requestDTO.setDate(LocalDate.of(2024, 11, 15));
        requestDTO.setPrixUnitaire(15.0);
        requestDTO.setQuantite(10);

        Vente existingVente = new Vente();
        existingVente.setId(venteId);
        existingVente.setDate(LocalDate.of(2024, 11, 1));
        existingVente.setPrixUnitaire(10.0);
        existingVente.setQuantite(5);

        Vente updatedVente = new Vente();
        updatedVente.setId(venteId);
        updatedVente.setDate(LocalDate.of(2024, 11, 15));
        updatedVente.setPrixUnitaire(15.0);
        updatedVente.setQuantite(10);

        VenteReponseDTO responseDTO = new VenteReponseDTO();
        responseDTO.setDate(LocalDate.of(2024, 11, 15));
        responseDTO.setPrixUnitaire(15.0);
        responseDTO.setQuantite(10);
        responseDTO.setRevenu(150.0);

        // Mock des comportements
        when(venteRepository.findById(venteId)).thenReturn(Optional.of(existingVente));
        when(venteMapper.toEntity(requestDTO)).thenReturn(updatedVente);
        when(venteRepository.save(existingVente)).thenReturn(updatedVente);
        when(venteMapper.toDTO(updatedVente)).thenReturn(responseDTO);

        // Appel de la méthode
        VenteReponseDTO result = venteService.modifierVente(venteId, requestDTO);

        // Vérifications
        assertNotNull(result);
        assertEquals(150.0, result.getRevenu());
        verify(venteRepository, times(1)).save(existingVente);
    }



}
