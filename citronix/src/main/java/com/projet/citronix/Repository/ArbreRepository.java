package com.projet.citronix.Repository;

import com.projet.citronix.entity.Arbre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArbreRepository extends JpaRepository<Arbre, Long> {
}
