package josiel.letscode.products.handle;

import josiel.letscode.products.exception.NoSeatsAvailableException;
import josiel.letscode.products.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler({ProductNotFoundException.class})
    public ResponseEntity<Object> hadlerTaskNotFound(ProductNotFoundException productNotFoundException){
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(productNotFoundException.getMessage());
    }

    @ExceptionHandler({NoSeatsAvailableException.class})
    public ResponseEntity<Object> hadlerTaskNotFound(NoSeatsAvailableException noSeatsAvailableException){
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(noSeatsAvailableException.getMessage());
    }
}
