package com.projet.citronix.Exception;

public class MiseAJourRecolteException extends Exception {

    public MiseAJourRecolteException(Long id) {
        super("Impossible de mettre à jour la récolte : aucune récolte trouvée avec l'identifiant : " + id);
    }

}
