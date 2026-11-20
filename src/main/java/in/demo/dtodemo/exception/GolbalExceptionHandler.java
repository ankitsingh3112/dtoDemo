package in.demo.dtodemo.exception;

import in.demo.dtodemo.dto.ExceptionDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GolbalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleResourceNotFoundException(
            ResourceNotFoundException ex,HttpServletRequest request){

        ExceptionDto exceptionDto=new ExceptionDto(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exceptionDto);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ExceptionDto> handleDuplicateResourceException(
            DuplicateResourceException ex, HttpServletRequest request){

        ExceptionDto exceptionDto=new ExceptionDto(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );


        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exceptionDto);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionDto> handleRuntimeException(
            RuntimeException ex,HttpServletRequest request){

        ExceptionDto exceptionDto=new ExceptionDto(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exceptionDto);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handleGenericException(Exception ex){

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Something went wrong. Please try again later");
    }
}
