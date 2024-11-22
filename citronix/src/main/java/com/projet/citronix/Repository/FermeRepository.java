package com.projet.citronix.Repository;

import com.projet.citronix.entity.Ferme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FermeRepository extends JpaRepository<Ferme,Long> {

    @Query("SELECT COUNT(c) FROM Champ c WHERE c.ferme.id = :fermeId")
    long countChampsByFermeId(@Param("fermeId") Long fermeId);


}
