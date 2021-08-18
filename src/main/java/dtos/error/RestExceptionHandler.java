package dtos.error;

import io.zipcoder.tc_spring_poll_application.exception.ResourceNotFoundException;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfe, HttpServletRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setDetail(rnfe.getMessage());
        errorDetail.setDeveloperMessage(rnfe.getClass().getName());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setTitle("Resource not found");
        return new ResponseEntity<>(errorDetail,null,HttpStatus.NOT_FOUND);
    }

}
