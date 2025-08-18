package br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.exception;

import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.dtos.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleAllExceptions(Exception ex) {
        ex.printStackTrace();

        System.out.println("Ocorreu uma exceção: " + ex.getMessage());

        // Cria o DTO de erro com a mensagem da exceção
        ErrorDTO errorDTO = new ErrorDTO(ex.getMessage(), java.time.LocalDateTime.now());

        // Retorna a resposta com o DTO e o status HTTP 400 (Bad Request)
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }
}
