package com.villamorvinzie.view.middleware;

import com.villamorvinzie.view.dto.response.ErrorResponseDto;
import com.villamorvinzie.view.exception.UserAlreadyExistAuthenticationException;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleException(Exception ex) {
        ErrorResponseDto ret = new ErrorResponseDto(ex.getMessage(), LocalDateTime.now(), null);
        return ResponseEntity.internalServerError().body(ret);
    }

    @ExceptionHandler({UserAlreadyExistAuthenticationException.class, BadCredentialsException.class})
    public ResponseEntity<ErrorResponseDto> handleBadRequestExceptions(Exception ex) {
        ErrorResponseDto ret = new ErrorResponseDto(ex.getMessage(), LocalDateTime.now(), null);
        return ResponseEntity.badRequest().body(ret);
    }

    @ExceptionHandler({UsernameNotFoundException.class})
    public ResponseEntity<ErrorResponseDto> handleNotFoundExceptions(Exception ex) {
        ErrorResponseDto ret = new ErrorResponseDto(ex.getMessage(), LocalDateTime.now(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ret);
    }
}
