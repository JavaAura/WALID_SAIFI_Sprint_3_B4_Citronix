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


    @Transient
    private int age;

    private Double etatProductivite;

    @ManyToOne
    @JoinColumn(name = "champ_id")
    private Champ champ;

    @OneToMany(mappedBy = "arbre", cascade = CascadeType.ALL)
    private List<DetailRecolte> detailsRecolte;

    public void getAges() {
        if (this.datePlantation != null) {
            this.age = LocalDate.now().getYear() - this.datePlantation.getYear();
        } else {
            this.age = 0;
        }
    }


}
