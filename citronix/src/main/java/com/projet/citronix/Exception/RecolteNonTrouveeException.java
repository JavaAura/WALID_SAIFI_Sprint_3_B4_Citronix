package com.projet.citronix.Exception;

public class RecolteNonTrouveeException extends Exception {

    public RecolteNonTrouveeException(Long id) {
        super("Récolte introuvable avec l'identifiant : " + id);
    }
    public RecolteNonTrouveeException(String message) {
        super(message);
    }


}
