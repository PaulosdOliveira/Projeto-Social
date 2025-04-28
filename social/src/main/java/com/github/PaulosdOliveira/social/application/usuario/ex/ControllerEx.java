package com.github.PaulosdOliveira.social.application.usuario.ex;

import com.github.PaulosdOliveira.social.ex.EmailDuplicadoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerEx {


    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(EmailDuplicadoException.class)
    public String handleEmailDuplicadoException(EmailDuplicadoException e) {
        return e.getMessage();
    }


    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  Map<String, Object> CamposNaoPreenchidosEx(MethodArgumentNotValidException e){
       Map<String, Object> camposErros = new HashMap<>();
       e.getFieldErrors().forEach( erro ->{
           camposErros.put(erro.getField(), erro.getDefaultMessage());
       });
       return camposErros;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public String internlaError(RuntimeException e){
        System.out.println(e.getMessage());
        return "Ocorreu um erro inesperado";
    }



}
