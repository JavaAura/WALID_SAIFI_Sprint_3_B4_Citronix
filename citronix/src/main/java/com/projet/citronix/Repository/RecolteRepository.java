package com.projet.citronix.Repository;

import com.projet.citronix.entity.Recolte;
import com.projet.citronix.entity.enums.Saison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface RecolteRepository extends JpaRepository<Recolte, Long> {

    Optional<Recolte> findBySaison(Saison saison);

    @Transactional
    @Modifying

    @Query("UPDATE Recolte r SET r.quantiteTotale = r.quantiteTotale + :quantite WHERE r.id = :id")
    void ajouterQuantite(Long id, double quantite);

    @Transactional
    @Modifying
    @Query("UPDATE Recolte r SET r.quantiteTotale = r.quantiteTotale - :quantite WHERE r.id = :id")
    void supprimerQuantite(Long id, double quantite);

}
