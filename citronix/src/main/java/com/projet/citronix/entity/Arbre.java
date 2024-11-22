package com.projet.citronix.entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    private Double etatProductivite;

    @ManyToOne
    @JoinColumn(name = "champ_id")
    private Champ champ;

    @OneToMany(mappedBy = "arbre", cascade = CascadeType.ALL)
    private List<DetailRecolte> detailsRecolte;



    public void updateAge() {
        if (this.datePlantation != null) {
            this.age = LocalDate.now().getYear() - this.datePlantation.getYear();
        } else {
            this.age = 0;
        }
    }

    public double calculateEtatProductivite() {
        updateAge();
        if (age < 3) {
            return 2.5;
        } else if (age <= 10) {
            return 12.0;
        } else {
            return 20.0;
        }
    }

    public void updateEtatProductivite() {
        this.etatProductivite = calculateEtatProductivite();
    }





}
