package com.Citronix.app.entity;

import com.Citronix.app.entity.enums.EtatProductivite;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Arbre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate datePlantation;

    private int age;

    @Enumerated(EnumType.STRING)
    private EtatProductivite etatProductivite;

    @ManyToOne
    @JoinColumn(name = "champ_id", nullable = false)
    private Champ champ;

    @OneToMany(mappedBy = "arbre", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetailRecolte> detailsRecolte;

    public void calculerAge() {
        this.age = LocalDate.now().getYear() - this.datePlantation.getYear();
    }

}
