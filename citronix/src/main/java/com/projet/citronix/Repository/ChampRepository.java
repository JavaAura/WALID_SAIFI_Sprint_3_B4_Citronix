package com.projet.citronix.Repository;


import com.projet.citronix.entity.Champ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChampRepository extends JpaRepository<Champ, Long> {
    @Query("SELECT COUNT(a) FROM Arbre a WHERE a.champ.id = :champId")

    long NombreArbresParChampId(@Param("champId") Long champId);
}
