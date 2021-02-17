package com.marcelosantos.cursomc.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.marcelosantos.cursomc.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class RsourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);		
	}
}



//FLUXO DA EXCEÇÃO
//
//1 - Manipulador das exceções = ResourceExceptionHandler // captura as exceções e retorna erro customizado
//metodo 	ResponseEntity<StandardError> ObjectNotfound()
//
//2 - Criação exceção customizada = objectNotfoundException 
//
//
//3 - objeto auxiliar da exceção = Classe StandardError
//Atributos  = status, msg, TimeStamp
//
//CAMADA DE SERVIÇO
//
//4 - categoriaService
//CategoriaBuscar() // metodo de servico
//throw objectNotfoundException(ex:"Objeto não encontrado") // feito chamada da exceção no service
//
//5 CAMADA DE RECURSO
//caso o metodo da camada de serviço retornar nulo, a resposta da exceção é enviada para a camada de recurso