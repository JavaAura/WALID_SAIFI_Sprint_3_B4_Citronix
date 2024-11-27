package com.projet.citronix.util;
import com.projet.citronix.Exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FermeNomTrouverException.class)
    public ResponseEntity<String> handleFermeNonTrouveeException(FermeNomTrouverException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ChampNonTrouveException.class)
    public ResponseEntity<String> handleChampNonTrouveException(ChampNonTrouveException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(DensiteArbresDepasseeException.class)
    public ResponseEntity<String> handleDensiteArbresDepasseeException(DensiteArbresDepasseeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(DetailRecolteNonTrouverException.class)
    public ResponseEntity<String> handleDetailRecolteNonTrouverException(DetailRecolteNonTrouverException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ErreurMetierException.class)
    public ResponseEntity<String> handleErreurMetierException(ErreurMetierException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }


    @ExceptionHandler(MiseAJourRecolteException.class)
    public ResponseEntity<String> handleMiseAJourRecolteException(MiseAJourRecolteException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(NombreDeChampsMaximumDepasseException.class)
    public ResponseEntity<String> handleNombreDeChampsMaximumDepasseException(NombreDeChampsMaximumDepasseException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(PeriodePlantationInvalideException.class)
    public ResponseEntity<String> handlePeriodePlantationInvalideException(PeriodePlantationInvalideException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(RecolteNonTrouveeException.class)
    public ResponseEntity<String> handleRecolteNonTrouveeException(RecolteNonTrouveeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
