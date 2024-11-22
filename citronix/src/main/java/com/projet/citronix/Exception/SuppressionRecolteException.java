package com.projet.citronix.Exception;

public class SuppressionRecolteException extends Exception {

    public SuppressionRecolteException(Long id) {
        super("Impossible de supprimer la récolte : aucune récolte trouvée avec l'identifiant : " + id);
    }

}
