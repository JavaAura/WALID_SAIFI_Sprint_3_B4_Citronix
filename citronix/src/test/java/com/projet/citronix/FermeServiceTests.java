package com.projet.citronix;

import com.projet.citronix.Dto.Request.FermeRequestDTO;
import com.projet.citronix.Mapper.FermeMapper;
import com.projet.citronix.Repository.FermeRepository;
import com.projet.citronix.Service.FermeService;
import com.projet.citronix.entity.Ferme;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

public class FermeServiceTests {
    @Mock
    FermeRepository fermeRepository;

    @Mock
    FermeMapper fermeMapper;

    @InjectMocks
    FermeService fermeService;

    @BeforeEach

    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void testAddFerme() {
        FermeRequestDTO requestDTO = new FermeRequestDTO(null, "Ferme SEHROUI", "Sebt Gzoula", 1500.0, LocalDate.of(2023, 11, 26));
        Ferme ferme = new Ferme();
        ferme.setNom(requestDTO.getNom());
        ferme.setLocalisation(requestDTO.getLocalisation());
        ferme.setDateCreation(requestDTO.getDateCreation());
        ferme.setSuperficie(requestDTO.getSuperficie());


    }


}
