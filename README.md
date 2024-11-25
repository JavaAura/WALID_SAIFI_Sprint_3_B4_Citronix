# Projet Citronix

## Contexte du Projet
Le projet **Citronix** consiste à développer une application de gestion pour une ferme de citrons, permettant aux fermiers de suivre la production, la récolte et la vente de leurs produits. L'application doit faciliter la gestion des fermes, des champs, des arbres, des récoltes, et des ventes, tout en optimisant le suivi de la productivité des arbres en fonction de leur âge.

---

## Fonctionnalités Principales

### Gestion des Fermes
- Créer, modifier et consulter les informations d'une ferme (nom, localisation, superficie, date de création).
- Recherche multicritère (Criteria Builder).

### Gestion des Champs
- Associer des champs à une ferme avec une superficie définie.
- La somme des superficies des champs d'une ferme doit être strictement inférieure à celle de la ferme.
- La superficie minimale des champs est de 0,1 hectare (1 000 m²) et la superficie maximale d'un champ ne peut pas dépasser 50% de la superficie totale de la ferme.
- Une ferme ne peut contenir plus de 10 champs.
- Chaque champ doit contenir un nombre d'arbres tel que la densité maximale est de 100 arbres par hectare (10 arbres par 1 000 m²).

### Gestion des Arbres
- Suivi des arbres avec leur date de plantation, âge, et champ d'appartenance.
- Calcul de l'âge des arbres.
- La productivité annuelle est déterminée en fonction de l'âge de l'arbre :
    - Arbre jeune (< 3 ans) : 2,5 kg / saison.
    - Arbre mature (3-10 ans) : 12 kg / saison.
    - Arbre vieux (> 10 ans) : 20 kg / saison.
- Les arbres ne peuvent pas dépasser 20 ans de productivité.

### Gestion des Récoltes
- Suivi des récoltes par saison (hiver, printemps, été, automne).
- Une seule récolte par saison (tous les 3 mois).
- Enregistrement de la date de récolte et de la quantité totale récoltée.
- Chaque champ ne peut être associé qu'à une seule récolte par saison.
- Un arbre ne peut pas être inclus dans plus d’une récolte pour une même saison.

### Détail des Récoltes
- Suivi de la quantité récoltée par arbre pour une récolte donnée.
- Association de chaque détail de récolte à un arbre spécifique.

### Gestion des Ventes
- Enregistrement des ventes avec la date, le prix unitaire, le client, et la récolte associée.
- Calcul du revenu : Revenu = quantité * prixUnitaire.


