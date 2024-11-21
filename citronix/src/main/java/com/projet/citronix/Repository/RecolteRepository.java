package com.projet.citronix.Repository;

import com.projet.citronix.entity.Recolte;
import com.projet.citronix.entity.enums.Saison;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecolteRepository extends JpaRepository<Recolte, Long> {

    Optional<Recolte> findBySaison(Saison saison);
}
