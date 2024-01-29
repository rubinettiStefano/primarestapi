package com.generation.primarestapi.controllers.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class UniversalExceptionHandler 
{

    @ExceptionHandler(HttpMessageNotReadableException.class)
    //catcha tutte le eccezioni del tipo messo tra TONDE
    //che avvengono in qualcosi punto del controller
    public ResponseEntity<?> gestisciEccezioneConversioneJson(HttpMessageNotReadableException e)//catch(HttpMessageNotReadableException e)
    {
        //scrivo esattemente lo stesso codice che scriverei in un catch
        return new ResponseEntity<String>("Errore di conversione da Json a Oggetto", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    //catcha tutte le eccezioni del tipo messo tra TONDE
    //che avvengono in qualcosi punto del controller
    public ResponseEntity<?> gestisciEccezionPathVariable(MethodArgumentTypeMismatchException e)
    {
        //scrivo esattemente lo stesso codice che scriverei in un catch
        return new ResponseEntity<String>("Errore nella path variable", HttpStatus.BAD_REQUEST);
    }
}
