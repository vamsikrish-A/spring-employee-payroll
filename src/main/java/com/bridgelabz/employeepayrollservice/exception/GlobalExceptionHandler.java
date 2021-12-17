package com.bridgelabz.employeepayrollservice.exception;
/**
 * @purpose: @ControllerAdvice annotation which allows to handle exceptions across te whole application in one
 *              Global Handling Component
 * @author: VamsiKrishna
 * @since: 13-12-2021
 */
import com.bridgelabz.employeepayrollservice.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ResponseDto> handleCustomException(CustomException exception) {
        ResponseDto responseDto = new ResponseDto(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<ResponseDto>(responseDto, (HttpStatus) responseDto.getData());
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ResponseDto> handleNotFoundException(DataNotFoundException exception) {
        ResponseDto responseDto = new ResponseDto(exception.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity(responseDto, (HttpStatus) responseDto.getData());
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ResponseDto> handleBadRequestExceptionException(BadRequestException exception) {
        ResponseDto responseDto = new ResponseDto(exception.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity(responseDto, (HttpStatus) responseDto.getData());
    }


}
