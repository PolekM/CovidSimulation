package pl.covidSimulation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

public class APIExceptionHandler {

    public ResponseEntity<Error> ExceptionBuilder(HttpStatus httpStatus,String massage){

        Error error = new Error(httpStatus.value(),massage,new Date());
        return new ResponseEntity<>(error, httpStatus);

    }

    @ExceptionHandler()
    public ResponseEntity<Error> handleBadRequestException(Exception exception){
        return ExceptionBuilder(HttpStatus.BAD_REQUEST,exception.getMessage());
    }
}
